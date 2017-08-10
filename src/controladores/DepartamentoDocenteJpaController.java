/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.DepartamentoDocente;
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
public class DepartamentoDocenteJpaController implements Serializable {

    public DepartamentoDocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DepartamentoDocente departamentoDocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(departamentoDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DepartamentoDocente departamentoDocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            departamentoDocente = em.merge(departamentoDocente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = departamentoDocente.getId();
                if (findDepartamentoDocente(id) == null) {
                    throw new NonexistentEntityException("The departamentoDocente with id " + id + " no longer exists.");
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
            DepartamentoDocente departamentoDocente;
            try {
                departamentoDocente = em.getReference(DepartamentoDocente.class, id);
                departamentoDocente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentoDocente with id " + id + " no longer exists.", enfe);
            }
            em.remove(departamentoDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DepartamentoDocente> findDepartamentoDocenteEntities() {
        return findDepartamentoDocenteEntities(true, -1, -1);
    }

    public List<DepartamentoDocente> findDepartamentoDocenteEntities(int maxResults, int firstResult) {
        return findDepartamentoDocenteEntities(false, maxResults, firstResult);
    }

    private List<DepartamentoDocente> findDepartamentoDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DepartamentoDocente.class));
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

    public DepartamentoDocente findDepartamentoDocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DepartamentoDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DepartamentoDocente> rt = cq.from(DepartamentoDocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
