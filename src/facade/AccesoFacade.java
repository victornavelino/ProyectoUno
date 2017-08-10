/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.AccesoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Acceso;
import entidades.persona.investigador.Investigador;
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
public class AccesoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static AccesoFacade instance = null;

    protected AccesoFacade() {
    }

    public static AccesoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AccesoFacade();
        }
    }

    public List<Acceso> getTodos() {
        Query quTodos = em.createQuery("SELECT a FROM Acceso a order by a.id desc");
        quTodos.setMaxResults(100);
        return quTodos.getResultList();
    }

    public void alta(Acceso acceso) {
        new AccesoJpaController(emf).create(acceso);
    }

    public void modificar(Acceso acceso) {
        try {
            new AccesoJpaController(emf).edit(acceso);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AccesoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccesoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new AccesoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AccesoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Acceso> getAccesosInvestigador(Investigador investigador) {
        Query quTodos = em.createQuery("SELECT a FROM Acceso a WHERE a.investigador=:investigador");
        quTodos.setParameter("investigador", investigador);
        return quTodos.getResultList();
    }

}
