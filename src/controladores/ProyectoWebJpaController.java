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
import entidades.proyectoWeb.PresupuestoWebDetallado;
import entidades.proyectoWeb.PresupuestoWeb;
import entidades.proyectoWeb.PresupuestoWebDetalladoDos;
import entidades.proyectoWeb.PresupuestoWebDetalladoTres;
import entidades.proyectoWeb.PresupuestoWebDetalladoCuatro;
import entidades.proyectoWeb.ParticipacionWeb;
import entidades.proyectoWeb.ProyectoWeb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franco
 */
public class ProyectoWebJpaController implements Serializable {

    public ProyectoWebJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProyectoWeb proyectoWeb) {
        if (proyectoWeb.getParticipacionesWeb() == null) {
            proyectoWeb.setParticipacionesWeb(new ArrayList<ParticipacionWeb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PresupuestoWebDetallado presupuestoWebAnioUno = proyectoWeb.getPresupuestoWebAnioUno();
            if (presupuestoWebAnioUno != null) {
                presupuestoWebAnioUno = em.getReference(presupuestoWebAnioUno.getClass(), presupuestoWebAnioUno.getId());
                proyectoWeb.setPresupuestoWebAnioUno(presupuestoWebAnioUno);
            }
            PresupuestoWeb presupuestoWeb = proyectoWeb.getPresupuestoWeb();
            if (presupuestoWeb != null) {
                presupuestoWeb = em.getReference(presupuestoWeb.getClass(), presupuestoWeb.getId());
                proyectoWeb.setPresupuestoWeb(presupuestoWeb);
            }
            PresupuestoWebDetalladoDos presupuestoWebAnioDos = proyectoWeb.getPresupuestoWebAnioDos();
            if (presupuestoWebAnioDos != null) {
                presupuestoWebAnioDos = em.getReference(presupuestoWebAnioDos.getClass(), presupuestoWebAnioDos.getId());
                proyectoWeb.setPresupuestoWebAnioDos(presupuestoWebAnioDos);
            }
            PresupuestoWebDetalladoTres presupuestoWebAnioTres = proyectoWeb.getPresupuestoWebAnioTres();
            if (presupuestoWebAnioTres != null) {
                presupuestoWebAnioTres = em.getReference(presupuestoWebAnioTres.getClass(), presupuestoWebAnioTres.getId());
                proyectoWeb.setPresupuestoWebAnioTres(presupuestoWebAnioTres);
            }
            PresupuestoWebDetalladoCuatro presupuestoWebAnioCuatro = proyectoWeb.getPresupuestoWebAnioCuatro();
            if (presupuestoWebAnioCuatro != null) {
                presupuestoWebAnioCuatro = em.getReference(presupuestoWebAnioCuatro.getClass(), presupuestoWebAnioCuatro.getId());
                proyectoWeb.setPresupuestoWebAnioCuatro(presupuestoWebAnioCuatro);
            }
            List<ParticipacionWeb> attachedParticipacionesWeb = new ArrayList<ParticipacionWeb>();
            for (ParticipacionWeb participacionesWebParticipacionWebToAttach : proyectoWeb.getParticipacionesWeb()) {
                participacionesWebParticipacionWebToAttach = em.getReference(participacionesWebParticipacionWebToAttach.getClass(), participacionesWebParticipacionWebToAttach.getId());
                attachedParticipacionesWeb.add(participacionesWebParticipacionWebToAttach);
            }
            proyectoWeb.setParticipacionesWeb(attachedParticipacionesWeb);
            em.persist(proyectoWeb);
            if (presupuestoWebAnioUno != null) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioUno = presupuestoWebAnioUno.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioUno != null) {
                    oldProyectoWebOfPresupuestoWebAnioUno.setPresupuestoWebAnioUno(null);
                    oldProyectoWebOfPresupuestoWebAnioUno = em.merge(oldProyectoWebOfPresupuestoWebAnioUno);
                }
                presupuestoWebAnioUno.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioUno = em.merge(presupuestoWebAnioUno);
            }
            if (presupuestoWeb != null) {
                ProyectoWeb oldProyectoWebOfPresupuestoWeb = presupuestoWeb.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWeb != null) {
                    oldProyectoWebOfPresupuestoWeb.setPresupuestoWeb(null);
                    oldProyectoWebOfPresupuestoWeb = em.merge(oldProyectoWebOfPresupuestoWeb);
                }
                presupuestoWeb.setProyectoWeb(proyectoWeb);
                presupuestoWeb = em.merge(presupuestoWeb);
            }
            if (presupuestoWebAnioDos != null) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioDos = presupuestoWebAnioDos.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioDos != null) {
                    oldProyectoWebOfPresupuestoWebAnioDos.setPresupuestoWebAnioDos(null);
                    oldProyectoWebOfPresupuestoWebAnioDos = em.merge(oldProyectoWebOfPresupuestoWebAnioDos);
                }
                presupuestoWebAnioDos.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioDos = em.merge(presupuestoWebAnioDos);
            }
            if (presupuestoWebAnioTres != null) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioTres = presupuestoWebAnioTres.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioTres != null) {
                    oldProyectoWebOfPresupuestoWebAnioTres.setPresupuestoWebAnioTres(null);
                    oldProyectoWebOfPresupuestoWebAnioTres = em.merge(oldProyectoWebOfPresupuestoWebAnioTres);
                }
                presupuestoWebAnioTres.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioTres = em.merge(presupuestoWebAnioTres);
            }
            if (presupuestoWebAnioCuatro != null) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioCuatro = presupuestoWebAnioCuatro.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioCuatro != null) {
                    oldProyectoWebOfPresupuestoWebAnioCuatro.setPresupuestoWebAnioCuatro(null);
                    oldProyectoWebOfPresupuestoWebAnioCuatro = em.merge(oldProyectoWebOfPresupuestoWebAnioCuatro);
                }
                presupuestoWebAnioCuatro.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioCuatro = em.merge(presupuestoWebAnioCuatro);
            }
            for (ParticipacionWeb participacionesWebParticipacionWeb : proyectoWeb.getParticipacionesWeb()) {
                ProyectoWeb oldProyectoWebOfParticipacionesWebParticipacionWeb = participacionesWebParticipacionWeb.getProyectoWeb();
                participacionesWebParticipacionWeb.setProyectoWeb(proyectoWeb);
                participacionesWebParticipacionWeb = em.merge(participacionesWebParticipacionWeb);
                if (oldProyectoWebOfParticipacionesWebParticipacionWeb != null) {
                    oldProyectoWebOfParticipacionesWebParticipacionWeb.getParticipacionesWeb().remove(participacionesWebParticipacionWeb);
                    oldProyectoWebOfParticipacionesWebParticipacionWeb = em.merge(oldProyectoWebOfParticipacionesWebParticipacionWeb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProyectoWeb proyectoWeb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoWeb persistentProyectoWeb = em.find(ProyectoWeb.class, proyectoWeb.getId());
            PresupuestoWebDetallado presupuestoWebAnioUnoOld = persistentProyectoWeb.getPresupuestoWebAnioUno();
            PresupuestoWebDetallado presupuestoWebAnioUnoNew = proyectoWeb.getPresupuestoWebAnioUno();
            PresupuestoWeb presupuestoWebOld = persistentProyectoWeb.getPresupuestoWeb();
            PresupuestoWeb presupuestoWebNew = proyectoWeb.getPresupuestoWeb();
            PresupuestoWebDetalladoDos presupuestoWebAnioDosOld = persistentProyectoWeb.getPresupuestoWebAnioDos();
            PresupuestoWebDetalladoDos presupuestoWebAnioDosNew = proyectoWeb.getPresupuestoWebAnioDos();
            PresupuestoWebDetalladoTres presupuestoWebAnioTresOld = persistentProyectoWeb.getPresupuestoWebAnioTres();
            PresupuestoWebDetalladoTres presupuestoWebAnioTresNew = proyectoWeb.getPresupuestoWebAnioTres();
            PresupuestoWebDetalladoCuatro presupuestoWebAnioCuatroOld = persistentProyectoWeb.getPresupuestoWebAnioCuatro();
            PresupuestoWebDetalladoCuatro presupuestoWebAnioCuatroNew = proyectoWeb.getPresupuestoWebAnioCuatro();
            List<ParticipacionWeb> participacionesWebOld = persistentProyectoWeb.getParticipacionesWeb();
            List<ParticipacionWeb> participacionesWebNew = proyectoWeb.getParticipacionesWeb();
            if (presupuestoWebAnioUnoNew != null) {
                presupuestoWebAnioUnoNew = em.getReference(presupuestoWebAnioUnoNew.getClass(), presupuestoWebAnioUnoNew.getId());
                proyectoWeb.setPresupuestoWebAnioUno(presupuestoWebAnioUnoNew);
            }
            if (presupuestoWebNew != null) {
                presupuestoWebNew = em.getReference(presupuestoWebNew.getClass(), presupuestoWebNew.getId());
                proyectoWeb.setPresupuestoWeb(presupuestoWebNew);
            }
            if (presupuestoWebAnioDosNew != null) {
                presupuestoWebAnioDosNew = em.getReference(presupuestoWebAnioDosNew.getClass(), presupuestoWebAnioDosNew.getId());
                proyectoWeb.setPresupuestoWebAnioDos(presupuestoWebAnioDosNew);
            }
            if (presupuestoWebAnioTresNew != null) {
                presupuestoWebAnioTresNew = em.getReference(presupuestoWebAnioTresNew.getClass(), presupuestoWebAnioTresNew.getId());
                proyectoWeb.setPresupuestoWebAnioTres(presupuestoWebAnioTresNew);
            }
            if (presupuestoWebAnioCuatroNew != null) {
                presupuestoWebAnioCuatroNew = em.getReference(presupuestoWebAnioCuatroNew.getClass(), presupuestoWebAnioCuatroNew.getId());
                proyectoWeb.setPresupuestoWebAnioCuatro(presupuestoWebAnioCuatroNew);
            }
            List<ParticipacionWeb> attachedParticipacionesWebNew = new ArrayList<ParticipacionWeb>();
            for (ParticipacionWeb participacionesWebNewParticipacionWebToAttach : participacionesWebNew) {
                participacionesWebNewParticipacionWebToAttach = em.getReference(participacionesWebNewParticipacionWebToAttach.getClass(), participacionesWebNewParticipacionWebToAttach.getId());
                attachedParticipacionesWebNew.add(participacionesWebNewParticipacionWebToAttach);
            }
            participacionesWebNew = attachedParticipacionesWebNew;
            proyectoWeb.setParticipacionesWeb(participacionesWebNew);
            proyectoWeb = em.merge(proyectoWeb);
            if (presupuestoWebAnioUnoOld != null && !presupuestoWebAnioUnoOld.equals(presupuestoWebAnioUnoNew)) {
                presupuestoWebAnioUnoOld.setProyectoWeb(null);
                presupuestoWebAnioUnoOld = em.merge(presupuestoWebAnioUnoOld);
            }
            if (presupuestoWebAnioUnoNew != null && !presupuestoWebAnioUnoNew.equals(presupuestoWebAnioUnoOld)) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioUno = presupuestoWebAnioUnoNew.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioUno != null) {
                    oldProyectoWebOfPresupuestoWebAnioUno.setPresupuestoWebAnioUno(null);
                    oldProyectoWebOfPresupuestoWebAnioUno = em.merge(oldProyectoWebOfPresupuestoWebAnioUno);
                }
                presupuestoWebAnioUnoNew.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioUnoNew = em.merge(presupuestoWebAnioUnoNew);
            }
            if (presupuestoWebOld != null && !presupuestoWebOld.equals(presupuestoWebNew)) {
                presupuestoWebOld.setProyectoWeb(null);
                presupuestoWebOld = em.merge(presupuestoWebOld);
            }
            if (presupuestoWebNew != null && !presupuestoWebNew.equals(presupuestoWebOld)) {
                ProyectoWeb oldProyectoWebOfPresupuestoWeb = presupuestoWebNew.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWeb != null) {
                    oldProyectoWebOfPresupuestoWeb.setPresupuestoWeb(null);
                    oldProyectoWebOfPresupuestoWeb = em.merge(oldProyectoWebOfPresupuestoWeb);
                }
                presupuestoWebNew.setProyectoWeb(proyectoWeb);
                presupuestoWebNew = em.merge(presupuestoWebNew);
            }
            if (presupuestoWebAnioDosOld != null && !presupuestoWebAnioDosOld.equals(presupuestoWebAnioDosNew)) {
                presupuestoWebAnioDosOld.setProyectoWeb(null);
                presupuestoWebAnioDosOld = em.merge(presupuestoWebAnioDosOld);
            }
            if (presupuestoWebAnioDosNew != null && !presupuestoWebAnioDosNew.equals(presupuestoWebAnioDosOld)) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioDos = presupuestoWebAnioDosNew.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioDos != null) {
                    oldProyectoWebOfPresupuestoWebAnioDos.setPresupuestoWebAnioDos(null);
                    oldProyectoWebOfPresupuestoWebAnioDos = em.merge(oldProyectoWebOfPresupuestoWebAnioDos);
                }
                presupuestoWebAnioDosNew.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioDosNew = em.merge(presupuestoWebAnioDosNew);
            }
            if (presupuestoWebAnioTresOld != null && !presupuestoWebAnioTresOld.equals(presupuestoWebAnioTresNew)) {
                presupuestoWebAnioTresOld.setProyectoWeb(null);
                presupuestoWebAnioTresOld = em.merge(presupuestoWebAnioTresOld);
            }
            if (presupuestoWebAnioTresNew != null && !presupuestoWebAnioTresNew.equals(presupuestoWebAnioTresOld)) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioTres = presupuestoWebAnioTresNew.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioTres != null) {
                    oldProyectoWebOfPresupuestoWebAnioTres.setPresupuestoWebAnioTres(null);
                    oldProyectoWebOfPresupuestoWebAnioTres = em.merge(oldProyectoWebOfPresupuestoWebAnioTres);
                }
                presupuestoWebAnioTresNew.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioTresNew = em.merge(presupuestoWebAnioTresNew);
            }
            if (presupuestoWebAnioCuatroOld != null && !presupuestoWebAnioCuatroOld.equals(presupuestoWebAnioCuatroNew)) {
                presupuestoWebAnioCuatroOld.setProyectoWeb(null);
                presupuestoWebAnioCuatroOld = em.merge(presupuestoWebAnioCuatroOld);
            }
            if (presupuestoWebAnioCuatroNew != null && !presupuestoWebAnioCuatroNew.equals(presupuestoWebAnioCuatroOld)) {
                ProyectoWeb oldProyectoWebOfPresupuestoWebAnioCuatro = presupuestoWebAnioCuatroNew.getProyectoWeb();
                if (oldProyectoWebOfPresupuestoWebAnioCuatro != null) {
                    oldProyectoWebOfPresupuestoWebAnioCuatro.setPresupuestoWebAnioCuatro(null);
                    oldProyectoWebOfPresupuestoWebAnioCuatro = em.merge(oldProyectoWebOfPresupuestoWebAnioCuatro);
                }
                presupuestoWebAnioCuatroNew.setProyectoWeb(proyectoWeb);
                presupuestoWebAnioCuatroNew = em.merge(presupuestoWebAnioCuatroNew);
            }
            for (ParticipacionWeb participacionesWebOldParticipacionWeb : participacionesWebOld) {
                if (!participacionesWebNew.contains(participacionesWebOldParticipacionWeb)) {
                    participacionesWebOldParticipacionWeb.setProyectoWeb(null);
                    participacionesWebOldParticipacionWeb = em.merge(participacionesWebOldParticipacionWeb);
                }
            }
            for (ParticipacionWeb participacionesWebNewParticipacionWeb : participacionesWebNew) {
                if (!participacionesWebOld.contains(participacionesWebNewParticipacionWeb)) {
                    ProyectoWeb oldProyectoWebOfParticipacionesWebNewParticipacionWeb = participacionesWebNewParticipacionWeb.getProyectoWeb();
                    participacionesWebNewParticipacionWeb.setProyectoWeb(proyectoWeb);
                    participacionesWebNewParticipacionWeb = em.merge(participacionesWebNewParticipacionWeb);
                    if (oldProyectoWebOfParticipacionesWebNewParticipacionWeb != null && !oldProyectoWebOfParticipacionesWebNewParticipacionWeb.equals(proyectoWeb)) {
                        oldProyectoWebOfParticipacionesWebNewParticipacionWeb.getParticipacionesWeb().remove(participacionesWebNewParticipacionWeb);
                        oldProyectoWebOfParticipacionesWebNewParticipacionWeb = em.merge(oldProyectoWebOfParticipacionesWebNewParticipacionWeb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proyectoWeb.getId();
                if (findProyectoWeb(id) == null) {
                    throw new NonexistentEntityException("The proyectoWeb with id " + id + " no longer exists.");
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
            ProyectoWeb proyectoWeb;
            try {
                proyectoWeb = em.getReference(ProyectoWeb.class, id);
                proyectoWeb.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyectoWeb with id " + id + " no longer exists.", enfe);
            }
            PresupuestoWebDetallado presupuestoWebAnioUno = proyectoWeb.getPresupuestoWebAnioUno();
            if (presupuestoWebAnioUno != null) {
                presupuestoWebAnioUno.setProyectoWeb(null);
                presupuestoWebAnioUno = em.merge(presupuestoWebAnioUno);
            }
            PresupuestoWeb presupuestoWeb = proyectoWeb.getPresupuestoWeb();
            if (presupuestoWeb != null) {
                presupuestoWeb.setProyectoWeb(null);
                presupuestoWeb = em.merge(presupuestoWeb);
            }
            PresupuestoWebDetalladoDos presupuestoWebAnioDos = proyectoWeb.getPresupuestoWebAnioDos();
            if (presupuestoWebAnioDos != null) {
                presupuestoWebAnioDos.setProyectoWeb(null);
                presupuestoWebAnioDos = em.merge(presupuestoWebAnioDos);
            }
            PresupuestoWebDetalladoTres presupuestoWebAnioTres = proyectoWeb.getPresupuestoWebAnioTres();
            if (presupuestoWebAnioTres != null) {
                presupuestoWebAnioTres.setProyectoWeb(null);
                presupuestoWebAnioTres = em.merge(presupuestoWebAnioTres);
            }
            PresupuestoWebDetalladoCuatro presupuestoWebAnioCuatro = proyectoWeb.getPresupuestoWebAnioCuatro();
            if (presupuestoWebAnioCuatro != null) {
                presupuestoWebAnioCuatro.setProyectoWeb(null);
                presupuestoWebAnioCuatro = em.merge(presupuestoWebAnioCuatro);
            }
            List<ParticipacionWeb> participacionesWeb = proyectoWeb.getParticipacionesWeb();
            for (ParticipacionWeb participacionesWebParticipacionWeb : participacionesWeb) {
                participacionesWebParticipacionWeb.setProyectoWeb(null);
                participacionesWebParticipacionWeb = em.merge(participacionesWebParticipacionWeb);
            }
            em.remove(proyectoWeb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProyectoWeb> findProyectoWebEntities() {
        return findProyectoWebEntities(true, -1, -1);
    }

    public List<ProyectoWeb> findProyectoWebEntities(int maxResults, int firstResult) {
        return findProyectoWebEntities(false, maxResults, firstResult);
    }

    private List<ProyectoWeb> findProyectoWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProyectoWeb.class));
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

    public ProyectoWeb findProyectoWeb(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProyectoWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProyectoWeb> rt = cq.from(ProyectoWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
