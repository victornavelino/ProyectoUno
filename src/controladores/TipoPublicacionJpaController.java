/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.TipoPublicacion;
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
public class TipoPublicacionJpaController implements Serializable {

    public TipoPublicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPublicacion tipoPublicacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoPublicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPublicacion tipoPublicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoPublicacion = em.merge(tipoPublicacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoPublicacion.getId();
                if (findTipoPublicacion(id) == null) {
                    throw new NonexistentEntityException("The tipoPublicacion with id " + id + " no longer exists.");
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
            TipoPublicacion tipoPublicacion;
            try {
                tipoPublicacion = em.getReference(TipoPublicacion.class, id);
                tipoPublicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPublicacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoPublicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPublicacion> findTipoPublicacionEntities() {
        return findTipoPublicacionEntities(true, -1, -1);
    }

    public List<TipoPublicacion> findTipoPublicacionEntities(int maxResults, int firstResult) {
        return findTipoPublicacionEntities(false, maxResults, firstResult);
    }

    private List<TipoPublicacion> findTipoPublicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPublicacion.class));
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

    public TipoPublicacion findTipoPublicacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPublicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPublicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPublicacion> rt = cq.from(TipoPublicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
