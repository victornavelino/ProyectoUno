/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.Presupuesto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyecto.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class PresupuestoJpaController implements Serializable {

    public PresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Presupuesto presupuesto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyecto = presupuesto.getProyecto();
            if (proyecto != null) {
                proyecto = em.getReference(proyecto.getClass(), proyecto.getId());
                presupuesto.setProyecto(proyecto);
            }
            em.persist(presupuesto);
            if (proyecto != null) {
                Presupuesto oldPresupuestoOfProyecto = proyecto.getPresupuesto();
                if (oldPresupuestoOfProyecto != null) {
                    oldPresupuestoOfProyecto.setProyecto(null);
                    oldPresupuestoOfProyecto = em.merge(oldPresupuestoOfProyecto);
                }
                proyecto.setPresupuesto(presupuesto);
                proyecto = em.merge(proyecto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Presupuesto presupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Presupuesto persistentPresupuesto = em.find(Presupuesto.class, presupuesto.getId());
            Proyecto proyectoOld = persistentPresupuesto.getProyecto();
            Proyecto proyectoNew = presupuesto.getProyecto();
            if (proyectoNew != null) {
                proyectoNew = em.getReference(proyectoNew.getClass(), proyectoNew.getId());
                presupuesto.setProyecto(proyectoNew);
            }
            presupuesto = em.merge(presupuesto);
            if (proyectoOld != null && !proyectoOld.equals(proyectoNew)) {
                proyectoOld.setPresupuesto(null);
                proyectoOld = em.merge(proyectoOld);
            }
            if (proyectoNew != null && !proyectoNew.equals(proyectoOld)) {
                Presupuesto oldPresupuestoOfProyecto = proyectoNew.getPresupuesto();
                if (oldPresupuestoOfProyecto != null) {
                    oldPresupuestoOfProyecto.setProyecto(null);
                    oldPresupuestoOfProyecto = em.merge(oldPresupuestoOfProyecto);
                }
                proyectoNew.setPresupuesto(presupuesto);
                proyectoNew = em.merge(proyectoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuesto.getId();
                if (findPresupuesto(id) == null) {
                    throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.");
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
            Presupuesto presupuesto;
            try {
                presupuesto = em.getReference(Presupuesto.class, id);
                presupuesto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.", enfe);
            }
            Proyecto proyecto = presupuesto.getProyecto();
            if (proyecto != null) {
                proyecto.setPresupuesto(null);
                proyecto = em.merge(proyecto);
            }
            em.remove(presupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Presupuesto> findPresupuestoEntities() {
        return findPresupuestoEntities(true, -1, -1);
    }

    public List<Presupuesto> findPresupuestoEntities(int maxResults, int firstResult) {
        return findPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Presupuesto> findPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Presupuesto.class));
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

    public Presupuesto findPresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Presupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Presupuesto> rt = cq.from(Presupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
