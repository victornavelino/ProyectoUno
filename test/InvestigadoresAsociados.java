/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.usuario.Usuario;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.persona.investigador.Investigador;
import entidades.titulo.TituloGrado;
import facade.FormacionAcademicaGradoFacade;
import facade.GrupoFacade;
import facade.InvestigadorFacade;
import java.util.List;
import facade.TituloGradoFacade;
import facade.UsuarioFacade;
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
public class InvestigadoresAsociados {
    
    public InvestigadoresAsociados() {
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
     //@Test
     public void hello() {
      List<TituloGrado> listaTitulos = TituloGradoFacade.getInstance().getTodosTituloGrado();
      TituloGrado titulo = listaTitulos.get(1);
      List<FormacionAcademicaGrado> list = TituloGradoFacade.getInstance().getInvestigadoresAsociados(titulo);
      if(list.isEmpty()){
          System.out.println(titulo);
          System.out.println("vacia");
      } else{
          System.out.println("tiene");
      }
     
     }
     @Test
     public void conexion(){
         Usuario u = new Usuario();
         u.setNombreUsuario("p");
         u.setContrasena("p");
         u.setGrupo(GrupoFacade.getInstance().buscar(1L));
         new UsuarioFacade().alta(u);
     }
     public void otraPrueba(){
//         List<TituloGrado> listaTituloGrado =  TituloGradoFacade.getInstance().getTituloGrado("Profesora en Biología");
//         for(TituloGrado tituloGrado : listaTituloGrado){
//             System.out.println(tituloGrado);
//             System.out.println(tituloGrado.getId());
//         }
         TituloGrado tituloGrado = TituloGradoFacade.getInstance().getTituloGrado(45L);
         //System.out.println(tituloGrado);
         List<Investigador> listaInvestigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
         for(Investigador investigador : listaInvestigadores){
             List<FormacionAcademicaGrado> listaFormacionAcademicaGrados = investigador.getFormacionesAcademicasGrado();
             for(FormacionAcademicaGrado formacionAcademicaGrado : listaFormacionAcademicaGrados){
//                 if(formacionAcademicaGrado.getTituloGrado().equals(tituloGrado)){
//                     System.out.println(investigador);
//                 }
                 System.out.println(formacionAcademicaGrado);
             }
                 
         }
     }
}
