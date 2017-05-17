/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.FormacionAcademicaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class FormacionAcademicaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(FormacionAcademica formacionAcademica) {
        new FormacionAcademicaJpaController(emf).create(formacionAcademica);
    }

    public void modificar(FormacionAcademica formacionAcademica) {
        try {
            new FormacionAcademicaJpaController(emf).edit(formacionAcademica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormacionAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new FormacionAcademicaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
