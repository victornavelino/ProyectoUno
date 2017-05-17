/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPict;
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
 * @author ruben
 */
public class FinanciacionPictJpaController implements Serializable {

    public FinanciacionPictJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FinanciacionPict financiacionPict) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(financiacionPict);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FinanciacionPict financiacionPict) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            financiacionPict = em.merge(financiacionPict);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = financiacionPict.getId();
                if (findFinanciacionPict(id) == null) {
                    throw new NonexistentEntityException("The financiacionPict with id " + id + " no longer exists.");
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
            FinanciacionPict financiacionPict;
            try {
                financiacionPict = em.getReference(FinanciacionPict.class, id);
                financiacionPict.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financiacionPict with id " + id + " no longer exists.", enfe);
            }
            em.remove(financiacionPict);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FinanciacionPict> findFinanciacionPictEntities() {
        return findFinanciacionPictEntities(true, -1, -1);
    }

    public List<FinanciacionPict> findFinanciacionPictEntities(int maxResults, int firstResult) {
        return findFinanciacionPictEntities(false, maxResults, firstResult);
    }

    private List<FinanciacionPict> findFinanciacionPictEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FinanciacionPict.class));
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

    public FinanciacionPict findFinanciacionPict(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FinanciacionPict.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanciacionPictCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FinanciacionPict> rt = cq.from(FinanciacionPict.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
