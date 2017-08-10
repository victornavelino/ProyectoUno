/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ProrrogaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadEjecutora;
import entidades.proyecto.Prorroga;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class ProrrogaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ProrrogaJpaController prorrogaJpaController = new ProrrogaJpaController(emf);

    private static ProrrogaFacade instance = null;

    protected ProrrogaFacade() {
    }

    public static ProrrogaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProrrogaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Prorroga prorroga) {
        new ProrrogaJpaController(emf).create(prorroga);
    }

    public void modificar(Prorroga prorroga) {
        try {
            new ProrrogaJpaController(emf).edit(prorroga);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProrrogaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProrrogaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new ProrrogaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProrrogaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Prorroga> getTodasProrroga() {
        Query quTodasProrroga = em.createQuery("SELECT p FROM Prorroga p ORDER BY p.fecha");
        return quTodasProrroga.getResultList();
    }

    public Prorroga buscar(long parseLong) {
        return new ProrrogaJpaController(emf).findProrroga(parseLong);
    }

}
