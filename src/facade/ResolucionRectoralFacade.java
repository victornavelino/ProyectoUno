/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ResolucionRectoralJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionRectoral;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class ResolucionRectoralFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ResolucionRectoralJpaController rectoralJpaController = new ResolucionRectoralJpaController(emf);

    private static ResolucionRectoralFacade instance = null;

    protected ResolucionRectoralFacade() {
    }

    public static ResolucionRectoralFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ResolucionRectoralFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaResolucionRectoral(ResolucionRectoral rectoral){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager(); 
        new ResolucionRectoralJpaController(emf).create(rectoral);
    }
    
    public void modificacionResolucionRectoral(ResolucionRectoral rectoral){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager(); 
            new ResolucionRectoralJpaController(emf).edit(rectoral);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ResolucionRectoralFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResolucionRectoralFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
