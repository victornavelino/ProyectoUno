/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.mensajes;

import entidades.persona.investigador.AreaSecyt;
import facade.AreaSecytFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author huguito
 */
public class InsertarAreas {

    public InsertarAreas() {
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
    public void insertarAreas() throws Exception {

        AreaSecyt area = new AreaSecyt();
        area.setDescripcion("Categorizaciones");
        AreaSecytFacade.getInstance().alta(area);
        area = new AreaSecyt();
        area.setDescripcion("Becas");
        AreaSecytFacade.getInstance().alta(area);
        area = new AreaSecyt();
        area.setDescripcion("Incentivos");
        AreaSecytFacade.getInstance().alta(area);
        area = new AreaSecyt();
        area.setDescripcion("Proyectos");
        AreaSecytFacade.getInstance().alta(area);
        area = new AreaSecyt();
        area.setDescripcion("Economico");
        AreaSecytFacade.getInstance().alta(area);
        area = new AreaSecyt();
        area.setDescripcion("Vinculacion");
        AreaSecytFacade.getInstance().alta(area);
    }
}