/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.economico.PagoEconomico;
import entidades.economico.Rendicion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wbivanco
 */
public class RendicionJpaController implements Serializable {

    public RendicionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rendicion rendicion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoEconomico pagoEconomico = rendicion.getPagoEconomico();
            if (pagoEconomico != null) {
                pagoEconomico = em.getReference(pagoEconomico.getClass(), pagoEconomico.getId());
                rendicion.setPagoEconomico(pagoEconomico);
            }
            em.persist(rendicion);
            if (pagoEconomico != null) {
                pagoEconomico.getRendiciones().add(rendicion);
                pagoEconomico = em.merge(pagoEconomico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rendicion rendicion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rendicion persistentRendicion = em.find(Rendicion.class, rendicion.getId());
            PagoEconomico pagoEconomicoOld = persistentRendicion.getPagoEconomico();
            PagoEconomico pagoEconomicoNew = rendicion.getPagoEconomico();
            if (pagoEconomicoNew != null) {
                pagoEconomicoNew = em.getReference(pagoEconomicoNew.getClass(), pagoEconomicoNew.getId());
                rendicion.setPagoEconomico(pagoEconomicoNew);
            }
            rendicion = em.merge(rendicion);
            if (pagoEconomicoOld != null && !pagoEconomicoOld.equals(pagoEconomicoNew)) {
                pagoEconomicoOld.getRendiciones().remove(rendicion);
                pagoEconomicoOld = em.merge(pagoEconomicoOld);
            }
            if (pagoEconomicoNew != null && !pagoEconomicoNew.equals(pagoEconomicoOld)) {
                pagoEconomicoNew.getRendiciones().add(rendicion);
                pagoEconomicoNew = em.merge(pagoEconomicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rendicion.getId();
                if (findRendicion(id) == null) {
                    throw new NonexistentEntityException("The rendicion with id " + id + " no longer exists.");
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
            Rendicion rendicion;
            try {
                rendicion = em.getReference(Rendicion.class, id);
                rendicion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rendicion with id " + id + " no longer exists.", enfe);
            }
            PagoEconomico pagoEconomico = rendicion.getPagoEconomico();
            if (pagoEconomico != null) {
                pagoEconomico.getRendiciones().remove(rendicion);
                pagoEconomico = em.merge(pagoEconomico);
            }
            em.remove(rendicion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rendicion> findRendicionEntities() {
        return findRendicionEntities(true, -1, -1);
    }

    public List<Rendicion> findRendicionEntities(int maxResults, int firstResult) {
        return findRendicionEntities(false, maxResults, firstResult);
    }

    private List<Rendicion> findRendicionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rendicion.class));
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

    public Rendicion findRendicion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rendicion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRendicionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rendicion> rt = cq.from(Rendicion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
