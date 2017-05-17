/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.persona.investigador.curso;

import controladores.FacultadAsignaturaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.FacultadAsignatura;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class FacultadAsignaturaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static FacultadAsignaturaFacade instance = null;

    protected FacultadAsignaturaFacade() {
    }

    public static FacultadAsignaturaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FacultadAsignaturaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FacultadAsignatura facultadAsignatura) {
        new FacultadAsignaturaJpaController(emf).create(facultadAsignatura);
    }

    public void modificar(FacultadAsignatura facultadAsignatura) {
        try {
            new FacultadAsignaturaJpaController(emf).edit(facultadAsignatura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FacultadAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FacultadAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FacultadAsignatura buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM FacultadAsignatura tt WHERE tt.id="
                + id);
        try {
            return (FacultadAsignatura) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            FacultadAsignatura facultadAsignatura = null;
            return facultadAsignatura;
        }

    }

    public List<FacultadAsignatura> listarFacultadAsignatura(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM FacultadAsignatura tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<FacultadAsignatura> listarTodosFacultadAsignatura() {
        Query quBuscar = em.createQuery("SELECT tt FROM FacultadAsignatura tt");
        return quBuscar.getResultList();
    }

    public List<FacultadAsignatura> listarTodosFacultadAsignaturaOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM FacultadAsignatura tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
