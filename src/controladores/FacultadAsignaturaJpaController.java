/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.FacultadAsignatura;
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
public class FacultadAsignaturaJpaController implements Serializable {

    public FacultadAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacultadAsignatura facultadAsignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(facultadAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacultadAsignatura facultadAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            facultadAsignatura = em.merge(facultadAsignatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = facultadAsignatura.getId();
                if (findFacultadAsignatura(id) == null) {
                    throw new NonexistentEntityException("The facultadAsignatura with id " + id + " no longer exists.");
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
            FacultadAsignatura facultadAsignatura;
            try {
                facultadAsignatura = em.getReference(FacultadAsignatura.class, id);
                facultadAsignatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultadAsignatura with id " + id + " no longer exists.", enfe);
            }
            em.remove(facultadAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacultadAsignatura> findFacultadAsignaturaEntities() {
        return findFacultadAsignaturaEntities(true, -1, -1);
    }

    public List<FacultadAsignatura> findFacultadAsignaturaEntities(int maxResults, int firstResult) {
        return findFacultadAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<FacultadAsignatura> findFacultadAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacultadAsignatura.class));
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

    public FacultadAsignatura findFacultadAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacultadAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacultadAsignatura> rt = cq.from(FacultadAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
