/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.FormacionAcademicaOtraJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaOtra;
import entidades.titulo.TituloOtro;
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
public class FormacionAcademicaOtraFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    FormacionAcademicaOtraJpaController formacionAcademicaOtraJpaController = new FormacionAcademicaOtraJpaController(emf);

    private static FormacionAcademicaOtraFacade instance = null;

    protected FormacionAcademicaOtraFacade() {
    }

        public static FormacionAcademicaOtraFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FormacionAcademicaOtraFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FormacionAcademicaOtra FormacionAcademicaOtra) {
        new FormacionAcademicaOtraJpaController(emf).create(FormacionAcademicaOtra);
    }

    public void modificar(FormacionAcademicaOtra FormacionAcademicaOtra) {
        try {
            new FormacionAcademicaOtraJpaController(emf).edit(FormacionAcademicaOtra);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaOtraFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormacionAcademicaOtraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new FormacionAcademicaOtraJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaOtraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<FormacionAcademicaOtra> getTodosFormacionAcademicaOtra() {
        Query quTodosFormacionAcademicaOtra = em.createQuery("SELECT at FROM FormacionAcademicaOtra at");
        return quTodosFormacionAcademicaOtra.getResultList();
    }

    public List<FormacionAcademicaOtra> getFormacionAcademicaOtra(String descripcion) {
        Query quFormacionAcademicaOtra = em.createQuery("SELECT at FROM FormacionAcademicaOtra at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quFormacionAcademicaOtra.getResultList();
    }

    public List<FormacionAcademicaOtra> getFormacionAcademicaOtra(TituloOtro tituloOtro) {
         Query quFormacionAcademicaOtra = em.createQuery("SELECT at FROM FormacionAcademicaOtra at "
                + "WHERE at.tituloOtro=:tituloOtro");
         quFormacionAcademicaOtra.setParameter("tituloOtro", tituloOtro);
        return quFormacionAcademicaOtra.getResultList();
    }

}
