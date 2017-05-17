/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.ProgramaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Programa;
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
public class ProgramaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ProgramaJpaController programaJpaController = new ProgramaJpaController(emf);

    private static ProgramaFacade instance = null;

    protected ProgramaFacade() {
    }

        public static ProgramaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProgramaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Programa Programa) {
        new ProgramaJpaController(emf).create(Programa);
    }

    public void modificar(Programa Programa) {
        try {
            new ProgramaJpaController(emf).edit(Programa);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProgramaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProgramaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new ProgramaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProgramaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Programa> getTodosPrograma() {
        Query quTodosPrograma = em.createQuery("SELECT at FROM Programa at");
        return quTodosPrograma.getResultList();
    }

    public List<Programa> getPrograma(String descripcion) {
        Query quPrograma = em.createQuery("SELECT at FROM Programa at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quPrograma.getResultList();
    }

    public List filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT i FROM Programa i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList(); 
    }

}
