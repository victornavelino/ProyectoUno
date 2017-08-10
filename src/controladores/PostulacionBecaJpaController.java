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
import java.util.ArrayList;
import java.util.List;
import entidades.becas.Evaluaciones;
import entidades.becas.PostulacionBeca;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author huguito
 */
public class PostulacionBecaJpaController implements Serializable {

    public PostulacionBecaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PostulacionBeca postulacionBeca) {
        if (postulacionBeca.getAsesores() == null) {
            postulacionBeca.setAsesores(new ArrayList<Investigador>());
        }
        if (postulacionBeca.getEvaluaciones() == null) {
            postulacionBeca.setEvaluaciones(new ArrayList<Evaluaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Investigador postulante = postulacionBeca.getPostulante();
            if (postulante != null) {
                postulante = em.getReference(postulante.getClass(), postulante.getId());
                postulacionBeca.setPostulante(postulante);
            }
            List<Investigador> attachedAsesores = new ArrayList<Investigador>();
            for (Investigador asesoresInvestigadorToAttach : postulacionBeca.getAsesores()) {
                asesoresInvestigadorToAttach = em.getReference(asesoresInvestigadorToAttach.getClass(), asesoresInvestigadorToAttach.getId());
                attachedAsesores.add(asesoresInvestigadorToAttach);
            }
            postulacionBeca.setAsesores(attachedAsesores);
            List<Evaluaciones> attachedEvaluaciones = new ArrayList<Evaluaciones>();
            for (Evaluaciones evaluacionesEvaluacionesToAttach : postulacionBeca.getEvaluaciones()) {
                evaluacionesEvaluacionesToAttach = em.getReference(evaluacionesEvaluacionesToAttach.getClass(), evaluacionesEvaluacionesToAttach.getId());
                attachedEvaluaciones.add(evaluacionesEvaluacionesToAttach);
            }
            postulacionBeca.setEvaluaciones(attachedEvaluaciones);
            em.persist(postulacionBeca);
            if (postulante != null) {
                postulante.getBecas().add(postulacionBeca);
                postulante = em.merge(postulante);
            }
            for (Investigador asesoresInvestigador : postulacionBeca.getAsesores()) {
                asesoresInvestigador.getBecas().add(postulacionBeca);
                asesoresInvestigador = em.merge(asesoresInvestigador);
            }
            for (Evaluaciones evaluacionesEvaluaciones : postulacionBeca.getEvaluaciones()) {
                PostulacionBeca oldPostulacionBecaOfEvaluacionesEvaluaciones = evaluacionesEvaluaciones.getPostulacionBeca();
                evaluacionesEvaluaciones.setPostulacionBeca(postulacionBeca);
                evaluacionesEvaluaciones = em.merge(evaluacionesEvaluaciones);
                if (oldPostulacionBecaOfEvaluacionesEvaluaciones != null) {
                    oldPostulacionBecaOfEvaluacionesEvaluaciones.getEvaluaciones().remove(evaluacionesEvaluaciones);
                    oldPostulacionBecaOfEvaluacionesEvaluaciones = em.merge(oldPostulacionBecaOfEvaluacionesEvaluaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PostulacionBeca postulacionBeca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PostulacionBeca persistentPostulacionBeca = em.find(PostulacionBeca.class, postulacionBeca.getId());
            Investigador postulanteOld = persistentPostulacionBeca.getPostulante();
            Investigador postulanteNew = postulacionBeca.getPostulante();
            List<Investigador> asesoresOld = persistentPostulacionBeca.getAsesores();
            List<Investigador> asesoresNew = postulacionBeca.getAsesores();
            List<Evaluaciones> evaluacionesOld = persistentPostulacionBeca.getEvaluaciones();
            List<Evaluaciones> evaluacionesNew = postulacionBeca.getEvaluaciones();
            if (postulanteNew != null) {
                postulanteNew = em.getReference(postulanteNew.getClass(), postulanteNew.getId());
                postulacionBeca.setPostulante(postulanteNew);
            }
            List<Investigador> attachedAsesoresNew = new ArrayList<Investigador>();
            for (Investigador asesoresNewInvestigadorToAttach : asesoresNew) {
                asesoresNewInvestigadorToAttach = em.getReference(asesoresNewInvestigadorToAttach.getClass(), asesoresNewInvestigadorToAttach.getId());
                attachedAsesoresNew.add(asesoresNewInvestigadorToAttach);
            }
            asesoresNew = attachedAsesoresNew;
            postulacionBeca.setAsesores(asesoresNew);
            List<Evaluaciones> attachedEvaluacionesNew = new ArrayList<Evaluaciones>();
            for (Evaluaciones evaluacionesNewEvaluacionesToAttach : evaluacionesNew) {
                evaluacionesNewEvaluacionesToAttach = em.getReference(evaluacionesNewEvaluacionesToAttach.getClass(), evaluacionesNewEvaluacionesToAttach.getId());
                attachedEvaluacionesNew.add(evaluacionesNewEvaluacionesToAttach);
            }
            evaluacionesNew = attachedEvaluacionesNew;
            postulacionBeca.setEvaluaciones(evaluacionesNew);
            postulacionBeca = em.merge(postulacionBeca);
            if (postulanteOld != null && !postulanteOld.equals(postulanteNew)) {
                postulanteOld.getBecas().remove(postulacionBeca);
                postulanteOld = em.merge(postulanteOld);
            }
            if (postulanteNew != null && !postulanteNew.equals(postulanteOld)) {
                postulanteNew.getBecas().add(postulacionBeca);
                postulanteNew = em.merge(postulanteNew);
            }
            for (Investigador asesoresOldInvestigador : asesoresOld) {
                if (!asesoresNew.contains(asesoresOldInvestigador)) {
                    asesoresOldInvestigador.getBecas().remove(postulacionBeca);
                    asesoresOldInvestigador = em.merge(asesoresOldInvestigador);
                }
            }
            for (Investigador asesoresNewInvestigador : asesoresNew) {
                if (!asesoresOld.contains(asesoresNewInvestigador)) {
                    asesoresNewInvestigador.getBecas().add(postulacionBeca);
                    asesoresNewInvestigador = em.merge(asesoresNewInvestigador);
                }
            }
            for (Evaluaciones evaluacionesOldEvaluaciones : evaluacionesOld) {
                if (!evaluacionesNew.contains(evaluacionesOldEvaluaciones)) {
                    evaluacionesOldEvaluaciones.setPostulacionBeca(null);
                    evaluacionesOldEvaluaciones = em.merge(evaluacionesOldEvaluaciones);
                }
            }
            for (Evaluaciones evaluacionesNewEvaluaciones : evaluacionesNew) {
                if (!evaluacionesOld.contains(evaluacionesNewEvaluaciones)) {
                    PostulacionBeca oldPostulacionBecaOfEvaluacionesNewEvaluaciones = evaluacionesNewEvaluaciones.getPostulacionBeca();
                    evaluacionesNewEvaluaciones.setPostulacionBeca(postulacionBeca);
                    evaluacionesNewEvaluaciones = em.merge(evaluacionesNewEvaluaciones);
                    if (oldPostulacionBecaOfEvaluacionesNewEvaluaciones != null && !oldPostulacionBecaOfEvaluacionesNewEvaluaciones.equals(postulacionBeca)) {
                        oldPostulacionBecaOfEvaluacionesNewEvaluaciones.getEvaluaciones().remove(evaluacionesNewEvaluaciones);
                        oldPostulacionBecaOfEvaluacionesNewEvaluaciones = em.merge(oldPostulacionBecaOfEvaluacionesNewEvaluaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = postulacionBeca.getId();
                if (findPostulacionBeca(id) == null) {
                    throw new NonexistentEntityException("The postulacionBeca with id " + id + " no longer exists.");
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
            PostulacionBeca postulacionBeca;
            try {
                postulacionBeca = em.getReference(PostulacionBeca.class, id);
                postulacionBeca.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The postulacionBeca with id " + id + " no longer exists.", enfe);
            }
            Investigador postulante = postulacionBeca.getPostulante();
            if (postulante != null) {
                postulante.getBecas().remove(postulacionBeca);
                postulante = em.merge(postulante);
            }
            List<Investigador> asesores = postulacionBeca.getAsesores();
            for (Investigador asesoresInvestigador : asesores) {
                asesoresInvestigador.getBecas().remove(postulacionBeca);
                asesoresInvestigador = em.merge(asesoresInvestigador);
            }
            List<Evaluaciones> evaluaciones = postulacionBeca.getEvaluaciones();
            for (Evaluaciones evaluacionesEvaluaciones : evaluaciones) {
                evaluacionesEvaluaciones.setPostulacionBeca(null);
                evaluacionesEvaluaciones = em.merge(evaluacionesEvaluaciones);
            }
            em.remove(postulacionBeca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PostulacionBeca> findPostulacionBecaEntities() {
        return findPostulacionBecaEntities(true, -1, -1);
    }

    public List<PostulacionBeca> findPostulacionBecaEntities(int maxResults, int firstResult) {
        return findPostulacionBecaEntities(false, maxResults, firstResult);
    }

    private List<PostulacionBeca> findPostulacionBecaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PostulacionBeca.class));
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

    public PostulacionBeca findPostulacionBeca(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PostulacionBeca.class, id);
        } finally {
            em.close();
        }
    }

    public int getPostulacionBecaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PostulacionBeca> rt = cq.from(PostulacionBeca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
