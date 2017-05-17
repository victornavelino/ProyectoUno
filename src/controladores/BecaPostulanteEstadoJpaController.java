/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.BecaPostulanteEstado;
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
 * @author huguito
 */
public class BecaPostulanteEstadoJpaController implements Serializable {

    public BecaPostulanteEstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BecaPostulanteEstado becaPostulanteEstado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(becaPostulanteEstado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BecaPostulanteEstado becaPostulanteEstado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            becaPostulanteEstado = em.merge(becaPostulanteEstado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = becaPostulanteEstado.getId();
                if (findBecaPostulanteEstado(id) == null) {
                    throw new NonexistentEntityException("The becaPostulanteEstado with id " + id + " no longer exists.");
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
            BecaPostulanteEstado becaPostulanteEstado;
            try {
                becaPostulanteEstado = em.getReference(BecaPostulanteEstado.class, id);
                becaPostulanteEstado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The becaPostulanteEstado with id " + id + " no longer exists.", enfe);
            }
            em.remove(becaPostulanteEstado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BecaPostulanteEstado> findBecaPostulanteEstadoEntities() {
        return findBecaPostulanteEstadoEntities(true, -1, -1);
    }

    public List<BecaPostulanteEstado> findBecaPostulanteEstadoEntities(int maxResults, int firstResult) {
        return findBecaPostulanteEstadoEntities(false, maxResults, firstResult);
    }

    private List<BecaPostulanteEstado> findBecaPostulanteEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BecaPostulanteEstado.class));
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

    public BecaPostulanteEstado findBecaPostulanteEstado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BecaPostulanteEstado.class, id);
        } finally {
            em.close();
        }
    }

    public int getBecaPostulanteEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BecaPostulanteEstado> rt = cq.from(BecaPostulanteEstado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
