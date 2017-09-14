/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CobroJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.Cobro;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class CobroFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    CobroJpaController cobroJpaController = new CobroJpaController(emf);

    private static CobroFacade instance = null;

    protected CobroFacade() {
    }

    public static CobroFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CobroFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaCobro(Cobro cobro){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();    
        new CobroJpaController(emf).create(cobro);        
    }           
    
    public void modificarCobro(Cobro cobro){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();    
            new  CobroJpaController(emf).edit(cobro);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CobroFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CobroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarCobro(Long idCobro){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();    
            new CobroJpaController(emf).destroy(idCobro);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CobroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
