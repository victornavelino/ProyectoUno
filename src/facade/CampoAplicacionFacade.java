/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.CampoAplicacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.CampoAplicacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class CampoAplicacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    CampoAplicacionJpaController campoAplicacionJpaController = new CampoAplicacionJpaController(emf);

    private static CampoAplicacionFacade instance = null;

    protected CampoAplicacionFacade() {
    }

        public static CampoAplicacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CampoAplicacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(CampoAplicacion CampoAplicacion) {
        new CampoAplicacionJpaController(emf).create(CampoAplicacion);
    }

    public void modificar(CampoAplicacion CampoAplicacion) {
        try {
            new CampoAplicacionJpaController(emf).edit(CampoAplicacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CampoAplicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CampoAplicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new CampoAplicacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CampoAplicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CampoAplicacion> getTodosCampoAplicacion() {
        Query quTodosCampoAplicacion = em.createQuery("SELECT ca FROM CampoAplicacion ca");
        return quTodosCampoAplicacion.getResultList();
    }

    public List<CampoAplicacion> getCampoAplicacion(String descripcion) {
        Query quCampoAplicacion = em.createQuery("SELECT ca FROM CampoAplicacion ca "
                + "WHERE ca.descripcion LIKE '" + descripcion + "'");
        return quCampoAplicacion.getResultList();
    }
    public List<CampoAplicacion> filtrar(String descripcion){
        
        Query quBuscar = em.createQuery("SELECT i FROM CampoAplicacion i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
       
    }
    

}
