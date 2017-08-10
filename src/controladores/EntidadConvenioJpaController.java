/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.EntidadConvenio;
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
public class EntidadConvenioJpaController implements Serializable {

    public EntidadConvenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadConvenio entidadConvenio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidadConvenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadConvenio entidadConvenio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entidadConvenio = em.merge(entidadConvenio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidadConvenio.getId();
                if (findEntidadConvenio(id) == null) {
                    throw new NonexistentEntityException("The entidadConvenio with id " + id + " no longer exists.");
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
            EntidadConvenio entidadConvenio;
            try {
                entidadConvenio = em.getReference(EntidadConvenio.class, id);
                entidadConvenio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadConvenio with id " + id + " no longer exists.", enfe);
            }
            em.remove(entidadConvenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadConvenio> findEntidadConvenioEntities() {
        return findEntidadConvenioEntities(true, -1, -1);
    }

    public List<EntidadConvenio> findEntidadConvenioEntities(int maxResults, int firstResult) {
        return findEntidadConvenioEntities(false, maxResults, firstResult);
    }

    private List<EntidadConvenio> findEntidadConvenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntidadConvenio.class));
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

    public EntidadConvenio findEntidadConvenio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadConvenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadConvenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntidadConvenio> rt = cq.from(EntidadConvenio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
