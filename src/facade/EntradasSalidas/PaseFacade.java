/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade.EntradasSalidas;


import controladores.PaseJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.Pase;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vouilloz
 */
public class PaseFacade {
    
    private static PaseFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    protected PaseFacade() {
    }
    
    

    public static PaseFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PaseFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Pase pase) {
        new PaseJpaController(emf).create(pase);
    }
    
    public void modificar(Pase pase) {
        try {
            new PaseJpaController(emf).edit(pase);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Pase> getTodas() {
        return new PaseJpaController(emf).findPaseEntities();
    }
    
    
    public Pase buscar(Long id) {
        return new PaseJpaController(emf).findPase(id);
    }
    public void eliminar(Long id) {
        try{
        new PaseJpaController(emf).destroy(id);
        }catch(NonexistentEntityException ex) {
            Logger.getLogger(PaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        emf.close();
    }
  /*  public List<Expediente> buscar(String nombre) {
        Query q = em.createQuery("SELECT a FROM Expediente a "
                + "WHERE o.nombre LIKE '%" + nombre + "%'");
        return q.getResultList();
    }
    
    public List<Expediente> buscarExp(String letra, String numero) {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.letra= '" + letra + "'AND e.numero='"+numero+"'");
        return quBuscarOrg.getResultList();
    }
    
    public List<Expediente> buscarPorAño(int año) {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.anio='"+ año +"'");
        return quBuscarOrg.getResultList();
    }
    
        public List<Expediente> busquedaPorLetra(String letra) {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.letra LIKE '%" + letra + "%'");
        return quBuscarOrg.getResultList();
    }
    public List<Expediente> busquedaPorNumero(String numero) {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.numero LIKE '%" + numero + "%'");
        return quBuscarOrg.getResultList();
    }

     public List<Expediente> getExpedientes() {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e");
        return quBuscarOrg.getResultList();
    }
     
     public List<Expediente> busquedaPorInciador(String iniciador) {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.iniciador LIKE '%" + iniciador + "%'");
        return quBuscarOrg.getResultList();
    }
     
     public List<Expediente> busquedaPorEstracto(String estracto) {
        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.estracto LIKE '%" + estracto + "%'");
        return quBuscarOrg.getResultList();
    }*/
    
}
