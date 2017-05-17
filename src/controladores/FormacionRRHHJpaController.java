/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyecto.Proyecto;
import entidades.proyecto.resultado.FormacionRRHH;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hugo
 */
public class FormacionRRHHJpaController implements Serializable {

    public FormacionRRHHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormacionRRHH formacionRRHH) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyecto = formacionRRHH.getProyecto();
            if (proyecto != null) {
                proyecto = em.getReference(proyecto.getClass(), proyecto.getId());
                formacionRRHH.setProyecto(proyecto);
            }
            em.persist(formacionRRHH);
            if (proyecto != null) {
                proyecto.getFormacionRRHHs().add(formacionRRHH);
                proyecto = em.merge(proyecto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormacionRRHH formacionRRHH) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormacionRRHH persistentFormacionRRHH = em.find(FormacionRRHH.class, formacionRRHH.getId());
            Proyecto proyectoOld = persistentFormacionRRHH.getProyecto();
            Proyecto proyectoNew = formacionRRHH.getProyecto();
            if (proyectoNew != null) {
                proyectoNew = em.getReference(proyectoNew.getClass(), proyectoNew.getId());
                formacionRRHH.setProyecto(proyectoNew);
            }
            formacionRRHH = em.merge(formacionRRHH);
            if (proyectoOld != null && !proyectoOld.equals(proyectoNew)) {
                proyectoOld.getFormacionRRHHs().remove(formacionRRHH);
                proyectoOld = em.merge(proyectoOld);
            }
            if (proyectoNew != null && !proyectoNew.equals(proyectoOld)) {
                proyectoNew.getFormacionRRHHs().add(formacionRRHH);
                proyectoNew = em.merge(proyectoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formacionRRHH.getId();
                if (findFormacionRRHH(id) == null) {
                    throw new NonexistentEntityException("The formacionRRHH with id " + id + " no longer exists.");
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
            FormacionRRHH formacionRRHH;
            try {
                formacionRRHH = em.getReference(FormacionRRHH.class, id);
                formacionRRHH.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacionRRHH with id " + id + " no longer exists.", enfe);
            }
            Proyecto proyecto = formacionRRHH.getProyecto();
            if (proyecto != null) {
                proyecto.getFormacionRRHHs().remove(formacionRRHH);
                proyecto = em.merge(proyecto);
            }
            em.remove(formacionRRHH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormacionRRHH> findFormacionRRHHEntities() {
        return findFormacionRRHHEntities(true, -1, -1);
    }

    public List<FormacionRRHH> findFormacionRRHHEntities(int maxResults, int firstResult) {
        return findFormacionRRHHEntities(false, maxResults, firstResult);
    }

    private List<FormacionRRHH> findFormacionRRHHEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormacionRRHH.class));
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

    public FormacionRRHH findFormacionRRHH(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormacionRRHH.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionRRHHCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormacionRRHH> rt = cq.from(FormacionRRHH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
