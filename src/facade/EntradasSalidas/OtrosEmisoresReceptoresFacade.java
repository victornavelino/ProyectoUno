/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade.EntradasSalidas;


import controladores.OtrosEmisoresReceptoresJpaController;
import controladores.PaseJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.OtrosEmisoresReceptores;
import entidades.EntradasSalidas.Pase;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vouilloz
 */
public class OtrosEmisoresReceptoresFacade {
    
    private static OtrosEmisoresReceptoresFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    protected OtrosEmisoresReceptoresFacade() {
    }
    
    

    public static OtrosEmisoresReceptoresFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new OtrosEmisoresReceptoresFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(OtrosEmisoresReceptores otro) {
        new OtrosEmisoresReceptoresJpaController(emf).create(otro);
    }
    
    public void modificar(OtrosEmisoresReceptores otro) {
        try {
            new OtrosEmisoresReceptoresJpaController(emf).edit(otro);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(OtrosEmisoresReceptoresFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OtrosEmisoresReceptoresFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<OtrosEmisoresReceptores> getTodas() {
        return new OtrosEmisoresReceptoresJpaController(emf).findOtrosEmisoresReceptoresEntities();
    }
    
    
    public OtrosEmisoresReceptores buscar(Long id) {
        return new OtrosEmisoresReceptoresJpaController(emf).findOtrosEmisoresReceptores(id);
    }
    public void eliminar(Long id) {
        try{
        new OtrosEmisoresReceptoresJpaController(emf).destroy(id);
        }catch(NonexistentEntityException ex) {
            Logger.getLogger(OtrosEmisoresReceptoresFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        emf.close();
    }
 
}
