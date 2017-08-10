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
import entidades.Resolucion;
import entidades.persona.Evaluador;
import java.util.ArrayList;
import java.util.List;
import entidades.proyecto.EvaluacionIndividual;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class EvaluadorJpaController implements Serializable {

    public EvaluadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evaluador evaluador) {
        if (evaluador.getResoluciones() == null) {
            evaluador.setResoluciones(new ArrayList<Resolucion>());
        }
        if (evaluador.getEvaluaciones() == null) {
            evaluador.setEvaluaciones(new ArrayList<EvaluacionIndividual>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Resolucion> attachedResoluciones = new ArrayList<Resolucion>();
            for (Resolucion resolucionesResolucionToAttach : evaluador.getResoluciones()) {
                resolucionesResolucionToAttach = em.getReference(resolucionesResolucionToAttach.getClass(), resolucionesResolucionToAttach.getId());
                attachedResoluciones.add(resolucionesResolucionToAttach);
            }
            evaluador.setResoluciones(attachedResoluciones);
            List<EvaluacionIndividual> attachedEvaluaciones = new ArrayList<EvaluacionIndividual>();
            for (EvaluacionIndividual evaluacionesEvaluacionIndividualToAttach : evaluador.getEvaluaciones()) {
                evaluacionesEvaluacionIndividualToAttach = em.getReference(evaluacionesEvaluacionIndividualToAttach.getClass(), evaluacionesEvaluacionIndividualToAttach.getId());
                attachedEvaluaciones.add(evaluacionesEvaluacionIndividualToAttach);
            }
            evaluador.setEvaluaciones(attachedEvaluaciones);
            em.persist(evaluador);
            for (Resolucion resolucionesResolucion : evaluador.getResoluciones()) {
                resolucionesResolucion.getEvaluadores().add(evaluador);
                resolucionesResolucion = em.merge(resolucionesResolucion);
            }
            for (EvaluacionIndividual evaluacionesEvaluacionIndividual : evaluador.getEvaluaciones()) {
                Evaluador oldEvaluadorOfEvaluacionesEvaluacionIndividual = evaluacionesEvaluacionIndividual.getEvaluador();
                evaluacionesEvaluacionIndividual.setEvaluador(evaluador);
                evaluacionesEvaluacionIndividual = em.merge(evaluacionesEvaluacionIndividual);
                if (oldEvaluadorOfEvaluacionesEvaluacionIndividual != null) {
                    oldEvaluadorOfEvaluacionesEvaluacionIndividual.getEvaluaciones().remove(evaluacionesEvaluacionIndividual);
                    oldEvaluadorOfEvaluacionesEvaluacionIndividual = em.merge(oldEvaluadorOfEvaluacionesEvaluacionIndividual);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evaluador evaluador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evaluador persistentEvaluador = em.find(Evaluador.class, evaluador.getId());
            List<Resolucion> resolucionesOld = persistentEvaluador.getResoluciones();
            List<Resolucion> resolucionesNew = evaluador.getResoluciones();
            List<EvaluacionIndividual> evaluacionesOld = persistentEvaluador.getEvaluaciones();
            List<EvaluacionIndividual> evaluacionesNew = evaluador.getEvaluaciones();
            List<Resolucion> attachedResolucionesNew = new ArrayList<Resolucion>();
            for (Resolucion resolucionesNewResolucionToAttach : resolucionesNew) {
                resolucionesNewResolucionToAttach = em.getReference(resolucionesNewResolucionToAttach.getClass(), resolucionesNewResolucionToAttach.getId());
                attachedResolucionesNew.add(resolucionesNewResolucionToAttach);
            }
            resolucionesNew = attachedResolucionesNew;
            evaluador.setResoluciones(resolucionesNew);
            List<EvaluacionIndividual> attachedEvaluacionesNew = new ArrayList<EvaluacionIndividual>();
            for (EvaluacionIndividual evaluacionesNewEvaluacionIndividualToAttach : evaluacionesNew) {
                evaluacionesNewEvaluacionIndividualToAttach = em.getReference(evaluacionesNewEvaluacionIndividualToAttach.getClass(), evaluacionesNewEvaluacionIndividualToAttach.getId());
                attachedEvaluacionesNew.add(evaluacionesNewEvaluacionIndividualToAttach);
            }
            evaluacionesNew = attachedEvaluacionesNew;
            evaluador.setEvaluaciones(evaluacionesNew);
            evaluador = em.merge(evaluador);
            for (Resolucion resolucionesOldResolucion : resolucionesOld) {
                if (!resolucionesNew.contains(resolucionesOldResolucion)) {
                    resolucionesOldResolucion.getEvaluadores().remove(evaluador);
                    resolucionesOldResolucion = em.merge(resolucionesOldResolucion);
                }
            }
            for (Resolucion resolucionesNewResolucion : resolucionesNew) {
                if (!resolucionesOld.contains(resolucionesNewResolucion)) {
                    resolucionesNewResolucion.getEvaluadores().add(evaluador);
                    resolucionesNewResolucion = em.merge(resolucionesNewResolucion);
                }
            }
            for (EvaluacionIndividual evaluacionesOldEvaluacionIndividual : evaluacionesOld) {
                if (!evaluacionesNew.contains(evaluacionesOldEvaluacionIndividual)) {
                    evaluacionesOldEvaluacionIndividual.setEvaluador(null);
                    evaluacionesOldEvaluacionIndividual = em.merge(evaluacionesOldEvaluacionIndividual);
                }
            }
            for (EvaluacionIndividual evaluacionesNewEvaluacionIndividual : evaluacionesNew) {
                if (!evaluacionesOld.contains(evaluacionesNewEvaluacionIndividual)) {
                    Evaluador oldEvaluadorOfEvaluacionesNewEvaluacionIndividual = evaluacionesNewEvaluacionIndividual.getEvaluador();
                    evaluacionesNewEvaluacionIndividual.setEvaluador(evaluador);
                    evaluacionesNewEvaluacionIndividual = em.merge(evaluacionesNewEvaluacionIndividual);
                    if (oldEvaluadorOfEvaluacionesNewEvaluacionIndividual != null && !oldEvaluadorOfEvaluacionesNewEvaluacionIndividual.equals(evaluador)) {
                        oldEvaluadorOfEvaluacionesNewEvaluacionIndividual.getEvaluaciones().remove(evaluacionesNewEvaluacionIndividual);
                        oldEvaluadorOfEvaluacionesNewEvaluacionIndividual = em.merge(oldEvaluadorOfEvaluacionesNewEvaluacionIndividual);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = evaluador.getId();
                if (findEvaluador(id) == null) {
                    throw new NonexistentEntityException("The evaluador with id " + id + " no longer exists.");
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
            Evaluador evaluador;
            try {
                evaluador = em.getReference(Evaluador.class, id);
                evaluador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluador with id " + id + " no longer exists.", enfe);
            }
            List<Resolucion> resoluciones = evaluador.getResoluciones();
            for (Resolucion resolucionesResolucion : resoluciones) {
                resolucionesResolucion.getEvaluadores().remove(evaluador);
                resolucionesResolucion = em.merge(resolucionesResolucion);
            }
            List<EvaluacionIndividual> evaluaciones = evaluador.getEvaluaciones();
            for (EvaluacionIndividual evaluacionesEvaluacionIndividual : evaluaciones) {
                evaluacionesEvaluacionIndividual.setEvaluador(null);
                evaluacionesEvaluacionIndividual = em.merge(evaluacionesEvaluacionIndividual);
            }
            em.remove(evaluador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Evaluador> findEvaluadorEntities() {
        return findEvaluadorEntities(true, -1, -1);
    }

    public List<Evaluador> findEvaluadorEntities(int maxResults, int firstResult) {
        return findEvaluadorEntities(false, maxResults, firstResult);
    }

    private List<Evaluador> findEvaluadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evaluador.class));
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

    public Evaluador findEvaluador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evaluador.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evaluador> rt = cq.from(Evaluador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
