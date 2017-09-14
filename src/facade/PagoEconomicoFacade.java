/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PagoEconomicoJpaController;
import controladores.PagoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.PagoEconomico;
import entidades.proyecto.Proyecto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author walter
 */
public class PagoEconomicoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    //PagoJpaController pagoJpaController = new PagoJpaController(emf);
    private static PagoEconomicoFacade instance = null;

    protected PagoEconomicoFacade() {
    }

    public static PagoEconomicoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciaciÃ³n mÃºltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PagoEconomicoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepciÃ³n:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void altaPago(PagoEconomico pago) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();    
        new PagoEconomicoJpaController(emf).create(pago);
    }

    public void modificarPago(PagoEconomico pago) {
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();    
            new PagoEconomicoJpaController(emf).edit(pago);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarPago(Long idPago){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();    
            new PagoEconomicoJpaController(emf).destroy(idPago);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PagoEconomicoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Proyecto pagosxProyecto(Proyecto proye) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        Query quPagos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.id=:proy");
        quPagos.setParameter("proy", proye.getId());
        try {
            return (Proyecto) quPagos.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
