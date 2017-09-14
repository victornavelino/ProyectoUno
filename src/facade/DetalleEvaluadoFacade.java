/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueEvaluadoJpaController;
import controladores.BloqueJpaController;
import controladores.CargoJpaController;
import controladores.ContenidoBloqueJpaController;
import controladores.DetalleEvaluadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.proyecto.evaluacion.Bloque;
import entidades.proyecto.evaluacion.BloqueEvaluado;
import entidades.proyecto.evaluacion.ContenidoBloque;
import entidades.proyecto.evaluacion.DetalleEvaluado;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class DetalleEvaluadoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DetalleEvaluadoFacade instance = null;

    protected DetalleEvaluadoFacade() {
    }

    public static DetalleEvaluadoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DetalleEvaluadoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DetalleEvaluado detalleEvaluado) {
        new DetalleEvaluadoJpaController(emf).create(detalleEvaluado);
    }

    public void modificar(DetalleEvaluado detalleEvaluado) {
        try {
            new DetalleEvaluadoJpaController(emf).edit(detalleEvaluado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DetalleEvaluadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DetalleEvaluadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DetalleEvaluado buscar(long id) {
        Query quBuscar = em.createQuery("SELECT b FROM DetalleEvaluado b WHERE b.id=:id");
        quBuscar.setParameter("id", id);
        try {
            return (DetalleEvaluado) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            DetalleEvaluado detalleEvaluado = null;
            return detalleEvaluado;
        }

    }

    public List<DetalleEvaluado> getTodos() {
        Query quBuscar = em.createQuery("SELECT b FROM DetalleEvaluado b");
        return quBuscar.getResultList();
    }
        public List<DetalleEvaluado> getTodos(Bloque bloque) {
        Query quBuscar = em.createQuery("SELECT b FROM DetalleEvaluado b where b.contenidoBloque.bloque=:bloque");
        quBuscar.setParameter("bloque", bloque);
        return quBuscar.getResultList();
    }

    public void eliminar(DetalleEvaluado detalleEvaluado) {
        try {
            new DetalleEvaluadoJpaController(emf).destroy(detalleEvaluado.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DetalleEvaluadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 

}
