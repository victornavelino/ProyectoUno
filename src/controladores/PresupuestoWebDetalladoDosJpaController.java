/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.PresupuestoWebDetalladoDos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyectoWeb.ProyectoWeb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class PresupuestoWebDetalladoDosJpaController implements Serializable {

    public PresupuestoWebDetalladoDosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PresupuestoWebDetalladoDos presupuestoWebDetalladoDos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb proyectoWeb = presupuestoWebDetalladoDos.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb = em.getReference(proyectoWeb.getClass(), proyectoWeb.getId());
                presupuestoWebDetalladoDos.setProyectoWeb(proyectoWeb);
            }
            em.persist(presupuestoWebDetalladoDos);
            if (proyectoWeb != null) {
                PresupuestoWebDetalladoDos oldPresupuestoWebAnioDosOfProyectoWeb = proyectoWeb.getPresupuestoWebAnioDos();
                if (oldPresupuestoWebAnioDosOfProyectoWeb != null) {
                    oldPresupuestoWebAnioDosOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioDosOfProyectoWeb = em.merge(oldPresupuestoWebAnioDosOfProyectoWeb);
                }
                proyectoWeb.setPresupuestoWebAnioDos(presupuestoWebDetalladoDos);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PresupuestoWebDetalladoDos presupuestoWebDetalladoDos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWebDetalladoDos persistentPresupuestoWebDetalladoDos = em.find(PresupuestoWebDetalladoDos.class, presupuestoWebDetalladoDos.getId());
            ProyectoWeb proyectoWebOld = persistentPresupuestoWebDetalladoDos.getProyectoWeb();
            ProyectoWeb proyectoWebNew = presupuestoWebDetalladoDos.getProyectoWeb();
            if (proyectoWebNew != null) {
                proyectoWebNew = em.getReference(proyectoWebNew.getClass(), proyectoWebNew.getId());
                presupuestoWebDetalladoDos.setProyectoWeb(proyectoWebNew);
            }
            presupuestoWebDetalladoDos = em.merge(presupuestoWebDetalladoDos);
            if (proyectoWebOld != null && !proyectoWebOld.equals(proyectoWebNew)) {
                proyectoWebOld.setPresupuestoWebAnioDos(null);
                proyectoWebOld = em.merge(proyectoWebOld);
            }
            if (proyectoWebNew != null && !proyectoWebNew.equals(proyectoWebOld)) {
                PresupuestoWebDetalladoDos oldPresupuestoWebAnioDosOfProyectoWeb = proyectoWebNew.getPresupuestoWebAnioDos();
                if (oldPresupuestoWebAnioDosOfProyectoWeb != null) {
                    oldPresupuestoWebAnioDosOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioDosOfProyectoWeb = em.merge(oldPresupuestoWebAnioDosOfProyectoWeb);
                }
                proyectoWebNew.setPresupuestoWebAnioDos(presupuestoWebDetalladoDos);
                proyectoWebNew = em.merge(proyectoWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuestoWebDetalladoDos.getId();
                if (findPresupuestoWebDetalladoDos(id) == null) {
                    throw new NonexistentEntityException("The presupuestoWebDetalladoDos with id " + id + " no longer exists.");
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
            PresupuestoWebDetalladoDos presupuestoWebDetalladoDos;
            try {
                presupuestoWebDetalladoDos = em.getReference(PresupuestoWebDetalladoDos.class, id);
                presupuestoWebDetalladoDos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuestoWebDetalladoDos with id " + id + " no longer exists.", enfe);
            }
            ProyectoWeb proyectoWeb = presupuestoWebDetalladoDos.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb.setPresupuestoWebAnioDos(null);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.remove(presupuestoWebDetalladoDos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PresupuestoWebDetalladoDos> findPresupuestoWebDetalladoDosEntities() {
        return findPresupuestoWebDetalladoDosEntities(true, -1, -1);
    }

    public List<PresupuestoWebDetalladoDos> findPresupuestoWebDetalladoDosEntities(int maxResults, int firstResult) {
        return findPresupuestoWebDetalladoDosEntities(false, maxResults, firstResult);
    }

    private List<PresupuestoWebDetalladoDos> findPresupuestoWebDetalladoDosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PresupuestoWebDetalladoDos.class));
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

    public PresupuestoWebDetalladoDos findPresupuestoWebDetalladoDos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PresupuestoWebDetalladoDos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoWebDetalladoDosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PresupuestoWebDetalladoDos> rt = cq.from(PresupuestoWebDetalladoDos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
