/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.proyecto.vinculacion;

import facade.*;
import controladores.OtrasAgenciasJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.OtrasAgencias;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Panchi
 */
public class OtraAgenciaFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    OtrasAgenciasJpaController otrasAgenciasJpaController = new OtrasAgenciasJpaController(emf);
    private static OtraAgenciaFacade instance = null;

    protected OtraAgenciaFacade() {
    }

    public static OtraAgenciaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new OtraAgenciaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(OtrasAgencias otrasAgencias) {
        new OtrasAgenciasJpaController(emf).create(otrasAgencias);
    }
    
    public void modificacion(OtrasAgencias otrasAgencias) {        
        try {
            new OtrasAgenciasJpaController(emf).edit(otrasAgencias);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(OtraAgenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OtraAgenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void eliminar(OtrasAgencias otrasAgencias) {
        try {
            new OtrasAgenciasJpaController(emf).destroy(otrasAgencias.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(OtraAgenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public List<OtrasAgencias> otrasAgenciasxDescripcion(String otrasAgencias){
        Query quDes = em.createQuery("SELECT l FROM OtrasAgencias l "
                + "WHERE l.descripcion LIKE :des");
        quDes.setParameter("des", "%" + otrasAgencias + "%");
        return  quDes.getResultList();        
    }
    
    public OtrasAgencias buscar(long id) {
        return otrasAgenciasJpaController.findOtrasAgencias(id);
    }
    public List<OtrasAgencias> getTodosOtrasAgencias() {
        Query quTodasOtrasAgencias = em.createQuery("SELECT nf FROM OtrasAgencias nf ORDER BY nf.descripcion");
        return quTodasOtrasAgencias.getResultList();
    }
}
