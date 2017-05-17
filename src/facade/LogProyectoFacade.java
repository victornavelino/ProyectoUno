/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.LogProyectoJpaController;
import controladores.NotaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.LogProyecto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class LogProyectoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static LogProyectoFacade instance = null;

    protected LogProyectoFacade() {
    }

    public static LogProyectoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new LogProyectoFacade();
        }
    }

    public List<LogProyecto> getTodos() {
        Query quTodos = em.createQuery("SELECT l FROM LogProyecto l order by l.id desc");
        quTodos.setMaxResults(100);
        return quTodos.getResultList();
    }

    public void alta(LogProyecto log) {
        new LogProyectoJpaController(emf).create(log);
    }

    public void modificar(LogProyecto log) {
        try {
            new LogProyectoJpaController(emf).edit(log);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LogProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new LogProyectoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
