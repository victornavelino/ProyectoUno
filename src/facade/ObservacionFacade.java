/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ObservacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Observacion;
import entidades.becas.PostulacionBeca;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author huguito
 */
public class ObservacionFacade {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ObservacionJpaController evaluacionEstadoJpaController = new ObservacionJpaController(emf);
    
    private static ObservacionFacade instance = null;

    protected ObservacionFacade() {
    }

    public static ObservacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ObservacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Observacion observacion) {
        new ObservacionJpaController(emf).create(observacion);
    }

    public void modificar(Observacion observacion) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        try {
            new ObservacionJpaController(emf).edit(observacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ObservacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ObservacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        try {
            new ObservacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ObservacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public List<Observacion> getTodas() {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        Query quBuscar = em.createQuery("SELECT o FROM Observacion o");
        return quBuscar.getResultList();
    }
    public List<Observacion> getObservacionesPostulacion(PostulacionBeca postulacion) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager ema = emf.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT o FROM Observacion o WHERE o.postulacionBeca=:postulacion");
        quBuscar.setParameter("postulacion", postulacion);
        return quBuscar.getResultList();
    }
    
    public Observacion buscar(long id){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        return new ObservacionJpaController(emf).findObservacion(id); 
               
    }
    
}
