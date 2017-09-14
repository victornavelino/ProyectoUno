/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.NumPagoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.NumPago;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
public class NumPagoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    NumPagoJpaController evaluacionEstadoJpaController = new NumPagoJpaController(emf);
    private static NumPagoFacade instance = null;

    protected NumPagoFacade() {
    }

    public static NumPagoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NumPagoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(NumPago numPago) {
        new NumPagoJpaController(emf).create(numPago);
    }

    public void modificar(NumPago numPago) {
        try {
            new NumPagoJpaController(emf).edit(numPago);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NumPagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NumPagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new NumPagoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NumPagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public List<NumPago> getTodas() {
        Query quBuscar = em.createQuery("SELECT n FROM NumPago n");
        return quBuscar.getResultList();
    }
    
}
