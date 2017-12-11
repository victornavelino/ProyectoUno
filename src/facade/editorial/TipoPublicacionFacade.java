/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.editorial;

import controladores.EditorialCientificaJpaController;
import controladores.TipoPublicacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EditorialCientifica;
import entidades.proyecto.editorial.TipoPublicacion;
import facade.ConexionFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author panchi
 */
public class TipoPublicacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static TipoPublicacionFacade instance = null;

    protected TipoPublicacionFacade() {
    }

    public static TipoPublicacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoPublicacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoPublicacion tipoPublicacion) {
        new TipoPublicacionJpaController(emf).create(tipoPublicacion);
    }

    public void modificar(TipoPublicacion tipoPublicacion) {
        try {
            new TipoPublicacionJpaController(emf).edit(tipoPublicacion);
        } catch (Exception ex) {
            Logger.getLogger(TipoPublicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoPublicacion buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoPublicacion tt WHERE tt.id="
                + id);
        try {
            return (TipoPublicacion) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            TipoPublicacion tipoPublicacion = null;
            return tipoPublicacion;
        }

    }
     public void eliminar(TipoPublicacion tipoPublicacion) {
        try {
            new TipoPublicacionJpaController(emf).destroy(tipoPublicacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoPublicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Eliminar(Long id){
        try {
            new TipoPublicacionJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoPublicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TipoPublicacion> listarTodos() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoPublicacion tt");
        return quBuscar.getResultList();
    }
    
    public List<TipoPublicacion> buscarPorFormato(TipoPublicacion tipoPublicacion) {
        
            
        Query quBuscar= em.createQuery("SELECT p FROM TipoPublicacion p "
                + "WHERE p.id = '" + tipoPublicacion.getId() + "'");
      
       return quBuscar.getResultList();
    }
}