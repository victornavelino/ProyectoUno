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
import entidades.proyecto.resultado.Propiedad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hugo
 */
public class PropiedadJpaController implements Serializable {

    public PropiedadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propiedad propiedad) {
        if (propiedad.getProyectos() == null) {
            propiedad.setProyectos(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Proyecto> attachedProyectos = new ArrayList<Proyecto>();
            for (Proyecto proyectosProyectoToAttach : propiedad.getProyectos()) {
                proyectosProyectoToAttach = em.getReference(proyectosProyectoToAttach.getClass(), proyectosProyectoToAttach.getId());
                attachedProyectos.add(proyectosProyectoToAttach);
            }
            propiedad.setProyectos(attachedProyectos);
            em.persist(propiedad);
            for (Proyecto proyectosProyecto : propiedad.getProyectos()) {
                proyectosProyecto.getPropiedades().add(propiedad);
                proyectosProyecto = em.merge(proyectosProyecto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propiedad propiedad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad persistentPropiedad = em.find(Propiedad.class, propiedad.getId());
            List<Proyecto> proyectosOld = persistentPropiedad.getProyectos();
            List<Proyecto> proyectosNew = propiedad.getProyectos();
            List<Proyecto> attachedProyectosNew = new ArrayList<Proyecto>();
            for (Proyecto proyectosNewProyectoToAttach : proyectosNew) {
                proyectosNewProyectoToAttach = em.getReference(proyectosNewProyectoToAttach.getClass(), proyectosNewProyectoToAttach.getId());
                attachedProyectosNew.add(proyectosNewProyectoToAttach);
            }
            proyectosNew = attachedProyectosNew;
            propiedad.setProyectos(proyectosNew);
            propiedad = em.merge(propiedad);
            for (Proyecto proyectosOldProyecto : proyectosOld) {
                if (!proyectosNew.contains(proyectosOldProyecto)) {
                    proyectosOldProyecto.getPropiedades().remove(propiedad);
                    proyectosOldProyecto = em.merge(proyectosOldProyecto);
                }
            }
            for (Proyecto proyectosNewProyecto : proyectosNew) {
                if (!proyectosOld.contains(proyectosNewProyecto)) {
                    proyectosNewProyecto.getPropiedades().add(propiedad);
                    proyectosNewProyecto = em.merge(proyectosNewProyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = propiedad.getId();
                if (findPropiedad(id) == null) {
                    throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.");
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
            Propiedad propiedad;
            try {
                propiedad = em.getReference(Propiedad.class, id);
                propiedad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.", enfe);
            }
            List<Proyecto> proyectos = propiedad.getProyectos();
            for (Proyecto proyectosProyecto : proyectos) {
                proyectosProyecto.getPropiedades().remove(propiedad);
                proyectosProyecto = em.merge(proyectosProyecto);
            }
            em.remove(propiedad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propiedad> findPropiedadEntities() {
        return findPropiedadEntities(true, -1, -1);
    }

    public List<Propiedad> findPropiedadEntities(int maxResults, int firstResult) {
        return findPropiedadEntities(false, maxResults, firstResult);
    }

    private List<Propiedad> findPropiedadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propiedad.class));
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

    public Propiedad findPropiedad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propiedad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPropiedadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propiedad> rt = cq.from(Propiedad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
