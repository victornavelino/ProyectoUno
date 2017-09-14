/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EspecialidadJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Especialidad;
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
public class EspecialidadFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EspecialidadJpaController especialidadJpaController = new EspecialidadJpaController(emf);

    private static EspecialidadFacade instance = null;

    protected EspecialidadFacade() {
    }

        public static EspecialidadFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EspecialidadFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Especialidad Especialidad) {
        new EspecialidadJpaController(emf).create(Especialidad);
    }

    public void modificar(Especialidad Especialidad) {
        try {
            new EspecialidadJpaController(emf).edit(Especialidad);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecialidadFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EspecialidadJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecialidadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Especialidad> getTodosEspecialidad() {
        Query quTodosEspecialidad = em.createQuery("SELECT at FROM Especialidad at");
        return quTodosEspecialidad.getResultList();
    }

    public List<Especialidad> getEspecialidad(String descripcion) {
        Query quEspecialidad = em.createQuery("SELECT at FROM Especialidad at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quEspecialidad.getResultList();
    }
    public List<Especialidad> filtrar(String descripcion){
        
        Query quBuscar = em.createQuery("SELECT i FROM Especialidad i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

}
