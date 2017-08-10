/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migradorEspecializacion;

import migradorDocencia.*;
import entidades.persona.investigador.Docencia;
import entidades.persona.investigador.Especializacion;
import entidades.persona.investigador.Investigador;
import facade.InvestigadorFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.security.util.PropertyExpander;

/**
 *
 * @author huguito
 */
public class migradorEspecializaciones {

    Especializacion especializacion;
    boolean flag;

    public migradorEspecializaciones() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
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
//        int contador = 0;
//
//        for (Investigador investigadorOrginial : InvestigadorFacade.getInstance().getTodosInvestigador()) {
//            System.out.println(investigadorOrginial);
//            flag = false;
//            especializacion = new Especializacion();
//            try {
//                especializacion.setEspecialidadActividadAcademica(investigadorOrginial.getEspecialidadActividadAcademica());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                especializacion.setEspecialidadInvestigacion(investigadorOrginial.getEspecialidadInvestigacion());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                especializacion.setInvestigador(investigadorOrginial);
//                flag = true;
//            } catch (Exception ex) {
//            }
//            especializacion.setAño(2011);
//
//            if (flag) {
//                contador++;
//                investigadorOrginial.getEspecializaciones().add(especializacion);
//                InvestigadorFacade.getInstance().modificar(investigadorOrginial);
//                System.out.println(contador+" - Migrado");
//                
//            } else {
//                System.out.println(" - Sin datos");
//
//            }
//
//
//
//        }
//        System.out.println(contador+" registros importados");

    }
}