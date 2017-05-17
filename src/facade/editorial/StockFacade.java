/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.editorial;

import controladores.EditorialCientificaJpaController;
import controladores.StockJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EditorialCientifica;
import entidades.proyecto.editorial.FormatoLibro;

import entidades.proyecto.editorial.Stock;
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
public class StockFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static StockFacade instance = null;

    protected StockFacade() {
    }

    public static StockFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new StockFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Stock stock) {
        new StockJpaController(emf).create(stock);
    }

    public void modificar(Stock stock) {
        try {
            new StockJpaController(emf).edit(stock);
        } catch (Exception ex) {
            Logger.getLogger(StockFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stock buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Stock tt WHERE tt.id="
                + id);
        try {
            return (Stock) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Stock stock = null;
            return stock;
        }

    }
     public void eliminar(Stock stock) {
        try {
            new StockJpaController(emf).destroy(stock.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(StockFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Eliminar(Long id){
        try {
            new StockJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(StockFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Stock> listarTodos() {
        Query quBuscar = em.createQuery("SELECT tt FROM Stock tt");
        return quBuscar.getResultList();
    }
    public List<Stock> buscarPorCodigo(String codigo) {
        try {
            Query quBuscarEditorialCientifica = em.createQuery("SELECT p FROM Stock p "
                    + "WHERE p.codigo =   '" + codigo + "'");
            return quBuscarEditorialCientifica.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    public List<Stock> buscarPorFormato(FormatoLibro formato) {
        
            
        Query quBuscar= em.createQuery("SELECT p FROM Stock p "
                + "WHERE p.formato = '" + formato + "'");
      
       return quBuscar.getResultList();
    }
    
        public List<Stock> buscarPorPublicacion (String busqueda){
//        Query quBuscarPorPublicacion= em.createQuery("select distinct p from Stock p, in (p.publicacion) sub "
  //              + " where sub.id = :var  " );
//        quBuscarPorPublicacion.setParameter("var", "%"+busqueda+"%");
        Query quBuscarPorPublicacion= em.createQuery(
                "select distinct p from Stock p   "
                + "WHERE p.publicacion.titulo like :var  " );
        quBuscarPorPublicacion.setParameter("var", "%"+busqueda+"%");
        //quBuscarPorPublicacion.setParameter("var", id);
        return quBuscarPorPublicacion.getResultList();
    }
    
        public List<Stock> buscarPorPublicacionAutor (String busqueda){
        Query quBuscarPorPublicacion= em.createQuery(
                "select distinct p from Stock p   "
                + "WHERE p.publicacion.autor like :var  " );
        quBuscarPorPublicacion.setParameter("var", "%"+busqueda+"%");
        //quBuscarPorPublicacion.setParameter("var", id);
        return quBuscarPorPublicacion.getResultList();
    }
    
        public List<Stock> buscarPorPublicacionISBN (String busqueda){
        Query quBuscarPorPublicacion= em.createQuery(
                "select distinct p from Stock p   "
                + "WHERE p.publicacion.isbn = :var  " );
        quBuscarPorPublicacion.setParameter("var", busqueda);
        //quBuscarPorPublicacion.setParameter("var", id);
        return quBuscarPorPublicacion.getResultList();
    }

    public List<Stock> getPorPublicacionId(Long id) {
 Query quBuscarPorPublicacion= em.createQuery("select distinct p from Stock p "
                + " where p.publicacion.id = :var  " );   
    
       quBuscarPorPublicacion.setParameter("var", id);
        return quBuscarPorPublicacion.getResultList();}
}
