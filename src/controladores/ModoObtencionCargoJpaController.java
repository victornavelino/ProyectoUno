/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.ModoObtencionCargo;
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
public class ModoObtencionCargoJpaController implements Serializable {

    public ModoObtencionCargoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ModoObtencionCargo modoObtencionCargo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(modoObtencionCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ModoObtencionCargo modoObtencionCargo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            modoObtencionCargo = em.merge(modoObtencionCargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = modoObtencionCargo.getId();
                if (findModoObtencionCargo(id) == null) {
                    throw new NonexistentEntityException("The modoObtencionCargo with id " + id + " no longer exists.");
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
            ModoObtencionCargo modoObtencionCargo;
            try {
                modoObtencionCargo = em.getReference(ModoObtencionCargo.class, id);
                modoObtencionCargo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modoObtencionCargo with id " + id + " no longer exists.", enfe);
            }
            em.remove(modoObtencionCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ModoObtencionCargo> findModoObtencionCargoEntities() {
        return findModoObtencionCargoEntities(true, -1, -1);
    }

    public List<ModoObtencionCargo> findModoObtencionCargoEntities(int maxResults, int firstResult) {
        return findModoObtencionCargoEntities(false, maxResults, firstResult);
    }

    private List<ModoObtencionCargo> findModoObtencionCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ModoObtencionCargo.class));
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

    public ModoObtencionCargo findModoObtencionCargo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ModoObtencionCargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getModoObtencionCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ModoObtencionCargo> rt = cq.from(ModoObtencionCargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
