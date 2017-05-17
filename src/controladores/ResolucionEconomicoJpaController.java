/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionEconomico;
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
public class ResolucionEconomicoJpaController implements Serializable {

    public ResolucionEconomicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResolucionEconomico resolucionEconomico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resolucionEconomico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResolucionEconomico resolucionEconomico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resolucionEconomico = em.merge(resolucionEconomico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resolucionEconomico.getId();
                if (findResolucionEconomico(id) == null) {
                    throw new NonexistentEntityException("The resolucionEconomico with id " + id + " no longer exists.");
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
            ResolucionEconomico resolucionEconomico;
            try {
                resolucionEconomico = em.getReference(ResolucionEconomico.class, id);
                resolucionEconomico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucionEconomico with id " + id + " no longer exists.", enfe);
            }
            em.remove(resolucionEconomico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResolucionEconomico> findResolucionEconomicoEntities() {
        return findResolucionEconomicoEntities(true, -1, -1);
    }

    public List<ResolucionEconomico> findResolucionEconomicoEntities(int maxResults, int firstResult) {
        return findResolucionEconomicoEntities(false, maxResults, firstResult);
    }

    private List<ResolucionEconomico> findResolucionEconomicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResolucionEconomico.class));
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

    public ResolucionEconomico findResolucionEconomico(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResolucionEconomico.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionEconomicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResolucionEconomico> rt = cq.from(ResolucionEconomico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
