/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ParticipacionVinculacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Investigador;

import entidades.proyecto.vinculacion.ParticipacionVinculacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Panchi
 */
public class ParticipacionVinculacionFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(ParticipacionVinculacion participacion) {
        new ParticipacionVinculacionJpaController(emf).create(participacion);
    }

    public void modificar(ParticipacionVinculacion participacion) {
        try {
            new ParticipacionVinculacionJpaController(emf).edit(participacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ParticipacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParticipacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new ParticipacionVinculacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ParticipacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ParticipacionVinculacion getParticipacion(Investigador investigador,
            List<ParticipacionVinculacion> participaciones) {
        ParticipacionVinculacion participacion = null;
        for (ParticipacionVinculacion p : participaciones) {
            if(investigador.equals(p.getInvestigador())) {
                participacion = p;
            }
        }
        return participacion;
    }
}
