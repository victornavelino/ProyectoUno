/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.MinisterioJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.Ministerio;
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
public class MinisterioFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    MinisterioJpaController ministerioJpaController = new MinisterioJpaController(emf);
    private static MinisterioFacade instance = null;

    protected MinisterioFacade() {
    }

    public static MinisterioFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new MinisterioFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Ministerio ministerio) {
        new MinisterioJpaController(emf).create(ministerio);
    }
    
    public void modificacion(Ministerio ministerio) {        
        try {
            new MinisterioJpaController(emf).edit(ministerio);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MinisterioFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MinisterioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Ministerio> ministerioxDescripcion(String ministerio){
        Query quDes = em.createQuery("SELECT l FROM Ministerio l "
                + "WHERE l.descripcion LIKE :des");
        quDes.setParameter("des", "%" + ministerio + "%");
        return  quDes.getResultList();        
    }
    
    public Ministerio buscar(long id) {
        return ministerioJpaController.findMinisterio(id);
    }
    public List<Ministerio> getTodosMinisterio() {
        Query quTodasMinisterio = em.createQuery("SELECT nf FROM Ministerio nf ORDER BY nf.descripcion");
        return quTodasMinisterio.getResultList();
    }
}
