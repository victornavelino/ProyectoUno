/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.entidades.proyectos.vinculacion;

import includes.Comunes;
import java.text.ParseException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Panchi
 */
public class ComasTest {
    
    public ComasTest() {
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
     public void hello() throws ParseException {
         //Float f = Float.parseFloat(jtfValor.getText());
        String s = "100.5";
        
        String d = s.replace(".",",");
        Float f = Comunes.formatodecimal(d);
        //Float a = new Float("2,2"); 
        
       // s.replaceAll(",", ".");
     //   s.trim();
        System.out.println(f);
     
     }
}
