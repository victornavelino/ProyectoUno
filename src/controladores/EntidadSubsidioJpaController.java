/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.EntidadSubsidio;
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
public class EntidadSubsidioJpaController implements Serializable {

    public EntidadSubsidioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadSubsidio entidadSubsidio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidadSubsidio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadSubsidio entidadSubsidio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entidadSubsidio = em.merge(entidadSubsidio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidadSubsidio.getId();
                if (findEntidadSubsidio(id) == null) {
                    throw new NonexistentEntityException("The entidadSubsidio with id " + id + " no longer exists.");
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
            EntidadSubsidio entidadSubsidio;
            try {
                entidadSubsidio = em.getReference(EntidadSubsidio.class, id);
                entidadSubsidio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadSubsidio with id " + id + " no longer exists.", enfe);
            }
            em.remove(entidadSubsidio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadSubsidio> findEntidadSubsidioEntities() {
        return findEntidadSubsidioEntities(true, -1, -1);
    }

    public List<EntidadSubsidio> findEntidadSubsidioEntities(int maxResults, int firstResult) {
        return findEntidadSubsidioEntities(false, maxResults, firstResult);
    }

    private List<EntidadSubsidio> findEntidadSubsidioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntidadSubsidio.class));
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

    public EntidadSubsidio findEntidadSubsidio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadSubsidio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadSubsidioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntidadSubsidio> rt = cq.from(EntidadSubsidio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
