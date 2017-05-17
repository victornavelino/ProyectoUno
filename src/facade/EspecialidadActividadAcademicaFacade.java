/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EspecialidadActividadAcademicaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.EspecialidadActividadAcademica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class EspecialidadActividadAcademicaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EspecialidadActividadAcademicaJpaController especialidadActividadAcademicaJpaController = new EspecialidadActividadAcademicaJpaController(emf);

    private static EspecialidadActividadAcademicaFacade instance = null;

    protected EspecialidadActividadAcademicaFacade() {
    }

        public static EspecialidadActividadAcademicaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EspecialidadActividadAcademicaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EspecialidadActividadAcademica especialidadActividadAcademica) {
        new EspecialidadActividadAcademicaJpaController(emf).create(especialidadActividadAcademica);
    }

    public void modificar(EspecialidadActividadAcademica especialidadActividadAcademica) {
        try {
            new EspecialidadActividadAcademicaJpaController(emf).edit(especialidadActividadAcademica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecialidadActividadAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadActividadAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EspecialidadActividadAcademicaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecialidadActividadAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EspecialidadActividadAcademica> getTodosEspecialidadActividadAcademica() {
        Query quTodosEspecialidadActividadAcademica = em.createQuery("SELECT at FROM EspecialidadActividadAcademica at");
        return quTodosEspecialidadActividadAcademica.getResultList();
    }

    public List<EspecialidadActividadAcademica> getEspecialidadActividadAcademica(String descripcion) {
        Query quEspecialidadActividadAcademica = em.createQuery("SELECT at FROM EspecialidadActividadAcademica at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quEspecialidadActividadAcademica.getResultList();
    }

    public List filtrar(String descripcion) {
      EntityManager ema = emf.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT eai From EspecialidadActividadAcademica eai "
                + "WHERE eai.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

}
