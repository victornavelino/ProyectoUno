/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EntidadAcreditadoraJpaController;
import controladores.UnidadEjecutoraJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadEjecutora;
import entidades.proyecto.EntidadAcreditadora;
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
public class EntidadAcreditadoraFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EntidadAcreditadoraJpaController entidadAcreditadoraJpaController = new EntidadAcreditadoraJpaController(emf);

    private static EntidadAcreditadoraFacade instance = null;

    protected EntidadAcreditadoraFacade() {
    }

        public static EntidadAcreditadoraFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EntidadAcreditadoraFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EntidadAcreditadora entidadAcreditadora) {
        new EntidadAcreditadoraJpaController(emf).create(entidadAcreditadora);
    }

    public void modificar(EntidadAcreditadora entidadAcreditadora) {
        try {
            new EntidadAcreditadoraJpaController(emf).edit(entidadAcreditadora);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadAcreditadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntidadAcreditadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EntidadAcreditadoraJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadAcreditadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EntidadAcreditadora> getTodosUnidadEjecutora() {
        return new EntidadAcreditadoraJpaController(emf).findEntidadAcreditadoraEntities();
    }


}
