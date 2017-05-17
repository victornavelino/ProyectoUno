/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.proyecto.vinculacion;

import controladores.FinanciacionPICTOJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPICTO;
import entidades.proyecto.vinculacion.financiacion.picto.Rubro;
import facade.ConexionFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ruben
 */
public class PictoFacade {

   
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em=emf.createEntityManager();
    
    private static PictoFacade instance=null;
    
    protected PictoFacade(){
        
    }
        
    public static PictoFacade getInstance(){
        if(instance==null){
            createInstance();
        }
        return instance;
    }
    
    private synchronized static void createInstance() {
        if(instance==null){
            instance=new PictoFacade();
        }
    }
    
    /**
     * 
     * metodo para cuando instanciemos diagPicto modo consulta y modificacion
     * 
     * @param id
     * @return FinanciacionPICTO 
     */
    public FinanciacionPICTO getPicto(Long id){
        Query getpicto=em.createQuery("select p from FinanciacionPICTO p where p.id=:id");
        getpicto.setParameter("id", id);
        List<FinanciacionPICTO> lista=getpicto.getResultList();
        if(!lista.isEmpty()){
            return lista.get(0);
        }else{
            return null;
        }
        
    }
    
    public List<Rubro> getTodosRubros(){
        Query gettodos=em.createQuery("select r from Rubro r");
        List<Rubro> rubros=gettodos.getResultList();
        return rubros;
    }
    
    public void alta(FinanciacionPICTO picto){
        new FinanciacionPICTOJpaController(emf).create(picto);         
    }
    
    public void modificacion(FinanciacionPICTO picto) {
        try {
            new FinanciacionPICTOJpaController(emf).edit(picto);
        } catch (NonexistentEntityException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
