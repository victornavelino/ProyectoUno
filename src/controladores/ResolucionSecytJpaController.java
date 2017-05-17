/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionSecyt;
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
public class ResolucionSecytJpaController implements Serializable {

    public ResolucionSecytJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResolucionSecyt resolucionSecyt) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resolucionSecyt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResolucionSecyt resolucionSecyt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resolucionSecyt = em.merge(resolucionSecyt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resolucionSecyt.getId();
                if (findResolucionSecyt(id) == null) {
                    throw new NonexistentEntityException("The resolucionSecyt with id " + id + " no longer exists.");
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
            ResolucionSecyt resolucionSecyt;
            try {
                resolucionSecyt = em.getReference(ResolucionSecyt.class, id);
                resolucionSecyt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucionSecyt with id " + id + " no longer exists.", enfe);
            }
            em.remove(resolucionSecyt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResolucionSecyt> findResolucionSecytEntities() {
        return findResolucionSecytEntities(true, -1, -1);
    }

    public List<ResolucionSecyt> findResolucionSecytEntities(int maxResults, int firstResult) {
        return findResolucionSecytEntities(false, maxResults, firstResult);
    }

    private List<ResolucionSecyt> findResolucionSecytEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResolucionSecyt.class));
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

    public ResolucionSecyt findResolucionSecyt(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResolucionSecyt.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionSecytCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResolucionSecyt> rt = cq.from(ResolucionSecyt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
