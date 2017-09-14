/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PropiedadJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Proyecto;
import entidades.proyecto.resultado.Industrial;
import entidades.proyecto.resultado.Intelectual;
import entidades.proyecto.resultado.Propiedad;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
public class PropiedadFacade {
    private static PropiedadFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

    protected PropiedadFacade() {
    }

    public static PropiedadFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PropiedadFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Propiedad propiedad) {
        new PropiedadJpaController(emf).create(propiedad);
    }

    public void modificar(Propiedad propiedad) {
        try {
            new PropiedadJpaController(emf).edit(propiedad);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PropiedadFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PropiedadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Propiedad> getTodas() {
        return new PropiedadJpaController(emf).findPropiedadEntities();
    }

    public List<Intelectual> getIntelectuales() {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Intelectual p");
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<Intelectual>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<Intelectual>();
        }
    }
    
    public List<Industrial> getIndustriales() {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Industrial p");
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<Industrial>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<Industrial>();
        }
    }
    
    public List<Intelectual> getIntelectualesProyecto(Proyecto proyecto){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT pint FROM Propiedad pint, IN (pint.proyectos) pr WHERE TYPE(pint) = Intelectual AND  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
        
    }
    
    public List<Industrial> getIndustrialesProyecto(Proyecto proyecto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT pind FROM Propiedad pind, IN (pind.proyectos) pr WHERE TYPE(pind) = Industrial AND  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
    }
    
    public List<Intelectual> getIntelectualesProyecto(ProyectoVinculacion proyectoVinculacion){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT pint FROM Propiedad pint, IN (pint.proyectosVinculacion) pr WHERE TYPE(pint) = Intelectual AND  pr = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
        
    }
    
    public List<Industrial> getIndustrialesProyectoVinculacion(ProyectoVinculacion proyectoVinculacion){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT pind FROM Propiedad pind, IN (pind.proyectosVinculacion) pr WHERE TYPE(pind) = Industrial AND  pr = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
    }
    public List<Intelectual> buscarIntelectuales(String intelectual){
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT i FROM Intelectual i"
                +" WHERE i.titulo LIKE '%"+intelectual+ "%'");
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<Intelectual>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<Intelectual>();
        }
    }
    
    public List<Industrial> buscarIndustriales(String industrial){
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT i FROM Industrial i"
                +" WHERE i.titulo LIKE '%"+industrial+ "%'");
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<Industrial>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<Industrial>();
        }
    }
    

    public Propiedad buscar(Long id) {
        return new PropiedadJpaController(emf).findPropiedad(id);
    }

    public void eliminar(Long id) {
        try{
        new PropiedadJpaController(emf).destroy(id);
        }catch(NonexistentEntityException ex) {
            Logger.getLogger(PropiedadFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
