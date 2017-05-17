/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.CategoriaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.Categoria;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class CategoriaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(Categoria categoria) {
        new CategoriaJpaController(emf).create(categoria);
    }

    public void modificar(Categoria categoria) {
        try {
            new CategoriaJpaController(emf).edit(categoria);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategoriaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new CategoriaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategoriaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Categoria buscar(long id) {
        Query quBuscar = em.createQuery("SELECT c FROM Categoria c WHERE "
                + "c.id=" + id);
        return (Categoria) quBuscar.getSingleResult();
    }

}
