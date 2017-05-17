/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.persona.investigador.Investigador;
import facade.InvestigadorFacade;
import includes.Comunes;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vista.diagInvestigadorBusquedaSimple;
import static org.junit.Assert.*;
import reportes.Reporte;

/**
 *
 * @author Administrador
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
        //  new reportes.Reporte().reporteParticipacionesInvestigador();
        //  new reportes.Reporte().reporteParticipacionesProyectos();
    }

    @Test
    public void hello2() {
//        new reportes.Reporte().reporteGraficoProyectoPorUnidadAcademica();
//        new reportes.Reporte().reporteInvestigadores();
//        new reportes.Reporte().reporteProyectos();
    }

    @Test
    public void hello3() {
        //new reportes.Reporte().reporteCategorizacion();
        //new reportes.Reporte().reporteGraficoCategorizaciones();
//       new reportes.Reporte().mostrarReporteCategorizacionInvestigador();
//       new reportes.Reporte().mostrarReporteParticionesUnInvestigador();
        InvestigadorFacade.getInstance().exportarAExcel();
    }
}
