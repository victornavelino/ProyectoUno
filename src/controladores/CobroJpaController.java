/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.Cobro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.economico.PagoEconomico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wbivanco
 */
public class CobroJpaController implements Serializable {

    public CobroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cobro cobro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoEconomico pago = cobro.getPago();
            if (pago != null) {
                pago = em.getReference(pago.getClass(), pago.getId());
                cobro.setPago(pago);
            }
            em.persist(cobro);
            if (pago != null) {
                pago.getCobros().add(cobro);
                pago = em.merge(pago);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cobro cobro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cobro persistentCobro = em.find(Cobro.class, cobro.getId());
            PagoEconomico pagoOld = persistentCobro.getPago();
            PagoEconomico pagoNew = cobro.getPago();
            if (pagoNew != null) {
                pagoNew = em.getReference(pagoNew.getClass(), pagoNew.getId());
                cobro.setPago(pagoNew);
            }
            cobro = em.merge(cobro);
            if (pagoOld != null && !pagoOld.equals(pagoNew)) {
                pagoOld.getCobros().remove(cobro);
                pagoOld = em.merge(pagoOld);
            }
            if (pagoNew != null && !pagoNew.equals(pagoOld)) {
                pagoNew.getCobros().add(cobro);
                pagoNew = em.merge(pagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cobro.getId();
                if (findCobro(id) == null) {
                    throw new NonexistentEntityException("The cobro with id " + id + " no longer exists.");
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
            Cobro cobro;
            try {
                cobro = em.getReference(Cobro.class, id);
                cobro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cobro with id " + id + " no longer exists.", enfe);
            }
            PagoEconomico pago = cobro.getPago();
            if (pago != null) {
                pago.getCobros().remove(cobro);
                pago = em.merge(pago);
            }
            em.remove(cobro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cobro> findCobroEntities() {
        return findCobroEntities(true, -1, -1);
    }

    public List<Cobro> findCobroEntities(int maxResults, int firstResult) {
        return findCobroEntities(false, maxResults, firstResult);
    }

    private List<Cobro> findCobroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cobro.class));
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

    public Cobro findCobro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cobro.class, id);
        } finally {
            em.close();
        }
    }

    public int getCobroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cobro> rt = cq.from(Cobro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
