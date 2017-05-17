/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.Resolucion;
import entidades.usuario.Usuario;
import facade.GrupoFacade;
import facade.ResolucionFacade;
import java.util.List;
import javax.swing.JDesktopPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vista.frPrincipal;
import vista.resoluciones.diagAsignacionResolucion;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class ResolucionTest {
    
    public ResolucionTest() {
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
     public void hello() {
        List<Resolucion> buscar = new ResolucionFacade().buscar("");
        for ( Resolucion b:buscar) {
             System.out.println(b);
         }
     
     }
}
