/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TipoAsignaturaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.TipoAsignatura;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class TipoAsignaturaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static TipoAsignaturaFacade instance = null;

    protected TipoAsignaturaFacade() {
    }

    public static TipoAsignaturaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoAsignaturaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoAsignatura tipoAsignatura) {
        new TipoAsignaturaJpaController(emf).create(tipoAsignatura);
    }

    public void modificar(TipoAsignatura tipoAsignatura) {
        try {
            new TipoAsignaturaJpaController(emf).edit(tipoAsignatura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoAsignatura buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoAsignatura tt WHERE tt.id="
                + id);
        try {
            return (TipoAsignatura) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            TipoAsignatura tipoAsignatura = null;
            return tipoAsignatura;
        }

    }

    public List<TipoAsignatura> listarTipoAsignatura(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoAsignatura tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<TipoAsignatura> listarTodosTipoAsignatura() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoAsignatura tt");
        return quBuscar.getResultList();
    }

    public List<TipoAsignatura> listarTodosTipoAsignaturaOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoAsignatura tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public List<TipoAsignatura> filtrar(String descripcion) {
          EntityManager ema = emf.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT ta From TipoAsignatura ta "
                + "WHERE ta.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new TipoAsignaturaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
