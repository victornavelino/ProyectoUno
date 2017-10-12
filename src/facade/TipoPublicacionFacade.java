/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TipoPublicacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.TipoPublicacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class TipoPublicacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    private static TipoPublicacionFacade instance = null;

    protected TipoPublicacionFacade() {
    }

    public static TipoPublicacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoPublicacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoPublicacion tipoPublicacion) {
        new TipoPublicacionJpaController(emf).create(tipoPublicacion);
    }
    
    public void eliminar(long id) {
        try {
            new TipoPublicacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoActividadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoPublicacion buscar(long id) {
        return new TipoPublicacionJpaController(emf).findTipoPublicacion(id);
    }

    public List<TipoPublicacion> listarTipoPublicacion(String text) {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT td FROM TipoPublicacion td WHERE td.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<TipoPublicacion> listarTodosTipoPublicacion() {

        return new TipoPublicacionJpaController(emf).findTipoPublicacionEntities();
    }

    public List<TipoPublicacion> getTodosPublicacion() {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT td FROM TipoPublicacion td ORDER BY td.descripcion");
        return quBuscar.getResultList();
    }
}
