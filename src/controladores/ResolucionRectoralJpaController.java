/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionRectoral;
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
public class ResolucionRectoralJpaController implements Serializable {

    public ResolucionRectoralJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResolucionRectoral resolucionRectoral) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resolucionRectoral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResolucionRectoral resolucionRectoral) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resolucionRectoral = em.merge(resolucionRectoral);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resolucionRectoral.getId();
                if (findResolucionRectoral(id) == null) {
                    throw new NonexistentEntityException("The resolucionRectoral with id " + id + " no longer exists.");
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
            ResolucionRectoral resolucionRectoral;
            try {
                resolucionRectoral = em.getReference(ResolucionRectoral.class, id);
                resolucionRectoral.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucionRectoral with id " + id + " no longer exists.", enfe);
            }
            em.remove(resolucionRectoral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResolucionRectoral> findResolucionRectoralEntities() {
        return findResolucionRectoralEntities(true, -1, -1);
    }

    public List<ResolucionRectoral> findResolucionRectoralEntities(int maxResults, int firstResult) {
        return findResolucionRectoralEntities(false, maxResults, firstResult);
    }

    private List<ResolucionRectoral> findResolucionRectoralEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResolucionRectoral.class));
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

    public ResolucionRectoral findResolucionRectoral(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResolucionRectoral.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionRectoralCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResolucionRectoral> rt = cq.from(ResolucionRectoral.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
