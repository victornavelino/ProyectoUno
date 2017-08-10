/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.RendicionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.Rendicion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wbivanco
 */
public class RendicionFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    //RendicionJpaController rendicionJpaController = new RendicionJpaController(emf);

    private static RendicionFacade instance = null;

    protected RendicionFacade() {
    }

    public static RendicionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new RendicionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaRendicion(Rendicion rendi){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        new RendicionJpaController(emf).create(rendi);
    }
    
    public void modificarRendicion(Rendicion rendi){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            EntityManager em = emf.createEntityManager();
            new RendicionJpaController(emf).edit(rendi);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RendicionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RendicionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarRendicion(Long idRendicion){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            EntityManager em = emf.createEntityManager();
            new RendicionJpaController(emf).destroy(idRendicion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RendicionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
