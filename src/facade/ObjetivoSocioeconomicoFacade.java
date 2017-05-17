/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ObjetivoSocioeconomicoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Proyecto;
import entidades.proyecto.ObjetivoSocioeconomico;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
public class ObjetivoSocioeconomicoFacade {
    
    private static ObjetivoSocioeconomicoFacade instance = null;

    protected ObjetivoSocioeconomicoFacade() {
    }

    public static ObjetivoSocioeconomicoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ObjetivoSocioeconomicoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ObjetivoSocioeconomico objetivoSocioeconomico) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        new ObjetivoSocioeconomicoJpaController(emf).create(objetivoSocioeconomico);
    }

    public void modificar(ObjetivoSocioeconomico objetivoSocioeconomico) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

            new ObjetivoSocioeconomicoJpaController(emf).edit(objetivoSocioeconomico);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ObjetivoSocioeconomicoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoSocioeconomicoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<ObjetivoSocioeconomico> getTodas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

        return new ObjetivoSocioeconomicoJpaController(emf).findObjetivoSocioeconomicoEntities();
    }

    public ObjetivoSocioeconomico buscar(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        return new ObjetivoSocioeconomicoJpaController(emf).findObjetivoSocioeconomico(id);
    }
    
    public void eliminar(Long id){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            new ObjetivoSocioeconomicoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ObjetivoSocioeconomicoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List filtrar(String descripcion) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
       EntityManager em=emf.createEntityManager();
       Query quBuscar = em.createQuery("SELECT i FROM ObjetivoSocioeconomico i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList(); 
    }

    
}
