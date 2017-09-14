/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.categorizacion;

import entidades.categorizacion.Categorizacion;
import entidades.persona.investigador.Investigador;
import facade.InvestigadorFacade;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author franco
 */
public class CategorizacionTest {

    public CategorizacionTest() {
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
        for (Investigador inv : InvestigadorFacade.getInstance().buscarPorNumeroDocumentoIdentidad(21658383)) {
            List<Categorizacion> categorizaciones = inv.getCategorizaciones();
//            for (Categorizacion cat : categorizaciones) {
//                System.out.println(cat);
//            }
                            System.out.println(categorizaciones.get(categorizaciones.size()-1));

            System.out.println("----------");

            Collections.sort(categorizaciones);
//            for (Categorizacion cat : categorizaciones) {
//                System.out.println(cat);
//            }
                                        System.out.println(categorizaciones.get(categorizaciones.size()-1));
            inv.setCategorizaciones(null);
            InvestigadorFacade.getInstance().modificar(inv);

            inv.setCategorizaciones(categorizaciones);
            
            
            InvestigadorFacade.getInstance().modificar(inv);
        }
    }
}
