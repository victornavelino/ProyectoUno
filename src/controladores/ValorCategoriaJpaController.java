/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.ValorCategoria;
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
public class ValorCategoriaJpaController implements Serializable {

    public ValorCategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValorCategoria valorCategoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valorCategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValorCategoria valorCategoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valorCategoria = em.merge(valorCategoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = valorCategoria.getId();
                if (findValorCategoria(id) == null) {
                    throw new NonexistentEntityException("The valorCategoria with id " + id + " no longer exists.");
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
            ValorCategoria valorCategoria;
            try {
                valorCategoria = em.getReference(ValorCategoria.class, id);
                valorCategoria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valorCategoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(valorCategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValorCategoria> findValorCategoriaEntities() {
        return findValorCategoriaEntities(true, -1, -1);
    }

    public List<ValorCategoria> findValorCategoriaEntities(int maxResults, int firstResult) {
        return findValorCategoriaEntities(false, maxResults, firstResult);
    }

    private List<ValorCategoria> findValorCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValorCategoria.class));
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

    public ValorCategoria findValorCategoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValorCategoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getValorCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValorCategoria> rt = cq.from(ValorCategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
