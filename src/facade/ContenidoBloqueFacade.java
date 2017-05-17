/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueEvaluadoJpaController;
import controladores.BloqueJpaController;
import controladores.CargoJpaController;
import controladores.ContenidoBloqueJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.proyecto.evaluacion.Bloque;
import entidades.proyecto.evaluacion.BloqueEvaluado;
import entidades.proyecto.evaluacion.ContenidoBloque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class ContenidoBloqueFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static ContenidoBloqueFacade instance = null;

    protected ContenidoBloqueFacade() {
    }

    public static ContenidoBloqueFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ContenidoBloqueFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ContenidoBloque contenidoBloque) {
        new ContenidoBloqueJpaController(emf).create(contenidoBloque);
    }

    public void modificar(ContenidoBloque contenidoBloque) {
        try {
            new ContenidoBloqueJpaController(emf).edit(contenidoBloque);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ContenidoBloqueFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ContenidoBloqueFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ContenidoBloque buscar(long id) {
        Query quBuscar = em.createQuery("SELECT b FROM ContenidoBloque b WHERE b.id=:id");
        quBuscar.setParameter("id", id);
        try {
            return (ContenidoBloque) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            ContenidoBloque contenidoBloque = null;
            return contenidoBloque;
        }

    }

    public List<ContenidoBloque> getTodos() {
        Query quBuscar = em.createQuery("SELECT b FROM ContenidoBloque b");
        return quBuscar.getResultList();
    }

    public List<ContenidoBloque> getTodos(Bloque bloque) {
        Query quBuscar = em.createQuery("SELECT b FROM ContenidoBloque b where b.bloque=:bloque");
        quBuscar.setParameter("bloque", bloque);
        return quBuscar.getResultList();
    }

    public void eliminar(ContenidoBloque contenidoBloque) {
        try { 
            new ContenidoBloqueJpaController(emf).destroy(contenidoBloque.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ContenidoBloqueFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
