/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.OtrasAgencias;
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
public class OtrasAgenciasJpaController implements Serializable {

    public OtrasAgenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OtrasAgencias otrasAgencias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(otrasAgencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OtrasAgencias otrasAgencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            otrasAgencias = em.merge(otrasAgencias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = otrasAgencias.getId();
                if (findOtrasAgencias(id) == null) {
                    throw new NonexistentEntityException("The otrasAgencias with id " + id + " no longer exists.");
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
            OtrasAgencias otrasAgencias;
            try {
                otrasAgencias = em.getReference(OtrasAgencias.class, id);
                otrasAgencias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The otrasAgencias with id " + id + " no longer exists.", enfe);
            }
            em.remove(otrasAgencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OtrasAgencias> findOtrasAgenciasEntities() {
        return findOtrasAgenciasEntities(true, -1, -1);
    }

    public List<OtrasAgencias> findOtrasAgenciasEntities(int maxResults, int firstResult) {
        return findOtrasAgenciasEntities(false, maxResults, firstResult);
    }

    private List<OtrasAgencias> findOtrasAgenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OtrasAgencias.class));
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

    public OtrasAgencias findOtrasAgencias(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OtrasAgencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getOtrasAgenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OtrasAgencias> rt = cq.from(OtrasAgencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
