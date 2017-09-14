/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.actividadConduccion.DedicacionConduccion;
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
public class DedicacionConduccionJpaController implements Serializable {

    public DedicacionConduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DedicacionConduccion dedicacionConduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dedicacionConduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DedicacionConduccion dedicacionConduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dedicacionConduccion = em.merge(dedicacionConduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dedicacionConduccion.getId();
                if (findDedicacionConduccion(id) == null) {
                    throw new NonexistentEntityException("The dedicacionConduccion with id " + id + " no longer exists.");
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
            DedicacionConduccion dedicacionConduccion;
            try {
                dedicacionConduccion = em.getReference(DedicacionConduccion.class, id);
                dedicacionConduccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dedicacionConduccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(dedicacionConduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DedicacionConduccion> findDedicacionConduccionEntities() {
        return findDedicacionConduccionEntities(true, -1, -1);
    }

    public List<DedicacionConduccion> findDedicacionConduccionEntities(int maxResults, int firstResult) {
        return findDedicacionConduccionEntities(false, maxResults, firstResult);
    }

    private List<DedicacionConduccion> findDedicacionConduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DedicacionConduccion.class));
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

    public DedicacionConduccion findDedicacionConduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DedicacionConduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDedicacionConduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DedicacionConduccion> rt = cq.from(DedicacionConduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
