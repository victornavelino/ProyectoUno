/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.ArchivoNotas;
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
 * @author vouilloz
 */
public class ArchivoNotasJpaController implements Serializable {

    public ArchivoNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArchivoNotas archivoNotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(archivoNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArchivoNotas archivoNotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            archivoNotas = em.merge(archivoNotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = archivoNotas.getId();
                if (findArchivoNotas(id) == null) {
                    throw new NonexistentEntityException("The archivoNotas with id " + id + " no longer exists.");
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
            ArchivoNotas archivoNotas;
            try {
                archivoNotas = em.getReference(ArchivoNotas.class, id);
                archivoNotas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The archivoNotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(archivoNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArchivoNotas> findArchivoNotasEntities() {
        return findArchivoNotasEntities(true, -1, -1);
    }

    public List<ArchivoNotas> findArchivoNotasEntities(int maxResults, int firstResult) {
        return findArchivoNotasEntities(false, maxResults, firstResult);
    }

    private List<ArchivoNotas> findArchivoNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArchivoNotas.class));
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

    public ArchivoNotas findArchivoNotas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArchivoNotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getArchivoNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArchivoNotas> rt = cq.from(ArchivoNotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
