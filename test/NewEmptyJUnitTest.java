/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.usuario.Usuario;
import java.util.List;
import entidades.persona.investigador.Investigador;
import facade.UsuarioFacade;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos
 */
public class NewEmptyJUnitTest {

    List<Investigador> investigadores;

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
       // investigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
    }

    @After
    public void tearDown() {
     //   System.out.println("Cantidad de investigadores = " + (investigadores.size() + 1));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void conexion(){
        Usuario u2 = new Usuario();
        u2.setNombreCompleto("hola");
        u2.setNombreUsuario("u");
        u2.setContrasena("u");
        new UsuarioFacade().alta(u2);
        List<Usuario> l = new UsuarioFacade().listarTodosUsuarios();
        for(Usuario u : l){
            System.out.println(u.getNombreUsuario()+" "+ u.getContrasena());
        }
        
    }
    
    //@Test(timeout=20000, expected=Exception.class)
    public void cargarInvestigadores() {        
        System.out.println("Cantidad de investigadores = " + investigadores.size());
        assertEquals(investigadores.size(), 1112);
        assertTrue(investigadores.size() > 0);
        int numero = Integer.parseInt(JOptionPane.showInputDialog("HOla"));
    }

}