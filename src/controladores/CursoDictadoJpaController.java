/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.CursoDictado;
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
public class CursoDictadoJpaController implements Serializable {

    public CursoDictadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CursoDictado cursoDictado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cursoDictado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoDictado cursoDictado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cursoDictado = em.merge(cursoDictado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cursoDictado.getId();
                if (findCursoDictado(id) == null) {
                    throw new NonexistentEntityException("The cursoDictado with id " + id + " no longer exists.");
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
            CursoDictado cursoDictado;
            try {
                cursoDictado = em.getReference(CursoDictado.class, id);
                cursoDictado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoDictado with id " + id + " no longer exists.", enfe);
            }
            em.remove(cursoDictado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CursoDictado> findCursoDictadoEntities() {
        return findCursoDictadoEntities(true, -1, -1);
    }

    public List<CursoDictado> findCursoDictadoEntities(int maxResults, int firstResult) {
        return findCursoDictadoEntities(false, maxResults, firstResult);
    }

    private List<CursoDictado> findCursoDictadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoDictado.class));
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

    public CursoDictado findCursoDictado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoDictado.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoDictadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoDictado> rt = cq.from(CursoDictado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
