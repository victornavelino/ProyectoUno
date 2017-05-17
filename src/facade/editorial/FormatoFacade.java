/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.editorial;

import controladores.EditorialCientificaJpaController;
import controladores.FormatoLibroJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EditorialCientifica;
import entidades.proyecto.editorial.FormatoLibro;
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
public class FormatoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static FormatoFacade instance = null;

    protected FormatoFacade() {
    }

    public static FormatoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new FormatoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(FormatoLibro stock) {
        new FormatoLibroJpaController(emf).create(stock);
    }

    public void modificar(FormatoLibro stock) {
        try {
            new FormatoLibroJpaController(emf).edit(stock);
        } catch (Exception ex) {
            Logger.getLogger(FormatoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FormatoLibro buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM FormatoLibro tt WHERE tt.id="
                + id);
        try {
            return (FormatoLibro) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            FormatoLibro stock = null;
            return stock;
        }

    }
     public void eliminar(FormatoLibro stock) {
        try {
            new FormatoLibroJpaController(emf).destroy(stock.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormatoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Eliminar(Long id){
        try {
            new FormatoLibroJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FormatoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<FormatoLibro> listarTodos() {
        Query quBuscar = em.createQuery("SELECT tt FROM FormatoLibro tt");
        return quBuscar.getResultList();
    }
    public List<FormatoLibro> buscarPorFormato(FormatoLibro formato) {
        
            
        Query quBuscar= em.createQuery("SELECT p FROM FormatoLibro p "
                + "WHERE p.id = '" + formato.getId() + "'");
      
       return quBuscar.getResultList();
    }
}