/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.AreaTematica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyecto.DisciplinaCientifica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class AreaTematicaJpaController implements Serializable {

    public AreaTematicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaTematica areaTematica) {
        if (areaTematica.getDisciplinasCientificas() == null) {
            areaTematica.setDisciplinasCientificas(new ArrayList<DisciplinaCientifica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DisciplinaCientifica> attachedDisciplinasCientificas = new ArrayList<DisciplinaCientifica>();
            for (DisciplinaCientifica disciplinasCientificasDisciplinaCientificaToAttach : areaTematica.getDisciplinasCientificas()) {
                disciplinasCientificasDisciplinaCientificaToAttach = em.getReference(disciplinasCientificasDisciplinaCientificaToAttach.getClass(), disciplinasCientificasDisciplinaCientificaToAttach.getId());
                attachedDisciplinasCientificas.add(disciplinasCientificasDisciplinaCientificaToAttach);
            }
            areaTematica.setDisciplinasCientificas(attachedDisciplinasCientificas);
            em.persist(areaTematica);
            for (DisciplinaCientifica disciplinasCientificasDisciplinaCientifica : areaTematica.getDisciplinasCientificas()) {
                AreaTematica oldAreaTematicaOfDisciplinasCientificasDisciplinaCientifica = disciplinasCientificasDisciplinaCientifica.getAreaTematica();
                disciplinasCientificasDisciplinaCientifica.setAreaTematica(areaTematica);
                disciplinasCientificasDisciplinaCientifica = em.merge(disciplinasCientificasDisciplinaCientifica);
                if (oldAreaTematicaOfDisciplinasCientificasDisciplinaCientifica != null) {
                    oldAreaTematicaOfDisciplinasCientificasDisciplinaCientifica.getDisciplinasCientificas().remove(disciplinasCientificasDisciplinaCientifica);
                    oldAreaTematicaOfDisciplinasCientificasDisciplinaCientifica = em.merge(oldAreaTematicaOfDisciplinasCientificasDisciplinaCientifica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaTematica areaTematica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaTematica persistentAreaTematica = em.find(AreaTematica.class, areaTematica.getId());
            List<DisciplinaCientifica> disciplinasCientificasOld = persistentAreaTematica.getDisciplinasCientificas();
            List<DisciplinaCientifica> disciplinasCientificasNew = areaTematica.getDisciplinasCientificas();
            List<DisciplinaCientifica> attachedDisciplinasCientificasNew = new ArrayList<DisciplinaCientifica>();
            for (DisciplinaCientifica disciplinasCientificasNewDisciplinaCientificaToAttach : disciplinasCientificasNew) {
                disciplinasCientificasNewDisciplinaCientificaToAttach = em.getReference(disciplinasCientificasNewDisciplinaCientificaToAttach.getClass(), disciplinasCientificasNewDisciplinaCientificaToAttach.getId());
                attachedDisciplinasCientificasNew.add(disciplinasCientificasNewDisciplinaCientificaToAttach);
            }
            disciplinasCientificasNew = attachedDisciplinasCientificasNew;
            areaTematica.setDisciplinasCientificas(disciplinasCientificasNew);
            areaTematica = em.merge(areaTematica);
            for (DisciplinaCientifica disciplinasCientificasOldDisciplinaCientifica : disciplinasCientificasOld) {
                if (!disciplinasCientificasNew.contains(disciplinasCientificasOldDisciplinaCientifica)) {
                    disciplinasCientificasOldDisciplinaCientifica.setAreaTematica(null);
                    disciplinasCientificasOldDisciplinaCientifica = em.merge(disciplinasCientificasOldDisciplinaCientifica);
                }
            }
            for (DisciplinaCientifica disciplinasCientificasNewDisciplinaCientifica : disciplinasCientificasNew) {
                if (!disciplinasCientificasOld.contains(disciplinasCientificasNewDisciplinaCientifica)) {
                    AreaTematica oldAreaTematicaOfDisciplinasCientificasNewDisciplinaCientifica = disciplinasCientificasNewDisciplinaCientifica.getAreaTematica();
                    disciplinasCientificasNewDisciplinaCientifica.setAreaTematica(areaTematica);
                    disciplinasCientificasNewDisciplinaCientifica = em.merge(disciplinasCientificasNewDisciplinaCientifica);
                    if (oldAreaTematicaOfDisciplinasCientificasNewDisciplinaCientifica != null && !oldAreaTematicaOfDisciplinasCientificasNewDisciplinaCientifica.equals(areaTematica)) {
                        oldAreaTematicaOfDisciplinasCientificasNewDisciplinaCientifica.getDisciplinasCientificas().remove(disciplinasCientificasNewDisciplinaCientifica);
                        oldAreaTematicaOfDisciplinasCientificasNewDisciplinaCientifica = em.merge(oldAreaTematicaOfDisciplinasCientificasNewDisciplinaCientifica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = areaTematica.getId();
                if (findAreaTematica(id) == null) {
                    throw new NonexistentEntityException("The areaTematica with id " + id + " no longer exists.");
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
            AreaTematica areaTematica;
            try {
                areaTematica = em.getReference(AreaTematica.class, id);
                areaTematica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaTematica with id " + id + " no longer exists.", enfe);
            }
            List<DisciplinaCientifica> disciplinasCientificas = areaTematica.getDisciplinasCientificas();
            for (DisciplinaCientifica disciplinasCientificasDisciplinaCientifica : disciplinasCientificas) {
                disciplinasCientificasDisciplinaCientifica.setAreaTematica(null);
                disciplinasCientificasDisciplinaCientifica = em.merge(disciplinasCientificasDisciplinaCientifica);
            }
            em.remove(areaTematica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaTematica> findAreaTematicaEntities() {
        return findAreaTematicaEntities(true, -1, -1);
    }

    public List<AreaTematica> findAreaTematicaEntities(int maxResults, int firstResult) {
        return findAreaTematicaEntities(false, maxResults, firstResult);
    }

    private List<AreaTematica> findAreaTematicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaTematica.class));
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

    public AreaTematica findAreaTematica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaTematica.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaTematicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaTematica> rt = cq.from(AreaTematica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
