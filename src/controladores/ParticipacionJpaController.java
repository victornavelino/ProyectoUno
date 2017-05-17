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
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Participacion;
import entidades.proyecto.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class ParticipacionJpaController implements Serializable {

    public ParticipacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Participacion participacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Investigador investigador = participacion.getInvestigador();
            if (investigador != null) {
                investigador = em.getReference(investigador.getClass(), investigador.getId());
                participacion.setInvestigador(investigador);
            }
            Proyecto proyecto = participacion.getProyecto();
            if (proyecto != null) {
                proyecto = em.getReference(proyecto.getClass(), proyecto.getId());
                participacion.setProyecto(proyecto);
            }
            em.persist(participacion);
            if (investigador != null) {
                investigador.getParticipacionesProyecto().add(participacion);
                investigador = em.merge(investigador);
            }
            if (proyecto != null) {
                proyecto.getParticipaciones().add(participacion);
                proyecto = em.merge(proyecto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Participacion participacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Participacion persistentParticipacion = em.find(Participacion.class, participacion.getId());
            Investigador investigadorOld = persistentParticipacion.getInvestigador();
            Investigador investigadorNew = participacion.getInvestigador();
            Proyecto proyectoOld = persistentParticipacion.getProyecto();
            Proyecto proyectoNew = participacion.getProyecto();
            if (investigadorNew != null) {
                investigadorNew = em.getReference(investigadorNew.getClass(), investigadorNew.getId());
                participacion.setInvestigador(investigadorNew);
            }
            if (proyectoNew != null) {
                proyectoNew = em.getReference(proyectoNew.getClass(), proyectoNew.getId());
                participacion.setProyecto(proyectoNew);
            }
            participacion = em.merge(participacion);
            if (investigadorOld != null && !investigadorOld.equals(investigadorNew)) {
                investigadorOld.getParticipacionesProyecto().remove(participacion);
                investigadorOld = em.merge(investigadorOld);
            }
            if (investigadorNew != null && !investigadorNew.equals(investigadorOld)) {
                investigadorNew.getParticipacionesProyecto().add(participacion);
                investigadorNew = em.merge(investigadorNew);
            }
            if (proyectoOld != null && !proyectoOld.equals(proyectoNew)) {
                proyectoOld.getParticipaciones().remove(participacion);
                proyectoOld = em.merge(proyectoOld);
            }
            if (proyectoNew != null && !proyectoNew.equals(proyectoOld)) {
                proyectoNew.getParticipaciones().add(participacion);
                proyectoNew = em.merge(proyectoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = participacion.getId();
                if (findParticipacion(id) == null) {
                    throw new NonexistentEntityException("The participacion with id " + id + " no longer exists.");
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
            Participacion participacion;
            try {
                participacion = em.getReference(Participacion.class, id);
                participacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participacion with id " + id + " no longer exists.", enfe);
            }
            Investigador investigador = participacion.getInvestigador();
            if (investigador != null) {
                investigador.getParticipacionesProyecto().remove(participacion);
                investigador = em.merge(investigador);
            }
            Proyecto proyecto = participacion.getProyecto();
            if (proyecto != null) {
                proyecto.getParticipaciones().remove(participacion);
                proyecto = em.merge(proyecto);
            }
            em.remove(participacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Participacion> findParticipacionEntities() {
        return findParticipacionEntities(true, -1, -1);
    }

    public List<Participacion> findParticipacionEntities(int maxResults, int firstResult) {
        return findParticipacionEntities(false, maxResults, firstResult);
    }

    private List<Participacion> findParticipacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Participacion.class));
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

    public Participacion findParticipacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Participacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Participacion> rt = cq.from(Participacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
