/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
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
public class FormacionAcademicaGradoJpaController implements Serializable {

    public FormacionAcademicaGradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormacionAcademicaGrado formacionAcademicaGrado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formacionAcademicaGrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormacionAcademicaGrado formacionAcademicaGrado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formacionAcademicaGrado = em.merge(formacionAcademicaGrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formacionAcademicaGrado.getId();
                if (findFormacionAcademicaGrado(id) == null) {
                    throw new NonexistentEntityException("The formacionAcademicaGrado with id " + id + " no longer exists.");
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
            FormacionAcademicaGrado formacionAcademicaGrado;
            try {
                formacionAcademicaGrado = em.getReference(FormacionAcademicaGrado.class, id);
                formacionAcademicaGrado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacionAcademicaGrado with id " + id + " no longer exists.", enfe);
            }
            em.remove(formacionAcademicaGrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormacionAcademicaGrado> findFormacionAcademicaGradoEntities() {
        return findFormacionAcademicaGradoEntities(true, -1, -1);
    }

    public List<FormacionAcademicaGrado> findFormacionAcademicaGradoEntities(int maxResults, int firstResult) {
        return findFormacionAcademicaGradoEntities(false, maxResults, firstResult);
    }

    private List<FormacionAcademicaGrado> findFormacionAcademicaGradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormacionAcademicaGrado.class));
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

    public FormacionAcademicaGrado findFormacionAcademicaGrado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormacionAcademicaGrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionAcademicaGradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormacionAcademicaGrado> rt = cq.from(FormacionAcademicaGrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
