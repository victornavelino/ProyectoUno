/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.RendicionDetalle;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author walter
 */
public class RendicionDetalleJpaController implements Serializable {

    public RendicionDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RendicionDetalle rendicionDetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rendicionDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RendicionDetalle rendicionDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rendicionDetalle = em.merge(rendicionDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rendicionDetalle.getId();
                if (findRendicionDetalle(id) == null) {
                    throw new NonexistentEntityException("The rendicionDetalle with id " + id + " no longer exists.");
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
            RendicionDetalle rendicionDetalle;
            try {
                rendicionDetalle = em.getReference(RendicionDetalle.class, id);
                rendicionDetalle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rendicionDetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(rendicionDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RendicionDetalle> findRendicionDetalleEntities() {
        return findRendicionDetalleEntities(true, -1, -1);
    }

    public List<RendicionDetalle> findRendicionDetalleEntities(int maxResults, int firstResult) {
        return findRendicionDetalleEntities(false, maxResults, firstResult);
    }

    private List<RendicionDetalle> findRendicionDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RendicionDetalle.class));
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

    public RendicionDetalle findRendicionDetalle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RendicionDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getRendicionDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RendicionDetalle> rt = cq.from(RendicionDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
