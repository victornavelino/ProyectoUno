/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.NotaExterna;
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
public class NotaExternaJpaController implements Serializable {

    public NotaExternaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaExterna notaExterna) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notaExterna);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotaExterna notaExterna) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notaExterna = em.merge(notaExterna);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notaExterna.getId();
                if (findNotaExterna(id) == null) {
                    throw new NonexistentEntityException("The notaExterna with id " + id + " no longer exists.");
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
            NotaExterna notaExterna;
            try {
                notaExterna = em.getReference(NotaExterna.class, id);
                notaExterna.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaExterna with id " + id + " no longer exists.", enfe);
            }
            em.remove(notaExterna);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotaExterna> findNotaExternaEntities() {
        return findNotaExternaEntities(true, -1, -1);
    }

    public List<NotaExterna> findNotaExternaEntities(int maxResults, int firstResult) {
        return findNotaExternaEntities(false, maxResults, firstResult);
    }

    private List<NotaExterna> findNotaExternaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotaExterna.class));
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

    public NotaExterna findNotaExterna(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaExterna.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaExternaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotaExterna> rt = cq.from(NotaExterna.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
