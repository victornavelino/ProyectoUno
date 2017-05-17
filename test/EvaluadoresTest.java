/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.categorizacion.Categoria;
import entidades.categorizacion.CategoriaAsignada;
import entidades.categorizacion.Categorizacion;
import entidades.persona.Evaluador;
import entidades.persona.investigador.Investigador;
import facade.CategoriaFacade;
import facade.CategorizacionFacade;
import facade.EvaluadorFacade;
import java.util.List;
import facade.InvestigadorFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author panchi
 */
public class EvaluadoresTest {
    
    public EvaluadoresTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello2(){
         List<Evaluador> lista =EvaluadorFacade.getInstance().getTodosEvaluador();
         for(Evaluador eval : lista){
             System.out.println(eval);
             if(eval.getInvestigador().getCategorizaciones()!=null){
                 
             List<Categorizacion> l = eval.getInvestigador().getCategorizaciones();
             for(Categorizacion cat : l){
                 if(cat.getCategoriaAsignada()!=null){
                 CategoriaAsignada categoriaAsignada = cat.getCategoriaAsignada();
                 System.out.println(categoriaAsignada.getValorCategoria().getDescripcion());
             }
             }
         }}
     
     }
     public void hello() {
     String valido = "false";
     List<Investigador> investigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
     for(Investigador investigador : investigadores){
         
         List<Categorizacion> categorias = investigador.getCategorizaciones();
         for(Categorizacion categorizacion : categorias){
           CategoriaAsignada categoriaAsignada = categorizacion.getCategoriaAsignada();
           
          if(categoriaAsignada != null){
               if(categoriaAsignada.getValorCategoria().getDescripcion().equals("1") || 
                    categoriaAsignada.getValorCategoria().getDescripcion().equals("2")){
                valido = "true";
                break;
          }else {
                   valido ="false";
               }
                            
            }
         }
     if(valido.equals("true")){
         Evaluador evaluador = new Evaluador();
         evaluador.setInvestigador(investigador);
         EvaluadorFacade.getInstance().alta(evaluador);
     }
     valido = "false";
     }
     
     }
}
