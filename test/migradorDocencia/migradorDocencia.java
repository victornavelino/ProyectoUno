/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migradorDocencia;

import entidades.persona.investigador.Docencia;
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
public class migradorDocencia {

    Docencia docencia;
    boolean flag;

    public migradorDocencia() {
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
//            docencia = new Docencia();
//            try {
//                docencia.setUniversidad(investigadorOrginial.getUniversidad());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setUnidadAcademica(investigadorOrginial.getUnidadAcademica());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setDepartamentoDocente(investigadorOrginial.getDepartamentoDocente());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setCategoriaDocente(investigadorOrginial.getCategoriaDocente());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setDedicacionDocente(investigadorOrginial.getDedicacionDocente());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setModoObtencionCargo(investigadorOrginial.getModoObtencionCargo());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setFechaObtencionCargo(investigadorOrginial.getFechaObtencionCargo());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setHorasDedicadasDocenciaPrimerCuatrimestre(investigadorOrginial.getHorasDedicadasDocenciaPrimerCuatrimestre());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setSemanasDedicadasDocenciaPrimerCuatrimestre(investigadorOrginial.getSemanasDedicadasDocenciaPrimerCuatrimestre());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setHorasDedicadasDocenciaSegundoCuatrimestre(investigadorOrginial.getHorasDedicadasDocenciaSegundoCuatrimestre());
//                flag = true;
//            } catch (Exception ex) {
//            }
//            try {
//                docencia.setSemanasDedicadasDocenciaSegundoCuatrimestre(investigadorOrginial.getSemanasDedicadasDocenciaSegundoCuatrimestre());
//                flag = true;
//            } catch (Exception ex) {
//            }
//
//            docencia.setAño(2012);
//
//            if (flag) {
//                contador++;
//                docencia.setInvestigador(investigadorOrginial);
//                investigadorOrginial.getDocencias().add(docencia);
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
//
  }
}