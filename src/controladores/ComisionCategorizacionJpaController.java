/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.ComisionCategorizacion;
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
 * @author Administrador
 */
public class ComisionCategorizacionJpaController implements Serializable {

    public ComisionCategorizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComisionCategorizacion comisionCategorizacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comisionCategorizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComisionCategorizacion comisionCategorizacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comisionCategorizacion = em.merge(comisionCategorizacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comisionCategorizacion.getId();
                if (findComisionCategorizacion(id) == null) {
                    throw new NonexistentEntityException("The comisionCategorizacion with id " + id + " no longer exists.");
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
            ComisionCategorizacion comisionCategorizacion;
            try {
                comisionCategorizacion = em.getReference(ComisionCategorizacion.class, id);
                comisionCategorizacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comisionCategorizacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(comisionCategorizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComisionCategorizacion> findComisionCategorizacionEntities() {
        return findComisionCategorizacionEntities(true, -1, -1);
    }

    public List<ComisionCategorizacion> findComisionCategorizacionEntities(int maxResults, int firstResult) {
        return findComisionCategorizacionEntities(false, maxResults, firstResult);
    }

    private List<ComisionCategorizacion> findComisionCategorizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComisionCategorizacion.class));
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

    public ComisionCategorizacion findComisionCategorizacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComisionCategorizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getComisionCategorizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComisionCategorizacion> rt = cq.from(ComisionCategorizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
