/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.DedicacionDocente;
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
public class DedicacionDocenteJpaController implements Serializable {

    public DedicacionDocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DedicacionDocente dedicacionDocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dedicacionDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DedicacionDocente dedicacionDocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dedicacionDocente = em.merge(dedicacionDocente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dedicacionDocente.getId();
                if (findDedicacionDocente(id) == null) {
                    throw new NonexistentEntityException("The dedicacionDocente with id " + id + " no longer exists.");
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
            DedicacionDocente dedicacionDocente;
            try {
                dedicacionDocente = em.getReference(DedicacionDocente.class, id);
                dedicacionDocente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dedicacionDocente with id " + id + " no longer exists.", enfe);
            }
            em.remove(dedicacionDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DedicacionDocente> findDedicacionDocenteEntities() {
        return findDedicacionDocenteEntities(true, -1, -1);
    }

    public List<DedicacionDocente> findDedicacionDocenteEntities(int maxResults, int firstResult) {
        return findDedicacionDocenteEntities(false, maxResults, firstResult);
    }

    private List<DedicacionDocente> findDedicacionDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DedicacionDocente.class));
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

    public DedicacionDocente findDedicacionDocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DedicacionDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDedicacionDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DedicacionDocente> rt = cq.from(DedicacionDocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
