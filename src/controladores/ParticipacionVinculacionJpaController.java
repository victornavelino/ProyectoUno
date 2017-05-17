/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.ParticipacionVinculacion;
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
public class ParticipacionVinculacionJpaController implements Serializable {

    public ParticipacionVinculacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ParticipacionVinculacion participacionVinculacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(participacionVinculacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParticipacionVinculacion participacionVinculacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            participacionVinculacion = em.merge(participacionVinculacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = participacionVinculacion.getId();
                if (findParticipacionVinculacion(id) == null) {
                    throw new NonexistentEntityException("The participacionVinculacion with id " + id + " no longer exists.");
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
            ParticipacionVinculacion participacionVinculacion;
            try {
                participacionVinculacion = em.getReference(ParticipacionVinculacion.class, id);
                participacionVinculacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participacionVinculacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(participacionVinculacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParticipacionVinculacion> findParticipacionVinculacionEntities() {
        return findParticipacionVinculacionEntities(true, -1, -1);
    }

    public List<ParticipacionVinculacion> findParticipacionVinculacionEntities(int maxResults, int firstResult) {
        return findParticipacionVinculacionEntities(false, maxResults, firstResult);
    }

    private List<ParticipacionVinculacion> findParticipacionVinculacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParticipacionVinculacion.class));
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

    public ParticipacionVinculacion findParticipacionVinculacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParticipacionVinculacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipacionVinculacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParticipacionVinculacion> rt = cq.from(ParticipacionVinculacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
