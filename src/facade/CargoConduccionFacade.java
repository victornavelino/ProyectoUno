/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CargoConduccionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.actividadConduccion.CargoConduccion;
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
 * @author Panchi
 */
public class CargoConduccionFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static CargoConduccionFacade instance = null;

    protected CargoConduccionFacade() {
    }

    public static CargoConduccionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CargoConduccionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(CargoConduccion cargoConduccion) {
        new CargoConduccionJpaController(emf).create(cargoConduccion);
    }

    public void modificar(CargoConduccion cargoConduccion) {
        try {
            new CargoConduccionJpaController(emf).edit(cargoConduccion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CargoConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CargoConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CargoConduccion buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM CargoConduccion tt WHERE tt.id="
                + id);
        try {
            return (CargoConduccion) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            CargoConduccion cargoConduccion = null;
            return cargoConduccion;
        }

    }

    public List<CargoConduccion> listarCargoConduccion(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM CargoConduccion tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<CargoConduccion> listarTodosCargoConduccion() {
        Query quBuscar = em.createQuery("SELECT tt FROM CargoConduccion tt");
        return quBuscar.getResultList();
    }

    public List<CargoConduccion> listarTodosCargoConduccionOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM CargoConduccion tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    } 

    public List<CargoConduccion> filtrar(String descripcion) {
       EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT cc From CargoConduccion cc "
                + "WHERE cc.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new CargoConduccionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CargoConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
