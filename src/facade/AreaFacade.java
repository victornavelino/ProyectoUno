/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;


import controladores.AreasJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.Areas;
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
public class AreaFacade {
    
    private static AreaFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    
    protected AreaFacade() {
    }

    public static AreaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AreaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Areas area) {
        new AreasJpaController(emf).create(area);
    }
    
    public void modificar(Areas area) {
        try {
            new AreasJpaController(emf).edit(area);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AreaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AreaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Areas> getTodas() {
        return new AreasJpaController(emf).findAreasEntities();
    }
    
    
    public Areas buscar(Long id) {
        return new AreasJpaController(emf).findAreas(id);
    }
    public void eliminar(Long id) {
        try{
        new AreasJpaController(emf).destroy(id);
        }catch(NonexistentEntityException ex) {
            Logger.getLogger(AreaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        emf.close();
    }
    public List<Areas> buscar(String nombre) {
        Query quBuscarArea = em.createQuery("SELECT a FROM Areas a "
                + "WHERE a.nombre LIKE '%" + nombre + "%'");
        return quBuscarArea.getResultList();
    }
}
