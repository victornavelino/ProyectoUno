/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Pago;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.becas.PostulacionBeca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hugo
 */
public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PostulacionBeca postulacionbeca = pago.getPostulacionbeca();
            if (postulacionbeca != null) {
                postulacionbeca = em.getReference(postulacionbeca.getClass(), postulacionbeca.getId());
                pago.setPostulacionbeca(postulacionbeca);
            }
            em.persist(pago);
            if (postulacionbeca != null) {
                postulacionbeca.getPago().add(pago);
                postulacionbeca = em.merge(postulacionbeca);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getId());
            PostulacionBeca postulacionbecaOld = persistentPago.getPostulacionbeca();
            PostulacionBeca postulacionbecaNew = pago.getPostulacionbeca();
            if (postulacionbecaNew != null) {
                postulacionbecaNew = em.getReference(postulacionbecaNew.getClass(), postulacionbecaNew.getId());
                pago.setPostulacionbeca(postulacionbecaNew);
            }
            pago = em.merge(pago);
            if (postulacionbecaOld != null && !postulacionbecaOld.equals(postulacionbecaNew)) {
                postulacionbecaOld.getPago().remove(pago);
                postulacionbecaOld = em.merge(postulacionbecaOld);
            }
            if (postulacionbecaNew != null && !postulacionbecaNew.equals(postulacionbecaOld)) {
                postulacionbecaNew.getPago().add(pago);
                postulacionbecaNew = em.merge(postulacionbecaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pago.getId();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            PostulacionBeca postulacionbeca = pago.getPostulacionbeca();
            if (postulacionbeca != null) {
                postulacionbeca.getPago().remove(pago);
                postulacionbeca = em.merge(postulacionbeca);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
