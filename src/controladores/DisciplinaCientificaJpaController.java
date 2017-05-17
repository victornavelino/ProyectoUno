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
import entidades.proyecto.AreaTematica;
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.SubDisciplinaCientifica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class DisciplinaCientificaJpaController implements Serializable {

    public DisciplinaCientificaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DisciplinaCientifica disciplinaCientifica) {
        if (disciplinaCientifica.getSubDisciplinasCientificas() == null) {
            disciplinaCientifica.setSubDisciplinasCientificas(new ArrayList<SubDisciplinaCientifica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaTematica areaTematica = disciplinaCientifica.getAreaTematica();
            if (areaTematica != null) {
                areaTematica = em.getReference(areaTematica.getClass(), areaTematica.getId());
                disciplinaCientifica.setAreaTematica(areaTematica);
            }
            List<SubDisciplinaCientifica> attachedSubDisciplinasCientificas = new ArrayList<SubDisciplinaCientifica>();
            for (SubDisciplinaCientifica subDisciplinasCientificasSubDisciplinaCientificaToAttach : disciplinaCientifica.getSubDisciplinasCientificas()) {
                subDisciplinasCientificasSubDisciplinaCientificaToAttach = em.getReference(subDisciplinasCientificasSubDisciplinaCientificaToAttach.getClass(), subDisciplinasCientificasSubDisciplinaCientificaToAttach.getId());
                attachedSubDisciplinasCientificas.add(subDisciplinasCientificasSubDisciplinaCientificaToAttach);
            }
            disciplinaCientifica.setSubDisciplinasCientificas(attachedSubDisciplinasCientificas);
            em.persist(disciplinaCientifica);
            if (areaTematica != null) {
                areaTematica.getDisciplinasCientificas().add(disciplinaCientifica);
                areaTematica = em.merge(areaTematica);
            }
            for (SubDisciplinaCientifica subDisciplinasCientificasSubDisciplinaCientifica : disciplinaCientifica.getSubDisciplinasCientificas()) {
                DisciplinaCientifica oldDisciplinaCientificaOfSubDisciplinasCientificasSubDisciplinaCientifica = subDisciplinasCientificasSubDisciplinaCientifica.getDisciplinaCientifica();
                subDisciplinasCientificasSubDisciplinaCientifica.setDisciplinaCientifica(disciplinaCientifica);
                subDisciplinasCientificasSubDisciplinaCientifica = em.merge(subDisciplinasCientificasSubDisciplinaCientifica);
                if (oldDisciplinaCientificaOfSubDisciplinasCientificasSubDisciplinaCientifica != null) {
                    oldDisciplinaCientificaOfSubDisciplinasCientificasSubDisciplinaCientifica.getSubDisciplinasCientificas().remove(subDisciplinasCientificasSubDisciplinaCientifica);
                    oldDisciplinaCientificaOfSubDisciplinasCientificasSubDisciplinaCientifica = em.merge(oldDisciplinaCientificaOfSubDisciplinasCientificasSubDisciplinaCientifica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DisciplinaCientifica disciplinaCientifica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DisciplinaCientifica persistentDisciplinaCientifica = em.find(DisciplinaCientifica.class, disciplinaCientifica.getId());
            AreaTematica areaTematicaOld = persistentDisciplinaCientifica.getAreaTematica();
            AreaTematica areaTematicaNew = disciplinaCientifica.getAreaTematica();
            List<SubDisciplinaCientifica> subDisciplinasCientificasOld = persistentDisciplinaCientifica.getSubDisciplinasCientificas();
            List<SubDisciplinaCientifica> subDisciplinasCientificasNew = disciplinaCientifica.getSubDisciplinasCientificas();
            if (areaTematicaNew != null) {
                areaTematicaNew = em.getReference(areaTematicaNew.getClass(), areaTematicaNew.getId());
                disciplinaCientifica.setAreaTematica(areaTematicaNew);
            }
            List<SubDisciplinaCientifica> attachedSubDisciplinasCientificasNew = new ArrayList<SubDisciplinaCientifica>();
            for (SubDisciplinaCientifica subDisciplinasCientificasNewSubDisciplinaCientificaToAttach : subDisciplinasCientificasNew) {
                subDisciplinasCientificasNewSubDisciplinaCientificaToAttach = em.getReference(subDisciplinasCientificasNewSubDisciplinaCientificaToAttach.getClass(), subDisciplinasCientificasNewSubDisciplinaCientificaToAttach.getId());
                attachedSubDisciplinasCientificasNew.add(subDisciplinasCientificasNewSubDisciplinaCientificaToAttach);
            }
            subDisciplinasCientificasNew = attachedSubDisciplinasCientificasNew;
            disciplinaCientifica.setSubDisciplinasCientificas(subDisciplinasCientificasNew);
            disciplinaCientifica = em.merge(disciplinaCientifica);
            if (areaTematicaOld != null && !areaTematicaOld.equals(areaTematicaNew)) {
                areaTematicaOld.getDisciplinasCientificas().remove(disciplinaCientifica);
                areaTematicaOld = em.merge(areaTematicaOld);
            }
            if (areaTematicaNew != null && !areaTematicaNew.equals(areaTematicaOld)) {
                areaTematicaNew.getDisciplinasCientificas().add(disciplinaCientifica);
                areaTematicaNew = em.merge(areaTematicaNew);
            }
            for (SubDisciplinaCientifica subDisciplinasCientificasOldSubDisciplinaCientifica : subDisciplinasCientificasOld) {
                if (!subDisciplinasCientificasNew.contains(subDisciplinasCientificasOldSubDisciplinaCientifica)) {
                    subDisciplinasCientificasOldSubDisciplinaCientifica.setDisciplinaCientifica(null);
                    subDisciplinasCientificasOldSubDisciplinaCientifica = em.merge(subDisciplinasCientificasOldSubDisciplinaCientifica);
                }
            }
            for (SubDisciplinaCientifica subDisciplinasCientificasNewSubDisciplinaCientifica : subDisciplinasCientificasNew) {
                if (!subDisciplinasCientificasOld.contains(subDisciplinasCientificasNewSubDisciplinaCientifica)) {
                    DisciplinaCientifica oldDisciplinaCientificaOfSubDisciplinasCientificasNewSubDisciplinaCientifica = subDisciplinasCientificasNewSubDisciplinaCientifica.getDisciplinaCientifica();
                    subDisciplinasCientificasNewSubDisciplinaCientifica.setDisciplinaCientifica(disciplinaCientifica);
                    subDisciplinasCientificasNewSubDisciplinaCientifica = em.merge(subDisciplinasCientificasNewSubDisciplinaCientifica);
                    if (oldDisciplinaCientificaOfSubDisciplinasCientificasNewSubDisciplinaCientifica != null && !oldDisciplinaCientificaOfSubDisciplinasCientificasNewSubDisciplinaCientifica.equals(disciplinaCientifica)) {
                        oldDisciplinaCientificaOfSubDisciplinasCientificasNewSubDisciplinaCientifica.getSubDisciplinasCientificas().remove(subDisciplinasCientificasNewSubDisciplinaCientifica);
                        oldDisciplinaCientificaOfSubDisciplinasCientificasNewSubDisciplinaCientifica = em.merge(oldDisciplinaCientificaOfSubDisciplinasCientificasNewSubDisciplinaCientifica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = disciplinaCientifica.getId();
                if (findDisciplinaCientifica(id) == null) {
                    throw new NonexistentEntityException("The disciplinaCientifica with id " + id + " no longer exists.");
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
            DisciplinaCientifica disciplinaCientifica;
            try {
                disciplinaCientifica = em.getReference(DisciplinaCientifica.class, id);
                disciplinaCientifica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplinaCientifica with id " + id + " no longer exists.", enfe);
            }
            AreaTematica areaTematica = disciplinaCientifica.getAreaTematica();
            if (areaTematica != null) {
                areaTematica.getDisciplinasCientificas().remove(disciplinaCientifica);
                areaTematica = em.merge(areaTematica);
            }
            List<SubDisciplinaCientifica> subDisciplinasCientificas = disciplinaCientifica.getSubDisciplinasCientificas();
            for (SubDisciplinaCientifica subDisciplinasCientificasSubDisciplinaCientifica : subDisciplinasCientificas) {
                subDisciplinasCientificasSubDisciplinaCientifica.setDisciplinaCientifica(null);
                subDisciplinasCientificasSubDisciplinaCientifica = em.merge(subDisciplinasCientificasSubDisciplinaCientifica);
            }
            em.remove(disciplinaCientifica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DisciplinaCientifica> findDisciplinaCientificaEntities() {
        return findDisciplinaCientificaEntities(true, -1, -1);
    }

    public List<DisciplinaCientifica> findDisciplinaCientificaEntities(int maxResults, int firstResult) {
        return findDisciplinaCientificaEntities(false, maxResults, firstResult);
    }

    private List<DisciplinaCientifica> findDisciplinaCientificaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DisciplinaCientifica.class));
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

    public DisciplinaCientifica findDisciplinaCientifica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DisciplinaCientifica.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinaCientificaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DisciplinaCientifica> rt = cq.from(DisciplinaCientifica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
