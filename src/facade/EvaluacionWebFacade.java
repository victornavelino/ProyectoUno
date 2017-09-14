/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueJpaController;
import controladores.CargoJpaController;
import controladores.EvaluacionWebJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.proyecto.evaluacion.Bloque;
import entidades.proyecto.evaluacion.EvaluacionWeb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
public class EvaluacionWebFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static EvaluacionWebFacade instance = null;

    protected EvaluacionWebFacade() {
    }

    public static EvaluacionWebFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluacionWebFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EvaluacionWeb evaluacionWeb) {
        new EvaluacionWebJpaController(emf).create(evaluacionWeb);
    }

    public void eliminar(EvaluacionWeb evaluacionWeb) {
        try {
            new EvaluacionWebJpaController(emf).destroy(evaluacionWeb.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionWebFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar(EvaluacionWeb evaluacionWeb) {
        try {
            new EvaluacionWebJpaController(emf).edit(evaluacionWeb);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionWebFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluacionWebFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EvaluacionWeb buscar(Long id) {
        return new EvaluacionWebJpaController(emf).findEvaluacionWeb(id);
    }

    public List<EvaluacionWeb> getTodos() {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT e FROM EvaluacionWeb e");
        return quBuscar.getResultList();
    }

}
