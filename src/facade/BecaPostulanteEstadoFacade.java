/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BecaPostulanteEstadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.BecaPostulanteEstado;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class BecaPostulanteEstadoFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    BecaPostulanteEstadoJpaController BecasPostulanteEstadoJpaController = new BecaPostulanteEstadoJpaController(emf);

    private static BecaPostulanteEstadoFacade instance = null;
    
     protected BecaPostulanteEstadoFacade() {
    }

        public static BecaPostulanteEstadoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BecaPostulanteEstadoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(BecaPostulanteEstado estado) {
        new BecaPostulanteEstadoJpaController(emf).create(estado);
    }

    public void modificar(BecaPostulanteEstado estado) {
        try {
            new BecaPostulanteEstadoJpaController(emf).edit(estado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BecaPostulanteEstadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BecaPostulanteEstadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new BecaPostulanteEstadoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BecaPostulanteEstadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BecaPostulanteEstado> getTodosBecasPostulanteEstado() {
        Query quTodosbecas = em.createQuery("SELECT e FROM BecaPostulanteEstado e");
        return quTodosbecas.getResultList();
    }
    
}
