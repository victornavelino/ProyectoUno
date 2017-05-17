/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.BecaInforme;
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
 * @author huguito
 */
public class BecaInformeJpaController implements Serializable {

    public BecaInformeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BecaInforme becaInforme) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(becaInforme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BecaInforme becaInforme) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            becaInforme = em.merge(becaInforme);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = becaInforme.getId();
                if (findBecaInforme(id) == null) {
                    throw new NonexistentEntityException("The becaInforme with id " + id + " no longer exists.");
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
            BecaInforme becaInforme;
            try {
                becaInforme = em.getReference(BecaInforme.class, id);
                becaInforme.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The becaInforme with id " + id + " no longer exists.", enfe);
            }
            em.remove(becaInforme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BecaInforme> findBecaInformeEntities() {
        return findBecaInformeEntities(true, -1, -1);
    }

    public List<BecaInforme> findBecaInformeEntities(int maxResults, int firstResult) {
        return findBecaInformeEntities(false, maxResults, firstResult);
    }

    private List<BecaInforme> findBecaInformeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BecaInforme.class));
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

    public BecaInforme findBecaInforme(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BecaInforme.class, id);
        } finally {
            em.close();
        }
    }

    public int getBecaInformeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BecaInforme> rt = cq.from(BecaInforme.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
