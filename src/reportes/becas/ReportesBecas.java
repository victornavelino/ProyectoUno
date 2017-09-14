/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes.becas;

import entidades.becas.Becas;
import facade.ConexionFacade;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import reportes.Reporte;

/**
 *
 * @author huguito
 */
public class ReportesBecas {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    
    public void dialogoReporte(JasperPrint jasperPrint, String titulo) {
        //abro el reporte en un dialog
        JDialog dialogo = new JDialog();
        dialogo.getContentPane().add(new JRViewer(jasperPrint));
        dialogo.setModal(true);
        dialogo.setTitle(titulo);
        dialogo.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        dialogo.pack();
        dialogo.setVisible(true);
    }
    
public void reporteBecas(Long inscripto) {
        try {
            Map parameters = new HashMap();
            parameters.put("inscripto", inscripto);
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/becas/reporteBecas.jasper"), parameters);
            dialogoReporte(jasperPrint, "Inscripcion");
            
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void reporteHistoricoBecas() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/becas/historicoBecas.jasper"), parameters);
            dialogoReporte(jasperPrint, "Becas - Convocatorias - Historicos");
            
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void reporteVigentesBecas() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/becas/vigentesBecas.jasper"), parameters);
            dialogoReporte(jasperPrint, "Becas en Vigencia");
            
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void reportePostulantesTodos() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/becas/postulantesTodosBecas.jasper"), parameters);
            dialogoReporte(jasperPrint, "Postulante inscriptos - Historicos");
            
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void reportePostulantesVigentes() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/becas/postulantesVigentesBecas.jasper"), parameters);
            dialogoReporte(jasperPrint, "Postulante inscriptos - Vigentes");
            
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
