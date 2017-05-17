/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;


import controladores.PaseJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.Pase;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
public class PaseFacade {
    
    private static PaseFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    
    protected PaseFacade() {
    }

    public static PaseFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PaseFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Pase pase) {
        new PaseJpaController(emf).create(pase);
    }
    
    public void modificar(Pase pase) {
        try {
            new PaseJpaController(emf).edit(pase);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Pase> getTodas() {
        return new PaseJpaController(emf).findPaseEntities();
    }
    
    
    public Pase buscar(Long id) {
        return new PaseJpaController(emf).findPase(id);
    }
    public void eliminar(Long id) {
        try{
        new PaseJpaController(emf).destroy(id);
        }catch(NonexistentEntityException ex) {
            Logger.getLogger(PaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        emf.close();
    }
    public List<Pase> buscar(String nombre) {
        Query quBuscarArea = em.createQuery("SELECT a FROM Pase a "
                + "WHERE o.nombre LIKE '%" + nombre + "%'");
        return quBuscarArea.getResultList();
    }
}
