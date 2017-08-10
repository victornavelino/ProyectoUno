/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.CategoriaAnterior;
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
public class CategoriaAnteriorJpaController implements Serializable {

    public CategoriaAnteriorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaAnterior categoriaAnterior) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaAnterior);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaAnterior categoriaAnterior) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaAnterior = em.merge(categoriaAnterior);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoriaAnterior.getId();
                if (findCategoriaAnterior(id) == null) {
                    throw new NonexistentEntityException("The categoriaAnterior with id " + id + " no longer exists.");
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
            CategoriaAnterior categoriaAnterior;
            try {
                categoriaAnterior = em.getReference(CategoriaAnterior.class, id);
                categoriaAnterior.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaAnterior with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaAnterior);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaAnterior> findCategoriaAnteriorEntities() {
        return findCategoriaAnteriorEntities(true, -1, -1);
    }

    public List<CategoriaAnterior> findCategoriaAnteriorEntities(int maxResults, int firstResult) {
        return findCategoriaAnteriorEntities(false, maxResults, firstResult);
    }

    private List<CategoriaAnterior> findCategoriaAnteriorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaAnterior.class));
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

    public CategoriaAnterior findCategoriaAnterior(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaAnterior.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaAnteriorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaAnterior> rt = cq.from(CategoriaAnterior.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
