/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.reportes;

import entidades.proyecto.Participacion;
import entidades.proyecto.Proyecto;
import facade.ParticipacionFacade;
import facade.ProyectoFacade;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hugo
 */
public class testAltaBaja {
    
    public testAltaBaja() {
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
        for (String[] s : ProyectoFacade.getInstance().getproyectosXDirectorAltaBaja()) {
            System.out.println("Proyectp"+s);
        }
     }
}
