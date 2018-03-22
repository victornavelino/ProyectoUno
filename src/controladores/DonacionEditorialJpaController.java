/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.DonacionEditorial;
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
public class DonacionEditorialJpaController implements Serializable {

    public DonacionEditorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DonacionEditorial donacionEditorial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(donacionEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DonacionEditorial donacionEditorial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            donacionEditorial = em.merge(donacionEditorial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = donacionEditorial.getId();
                if (findDonacionEditorial(id) == null) {
                    throw new NonexistentEntityException("The donacionEditorial with id " + id + " no longer exists.");
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
            DonacionEditorial donacionEditorial;
            try {
                donacionEditorial = em.getReference(DonacionEditorial.class, id);
                donacionEditorial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The donacionEditorial with id " + id + " no longer exists.", enfe);
            }
            em.remove(donacionEditorial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DonacionEditorial> findDonacionEditorialEntities() {
        return findDonacionEditorialEntities(true, -1, -1);
    }

    public List<DonacionEditorial> findDonacionEditorialEntities(int maxResults, int firstResult) {
        return findDonacionEditorialEntities(false, maxResults, firstResult);
    }

    private List<DonacionEditorial> findDonacionEditorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DonacionEditorial.class));
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

    public DonacionEditorial findDonacionEditorial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DonacionEditorial.class, id);
        } finally {
            em.close();
        }
    }

    public int getDonacionEditorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DonacionEditorial> rt = cq.from(DonacionEditorial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
