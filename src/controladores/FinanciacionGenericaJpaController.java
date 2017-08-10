/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.FinanciacionGenerica;
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
public class FinanciacionGenericaJpaController implements Serializable {

    public FinanciacionGenericaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FinanciacionGenerica financiacionGenerica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(financiacionGenerica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FinanciacionGenerica financiacionGenerica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            financiacionGenerica = em.merge(financiacionGenerica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = financiacionGenerica.getId();
                if (findFinanciacionGenerica(id) == null) {
                    throw new NonexistentEntityException("The financiacionGenerica with id " + id + " no longer exists.");
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
            FinanciacionGenerica financiacionGenerica;
            try {
                financiacionGenerica = em.getReference(FinanciacionGenerica.class, id);
                financiacionGenerica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financiacionGenerica with id " + id + " no longer exists.", enfe);
            }
            em.remove(financiacionGenerica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FinanciacionGenerica> findFinanciacionGenericaEntities() {
        return findFinanciacionGenericaEntities(true, -1, -1);
    }

    public List<FinanciacionGenerica> findFinanciacionGenericaEntities(int maxResults, int firstResult) {
        return findFinanciacionGenericaEntities(false, maxResults, firstResult);
    }

    private List<FinanciacionGenerica> findFinanciacionGenericaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FinanciacionGenerica.class));
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

    public FinanciacionGenerica findFinanciacionGenerica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FinanciacionGenerica.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanciacionGenericaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FinanciacionGenerica> rt = cq.from(FinanciacionGenerica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
