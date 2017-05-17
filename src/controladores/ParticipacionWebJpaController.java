/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.ParticipacionWeb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyectoWeb.ProyectoWeb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class ParticipacionWebJpaController implements Serializable {

    public ParticipacionWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ParticipacionWeb participacionWeb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb proyectoWeb = participacionWeb.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb = em.getReference(proyectoWeb.getClass(), proyectoWeb.getId());
                participacionWeb.setProyectoWeb(proyectoWeb);
            }
            em.persist(participacionWeb);
            if (proyectoWeb != null) {
                proyectoWeb.getParticipacionesWeb().add(participacionWeb);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParticipacionWeb participacionWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParticipacionWeb persistentParticipacionWeb = em.find(ParticipacionWeb.class, participacionWeb.getId());
            ProyectoWeb proyectoWebOld = persistentParticipacionWeb.getProyectoWeb();
            ProyectoWeb proyectoWebNew = participacionWeb.getProyectoWeb();
            if (proyectoWebNew != null) {
                proyectoWebNew = em.getReference(proyectoWebNew.getClass(), proyectoWebNew.getId());
                participacionWeb.setProyectoWeb(proyectoWebNew);
            }
            participacionWeb = em.merge(participacionWeb);
            if (proyectoWebOld != null && !proyectoWebOld.equals(proyectoWebNew)) {
                proyectoWebOld.getParticipacionesWeb().remove(participacionWeb);
                proyectoWebOld = em.merge(proyectoWebOld);
            }
            if (proyectoWebNew != null && !proyectoWebNew.equals(proyectoWebOld)) {
                proyectoWebNew.getParticipacionesWeb().add(participacionWeb);
                proyectoWebNew = em.merge(proyectoWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = participacionWeb.getId();
                if (findParticipacionWeb(id) == null) {
                    throw new NonexistentEntityException("The participacionWeb with id " + id + " no longer exists.");
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
            ParticipacionWeb participacionWeb;
            try {
                participacionWeb = em.getReference(ParticipacionWeb.class, id);
                participacionWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participacionWeb with id " + id + " no longer exists.", enfe);
            }
            ProyectoWeb proyectoWeb = participacionWeb.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb.getParticipacionesWeb().remove(participacionWeb);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.remove(participacionWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParticipacionWeb> findParticipacionWebEntities() {
        return findParticipacionWebEntities(true, -1, -1);
    }

    public List<ParticipacionWeb> findParticipacionWebEntities(int maxResults, int firstResult) {
        return findParticipacionWebEntities(false, maxResults, firstResult);
    }

    private List<ParticipacionWeb> findParticipacionWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParticipacionWeb.class));
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

    public ParticipacionWeb findParticipacionWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParticipacionWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipacionWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParticipacionWeb> rt = cq.from(ParticipacionWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
