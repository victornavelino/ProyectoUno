/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.FormatoLibro;
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
 * @author Francisco
 */
public class FormatoLibroJpaController implements Serializable {

    public FormatoLibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormatoLibro formatoLibro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formatoLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormatoLibro formatoLibro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formatoLibro = em.merge(formatoLibro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formatoLibro.getId();
                if (findFormatoLibro(id) == null) {
                    throw new NonexistentEntityException("The formatoLibro with id " + id + " no longer exists.");
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
            FormatoLibro formatoLibro;
            try {
                formatoLibro = em.getReference(FormatoLibro.class, id);
                formatoLibro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formatoLibro with id " + id + " no longer exists.", enfe);
            }
            em.remove(formatoLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormatoLibro> findFormatoLibroEntities() {
        return findFormatoLibroEntities(true, -1, -1);
    }

    public List<FormatoLibro> findFormatoLibroEntities(int maxResults, int firstResult) {
        return findFormatoLibroEntities(false, maxResults, firstResult);
    }

    private List<FormatoLibro> findFormatoLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormatoLibro.class));
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

    public FormatoLibro findFormatoLibro(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormatoLibro.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormatoLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormatoLibro> rt = cq.from(FormatoLibro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
