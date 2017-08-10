/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.RecursoSubsidio;
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
public class RecursoSubsidioJpaController implements Serializable {

    public RecursoSubsidioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecursoSubsidio recursoSubsidio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(recursoSubsidio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecursoSubsidio recursoSubsidio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            recursoSubsidio = em.merge(recursoSubsidio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = recursoSubsidio.getId();
                if (findRecursoSubsidio(id) == null) {
                    throw new NonexistentEntityException("The recursoSubsidio with id " + id + " no longer exists.");
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
            RecursoSubsidio recursoSubsidio;
            try {
                recursoSubsidio = em.getReference(RecursoSubsidio.class, id);
                recursoSubsidio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recursoSubsidio with id " + id + " no longer exists.", enfe);
            }
            em.remove(recursoSubsidio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecursoSubsidio> findRecursoSubsidioEntities() {
        return findRecursoSubsidioEntities(true, -1, -1);
    }

    public List<RecursoSubsidio> findRecursoSubsidioEntities(int maxResults, int firstResult) {
        return findRecursoSubsidioEntities(false, maxResults, firstResult);
    }

    private List<RecursoSubsidio> findRecursoSubsidioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecursoSubsidio.class));
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

    public RecursoSubsidio findRecursoSubsidio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecursoSubsidio.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecursoSubsidioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecursoSubsidio> rt = cq.from(RecursoSubsidio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
