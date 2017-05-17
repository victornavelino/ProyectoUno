/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EvaluacionEstadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.EvaluacionEstado;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
public class EvaluacionEstadoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EvaluacionEstadoJpaController evaluacionEstadoJpaController = new EvaluacionEstadoJpaController(emf);
    private static EvaluacionEstadoFacade instance = null;

    protected EvaluacionEstadoFacade() {
    }

    public static EvaluacionEstadoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluacionEstadoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EvaluacionEstado evaluacionEstado) {
        new EvaluacionEstadoJpaController(emf).create(evaluacionEstado);
    }

    public void modificar(EvaluacionEstado evaluacionEstado) {
        try {
            new EvaluacionEstadoJpaController(emf).edit(evaluacionEstado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionEstadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluacionEstadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EvaluacionEstadoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionEstadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public List<EvaluacionEstado> getTodas() {
        Query quBuscar = em.createQuery("SELECT e FROM EvaluacionEstado e");
        return quBuscar.getResultList();
    }
    
}
