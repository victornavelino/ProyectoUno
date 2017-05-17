/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.BienConsumoWeb;
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
public class BienConsumoWebJpaController implements Serializable {

    public BienConsumoWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BienConsumoWeb bienConsumoWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bienConsumoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BienConsumoWeb bienConsumoWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bienConsumoWeb = em.merge(bienConsumoWeb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bienConsumoWeb.getId();
                if (findBienConsumoWeb(id) == null) {
                    throw new NonexistentEntityException("The bienConsumoWeb with id " + id + " no longer exists.");
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
            BienConsumoWeb bienConsumoWeb;
            try {
                bienConsumoWeb = em.getReference(BienConsumoWeb.class, id);
                bienConsumoWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bienConsumoWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(bienConsumoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BienConsumoWeb> findBienConsumoWebEntities() {
        return findBienConsumoWebEntities(true, -1, -1);
    }

    public List<BienConsumoWeb> findBienConsumoWebEntities(int maxResults, int firstResult) {
        return findBienConsumoWebEntities(false, maxResults, firstResult);
    }

    private List<BienConsumoWeb> findBienConsumoWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BienConsumoWeb.class));
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

    public BienConsumoWeb findBienConsumoWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BienConsumoWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getBienConsumoWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BienConsumoWeb> rt = cq.from(BienConsumoWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
