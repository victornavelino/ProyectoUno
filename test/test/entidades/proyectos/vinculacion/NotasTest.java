/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.entidades.proyectos.vinculacion;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.NotaExterna;
import entidades.proyecto.vinculacion.NotaInterna;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import facade.ProyectoVinculacionFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author panchi
 */
public class NotasTest {
    
    public NotasTest() {
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
    // @Test
    // public void hello() {}
    
    @Test
    public void moverNotas(){
        List<ProyectoVinculacion> proyectos = ProyectoVinculacionFacade.getInstance().getTodos();
        for (ProyectoVinculacion p : proyectos){
        List<Financiacion> financiaciones = p.getFinanciaciones();
                List<NotaInterna> lii = p.getNotasInternas();
                List<NotaExterna> lie = p.getNotasExternas();
        
        
            for (Financiacion f : financiaciones){
                List<NotaInterna> nis = f.getNotasInternas();
                List<NotaExterna> nes = f.getNotasExternas();

                lii.addAll(nis);
                lie.addAll(nes);
                
            }
            
                p.setNotasInternas(lii);
                p.setNotasExternas(lie);
                ProyectoVinculacionFacade.getInstance().modificar(p);
                
    }
        
    }
}
