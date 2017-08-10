/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;


import controladores.NotaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Nota;
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
public class NotaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    private static NotaFacade instance = null;
    
     protected NotaFacade() {
    }

        public static NotaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NotaFacade();
        }
    }
    
    public List<Nota> getTodos(){
        Query quTodos = em.createQuery("SELECT n FROM Nota n");
        return quTodos.getResultList();
    }
    public void alta(Nota nota) {
        new NotaJpaController(emf).create(nota);
    }

    public void modificar(Nota nota) {
        try {
            new NotaJpaController(emf).edit(nota);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NotaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NotaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new NotaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NotaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
}
