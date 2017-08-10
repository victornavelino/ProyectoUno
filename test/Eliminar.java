/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Participacion;
import facade.InvestigadorFacade;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Panchi
 */
public class Eliminar {

    public Eliminar() {
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
    public void eliminar() {
        Investigador investigador = (Investigador) InvestigadorFacade.getInstance().buscarApellidoNombre("ABARZA", "Faustino Orlando");
        List<Participacion> lista = new ArrayList();
        lista = investigador.getParticipacionesProyecto();
        for (Participacion p : lista) {
            System.out.println(p);
        }
        //Los datos comentados son por el cambio en la entidad Investigador
       //ahora estan en la entidad Docencia y Especializaciones esos datos (HUGO)
        investigador.setPersona(null);
        investigador.setActividadesConduccion(null);
        investigador.setBecas(null);
//        investigador.setCategoriaDocente(null);
        investigador.setCategorizaciones(null);
        investigador.setCursosDictados(null);
//        investigador.setDedicacionDocente(null);
//        investigador.setDepartamentoDocente(null);
//        investigador.setEspecialidadActividadAcademica(null);
//        investigador.setEspecialidadInvestigacion(null);
        investigador.setFormacionesAcademicasGrado(null);
        investigador.setFormacionesAcademicasOtras(null);
        investigador.setFormacionesAcademicasPosgrado(null);
        investigador.setParticipacionesProyecto(null);
        investigador.setResoluciones(null);
//        investigador.setUniversidad(null);
//        investigador.setUnidadAcademica(null);
//        investigador.setModoObtencionCargo(null);

        System.out.println(investigador);
        lista = investigador.getParticipacionesProyecto();
        for (Participacion p : lista) {
            System.out.println(p);
        }

    }
}
