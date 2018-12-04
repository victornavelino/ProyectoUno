/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PublicacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.AreaTematica;
import entidades.proyecto.Proyecto;
import entidades.proyecto.resultado.*;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import includes.Comunes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author franco
 */
public class PublicacionFacade {

    private static PublicacionFacade instance = null;

    protected PublicacionFacade() {
    }

    public static PublicacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PublicacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Publicacion publicacion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        new PublicacionJpaController(emf).create(publicacion);
    }

    public void modificar(Publicacion publicacion) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

            new PublicacionJpaController(emf).edit(publicacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PublicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PublicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Publicacion> getTodas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        return new PublicacionJpaController(emf).findPublicacionEntities();
    }

    public List<Libro> getLibros() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT l FROM Libro l where l.id IN (Select p.id from Publicacion p where type(p)= Libro and p.id=l.id)");
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
    public List<Libro> getLibros(String texto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p WHERE TYPE(p) = Libro AND p.titulo LIKE '%"+texto+"%'");
        
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
    

    public List<Libro> getLibrosProyecto(Proyecto proyecto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectos) pr WHERE TYPE(p) = Libro AND  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
    }
        public List<Libro> getLibrosProyectoVinculacion(ProyectoVinculacion proyectoVinculacion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectosVinculacion) pr WHERE TYPE(p) = Libro AND  pr = :proyectovinculacion");
        quBuscar.setParameter("proyectovinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
    }
    
public List<Libro> buscarLibros(String libro, Proyecto proyecto) {
        List<Libro> lista = new ArrayList<>();
        for (Publicacion publicacion : proyecto.getPublicaciones()) {
            if (publicacion.getClass().getSimpleName().equals("Libro")) {
                if (publicacion.getTitulo().contains(libro)) {
                    lista.add((Libro) publicacion);
                }
            }
        }
        return lista;
    }



    public List<CapituloLibro> getCapitulosLibro(Proyecto proyecto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectos) pr WHERE TYPE(p) = CapituloLibro AND  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
    }

    public List<CapituloLibro> getCapitulosLibro(ProyectoVinculacion proyectoVinculacion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectosVinculacion) pr WHERE TYPE(p) = CapituloLibro AND  pr = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
    }



    public List<CapituloLibro> getCapitulosLibro() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT cl FROM CapituloLibro cl");
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

    public List<Congreso> getCongresos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT c FROM Congreso c");
        try {
            if (!quBuscar.getResultList().isEmpty()) {
                return quBuscar.getResultList();
            } else {
                return new ArrayList<>();
            }
        } catch (NoResultException ex) {
            return new ArrayList<Congreso>();
        }
    }
    public List<Congreso> getCongresos(Proyecto proyecto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectos) pr WHERE TYPE(p) = Congreso AND  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
    }
    
    public List<Congreso> getCongresos(ProyectoVinculacion proyectoVinculacion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectosVinculacion) pr WHERE TYPE(p) = Congreso AND  pr = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
    }
    

    public List<ArticuloRevista> getArticulosRevista() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT ar FROM ArticuloRevista ar");
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
    
    public List<ArticuloRevista> getArticuloRevista(Proyecto proyecto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectos) pr WHERE TYPE(p) = ArticuloRevista AND  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
    }
    
    public List<ArticuloRevista> getArticuloRevista(ProyectoVinculacion proyectoVinculacion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT p FROM Publicacion p, IN (p.proyectosVinculacion) pr WHERE TYPE(p) = ArticuloRevista AND  pr = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
    }

    public Publicacion buscar(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        return new PublicacionJpaController(emf).findPublicacion(id);
    }

    public void eliminar(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

            new PublicacionJpaController(emf).destroy(id);
        

} catch (NonexistentEntityException ex) {
            Logger.getLogger(PublicacionFacade.class  

.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
}
