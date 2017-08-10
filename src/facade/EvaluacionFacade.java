/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EvaluacionJpaController;
import controladores.exceptions.NonexistentEntityException;

import entidades.proyecto.Evaluacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class EvaluacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static EvaluacionFacade instance = null;

    protected EvaluacionFacade() {
    }

    public static EvaluacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Evaluacion evaluacion) {
        new EvaluacionJpaController(emf).create(evaluacion);
    }

    public void modificar(Evaluacion evaluacion) {
        try {
            new EvaluacionJpaController(emf).edit(evaluacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Evaluacion buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Evaluacion tt WHERE tt.id="
                + id);
        try {
            return (Evaluacion) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Evaluacion evaluacion = null;
            return evaluacion;
        }

    }
    public void Eliminar(Long id){
        try {
            new EvaluacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Evaluacion> listarEvaluacion(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Evaluacion tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Evaluacion> listarTodosEvaluacion() {
        Query quBuscar = em.createQuery("SELECT tt FROM Evaluacion tt");
        return quBuscar.getResultList();
    }

    public List<Evaluacion> listarTodosEvaluacionOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Evaluacion tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
