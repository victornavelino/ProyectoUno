/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.persona.investigador.curso;

import controladores.CarreraAsignaturaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.CarreraAsignatura;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class CarreraAsignaturaFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static CarreraAsignaturaFacade instance = null;
    
    protected CarreraAsignaturaFacade() {
    }
    
    public static CarreraAsignaturaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CarreraAsignaturaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void alta(CarreraAsignatura carreraAsignatura) {
        new CarreraAsignaturaJpaController(emf).create(carreraAsignatura);
    }
    
    public void eliminar(long id) {
        try {
            new CarreraAsignaturaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CarreraAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificar(CarreraAsignatura carreraAsignatura) {
        try {
            new CarreraAsignaturaJpaController(emf).edit(carreraAsignatura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CarreraAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CarreraAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CarreraAsignatura buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM CarreraAsignatura tt WHERE tt.id="
                + id);
        try {
            return (CarreraAsignatura) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            CarreraAsignatura carreraAsignatura = null;
            return carreraAsignatura;
        }
        
    }
    
    public List<CarreraAsignatura> listarCarreraAsignatura(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM CarreraAsignatura tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }
    
    public List<CarreraAsignatura> listarTodosCarreraAsignatura() {
        Query quBuscar = em.createQuery("SELECT tt FROM CarreraAsignatura tt");
        return quBuscar.getResultList();
    }
    
    public List<CarreraAsignatura> listarTodosCarreraAsignaturaOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM CarreraAsignatura tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
