/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proyectoWeb;

import controladores.ParticipacionWebJpaController;
import controladores.ProyectoJpaController;
import entidades.Resolucion;
import entidades.persona.investigador.Investigador;
import entidades.proyectoWeb.ArchivoWeb;
import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.ParticipacionWeb;
import entidades.proyectoWeb.PresupuestoWeb;
import entidades.proyectoWeb.ProyectoWeb;
import facade.CategorizacionFacade;
import facade.ConexionFacade;
import facade.InvestigadorFacade;
import facade.ProyectoFacade;
import facade.ProyectoWebFacade;
import facade.ResolucionFacade;
import facade.RolFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.crypto.dsig.keyinfo.PGPData;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author franco
 */
public class ProyectoWebTest {

    public ProyectoWebTest() {
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        ParticipacionWebJpaController webJpaController = new ParticipacionWebJpaController(emf);
        
            //proyectoweb
//        ProyectoWeb pW = new ProyectoWeb();
//        pW.setAntecedentes("notiene");
//        pW.setAprobado(Boolean.FALSE);
//        pW.setCapacitacionFormacionRH("capacitacion rrhh");
//        pW.setCodigo("codigo666");
//        pW.setConvocatoria(null);
//        pW.setCronogramaTrabajo("cronograma");
//        pW.setFechaArchivo(new Date());
//        pW.setFechaFinalizacion(new Date());
//        pW.setFechaInicio(new Date());
//        pW.setFinalizado(Boolean.TRUE);
//        pW.setTitulo("tituñppppooo");
//        ProyectoWebFacade.getInstance().alta(pW);
            //archivos web
//        System.out.println("resolucions pruebas"+new ResolucionFacade().buscar("prueba"));
//        try {
//            ProyectoWeb proyectoWeb = ProyectoWebFacade.getInstance().buscar(2L);
//            for (Resolucion resolucion : new ResolucionFacade().buscar("convenio")) {
//                ArchivoWeb archivoWeb= new ArchivoWeb();
//                archivoWeb.setContenidoArchivo(resolucion.getDocumento().getContenidoArchivo());
//                archivoWeb.setNombre(resolucion.getDocumento().getNombreArchivo());
//                 proyectoWeb.getLstArchivoWeb().add(archivoWeb);
//            }
//            ProyectoWebFacade.getInstance().editar(proyectoWeb);
//        } catch (Exception ex) {
//            Logger.getLogger(ProyectoWebTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

        //eliminar proyecto
        try{
          new ProyectoJpaController(emf).destroy(538L);  
        }catch(Exception e){
            
        }
        
        
        
        //participacion web
//        ParticipacionWeb participacionWeb = new ParticipacionWeb();
//        participacionWeb.setDedicacionSemanal(12);
//        participacionWeb.setFechaDesde(new Date());
//        participacionWeb.setFechaHasta(new Date());
//        participacionWeb.setInvestigador(InvestigadorFacade.getInstance().buscar(2L));
//        try {
//            participacionWeb.setProyectoWeb(ProyectoWebFacade.getInstance().buscar(2L));
//        } catch (Exception ex) {
//            Logger.getLogger(ProyectoWebTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //participacionWeb.setRol(RolFacade);
//        webJpaController.create(participacionWeb);
//        try {
//            //       for (Investigador in: CategorizacionFacade.getInstance().CategorizacionesInvestigador()) {
//            //            System.out.println(in.getCategorizaciones().get(in.getCategorizaciones().size()-1));
//            //        }
//            //new reportes.Reporte().reporteProyectoConvocatoria(ProyectoWebFacade.getInstance().listar().get(0));
//            for (ProyectoWeb get : ProyectoWebFacade.getInstance().listar()) {
//                System.out.println(get.getPresupuestoWeb());
//            }
//
//    //        System.out.println("1:"+ProyectoWebFacade.getInstance().listar().get(0).getPresupuestoWebAnioUno());
//            //                System.out.println("2:"+ProyectoWebFacade.getInstance().listar().get(0).getPresupuestoWebAnioUno());
//            //
//            //                        System.out.println("3:"+ProyectoWebFacade.getInstance().listar().get(0).getPresupuestoWebAnioUno());
//            //
//            //                                System.out.println("4"+ProyectoWebFacade.getInstance().listar().get(0).getPresupuestoWebAnioUno());
//        } catch (Exception ex) {
//            Logger.getLogger(ProyectoWebTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
