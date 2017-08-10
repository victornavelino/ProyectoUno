/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.LineaPrioritaria;
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
public class LineaPrioritariaJpaController implements Serializable {

    public LineaPrioritariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LineaPrioritaria lineaPrioritaria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lineaPrioritaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LineaPrioritaria lineaPrioritaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lineaPrioritaria = em.merge(lineaPrioritaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = lineaPrioritaria.getId();
                if (findLineaPrioritaria(id) == null) {
                    throw new NonexistentEntityException("The lineaPrioritaria with id " + id + " no longer exists.");
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
            LineaPrioritaria lineaPrioritaria;
            try {
                lineaPrioritaria = em.getReference(LineaPrioritaria.class, id);
                lineaPrioritaria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineaPrioritaria with id " + id + " no longer exists.", enfe);
            }
            em.remove(lineaPrioritaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LineaPrioritaria> findLineaPrioritariaEntities() {
        return findLineaPrioritariaEntities(true, -1, -1);
    }

    public List<LineaPrioritaria> findLineaPrioritariaEntities(int maxResults, int firstResult) {
        return findLineaPrioritariaEntities(false, maxResults, firstResult);
    }

    private List<LineaPrioritaria> findLineaPrioritariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LineaPrioritaria.class));
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

    public LineaPrioritaria findLineaPrioritaria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LineaPrioritaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineaPrioritariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LineaPrioritaria> rt = cq.from(LineaPrioritaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
