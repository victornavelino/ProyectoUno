/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyectoWeb.PresupuestoWebDetallado;
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
public class PresupuestoWebDetalladoJpaController implements Serializable {

    public PresupuestoWebDetalladoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PresupuestoWebDetallado presupuestoWebDetallado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb proyectoWeb = presupuestoWebDetallado.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb = em.getReference(proyectoWeb.getClass(), proyectoWeb.getId());
                presupuestoWebDetallado.setProyectoWeb(proyectoWeb);
            }
            em.persist(presupuestoWebDetallado);
            if (proyectoWeb != null) {
                PresupuestoWebDetallado oldPresupuestoWebAnioUnoOfProyectoWeb = proyectoWeb.getPresupuestoWebAnioUno();
                if (oldPresupuestoWebAnioUnoOfProyectoWeb != null) {
                    oldPresupuestoWebAnioUnoOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioUnoOfProyectoWeb = em.merge(oldPresupuestoWebAnioUnoOfProyectoWeb);
                }
                proyectoWeb.setPresupuestoWebAnioUno(presupuestoWebDetallado);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PresupuestoWebDetallado presupuestoWebDetallado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWebDetallado persistentPresupuestoWebDetallado = em.find(PresupuestoWebDetallado.class, presupuestoWebDetallado.getId());
            ProyectoWeb proyectoWebOld = persistentPresupuestoWebDetallado.getProyectoWeb();
            ProyectoWeb proyectoWebNew = presupuestoWebDetallado.getProyectoWeb();
            if (proyectoWebNew != null) {
                proyectoWebNew = em.getReference(proyectoWebNew.getClass(), proyectoWebNew.getId());
                presupuestoWebDetallado.setProyectoWeb(proyectoWebNew);
            }
            presupuestoWebDetallado = em.merge(presupuestoWebDetallado);
            if (proyectoWebOld != null && !proyectoWebOld.equals(proyectoWebNew)) {
                proyectoWebOld.setPresupuestoWebAnioUno(null);
                proyectoWebOld = em.merge(proyectoWebOld);
            }
            if (proyectoWebNew != null && !proyectoWebNew.equals(proyectoWebOld)) {
                PresupuestoWebDetallado oldPresupuestoWebAnioUnoOfProyectoWeb = proyectoWebNew.getPresupuestoWebAnioUno();
                if (oldPresupuestoWebAnioUnoOfProyectoWeb != null) {
                    oldPresupuestoWebAnioUnoOfProyectoWeb.setProyectoWeb(null);
                    oldPresupuestoWebAnioUnoOfProyectoWeb = em.merge(oldPresupuestoWebAnioUnoOfProyectoWeb);
                }
                proyectoWebNew.setPresupuestoWebAnioUno(presupuestoWebDetallado);
                proyectoWebNew = em.merge(proyectoWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuestoWebDetallado.getId();
                if (findPresupuestoWebDetallado(id) == null) {
                    throw new NonexistentEntityException("The presupuestoWebDetallado with id " + id + " no longer exists.");
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
            PresupuestoWebDetallado presupuestoWebDetallado;
            try {
                presupuestoWebDetallado = em.getReference(PresupuestoWebDetallado.class, id);
                presupuestoWebDetallado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuestoWebDetallado with id " + id + " no longer exists.", enfe);
            }
            ProyectoWeb proyectoWeb = presupuestoWebDetallado.getProyectoWeb();
            if (proyectoWeb != null) {
                proyectoWeb.setPresupuestoWebAnioUno(null);
                proyectoWeb = em.merge(proyectoWeb);
            }
            em.remove(presupuestoWebDetallado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PresupuestoWebDetallado> findPresupuestoWebDetalladoEntities() {
        return findPresupuestoWebDetalladoEntities(true, -1, -1);
    }

    public List<PresupuestoWebDetallado> findPresupuestoWebDetalladoEntities(int maxResults, int firstResult) {
        return findPresupuestoWebDetalladoEntities(false, maxResults, firstResult);
    }

    private List<PresupuestoWebDetallado> findPresupuestoWebDetalladoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PresupuestoWebDetallado.class));
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

    public PresupuestoWebDetallado findPresupuestoWebDetallado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PresupuestoWebDetallado.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoWebDetalladoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PresupuestoWebDetallado> rt = cq.from(PresupuestoWebDetallado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
