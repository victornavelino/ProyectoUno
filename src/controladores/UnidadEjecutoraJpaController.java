/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadEjecutora;
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
 * @author franco
 */
public class UnidadEjecutoraJpaController implements Serializable {

    public UnidadEjecutoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadEjecutora unidadEjecutora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(unidadEjecutora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadEjecutora unidadEjecutora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            unidadEjecutora = em.merge(unidadEjecutora);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = unidadEjecutora.getId();
                if (findUnidadEjecutora(id) == null) {
                    throw new NonexistentEntityException("The unidadEjecutora with id " + id + " no longer exists.");
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
            UnidadEjecutora unidadEjecutora;
            try {
                unidadEjecutora = em.getReference(UnidadEjecutora.class, id);
                unidadEjecutora.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadEjecutora with id " + id + " no longer exists.", enfe);
            }
            em.remove(unidadEjecutora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadEjecutora> findUnidadEjecutoraEntities() {
        return findUnidadEjecutoraEntities(true, -1, -1);
    }

    public List<UnidadEjecutora> findUnidadEjecutoraEntities(int maxResults, int firstResult) {
        return findUnidadEjecutoraEntities(false, maxResults, firstResult);
    }

    private List<UnidadEjecutora> findUnidadEjecutoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadEjecutora.class));
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

    public UnidadEjecutora findUnidadEjecutora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadEjecutora.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadEjecutoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadEjecutora> rt = cq.from(UnidadEjecutora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
