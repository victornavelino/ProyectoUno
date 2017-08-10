/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.entidades.proyectos.vinculacion;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionDetem;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
import entidades.proyecto.vinculacion.financiacion.pfip.Consultoria;
import facade.ProyectoVinculacionFacade;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Panchi
 */
public class ConsultoriaTest {
    
    public ConsultoriaTest() {
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
    public void modificarConsultorias() {
        FinanciacionPfip pfip;
        FinanciacionDetem detem;
        List<ProyectoVinculacion> proyectos = ProyectoVinculacionFacade.getInstance().getTodos();

        //List<FinanciacionPfip> proypfip = FinanciacionFacade.getInstance().getFinanciacionPorDescripcion("PFIP");
        for (ProyectoVinculacion p : proyectos) {
           // System.out.println(p.getTitulo());
            List<Financiacion> financiaciones = p.getFinanciaciones();
            for (Financiacion f : financiaciones) {
                //System.out.println(f.getDescripcion());
                if (f.getDescripcion().contains("PFIP") || f.getDescripcion().contains("pfip")) {

                    pfip = (FinanciacionPfip) f;
                    List<Consultoria> consultorias = pfip.getConsultorias();
                    for (Consultoria e : consultorias) {
                     //   System.out.println(e.getMesInicio());
                        Calendar cal = Calendar.getInstance();
                        
                        cal.setTime(p.getFechaInicio());
                        int month = e.getMesInicio();
                        month = month - 1;

                        cal.set(cal.get(Calendar.YEAR), month, 01);
                        e.setFechaInicio(cal.getTime());
                        cal.add(Calendar.MONTH, e.getMesFin()-e.getMesInicio());
                        //cal.setTime(p.getFechaFinalizacion());
                        //System.out.println("FECHAAA" + p.getFechaFinalizacion());
                        //int monthend = e.getMesFin();
                    //    month = e.getMesFin();
                      //  month = month -  1;
                       
                        //cal.set(cal.get(Calendar.YEAR), month, 01);
                       e.setFechaFin(cal.getTime());
           //             System.out.println(cal);
                        try {
                            ProyectoVinculacionFacade.getInstance().modificar(p);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Mal guardado", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
                if (f.getDescripcion().contains("DETEM") || f.getDescripcion().contains("detem")) {
                    detem = (FinanciacionDetem) f;
                    List<Consultoria> consultorias = detem.getConsultorias();
                      for (Consultoria e : consultorias) {
                   //     System.out.println(e.getMesInicio());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(p.getFechaInicio());
                        int month = e.getMesInicio();
                        month = month - 1;

                        cal.set(cal.get(Calendar.YEAR), month, 01);
                        e.setFechaInicio(cal.getTime());
                                                cal.add(Calendar.MONTH, e.getMesFin()-e.getMesInicio());
                       e.setFechaFin(cal.getTime());

                     //   System.out.println(cal);
                        try {
                            ProyectoVinculacionFacade.getInstance().modificar(p);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Mal guardado", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }

            }
        }


    }
}
