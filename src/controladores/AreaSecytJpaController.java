/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.AreaSecyt;
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
public class AreaSecytJpaController implements Serializable {

    public AreaSecytJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaSecyt areaSecyt) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(areaSecyt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaSecyt areaSecyt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            areaSecyt = em.merge(areaSecyt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = areaSecyt.getId();
                if (findAreaSecyt(id) == null) {
                    throw new NonexistentEntityException("The areaSecyt with id " + id + " no longer exists.");
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
            AreaSecyt areaSecyt;
            try {
                areaSecyt = em.getReference(AreaSecyt.class, id);
                areaSecyt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaSecyt with id " + id + " no longer exists.", enfe);
            }
            em.remove(areaSecyt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaSecyt> findAreaSecytEntities() {
        return findAreaSecytEntities(true, -1, -1);
    }

    public List<AreaSecyt> findAreaSecytEntities(int maxResults, int firstResult) {
        return findAreaSecytEntities(false, maxResults, firstResult);
    }

    private List<AreaSecyt> findAreaSecytEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaSecyt.class));
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

    public AreaSecyt findAreaSecyt(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaSecyt.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaSecytCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaSecyt> rt = cq.from(AreaSecyt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
