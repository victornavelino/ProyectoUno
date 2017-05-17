/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaOtra;
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
public class FormacionAcademicaOtraJpaController implements Serializable {

    public FormacionAcademicaOtraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormacionAcademicaOtra formacionAcademicaOtra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formacionAcademicaOtra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormacionAcademicaOtra formacionAcademicaOtra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formacionAcademicaOtra = em.merge(formacionAcademicaOtra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formacionAcademicaOtra.getId();
                if (findFormacionAcademicaOtra(id) == null) {
                    throw new NonexistentEntityException("The formacionAcademicaOtra with id " + id + " no longer exists.");
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
            FormacionAcademicaOtra formacionAcademicaOtra;
            try {
                formacionAcademicaOtra = em.getReference(FormacionAcademicaOtra.class, id);
                formacionAcademicaOtra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacionAcademicaOtra with id " + id + " no longer exists.", enfe);
            }
            em.remove(formacionAcademicaOtra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormacionAcademicaOtra> findFormacionAcademicaOtraEntities() {
        return findFormacionAcademicaOtraEntities(true, -1, -1);
    }

    public List<FormacionAcademicaOtra> findFormacionAcademicaOtraEntities(int maxResults, int firstResult) {
        return findFormacionAcademicaOtraEntities(false, maxResults, firstResult);
    }

    private List<FormacionAcademicaOtra> findFormacionAcademicaOtraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormacionAcademicaOtra.class));
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

    public FormacionAcademicaOtra findFormacionAcademicaOtra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormacionAcademicaOtra.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionAcademicaOtraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormacionAcademicaOtra> rt = cq.from(FormacionAcademicaOtra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
