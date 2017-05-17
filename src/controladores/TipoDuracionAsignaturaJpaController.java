/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
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
public class TipoDuracionAsignaturaJpaController implements Serializable {

    public TipoDuracionAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDuracionAsignatura tipoDuracionAsignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoDuracionAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDuracionAsignatura tipoDuracionAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoDuracionAsignatura = em.merge(tipoDuracionAsignatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoDuracionAsignatura.getId();
                if (findTipoDuracionAsignatura(id) == null) {
                    throw new NonexistentEntityException("The tipoDuracionAsignatura with id " + id + " no longer exists.");
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
            TipoDuracionAsignatura tipoDuracionAsignatura;
            try {
                tipoDuracionAsignatura = em.getReference(TipoDuracionAsignatura.class, id);
                tipoDuracionAsignatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDuracionAsignatura with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoDuracionAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDuracionAsignatura> findTipoDuracionAsignaturaEntities() {
        return findTipoDuracionAsignaturaEntities(true, -1, -1);
    }

    public List<TipoDuracionAsignatura> findTipoDuracionAsignaturaEntities(int maxResults, int firstResult) {
        return findTipoDuracionAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<TipoDuracionAsignatura> findTipoDuracionAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDuracionAsignatura.class));
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

    public TipoDuracionAsignatura findTipoDuracionAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDuracionAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDuracionAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDuracionAsignatura> rt = cq.from(TipoDuracionAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
