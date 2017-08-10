/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.TipoBeneficiarioBecas;
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
public class TipoBeneficiarioBecasJpaController implements Serializable {

    public TipoBeneficiarioBecasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoBeneficiarioBecas tipoBeneficiarioBecas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoBeneficiarioBecas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoBeneficiarioBecas tipoBeneficiarioBecas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoBeneficiarioBecas = em.merge(tipoBeneficiarioBecas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoBeneficiarioBecas.getId();
                if (findTipoBeneficiarioBecas(id) == null) {
                    throw new NonexistentEntityException("The tipoBeneficiarioBecas with id " + id + " no longer exists.");
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
            TipoBeneficiarioBecas tipoBeneficiarioBecas;
            try {
                tipoBeneficiarioBecas = em.getReference(TipoBeneficiarioBecas.class, id);
                tipoBeneficiarioBecas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoBeneficiarioBecas with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoBeneficiarioBecas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoBeneficiarioBecas> findTipoBeneficiarioBecasEntities() {
        return findTipoBeneficiarioBecasEntities(true, -1, -1);
    }

    public List<TipoBeneficiarioBecas> findTipoBeneficiarioBecasEntities(int maxResults, int firstResult) {
        return findTipoBeneficiarioBecasEntities(false, maxResults, firstResult);
    }

    private List<TipoBeneficiarioBecas> findTipoBeneficiarioBecasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoBeneficiarioBecas.class));
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

    public TipoBeneficiarioBecas findTipoBeneficiarioBecas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoBeneficiarioBecas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoBeneficiarioBecasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoBeneficiarioBecas> rt = cq.from(TipoBeneficiarioBecas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
