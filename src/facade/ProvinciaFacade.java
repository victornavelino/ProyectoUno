/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ProvinciaJpaController;
import entidades.localidad.Provincia;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class ProvinciaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static ProvinciaFacade instance = null;

    protected ProvinciaFacade() {
    }

    public static ProvinciaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProvinciaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Provincia cliente) {
        new ProvinciaJpaController(emf).create(cliente);
    }

    public Provincia buscar(long id) {
        Query quBuscar = em.createQuery("SELECT p FROM Provincia p WHERE p.id="
                + id);
        try {
            return (Provincia) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Provincia provincia = null;
            return provincia;
        }

    }

    public List<Provincia> listarProvincia(String text) {
        Query quBuscar = em.createQuery("SELECT p FROM Provincia p WHERE p.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Provincia> listarTodosProvincia() {
        Query quBuscar = em.createQuery("SELECT p FROM Provincia p");
        return quBuscar.getResultList();
    }

    public List<Provincia> listarTodosProvinciaOrdenados() {
        Query quBuscar = em.createQuery("SELECT p FROM Provincia p ORDER BY p.descripcion");
        return quBuscar.getResultList();
    }
}
