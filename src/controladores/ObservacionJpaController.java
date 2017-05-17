/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Observacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.becas.PostulacionBeca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author huguito
 */
public class ObservacionJpaController implements Serializable {

    public ObservacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Observacion observacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PostulacionBeca postulacionBeca = observacion.getPostulacionBeca();
            if (postulacionBeca != null) {
                postulacionBeca = em.getReference(postulacionBeca.getClass(), postulacionBeca.getId());
                observacion.setPostulacionBeca(postulacionBeca);
            }
            em.persist(observacion);
            if (postulacionBeca != null) {
                postulacionBeca.getObservaciones().add(observacion);
                postulacionBeca = em.merge(postulacionBeca);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Observacion observacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Observacion persistentObservacion = em.find(Observacion.class, observacion.getId());
            PostulacionBeca postulacionBecaOld = persistentObservacion.getPostulacionBeca();
            PostulacionBeca postulacionBecaNew = observacion.getPostulacionBeca();
            if (postulacionBecaNew != null) {
                postulacionBecaNew = em.getReference(postulacionBecaNew.getClass(), postulacionBecaNew.getId());
                observacion.setPostulacionBeca(postulacionBecaNew);
            }
            observacion = em.merge(observacion);
            if (postulacionBecaOld != null && !postulacionBecaOld.equals(postulacionBecaNew)) {
                postulacionBecaOld.getObservaciones().remove(observacion);
                postulacionBecaOld = em.merge(postulacionBecaOld);
            }
            if (postulacionBecaNew != null && !postulacionBecaNew.equals(postulacionBecaOld)) {
                postulacionBecaNew.getObservaciones().add(observacion);
                postulacionBecaNew = em.merge(postulacionBecaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = observacion.getId();
                if (findObservacion(id) == null) {
                    throw new NonexistentEntityException("The observacion with id " + id + " no longer exists.");
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
            Observacion observacion;
            try {
                observacion = em.getReference(Observacion.class, id);
                observacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The observacion with id " + id + " no longer exists.", enfe);
            }
            PostulacionBeca postulacionBeca = observacion.getPostulacionBeca();
            if (postulacionBeca != null) {
                postulacionBeca.getObservaciones().remove(observacion);
                postulacionBeca = em.merge(postulacionBeca);
            }
            em.remove(observacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Observacion> findObservacionEntities() {
        return findObservacionEntities(true, -1, -1);
    }

    public List<Observacion> findObservacionEntities(int maxResults, int firstResult) {
        return findObservacionEntities(false, maxResults, firstResult);
    }

    private List<Observacion> findObservacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Observacion.class));
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

    public Observacion findObservacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Observacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getObservacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Observacion> rt = cq.from(Observacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
