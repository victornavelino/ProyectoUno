/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.becas.evaluacion;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import controladores.BecasComiteEvaluadorJpaController;
import controladores.BecasEvaluacionWebJpaController;
import entidades.becas.evaluacion.BecasComiteEvaluador;
import entidades.becas.evaluacion.BecasEvaluacionWeb;
import facade.ConexionFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;


/**
 *
 * @author AFerSor
 */
public class BecasEvaluacionWebFacade {
   EntityManagerFactory emf= Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
   EntityManager em= emf.createEntityManager();
   BecasEvaluacionWebJpaController jpaControler = new BecasEvaluacionWebJpaController(emf);
   BecasComiteEvaluadorJpaController jpaControlerCE = new BecasComiteEvaluadorJpaController(emf);
   
   private static BecasEvaluacionWebFacade instance = null;
   
    public static BecasEvaluacionWebFacade getInstance() {

        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BecasEvaluacionWebFacade();
        }
    }//fin  createInstance
    
    public void alta(BecasEvaluacionWeb pr) throws Exception{
        System.out.println("Antes del alta");
        
        for(BecasComiteEvaluador bce : pr.getBecasComitesEvaluadores()){
            //jpaControlerCE.create(bce);
            System.out.println(" bce " + bce );
            System.out.println(" bce id " + bce.getId());
            System.out.println(" bce evaluador id " + bce.getEvaluador().getId());
            System.out.println(" bce responsable " + bce.getResponsable());
        }//fin for
        //pr.setBecasComitesEvaluadores(new ArrayList<BecasComiteEvaluador>());
        jpaControler.create(pr);
    }//fin alta
    
    public void modificar(BecasEvaluacionWeb pr)throws Exception{
        jpaControler.edit(pr);
    }//fin modificar
    
    public void eliminar(BecasEvaluacionWeb pr)throws Exception{
         try{

             jpaControler.destroy(pr.getId());

                    
        }catch(Exception e){
            if(e.getCause().toString().contains("DatabaseException")){
                throw new Exception("No se puede eliminar esta siendo usado por otros registros.");
            }else{
                throw new Exception(e.getMessage());
            }
            
        }
        
    }//fin eliminar
    
    public List<BecasEvaluacionWeb> findBecasEvaluacionWebLike(String cadena) throws Exception{
         em.close();
         em = emf.createEntityManager();
         Query query = null;

         if(!cadena.isEmpty()){
             query = em.createQuery("SELECT l FROM BecasEvaluacionWeb l WHERE l.postulacionBeca.beca.descripcion "
                     + "LIKE '"+cadena.trim().toLowerCase()+ "%' OR l.postulacionBeca.postulante.persona.nombre LIKE '%" +
                     cadena.trim().toLowerCase() + "%' OR l.postulacionBeca.postulante.persona.apellido LIKE '%" +
                     cadena.trim().toLowerCase() + "%' ORDER BY "
                     + "l.postulacionBeca.beca.descripcion " );
             
             /*query = em.createQuery("SELECT l FROM BecasEvaluacionWeb l WHERE l.nombre "
                     + "LIKE '"+cadena.trim().toLowerCase()+ "%' ORDER BY "
                     + "l.nombre" );*/
         }else{

             query = em.createQuery("SELECT l FROM BecasEvaluacionWeb l");

         }
         
         
         return query.getResultList();

     }//fin findMedicamentoLike
    
    public Boolean bFindByPostulacionBeca(Long idPostulacionBeca, Long idBecasEvaluacionWeb, int op) throws Exception{
        
        em.close();
        em = emf.createEntityManager();
        try{
            Query query = null;

            switch (op) {
                case 0:
                    query = em.createNamedQuery("BecasEvaluacionWeb.findByPostulacionBeca");
                    break;
                case 1:
                    query = em.createNamedQuery("BecasEvaluacionWeb.findByPostulacionBecaID");
                    query.setParameter("id", idBecasEvaluacionWeb);
                    break;
            }//fin switch

            query.setParameter("idPostulacionBeca", idPostulacionBeca);

            query.getSingleResult();
            return Boolean.TRUE;
        }catch(NoResultException e){
            return Boolean.FALSE;
        }
        
    }//fin bFindByNombre
    
    public BecasEvaluacionWeb findByNombre(String nombre) throws Exception{
        
        /*em.close();
        em = emf.createEntityManager();
        try{
            Query query = em.createNamedQuery("BecasEvaluacionWeb.findByNombre");
            query.setParameter("nombre", nombre.toLowerCase().trim());

            return (BecasEvaluacionWeb)query.getSingleResult();

        }catch(NoResultException e){
            return null;
        }*/
        return null;
        
    }//fin bFindByNombre
    
    public BecasEvaluacionWeb findById(Long id) throws Exception{
        
        try{
            Query query = em.createNamedQuery("BecasEvaluacionWeb.findById");
            query.setParameter("idBecasEvaluacionWeb", id);

            return (BecasEvaluacionWeb)query.getSingleResult();

        }catch(NoResultException e){
            return null;
        }
        
    }//fin bFindByNombre
    
     public List<BecasEvaluacionWeb> getBecasEvaluacionWebs(){
        Query query= em.createQuery("SELECT l FROM BecasEvaluacionWeb l ORDER BY l.nombre");        
        return query.getResultList();
    }
      
}//FIN CLASE
