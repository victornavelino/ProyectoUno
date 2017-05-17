/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.PresupuestoWebDetalladoCuatro;
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
public class PresupuestoWebDetalladoCuatroJpaController implements Serializable {

    public PresupuestoWebDetalladoCuatroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PresupuestoWebDetalladoCuatro presupuestoWebDetalladoCuatro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb proyectoWeb = presupuestoWebDetalladoCuatro.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb = em.getReference(proyectoWeb.getClass(), proyectoWeb.getId());
                presupuestoWebDetalladoCuatro.setProyectoWeb(proyectoWeb);
            }
            em.persist(presupuestoWebDetalladoCuatro);
            if (proyectoWeb != null) {
                PresupuestoWebDetalladoCuatro oldPresupuestoWebAnioCuatroOfProyectoWeb = proyectoWeb.getPresupuestoWebAnioCuatro();
                if (oldPresupuestoWebAnioCuatroOfProyectoWeb != null) {
                    oldPresupuestoWebAnioCuatroOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioCuatroOfProyectoWeb = em.merge(oldPresupuestoWebAnioCuatroOfProyectoWeb);
                }
                proyectoWeb.setPresupuestoWebAnioCuatro(presupuestoWebDetalladoCuatro);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PresupuestoWebDetalladoCuatro presupuestoWebDetalladoCuatro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWebDetalladoCuatro persistentPresupuestoWebDetalladoCuatro = em.find(PresupuestoWebDetalladoCuatro.class, presupuestoWebDetalladoCuatro.getId());
            ProyectoWeb proyectoWebOld = persistentPresupuestoWebDetalladoCuatro.getProyectoWeb();
            ProyectoWeb proyectoWebNew = presupuestoWebDetalladoCuatro.getProyectoWeb();
            if (proyectoWebNew != null) {
                proyectoWebNew = em.getReference(proyectoWebNew.getClass(), proyectoWebNew.getId());
                presupuestoWebDetalladoCuatro.setProyectoWeb(proyectoWebNew);
            }
            presupuestoWebDetalladoCuatro = em.merge(presupuestoWebDetalladoCuatro);
            if (proyectoWebOld != null && !proyectoWebOld.equals(proyectoWebNew)) {
                proyectoWebOld.setPresupuestoWebAnioCuatro(null);
                proyectoWebOld = em.merge(proyectoWebOld);
            }
            if (proyectoWebNew != null && !proyectoWebNew.equals(proyectoWebOld)) {
                PresupuestoWebDetalladoCuatro oldPresupuestoWebAnioCuatroOfProyectoWeb = proyectoWebNew.getPresupuestoWebAnioCuatro();
                if (oldPresupuestoWebAnioCuatroOfProyectoWeb != null) {
                    oldPresupuestoWebAnioCuatroOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioCuatroOfProyectoWeb = em.merge(oldPresupuestoWebAnioCuatroOfProyectoWeb);
                }
                proyectoWebNew.setPresupuestoWebAnioCuatro(presupuestoWebDetalladoCuatro);
                proyectoWebNew = em.merge(proyectoWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuestoWebDetalladoCuatro.getId();
                if (findPresupuestoWebDetalladoCuatro(id) == null) {
                    throw new NonexistentEntityException("The presupuestoWebDetalladoCuatro with id " + id + " no longer exists.");
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
            PresupuestoWebDetalladoCuatro presupuestoWebDetalladoCuatro;
            try {
                presupuestoWebDetalladoCuatro = em.getReference(PresupuestoWebDetalladoCuatro.class, id);
                presupuestoWebDetalladoCuatro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuestoWebDetalladoCuatro with id " + id + " no longer exists.", enfe);
            }
            ProyectoWeb proyectoWeb = presupuestoWebDetalladoCuatro.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb.setPresupuestoWebAnioCuatro(null);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.remove(presupuestoWebDetalladoCuatro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PresupuestoWebDetalladoCuatro> findPresupuestoWebDetalladoCuatroEntities() {
        return findPresupuestoWebDetalladoCuatroEntities(true, -1, -1);
    }

    public List<PresupuestoWebDetalladoCuatro> findPresupuestoWebDetalladoCuatroEntities(int maxResults, int firstResult) {
        return findPresupuestoWebDetalladoCuatroEntities(false, maxResults, firstResult);
    }

    private List<PresupuestoWebDetalladoCuatro> findPresupuestoWebDetalladoCuatroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PresupuestoWebDetalladoCuatro.class));
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

    public PresupuestoWebDetalladoCuatro findPresupuestoWebDetalladoCuatro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PresupuestoWebDetalladoCuatro.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoWebDetalladoCuatroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PresupuestoWebDetalladoCuatro> rt = cq.from(PresupuestoWebDetalladoCuatro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
