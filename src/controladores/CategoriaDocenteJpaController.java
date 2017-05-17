/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.CategoriaDocente;
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
public class CategoriaDocenteJpaController implements Serializable {

    public CategoriaDocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaDocente categoriaDocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaDocente categoriaDocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaDocente = em.merge(categoriaDocente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoriaDocente.getId();
                if (findCategoriaDocente(id) == null) {
                    throw new NonexistentEntityException("The categoriaDocente with id " + id + " no longer exists.");
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
            CategoriaDocente categoriaDocente;
            try {
                categoriaDocente = em.getReference(CategoriaDocente.class, id);
                categoriaDocente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaDocente with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaDocente> findCategoriaDocenteEntities() {
        return findCategoriaDocenteEntities(true, -1, -1);
    }

    public List<CategoriaDocente> findCategoriaDocenteEntities(int maxResults, int firstResult) {
        return findCategoriaDocenteEntities(false, maxResults, firstResult);
    }

    private List<CategoriaDocente> findCategoriaDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaDocente.class));
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

    public CategoriaDocente findCategoriaDocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaDocente> rt = cq.from(CategoriaDocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
