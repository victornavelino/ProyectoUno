/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.actividadConduccion.ActividadConduccion;
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
public class ActividadConduccionJpaController implements Serializable {

    public ActividadConduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActividadConduccion actividadConduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(actividadConduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadConduccion actividadConduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            actividadConduccion = em.merge(actividadConduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = actividadConduccion.getId();
                if (findActividadConduccion(id) == null) {
                    throw new NonexistentEntityException("The actividadConduccion with id " + id + " no longer exists.");
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
            ActividadConduccion actividadConduccion;
            try {
                actividadConduccion = em.getReference(ActividadConduccion.class, id);
                actividadConduccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadConduccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(actividadConduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActividadConduccion> findActividadConduccionEntities() {
        return findActividadConduccionEntities(true, -1, -1);
    }

    public List<ActividadConduccion> findActividadConduccionEntities(int maxResults, int firstResult) {
        return findActividadConduccionEntities(false, maxResults, firstResult);
    }

    private List<ActividadConduccion> findActividadConduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActividadConduccion.class));
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

    public ActividadConduccion findActividadConduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadConduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadConduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActividadConduccion> rt = cq.from(ActividadConduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
