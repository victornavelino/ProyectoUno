/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.TipoProyectoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.TipoProyecto;
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
public class TipoProyectoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    TipoProyectoJpaController tipoProyectoJpaController = new TipoProyectoJpaController(emf);

    private static TipoProyectoFacade instance = null;

    protected TipoProyectoFacade() {
    }

        public static TipoProyectoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoProyectoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoProyecto tipoProyecto) {
        new TipoProyectoJpaController(emf).create(tipoProyecto);
    }

    public void modificar(TipoProyecto tipoProyecto) {
        try {
            new TipoProyectoJpaController(emf).edit(tipoProyecto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TipoProyectoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TipoProyecto> getTodosTipoProyecto() {
        Query quTodosTipoProyecto = em.createQuery("SELECT tp FROM TipoProyecto tp");
        return quTodosTipoProyecto.getResultList();
    }

    public List filtrar(String descripcion) {
         Query quBuscar = em.createQuery("SELECT i FROM TipoProyecto i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();        
    }

}
