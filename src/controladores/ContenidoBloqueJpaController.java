/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.evaluacion.ContenidoBloque;
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
public class ContenidoBloqueJpaController implements Serializable {

    public ContenidoBloqueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContenidoBloque contenidoBloque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contenidoBloque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContenidoBloque contenidoBloque) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            contenidoBloque = em.merge(contenidoBloque);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contenidoBloque.getId();
                if (findContenidoBloque(id) == null) {
                    throw new NonexistentEntityException("The contenidoBloque with id " + id + " no longer exists.");
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
            ContenidoBloque contenidoBloque;
            try {
                contenidoBloque = em.getReference(ContenidoBloque.class, id);
                contenidoBloque.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenidoBloque with id " + id + " no longer exists.", enfe);
            }
            em.remove(contenidoBloque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContenidoBloque> findContenidoBloqueEntities() {
        return findContenidoBloqueEntities(true, -1, -1);
    }

    public List<ContenidoBloque> findContenidoBloqueEntities(int maxResults, int firstResult) {
        return findContenidoBloqueEntities(false, maxResults, firstResult);
    }

    private List<ContenidoBloque> findContenidoBloqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContenidoBloque.class));
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

    public ContenidoBloque findContenidoBloque(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContenidoBloque.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenidoBloqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContenidoBloque> rt = cq.from(ContenidoBloque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
