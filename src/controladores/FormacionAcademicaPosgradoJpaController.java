/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
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
public class FormacionAcademicaPosgradoJpaController implements Serializable {

    public FormacionAcademicaPosgradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormacionAcademicaPosgrado formacionAcademicaPosgrado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formacionAcademicaPosgrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormacionAcademicaPosgrado formacionAcademicaPosgrado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formacionAcademicaPosgrado = em.merge(formacionAcademicaPosgrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formacionAcademicaPosgrado.getId();
                if (findFormacionAcademicaPosgrado(id) == null) {
                    throw new NonexistentEntityException("The formacionAcademicaPosgrado with id " + id + " no longer exists.");
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
            FormacionAcademicaPosgrado formacionAcademicaPosgrado;
            try {
                formacionAcademicaPosgrado = em.getReference(FormacionAcademicaPosgrado.class, id);
                formacionAcademicaPosgrado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacionAcademicaPosgrado with id " + id + " no longer exists.", enfe);
            }
            em.remove(formacionAcademicaPosgrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormacionAcademicaPosgrado> findFormacionAcademicaPosgradoEntities() {
        return findFormacionAcademicaPosgradoEntities(true, -1, -1);
    }

    public List<FormacionAcademicaPosgrado> findFormacionAcademicaPosgradoEntities(int maxResults, int firstResult) {
        return findFormacionAcademicaPosgradoEntities(false, maxResults, firstResult);
    }

    private List<FormacionAcademicaPosgrado> findFormacionAcademicaPosgradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormacionAcademicaPosgrado.class));
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

    public FormacionAcademicaPosgrado findFormacionAcademicaPosgrado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormacionAcademicaPosgrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionAcademicaPosgradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormacionAcademicaPosgrado> rt = cq.from(FormacionAcademicaPosgrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
