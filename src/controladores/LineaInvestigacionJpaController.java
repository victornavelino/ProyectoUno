/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.LineaInvestigacion;
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
public class LineaInvestigacionJpaController implements Serializable {

    public LineaInvestigacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LineaInvestigacion lineaInvestigacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lineaInvestigacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LineaInvestigacion lineaInvestigacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lineaInvestigacion = em.merge(lineaInvestigacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = lineaInvestigacion.getId();
                if (findLineaInvestigacion(id) == null) {
                    throw new NonexistentEntityException("The lineaInvestigacion with id " + id + " no longer exists.");
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
            LineaInvestigacion lineaInvestigacion;
            try {
                lineaInvestigacion = em.getReference(LineaInvestigacion.class, id);
                lineaInvestigacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineaInvestigacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(lineaInvestigacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LineaInvestigacion> findLineaInvestigacionEntities() {
        return findLineaInvestigacionEntities(true, -1, -1);
    }

    public List<LineaInvestigacion> findLineaInvestigacionEntities(int maxResults, int firstResult) {
        return findLineaInvestigacionEntities(false, maxResults, firstResult);
    }

    private List<LineaInvestigacion> findLineaInvestigacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LineaInvestigacion.class));
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

    public LineaInvestigacion findLineaInvestigacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LineaInvestigacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineaInvestigacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LineaInvestigacion> rt = cq.from(LineaInvestigacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
