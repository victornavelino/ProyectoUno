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
import entidades.becas.PostulacionBeca;
import java.util.ArrayList;
import java.util.List;
import entidades.proyecto.Participacion;
import entidades.Resolucion;
import entidades.persona.investigador.Investigador;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class InvestigadorJpaController implements Serializable {

    public InvestigadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Investigador investigador) {
        if (investigador.getBecas() == null) {
            investigador.setBecas(new ArrayList<PostulacionBeca>());
        }
        if (investigador.getParticipacionesProyecto() == null) {
            investigador.setParticipacionesProyecto(new ArrayList<Participacion>());
        }
        if (investigador.getResoluciones() == null) {
            investigador.setResoluciones(new ArrayList<Resolucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PostulacionBeca> attachedBecas = new ArrayList<PostulacionBeca>();
            for (PostulacionBeca becasPostulacionBecaToAttach : investigador.getBecas()) {
                becasPostulacionBecaToAttach = em.getReference(becasPostulacionBecaToAttach.getClass(), becasPostulacionBecaToAttach.getId());
                attachedBecas.add(becasPostulacionBecaToAttach);
            }
            investigador.setBecas(attachedBecas);
            List<Participacion> attachedParticipacionesProyecto = new ArrayList<Participacion>();
            for (Participacion participacionesProyectoParticipacionToAttach : investigador.getParticipacionesProyecto()) {
                participacionesProyectoParticipacionToAttach = em.getReference(participacionesProyectoParticipacionToAttach.getClass(), participacionesProyectoParticipacionToAttach.getId());
                attachedParticipacionesProyecto.add(participacionesProyectoParticipacionToAttach);
            }
            investigador.setParticipacionesProyecto(attachedParticipacionesProyecto);
            List<Resolucion> attachedResoluciones = new ArrayList<Resolucion>();
            for (Resolucion resolucionesResolucionToAttach : investigador.getResoluciones()) {
                resolucionesResolucionToAttach = em.getReference(resolucionesResolucionToAttach.getClass(), resolucionesResolucionToAttach.getId());
                attachedResoluciones.add(resolucionesResolucionToAttach);
            }
            investigador.setResoluciones(attachedResoluciones);
            em.persist(investigador);
            for (PostulacionBeca becasPostulacionBeca : investigador.getBecas()) {
                Investigador oldPostulanteOfBecasPostulacionBeca = becasPostulacionBeca.getPostulante();
                becasPostulacionBeca.setPostulante(investigador);
                becasPostulacionBeca = em.merge(becasPostulacionBeca);
                if (oldPostulanteOfBecasPostulacionBeca != null) {
                    oldPostulanteOfBecasPostulacionBeca.getBecas().remove(becasPostulacionBeca);
                    oldPostulanteOfBecasPostulacionBeca = em.merge(oldPostulanteOfBecasPostulacionBeca);
                }
            }
            for (Participacion participacionesProyectoParticipacion : investigador.getParticipacionesProyecto()) {
                Investigador oldInvestigadorOfParticipacionesProyectoParticipacion = participacionesProyectoParticipacion.getInvestigador();
                participacionesProyectoParticipacion.setInvestigador(investigador);
                participacionesProyectoParticipacion = em.merge(participacionesProyectoParticipacion);
                if (oldInvestigadorOfParticipacionesProyectoParticipacion != null) {
                    oldInvestigadorOfParticipacionesProyectoParticipacion.getParticipacionesProyecto().remove(participacionesProyectoParticipacion);
                    oldInvestigadorOfParticipacionesProyectoParticipacion = em.merge(oldInvestigadorOfParticipacionesProyectoParticipacion);
                }
            }
            for (Resolucion resolucionesResolucion : investigador.getResoluciones()) {
                resolucionesResolucion.getInvestigadores().add(investigador);
                resolucionesResolucion = em.merge(resolucionesResolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Investigador investigador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Investigador persistentInvestigador = em.find(Investigador.class, investigador.getId());
            List<PostulacionBeca> becasOld = persistentInvestigador.getBecas();
            List<PostulacionBeca> becasNew = investigador.getBecas();
            List<Participacion> participacionesProyectoOld = persistentInvestigador.getParticipacionesProyecto();
            List<Participacion> participacionesProyectoNew = investigador.getParticipacionesProyecto();
            List<Resolucion> resolucionesOld = persistentInvestigador.getResoluciones();
            List<Resolucion> resolucionesNew = investigador.getResoluciones();
            List<PostulacionBeca> attachedBecasNew = new ArrayList<PostulacionBeca>();
            for (PostulacionBeca becasNewPostulacionBecaToAttach : becasNew) {
                becasNewPostulacionBecaToAttach = em.getReference(becasNewPostulacionBecaToAttach.getClass(), becasNewPostulacionBecaToAttach.getId());
                attachedBecasNew.add(becasNewPostulacionBecaToAttach);
            }
            becasNew = attachedBecasNew;
            investigador.setBecas(becasNew);
            List<Participacion> attachedParticipacionesProyectoNew = new ArrayList<Participacion>();
            for (Participacion participacionesProyectoNewParticipacionToAttach : participacionesProyectoNew) {
                participacionesProyectoNewParticipacionToAttach = em.getReference(participacionesProyectoNewParticipacionToAttach.getClass(), participacionesProyectoNewParticipacionToAttach.getId());
                attachedParticipacionesProyectoNew.add(participacionesProyectoNewParticipacionToAttach);
            }
            participacionesProyectoNew = attachedParticipacionesProyectoNew;
            investigador.setParticipacionesProyecto(participacionesProyectoNew);
            List<Resolucion> attachedResolucionesNew = new ArrayList<Resolucion>();
            for (Resolucion resolucionesNewResolucionToAttach : resolucionesNew) {
                resolucionesNewResolucionToAttach = em.getReference(resolucionesNewResolucionToAttach.getClass(), resolucionesNewResolucionToAttach.getId());
                attachedResolucionesNew.add(resolucionesNewResolucionToAttach);
            }
            resolucionesNew = attachedResolucionesNew;
            investigador.setResoluciones(resolucionesNew);
            investigador = em.merge(investigador);
            for (PostulacionBeca becasOldPostulacionBeca : becasOld) {
                if (!becasNew.contains(becasOldPostulacionBeca)) {
                    becasOldPostulacionBeca.setPostulante(null);
                    becasOldPostulacionBeca = em.merge(becasOldPostulacionBeca);
                }
            }
            for (PostulacionBeca becasNewPostulacionBeca : becasNew) {
                if (!becasOld.contains(becasNewPostulacionBeca)) {
                    Investigador oldPostulanteOfBecasNewPostulacionBeca = becasNewPostulacionBeca.getPostulante();
                    becasNewPostulacionBeca.setPostulante(investigador);
                    becasNewPostulacionBeca = em.merge(becasNewPostulacionBeca);
                    if (oldPostulanteOfBecasNewPostulacionBeca != null && !oldPostulanteOfBecasNewPostulacionBeca.equals(investigador)) {
                        oldPostulanteOfBecasNewPostulacionBeca.getBecas().remove(becasNewPostulacionBeca);
                        oldPostulanteOfBecasNewPostulacionBeca = em.merge(oldPostulanteOfBecasNewPostulacionBeca);
                    }
                }
            }
            for (Participacion participacionesProyectoOldParticipacion : participacionesProyectoOld) {
                if (!participacionesProyectoNew.contains(participacionesProyectoOldParticipacion)) {
                    participacionesProyectoOldParticipacion.setInvestigador(null);
                    participacionesProyectoOldParticipacion = em.merge(participacionesProyectoOldParticipacion);
                }
            }
            for (Participacion participacionesProyectoNewParticipacion : participacionesProyectoNew) {
                if (!participacionesProyectoOld.contains(participacionesProyectoNewParticipacion)) {
                    Investigador oldInvestigadorOfParticipacionesProyectoNewParticipacion = participacionesProyectoNewParticipacion.getInvestigador();
                    participacionesProyectoNewParticipacion.setInvestigador(investigador);
                    participacionesProyectoNewParticipacion = em.merge(participacionesProyectoNewParticipacion);
                    if (oldInvestigadorOfParticipacionesProyectoNewParticipacion != null && !oldInvestigadorOfParticipacionesProyectoNewParticipacion.equals(investigador)) {
                        oldInvestigadorOfParticipacionesProyectoNewParticipacion.getParticipacionesProyecto().remove(participacionesProyectoNewParticipacion);
                        oldInvestigadorOfParticipacionesProyectoNewParticipacion = em.merge(oldInvestigadorOfParticipacionesProyectoNewParticipacion);
                    }
                }
            }
            for (Resolucion resolucionesOldResolucion : resolucionesOld) {
                if (!resolucionesNew.contains(resolucionesOldResolucion)) {
                    resolucionesOldResolucion.getInvestigadores().remove(investigador);
                    resolucionesOldResolucion = em.merge(resolucionesOldResolucion);
                }
            }
            for (Resolucion resolucionesNewResolucion : resolucionesNew) {
                if (!resolucionesOld.contains(resolucionesNewResolucion)) {
                    resolucionesNewResolucion.getInvestigadores().add(investigador);
                    resolucionesNewResolucion = em.merge(resolucionesNewResolucion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = investigador.getId();
                if (findInvestigador(id) == null) {
                    throw new NonexistentEntityException("The investigador with id " + id + " no longer exists.");
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
            Investigador investigador;
            try {
                investigador = em.getReference(Investigador.class, id);
                investigador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The investigador with id " + id + " no longer exists.", enfe);
            }
            List<PostulacionBeca> becas = investigador.getBecas();
            for (PostulacionBeca becasPostulacionBeca : becas) {
                becasPostulacionBeca.setPostulante(null);
                becasPostulacionBeca = em.merge(becasPostulacionBeca);
            }
            List<Participacion> participacionesProyecto = investigador.getParticipacionesProyecto();
            for (Participacion participacionesProyectoParticipacion : participacionesProyecto) {
                participacionesProyectoParticipacion.setInvestigador(null);
                participacionesProyectoParticipacion = em.merge(participacionesProyectoParticipacion);
            }
            List<Resolucion> resoluciones = investigador.getResoluciones();
            for (Resolucion resolucionesResolucion : resoluciones) {
                resolucionesResolucion.getInvestigadores().remove(investigador);
                resolucionesResolucion = em.merge(resolucionesResolucion);
            }
            em.remove(investigador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Investigador> findInvestigadorEntities() {
        return findInvestigadorEntities(true, -1, -1);
    }

    public List<Investigador> findInvestigadorEntities(int maxResults, int firstResult) {
        return findInvestigadorEntities(false, maxResults, firstResult);
    }

    private List<Investigador> findInvestigadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Investigador.class));
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

    public Investigador findInvestigador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Investigador.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvestigadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Investigador> rt = cq.from(Investigador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
