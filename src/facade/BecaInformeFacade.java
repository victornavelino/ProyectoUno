/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;


import controladores.BecaInformeJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.BecaInforme;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class BecaInformeFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    BecaInformeJpaController BecasJpaController = new BecaInformeJpaController(emf);

    private static BecaInformeFacade instance = null;
    
     protected BecaInformeFacade() {
    }

        public static BecaInformeFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BecaInformeFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(BecaInforme informe) {
        new BecaInformeJpaController(emf).create(informe);
    }

    public void modificar(BecaInforme informe) {
        try {
            new BecaInformeJpaController(emf).edit(informe);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BecaInformeFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BecaInformeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new BecaInformeJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BecaInformeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BecaInforme> getTodosBecasInforme() {
        Query quTodosbecas = em.createQuery("SELECT i FROM BecaInforme i");
        return quTodosbecas.getResultList();
    }
    
    
}
