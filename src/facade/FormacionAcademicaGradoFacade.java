/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.FormacionAcademicaGradoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.titulo.TituloGrado;
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
public class FormacionAcademicaGradoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    FormacionAcademicaGradoJpaController formacionAcademicaGradoJpaController = new FormacionAcademicaGradoJpaController(emf);

    private static FormacionAcademicaGradoFacade instance = null;

    protected FormacionAcademicaGradoFacade() {
    }

        public static FormacionAcademicaGradoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FormacionAcademicaGradoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FormacionAcademicaGrado FormacionAcademicaGrado) {
        new FormacionAcademicaGradoJpaController(emf).create(FormacionAcademicaGrado);
    }

    public void modificar(FormacionAcademicaGrado FormacionAcademicaGrado) {
        try {
            new FormacionAcademicaGradoJpaController(emf).edit(FormacionAcademicaGrado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaGradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormacionAcademicaGradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new FormacionAcademicaGradoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaGradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<FormacionAcademicaGrado> getTodosFormacionAcademicaGrado() {
        Query quTodosFormacionAcademicaGrado = em.createQuery("SELECT at FROM FormacionAcademicaGrado at");
        return quTodosFormacionAcademicaGrado.getResultList();
    }

    public List<FormacionAcademicaGrado> getFormacionAcademicaGrado(String descripcion) {
        Query quFormacionAcademicaGrado = em.createQuery("SELECT at FROM FormacionAcademicaGrado at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quFormacionAcademicaGrado.getResultList();
    }

    public List<FormacionAcademicaGrado> getFormacionAcademicaGrado(TituloGrado tituloGrado) {
        Query quFormacionAcademicaGrado = em.createQuery("SELECT at FROM FormacionAcademicaGrado at "
                + "WHERE at.tituloGrado=:tituloGrado");
        quFormacionAcademicaGrado.setParameter("tituloGrado", tituloGrado);
        return quFormacionAcademicaGrado.getResultList();
    }

}
