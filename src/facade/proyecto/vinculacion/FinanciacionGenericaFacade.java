/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.proyecto.vinculacion;

import controladores.FinanciacionGenericaJpaController;
import facade.*;
import controladores.FinanciacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.Financiacion;

import entidades.proyecto.vinculacion.financiacion.FinanciacionGenerica;
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
public class FinanciacionGenericaFacade {
   

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    FinanciacionJpaController financiacionJpaController = new FinanciacionJpaController(emf);
    private static FinanciacionGenericaFacade instance = null;

    protected FinanciacionGenericaFacade() {
    }

    public static FinanciacionGenericaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FinanciacionGenericaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FinanciacionGenerica financiacion) {
//        if (!existeFinanciacionConNumeroDocumentoIdentidad(FinanciacionGenerica.getPersona().getDocumentoIdentidad().getTipoDocumento(),
  //              FinanciacionGenerica.getPersona().getDocumentoIdentidad().getNumero())) {
            new FinanciacionGenericaJpaController(emf).create(financiacion);
    //    } else {
      //      System.out.println("Alta incorrecta. Ya existe un FinanciacionGenerica con ese número de documento de identidad");
     //   }
    }

    public void modificar(FinanciacionGenerica financiacion) {
        try {
            new FinanciacionJpaController(emf).edit(financiacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(FinanciacionGenerica financiacion) {
        try {
            new FinanciacionJpaController(emf).destroy(financiacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Financiacion buscar(long id) {
        return new FinanciacionJpaController(emf).findFinanciacion(id);
    }

    public FinanciacionGenerica getUltimoFinanciacion() {
        Query quFinanciacion = em.createQuery("SELECT i FROM FinanciacionGenerica i");
        List<FinanciacionGenerica> financiaciones = quFinanciacion.getResultList();
        if (!financiaciones.isEmpty()) {
            return financiaciones.get(financiaciones.size() - 1);
        } else {
            return null;
        }
    }

    public List<FinanciacionGenerica> getTodosFinanciacion() {
        Query quTodosFinanciacion = em.createQuery("SELECT i FROM FinanciacionGenerica i "
                + "ORDER BY i.descripcion");
        return quTodosFinanciacion.getResultList();
    }
        public List<FinanciacionGenerica> getTodosFinanciacionDistinct() {
        Query quTodosFinanciacion = em.createQuery("SELECT DISTINCT i.descripcion FROM FinanciacionGenerica i "
                + "ORDER BY i.descripcion");
        return quTodosFinanciacion.getResultList();
    }
      public List<FinanciacionPfip> getFinanciacionPorDescripcion(String descripcion) {
        Query quTodosFinanciacion = em.createQuery("SELECT i FROM FinanciacionGenerica i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quTodosFinanciacion.getResultList();
    }
}
