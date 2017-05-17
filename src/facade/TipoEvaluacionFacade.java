/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TipoEvaluacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.evaluacion.TipoEvaluacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class TipoEvaluacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    private static TipoEvaluacionFacade instance = null;

    protected TipoEvaluacionFacade() {
    }

    public static TipoEvaluacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoEvaluacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoEvaluacion tipoEvaluacion) {
        new TipoEvaluacionJpaController(emf).create(tipoEvaluacion);
    }
    public void modificacion(TipoEvaluacion tipoEvaluacion) {
        try {
            new TipoEvaluacionJpaController(emf).edit(tipoEvaluacion);
        } catch (Exception ex) {
            Logger.getLogger(TipoEvaluacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(TipoEvaluacion tipoEvaluacion) {
        try {
            new TipoEvaluacionJpaController(emf).destroy(tipoEvaluacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoEvaluacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoEvaluacion buscar(long id) {
        return new TipoEvaluacionJpaController(emf).findTipoEvaluacion(id);
    }

    public List<TipoEvaluacion> listarTipoEvaluacion(String text) {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT td FROM TipoEvaluacion td WHERE td.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<TipoEvaluacion> listarTodosTipoEvaluacion() {

        return new TipoEvaluacionJpaController(emf).findTipoEvaluacionEntities();
    }

    public List<TipoEvaluacion> listarTodosTipoEvaluacionOrdenados() {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT td FROM TipoEvaluacion td ORDER BY td.descripcion");
        return quBuscar.getResultList();
    }
}
