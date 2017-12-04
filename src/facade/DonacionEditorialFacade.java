/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DonacionEditorialJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.DonacionEditorial;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author wbivanco
 */
public class DonacionEditorialFacade {
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DonacionEditorialFacade instance = null;

    protected DonacionEditorialFacade() {
    }

    public static DonacionEditorialFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DonacionEditorialFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DonacionEditorial donacion) {
        new DonacionEditorialJpaController(emf).create(donacion);
    }
    
    public void modificar(DonacionEditorial donacion) {
        try {
            new DonacionEditorialJpaController(emf).edit(donacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DonacionEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DonacionEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    public List<DonacionEditorial> listarTodas() {
        Query quBuscar = em.createQuery("SELECT d FROM DonacionEditorial d ORDER BY d.fecha");
        return quBuscar.getResultList();
    }      

    public void eliminar(long id) {
        try {
            new DonacionEditorialJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DonacionEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


