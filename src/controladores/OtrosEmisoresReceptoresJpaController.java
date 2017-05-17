/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.OtrosEmisoresReceptores;
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
public class OtrosEmisoresReceptoresJpaController implements Serializable {

    public OtrosEmisoresReceptoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OtrosEmisoresReceptores otrosEmisoresReceptores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(otrosEmisoresReceptores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OtrosEmisoresReceptores otrosEmisoresReceptores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            otrosEmisoresReceptores = em.merge(otrosEmisoresReceptores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = otrosEmisoresReceptores.getId();
                if (findOtrosEmisoresReceptores(id) == null) {
                    throw new NonexistentEntityException("The otrosEmisoresReceptores with id " + id + " no longer exists.");
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
            OtrosEmisoresReceptores otrosEmisoresReceptores;
            try {
                otrosEmisoresReceptores = em.getReference(OtrosEmisoresReceptores.class, id);
                otrosEmisoresReceptores.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The otrosEmisoresReceptores with id " + id + " no longer exists.", enfe);
            }
            em.remove(otrosEmisoresReceptores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OtrosEmisoresReceptores> findOtrosEmisoresReceptoresEntities() {
        return findOtrosEmisoresReceptoresEntities(true, -1, -1);
    }

    public List<OtrosEmisoresReceptores> findOtrosEmisoresReceptoresEntities(int maxResults, int firstResult) {
        return findOtrosEmisoresReceptoresEntities(false, maxResults, firstResult);
    }

    private List<OtrosEmisoresReceptores> findOtrosEmisoresReceptoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OtrosEmisoresReceptores.class));
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

    public OtrosEmisoresReceptores findOtrosEmisoresReceptores(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OtrosEmisoresReceptores.class, id);
        } finally {
            em.close();
        }
    }

    public int getOtrosEmisoresReceptoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OtrosEmisoresReceptores> rt = cq.from(OtrosEmisoresReceptores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
