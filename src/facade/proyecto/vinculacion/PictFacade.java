/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.proyecto.vinculacion;

import controladores.FinanciacionPictJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPict;
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
public class PictFacade {
    
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em=emf.createEntityManager();
    
    private static PictFacade instance=null;
    
    protected PictFacade(){
        
    }
        
    public static PictFacade getInstance(){
        if(instance==null){
            createInstance();
        }
        return instance;
    }
    
    private synchronized static void createInstance() {
        if(instance==null){
            instance=new PictFacade();
        }
    }
    
    /**
     * 
     * metodo para cuando instanciemos diagPicto modo consulta y modificacion
     * 
     * @param id
     * @return FinanciacionPICTO 
     */
    public FinanciacionPict getPicto(Long id){
        Query getpicto=em.createQuery("select p from FinanciacionPICTO p where p.id=:id");
        getpicto.setParameter("id", id);
        List<FinanciacionPict> lista=getpicto.getResultList();
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
    
    public void alta(FinanciacionPict pict){
        new FinanciacionPictJpaController(emf).create(pict);         
    }
    
    public void modificacion(FinanciacionPict pict) {
        try {
            new FinanciacionPictJpaController(emf).edit(pict);
        } catch (NonexistentEntityException ex) {
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
