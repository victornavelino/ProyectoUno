/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JFrame;
import org.junit.*;
import static org.junit.Assert.*;
import vista.panelControl.diagRepetidos;

/**
 *
 * @author Panchi
 */
public class diagRepetidosTest {
    
    public diagRepetidosTest() {
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
     
     diagRepetidos diagRepetidos = new diagRepetidos(new JFrame(), true);

     }
}
