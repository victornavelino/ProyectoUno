/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DedicacionConduccionJpaController;
import controladores.exceptions.NonexistentEntityException;

import entidades.persona.investigador.actividadConduccion.DedicacionConduccion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class DedicacionConduccionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DedicacionConduccionFacade instance = null;

    protected DedicacionConduccionFacade() {
    }

    public static DedicacionConduccionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DedicacionConduccionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DedicacionConduccion dedicacionDocente) {
        new DedicacionConduccionJpaController(emf).create(dedicacionDocente);
    }

    public DedicacionConduccion buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionConduccion tt WHERE tt.id="
                + id);
        try {
            return (DedicacionConduccion) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            DedicacionConduccion dedicacionDocente = null;
            return dedicacionDocente;
        }

    }

    public List<DedicacionConduccion> listarDedicacionConduccion(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionConduccion tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<DedicacionConduccion> listarTodosDedicacionConduccion() {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionConduccion tt");
        return quBuscar.getResultList();
    }

    public List<DedicacionConduccion> listarTodosDedicacionConduccionOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionConduccion tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public void modificar(DedicacionConduccion dedicacionDocente) {
        try {
            new DedicacionConduccionJpaController(emf).edit(dedicacionDocente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DedicacionConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DedicacionConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DedicacionConduccion> filtrar(String descripcion) {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT dc From DedicacionConduccion dc "
                + "WHERE dc.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new DedicacionConduccionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DedicacionConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
