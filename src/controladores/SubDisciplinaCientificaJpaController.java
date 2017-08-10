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
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.SubDisciplinaCientifica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class SubDisciplinaCientificaJpaController implements Serializable {

    public SubDisciplinaCientificaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubDisciplinaCientifica subDisciplinaCientifica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DisciplinaCientifica disciplinaCientifica = subDisciplinaCientifica.getDisciplinaCientifica();
            if (disciplinaCientifica != null) {
                disciplinaCientifica = em.getReference(disciplinaCientifica.getClass(), disciplinaCientifica.getId());
                subDisciplinaCientifica.setDisciplinaCientifica(disciplinaCientifica);
            }
            em.persist(subDisciplinaCientifica);
            if (disciplinaCientifica != null) {
                disciplinaCientifica.getSubDisciplinasCientificas().add(subDisciplinaCientifica);
                disciplinaCientifica = em.merge(disciplinaCientifica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubDisciplinaCientifica subDisciplinaCientifica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubDisciplinaCientifica persistentSubDisciplinaCientifica = em.find(SubDisciplinaCientifica.class, subDisciplinaCientifica.getId());
            DisciplinaCientifica disciplinaCientificaOld = persistentSubDisciplinaCientifica.getDisciplinaCientifica();
            DisciplinaCientifica disciplinaCientificaNew = subDisciplinaCientifica.getDisciplinaCientifica();
            if (disciplinaCientificaNew != null) {
                disciplinaCientificaNew = em.getReference(disciplinaCientificaNew.getClass(), disciplinaCientificaNew.getId());
                subDisciplinaCientifica.setDisciplinaCientifica(disciplinaCientificaNew);
            }
            subDisciplinaCientifica = em.merge(subDisciplinaCientifica);
            if (disciplinaCientificaOld != null && !disciplinaCientificaOld.equals(disciplinaCientificaNew)) {
                disciplinaCientificaOld.getSubDisciplinasCientificas().remove(subDisciplinaCientifica);
                disciplinaCientificaOld = em.merge(disciplinaCientificaOld);
            }
            if (disciplinaCientificaNew != null && !disciplinaCientificaNew.equals(disciplinaCientificaOld)) {
                disciplinaCientificaNew.getSubDisciplinasCientificas().add(subDisciplinaCientifica);
                disciplinaCientificaNew = em.merge(disciplinaCientificaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = subDisciplinaCientifica.getId();
                if (findSubDisciplinaCientifica(id) == null) {
                    throw new NonexistentEntityException("The subDisciplinaCientifica with id " + id + " no longer exists.");
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
            SubDisciplinaCientifica subDisciplinaCientifica;
            try {
                subDisciplinaCientifica = em.getReference(SubDisciplinaCientifica.class, id);
                subDisciplinaCientifica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subDisciplinaCientifica with id " + id + " no longer exists.", enfe);
            }
            DisciplinaCientifica disciplinaCientifica = subDisciplinaCientifica.getDisciplinaCientifica();
            if (disciplinaCientifica != null) {
                disciplinaCientifica.getSubDisciplinasCientificas().remove(subDisciplinaCientifica);
                disciplinaCientifica = em.merge(disciplinaCientifica);
            }
            em.remove(subDisciplinaCientifica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SubDisciplinaCientifica> findSubDisciplinaCientificaEntities() {
        return findSubDisciplinaCientificaEntities(true, -1, -1);
    }

    public List<SubDisciplinaCientifica> findSubDisciplinaCientificaEntities(int maxResults, int firstResult) {
        return findSubDisciplinaCientificaEntities(false, maxResults, firstResult);
    }

    private List<SubDisciplinaCientifica> findSubDisciplinaCientificaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubDisciplinaCientifica.class));
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

    public SubDisciplinaCientifica findSubDisciplinaCientifica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubDisciplinaCientifica.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubDisciplinaCientificaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubDisciplinaCientifica> rt = cq.from(SubDisciplinaCientifica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
