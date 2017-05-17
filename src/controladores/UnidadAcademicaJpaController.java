/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadAcademica;
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
public class UnidadAcademicaJpaController implements Serializable {

    public UnidadAcademicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadAcademica unidadAcademica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(unidadAcademica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadAcademica unidadAcademica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            unidadAcademica = em.merge(unidadAcademica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = unidadAcademica.getId();
                if (findUnidadAcademica(id) == null) {
                    throw new NonexistentEntityException("The unidadAcademica with id " + id + " no longer exists.");
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
            UnidadAcademica unidadAcademica;
            try {
                unidadAcademica = em.getReference(UnidadAcademica.class, id);
                unidadAcademica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadAcademica with id " + id + " no longer exists.", enfe);
            }
            em.remove(unidadAcademica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadAcademica> findUnidadAcademicaEntities() {
        return findUnidadAcademicaEntities(true, -1, -1);
    }

    public List<UnidadAcademica> findUnidadAcademicaEntities(int maxResults, int firstResult) {
        return findUnidadAcademicaEntities(false, maxResults, firstResult);
    }

    private List<UnidadAcademica> findUnidadAcademicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadAcademica.class));
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

    public UnidadAcademica findUnidadAcademica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadAcademica.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadAcademicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadAcademica> rt = cq.from(UnidadAcademica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
