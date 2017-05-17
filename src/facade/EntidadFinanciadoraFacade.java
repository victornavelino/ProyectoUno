/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EntidadAcreditadoraJpaController;
import controladores.EntidadFinanciadoraJpaController;
import controladores.UnidadEjecutoraJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadEjecutora;
import entidades.proyecto.EntidadAcreditadora;
import entidades.proyecto.EntidadFinanciadora;
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
public class EntidadFinanciadoraFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EntidadFinanciadoraJpaController entidadFinanciadoraJpaController = new EntidadFinanciadoraJpaController(emf);

    private static EntidadFinanciadoraFacade instance = null;

    protected EntidadFinanciadoraFacade() {
    }

        public static EntidadFinanciadoraFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EntidadFinanciadoraFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EntidadFinanciadora entidadFinanciadora) {
        new EntidadFinanciadoraJpaController(emf).create(entidadFinanciadora);
    }

    public void modificar(EntidadFinanciadora entidadFinanciadora) {
        try {
            new EntidadFinanciadoraJpaController(emf).edit(entidadFinanciadora);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadFinanciadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntidadFinanciadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EntidadFinanciadoraJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadFinanciadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EntidadFinanciadora> getTodosEntidadFinanciadoras() {
        return new EntidadFinanciadoraJpaController(emf).findEntidadFinanciadoraEntities();
    }


}
