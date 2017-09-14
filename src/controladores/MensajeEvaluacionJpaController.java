/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.evaluacion.MensajeEvaluacion;
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
public class MensajeEvaluacionJpaController implements Serializable {

    public MensajeEvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MensajeEvaluacion mensajeEvaluacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mensajeEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MensajeEvaluacion mensajeEvaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mensajeEvaluacion = em.merge(mensajeEvaluacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mensajeEvaluacion.getId();
                if (findMensajeEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The mensajeEvaluacion with id " + id + " no longer exists.");
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
            MensajeEvaluacion mensajeEvaluacion;
            try {
                mensajeEvaluacion = em.getReference(MensajeEvaluacion.class, id);
                mensajeEvaluacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensajeEvaluacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(mensajeEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MensajeEvaluacion> findMensajeEvaluacionEntities() {
        return findMensajeEvaluacionEntities(true, -1, -1);
    }

    public List<MensajeEvaluacion> findMensajeEvaluacionEntities(int maxResults, int firstResult) {
        return findMensajeEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<MensajeEvaluacion> findMensajeEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MensajeEvaluacion.class));
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

    public MensajeEvaluacion findMensajeEvaluacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MensajeEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensajeEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MensajeEvaluacion> rt = cq.from(MensajeEvaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
