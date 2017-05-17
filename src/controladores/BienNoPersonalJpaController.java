/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.BienNoPersonal;
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
 * @author walter
 */
public class BienNoPersonalJpaController implements Serializable {

    public BienNoPersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BienNoPersonal bienNoPersonal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bienNoPersonal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BienNoPersonal bienNoPersonal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bienNoPersonal = em.merge(bienNoPersonal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bienNoPersonal.getId();
                if (findBienNoPersonal(id) == null) {
                    throw new NonexistentEntityException("The bienNoPersonal with id " + id + " no longer exists.");
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
            BienNoPersonal bienNoPersonal;
            try {
                bienNoPersonal = em.getReference(BienNoPersonal.class, id);
                bienNoPersonal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bienNoPersonal with id " + id + " no longer exists.", enfe);
            }
            em.remove(bienNoPersonal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BienNoPersonal> findBienNoPersonalEntities() {
        return findBienNoPersonalEntities(true, -1, -1);
    }

    public List<BienNoPersonal> findBienNoPersonalEntities(int maxResults, int firstResult) {
        return findBienNoPersonalEntities(false, maxResults, firstResult);
    }

    private List<BienNoPersonal> findBienNoPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BienNoPersonal.class));
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

    public BienNoPersonal findBienNoPersonal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BienNoPersonal.class, id);
        } finally {
            em.close();
        }
    }

    public int getBienNoPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BienNoPersonal> rt = cq.from(BienNoPersonal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
