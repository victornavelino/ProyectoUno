/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PresupuestoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.Presupuesto;
import entidades.proyecto.Proyecto;
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
public class PresupuestoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    PresupuestoJpaController presupuestoJpaController = new PresupuestoJpaController(emf);

    private static PresupuestoFacade instance = null;

    protected PresupuestoFacade() {
    }

    public static PresupuestoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PresupuestoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaPresupuesto(Presupuesto presupuesto){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();    
        new PresupuestoJpaController(emf).create(presupuesto);
    }
    
    public void modificarPresupuesto(Presupuesto presupuesto){
        try {        
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();    
            new PresupuestoJpaController(emf).edit(presupuesto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PresupuestoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PresupuestoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Presupuesto presupuestoxProyecto(Proyecto proye){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        try{
            Query quPresu = em.createQuery("SELECT p FROM Presupuesto p WHERE "
                    + "p.proyecto=:pro");
            quPresu.setParameter("pro", proye);
            return (Presupuesto) quPresu.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
}
