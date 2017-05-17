/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EvaluacionEditorialJpaController;
import controladores.exceptions.NonexistentEntityException;

import entidades.proyecto.editorial.EvaluacionEditorial;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class EvaluacionEditorialFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static EvaluacionEditorialFacade instance = null;

    protected EvaluacionEditorialFacade() {
    }

    public static EvaluacionEditorialFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluacionEditorialFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EvaluacionEditorial evaluacionEditorial) {
        new EvaluacionEditorialJpaController(emf).create(evaluacionEditorial);
    }

    public void modificar(EvaluacionEditorial evaluacionEditorial) {
        try {
            new EvaluacionEditorialJpaController(emf).edit(evaluacionEditorial);
        } catch (Exception ex) {
            Logger.getLogger(EvaluacionEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EvaluacionEditorial buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM EvaluacionEditorial tt WHERE tt.id="
                + id);
        try {
            return (EvaluacionEditorial) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            EvaluacionEditorial evaluacionEditorial = null;
            return evaluacionEditorial;
        }

    }
    public void Eliminar(Long id){
        try {
            new EvaluacionEditorialJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EvaluacionEditorial> listarEvaluacion(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM EvaluacionEditorial tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<EvaluacionEditorial> listarTodosEvaluacion() {
        Query quBuscar = em.createQuery("SELECT tt FROM EvaluacionEditorial tt");
        return quBuscar.getResultList();
    }

    public List<EvaluacionEditorial> listarTodosEvaluacionOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM EvaluacionEditorial tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
