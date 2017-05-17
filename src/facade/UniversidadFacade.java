/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.UniversidadJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Universidad;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class UniversidadFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static UniversidadFacade instance = null;

    protected UniversidadFacade() {
    }

    public static UniversidadFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new UniversidadFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Universidad universidad) {
        new UniversidadJpaController(emf).create(universidad);
    }

    public Universidad buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Universidad tt WHERE tt.id="
                + id);
        try {
            return (Universidad) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Universidad universidad = null;
            return universidad;
        }

    }

    public List<Universidad> listarUniversidad(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Universidad tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Universidad> listarTodosUniversidad() {
        Query quBuscar = em.createQuery("SELECT tt FROM Universidad tt");
        return quBuscar.getResultList();
    }

    public List<Universidad> listarTodosUniversidadOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Universidad tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public void modificar(Universidad universidad) {
        try {
            new UniversidadJpaController(emf).edit(universidad);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UniversidadFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UniversidadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Universidad> filtrar(String descripcion){
        
        Query quBuscar = em.createQuery("SELECT i FROM Universidad i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

    public void eliminar(long id) {
        try {
            new UniversidadJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CampoAplicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
