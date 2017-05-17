/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.FondosySaldos;
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
 * @author ruben
 */
public class FondosySaldosJpaController implements Serializable {

    public FondosySaldosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FondosySaldos fondosySaldos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fondosySaldos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FondosySaldos fondosySaldos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fondosySaldos = em.merge(fondosySaldos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fondosySaldos.getId();
                if (findFondosySaldos(id) == null) {
                    throw new NonexistentEntityException("The fondosySaldos with id " + id + " no longer exists.");
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
            FondosySaldos fondosySaldos;
            try {
                fondosySaldos = em.getReference(FondosySaldos.class, id);
                fondosySaldos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fondosySaldos with id " + id + " no longer exists.", enfe);
            }
            em.remove(fondosySaldos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FondosySaldos> findFondosySaldosEntities() {
        return findFondosySaldosEntities(true, -1, -1);
    }

    public List<FondosySaldos> findFondosySaldosEntities(int maxResults, int firstResult) {
        return findFondosySaldosEntities(false, maxResults, firstResult);
    }

    private List<FondosySaldos> findFondosySaldosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FondosySaldos.class));
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

    public FondosySaldos findFondosySaldos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FondosySaldos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFondosySaldosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FondosySaldos> rt = cq.from(FondosySaldos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
