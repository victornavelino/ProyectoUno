/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EntidadConvenioJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.EntidadConvenio;
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
public class EntidadConvenioFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EntidadConvenioJpaController entidadConvenioJpaController = new EntidadConvenioJpaController(emf);

    private static EntidadConvenioFacade instance = null;

    protected EntidadConvenioFacade() {
    }

        public static EntidadConvenioFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EntidadConvenioFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EntidadConvenio EntidadConvenio) {
        new EntidadConvenioJpaController(emf).create(EntidadConvenio);
    }

    public void modificar(EntidadConvenio EntidadConvenio) {
        try {
            new EntidadConvenioJpaController(emf).edit(EntidadConvenio);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadConvenioFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntidadConvenioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EntidadConvenioJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadConvenioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EntidadConvenio> getTodosEntidadConvenio() {
        Query quTodosEntidadConvenio = em.createQuery("SELECT at FROM EntidadConvenio at");
        return quTodosEntidadConvenio.getResultList();
    }

    public List<EntidadConvenio> getEntidadConvenio(String descripcion) {
        Query quEntidadConvenio = em.createQuery("SELECT at FROM EntidadConvenio at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quEntidadConvenio.getResultList();
    }
    public List<EntidadConvenio> filtrar(String descripcion){
        
        Query quBuscar = em.createQuery("SELECT i FROM EntidadConvenio i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

}
