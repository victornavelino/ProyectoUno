/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CategorizacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadAcademica;
import entidades.categorizacion.Categorizacion;
import entidades.persona.investigador.Investigador;
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
public class CategorizacionFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    CategorizacionJpaController categorizacionJpaController = new CategorizacionJpaController(emf);
    private static CategorizacionFacade instance = null;

    protected CategorizacionFacade() {
    }

    public static CategorizacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CategorizacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Categorizacion categorizacion) {
        categorizacionJpaController.create(categorizacion);
    }
    
    public void baja(Categorizacion categorizacion){        
        try {
            categorizacionJpaController.destroy(categorizacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategorizacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificacion(Categorizacion categorizacion){
        try {
            categorizacionJpaController.edit(categorizacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategorizacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategorizacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Categorizacion> listaCategoriasInvestigador(){
        Query quCategorias = em.createQuery("SELECT c FROM Investigador i,  IN (i.categorizaciones) c "
                + " ORDER BY i.persona");
        return quCategorias.getResultList();
    }
    
    public List<Investigador> CategorizacionesInvestigador(){
        Query quCategorizaciones=em.createQuery("select i from Investigador i where"
                + " i.categorizaciones is not empty");
        return quCategorizaciones.getResultList();
    }

    public List getCategorizacionXUnidadAcademica(UnidadAcademica unidadAcademica) {
     Query quCategorias = em.createQuery("SELECT c FROM Categorizacion c where c.unidadAcademica=:unidadAcademica");
     quCategorias.setParameter("unidadAcademica", unidadAcademica);
        return quCategorias.getResultList();    
    }
}
