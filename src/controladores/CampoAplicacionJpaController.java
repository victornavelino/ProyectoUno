/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.CampoAplicacion;
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
public class CampoAplicacionJpaController implements Serializable {

    public CampoAplicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CampoAplicacion campoAplicacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(campoAplicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CampoAplicacion campoAplicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            campoAplicacion = em.merge(campoAplicacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = campoAplicacion.getId();
                if (findCampoAplicacion(id) == null) {
                    throw new NonexistentEntityException("The campoAplicacion with id " + id + " no longer exists.");
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
            CampoAplicacion campoAplicacion;
            try {
                campoAplicacion = em.getReference(CampoAplicacion.class, id);
                campoAplicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The campoAplicacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(campoAplicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CampoAplicacion> findCampoAplicacionEntities() {
        return findCampoAplicacionEntities(true, -1, -1);
    }

    public List<CampoAplicacion> findCampoAplicacionEntities(int maxResults, int firstResult) {
        return findCampoAplicacionEntities(false, maxResults, firstResult);
    }

    private List<CampoAplicacion> findCampoAplicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CampoAplicacion.class));
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

    public CampoAplicacion findCampoAplicacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CampoAplicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCampoAplicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CampoAplicacion> rt = cq.from(CampoAplicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
