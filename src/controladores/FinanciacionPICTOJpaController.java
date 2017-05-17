/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPICTO;
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
public class FinanciacionPICTOJpaController implements Serializable {

    public FinanciacionPICTOJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FinanciacionPICTO financiacionPICTO) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(financiacionPICTO);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FinanciacionPICTO financiacionPICTO) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            financiacionPICTO = em.merge(financiacionPICTO);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = financiacionPICTO.getId();
                if (findFinanciacionPICTO(id) == null) {
                    throw new NonexistentEntityException("The financiacionPICTO with id " + id + " no longer exists.");
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
            FinanciacionPICTO financiacionPICTO;
            try {
                financiacionPICTO = em.getReference(FinanciacionPICTO.class, id);
                financiacionPICTO.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financiacionPICTO with id " + id + " no longer exists.", enfe);
            }
            em.remove(financiacionPICTO);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FinanciacionPICTO> findFinanciacionPICTOEntities() {
        return findFinanciacionPICTOEntities(true, -1, -1);
    }

    public List<FinanciacionPICTO> findFinanciacionPICTOEntities(int maxResults, int firstResult) {
        return findFinanciacionPICTOEntities(false, maxResults, firstResult);
    }

    private List<FinanciacionPICTO> findFinanciacionPICTOEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FinanciacionPICTO.class));
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

    public FinanciacionPICTO findFinanciacionPICTO(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FinanciacionPICTO.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanciacionPICTOCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FinanciacionPICTO> rt = cq.from(FinanciacionPICTO.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
