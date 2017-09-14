/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.TituloOtroJpaController;
import controladores.exceptions.NonexistentEntityException;
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
public class TituloOtroFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    TituloOtroJpaController tituloOtroJpaController = new TituloOtroJpaController(emf);

    private static TituloOtroFacade instance = null;

    protected TituloOtroFacade() {
    }

        public static TituloOtroFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TituloOtroFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TituloOtro TituloOtro) {
        new TituloOtroJpaController(emf).create(TituloOtro);
    }

    public void modificar(TituloOtro TituloOtro) {
        try {
            new TituloOtroJpaController(emf).edit(TituloOtro);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloOtroFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TituloOtroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TituloOtroJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloOtroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TituloOtro> getTodosTituloOtro() {
        Query quTodosTituloOtro = em.createQuery("SELECT at FROM TituloOtro at");
        return quTodosTituloOtro.getResultList();
    }

    public List<TituloOtro> getTituloOtro(String descripcion) {
        Query quTituloOtro = em.createQuery("SELECT at FROM TituloOtro at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quTituloOtro.getResultList();
    }
     public List<TituloOtro> filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM TituloOtro i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

}
