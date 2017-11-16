/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DestinoEditorialJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.DestinoEditorial;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author wbivanco
 */
public class DestinoEditorialFacade {
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DestinoEditorialFacade instance = null;

    protected DestinoEditorialFacade() {
    }

    public static DestinoEditorialFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DestinoEditorialFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DestinoEditorial destino) {
        new DestinoEditorialJpaController(emf).create(destino);
    }
    
    public void modificar(DestinoEditorial destino) {
        try {
            new DestinoEditorialJpaController(emf).edit(destino);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DestinoEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DestinoEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    public List<DestinoEditorial> listarTodosDestinoOrdenados() {
        Query quBuscar = em.createQuery("SELECT d FROM editorial_destinoEditorial d ORDER BY d.nombre");
        return quBuscar.getResultList();
    }   

    public void eliminar(long id) {
        try {
            new DestinoEditorialJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DestinoEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


