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
import entidades.economico.Cobro;
import entidades.economico.PagoEconomico;
import java.util.ArrayList;
import java.util.List;
import entidades.economico.Rendicion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wbivanco
 */
public class PagoEconomicoJpaController implements Serializable {

    public PagoEconomicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagoEconomico pagoEconomico) {
        if (pagoEconomico.getCobros() == null) {
            pagoEconomico.setCobros(new ArrayList<Cobro>());
        }
        if (pagoEconomico.getRendiciones() == null) {
            pagoEconomico.setRendiciones(new ArrayList<Rendicion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cobro> attachedCobros = new ArrayList<Cobro>();
            for (Cobro cobrosCobroToAttach : pagoEconomico.getCobros()) {
                cobrosCobroToAttach = em.getReference(cobrosCobroToAttach.getClass(), cobrosCobroToAttach.getId());
                attachedCobros.add(cobrosCobroToAttach);
            }
            pagoEconomico.setCobros(attachedCobros);
            List<Rendicion> attachedRendiciones = new ArrayList<Rendicion>();
            for (Rendicion rendicionesRendicionToAttach : pagoEconomico.getRendiciones()) {
                rendicionesRendicionToAttach = em.getReference(rendicionesRendicionToAttach.getClass(), rendicionesRendicionToAttach.getId());
                attachedRendiciones.add(rendicionesRendicionToAttach);
            }
            pagoEconomico.setRendiciones(attachedRendiciones);
            em.persist(pagoEconomico);
            for (Cobro cobrosCobro : pagoEconomico.getCobros()) {
                PagoEconomico oldPagoOfCobrosCobro = cobrosCobro.getPago();
                cobrosCobro.setPago(pagoEconomico);
                cobrosCobro = em.merge(cobrosCobro);
                if (oldPagoOfCobrosCobro != null) {
                    oldPagoOfCobrosCobro.getCobros().remove(cobrosCobro);
                    oldPagoOfCobrosCobro = em.merge(oldPagoOfCobrosCobro);
                }
            }
            for (Rendicion rendicionesRendicion : pagoEconomico.getRendiciones()) {
                PagoEconomico oldPagoEconomicoOfRendicionesRendicion = rendicionesRendicion.getPagoEconomico();
                rendicionesRendicion.setPagoEconomico(pagoEconomico);
                rendicionesRendicion = em.merge(rendicionesRendicion);
                if (oldPagoEconomicoOfRendicionesRendicion != null) {
                    oldPagoEconomicoOfRendicionesRendicion.getRendiciones().remove(rendicionesRendicion);
                    oldPagoEconomicoOfRendicionesRendicion = em.merge(oldPagoEconomicoOfRendicionesRendicion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagoEconomico pagoEconomico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagoEconomico persistentPagoEconomico = em.find(PagoEconomico.class, pagoEconomico.getId());
            List<Cobro> cobrosOld = persistentPagoEconomico.getCobros();
            List<Cobro> cobrosNew = pagoEconomico.getCobros();
            List<Rendicion> rendicionesOld = persistentPagoEconomico.getRendiciones();
            List<Rendicion> rendicionesNew = pagoEconomico.getRendiciones();
            List<Cobro> attachedCobrosNew = new ArrayList<Cobro>();
            for (Cobro cobrosNewCobroToAttach : cobrosNew) {
                cobrosNewCobroToAttach = em.getReference(cobrosNewCobroToAttach.getClass(), cobrosNewCobroToAttach.getId());
                attachedCobrosNew.add(cobrosNewCobroToAttach);
            }
            cobrosNew = attachedCobrosNew;
            pagoEconomico.setCobros(cobrosNew);
            List<Rendicion> attachedRendicionesNew = new ArrayList<Rendicion>();
            for (Rendicion rendicionesNewRendicionToAttach : rendicionesNew) {
                rendicionesNewRendicionToAttach = em.getReference(rendicionesNewRendicionToAttach.getClass(), rendicionesNewRendicionToAttach.getId());
                attachedRendicionesNew.add(rendicionesNewRendicionToAttach);
            }
            rendicionesNew = attachedRendicionesNew;
            pagoEconomico.setRendiciones(rendicionesNew);
            pagoEconomico = em.merge(pagoEconomico);
            for (Cobro cobrosOldCobro : cobrosOld) {
                if (!cobrosNew.contains(cobrosOldCobro)) {
                    cobrosOldCobro.setPago(null);
                    cobrosOldCobro = em.merge(cobrosOldCobro);
                }
            }
            for (Cobro cobrosNewCobro : cobrosNew) {
                if (!cobrosOld.contains(cobrosNewCobro)) {
                    PagoEconomico oldPagoOfCobrosNewCobro = cobrosNewCobro.getPago();
                    cobrosNewCobro.setPago(pagoEconomico);
                    cobrosNewCobro = em.merge(cobrosNewCobro);
                    if (oldPagoOfCobrosNewCobro != null && !oldPagoOfCobrosNewCobro.equals(pagoEconomico)) {
                        oldPagoOfCobrosNewCobro.getCobros().remove(cobrosNewCobro);
                        oldPagoOfCobrosNewCobro = em.merge(oldPagoOfCobrosNewCobro);
                    }
                }
            }
            for (Rendicion rendicionesOldRendicion : rendicionesOld) {
                if (!rendicionesNew.contains(rendicionesOldRendicion)) {
                    rendicionesOldRendicion.setPagoEconomico(null);
                    rendicionesOldRendicion = em.merge(rendicionesOldRendicion);
                }
            }
            for (Rendicion rendicionesNewRendicion : rendicionesNew) {
                if (!rendicionesOld.contains(rendicionesNewRendicion)) {
                    PagoEconomico oldPagoEconomicoOfRendicionesNewRendicion = rendicionesNewRendicion.getPagoEconomico();
                    rendicionesNewRendicion.setPagoEconomico(pagoEconomico);
                    rendicionesNewRendicion = em.merge(rendicionesNewRendicion);
                    if (oldPagoEconomicoOfRendicionesNewRendicion != null && !oldPagoEconomicoOfRendicionesNewRendicion.equals(pagoEconomico)) {
                        oldPagoEconomicoOfRendicionesNewRendicion.getRendiciones().remove(rendicionesNewRendicion);
                        oldPagoEconomicoOfRendicionesNewRendicion = em.merge(oldPagoEconomicoOfRendicionesNewRendicion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pagoEconomico.getId();
                if (findPagoEconomico(id) == null) {
                    throw new NonexistentEntityException("The pagoEconomico with id " + id + " no longer exists.");
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
            PagoEconomico pagoEconomico;
            try {
                pagoEconomico = em.getReference(PagoEconomico.class, id);
                pagoEconomico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoEconomico with id " + id + " no longer exists.", enfe);
            }
            List<Cobro> cobros = pagoEconomico.getCobros();
            for (Cobro cobrosCobro : cobros) {
                cobrosCobro.setPago(null);
                cobrosCobro = em.merge(cobrosCobro);
            }
            List<Rendicion> rendiciones = pagoEconomico.getRendiciones();
            for (Rendicion rendicionesRendicion : rendiciones) {
                rendicionesRendicion.setPagoEconomico(null);
                rendicionesRendicion = em.merge(rendicionesRendicion);
            }
            em.remove(pagoEconomico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagoEconomico> findPagoEconomicoEntities() {
        return findPagoEconomicoEntities(true, -1, -1);
    }

    public List<PagoEconomico> findPagoEconomicoEntities(int maxResults, int firstResult) {
        return findPagoEconomicoEntities(false, maxResults, firstResult);
    }

    private List<PagoEconomico> findPagoEconomicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagoEconomico.class));
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

    public PagoEconomico findPagoEconomico(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagoEconomico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoEconomicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagoEconomico> rt = cq.from(PagoEconomico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
