/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.ArrayList;
import entidades.proyecto.Evaluacion;
import entidades.proyecto.Proyecto;
import facade.EvaluacionFacade;
import facade.ProyectoFacade;
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
public class EvaluacionTest {
    
    public EvaluacionTest() {
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
         Proyecto p = ProyectoFacade.getInstance().getUltimoProyecto();
         Evaluacion e =  EvaluacionFacade.getInstance().buscar(1L);
         Evaluacion e2 =  EvaluacionFacade.getInstance().buscar(1L);
         List<Evaluacion> lista = new ArrayList<Evaluacion>();
         //System.out.println(e);
         lista.add(e);
         lista.add(e2);
         p.setEvaluaciones(lista);
         ProyectoFacade.getInstance().modificar(p);
//         for(Evaluacion ev  : p.getEvaluaciones()){
//             System.out.println(ev);
//         }
         
         
}}
