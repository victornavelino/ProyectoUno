/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.UnidadAcademicaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
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
public class UnidadAcademicaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    UnidadAcademicaJpaController unidadAcademicaJpaController = new UnidadAcademicaJpaController(emf);

    private static UnidadAcademicaFacade instance = null;

    protected UnidadAcademicaFacade() {
    }

        public static UnidadAcademicaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new UnidadAcademicaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(UnidadAcademica unidadAcademica) {
        new UnidadAcademicaJpaController(emf).create(unidadAcademica);
    }

    public void modificar(UnidadAcademica unidadAcademica) {
        try {
            new UnidadAcademicaJpaController(emf).edit(unidadAcademica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UnidadAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new UnidadAcademicaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadAcademicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UnidadAcademica> getTodasUnidadAcademica() {
        Query quTodasUnidadAcademica = em.createQuery("SELECT ua FROM UnidadAcademica ua ORDER BY ua.descripcion");
        return quTodasUnidadAcademica.getResultList();
    }

    public List<UnidadEjecutora> getUnidadesEjecutoras(UnidadAcademica unidadAcademica) {
        Query quBuscar = em.createQuery("SELECT ue FROM UnidadEjecutora ue "
                + "WHERE ue.unidadAcademica.id=:param");
        quBuscar.setParameter("param", unidadAcademica.getId());
        return quBuscar.getResultList();
    }

    public UnidadAcademica buscar(long parseLong) {
      return  new UnidadAcademicaJpaController(emf).findUnidadAcademica(parseLong);
    }

    public List filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM UnidadAcademica i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }   
}
