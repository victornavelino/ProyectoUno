/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.entidades.proyectos.vinculacion;

import controladores.CronogramaDePagoJpaController;
import controladores.FinanciacionJpaController;
import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
import entidades.proyecto.vinculacion.financiacion.pfip.CronogramaDePago;
import facade.FinanciacionFacade;
import facade.ProyectoVinculacionFacade;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.swing.JRViewer;
import org.junit.*;
import static org.junit.Assert.*;
import reportes.Anexo2;
import reportes.Desembolso;
import reportes.Reporte;

/**
 *
 * @author Panchi
 */
public class CronogramaTest {
    
    public CronogramaTest() {
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
    
      public void prueba(){
          //Float f = Float.parseFloat(jtfValor.getText());
        String s = "100.000,5";
        
        String d = s.replace(".","");
       // s.replaceAll(",", ".");
     //   s.trim();
        System.out.println(d);
          
      }
      
    public void reporte(){
         ProyectoVinculacion p = ProyectoVinculacionFacade.getInstance().getUltimoProyectoVinculacion();
         List<Financiacion> f = p.getFinanciaciones();
         FinanciacionPfip ff = (FinanciacionPfip) f.get(0);
         System.out.println(p.getTitulo());
         List<Desembolso> lista = new ArrayList<>();
            for (CronogramaDePago c : ff.getCronogramasdepagos()){
                System.out.println(c);
                Desembolso d = new Desembolso();
                d.setId(c.getId());
                d.setEtapaDeCronograma(c.getEtapaDeCronograma());
                d.setDesembolso(c.getDesembolso());
                d.setEtapaAEjecutar(c.getEtapaAEjecutar());
                d.setCostoEjecucion(c.getCostoEjecucion());
                d.setSaldo(c.getSaldo());
                lista.add(d);
            }

            new reportes.Reporte().reporteCronogramaDesembolsos(lista, p.getTitulo());
//       new reportes.Reporte().reporteCronogramaDesembolsos(ff.getId(), p.getTitulo());
     }
   
     public void reporte2(){

            Anexo2 a  = new Anexo2();
            a.setNroEtapa(1);
            a.setMesFin(1);
            a.setMesInicio(1);
            a.setMontoContra(3f);
            a.setMontoMincyt(3f);
    //       new reportes.Reporte().reporteConvenio2(a, "asdfa");
          //  new reportes.Reporte().reporteConvenio1("asdfas");
   //    new reportes.Reporte().reportePrueba("asdf");
        //    new reportes.Reporte().reportePrueba("asdf");
      
     }
    
     
     
     public void hello() {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU");
    EntityManager em = emf.createEntityManager();
        FinanciacionJpaController ministerioJpaController = new FinanciacionJpaController(emf);
  CronogramaDePagoJpaController cronoJpaController = new CronogramaDePagoJpaController(emf);

//
//     FinanciacionPfip f = new FinanciacionPfip();
//     ministerioJpaController.create(f);
//     CronogramaDePago c = new CronogramaDePago();
//     c.setFinanciacionPfip(f);
//     cronoJpaController.create(c);
        
        Query quTodasMinisterio = em.createQuery("SELECT c FROM CronogramaDePago c "
                + "");
        List<CronogramaDePago> lista =  quTodasMinisterio.getResultList();
        for(CronogramaDePago cc :lista){
       //     System.out.println(cc.getFinanciacionPfip().getEtapas());
        }
     }

     
       public void reporteFinanciacionCronograma(){
      //   try {
             
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU");
    EntityManager em = emf.createEntityManager();
            // ProyectoVinculacion p = ProyectoVinculacionFacade.getInstance().getUltimoProyectoVinculacion();
//            List<Financiacion> listaf = p.getFinanciaciones();
//           
  //          FinanciacionPfip f = new FinanciacionPfip();
             List<FinanciacionPfip> listaf = FinanciacionFacade.getInstance().getFinanciacionPorDescripcion("PFIP");
                      for(FinanciacionPfip ff : listaf){
                System.out.println(ff.getId());
                
               
            }
            
//                     listaf.add(f);
//             p.setFinanciaciones(listaf);
//             ProyectoVinculacionFacade.getInstance().modificar(p);
//             System.out.println(p.getCodigo());
    //        Map parameters = new HashMap();
//            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
//            parameters.put("ID_PROYECTO", p.getId());
//            System.out.println(p.getId());
//  //          parameters.put("ID_FINANCIACION", f.getId());
//            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/report4.jasper"), parameters);
//            dialogoReporte(jasperPrint, "Listado de Etapas del Proyecto");
//        } catch (JRException ex) {
//            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

  //      }
}
     private void dialogoReporte(JasperPrint jasperPrint, String titulo) {
        //abro el reporte en un dialog
        JDialog dialogo = new JDialog();
        dialogo.getContentPane().add(new JRViewer(jasperPrint));
        dialogo.setModal(true);
        dialogo.setTitle(titulo);
        dialogo.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        dialogo.pack();
        dialogo.setAlwaysOnTop(true);
        dialogo.setVisible(true);
    }}
