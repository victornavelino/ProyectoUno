/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.FormatoEditorial;
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
 * @author walter
 */
public class FormatoEditorialJpaController implements Serializable {

    public FormatoEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormatoEditorial formatoEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formatoEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormatoEditorial formatoEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formatoEditorial = em.merge(formatoEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formatoEditorial.getId();
                if (findFormatoEditorial(id) == null) {
                    throw new NonexistentEntityException("The formatoEditorial with id " + id + " no longer exists.");
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
            FormatoEditorial formatoEditorial;
            try {
                formatoEditorial = em.getReference(FormatoEditorial.class, id);
                formatoEditorial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formatoEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(formatoEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormatoEditorial> findFormatoEditorialEntities() {
        return findFormatoEditorialEntities(true, -1, -1);
    }

    public List<FormatoEditorial> findFormatoEditorialEntities(int maxResults, int firstResult) {
        return findFormatoEditorialEntities(false, maxResults, firstResult);
    }

    private List<FormatoEditorial> findFormatoEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormatoEditorial.class));
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

    public FormatoEditorial findFormatoEditorial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormatoEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormatoEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormatoEditorial> rt = cq.from(FormatoEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
