/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.PresupuestoWeb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyectoWeb.ProyectoWeb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class PresupuestoWebJpaController implements Serializable {

    public PresupuestoWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PresupuestoWeb presupuestoWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb proyectoWeb = presupuestoWeb.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb = em.getReference(proyectoWeb.getClass(), proyectoWeb.getId());
                presupuestoWeb.setProyectoWeb(proyectoWeb);
            }
            em.persist(presupuestoWeb);
            if (proyectoWeb != null) {
                PresupuestoWeb oldPresupuestoWebOfProyectoWeb = proyectoWeb.getPresupuestoWeb();
                if (oldPresupuestoWebOfProyectoWeb != null) {
                    oldPresupuestoWebOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebOfProyectoWeb = em.merge(oldPresupuestoWebOfProyectoWeb);
                }
                proyectoWeb.setPresupuestoWeb(presupuestoWeb);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PresupuestoWeb presupuestoWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWeb persistentPresupuestoWeb = em.find(PresupuestoWeb.class, presupuestoWeb.getId());
            ProyectoWeb proyectoWebOld = persistentPresupuestoWeb.getProyectoWeb();
            ProyectoWeb proyectoWebNew = presupuestoWeb.getProyectoWeb();
            if (proyectoWebNew != null) {
                proyectoWebNew = em.getReference(proyectoWebNew.getClass(), proyectoWebNew.getId());
                presupuestoWeb.setProyectoWeb(proyectoWebNew);
            }
            presupuestoWeb = em.merge(presupuestoWeb);
            if (proyectoWebOld != null && !proyectoWebOld.equals(proyectoWebNew)) {
                proyectoWebOld.setPresupuestoWeb(null);
                proyectoWebOld = em.merge(proyectoWebOld);
            }
            if (proyectoWebNew != null && !proyectoWebNew.equals(proyectoWebOld)) {
                PresupuestoWeb oldPresupuestoWebOfProyectoWeb = proyectoWebNew.getPresupuestoWeb();
                if (oldPresupuestoWebOfProyectoWeb != null) {
                    oldPresupuestoWebOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebOfProyectoWeb = em.merge(oldPresupuestoWebOfProyectoWeb);
                }
                proyectoWebNew.setPresupuestoWeb(presupuestoWeb);
                proyectoWebNew = em.merge(proyectoWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuestoWeb.getId();
                if (findPresupuestoWeb(id) == null) {
                    throw new NonexistentEntityException("The presupuestoWeb with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWeb presupuestoWeb;
            try {
                presupuestoWeb = em.getReference(PresupuestoWeb.class, id);
                presupuestoWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuestoWeb with id " + id + " no longer exists.", enfe);
            }
            ProyectoWeb proyectoWeb = presupuestoWeb.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb.setPresupuestoWeb(null);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.remove(presupuestoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PresupuestoWeb> findPresupuestoWebEntities() {
        return findPresupuestoWebEntities(true, -1, -1);
    }

    public List<PresupuestoWeb> findPresupuestoWebEntities(int maxResults, int firstResult) {
        return findPresupuestoWebEntities(false, maxResults, firstResult);
    }

    private List<PresupuestoWeb> findPresupuestoWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PresupuestoWeb.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PresupuestoWeb findPresupuestoWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PresupuestoWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PresupuestoWeb> rt = cq.from(PresupuestoWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
