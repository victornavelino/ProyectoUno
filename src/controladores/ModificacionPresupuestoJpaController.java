/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ModificacionPresupuesto;
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
 * @author Walter
 */
public class ModificacionPresupuestoJpaController implements Serializable {

    public ModificacionPresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ModificacionPresupuesto modificacionPresupuesto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(modificacionPresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ModificacionPresupuesto modificacionPresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            modificacionPresupuesto = em.merge(modificacionPresupuesto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = modificacionPresupuesto.getId();
                if (findModificacionPresupuesto(id) == null) {
                    throw new NonexistentEntityException("The modificacionPresupuesto with id " + id + " no longer exists.");
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
            ModificacionPresupuesto modificacionPresupuesto;
            try {
                modificacionPresupuesto = em.getReference(ModificacionPresupuesto.class, id);
                modificacionPresupuesto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modificacionPresupuesto with id " + id + " no longer exists.", enfe);
            }
            em.remove(modificacionPresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ModificacionPresupuesto> findModificacionPresupuestoEntities() {
        return findModificacionPresupuestoEntities(true, -1, -1);
    }

    public List<ModificacionPresupuesto> findModificacionPresupuestoEntities(int maxResults, int firstResult) {
        return findModificacionPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<ModificacionPresupuesto> findModificacionPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ModificacionPresupuesto.class));
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

    public ModificacionPresupuesto findModificacionPresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ModificacionPresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getModificacionPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ModificacionPresupuesto> rt = cq.from(ModificacionPresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
