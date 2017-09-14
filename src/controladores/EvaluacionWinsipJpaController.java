/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.EvaluacionWinsip;
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
 * @author hugo
 */
public class EvaluacionWinsipJpaController implements Serializable {

    public EvaluacionWinsipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EvaluacionWinsip evaluacionWinsip) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(evaluacionWinsip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EvaluacionWinsip evaluacionWinsip) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            evaluacionWinsip = em.merge(evaluacionWinsip);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = evaluacionWinsip.getId();
                if (findEvaluacionWinsip(id) == null) {
                    throw new NonexistentEntityException("The evaluacionWinsip with id " + id + " no longer exists.");
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
            EvaluacionWinsip evaluacionWinsip;
            try {
                evaluacionWinsip = em.getReference(EvaluacionWinsip.class, id);
                evaluacionWinsip.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacionWinsip with id " + id + " no longer exists.", enfe);
            }
            em.remove(evaluacionWinsip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EvaluacionWinsip> findEvaluacionWinsipEntities() {
        return findEvaluacionWinsipEntities(true, -1, -1);
    }

    public List<EvaluacionWinsip> findEvaluacionWinsipEntities(int maxResults, int firstResult) {
        return findEvaluacionWinsipEntities(false, maxResults, firstResult);
    }

    private List<EvaluacionWinsip> findEvaluacionWinsipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EvaluacionWinsip.class));
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

    public EvaluacionWinsip findEvaluacionWinsip(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EvaluacionWinsip.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionWinsipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EvaluacionWinsip> rt = cq.from(EvaluacionWinsip.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
