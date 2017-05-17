/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.UnidadInvestigacion;
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
public class UnidadInvestigacionJpaController implements Serializable {

    public UnidadInvestigacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadInvestigacion unidadInvestigacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(unidadInvestigacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadInvestigacion unidadInvestigacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            unidadInvestigacion = em.merge(unidadInvestigacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = unidadInvestigacion.getId();
                if (findUnidadInvestigacion(id) == null) {
                    throw new NonexistentEntityException("The unidadInvestigacion with id " + id + " no longer exists.");
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
            UnidadInvestigacion unidadInvestigacion;
            try {
                unidadInvestigacion = em.getReference(UnidadInvestigacion.class, id);
                unidadInvestigacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadInvestigacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(unidadInvestigacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadInvestigacion> findUnidadInvestigacionEntities() {
        return findUnidadInvestigacionEntities(true, -1, -1);
    }

    public List<UnidadInvestigacion> findUnidadInvestigacionEntities(int maxResults, int firstResult) {
        return findUnidadInvestigacionEntities(false, maxResults, firstResult);
    }

    private List<UnidadInvestigacion> findUnidadInvestigacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadInvestigacion.class));
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

    public UnidadInvestigacion findUnidadInvestigacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadInvestigacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadInvestigacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadInvestigacion> rt = cq.from(UnidadInvestigacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
