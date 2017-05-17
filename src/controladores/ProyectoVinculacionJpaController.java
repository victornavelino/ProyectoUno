/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
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
public class ProyectoVinculacionJpaController implements Serializable {

    public ProyectoVinculacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProyectoVinculacion proyectoVinculacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proyectoVinculacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProyectoVinculacion proyectoVinculacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proyectoVinculacion = em.merge(proyectoVinculacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proyectoVinculacion.getId();
                if (findProyectoVinculacion(id) == null) {
                    throw new NonexistentEntityException("The proyectoVinculacion with id " + id + " no longer exists.");
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
            ProyectoVinculacion proyectoVinculacion;
            try {
                proyectoVinculacion = em.getReference(ProyectoVinculacion.class, id);
                proyectoVinculacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyectoVinculacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(proyectoVinculacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProyectoVinculacion> findProyectoVinculacionEntities() {
        return findProyectoVinculacionEntities(true, -1, -1);
    }

    public List<ProyectoVinculacion> findProyectoVinculacionEntities(int maxResults, int firstResult) {
        return findProyectoVinculacionEntities(false, maxResults, firstResult);
    }

    private List<ProyectoVinculacion> findProyectoVinculacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProyectoVinculacion.class));
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

    public ProyectoVinculacion findProyectoVinculacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProyectoVinculacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoVinculacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProyectoVinculacion> rt = cq.from(ProyectoVinculacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
