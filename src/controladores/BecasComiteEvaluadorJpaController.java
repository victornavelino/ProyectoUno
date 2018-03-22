/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.becas.evaluacion.BecasComiteEvaluador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.becas.evaluacion.BecasEvaluacionWeb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego
 */
public class BecasComiteEvaluadorJpaController implements Serializable {

    public BecasComiteEvaluadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BecasComiteEvaluador becasComiteEvaluador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BecasEvaluacionWeb becasEvaluacionWeb = becasComiteEvaluador.getBecasEvaluacionWeb();
            if (becasEvaluacionWeb != null && becasEvaluacionWeb.getId() != null) {
                becasEvaluacionWeb = em.getReference(becasEvaluacionWeb.getClass(), becasEvaluacionWeb.getId());
                becasComiteEvaluador.setBecasEvaluacionWeb(becasEvaluacionWeb);
            }
            em.persist(becasComiteEvaluador);
            if (becasEvaluacionWeb != null && becasEvaluacionWeb.getId() != null) {
                becasEvaluacionWeb.getBecasComitesEvaluadores().add(becasComiteEvaluador);
                becasEvaluacionWeb = em.merge(becasEvaluacionWeb);
            }
            System.out.println("antes del comit");
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BecasComiteEvaluador becasComiteEvaluador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BecasComiteEvaluador persistentBecasComiteEvaluador = em.find(BecasComiteEvaluador.class, becasComiteEvaluador.getId());
            BecasEvaluacionWeb becasEvaluacionWebOld = persistentBecasComiteEvaluador.getBecasEvaluacionWeb();
            BecasEvaluacionWeb becasEvaluacionWebNew = becasComiteEvaluador.getBecasEvaluacionWeb();
            if (becasEvaluacionWebNew != null) {
                becasEvaluacionWebNew = em.getReference(becasEvaluacionWebNew.getClass(), becasEvaluacionWebNew.getId());
                becasComiteEvaluador.setBecasEvaluacionWeb(becasEvaluacionWebNew);
            }
            becasComiteEvaluador = em.merge(becasComiteEvaluador);
            if (becasEvaluacionWebOld != null && !becasEvaluacionWebOld.equals(becasEvaluacionWebNew)) {
                becasEvaluacionWebOld.getBecasComitesEvaluadores().remove(becasComiteEvaluador);
                becasEvaluacionWebOld = em.merge(becasEvaluacionWebOld);
            }
            if (becasEvaluacionWebNew != null && !becasEvaluacionWebNew.equals(becasEvaluacionWebOld)) {
                becasEvaluacionWebNew.getBecasComitesEvaluadores().add(becasComiteEvaluador);
                becasEvaluacionWebNew = em.merge(becasEvaluacionWebNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = becasComiteEvaluador.getId();
                if (findBecasComiteEvaluador(id) == null) {
                    throw new NonexistentEntityException("The becasComiteEvaluador with id " + id + " no longer exists.");
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
            BecasComiteEvaluador becasComiteEvaluador;
            try {
                becasComiteEvaluador = em.getReference(BecasComiteEvaluador.class, id);
                becasComiteEvaluador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The becasComiteEvaluador with id " + id + " no longer exists.", enfe);
            }
            BecasEvaluacionWeb becasEvaluacionWeb = becasComiteEvaluador.getBecasEvaluacionWeb();
            if (becasEvaluacionWeb != null) {
                becasEvaluacionWeb.getBecasComitesEvaluadores().remove(becasComiteEvaluador);
                becasEvaluacionWeb = em.merge(becasEvaluacionWeb);
            }
            em.remove(becasComiteEvaluador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BecasComiteEvaluador> findBecasComiteEvaluadorEntities() {
        return findBecasComiteEvaluadorEntities(true, -1, -1);
    }

    public List<BecasComiteEvaluador> findBecasComiteEvaluadorEntities(int maxResults, int firstResult) {
        return findBecasComiteEvaluadorEntities(false, maxResults, firstResult);
    }

    private List<BecasComiteEvaluador> findBecasComiteEvaluadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BecasComiteEvaluador.class));
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

    public BecasComiteEvaluador findBecasComiteEvaluador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BecasComiteEvaluador.class, id);
        } finally {
            em.close();
        }
    }

    public int getBecasComiteEvaluadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BecasComiteEvaluador> rt = cq.from(BecasComiteEvaluador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
