/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.EntidadEvaluadora;
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
public class EntidadEvaluadoraJpaController implements Serializable {

    public EntidadEvaluadoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadEvaluadora entidadEvaluadora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidadEvaluadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadEvaluadora entidadEvaluadora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entidadEvaluadora = em.merge(entidadEvaluadora);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidadEvaluadora.getId();
                if (findEntidadEvaluadora(id) == null) {
                    throw new NonexistentEntityException("The entidadEvaluadora with id " + id + " no longer exists.");
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
            EntidadEvaluadora entidadEvaluadora;
            try {
                entidadEvaluadora = em.getReference(EntidadEvaluadora.class, id);
                entidadEvaluadora.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadEvaluadora with id " + id + " no longer exists.", enfe);
            }
            em.remove(entidadEvaluadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadEvaluadora> findEntidadEvaluadoraEntities() {
        return findEntidadEvaluadoraEntities(true, -1, -1);
    }

    public List<EntidadEvaluadora> findEntidadEvaluadoraEntities(int maxResults, int firstResult) {
        return findEntidadEvaluadoraEntities(false, maxResults, firstResult);
    }

    private List<EntidadEvaluadora> findEntidadEvaluadoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntidadEvaluadora.class));
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

    public EntidadEvaluadora findEntidadEvaluadora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadEvaluadora.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadEvaluadoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntidadEvaluadora> rt = cq.from(EntidadEvaluadora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
