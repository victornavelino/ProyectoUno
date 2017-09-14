/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author Panchi
 */


import controladores.NivelFinanciacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.NivelFinanciacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author wbivanco
 */
public class NivelFinanciacionFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    NivelFinanciacionJpaController nivelFinanciacionJpaController = new NivelFinanciacionJpaController(emf);
    private static NivelFinanciacionFacade instance = null;

    protected NivelFinanciacionFacade() {
    }

    public static NivelFinanciacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NivelFinanciacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(NivelFinanciacion nivelFinanciacion) {
        new NivelFinanciacionJpaController(emf).create(nivelFinanciacion);
    }
    
    public void modificacion(NivelFinanciacion nivelFinanciacion) {        
        try {
            new NivelFinanciacionJpaController(emf).edit(nivelFinanciacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NivelFinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NivelFinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    public List<NivelFinanciacion> nivelFinanciacionxDescripcion(String nivelFinanciacion){
        Query quDes = em.createQuery("SELECT l FROM NivelFinanciacion l "
                + "WHERE l.descripcion LIKE :des");
        quDes.setParameter("des", "%" + nivelFinanciacion + "%");
        return  quDes.getResultList();        
    }
    
    public NivelFinanciacion buscar(long id) {
        return nivelFinanciacionJpaController.findNivelFinanciacion(id);
    }
    public List<NivelFinanciacion> getTodosNivelFinanciacion() {
        Query quTodasNivelFinanciacion = em.createQuery("SELECT nf FROM NivelFinanciacion nf ORDER BY nf.descripcion");
        return quTodasNivelFinanciacion.getResultList();
    }
    public void borrar(NivelFinanciacion n ){
        try {
            new NivelFinanciacionJpaController(emf).destroy(n.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NivelFinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
