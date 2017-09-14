/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueJpaController;
import controladores.CargoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.proyecto.evaluacion.Bloque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class BloqueFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static BloqueFacade instance = null;

    protected BloqueFacade() {
    }

    public static BloqueFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BloqueFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Bloque bloque) {
        new BloqueJpaController(emf).create(bloque);
    }

    public void modificar(Bloque bloque) {
        try {
            new BloqueJpaController(emf).edit(bloque);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BloqueFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BloqueFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bloque buscar(long id) {
        Query quBuscar = em.createQuery("SELECT b FROM Bloque b WHERE b.id=:id");
        quBuscar.setParameter("id", id);
        try {
            return (Bloque) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Bloque bloque = null;
            return bloque;
        }

    }

    public List<Bloque> getTodos() {
        Query quBuscar = em.createQuery("SELECT b FROM Bloque b");
        return quBuscar.getResultList();
    }

    public List<Bloque> getBloques(String descripcion) {
        Query quBuscar = em.createQuery("SELECT b FROM Bloque b where b.descripcion=:descripcion");
        quBuscar.setParameter("descripcion", descripcion);
        return quBuscar.getResultList();
    }

}
