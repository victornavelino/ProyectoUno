/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.UnidadEjecutoraJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadEjecutora;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class UnidadEjecutoraFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    UnidadEjecutoraJpaController unidadEjecutoraJpaController = new UnidadEjecutoraJpaController(emf);

    private static UnidadEjecutoraFacade instance = null;

    protected UnidadEjecutoraFacade() {
    }

        public static UnidadEjecutoraFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new UnidadEjecutoraFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(UnidadEjecutora UnidadEjecutora) {
        new UnidadEjecutoraJpaController(emf).create(UnidadEjecutora);
    }

    public void modificar(UnidadEjecutora UnidadEjecutora) {
        try {
            new UnidadEjecutoraJpaController(emf).edit(UnidadEjecutora);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadEjecutoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UnidadEjecutoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new UnidadEjecutoraJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadEjecutoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UnidadEjecutora> getTodosUnidadEjecutora() {
        Query quTodosUnidadEjecutora = em.createQuery("SELECT ca FROM UnidadEjecutora ca");
        return quTodosUnidadEjecutora.getResultList();
    }

    public List<UnidadEjecutora> getUnidadEjecutora(String descripcion) {
        Query quUnidadEjecutora = em.createQuery("SELECT ca FROM UnidadEjecutora ca "
                + "WHERE ca.descripcion LIKE '" + descripcion + "'");
        return quUnidadEjecutora.getResultList();
    }

    public List filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM UnidadEjecutora i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

}
