/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.EntidadFinanciadora;
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
 * @author hugo
 */
public class EntidadFinanciadoraJpaController implements Serializable {

    public EntidadFinanciadoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadFinanciadora entidadFinanciadora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidadFinanciadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadFinanciadora entidadFinanciadora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entidadFinanciadora = em.merge(entidadFinanciadora);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidadFinanciadora.getId();
                if (findEntidadFinanciadora(id) == null) {
                    throw new NonexistentEntityException("The entidadFinanciadora with id " + id + " no longer exists.");
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
            EntidadFinanciadora entidadFinanciadora;
            try {
                entidadFinanciadora = em.getReference(EntidadFinanciadora.class, id);
                entidadFinanciadora.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadFinanciadora with id " + id + " no longer exists.", enfe);
            }
            em.remove(entidadFinanciadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadFinanciadora> findEntidadFinanciadoraEntities() {
        return findEntidadFinanciadoraEntities(true, -1, -1);
    }

    public List<EntidadFinanciadora> findEntidadFinanciadoraEntities(int maxResults, int firstResult) {
        return findEntidadFinanciadoraEntities(false, maxResults, firstResult);
    }

    private List<EntidadFinanciadora> findEntidadFinanciadoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntidadFinanciadora.class));
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

    public EntidadFinanciadora findEntidadFinanciadora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadFinanciadora.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadFinanciadoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntidadFinanciadora> rt = cq.from(EntidadFinanciadora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
