/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.Winsip;
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
 * @author hugo
 */
public class WinsipJpaController implements Serializable {

    public WinsipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Winsip winsip) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(winsip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Winsip winsip) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            winsip = em.merge(winsip);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = winsip.getId();
                if (findWinsip(id) == null) {
                    throw new NonexistentEntityException("The winsip with id " + id + " no longer exists.");
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
            Winsip winsip;
            try {
                winsip = em.getReference(Winsip.class, id);
                winsip.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The winsip with id " + id + " no longer exists.", enfe);
            }
            em.remove(winsip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Winsip> findWinsipEntities() {
        return findWinsipEntities(true, -1, -1);
    }

    public List<Winsip> findWinsipEntities(int maxResults, int firstResult) {
        return findWinsipEntities(false, maxResults, firstResult);
    }

    private List<Winsip> findWinsipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Winsip.class));
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

    public Winsip findWinsip(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Winsip.class, id);
        } finally {
            em.close();
        }
    }

    public int getWinsipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Winsip> rt = cq.from(Winsip.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
