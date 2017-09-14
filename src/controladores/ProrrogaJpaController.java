/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Prorroga;
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
public class ProrrogaJpaController implements Serializable {

    public ProrrogaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prorroga prorroga) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prorroga);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prorroga prorroga) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prorroga = em.merge(prorroga);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prorroga.getId();
                if (findProrroga(id) == null) {
                    throw new NonexistentEntityException("The prorroga with id " + id + " no longer exists.");
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
            Prorroga prorroga;
            try {
                prorroga = em.getReference(Prorroga.class, id);
                prorroga.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prorroga with id " + id + " no longer exists.", enfe);
            }
            em.remove(prorroga);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prorroga> findProrrogaEntities() {
        return findProrrogaEntities(true, -1, -1);
    }

    public List<Prorroga> findProrrogaEntities(int maxResults, int firstResult) {
        return findProrrogaEntities(false, maxResults, firstResult);
    }

    private List<Prorroga> findProrrogaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prorroga.class));
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

    public Prorroga findProrroga(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prorroga.class, id);
        } finally {
            em.close();
        }
    }

    public int getProrrogaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prorroga> rt = cq.from(Prorroga.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
