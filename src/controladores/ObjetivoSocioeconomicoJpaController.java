/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.ObjetivoSocioeconomico;
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
 * @author franco
 */
public class ObjetivoSocioeconomicoJpaController implements Serializable {

    public ObjetivoSocioeconomicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjetivoSocioeconomico objetivoSocioeconomico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objetivoSocioeconomico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjetivoSocioeconomico objetivoSocioeconomico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            objetivoSocioeconomico = em.merge(objetivoSocioeconomico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = objetivoSocioeconomico.getId();
                if (findObjetivoSocioeconomico(id) == null) {
                    throw new NonexistentEntityException("The objetivoSocioeconomico with id " + id + " no longer exists.");
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
            ObjetivoSocioeconomico objetivoSocioeconomico;
            try {
                objetivoSocioeconomico = em.getReference(ObjetivoSocioeconomico.class, id);
                objetivoSocioeconomico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivoSocioeconomico with id " + id + " no longer exists.", enfe);
            }
            em.remove(objetivoSocioeconomico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjetivoSocioeconomico> findObjetivoSocioeconomicoEntities() {
        return findObjetivoSocioeconomicoEntities(true, -1, -1);
    }

    public List<ObjetivoSocioeconomico> findObjetivoSocioeconomicoEntities(int maxResults, int firstResult) {
        return findObjetivoSocioeconomicoEntities(false, maxResults, firstResult);
    }

    private List<ObjetivoSocioeconomico> findObjetivoSocioeconomicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ObjetivoSocioeconomico.class));
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

    public ObjetivoSocioeconomico findObjetivoSocioeconomico(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjetivoSocioeconomico.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivoSocioeconomicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ObjetivoSocioeconomico> rt = cq.from(ObjetivoSocioeconomico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
