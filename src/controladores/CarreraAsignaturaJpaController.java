/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.CarreraAsignatura;
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
public class CarreraAsignaturaJpaController implements Serializable {

    public CarreraAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarreraAsignatura carreraAsignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(carreraAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CarreraAsignatura carreraAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            carreraAsignatura = em.merge(carreraAsignatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = carreraAsignatura.getId();
                if (findCarreraAsignatura(id) == null) {
                    throw new NonexistentEntityException("The carreraAsignatura with id " + id + " no longer exists.");
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
            CarreraAsignatura carreraAsignatura;
            try {
                carreraAsignatura = em.getReference(CarreraAsignatura.class, id);
                carreraAsignatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carreraAsignatura with id " + id + " no longer exists.", enfe);
            }
            em.remove(carreraAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarreraAsignatura> findCarreraAsignaturaEntities() {
        return findCarreraAsignaturaEntities(true, -1, -1);
    }

    public List<CarreraAsignatura> findCarreraAsignaturaEntities(int maxResults, int firstResult) {
        return findCarreraAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<CarreraAsignatura> findCarreraAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarreraAsignatura.class));
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

    public CarreraAsignatura findCarreraAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarreraAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarreraAsignatura> rt = cq.from(CarreraAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
