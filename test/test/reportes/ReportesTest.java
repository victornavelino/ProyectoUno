/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.reportes;

import facade.InvestigadorFacade;
import facade.ProyectoFacade;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author franco
 */
public class ReportesTest {
    
    public ReportesTest() {
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
    // ProyectoFacade.getInstance().exportarAExcelInvProySubdisc();
         InvestigadorFacade.getInstance().exportarAExcelDatosDocentesCatTelMail();
     }
}
