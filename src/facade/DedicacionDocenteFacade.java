/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DedicacionDocenteJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.DedicacionDocente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class DedicacionDocenteFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DedicacionDocenteFacade instance = null;

    protected DedicacionDocenteFacade() {
    }

    public static DedicacionDocenteFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DedicacionDocenteFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DedicacionDocente dedicacionDocente) {
        new DedicacionDocenteJpaController(emf).create(dedicacionDocente);
    }

    public DedicacionDocente buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionDocente tt WHERE tt.id="
                + id);
        try {
            return (DedicacionDocente) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            DedicacionDocente dedicacionDocente = null;
            return dedicacionDocente;
        }

    }

    public List<DedicacionDocente> listarDedicacionDocente(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionDocente tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<DedicacionDocente> listarTodosDedicacionDocente() {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionDocente tt");
        return quBuscar.getResultList();
    }

    public List<DedicacionDocente> listarTodosDedicacionDocenteOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM DedicacionDocente tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public void modificar(DedicacionDocente dedicacionDocente) {
        try {
            new DedicacionDocenteJpaController(emf).edit(dedicacionDocente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DedicacionDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DedicacionDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List filtrar(String descripcion) {
          Query quBuscar = em.createQuery("SELECT cd From DedicacionDocente cd "
                + "WHERE cd.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new DedicacionDocenteJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DedicacionDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
