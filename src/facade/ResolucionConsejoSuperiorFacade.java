/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ResolucionConsejoSuperiorJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ResolucionConsejoSuperior;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class ResolucionConsejoSuperiorFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ResolucionConsejoSuperiorJpaController consejoSuperiorJpaController = 
            new ResolucionConsejoSuperiorJpaController(emf);

    private static ResolucionConsejoSuperiorFacade instance = null;

    protected ResolucionConsejoSuperiorFacade() {
    }

    public static ResolucionConsejoSuperiorFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ResolucionConsejoSuperiorFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaResolucionConsejoSuperior(ResolucionConsejoSuperior consejoSuperior){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager(); 
        new ResolucionConsejoSuperiorJpaController(emf).create(consejoSuperior);
    }
    
    public void modificacionResolucionConsejoSuperior(ResolucionConsejoSuperior consejoSuperior){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager(); 
            new ResolucionConsejoSuperiorJpaController(emf).edit(consejoSuperior);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ResolucionConsejoSuperiorFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResolucionConsejoSuperiorFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
