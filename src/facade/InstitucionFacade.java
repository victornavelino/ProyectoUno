/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.InstitucionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Institucion;
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
 * @author Panchi
 */
public class InstitucionFacade {
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static InstitucionFacade instance = null;

    protected InstitucionFacade() {
    }

    public static InstitucionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new InstitucionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Institucion institucion) {
        new InstitucionJpaController(emf).create(institucion);
    }
    
    public void modificar(Institucion institucion) {
        try {
            new InstitucionJpaController(emf).edit(institucion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(InstitucionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InstitucionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Institucion buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Institucion tt WHERE tt.id="
                + id);
        try {
            return (Institucion) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Institucion institucion = null;
            return institucion;
        }

    }

    public List<Institucion> listarInstitucion(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Institucion tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Institucion> listarTodosInstitucion() {
        Query quBuscar = em.createQuery("SELECT tt FROM Institucion tt");
        return quBuscar.getResultList();
    }

    public List<Institucion> listarTodosInstitucionOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Institucion tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
    public List<Institucion> filtrar(String descripcion){
        
        Query quBuscar = em.createQuery("SELECT i FROM Institucion i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }

   public void eliminar(long id) {
        try {
            new InstitucionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CampoAplicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


