/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CalificacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Calificacion;
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
public class CalificacionFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    CalificacionJpaController calificacionJpaController = new CalificacionJpaController(emf);
    private static CalificacionFacade instance = null;

    protected CalificacionFacade() {
    }

    public static CalificacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CalificacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Calificacion calificacion) {
        new CalificacionJpaController(emf).create(calificacion);
    }

    public void modificar(Calificacion calificacion) {
        try {
            new CalificacionJpaController(emf).edit(calificacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CalificacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CalificacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    public void eliminar(long id) {
        try {
            new CalificacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CalificacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Calificacion buscar(long id) {
        return new CalificacionJpaController(emf).findCalificacion(id);
    }

    public List<Calificacion> getTodas(String enLetras) {
        Query quBuscar = em.createQuery("SELECT c FROM Calificacion c WHERE c.descripcion LIKE '%"
                + enLetras + "%'");
        return quBuscar.getResultList();
    }
     public List<Calificacion> getTodas() {
        Query quBuscar = em.createQuery("SELECT c FROM Calificacion c");
        return quBuscar.getResultList();
    }
    
}
