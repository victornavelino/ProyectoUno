/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
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
 * @author Panchi
 */
public class FinanciacionPfipJpaController implements Serializable {

    public FinanciacionPfipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FinanciacionPfip financiacionPfip) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(financiacionPfip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FinanciacionPfip financiacionPfip) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            financiacionPfip = em.merge(financiacionPfip);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = financiacionPfip.getId();
                if (findFinanciacionPfip(id) == null) {
                    throw new NonexistentEntityException("The financiacionPfip with id " + id + " no longer exists.");
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
            FinanciacionPfip financiacionPfip;
            try {
                financiacionPfip = em.getReference(FinanciacionPfip.class, id);
                financiacionPfip.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financiacionPfip with id " + id + " no longer exists.", enfe);
            }
            em.remove(financiacionPfip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FinanciacionPfip> findFinanciacionPfipEntities() {
        return findFinanciacionPfipEntities(true, -1, -1);
    }

    public List<FinanciacionPfip> findFinanciacionPfipEntities(int maxResults, int firstResult) {
        return findFinanciacionPfipEntities(false, maxResults, firstResult);
    }

    private List<FinanciacionPfip> findFinanciacionPfipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FinanciacionPfip.class));
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

    public FinanciacionPfip findFinanciacionPfip(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FinanciacionPfip.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanciacionPfipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FinanciacionPfip> rt = cq.from(FinanciacionPfip.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
