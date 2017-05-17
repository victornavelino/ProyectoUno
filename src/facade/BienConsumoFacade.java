/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BienConsumoJpaController;
import entidades.economico.BienConsumo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class BienConsumoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    BienConsumoJpaController bienConsumoJpaController = new BienConsumoJpaController(emf);

    private static BienConsumoFacade instance = null;

    protected BienConsumoFacade() {
    }

    public static BienConsumoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BienConsumoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaBienConsumo(BienConsumo bienConsumo){
        bienConsumoJpaController.create(bienConsumo);
    }
    
}
