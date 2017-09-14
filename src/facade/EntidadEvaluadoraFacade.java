/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EntidadEvaluadoraJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.EntidadEvaluadora;
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
public class EntidadEvaluadoraFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EntidadEvaluadoraJpaController entidadEvaluadoraJpaController = new EntidadEvaluadoraJpaController(emf);

    private static EntidadEvaluadoraFacade instance = null;

    protected EntidadEvaluadoraFacade() {
    }

        public static EntidadEvaluadoraFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EntidadEvaluadoraFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EntidadEvaluadora EntidadEvaluadora) {
        new EntidadEvaluadoraJpaController(emf).create(EntidadEvaluadora);
    }

    public void modificar(EntidadEvaluadora EntidadEvaluadora) {
        try {
            new EntidadEvaluadoraJpaController(emf).edit(EntidadEvaluadora);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadEvaluadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntidadEvaluadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EntidadEvaluadoraJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntidadEvaluadoraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EntidadEvaluadora> getTodosEntidadEvaluadora() {
        Query quTodosEntidadEvaluadora = em.createQuery("SELECT at FROM EntidadEvaluadora at");
        return quTodosEntidadEvaluadora.getResultList();
    }

    public List<EntidadEvaluadora> getEntidadEvaluadora(String descripcion) {
        Query quEntidadEvaluadora = em.createQuery("SELECT at FROM EntidadEvaluadora at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quEntidadEvaluadora.getResultList();
    }

    public List filtrar(String descripcion) {
       Query quBuscar = em.createQuery("SELECT i FROM EntidadEvaluadora i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();  
    }

}
