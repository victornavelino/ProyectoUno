/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.NotaEntradaSalida;
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
public class NotaEntradaSalidaJpaController implements Serializable {

    public NotaEntradaSalidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaEntradaSalida notaEntradaSalida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notaEntradaSalida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotaEntradaSalida notaEntradaSalida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notaEntradaSalida = em.merge(notaEntradaSalida);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notaEntradaSalida.getId();
                if (findNotaEntradaSalida(id) == null) {
                    throw new NonexistentEntityException("The notaEntradaSalida with id " + id + " no longer exists.");
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
            NotaEntradaSalida notaEntradaSalida;
            try {
                notaEntradaSalida = em.getReference(NotaEntradaSalida.class, id);
                notaEntradaSalida.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaEntradaSalida with id " + id + " no longer exists.", enfe);
            }
            em.remove(notaEntradaSalida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotaEntradaSalida> findNotaEntradaSalidaEntities() {
        return findNotaEntradaSalidaEntities(true, -1, -1);
    }

    public List<NotaEntradaSalida> findNotaEntradaSalidaEntities(int maxResults, int firstResult) {
        return findNotaEntradaSalidaEntities(false, maxResults, firstResult);
    }

    private List<NotaEntradaSalida> findNotaEntradaSalidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotaEntradaSalida.class));
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

    public NotaEntradaSalida findNotaEntradaSalida(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaEntradaSalida.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaEntradaSalidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotaEntradaSalida> rt = cq.from(NotaEntradaSalida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
