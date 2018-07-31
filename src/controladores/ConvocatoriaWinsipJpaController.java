/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.convocatoriawinsip.ConvocatoriaWinsip;
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
public class ConvocatoriaWinsipJpaController implements Serializable {

    public ConvocatoriaWinsipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConvocatoriaWinsip convocatoriaWinsip) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(convocatoriaWinsip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConvocatoriaWinsip convocatoriaWinsip) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            convocatoriaWinsip = em.merge(convocatoriaWinsip);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = convocatoriaWinsip.getId();
                if (findConvocatoriaWinsip(id) == null) {
                    throw new NonexistentEntityException("The convocatoriaWinsip with id " + id + " no longer exists.");
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
            ConvocatoriaWinsip convocatoriaWinsip;
            try {
                convocatoriaWinsip = em.getReference(ConvocatoriaWinsip.class, id);
                convocatoriaWinsip.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convocatoriaWinsip with id " + id + " no longer exists.", enfe);
            }
            em.remove(convocatoriaWinsip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConvocatoriaWinsip> findConvocatoriaWinsipEntities() {
        return findConvocatoriaWinsipEntities(true, -1, -1);
    }

    public List<ConvocatoriaWinsip> findConvocatoriaWinsipEntities(int maxResults, int firstResult) {
        return findConvocatoriaWinsipEntities(false, maxResults, firstResult);
    }

    private List<ConvocatoriaWinsip> findConvocatoriaWinsipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConvocatoriaWinsip.class));
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

    public ConvocatoriaWinsip findConvocatoriaWinsip(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConvocatoriaWinsip.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvocatoriaWinsipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConvocatoriaWinsip> rt = cq.from(ConvocatoriaWinsip.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
