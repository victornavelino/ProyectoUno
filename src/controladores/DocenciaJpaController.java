/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Docencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.persona.investigador.Investigador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author huguito
 */
public class DocenciaJpaController implements Serializable {

    public DocenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docencia docencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Investigador investigador = docencia.getInvestigador();
            if (investigador != null) {
                investigador = em.getReference(investigador.getClass(), investigador.getId());
                docencia.setInvestigador(investigador);
            }
            em.persist(docencia);
            if (investigador != null) {
                investigador.getDocencias().add(docencia);
                investigador = em.merge(investigador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docencia docencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docencia persistentDocencia = em.find(Docencia.class, docencia.getId());
            Investigador investigadorOld = persistentDocencia.getInvestigador();
            Investigador investigadorNew = docencia.getInvestigador();
            if (investigadorNew != null) {
                investigadorNew = em.getReference(investigadorNew.getClass(), investigadorNew.getId());
                docencia.setInvestigador(investigadorNew);
            }
            docencia = em.merge(docencia);
            if (investigadorOld != null && !investigadorOld.equals(investigadorNew)) {
                investigadorOld.getDocencias().remove(docencia);
                investigadorOld = em.merge(investigadorOld);
            }
            if (investigadorNew != null && !investigadorNew.equals(investigadorOld)) {
                investigadorNew.getDocencias().add(docencia);
                investigadorNew = em.merge(investigadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = docencia.getId();
                if (findDocencia(id) == null) {
                    throw new NonexistentEntityException("The docencia with id " + id + " no longer exists.");
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
            Docencia docencia;
            try {
                docencia = em.getReference(Docencia.class, id);
                docencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docencia with id " + id + " no longer exists.", enfe);
            }
            Investigador investigador = docencia.getInvestigador();
            if (investigador != null) {
                investigador.getDocencias().remove(docencia);
                investigador = em.merge(investigador);
            }
            em.remove(docencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docencia> findDocenciaEntities() {
        return findDocenciaEntities(true, -1, -1);
    }

    public List<Docencia> findDocenciaEntities(int maxResults, int firstResult) {
        return findDocenciaEntities(false, maxResults, firstResult);
    }

    private List<Docencia> findDocenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docencia.class));
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

    public Docencia findDocencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docencia> rt = cq.from(Docencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
