/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.PresupuestoWebDetalladoTres;
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
public class PresupuestoWebDetalladoTresJpaController implements Serializable {

    public PresupuestoWebDetalladoTresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PresupuestoWebDetalladoTres presupuestoWebDetalladoTres) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb proyectoWeb = presupuestoWebDetalladoTres.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb = em.getReference(proyectoWeb.getClass(), proyectoWeb.getId());
                presupuestoWebDetalladoTres.setProyectoWeb(proyectoWeb);
            }
            em.persist(presupuestoWebDetalladoTres);
            if (proyectoWeb != null) {
                PresupuestoWebDetalladoTres oldPresupuestoWebAnioTresOfProyectoWeb = proyectoWeb.getPresupuestoWebAnioTres();
                if (oldPresupuestoWebAnioTresOfProyectoWeb != null) {
                    oldPresupuestoWebAnioTresOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioTresOfProyectoWeb = em.merge(oldPresupuestoWebAnioTresOfProyectoWeb);
                }
                proyectoWeb.setPresupuestoWebAnioTres(presupuestoWebDetalladoTres);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PresupuestoWebDetalladoTres presupuestoWebDetalladoTres) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWebDetalladoTres persistentPresupuestoWebDetalladoTres = em.find(PresupuestoWebDetalladoTres.class, presupuestoWebDetalladoTres.getId());
            ProyectoWeb proyectoWebOld = persistentPresupuestoWebDetalladoTres.getProyectoWeb();
            ProyectoWeb proyectoWebNew = presupuestoWebDetalladoTres.getProyectoWeb();
            if (proyectoWebNew != null) {
                proyectoWebNew = em.getReference(proyectoWebNew.getClass(), proyectoWebNew.getId());
                presupuestoWebDetalladoTres.setProyectoWeb(proyectoWebNew);
            }
            presupuestoWebDetalladoTres = em.merge(presupuestoWebDetalladoTres);
            if (proyectoWebOld != null && !proyectoWebOld.equals(proyectoWebNew)) {
                proyectoWebOld.setPresupuestoWebAnioTres(null);
                proyectoWebOld = em.merge(proyectoWebOld);
            }
            if (proyectoWebNew != null && !proyectoWebNew.equals(proyectoWebOld)) {
                PresupuestoWebDetalladoTres oldPresupuestoWebAnioTresOfProyectoWeb = proyectoWebNew.getPresupuestoWebAnioTres();
                if (oldPresupuestoWebAnioTresOfProyectoWeb != null) {
                    oldPresupuestoWebAnioTresOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioTresOfProyectoWeb = em.merge(oldPresupuestoWebAnioTresOfProyectoWeb);
                }
                proyectoWebNew.setPresupuestoWebAnioTres(presupuestoWebDetalladoTres);
                proyectoWebNew = em.merge(proyectoWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuestoWebDetalladoTres.getId();
                if (findPresupuestoWebDetalladoTres(id) == null) {
                    throw new NonexistentEntityException("The presupuestoWebDetalladoTres with id " + id + " no longer exists.");
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
            PresupuestoWebDetalladoTres presupuestoWebDetalladoTres;
            try {
                presupuestoWebDetalladoTres = em.getReference(PresupuestoWebDetalladoTres.class, id);
                presupuestoWebDetalladoTres.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuestoWebDetalladoTres with id " + id + " no longer exists.", enfe);
            }
            ProyectoWeb proyectoWeb = presupuestoWebDetalladoTres.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb.setPresupuestoWebAnioTres(null);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.remove(presupuestoWebDetalladoTres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PresupuestoWebDetalladoTres> findPresupuestoWebDetalladoTresEntities() {
        return findPresupuestoWebDetalladoTresEntities(true, -1, -1);
    }

    public List<PresupuestoWebDetalladoTres> findPresupuestoWebDetalladoTresEntities(int maxResults, int firstResult) {
        return findPresupuestoWebDetalladoTresEntities(false, maxResults, firstResult);
    }

    private List<PresupuestoWebDetalladoTres> findPresupuestoWebDetalladoTresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PresupuestoWebDetalladoTres.class));
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

    public PresupuestoWebDetalladoTres findPresupuestoWebDetalladoTres(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PresupuestoWebDetalladoTres.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoWebDetalladoTresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PresupuestoWebDetalladoTres> rt = cq.from(PresupuestoWebDetalladoTres.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
