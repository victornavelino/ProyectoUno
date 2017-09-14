/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.FormacionRRHHJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Proyecto;
import entidades.proyecto.resultado.FormacionRRHH;
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
public class FormacionRRHHFacade {
    
    private static FormacionRRHHFacade instance = null;

    protected FormacionRRHHFacade() {
    }

    public static FormacionRRHHFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FormacionRRHHFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FormacionRRHH formacionRRHH) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        new FormacionRRHHJpaController(emf).create(formacionRRHH);
    }

    public void modificar(FormacionRRHH formacionRRHH) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

            new FormacionRRHHJpaController(emf).edit(formacionRRHH);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionRRHHFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormacionRRHHFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<FormacionRRHH> getTodas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        return new FormacionRRHHJpaController(emf).findFormacionRRHHEntities();
    }

    public List<FormacionRRHH> getFormacionRRHH() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT f FROM FormacionRRHH f");
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }
    public List<FormacionRRHH> getFormacionesRH(String texto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT f FROM FormacionRRHH f WHERE TYPE(p) = Libro AND p.titulo LIKE '%"+texto+"%'");
        
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }
    
    public List<FormacionRRHH> getFormacionesRRHHProyecto(Proyecto proyecto) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT f FROM FormacionRRHH f WHERE  f.proyecto = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
    }
    
    public List<FormacionRRHH> getFormacionesRRHHProyectoVinculacion(ProyectoVinculacion proyectoVinculacion) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT f FROM FormacionRRHH f WHERE  f.proyectoVinculacion = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
    }
    
    public FormacionRRHH buscar(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        return new FormacionRRHHJpaController(emf).findFormacionRRHH(id);
    }
    
    public void eliminar(Long id){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            new FormacionRRHHJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormacionRRHHFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
