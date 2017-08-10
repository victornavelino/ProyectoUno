/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.TituloGradoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.persona.investigador.Investigador;
import entidades.titulo.TituloGrado;
import java.util.ArrayList;
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
public class TituloGradoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    TituloGradoJpaController tituloGradoJpaController = new TituloGradoJpaController(emf);

    private static TituloGradoFacade instance = null;

    protected TituloGradoFacade() {
    }

        public static TituloGradoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TituloGradoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TituloGrado TituloGrado) {
        new TituloGradoJpaController(emf).create(TituloGrado);
    }

    public void modificar(TituloGrado TituloGrado) {
        try {
            new TituloGradoJpaController(emf).edit(TituloGrado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloGradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TituloGradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TituloGradoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloGradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TituloGrado> getTodosTituloGrado() {
        Query quTodosTituloGrado = em.createQuery("SELECT at FROM TituloGrado at");
        return quTodosTituloGrado.getResultList();
    }

    public TituloGrado getTituloGrado (Long id){
        TituloGrado tituloGrado;
        tituloGrado = new TituloGradoJpaController(emf).findTituloGrado(id);
        return tituloGrado;
    }
    public List<TituloGrado> getTituloGrado(String descripcion) {
        Query quTituloGrado = em.createQuery("SELECT at FROM TituloGrado at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quTituloGrado.getResultList();
    }
    public List<FormacionAcademicaGrado> getInvestigadoresAsociados(TituloGrado tituloGrado){
        
        Query quTituloGrado = em.createQuery("SELECT at FROM FormacionAcademicaGrado at "
                + "WHERE at.tituloGrado = :titulo");
        quTituloGrado.setParameter("titulo", tituloGrado);
        return quTituloGrado.getResultList();
    }
      public List<TituloGrado> filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM TituloGrado i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

}
