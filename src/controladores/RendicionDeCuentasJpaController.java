/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.picto.RendicionDeCuentas;
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
public class RendicionDeCuentasJpaController implements Serializable {

    public RendicionDeCuentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RendicionDeCuentas rendicionDeCuentas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rendicionDeCuentas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RendicionDeCuentas rendicionDeCuentas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rendicionDeCuentas = em.merge(rendicionDeCuentas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rendicionDeCuentas.getId();
                if (findRendicionDeCuentas(id) == null) {
                    throw new NonexistentEntityException("The rendicionDeCuentas with id " + id + " no longer exists.");
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
            RendicionDeCuentas rendicionDeCuentas;
            try {
                rendicionDeCuentas = em.getReference(RendicionDeCuentas.class, id);
                rendicionDeCuentas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rendicionDeCuentas with id " + id + " no longer exists.", enfe);
            }
            em.remove(rendicionDeCuentas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RendicionDeCuentas> findRendicionDeCuentasEntities() {
        return findRendicionDeCuentasEntities(true, -1, -1);
    }

    public List<RendicionDeCuentas> findRendicionDeCuentasEntities(int maxResults, int firstResult) {
        return findRendicionDeCuentasEntities(false, maxResults, firstResult);
    }

    private List<RendicionDeCuentas> findRendicionDeCuentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RendicionDeCuentas.class));
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

    public RendicionDeCuentas findRendicionDeCuentas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RendicionDeCuentas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRendicionDeCuentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RendicionDeCuentas> rt = cq.from(RendicionDeCuentas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
