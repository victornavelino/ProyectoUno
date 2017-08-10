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
import entidades.proyecto.ProyectoDatosComplementarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hugo
 */
public class ProyectoDatosComplementariosJpaController implements Serializable {

    public ProyectoDatosComplementariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProyectoDatosComplementarios proyectoDatosComplementarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyecto = proyectoDatosComplementarios.getProyecto();
            if (proyecto != null) {
                proyecto = em.getReference(proyecto.getClass(), proyecto.getId());
                proyectoDatosComplementarios.setProyecto(proyecto);
            }
            em.persist(proyectoDatosComplementarios);
            if (proyecto != null) {
                ProyectoDatosComplementarios oldProyectoDatosComplementariosOfProyecto = proyecto.getProyectoDatosComplementarios();
                if (oldProyectoDatosComplementariosOfProyecto != null) {
                    oldProyectoDatosComplementariosOfProyecto.setProyecto(null);
                    oldProyectoDatosComplementariosOfProyecto = em.merge(oldProyectoDatosComplementariosOfProyecto);
                }
                proyecto.setProyectoDatosComplementarios(proyectoDatosComplementarios);
                proyecto = em.merge(proyecto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProyectoDatosComplementarios proyectoDatosComplementarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoDatosComplementarios persistentProyectoDatosComplementarios = em.find(ProyectoDatosComplementarios.class, proyectoDatosComplementarios.getId());
            Proyecto proyectoOld = persistentProyectoDatosComplementarios.getProyecto();
            Proyecto proyectoNew = proyectoDatosComplementarios.getProyecto();
            if (proyectoNew != null) {
                proyectoNew = em.getReference(proyectoNew.getClass(), proyectoNew.getId());
                proyectoDatosComplementarios.setProyecto(proyectoNew);
            }
            proyectoDatosComplementarios = em.merge(proyectoDatosComplementarios);
            if (proyectoOld != null && !proyectoOld.equals(proyectoNew)) {
                proyectoOld.setProyectoDatosComplementarios(null);
                proyectoOld = em.merge(proyectoOld);
            }
            if (proyectoNew != null && !proyectoNew.equals(proyectoOld)) {
                ProyectoDatosComplementarios oldProyectoDatosComplementariosOfProyecto = proyectoNew.getProyectoDatosComplementarios();
                if (oldProyectoDatosComplementariosOfProyecto != null) {
                    oldProyectoDatosComplementariosOfProyecto.setProyecto(null);
                    oldProyectoDatosComplementariosOfProyecto = em.merge(oldProyectoDatosComplementariosOfProyecto);
                }
                proyectoNew.setProyectoDatosComplementarios(proyectoDatosComplementarios);
                proyectoNew = em.merge(proyectoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proyectoDatosComplementarios.getId();
                if (findProyectoDatosComplementarios(id) == null) {
                    throw new NonexistentEntityException("The proyectoDatosComplementarios with id " + id + " no longer exists.");
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
            ProyectoDatosComplementarios proyectoDatosComplementarios;
            try {
                proyectoDatosComplementarios = em.getReference(ProyectoDatosComplementarios.class, id);
                proyectoDatosComplementarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyectoDatosComplementarios with id " + id + " no longer exists.", enfe);
            }
            Proyecto proyecto = proyectoDatosComplementarios.getProyecto();
            if (proyecto != null) {
                proyecto.setProyectoDatosComplementarios(null);
                proyecto = em.merge(proyecto);
            }
            em.remove(proyectoDatosComplementarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProyectoDatosComplementarios> findProyectoDatosComplementariosEntities() {
        return findProyectoDatosComplementariosEntities(true, -1, -1);
    }

    public List<ProyectoDatosComplementarios> findProyectoDatosComplementariosEntities(int maxResults, int firstResult) {
        return findProyectoDatosComplementariosEntities(false, maxResults, firstResult);
    }

    private List<ProyectoDatosComplementarios> findProyectoDatosComplementariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProyectoDatosComplementarios.class));
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

    public ProyectoDatosComplementarios findProyectoDatosComplementarios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProyectoDatosComplementarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoDatosComplementariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProyectoDatosComplementarios> rt = cq.from(ProyectoDatosComplementarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
