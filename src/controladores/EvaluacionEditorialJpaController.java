/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EvaluacionEditorial;
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
 * @author francisco
 */
public class EvaluacionEditorialJpaController implements Serializable {

    public EvaluacionEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EvaluacionEditorial evaluacionEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(evaluacionEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EvaluacionEditorial evaluacionEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            evaluacionEditorial = em.merge(evaluacionEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = evaluacionEditorial.getId();
                if (findEvaluacionEditorial(id) == null) {
                    throw new NonexistentEntityException("The evaluacionEditorial with id " + id + " no longer exists.");
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
            EvaluacionEditorial evaluacionEditorial;
            try {
                evaluacionEditorial = em.getReference(EvaluacionEditorial.class, id);
                evaluacionEditorial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacionEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(evaluacionEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EvaluacionEditorial> findEvaluacionEditorialEntities() {
        return findEvaluacionEditorialEntities(true, -1, -1);
    }

    public List<EvaluacionEditorial> findEvaluacionEditorialEntities(int maxResults, int firstResult) {
        return findEvaluacionEditorialEntities(false, maxResults, firstResult);
    }

    private List<EvaluacionEditorial> findEvaluacionEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EvaluacionEditorial.class));
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

    public EvaluacionEditorial findEvaluacionEditorial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EvaluacionEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EvaluacionEditorial> rt = cq.from(EvaluacionEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
