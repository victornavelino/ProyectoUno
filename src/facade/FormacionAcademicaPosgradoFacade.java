/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.FormacionAcademicaPosgradoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
import entidades.titulo.TituloPosgrado;
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
public class FormacionAcademicaPosgradoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    FormacionAcademicaPosgradoJpaController formacionAcademicaPosgradoJpaController = new FormacionAcademicaPosgradoJpaController(emf);

    private static FormacionAcademicaPosgradoFacade instance = null;

    protected FormacionAcademicaPosgradoFacade() {
    }

        public static FormacionAcademicaPosgradoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FormacionAcademicaPosgradoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FormacionAcademicaPosgrado FormacionAcademicaPosgrado) {
        new FormacionAcademicaPosgradoJpaController(emf).create(FormacionAcademicaPosgrado);
    }

    public void modificar(FormacionAcademicaPosgrado FormacionAcademicaPosgrado) {
        try {
            new FormacionAcademicaPosgradoJpaController(emf).edit(FormacionAcademicaPosgrado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaPosgradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormacionAcademicaPosgradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new FormacionAcademicaPosgradoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionAcademicaPosgradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<FormacionAcademicaPosgrado> getTodosFormacionAcademicaPosgrado() {
        Query quTodosFormacionAcademicaPosgrado = em.createQuery("SELECT at FROM FormacionAcademicaPosgrado at");
        return quTodosFormacionAcademicaPosgrado.getResultList();
    }

    public List<FormacionAcademicaPosgrado> getFormacionAcademicaPosgrado(String descripcion) {
        Query quFormacionAcademicaPosgrado = em.createQuery("SELECT at FROM FormacionAcademicaPosgrado at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quFormacionAcademicaPosgrado.getResultList();
    }
    
    public List<FormacionAcademicaPosgrado> getFormacionAcademicaPosgrado(TituloPosgrado tituloPosgrado) {
        Query quFormacionAcademicaPosgrado = em.createQuery("SELECT at FROM FormacionAcademicaPosgrado at "
                + "WHERE at.tituloPosgrado=:tituloPosgrado");
        quFormacionAcademicaPosgrado.setParameter("tituloPosgrado", tituloPosgrado);
        return quFormacionAcademicaPosgrado.getResultList();
    }

}
