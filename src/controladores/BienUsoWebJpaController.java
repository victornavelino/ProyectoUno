/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.BienUsoWeb;
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
public class BienUsoWebJpaController implements Serializable {

    public BienUsoWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BienUsoWeb bienUsoWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bienUsoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BienUsoWeb bienUsoWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bienUsoWeb = em.merge(bienUsoWeb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bienUsoWeb.getId();
                if (findBienUsoWeb(id) == null) {
                    throw new NonexistentEntityException("The bienUsoWeb with id " + id + " no longer exists.");
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
            BienUsoWeb bienUsoWeb;
            try {
                bienUsoWeb = em.getReference(BienUsoWeb.class, id);
                bienUsoWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bienUsoWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(bienUsoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BienUsoWeb> findBienUsoWebEntities() {
        return findBienUsoWebEntities(true, -1, -1);
    }

    public List<BienUsoWeb> findBienUsoWebEntities(int maxResults, int firstResult) {
        return findBienUsoWebEntities(false, maxResults, firstResult);
    }

    private List<BienUsoWeb> findBienUsoWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BienUsoWeb.class));
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

    public BienUsoWeb findBienUsoWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BienUsoWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getBienUsoWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BienUsoWeb> rt = cq.from(BienUsoWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
