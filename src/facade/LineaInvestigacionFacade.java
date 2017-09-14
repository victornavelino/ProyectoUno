/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.LineaInvestigacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.LineaInvestigacion;
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
public class LineaInvestigacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    LineaInvestigacionJpaController lineaInvestigacionJpaController = new LineaInvestigacionJpaController(emf);

    private static LineaInvestigacionFacade instance = null;

    protected LineaInvestigacionFacade() {
    }

        public static LineaInvestigacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new LineaInvestigacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(LineaInvestigacion LineaInvestigacion) {
        new LineaInvestigacionJpaController(emf).create(LineaInvestigacion);
    }

    public void modificar(LineaInvestigacion LineaInvestigacion) {
        try {
            new LineaInvestigacionJpaController(emf).edit(LineaInvestigacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LineaInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LineaInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new LineaInvestigacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LineaInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<LineaInvestigacion> getTodosLineaInvestigacion() {
        Query quTodosLineaInvestigacion = em.createQuery("SELECT at FROM LineaInvestigacion at");
        return quTodosLineaInvestigacion.getResultList();
    }

    public List<LineaInvestigacion> getLineaInvestigacion(String descripcion) {
        Query quLineaInvestigacion = em.createQuery("SELECT at FROM LineaInvestigacion at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quLineaInvestigacion.getResultList();
    }

    public List filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM LineaInvestigacion i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

}
