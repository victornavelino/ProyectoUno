/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ResolucionSecytJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionSecyt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class ResolucionSecytFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ResolucionSecytJpaController secytJpaController = new ResolucionSecytJpaController(emf);

    private static ResolucionSecytFacade instance = null;

    protected ResolucionSecytFacade() {
    }

    public static ResolucionSecytFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ResolucionSecytFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaResolucionSecyt(ResolucionSecyt secyt){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();    
        new ResolucionSecytJpaController(emf).create(secyt);
    }
    
    public void modificacionResolucionSecyt(ResolucionSecyt secyt){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();    
            new ResolucionSecytJpaController(emf).edit(secyt);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ResolucionSecytFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResolucionSecytFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
