/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionConsejoSuperior;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author walter
 */
public class ResolucionConsejoSuperiorJpaController implements Serializable {

    public ResolucionConsejoSuperiorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResolucionConsejoSuperior resolucionConsejoSuperior) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resolucionConsejoSuperior);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResolucionConsejoSuperior resolucionConsejoSuperior) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resolucionConsejoSuperior = em.merge(resolucionConsejoSuperior);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resolucionConsejoSuperior.getId();
                if (findResolucionConsejoSuperior(id) == null) {
                    throw new NonexistentEntityException("The resolucionConsejoSuperior with id " + id + " no longer exists.");
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
            ResolucionConsejoSuperior resolucionConsejoSuperior;
            try {
                resolucionConsejoSuperior = em.getReference(ResolucionConsejoSuperior.class, id);
                resolucionConsejoSuperior.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucionConsejoSuperior with id " + id + " no longer exists.", enfe);
            }
            em.remove(resolucionConsejoSuperior);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResolucionConsejoSuperior> findResolucionConsejoSuperiorEntities() {
        return findResolucionConsejoSuperiorEntities(true, -1, -1);
    }

    public List<ResolucionConsejoSuperior> findResolucionConsejoSuperiorEntities(int maxResults, int firstResult) {
        return findResolucionConsejoSuperiorEntities(false, maxResults, firstResult);
    }

    private List<ResolucionConsejoSuperior> findResolucionConsejoSuperiorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResolucionConsejoSuperior.class));
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

    public ResolucionConsejoSuperior findResolucionConsejoSuperior(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResolucionConsejoSuperior.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionConsejoSuperiorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResolucionConsejoSuperior> rt = cq.from(ResolucionConsejoSuperior.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
