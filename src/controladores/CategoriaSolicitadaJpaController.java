/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.CategoriaSolicitada;
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
public class CategoriaSolicitadaJpaController implements Serializable {

    public CategoriaSolicitadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaSolicitada categoriaSolicitada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaSolicitada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaSolicitada categoriaSolicitada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaSolicitada = em.merge(categoriaSolicitada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoriaSolicitada.getId();
                if (findCategoriaSolicitada(id) == null) {
                    throw new NonexistentEntityException("The categoriaSolicitada with id " + id + " no longer exists.");
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
            CategoriaSolicitada categoriaSolicitada;
            try {
                categoriaSolicitada = em.getReference(CategoriaSolicitada.class, id);
                categoriaSolicitada.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaSolicitada with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaSolicitada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaSolicitada> findCategoriaSolicitadaEntities() {
        return findCategoriaSolicitadaEntities(true, -1, -1);
    }

    public List<CategoriaSolicitada> findCategoriaSolicitadaEntities(int maxResults, int firstResult) {
        return findCategoriaSolicitadaEntities(false, maxResults, firstResult);
    }

    private List<CategoriaSolicitada> findCategoriaSolicitadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaSolicitada.class));
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

    public CategoriaSolicitada findCategoriaSolicitada(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaSolicitada.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaSolicitadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaSolicitada> rt = cq.from(CategoriaSolicitada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
