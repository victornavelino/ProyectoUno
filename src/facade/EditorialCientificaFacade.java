/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EditorialCientificaJpaController;
import facade.proyecto.vinculacion.*;
import controladores.FinanciacionGenericaJpaController;
import facade.*;
import controladores.FinanciacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EditorialCientifica;
import entidades.proyecto.editorial.TipoPublicacion;
import entidades.proyecto.vinculacion.Financiacion;

import entidades.proyecto.vinculacion.financiacion.FinanciacionGenerica;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
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
public class EditorialCientificaFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EditorialCientificaJpaController editorialJpaController = new EditorialCientificaJpaController(emf);
    private static EditorialCientificaFacade instance = null;
    private Query quBuscar;
    protected EditorialCientificaFacade() {
    }

    public static EditorialCientificaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EditorialCientificaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EditorialCientifica editorialCientifica) {
//        if (!existeFinanciacionConNumeroDocumentoIdentidad(FinanciacionGenerica.getPersona().getDocumentoIdentidad().getTipoDocumento(),
  //              FinanciacionGenerica.getPersona().getDocumentoIdentidad().getNumero())) {
            new EditorialCientificaJpaController(emf).create(editorialCientifica);
    //    } else {
      //      System.out.println("Alta incorrecta. Ya existe un FinanciacionGenerica con ese número de documento de identidad");
     //   }
    }

    public void modificar(EditorialCientifica editorialCientifica) {
        try {
            new EditorialCientificaJpaController(emf).edit(editorialCientifica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.EditorialCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(facade.EditorialCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(EditorialCientifica editorialCientifica) {
        try {
            new EditorialCientificaJpaController(emf).destroy(editorialCientifica.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.EditorialCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<EditorialCientifica> buscarPorTitulo(String busqueda) {
        
        
        if(busqueda.length() < 4){
            
        quBuscar= em.createQuery("SELECT p FROM EditorialCientifica p "
                + "WHERE p.titulo LIKE '" + busqueda + "%'");
        } else{
       quBuscar = em.createQuery("SELECT p FROM EditorialCientifica p "
                + "WHERE p.titulo LIKE '%" + busqueda + "%'" );
            
        }
       return quBuscar.getResultList();
    }
        public List<EditorialCientifica> buscarPorTipoPublicacion (String busqueda){
        Query quBuscarPorTipoPublicacion= em.createQuery("select distinct p from EditorialCientifica p, in (p.tipoPublicacion) sub "
                + " where sub.descripcion like :var  " );
        quBuscarPorTipoPublicacion.setParameter("var", "%"+busqueda+"%");
        return quBuscarPorTipoPublicacion.getResultList();
    }

    public List<EditorialCientifica> buscarPorISBN(String isbn) {
        try {
            Query quBuscarEditorialCientifica = em.createQuery("SELECT p FROM EditorialCientifica p "
                    + "WHERE p.ISBN =   '" + isbn + "'");
            return quBuscarEditorialCientifica.getResultList();
            //EditorialCientifica editorialCientifica = (EditorialCientifica) quBuscarEditorialCientifica.getSingleResult();
            //return editorialCientifica;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<EditorialCientifica> buscarPorAutor(String autor) {
        
            Query quBuscarEditorialCientifica = em.createQuery("SELECT distinct p FROM EditorialCientifica p "
                    + "WHERE p.autor like :var   ");
            quBuscarEditorialCientifica.setParameter("var", "%"+autor+"%");
            return quBuscarEditorialCientifica.getResultList();
            
         
    }
    public EditorialCientifica buscar(long id) {
        return new EditorialCientificaJpaController(emf).findEditorialCientifica(id);
    }

    public EditorialCientifica getUltimoFinanciacion() {
        Query quFinanciacion = em.createQuery("SELECT i FROM EditorialCientifica i");
        List<EditorialCientifica> financiaciones = quFinanciacion.getResultList();
        if (!financiaciones.isEmpty()) {
            return financiaciones.get(financiaciones.size() - 1);
        } else {
            return null;
        }
    }

    public List<EditorialCientifica> getTodosEditorial() {
        Query quTodosEditorial = em.createQuery("SELECT i FROM EditorialCientifica i "
                + "ORDER BY i.titulo");
        return quTodosEditorial.getResultList();
    }
      public List<EditorialCientifica> getFinanciacionPorDescripcion(String descripcion) {
        Query quTodosFinanciacion = em.createQuery("SELECT i FROM FinanciacionGenerica i "
                + "WHERE i.descripcion LIKE '%" + descripcion + "%'");
        return quTodosFinanciacion.getResultList();
    }

    public List<EditorialCientifica> buscarPorTodo(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<EditorialCientifica> listarTiposPublicacionEnEditorial(Long id){
        Query quBuscar = em.createQuery("SELECT e FROM EditorialCientifica e "
               + "WHERE e.tipoPublicacion.id=" + id);
        return quBuscar.getResultList();       
    } 
    
    public List<EditorialCientifica> listarFormatosEnEditorial(Long id){
        Query quBuscar = em.createQuery("SELECT e FROM EditorialCientifica e "
               + "WHERE e.formato.id=" + id);
        return quBuscar.getResultList();       
    } 
    
    public List<EditorialCientifica> listarIdiomasEnEditorial(long id){
        Query quBuscar = em.createQuery("SELECT e FROM EditorialCientifica e "
               + "WHERE e.idioma.id=" + id);
        return quBuscar.getResultList();       
    }
    
    public List<EditorialCientifica> listarTematicasEnEditorial(Long id){
        Query quBuscar = em.createQuery("SELECT e FROM EditorialCientifica e "
               + "WHERE e.tematica.id=" + id);
        return quBuscar.getResultList();       
    } 
    
    public List<EditorialCientifica> listarAcademicasEnEditorial(long id){
        Query quBuscar = em.createQuery("SELECT e FROM EditorialCientifica e "
               + "WHERE e.unidadAcademica.id=" + id);
        return quBuscar.getResultList();       
    }
}
