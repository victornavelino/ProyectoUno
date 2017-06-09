/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EvaluacionWinsipJpaController;
import controladores.WinsipJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.EvaluacionWinsip;
import entidades.categorizacion.Winsip;
import entidades.proyecto.Proyecto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class WinsipFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    WinsipJpaController winsipJpaController = new WinsipJpaController(emf);
    private static WinsipFacade instance = null;

    protected WinsipFacade() {
    }

    public static WinsipFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new WinsipFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Winsip winsip) {
        new WinsipJpaController(emf).create(winsip);
    }

    public void modificar(Winsip winsip) {
        try {
            new WinsipJpaController(emf).edit(winsip);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(WinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(WinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new WinsipJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(WinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Winsip buscar(long id) {
        Query quBuscar = em.createQuery("SELECT c FROM Winsip c WHERE "
                + "c.id=" + id);
        return (Winsip) quBuscar.getSingleResult();
    }

    public List<Winsip> listar(Proyecto proyecto) {
        Query quBuscar = em.createQuery("SELECT w FROM Winsip w WHERE "
                + "w.proyecto=:proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        try {
            return quBuscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<Winsip> getTodos() {
        return new WinsipJpaController(emf).findWinsipEntities();
    }

    public EvaluacionWinsip buscarEvaluacion(long id) {
        return new EvaluacionWinsipJpaController(emf).findEvaluacionWinsip(id);
    }

    public List<Winsip> getWinsipAño(List<Integer> listaAño) {
        Query quBuscar = em.createQuery("SELECT w FROM Winsip w WHERE w.año in :listaAño");
        quBuscar.setParameter("listaAño", listaAño);
        try {
            return quBuscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<Object[]> getListaReporteWinsip() {
        Query quBuscar = em.createQuery("SELECT w.proyecto.codigoIncentivos ,CONCAT(pa.investigador.persona.apellido ,', ' , pa.investigador.persona.nombre),"
                + " w.tipoInforme, w.año,w.fechaEvaluacion, w.evaluacionWinsip,  w.lugarEvaluacion, w, w.evaluacionProyecto.documento.nombreArchivo,"
                + "w.evaluacionIntegrantes.documento.nombreArchivo  FROM Winsip w, IN(w.proyecto.participaciones) pa  WHERE ((pa.fechaHasta IS NULL  OR pa.fechaHasta > CURRENT_DATE ) "
                + "OR pa.proyecto.fechaFinalizacion < CURRENT_DATE ) AND pa.rol.id = 1 ORDER BY  w.proyecto.codigoIncentivos");
        try {
            return quBuscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
    
    public List<Object[]> getListaReporteWinsip2() {
        Query quBuscar = em.createQuery("SELECT DISTINCT w , CONCAT(pa.investigador.persona.apellido ,', ' , pa.investigador.persona.nombre)" +
                " ,w.evaluacionProyecto.documento.nombreArchivo, w.evaluacionIntegrantes.documento.nombreArchivo"+
 " FROM Winsip w JOIN FETCH w.evaluacionesIntegrantes , IN(w.proyecto.participaciones) pa  WHERE ((pa.fechaHasta IS NULL  OR pa.fechaHasta > CURRENT_DATE )" +
  " OR pa.proyecto.fechaFinalizacion < CURRENT_DATE ) AND pa.rol.id = 1 ORDER BY  w.proyecto.codigoIncentivos");
        quBuscar.setHint("eclipselink.read-only", "true");
        quBuscar.setHint("eclipselink.jdbc.fetch-size", "256");
        try {
            return quBuscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<Winsip> getWinsipsAnioSeleccionado(int anio) {
        Query quBuscar = em.createQuery("SELECT w FROM Winsip w WHERE w.año=:anio");
        quBuscar.setParameter("anio", anio);
        try {
            return quBuscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public Winsip getEstado(int año) {
        Query quBuscar = em.createQuery("SELECT w FROM Winsip w WHERE w.año=:anio");
        quBuscar.setParameter("anio", año);
        try {
            return (Winsip) quBuscar.getResultList().get(0);
        } catch (Exception ex) {
            return new Winsip();
        }
    }

}
