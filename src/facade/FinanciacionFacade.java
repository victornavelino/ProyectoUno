/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.FinanciacionJpaController;
import controladores.exceptions.NonexistentEntityException;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Panchi
 */
public class FinanciacionFacade {
   

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    FinanciacionJpaController financiacionJpaController = new FinanciacionJpaController(emf);
    private static facade.FinanciacionFacade instance = null;

    protected FinanciacionFacade() {
    }

    public static facade.FinanciacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new facade.FinanciacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Financiacion financiacion) {
//        if (!existeFinanciacionConNumeroDocumentoIdentidad(Financiacion.getPersona().getDocumentoIdentidad().getTipoDocumento(),
  //              Financiacion.getPersona().getDocumentoIdentidad().getNumero())) {
            new FinanciacionJpaController(emf).create(financiacion);
    //    } else {
      //      System.out.println("Alta incorrecta. Ya existe un Financiacion con ese número de documento de identidad");
     //   }
    }

    public void modificar(Financiacion financiacion) {
        try {
            new FinanciacionJpaController(emf).edit(financiacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Financiacion financiacion) {
        try {
            new FinanciacionJpaController(emf).destroy(financiacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Financiacion buscar(long id) {
        return new FinanciacionJpaController(emf).findFinanciacion(id);
    }

    public Financiacion getUltimoFinanciacion() {
        Query quFinanciacion = em.createQuery("SELECT i FROM Financiacion i");
        List<Financiacion> financiaciones = quFinanciacion.getResultList();
        if (!financiaciones.isEmpty()) {
            return financiaciones.get(financiaciones.size() - 1);
        } else {
            return null;
        }
    }

    public List<Financiacion> getTodosFinanciacion() {
        Query quTodosFinanciacion = em.createQuery("SELECT i FROM Financiacion i "
                + "ORDER BY i.descripcion");
        return quTodosFinanciacion.getResultList();
    }
        public List<Financiacion> getTodosFinanciacionDistinct() {
        Query quTodosFinanciacion = em.createQuery("SELECT DISTINCT i.descripcion FROM Financiacion i "
                + "ORDER BY i.descripcion");
        return quTodosFinanciacion.getResultList();
    }
      public List<FinanciacionPfip> getFinanciacionPorDescripcion(String descripcion) {
        Query quTodosFinanciacion = em.createQuery("SELECT i FROM Financiacion i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quTodosFinanciacion.getResultList();
    }
           public List<Financiacion> getFinanciacionPorDescripcion2(String descripcion) {
        Query quTodosFinanciacion = em.createQuery("SELECT i FROM Financiacion i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quTodosFinanciacion.getResultList();
    }


}
