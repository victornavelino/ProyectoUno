/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueJpaController;
import controladores.CalificacionWebJpaController;
import controladores.CargoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.proyecto.evaluacion.Bloque;
import entidades.proyecto.evaluacion.CalificacionWeb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class CalificacionWebFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static CalificacionWebFacade instance = null;

    protected CalificacionWebFacade() {
    }

    public static CalificacionWebFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CalificacionWebFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(CalificacionWeb calificacionWeb) {
        new CalificacionWebJpaController(emf).create(calificacionWeb);
    }

    public void modificar(CalificacionWeb calificacionWeb) {
        try {
            new CalificacionWebJpaController(emf).edit(calificacionWeb);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CalificacionWebFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CalificacionWebFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CalificacionWeb buscar(long id) {
        Query quBuscar = em.createQuery("SELECT b FROM CalificacionWeb b WHERE b.id=:id");
        quBuscar.setParameter("id", id);
        try {
            return (CalificacionWeb) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            CalificacionWeb calificacionWeb = null;
            return calificacionWeb;
        }

    }

    public List<CalificacionWeb> getTodos() {
        Query quBuscar = em.createQuery("SELECT b FROM CalificacionWeb b");
        return quBuscar.getResultList();
    }

    public List<CalificacionWeb> getCalificaciones(String descripcion) {
        Query quBuscar = em.createQuery("SELECT b FROM CalificacionWeb b where b.descripcion=:descripcion");
        quBuscar.setParameter("descripcion", descripcion);
        return quBuscar.getResultList();
    }

    public void eliminar(CalificacionWeb calificacionWeb) {
        try {
            new CalificacionWebJpaController(emf).destroy(calificacionWeb.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CalificacionWebFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
