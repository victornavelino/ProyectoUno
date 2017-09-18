/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.becas.evaluacion.BecasComiteEvaluador;
import entidades.becas.evaluacion.BecasEvaluacionWeb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author diego
 */
public class BecasEvaluacionWebJpaController implements Serializable {

    public BecasEvaluacionWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BecasEvaluacionWeb becasEvaluacionWeb) {
        /*if (becasEvaluacionWeb.getBecasComitesEvaluadores() == null) {
            becasEvaluacionWeb.setBecasComitesEvaluadores(new ArrayList<BecasComiteEvaluador>());
        }*/
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            /*List<BecasComiteEvaluador> attachedBecasComitesEvaluadores = new ArrayList<BecasComiteEvaluador>();
            for (BecasComiteEvaluador becasComitesEvaluadoresBecasComiteEvaluadorToAttach : becasEvaluacionWeb.getBecasComitesEvaluadores()) {
                becasComitesEvaluadoresBecasComiteEvaluadorToAttach = em.getReference(becasComitesEvaluadoresBecasComiteEvaluadorToAttach.getClass(), becasComitesEvaluadoresBecasComiteEvaluadorToAttach.getId());
                attachedBecasComitesEvaluadores.add(becasComitesEvaluadoresBecasComiteEvaluadorToAttach);
            }
            becasEvaluacionWeb.setBecasComitesEvaluadores(attachedBecasComitesEvaluadores);*/
            em.persist(becasEvaluacionWeb);
            /*for (BecasComiteEvaluador becasComitesEvaluadoresBecasComiteEvaluador : becasEvaluacionWeb.getBecasComitesEvaluadores()) {
                BecasEvaluacionWeb oldBecasEvaluacionWebOfBecasComitesEvaluadoresBecasComiteEvaluador = becasComitesEvaluadoresBecasComiteEvaluador.getBecasEvaluacionWeb();
                becasComitesEvaluadoresBecasComiteEvaluador.setBecasEvaluacionWeb(becasEvaluacionWeb);
                becasComitesEvaluadoresBecasComiteEvaluador = em.merge(becasComitesEvaluadoresBecasComiteEvaluador);
                if (oldBecasEvaluacionWebOfBecasComitesEvaluadoresBecasComiteEvaluador != null) {
                    oldBecasEvaluacionWebOfBecasComitesEvaluadoresBecasComiteEvaluador.getBecasComitesEvaluadores().remove(becasComitesEvaluadoresBecasComiteEvaluador);
                    oldBecasEvaluacionWebOfBecasComitesEvaluadoresBecasComiteEvaluador = em.merge(oldBecasEvaluacionWebOfBecasComitesEvaluadoresBecasComiteEvaluador);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BecasEvaluacionWeb becasEvaluacionWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BecasEvaluacionWeb persistentBecasEvaluacionWeb = em.find(BecasEvaluacionWeb.class, becasEvaluacionWeb.getId());
            List<BecasComiteEvaluador> becasComitesEvaluadoresOld = persistentBecasEvaluacionWeb.getBecasComitesEvaluadores();
            List<BecasComiteEvaluador> becasComitesEvaluadoresNew = becasEvaluacionWeb.getBecasComitesEvaluadores();
            List<BecasComiteEvaluador> attachedBecasComitesEvaluadoresNew = new ArrayList<BecasComiteEvaluador>();
            for (BecasComiteEvaluador becasComitesEvaluadoresNewBecasComiteEvaluadorToAttach : becasComitesEvaluadoresNew) {
                if(becasComitesEvaluadoresNewBecasComiteEvaluadorToAttach.getId() != null){
                    becasComitesEvaluadoresNewBecasComiteEvaluadorToAttach = em.getReference(becasComitesEvaluadoresNewBecasComiteEvaluadorToAttach.getClass(), becasComitesEvaluadoresNewBecasComiteEvaluadorToAttach.getId());
                }
                    attachedBecasComitesEvaluadoresNew.add(becasComitesEvaluadoresNewBecasComiteEvaluadorToAttach);
  
            }
            becasComitesEvaluadoresNew = attachedBecasComitesEvaluadoresNew;
            becasEvaluacionWeb.setBecasComitesEvaluadores(becasComitesEvaluadoresNew);
            becasEvaluacionWeb = em.merge(becasEvaluacionWeb);
            for (BecasComiteEvaluador becasComitesEvaluadoresOldBecasComiteEvaluador : becasComitesEvaluadoresOld) {
                if (!becasComitesEvaluadoresNew.contains(becasComitesEvaluadoresOldBecasComiteEvaluador)) {
                    becasComitesEvaluadoresOldBecasComiteEvaluador.setBecasEvaluacionWeb(null);
                    becasComitesEvaluadoresOldBecasComiteEvaluador = em.merge(becasComitesEvaluadoresOldBecasComiteEvaluador);
                }
            }
            for (BecasComiteEvaluador becasComitesEvaluadoresNewBecasComiteEvaluador : becasComitesEvaluadoresNew) {
                if (!becasComitesEvaluadoresOld.contains(becasComitesEvaluadoresNewBecasComiteEvaluador)) {
                    BecasEvaluacionWeb oldBecasEvaluacionWebOfBecasComitesEvaluadoresNewBecasComiteEvaluador = becasComitesEvaluadoresNewBecasComiteEvaluador.getBecasEvaluacionWeb();
                    becasComitesEvaluadoresNewBecasComiteEvaluador.setBecasEvaluacionWeb(becasEvaluacionWeb);
                    becasComitesEvaluadoresNewBecasComiteEvaluador = em.merge(becasComitesEvaluadoresNewBecasComiteEvaluador);
                    if (oldBecasEvaluacionWebOfBecasComitesEvaluadoresNewBecasComiteEvaluador != null && !oldBecasEvaluacionWebOfBecasComitesEvaluadoresNewBecasComiteEvaluador.equals(becasEvaluacionWeb)) {
                        oldBecasEvaluacionWebOfBecasComitesEvaluadoresNewBecasComiteEvaluador.getBecasComitesEvaluadores().remove(becasComitesEvaluadoresNewBecasComiteEvaluador);
                        oldBecasEvaluacionWebOfBecasComitesEvaluadoresNewBecasComiteEvaluador = em.merge(oldBecasEvaluacionWebOfBecasComitesEvaluadoresNewBecasComiteEvaluador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = becasEvaluacionWeb.getId();
                if (findBecasEvaluacionWeb(id) == null) {
                    throw new NonexistentEntityException("The becasEvaluacionWeb with id " + id + " no longer exists.");
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
            BecasEvaluacionWeb becasEvaluacionWeb;
            try {
                becasEvaluacionWeb = em.getReference(BecasEvaluacionWeb.class, id);
                becasEvaluacionWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The becasEvaluacionWeb with id " + id + " no longer exists.", enfe);
            }
            List<BecasComiteEvaluador> becasComitesEvaluadores = becasEvaluacionWeb.getBecasComitesEvaluadores();
            for (BecasComiteEvaluador becasComitesEvaluadoresBecasComiteEvaluador : becasComitesEvaluadores) {
                becasComitesEvaluadoresBecasComiteEvaluador.setBecasEvaluacionWeb(null);
                becasComitesEvaluadoresBecasComiteEvaluador = em.merge(becasComitesEvaluadoresBecasComiteEvaluador);
            }
            em.remove(becasEvaluacionWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BecasEvaluacionWeb> findBecasEvaluacionWebEntities() {
        return findBecasEvaluacionWebEntities(true, -1, -1);
    }

    public List<BecasEvaluacionWeb> findBecasEvaluacionWebEntities(int maxResults, int firstResult) {
        return findBecasEvaluacionWebEntities(false, maxResults, firstResult);
    }

    private List<BecasEvaluacionWeb> findBecasEvaluacionWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BecasEvaluacionWeb.class));
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

    public BecasEvaluacionWeb findBecasEvaluacionWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BecasEvaluacionWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getBecasEvaluacionWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BecasEvaluacionWeb> rt = cq.from(BecasEvaluacionWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
