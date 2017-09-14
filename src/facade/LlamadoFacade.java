/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.LlamadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.Llamado;
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
public class LlamadoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    LlamadoJpaController llamadoJpaController = new LlamadoJpaController(emf);
    private static LlamadoFacade instance = null;

    protected LlamadoFacade() {
    }

    public static LlamadoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new LlamadoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Llamado llamado) {
        new LlamadoJpaController(emf).create(llamado);
    }
    
    public void modificacion(Llamado llamado) {        
        try {
            new LlamadoJpaController(emf).edit(llamado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LlamadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LlamadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminacion(Llamado llamado){
        try {
            new LlamadoJpaController(emf).destroy(llamado.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LlamadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            
    
    public List<Llamado> llamadoTraerTodosOrdenadosFecha(){
        Query quLlamados = em.createQuery("SELECT l FROM Llamado l ORDER BY l.fecha");
        return quLlamados.getResultList();
    }
    
    public List<Llamado> llamadoxDescripcion(String llamado){
        Query quDes = em.createQuery("SELECT l FROM Llamado l "
                + "WHERE l.descripcion LIKE :des");
        quDes.setParameter("des", "%" + llamado + "%");
        return  quDes.getResultList();        
    }
    
    public Llamado buscar(long id) {
        return llamadoJpaController.findLlamado(id);
    }
    
}
