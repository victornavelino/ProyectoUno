/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ComisionCategorizacionJpaController;
import entidades.categorizacion.ComisionCategorizacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author walter
 */
public class ComisionCategorizacionFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();    
    private static ComisionCategorizacionFacade instance = null;

    protected ComisionCategorizacionFacade() {
    }

    public static ComisionCategorizacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ComisionCategorizacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ComisionCategorizacion comisionCategorizacion) {
        new ComisionCategorizacionJpaController(emf).create(comisionCategorizacion);
    }
    
    public List<ComisionCategorizacion> comisionCategorizacionTodos(){
        Query quBuscar = em.createQuery("SELECT cc FROM ComisionCategorizacion cc");
        return quBuscar.getResultList();
    }
    
}
