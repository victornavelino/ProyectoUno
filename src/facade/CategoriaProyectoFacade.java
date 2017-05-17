/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.CategoriaProyectoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.CategoriaProyecto;
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
public class CategoriaProyectoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(CategoriaProyecto CategoriaProyecto) {
        new CategoriaProyectoJpaController(emf).create(CategoriaProyecto);
    }

    public void modificar(CategoriaProyecto CategoriaProyecto) {
        try {
            new CategoriaProyectoJpaController(emf).edit(CategoriaProyecto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategoriaProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new CategoriaProyectoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategoriaProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CategoriaProyecto> getTodosCategoriaProyecto() {
        Query quTodosCategoriaProyecto = em.createQuery("SELECT cp FROM CategoriaProyecto cp");
        return quTodosCategoriaProyecto.getResultList();
    }

    public List<CategoriaProyecto> getCategoriaProyecto(String descripcion) {
        Query quCategoriaProyecto = em.createQuery("SELECT cp FROM CategoriaProyecto cp "
                + "WHERE cp.descripcion LIKE '" + descripcion + "'");
        return quCategoriaProyecto.getResultList();
    }

}
