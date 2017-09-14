/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.TituloPosgradoJpaController;
import controladores.exceptions.NonexistentEntityException;
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
public class TituloPosgradoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    TituloPosgradoJpaController tituloPosgradoJpaController = new TituloPosgradoJpaController(emf);

    private static TituloPosgradoFacade instance = null;

    protected TituloPosgradoFacade() {
    }

        public static TituloPosgradoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TituloPosgradoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TituloPosgrado TituloPosgrado) {
        new TituloPosgradoJpaController(emf).create(TituloPosgrado);
    }

    public void modificar(TituloPosgrado TituloPosgrado) {
        try {
            new TituloPosgradoJpaController(emf).edit(TituloPosgrado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloPosgradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TituloPosgradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TituloPosgradoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TituloPosgradoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TituloPosgrado> getTodosTituloPosgrado() {
        Query quTodosTituloPosgrado = em.createQuery("SELECT at FROM TituloPosgrado at");
        return quTodosTituloPosgrado.getResultList();
    }

    public List<TituloPosgrado> getTituloPosgrado(String descripcion) {
        Query quTituloPosgrado = em.createQuery("SELECT at FROM TituloPosgrado at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quTituloPosgrado.getResultList();
    }
     public List<TituloPosgrado> filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM TituloPosgrado i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

}
