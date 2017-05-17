/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.DocumentacionBeca;
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
public class DocumentacionBecaJpaController implements Serializable {

    public DocumentacionBecaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentacionBeca documentacionBeca) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(documentacionBeca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentacionBeca documentacionBeca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            documentacionBeca = em.merge(documentacionBeca);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = documentacionBeca.getId();
                if (findDocumentacionBeca(id) == null) {
                    throw new NonexistentEntityException("The documentacionBeca with id " + id + " no longer exists.");
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
            DocumentacionBeca documentacionBeca;
            try {
                documentacionBeca = em.getReference(DocumentacionBeca.class, id);
                documentacionBeca.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentacionBeca with id " + id + " no longer exists.", enfe);
            }
            em.remove(documentacionBeca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentacionBeca> findDocumentacionBecaEntities() {
        return findDocumentacionBecaEntities(true, -1, -1);
    }

    public List<DocumentacionBeca> findDocumentacionBecaEntities(int maxResults, int firstResult) {
        return findDocumentacionBecaEntities(false, maxResults, firstResult);
    }

    private List<DocumentacionBeca> findDocumentacionBecaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DocumentacionBeca.class));
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

    public DocumentacionBeca findDocumentacionBeca(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentacionBeca.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentacionBecaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DocumentacionBeca> rt = cq.from(DocumentacionBeca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
