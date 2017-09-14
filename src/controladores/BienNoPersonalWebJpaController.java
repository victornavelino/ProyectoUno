/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.BienNoPersonalWeb;
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
public class BienNoPersonalWebJpaController implements Serializable {

    public BienNoPersonalWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BienNoPersonalWeb bienNoPersonalWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bienNoPersonalWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BienNoPersonalWeb bienNoPersonalWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bienNoPersonalWeb = em.merge(bienNoPersonalWeb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bienNoPersonalWeb.getId();
                if (findBienNoPersonalWeb(id) == null) {
                    throw new NonexistentEntityException("The bienNoPersonalWeb with id " + id + " no longer exists.");
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
            BienNoPersonalWeb bienNoPersonalWeb;
            try {
                bienNoPersonalWeb = em.getReference(BienNoPersonalWeb.class, id);
                bienNoPersonalWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bienNoPersonalWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(bienNoPersonalWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BienNoPersonalWeb> findBienNoPersonalWebEntities() {
        return findBienNoPersonalWebEntities(true, -1, -1);
    }

    public List<BienNoPersonalWeb> findBienNoPersonalWebEntities(int maxResults, int firstResult) {
        return findBienNoPersonalWebEntities(false, maxResults, firstResult);
    }

    private List<BienNoPersonalWeb> findBienNoPersonalWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BienNoPersonalWeb.class));
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

    public BienNoPersonalWeb findBienNoPersonalWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BienNoPersonalWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getBienNoPersonalWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BienNoPersonalWeb> rt = cq.from(BienNoPersonalWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
