/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademica;
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
 * @author Administrador
 */
public class FormacionAcademicaJpaController implements Serializable {

    public FormacionAcademicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormacionAcademica formacionAcademica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formacionAcademica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormacionAcademica formacionAcademica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formacionAcademica = em.merge(formacionAcademica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formacionAcademica.getId();
                if (findFormacionAcademica(id) == null) {
                    throw new NonexistentEntityException("The formacionAcademica with id " + id + " no longer exists.");
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
            FormacionAcademica formacionAcademica;
            try {
                formacionAcademica = em.getReference(FormacionAcademica.class, id);
                formacionAcademica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacionAcademica with id " + id + " no longer exists.", enfe);
            }
            em.remove(formacionAcademica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormacionAcademica> findFormacionAcademicaEntities() {
        return findFormacionAcademicaEntities(true, -1, -1);
    }

    public List<FormacionAcademica> findFormacionAcademicaEntities(int maxResults, int firstResult) {
        return findFormacionAcademicaEntities(false, maxResults, firstResult);
    }

    private List<FormacionAcademica> findFormacionAcademicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormacionAcademica.class));
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

    public FormacionAcademica findFormacionAcademica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormacionAcademica.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionAcademicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormacionAcademica> rt = cq.from(FormacionAcademica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    }
