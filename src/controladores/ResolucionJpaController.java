/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.Resolucion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.persona.Evaluador;
import java.util.ArrayList;
import java.util.List;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class ResolucionJpaController implements Serializable {

    public ResolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resolucion resolucion) {
        if (resolucion.getEvaluadores() == null) {
            resolucion.setEvaluadores(new ArrayList<Evaluador>());
        }
        if (resolucion.getInvestigadores() == null) {
            resolucion.setInvestigadores(new ArrayList<Investigador>());
        }
        if (resolucion.getProyectos() == null) {
            resolucion.setProyectos(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Evaluador> attachedEvaluadores = new ArrayList<Evaluador>();
            for (Evaluador evaluadoresEvaluadorToAttach : resolucion.getEvaluadores()) {
                evaluadoresEvaluadorToAttach = em.getReference(evaluadoresEvaluadorToAttach.getClass(), evaluadoresEvaluadorToAttach.getId());
                attachedEvaluadores.add(evaluadoresEvaluadorToAttach);
            }
            resolucion.setEvaluadores(attachedEvaluadores);
            List<Investigador> attachedInvestigadores = new ArrayList<Investigador>();
            for (Investigador investigadoresInvestigadorToAttach : resolucion.getInvestigadores()) {
                investigadoresInvestigadorToAttach = em.getReference(investigadoresInvestigadorToAttach.getClass(), investigadoresInvestigadorToAttach.getId());
                attachedInvestigadores.add(investigadoresInvestigadorToAttach);
            }
            resolucion.setInvestigadores(attachedInvestigadores);
            List<Proyecto> attachedProyectos = new ArrayList<Proyecto>();
            for (Proyecto proyectosProyectoToAttach : resolucion.getProyectos()) {
                proyectosProyectoToAttach = em.getReference(proyectosProyectoToAttach.getClass(), proyectosProyectoToAttach.getId());
                attachedProyectos.add(proyectosProyectoToAttach);
            }
            resolucion.setProyectos(attachedProyectos);
            em.persist(resolucion);
            for (Evaluador evaluadoresEvaluador : resolucion.getEvaluadores()) {
                evaluadoresEvaluador.getResoluciones().add(resolucion);
                evaluadoresEvaluador = em.merge(evaluadoresEvaluador);
            }
            for (Investigador investigadoresInvestigador : resolucion.getInvestigadores()) {
                investigadoresInvestigador.getResoluciones().add(resolucion);
                investigadoresInvestigador = em.merge(investigadoresInvestigador);
            }
            for (Proyecto proyectosProyecto : resolucion.getProyectos()) {
                proyectosProyecto.getResoluciones().add(resolucion);
                proyectosProyecto = em.merge(proyectosProyecto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resolucion resolucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resolucion persistentResolucion = em.find(Resolucion.class, resolucion.getId());
            List<Evaluador> evaluadoresOld = persistentResolucion.getEvaluadores();
            List<Evaluador> evaluadoresNew = resolucion.getEvaluadores();
            List<Investigador> investigadoresOld = persistentResolucion.getInvestigadores();
            List<Investigador> investigadoresNew = resolucion.getInvestigadores();
            List<Proyecto> proyectosOld = persistentResolucion.getProyectos();
            List<Proyecto> proyectosNew = resolucion.getProyectos();
            List<Evaluador> attachedEvaluadoresNew = new ArrayList<Evaluador>();
            for (Evaluador evaluadoresNewEvaluadorToAttach : evaluadoresNew) {
                evaluadoresNewEvaluadorToAttach = em.getReference(evaluadoresNewEvaluadorToAttach.getClass(), evaluadoresNewEvaluadorToAttach.getId());
                attachedEvaluadoresNew.add(evaluadoresNewEvaluadorToAttach);
            }
            evaluadoresNew = attachedEvaluadoresNew;
            resolucion.setEvaluadores(evaluadoresNew);
            List<Investigador> attachedInvestigadoresNew = new ArrayList<Investigador>();
            for (Investigador investigadoresNewInvestigadorToAttach : investigadoresNew) {
                investigadoresNewInvestigadorToAttach = em.getReference(investigadoresNewInvestigadorToAttach.getClass(), investigadoresNewInvestigadorToAttach.getId());
                attachedInvestigadoresNew.add(investigadoresNewInvestigadorToAttach);
            }
            investigadoresNew = attachedInvestigadoresNew;
            resolucion.setInvestigadores(investigadoresNew);
            List<Proyecto> attachedProyectosNew = new ArrayList<Proyecto>();
            for (Proyecto proyectosNewProyectoToAttach : proyectosNew) {
                proyectosNewProyectoToAttach = em.getReference(proyectosNewProyectoToAttach.getClass(), proyectosNewProyectoToAttach.getId());
                attachedProyectosNew.add(proyectosNewProyectoToAttach);
            }
            proyectosNew = attachedProyectosNew;
            resolucion.setProyectos(proyectosNew);
            resolucion = em.merge(resolucion);
            for (Evaluador evaluadoresOldEvaluador : evaluadoresOld) {
                if (!evaluadoresNew.contains(evaluadoresOldEvaluador)) {
                    evaluadoresOldEvaluador.getResoluciones().remove(resolucion);
                    evaluadoresOldEvaluador = em.merge(evaluadoresOldEvaluador);
                }
            }
            for (Evaluador evaluadoresNewEvaluador : evaluadoresNew) {
                if (!evaluadoresOld.contains(evaluadoresNewEvaluador)) {
                    evaluadoresNewEvaluador.getResoluciones().add(resolucion);
                    evaluadoresNewEvaluador = em.merge(evaluadoresNewEvaluador);
                }
            }
            for (Investigador investigadoresOldInvestigador : investigadoresOld) {
                if (!investigadoresNew.contains(investigadoresOldInvestigador)) {
                    investigadoresOldInvestigador.getResoluciones().remove(resolucion);
                    investigadoresOldInvestigador = em.merge(investigadoresOldInvestigador);
                }
            }
            for (Investigador investigadoresNewInvestigador : investigadoresNew) {
                if (!investigadoresOld.contains(investigadoresNewInvestigador)) {
                    investigadoresNewInvestigador.getResoluciones().add(resolucion);
                    investigadoresNewInvestigador = em.merge(investigadoresNewInvestigador);
                }
            }
            for (Proyecto proyectosOldProyecto : proyectosOld) {
                if (!proyectosNew.contains(proyectosOldProyecto)) {
                    proyectosOldProyecto.getResoluciones().remove(resolucion);
                    proyectosOldProyecto = em.merge(proyectosOldProyecto);
                }
            }
            for (Proyecto proyectosNewProyecto : proyectosNew) {
                if (!proyectosOld.contains(proyectosNewProyecto)) {
                    proyectosNewProyecto.getResoluciones().add(resolucion);
                    proyectosNewProyecto = em.merge(proyectosNewProyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resolucion.getId();
                if (findResolucion(id) == null) {
                    throw new NonexistentEntityException("The resolucion with id " + id + " no longer exists.");
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
            Resolucion resolucion;
            try {
                resolucion = em.getReference(Resolucion.class, id);
                resolucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucion with id " + id + " no longer exists.", enfe);
            }
            List<Evaluador> evaluadores = resolucion.getEvaluadores();
            for (Evaluador evaluadoresEvaluador : evaluadores) {
                evaluadoresEvaluador.getResoluciones().remove(resolucion);
                evaluadoresEvaluador = em.merge(evaluadoresEvaluador);
            }
            List<Investigador> investigadores = resolucion.getInvestigadores();
            for (Investigador investigadoresInvestigador : investigadores) {
                investigadoresInvestigador.getResoluciones().remove(resolucion);
                investigadoresInvestigador = em.merge(investigadoresInvestigador);
            }
            List<Proyecto> proyectos = resolucion.getProyectos();
            for (Proyecto proyectosProyecto : proyectos) {
                proyectosProyecto.getResoluciones().remove(resolucion);
                proyectosProyecto = em.merge(proyectosProyecto);
            }
            em.remove(resolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resolucion> findResolucionEntities() {
        return findResolucionEntities(true, -1, -1);
    }

    public List<Resolucion> findResolucionEntities(int maxResults, int firstResult) {
        return findResolucionEntities(false, maxResults, firstResult);
    }

    private List<Resolucion> findResolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resolucion.class));
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

    public Resolucion findResolucion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resolucion> rt = cq.from(Resolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
