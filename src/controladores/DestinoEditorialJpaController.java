/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.DestinoEditorial;
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
public class DestinoEditorialJpaController implements Serializable {

    public DestinoEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DestinoEditorial destinoEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(destinoEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DestinoEditorial destinoEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            destinoEditorial = em.merge(destinoEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = destinoEditorial.getId();
                if (findDestinoEditorial(id) == null) {
                    throw new NonexistentEntityException("The destinoEditorial with id " + id + " no longer exists.");
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
            DestinoEditorial destinoEditorial;
            try {
                destinoEditorial = em.getReference(DestinoEditorial.class, id);
                destinoEditorial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destinoEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(destinoEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DestinoEditorial> findDestinoEditorialEntities() {
        return findDestinoEditorialEntities(true, -1, -1);
    }

    public List<DestinoEditorial> findDestinoEditorialEntities(int maxResults, int firstResult) {
        return findDestinoEditorialEntities(false, maxResults, firstResult);
    }

    private List<DestinoEditorial> findDestinoEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DestinoEditorial.class));
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

    public DestinoEditorial findDestinoEditorial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DestinoEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getDestinoEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DestinoEditorial> rt = cq.from(DestinoEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
