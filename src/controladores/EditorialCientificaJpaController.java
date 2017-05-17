/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EditorialCientifica;
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
 * @author francisco
 */
public class EditorialCientificaJpaController implements Serializable {

    public EditorialCientificaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EditorialCientifica editorialCientifica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(editorialCientifica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EditorialCientifica editorialCientifica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            editorialCientifica = em.merge(editorialCientifica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = editorialCientifica.getId();
                if (findEditorialCientifica(id) == null) {
                    throw new NonexistentEntityException("The editorialCientifica with id " + id + " no longer exists.");
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
            EditorialCientifica editorialCientifica;
            try {
                editorialCientifica = em.getReference(EditorialCientifica.class, id);
                editorialCientifica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The editorialCientifica with id " + id + " no longer exists.", enfe);
            }
            em.remove(editorialCientifica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EditorialCientifica> findEditorialCientificaEntities() {
        return findEditorialCientificaEntities(true, -1, -1);
    }

    public List<EditorialCientifica> findEditorialCientificaEntities(int maxResults, int firstResult) {
        return findEditorialCientificaEntities(false, maxResults, firstResult);
    }

    private List<EditorialCientifica> findEditorialCientificaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EditorialCientifica.class));
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

    public EditorialCientifica findEditorialCientifica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EditorialCientifica.class, id);
        } finally {
            em.close();
        }
    }

    public int getEditorialCientificaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EditorialCientifica> rt = cq.from(EditorialCientifica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
