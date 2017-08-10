/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.titulo.TituloOtro;
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
public class TituloOtroJpaController implements Serializable {

    public TituloOtroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TituloOtro tituloOtro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tituloOtro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TituloOtro tituloOtro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tituloOtro = em.merge(tituloOtro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tituloOtro.getId();
                if (findTituloOtro(id) == null) {
                    throw new NonexistentEntityException("The tituloOtro with id " + id + " no longer exists.");
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
            TituloOtro tituloOtro;
            try {
                tituloOtro = em.getReference(TituloOtro.class, id);
                tituloOtro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tituloOtro with id " + id + " no longer exists.", enfe);
            }
            em.remove(tituloOtro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TituloOtro> findTituloOtroEntities() {
        return findTituloOtroEntities(true, -1, -1);
    }

    public List<TituloOtro> findTituloOtroEntities(int maxResults, int firstResult) {
        return findTituloOtroEntities(false, maxResults, firstResult);
    }

    private List<TituloOtro> findTituloOtroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TituloOtro.class));
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

    public TituloOtro findTituloOtro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TituloOtro.class, id);
        } finally {
            em.close();
        }
    }

    public int getTituloOtroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TituloOtro> rt = cq.from(TituloOtro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
