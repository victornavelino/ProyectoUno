/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ModificacionPresupuestoJpaController;
import controladores.PresupuestoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.ModificacionPresupuesto;
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
 * @author Walter
 */
public class ModificacionPresupuestoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ModificacionPresupuestoJpaController modificacionPresupuestoJpaController = new ModificacionPresupuestoJpaController(emf);

    private static ModificacionPresupuestoFacade instance = null;

    protected ModificacionPresupuestoFacade() {
    }

    public static ModificacionPresupuestoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ModificacionPresupuestoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaModificacionPresupuesto(ModificacionPresupuesto modificacionPresupuesto){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();    
        new ModificacionPresupuestoJpaController(emf).create(modificacionPresupuesto);
    }
    
    public void eliminarModificacionPresupuesto(long id){
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            em = emf.createEntityManager();
            new ModificacionPresupuestoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModificacionPresupuestoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ModificacionPresupuesto> modificacionesxPresupuesto(Presupuesto presu){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        try{
            Query quModi = em.createQuery("SELECT mp FROM ModificacionPresupuesto"
                    + " mp WHERE mp.presupuesto=:pre");
            quModi.setParameter("pre", presu);
            return quModi.getResultList();
        } catch(NoResultException e){
            return null;
        }
    }
}
