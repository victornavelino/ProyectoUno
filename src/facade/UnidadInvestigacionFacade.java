/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.UnidadInvestigacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.UnidadInvestigacion;
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
public class UnidadInvestigacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    UnidadInvestigacionJpaController unidadInvestigacionJpaController = new UnidadInvestigacionJpaController(emf);

    private static UnidadInvestigacionFacade instance = null;

    protected UnidadInvestigacionFacade() {
    }

        public static UnidadInvestigacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new UnidadInvestigacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(UnidadInvestigacion UnidadInvestigacion) {
        new UnidadInvestigacionJpaController(emf).create(UnidadInvestigacion);
    }

    public void modificar(UnidadInvestigacion UnidadInvestigacion) {
        try {
            new UnidadInvestigacionJpaController(emf).edit(UnidadInvestigacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UnidadInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new UnidadInvestigacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UnidadInvestigacion> getTodosUnidadInvestigacion() {
        Query quTodosUnidadInvestigacion = em.createQuery("SELECT at FROM UnidadInvestigacion at");
        return quTodosUnidadInvestigacion.getResultList();
    }

    public List<UnidadInvestigacion> getUnidadInvestigacion(String descripcion) {
        Query quUnidadInvestigacion = em.createQuery("SELECT at FROM UnidadInvestigacion at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quUnidadInvestigacion.getResultList();
    }
    public List<UnidadInvestigacion> filtrar(String descripcion){
        
        Query quBuscar = em.createQuery("SELECT i FROM UnidadInvestigacion i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

}
