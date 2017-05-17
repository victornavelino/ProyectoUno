/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EditorialCientificaJpaController;
import controladores.ExpedienteEditorialJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.editorial.EditorialCientifica;

import entidades.proyecto.editorial.ExpedienteEditorial;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class ExpedienteEditorialFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static ExpedienteEditorialFacade instance = null;

    protected ExpedienteEditorialFacade() {
    }

    public static ExpedienteEditorialFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ExpedienteEditorialFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ExpedienteEditorial expedienteEditorial) {
        new ExpedienteEditorialJpaController(emf).create(expedienteEditorial);
    }

    public void modificar(ExpedienteEditorial expedienteEditorial) {
        try {
            new ExpedienteEditorialJpaController(emf).edit(expedienteEditorial);
        } catch (Exception ex) {
            Logger.getLogger(ExpedienteEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ExpedienteEditorial buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM ExpedienteEditorial tt WHERE tt.id="
                + id);
        try {
            return (ExpedienteEditorial) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            ExpedienteEditorial expedienteEditorial = null;
            return expedienteEditorial;
        }

    }
     public void eliminar(ExpedienteEditorial expedienteEditorial) {
        try {
            new ExpedienteEditorialJpaController(emf).destroy(expedienteEditorial.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.ExpedienteEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Eliminar(Long id){
        try {
            new ExpedienteEditorialJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ExpedienteEditorialFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<ExpedienteEditorial> listarTodos() {
        Query quBuscar = em.createQuery("SELECT tt FROM ExpedienteEditorial tt");
        return quBuscar.getResultList();
    }
    public List<ExpedienteEditorial> buscarPorCodigo(String codigo) {
        try {
            Query quBuscarEditorialCientifica = em.createQuery("SELECT p FROM ExpedienteEditorial p "
                    + "WHERE p.codigo =   '" + codigo + "'");
            return quBuscarEditorialCientifica.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
     public List<ExpedienteEditorial> buscarPorIniciador(String busqueda) {
        
            
        Query quBuscar= em.createQuery("SELECT p FROM ExpedienteEditorial p "
                + "WHERE p.iniciador LIKE '" + busqueda + "%'");
      
       return quBuscar.getResultList();
    }
    
        public List<ExpedienteEditorial> buscarPorPublicacion (String busqueda){
        Query quBuscarPorPublicacion= em.createQuery(
                "select distinct p from ExpedienteEditorial p   "
                + " WHERE p.publicacion.titulo like :var  " );
        quBuscarPorPublicacion.setParameter("var", "%"+busqueda+"%");
        return quBuscarPorPublicacion.getResultList();
    }
}
