/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.LineaPrioritariaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.LineaPrioritaria;
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
public class LineaPrioritariaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(LineaPrioritaria lineaPrioritaria) {
        new LineaPrioritariaJpaController(emf).create(lineaPrioritaria);
    }

    public void modificar(LineaPrioritaria lineaPrioritaria) {
        try {
            new LineaPrioritariaJpaController(emf).edit(lineaPrioritaria);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LineaPrioritariaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LineaPrioritariaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new LineaPrioritariaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LineaPrioritariaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<LineaPrioritaria> getTodosLineaPrioritaria() {
        Query quTodosLineaPrioritaria = em.createQuery("SELECT lp FROM LineaPrioritaria lp");
        return quTodosLineaPrioritaria.getResultList();
    }

    public List<LineaPrioritaria> getLineaPrioritaria(String descripcion) {
        Query quLineaPrioritaria = em.createQuery("SELECT lp FROM LineaPrioritaria lp "
                + "WHERE lp.descripcion LIKE '" + descripcion + "'");
        return quLineaPrioritaria.getResultList();
    }

}
