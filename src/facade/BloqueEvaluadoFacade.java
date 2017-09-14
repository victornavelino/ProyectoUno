/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueEvaluadoJpaController;
import controladores.BloqueJpaController;
import controladores.CargoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.proyecto.evaluacion.Bloque;
import entidades.proyecto.evaluacion.BloqueEvaluado;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class BloqueEvaluadoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static BloqueEvaluadoFacade instance = null;

    protected BloqueEvaluadoFacade() {
    }

    public static BloqueEvaluadoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BloqueEvaluadoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(BloqueEvaluado bloqueEvaluado) {
        new BloqueEvaluadoJpaController(emf).create(bloqueEvaluado);
    }

    public void modificar(BloqueEvaluado bloqueEvaluado) {
        try {
            new BloqueEvaluadoJpaController(emf).edit(bloqueEvaluado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BloqueEvaluadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BloqueEvaluadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BloqueEvaluado buscar(long id) {
        Query quBuscar = em.createQuery("SELECT b FROM BloqueEvaluado b WHERE b.id=:id");
        quBuscar.setParameter("id", id);
        try {
            return (BloqueEvaluado) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            BloqueEvaluado bloqueEvaluado = null;
            return bloqueEvaluado;
        }

    }

    public List<BloqueEvaluado> getTodos() {
        Query quBuscar = em.createQuery("SELECT b FROM BloqueEvaluado b");
        return quBuscar.getResultList();
    }

    public void eliminar(BloqueEvaluado bloqueEvaluado) {
        try {
            new BloqueEvaluadoJpaController(emf).destroy(bloqueEvaluado.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BloqueEvaluadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 

}
