/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.IdiomaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Idioma;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author walter
 */
public class IdiomaFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static IdiomaFacade instance = null;

    protected IdiomaFacade() {
    }

    public static IdiomaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new IdiomaFacade();
        }
    }
    
    //El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Idioma idioma) {
        new IdiomaJpaController(emf).create(idioma);
    }
    
    public void modificar(Idioma idioma) {
        try {
            new IdiomaJpaController(emf).edit(idioma);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(IdiomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IdiomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void eliminar(long id) {
        try {
            new IdiomaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(IdiomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
