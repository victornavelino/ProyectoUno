/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.becas.evaluacion;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import controladores.BecasItemEvaluarJpaController;
import entidades.becas.evaluacion.BecasItemEvaluar;
import facade.ConexionFacade;
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
public class BecasItemEvaluarFacade {
   EntityManagerFactory emf= Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
   EntityManager em= emf.createEntityManager();
   BecasItemEvaluarJpaController jpaControler = new BecasItemEvaluarJpaController(emf);
   
   private static BecasItemEvaluarFacade instance = null;
   
    public static BecasItemEvaluarFacade getInstance() {

        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BecasItemEvaluarFacade();
        }
    }//fin  createInstance
    
    public void alta(BecasItemEvaluar pr) throws Exception{
        jpaControler.create(pr);
    }//fin alta
    
    public void modificar(BecasItemEvaluar pr)throws Exception{
        jpaControler.edit(pr);
    }//fin modificar
    
    public void eliminar(BecasItemEvaluar pr)throws Exception{
         try{
             
            //verificar si hay departamento con el id d ela provincia             
             /*Query query  = em.createQuery("SELECT a FROM BecasItemEvaluar a WHERE a.becasItemEvaluar.id = :idTipo");
             query.setParameter("idTipo", pr.getId());
             
             
             if(query.getResultList().isEmpty()){*/
                 jpaControler.destroy(pr.getId());
             /*}else{
                 throw new Exception("No se puede eliminar. Existen actividades relacionadas a este tipo de actividad.");
             }*/
                    
        }catch(Exception e){
            if(e.getCause().toString().contains("DatabaseException")){
                throw new Exception("No se puede eliminar esta siendo usado por otros registros.");
            }else{
                throw new Exception(e.getMessage());
            }
            
        }
        
    }//fin eliminar
    
    public List<BecasItemEvaluar> findBecasItemEvaluarLike(String cadena) throws Exception{
         em.close();
         em = emf.createEntityManager();
         Query query = null;
         if(!cadena.isEmpty()){
             query = em.createQuery("SELECT l FROM BecasItemEvaluar l WHERE l.descripcion "
                     + "LIKE '%"+cadena.trim().toLowerCase()+ "%' ORDER BY "
                     + "l.descripcion" );
         }else{
             query = em.createQuery("SELECT l FROM BecasItemEvaluar l ORDER BY "
                     + "l.descripcion" );
         }
         
         
         return query.getResultList();
     }//fin findMedicamentoLike
    
    public Boolean bFindByNombre(String nombre, Long idBecasItemEvaluar, int op) throws Exception{

        em.close();
        em = emf.createEntityManager();
        try{
            Query query = null;

            switch (op) {
                case 0:
                    query = em.createNamedQuery("BecasItemEvaluar.findByDescripcion");
                    break;
                case 1:
                    query = em.createNamedQuery("BecasItemEvaluar.findByDescripcionID");
                    query.setParameter("id", idBecasItemEvaluar);
                    break;
            }//fin switch

            query.setParameter("descripcion", nombre.toLowerCase().trim());

            query.getSingleResult();
            return Boolean.TRUE;
        }catch(NoResultException e){
            return Boolean.FALSE;
        }
        
    }//fin bFindByNombre
    
    public BecasItemEvaluar findByNombre(String nombre) throws Exception{
        
        em.close();
        em = emf.createEntityManager();
        try{
            Query query = em.createNamedQuery("BecasItemEvaluar.findByDescripcion");
            query.setParameter("descripcion", nombre.toLowerCase().trim());

            return (BecasItemEvaluar)query.getSingleResult();

        }catch(NoResultException e){
            return null;
        }
        
    }//fin bFindByNombre
     public List<BecasItemEvaluar> getBecasItemEvaluars(){
        Query query= em.createQuery("SELECT l FROM BecasItemEvaluar l ORDER BY l.descripcion");        
        return query.getResultList();
    }
     
     public DefaultComboBoxModel getComboBecasItemEvaluar(){
        
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        List<BecasItemEvaluar> lstBecasItemEvaluar = this.getBecasItemEvaluars();
        if(lstBecasItemEvaluar!=null){
            for(BecasItemEvaluar l : lstBecasItemEvaluar){
                dcbm.addElement(l);
            }//fin for
            
        }//fin if
        
        return dcbm;
    }//fin getComboProvincia()
     
    
    
}//FIN CLASE
