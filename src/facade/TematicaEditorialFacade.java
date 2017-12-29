/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TematicaEditorialJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.TematicaEditorial;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author walter
 */
public class TematicaEditorialFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static TematicaEditorialFacade instance = null;

    protected TematicaEditorialFacade() {
    }

    public static TematicaEditorialFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TematicaEditorialFacade();
        }
    }
    
    //El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TematicaEditorial tematica) {
        new TematicaEditorialJpaController(emf).create(tematica);
    }
    
    public void modificar(TematicaEditorial tematica) {
        try {
            new TematicaEditorialJpaController(emf).edit(tematica);
        } catch (Exception ex) {
            Logger.getLogger(TematicaEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void eliminar(long id) {
        try {
            new TematicaEditorialJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TematicaEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TematicaEditorial> listarTodasOrdenadas(){
        Query quBuscar = em.createQuery("SELECT t FROM TematicaEditorial t ORDER BY t.tematica");
        return quBuscar.getResultList();       
    }   
}
