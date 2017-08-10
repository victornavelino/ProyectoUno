/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;


import controladores.CorrespondenciaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.Correspondencia;


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
public class CorrespondenciaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    private static CorrespondenciaFacade instance = null;
    
     protected CorrespondenciaFacade() {
    }

        public static CorrespondenciaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CorrespondenciaFacade();
        }
    }
    
    public List<Correspondencia> getTodos(){
        Query quTodos = em.createQuery("SELECT c FROM Correspondencia c");
        return quTodos.getResultList();
    }
    public void alta(Correspondencia correspondencia) {
        new CorrespondenciaJpaController(emf).create(correspondencia);
    }

    public void modificar(Correspondencia correspondencia) {
        try {
            new CorrespondenciaJpaController(emf).edit(correspondencia);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CorrespondenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CorrespondenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new CorrespondenciaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CorrespondenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
}
