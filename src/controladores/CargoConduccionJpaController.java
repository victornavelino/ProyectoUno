/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.actividadConduccion.CargoConduccion;
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
public class CargoConduccionJpaController implements Serializable {

    public CargoConduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CargoConduccion cargoConduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cargoConduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CargoConduccion cargoConduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cargoConduccion = em.merge(cargoConduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cargoConduccion.getId();
                if (findCargoConduccion(id) == null) {
                    throw new NonexistentEntityException("The cargoConduccion with id " + id + " no longer exists.");
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
            CargoConduccion cargoConduccion;
            try {
                cargoConduccion = em.getReference(CargoConduccion.class, id);
                cargoConduccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargoConduccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(cargoConduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CargoConduccion> findCargoConduccionEntities() {
        return findCargoConduccionEntities(true, -1, -1);
    }

    public List<CargoConduccion> findCargoConduccionEntities(int maxResults, int firstResult) {
        return findCargoConduccionEntities(false, maxResults, firstResult);
    }

    private List<CargoConduccion> findCargoConduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CargoConduccion.class));
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

    public CargoConduccion findCargoConduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CargoConduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoConduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CargoConduccion> rt = cq.from(CargoConduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
