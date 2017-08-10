/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.PlantillaMensaje;
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
public class PlantillaMensajeJpaController implements Serializable {

    public PlantillaMensajeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlantillaMensaje plantillaMensaje) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(plantillaMensaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlantillaMensaje plantillaMensaje) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            plantillaMensaje = em.merge(plantillaMensaje);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = plantillaMensaje.getId();
                if (findPlantillaMensaje(id) == null) {
                    throw new NonexistentEntityException("The plantillaMensaje with id " + id + " no longer exists.");
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
            PlantillaMensaje plantillaMensaje;
            try {
                plantillaMensaje = em.getReference(PlantillaMensaje.class, id);
                plantillaMensaje.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plantillaMensaje with id " + id + " no longer exists.", enfe);
            }
            em.remove(plantillaMensaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlantillaMensaje> findPlantillaMensajeEntities() {
        return findPlantillaMensajeEntities(true, -1, -1);
    }

    public List<PlantillaMensaje> findPlantillaMensajeEntities(int maxResults, int firstResult) {
        return findPlantillaMensajeEntities(false, maxResults, firstResult);
    }

    private List<PlantillaMensaje> findPlantillaMensajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlantillaMensaje.class));
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

    public PlantillaMensaje findPlantillaMensaje(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlantillaMensaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlantillaMensajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlantillaMensaje> rt = cq.from(PlantillaMensaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
