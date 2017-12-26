/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.FormatoEditorialJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.FormatoEditorial;
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
public class FormatoEditorialFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static FormatoEditorialFacade instance = null;

    protected FormatoEditorialFacade() {
    }

    public static FormatoEditorialFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FormatoEditorialFacade();
        }
    }
    
    //El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FormatoEditorial formato) {
        new FormatoEditorialJpaController(emf).create(formato);
    }
    
    public void modificar(FormatoEditorial formato) {
        try {
            new FormatoEditorialJpaController(emf).edit(formato);
        } catch (Exception ex) {
            Logger.getLogger(FormatoEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void eliminar(long id) {
        try {
            new FormatoEditorialJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormatoEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<FormatoEditorial> listarTodosOrdenados(){
        Query quBuscar = em.createQuery("SELECT f FROM FormatoEditorial f ORDER BY f.formato");
        return quBuscar.getResultList();       
    }    
    
}
