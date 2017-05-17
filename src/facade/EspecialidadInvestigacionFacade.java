/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EspecialidadInvestigacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.EspecialidadInvestigacion;

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
public class EspecialidadInvestigacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EspecialidadInvestigacionJpaController especialidadInvestigacionJpaController = new EspecialidadInvestigacionJpaController(emf);

    private static EspecialidadInvestigacionFacade instance = null;

    protected EspecialidadInvestigacionFacade() {
    }

        public static EspecialidadInvestigacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EspecialidadInvestigacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EspecialidadInvestigacion especialidadInvestigacion) {
        new EspecialidadInvestigacionJpaController(emf).create(especialidadInvestigacion);
    }

    public void modificar(EspecialidadInvestigacion especialidadInvestigacion) {
        try {
            new EspecialidadInvestigacionJpaController(emf).edit(especialidadInvestigacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecialidadInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EspecialidadInvestigacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecialidadInvestigacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EspecialidadInvestigacion> getTodosEspecialidadInvestigacion() {
        Query quTodosEspecialidadInvestigacion = em.createQuery("SELECT at FROM EspecialidadInvestigacion at");
        return quTodosEspecialidadInvestigacion.getResultList();
    }

    public List<EspecialidadInvestigacion> getEspecialidadInvestigacion(String descripcion) {
        Query quEspecialidadInvestigacion = em.createQuery("SELECT at FROM EspecialidadInvestigacion at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quEspecialidadInvestigacion.getResultList();
    }

    public List<EspecialidadInvestigacion> filtrar(String descripcion) {
       EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT ei From EspecialidadInvestigacion ei "
                + "WHERE ei.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

}
