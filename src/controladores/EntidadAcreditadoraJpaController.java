/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.EntidadAcreditadora;
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
public class EntidadAcreditadoraJpaController implements Serializable {

    public EntidadAcreditadoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadAcreditadora entidadAcreditadora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entidadAcreditadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadAcreditadora entidadAcreditadora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entidadAcreditadora = em.merge(entidadAcreditadora);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidadAcreditadora.getId();
                if (findEntidadAcreditadora(id) == null) {
                    throw new NonexistentEntityException("The entidadAcreditadora with id " + id + " no longer exists.");
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
            EntidadAcreditadora entidadAcreditadora;
            try {
                entidadAcreditadora = em.getReference(EntidadAcreditadora.class, id);
                entidadAcreditadora.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadAcreditadora with id " + id + " no longer exists.", enfe);
            }
            em.remove(entidadAcreditadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadAcreditadora> findEntidadAcreditadoraEntities() {
        return findEntidadAcreditadoraEntities(true, -1, -1);
    }

    public List<EntidadAcreditadora> findEntidadAcreditadoraEntities(int maxResults, int firstResult) {
        return findEntidadAcreditadoraEntities(false, maxResults, firstResult);
    }

    private List<EntidadAcreditadora> findEntidadAcreditadoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntidadAcreditadora.class));
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

    public EntidadAcreditadora findEntidadAcreditadora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadAcreditadora.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadAcreditadoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntidadAcreditadora> rt = cq.from(EntidadAcreditadora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
