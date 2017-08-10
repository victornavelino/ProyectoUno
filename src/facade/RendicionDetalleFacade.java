/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.RendicionDetalleJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.RendicionDetalle;
import entidades.proyecto.Proyecto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author walter
 */
public class RendicionDetalleFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    RendicionDetalleJpaController rendicionDetalleJpaController = new RendicionDetalleJpaController(emf);

    private static RendicionDetalleFacade instance = null;

    protected RendicionDetalleFacade() {
    }

    public static RendicionDetalleFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new RendicionDetalleFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public void altaRendicionDetalle(RendicionDetalle rendicionDetalle){
        rendicionDetalleJpaController.create(rendicionDetalle);        
    } 
    
    public void bajaRendicionDetalle(Long idRendiDeta){
        try {
            rendicionDetalleJpaController.destroy(idRendiDeta);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RendicionDetalleFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Proyecto> buscarxDescripcion(String deta){
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();        
        String consulta = "SELECT DISTINCT(p) FROM Proyecto p,"
                + "IN (p.pagos) u, IN (u.rendiciones) r, IN (r.rendicionDetalle) rd "
                + "WHERE rd.detalle LIKE :detalle";        
        Query quArtis = em.createQuery(consulta);
        quArtis.setParameter("detalle", "%"+deta+"%");        
        return quArtis.getResultList();
    }
        
}
