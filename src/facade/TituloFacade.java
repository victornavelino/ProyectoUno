/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.TituloJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.titulo.Titulo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class TituloFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(Titulo titulo) {
        new TituloJpaController(emf).create(titulo);
    }

    public void modificar(Titulo titulo) {
        try {
            new TituloJpaController(emf).edit(titulo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TituloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TituloJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
