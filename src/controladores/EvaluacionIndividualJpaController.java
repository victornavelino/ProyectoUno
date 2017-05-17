/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.persona.Evaluador;
import entidades.proyecto.EvaluacionIndividual;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class EvaluacionIndividualJpaController implements Serializable {

    public EvaluacionIndividualJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EvaluacionIndividual evaluacionIndividual) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evaluador evaluador = evaluacionIndividual.getEvaluador();
            if (evaluador != null) {
                evaluador = em.getReference(evaluador.getClass(), evaluador.getId());
                evaluacionIndividual.setEvaluador(evaluador);
            }
            em.persist(evaluacionIndividual);
            if (evaluador != null) {
                evaluador.getEvaluaciones().add(evaluacionIndividual);
                evaluador = em.merge(evaluador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EvaluacionIndividual evaluacionIndividual) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EvaluacionIndividual persistentEvaluacionIndividual = em.find(EvaluacionIndividual.class, evaluacionIndividual.getId());
            Evaluador evaluadorOld = persistentEvaluacionIndividual.getEvaluador();
            Evaluador evaluadorNew = evaluacionIndividual.getEvaluador();
            if (evaluadorNew != null) {
                evaluadorNew = em.getReference(evaluadorNew.getClass(), evaluadorNew.getId());
                evaluacionIndividual.setEvaluador(evaluadorNew);
            }
            evaluacionIndividual = em.merge(evaluacionIndividual);
            if (evaluadorOld != null && !evaluadorOld.equals(evaluadorNew)) {
                evaluadorOld.getEvaluaciones().remove(evaluacionIndividual);
                evaluadorOld = em.merge(evaluadorOld);
            }
            if (evaluadorNew != null && !evaluadorNew.equals(evaluadorOld)) {
                evaluadorNew.getEvaluaciones().add(evaluacionIndividual);
                evaluadorNew = em.merge(evaluadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = evaluacionIndividual.getId();
                if (findEvaluacionIndividual(id) == null) {
                    throw new NonexistentEntityException("The evaluacionIndividual with id " + id + " no longer exists.");
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
            EvaluacionIndividual evaluacionIndividual;
            try {
                evaluacionIndividual = em.getReference(EvaluacionIndividual.class, id);
                evaluacionIndividual.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacionIndividual with id " + id + " no longer exists.", enfe);
            }
            Evaluador evaluador = evaluacionIndividual.getEvaluador();
            if (evaluador != null) {
                evaluador.getEvaluaciones().remove(evaluacionIndividual);
                evaluador = em.merge(evaluador);
            }
            em.remove(evaluacionIndividual);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EvaluacionIndividual> findEvaluacionIndividualEntities() {
        return findEvaluacionIndividualEntities(true, -1, -1);
    }

    public List<EvaluacionIndividual> findEvaluacionIndividualEntities(int maxResults, int firstResult) {
        return findEvaluacionIndividualEntities(false, maxResults, firstResult);
    }

    private List<EvaluacionIndividual> findEvaluacionIndividualEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EvaluacionIndividual.class));
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

    public EvaluacionIndividual findEvaluacionIndividual(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EvaluacionIndividual.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionIndividualCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EvaluacionIndividual> rt = cq.from(EvaluacionIndividual.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
