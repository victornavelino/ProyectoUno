/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.ExpedienteEditorial;
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
 * @author francisco
 */
public class ExpedienteEditorialJpaController implements Serializable {

    public ExpedienteEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExpedienteEditorial expedienteEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(expedienteEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExpedienteEditorial expedienteEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            expedienteEditorial = em.merge(expedienteEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = expedienteEditorial.getId();
                if (findExpedienteEditorial(id) == null) {
                    throw new NonexistentEntityException("The expedienteEditorial with id " + id + " no longer exists.");
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
            ExpedienteEditorial expedienteEditorial;
            try {
                expedienteEditorial = em.getReference(ExpedienteEditorial.class, id);
                expedienteEditorial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The expedienteEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(expedienteEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExpedienteEditorial> findExpedienteEditorialEntities() {
        return findExpedienteEditorialEntities(true, -1, -1);
    }

    public List<ExpedienteEditorial> findExpedienteEditorialEntities(int maxResults, int firstResult) {
        return findExpedienteEditorialEntities(false, maxResults, firstResult);
    }

    private List<ExpedienteEditorial> findExpedienteEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExpedienteEditorial.class));
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

    public ExpedienteEditorial findExpedienteEditorial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExpedienteEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpedienteEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExpedienteEditorial> rt = cq.from(ExpedienteEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
