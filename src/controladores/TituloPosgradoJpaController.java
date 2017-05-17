/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.titulo.TituloPosgrado;
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
public class TituloPosgradoJpaController implements Serializable {

    public TituloPosgradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TituloPosgrado tituloPosgrado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tituloPosgrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TituloPosgrado tituloPosgrado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tituloPosgrado = em.merge(tituloPosgrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tituloPosgrado.getId();
                if (findTituloPosgrado(id) == null) {
                    throw new NonexistentEntityException("The tituloPosgrado with id " + id + " no longer exists.");
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
            TituloPosgrado tituloPosgrado;
            try {
                tituloPosgrado = em.getReference(TituloPosgrado.class, id);
                tituloPosgrado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tituloPosgrado with id " + id + " no longer exists.", enfe);
            }
            em.remove(tituloPosgrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TituloPosgrado> findTituloPosgradoEntities() {
        return findTituloPosgradoEntities(true, -1, -1);
    }

    public List<TituloPosgrado> findTituloPosgradoEntities(int maxResults, int firstResult) {
        return findTituloPosgradoEntities(false, maxResults, firstResult);
    }

    private List<TituloPosgrado> findTituloPosgradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TituloPosgrado.class));
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

    public TituloPosgrado findTituloPosgrado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TituloPosgrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTituloPosgradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TituloPosgrado> rt = cq.from(TituloPosgrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
