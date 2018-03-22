/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.evaluacion.BecasItemEvaluar;
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
 * @author diego
 */
public class BecasItemEvaluarJpaController implements Serializable {

    public BecasItemEvaluarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BecasItemEvaluar becasItemEvaluar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(becasItemEvaluar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BecasItemEvaluar becasItemEvaluar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            becasItemEvaluar = em.merge(becasItemEvaluar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = becasItemEvaluar.getId();
                if (findBecasItemEvaluar(id) == null) {
                    throw new NonexistentEntityException("The becasItemEvaluar with id " + id + " no longer exists.");
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
            BecasItemEvaluar becasItemEvaluar;
            try {
                becasItemEvaluar = em.getReference(BecasItemEvaluar.class, id);
                becasItemEvaluar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The becasItemEvaluar with id " + id + " no longer exists.", enfe);
            }
            em.remove(becasItemEvaluar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BecasItemEvaluar> findBecasItemEvaluarEntities() {
        return findBecasItemEvaluarEntities(true, -1, -1);
    }

    public List<BecasItemEvaluar> findBecasItemEvaluarEntities(int maxResults, int firstResult) {
        return findBecasItemEvaluarEntities(false, maxResults, firstResult);
    }

    private List<BecasItemEvaluar> findBecasItemEvaluarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BecasItemEvaluar.class));
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

    public BecasItemEvaluar findBecasItemEvaluar(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BecasItemEvaluar.class, id);
        } finally {
            em.close();
        }
    }

    public int getBecasItemEvaluarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BecasItemEvaluar> rt = cq.from(BecasItemEvaluar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
