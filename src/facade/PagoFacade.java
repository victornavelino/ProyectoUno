/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PagoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.NotaPago;
import entidades.becas.Pago;
import entidades.becas.PostulacionBeca;
import includes.Comunes;
import includes.ExportarExcel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class PagoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    PagoJpaController pagoJpaController = new PagoJpaController(emf);
    private static PagoFacade instance = null;

    protected PagoFacade() {
    }

    public static PagoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PagoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Pago pago) {
        new PagoJpaController(emf).create(pago);
    }

    public void modificar(Pago pago) {
        try {
            new PagoJpaController(emf).edit(pago);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new PagoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pago buscar(long id) {
        return new PagoJpaController(emf).findPago(id);
    }

    public List<Pago> getTodas(String text) {
        Query quBuscar = em.createQuery("SELECT p FROM Pago p WHERE p.expediente LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Pago> getTodas() {
        Query quBuscar = em.createQuery("SELECT p FROM Pago p");
        return quBuscar.getResultList();
    }

    public List<Pago> getPagosPostulante(PostulacionBeca postulacion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT p FROM Pago p WHERE p.postulacionBeca =:postulacion");
        quBuscar.setParameter("postulacion", postulacion);
        return quBuscar.getResultList();
    }

    
}
