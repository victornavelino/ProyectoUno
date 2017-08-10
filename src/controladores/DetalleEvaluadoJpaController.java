/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.evaluacion.DetalleEvaluado;
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
public class DetalleEvaluadoJpaController implements Serializable {

    public DetalleEvaluadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleEvaluado detalleEvaluado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detalleEvaluado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleEvaluado detalleEvaluado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalleEvaluado = em.merge(detalleEvaluado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleEvaluado.getId();
                if (findDetalleEvaluado(id) == null) {
                    throw new NonexistentEntityException("The detalleEvaluado with id " + id + " no longer exists.");
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
            DetalleEvaluado detalleEvaluado;
            try {
                detalleEvaluado = em.getReference(DetalleEvaluado.class, id);
                detalleEvaluado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleEvaluado with id " + id + " no longer exists.", enfe);
            }
            em.remove(detalleEvaluado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleEvaluado> findDetalleEvaluadoEntities() {
        return findDetalleEvaluadoEntities(true, -1, -1);
    }

    public List<DetalleEvaluado> findDetalleEvaluadoEntities(int maxResults, int firstResult) {
        return findDetalleEvaluadoEntities(false, maxResults, firstResult);
    }

    private List<DetalleEvaluado> findDetalleEvaluadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleEvaluado.class));
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

    public DetalleEvaluado findDetalleEvaluado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleEvaluado.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleEvaluadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleEvaluado> rt = cq.from(DetalleEvaluado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
