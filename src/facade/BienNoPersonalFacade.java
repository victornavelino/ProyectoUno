/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BienNoPersonalJpaController;
import entidades.economico.BienNoPersonal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class BienNoPersonalFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    BienNoPersonalJpaController bienNoPersonalJpaController = new BienNoPersonalJpaController(emf);

    private static BienNoPersonalFacade instance = null;

    protected BienNoPersonalFacade() {
    }

    public static BienNoPersonalFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BienNoPersonalFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaBienNoPersonal(BienNoPersonal bienNoPersonal){
        bienNoPersonalJpaController.create(bienNoPersonal);
    }
}
