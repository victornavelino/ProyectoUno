/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CargoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class CargoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static CargoFacade instance = null;

    protected CargoFacade() {
    }

    public static CargoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CargoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Cargo cargo) {
        new CargoJpaController(emf).create(cargo);
    }

    public void modificar(Cargo cargo) {
        try {
            new CargoJpaController(emf).edit(cargo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CargoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CargoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cargo buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Cargo tt WHERE tt.id="
                + id);
        try {
            return (Cargo) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Cargo cargo = null;
            return cargo;
        }

    }

    public List<Cargo> listarCargo(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Cargo tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Cargo> listarTodosCargo() {
        Query quBuscar = em.createQuery("SELECT tt FROM Cargo tt");
        return quBuscar.getResultList();
    }

    public List<Cargo> listarTodosCargoOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Cargo tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
