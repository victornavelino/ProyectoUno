/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ParticipacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Participacion;
import entidades.proyecto.Proyecto;
import includes.Comunes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class ParticipacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    
    

    public void alta(Participacion participacion) {
        new ParticipacionJpaController(emf).create(participacion);
    }

    public void modificar(Participacion participacion) {
        try {
            new ParticipacionJpaController(emf).edit(participacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ParticipacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParticipacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new ParticipacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ParticipacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Participacion getParticipacion(Investigador investigador,
            List<Participacion> participaciones) {
        Participacion participacion = null;
        for (Participacion p : participaciones) {
            if (investigador.equals(p.getInvestigador())) {
                participacion = p;
            }
        }
        return participacion;
    }

    public List<Participacion> getTodos() {
        return new ParticipacionJpaController(emf).findParticipacionEntities();
    }

    public Participacion getDirector(Proyecto proy) {
        try {
            Query quDirector = em.createQuery("SELECT pa FROM Participacion pa "
                    + "WHERE pa.rol.descripcion=:car AND "
                    + "((pa.fechaHasta IS NULL  OR pa.fechaHasta > :fecha ) OR pa.proyecto.fechaFinalizacion < :fecha ) AND pa.rol.descripcion=:car AND "
                    + "pa.proyecto.id=:proye ORDER BY pa.fechaHasta DESC");
            quDirector.setParameter("car", "Director");
            quDirector.setParameter("fecha",Comunes.obtenerFechaActualDesdeDB());
            quDirector.setParameter("proye", proy.getId());
            return (Participacion) quDirector.getResultList().get(0);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    public Participacion getCoDirector(Proyecto proy) {
        try {
            Query quDirector = em.createQuery("SELECT pa FROM Participacion pa "
                    + "WHERE pa.rol.descripcion=:car AND "
                    + "((pa.fechaHasta IS NULL  OR pa.fechaHasta > :fecha ) OR pa.proyecto.fechaFinalizacion < :fecha ) AND pa.rol.descripcion=:car AND "
                    + "pa.proyecto.id=:proye ORDER BY pa.fechaHasta DESC");
            quDirector.setParameter("car", "Co-Director");
            quDirector.setParameter("fecha",Comunes.obtenerFechaActualDesdeDB());
            quDirector.setParameter("proye", proy.getId());
            return (Participacion) quDirector.getResultList().get(0);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
