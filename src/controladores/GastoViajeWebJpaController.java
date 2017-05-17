/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.GastoViajeWeb;
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
public class GastoViajeWebJpaController implements Serializable {

    public GastoViajeWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GastoViajeWeb gastoViajeWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gastoViajeWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GastoViajeWeb gastoViajeWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gastoViajeWeb = em.merge(gastoViajeWeb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = gastoViajeWeb.getId();
                if (findGastoViajeWeb(id) == null) {
                    throw new NonexistentEntityException("The gastoViajeWeb with id " + id + " no longer exists.");
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
            GastoViajeWeb gastoViajeWeb;
            try {
                gastoViajeWeb = em.getReference(GastoViajeWeb.class, id);
                gastoViajeWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gastoViajeWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(gastoViajeWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GastoViajeWeb> findGastoViajeWebEntities() {
        return findGastoViajeWebEntities(true, -1, -1);
    }

    public List<GastoViajeWeb> findGastoViajeWebEntities(int maxResults, int firstResult) {
        return findGastoViajeWebEntities(false, maxResults, firstResult);
    }

    private List<GastoViajeWeb> findGastoViajeWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GastoViajeWeb.class));
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

    public GastoViajeWeb findGastoViajeWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GastoViajeWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastoViajeWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GastoViajeWeb> rt = cq.from(GastoViajeWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
