/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.NivelFinanciacion;
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
public class NivelFinanciacionJpaController implements Serializable {

    public NivelFinanciacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NivelFinanciacion nivelFinanciacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nivelFinanciacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NivelFinanciacion nivelFinanciacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nivelFinanciacion = em.merge(nivelFinanciacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = nivelFinanciacion.getId();
                if (findNivelFinanciacion(id) == null) {
                    throw new NonexistentEntityException("The nivelFinanciacion with id " + id + " no longer exists.");
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
            NivelFinanciacion nivelFinanciacion;
            try {
                nivelFinanciacion = em.getReference(NivelFinanciacion.class, id);
                nivelFinanciacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelFinanciacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(nivelFinanciacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NivelFinanciacion> findNivelFinanciacionEntities() {
        return findNivelFinanciacionEntities(true, -1, -1);
    }

    public List<NivelFinanciacion> findNivelFinanciacionEntities(int maxResults, int firstResult) {
        return findNivelFinanciacionEntities(false, maxResults, firstResult);
    }

    private List<NivelFinanciacion> findNivelFinanciacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NivelFinanciacion.class));
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

    public NivelFinanciacion findNivelFinanciacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NivelFinanciacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelFinanciacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NivelFinanciacion> rt = cq.from(NivelFinanciacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
