/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.titulo.TituloGrado;
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
public class TituloGradoJpaController implements Serializable {

    public TituloGradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TituloGrado tituloGrado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tituloGrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TituloGrado tituloGrado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tituloGrado = em.merge(tituloGrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tituloGrado.getId();
                if (findTituloGrado(id) == null) {
                    throw new NonexistentEntityException("The tituloGrado with id " + id + " no longer exists.");
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
            TituloGrado tituloGrado;
            try {
                tituloGrado = em.getReference(TituloGrado.class, id);
                tituloGrado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tituloGrado with id " + id + " no longer exists.", enfe);
            }
            em.remove(tituloGrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TituloGrado> findTituloGradoEntities() {
        return findTituloGradoEntities(true, -1, -1);
    }

    public List<TituloGrado> findTituloGradoEntities(int maxResults, int firstResult) {
        return findTituloGradoEntities(false, maxResults, firstResult);
    }

    private List<TituloGrado> findTituloGradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TituloGrado.class));
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

    public TituloGrado findTituloGrado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TituloGrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTituloGradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TituloGrado> rt = cq.from(TituloGrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
