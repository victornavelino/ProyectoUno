/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.pfip.CronogramaDePago;
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
 * @author Panchi
 */
public class CronogramaDePagoJpaController implements Serializable {

    public CronogramaDePagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CronogramaDePago cronogramaDePago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cronogramaDePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CronogramaDePago cronogramaDePago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cronogramaDePago = em.merge(cronogramaDePago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cronogramaDePago.getId();
                if (findCronogramaDePago(id) == null) {
                    throw new NonexistentEntityException("The cronogramaDePago with id " + id + " no longer exists.");
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
            CronogramaDePago cronogramaDePago;
            try {
                cronogramaDePago = em.getReference(CronogramaDePago.class, id);
                cronogramaDePago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cronogramaDePago with id " + id + " no longer exists.", enfe);
            }
            em.remove(cronogramaDePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CronogramaDePago> findCronogramaDePagoEntities() {
        return findCronogramaDePagoEntities(true, -1, -1);
    }

    public List<CronogramaDePago> findCronogramaDePagoEntities(int maxResults, int firstResult) {
        return findCronogramaDePagoEntities(false, maxResults, firstResult);
    }

    private List<CronogramaDePago> findCronogramaDePagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CronogramaDePago.class));
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

    public CronogramaDePago findCronogramaDePago(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CronogramaDePago.class, id);
        } finally {
            em.close();
        }
    }

    public int getCronogramaDePagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CronogramaDePago> rt = cq.from(CronogramaDePago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
