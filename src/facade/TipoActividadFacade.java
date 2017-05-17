/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.TipoActividadJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.TipoActividad;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class TipoActividadFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(TipoActividad TipoActividad) {
        new TipoActividadJpaController(emf).create(TipoActividad);
    }

    public void modificar(TipoActividad TipoActividad) {
        try {
            new TipoActividadJpaController(emf).edit(TipoActividad);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoActividadFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoActividadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TipoActividadJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoActividadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TipoActividad> getTodosTipoActividad() {
        Query quTodosTipoActividad = em.createQuery("SELECT ta FROM TipoActividad ta");
        return quTodosTipoActividad.getResultList();
    }

    public List<TipoActividad> getTipoActividad(String descripcion) {
        Query quTipoActividad = em.createQuery("SELECT ta FROM TipoActividad ta "
                + "WHERE ta.descripcion LIKE '" + descripcion + "'");
        return quTipoActividad.getResultList();
    }

}
