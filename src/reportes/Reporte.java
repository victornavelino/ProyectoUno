/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import entidades.proyecto.Rol;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyectoWeb.ProyectoWeb;
import facade.ConexionFacade;
import includes.CvarWsClient;
import java.awt.Toolkit;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.xml.sax.InputSource;
import vista.diagInvestigadorBusquedaSimple;
import vista.diagProyectoBusquedaSimple;

/**
 *
 * @author Administrador
 */
public class Reporte {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    private void dialogoReporte(JasperPrint jasperPrint, String titulo) {
        //abro el reporte en un dialog
        JDialog dialogo = new JDialog();
        dialogo.getContentPane().add(new JRViewer(jasperPrint));
        dialogo.setModal(true);
        dialogo.setTitle(titulo);
        dialogo.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        dialogo.pack();
        //dialogo.setAlwaysOnTop(true);
        dialogo.setVisible(true);
    }

    public void reporteProyectos() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteProyectos.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de proyectos");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteGraficoProyectoPorUnidadAcademica() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteProyectosGraficosUA.jasper"), parameters);
            dialogoReporte(jasperPrint, "Proyectos por Unidad Académica");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteProyectoConvocatoria(ProyectoWeb proyecto) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            //Calendar cal = Calendar.getInstance();
            //cal.setTime(proyecto.getConvocatoria().getFechaIni());
            //parameters.put("anio", String.valueOf(cal.get(Calendar.YEAR)));
            Collections.sort(proyecto.getParticipacionesWeb());
            parameters.put("proyecto", proyecto);
            //parameters.put("director", ParticipacionWebFacade.getInstance().getDirector(proyecto).getInvestigador().toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteProyecto.jasper"), parameters);
            dialogoReporte(jasperPrint, "Proyectos Web");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteProyectoConvocatoriaVistaPrevia(ProyectoWeb proyecto) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            parameters.put("proyecto", proyecto);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteProyectoVistaPrevia.jasper"), parameters);
            dialogoReporte(jasperPrint, "Proyectos Web Vista Previa");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteInvestigadores() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteInvestigadores.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteParticipacionesInvestigador() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteParticipacionesInvestigador.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteParticipacionesInvestigador(Investigador investigador) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            parameters.put("ID_INVESTIGADOR", investigador.getId());
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteParticipacionesInvestigadorID.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteInvestigadorRol(Rol rol) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            parameters.put("idrol", rol.getId());
            parameters.put("roldescripcion", rol.getDescripcion());
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteInvestigadorRol.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores segun Rol");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteParticipacionesProyectos() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteParticipacionesProyectos.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteCategorizacion() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteCategorizaciones.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteGraficoCategorizaciones() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteUltimaCategorizacionGrafico.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteCategorizacionesInvestigador() {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteInvestigadoresCategorizacion.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteCategorizacionInvestigador(Investigador investigador) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            parameters.put("ID_INVESTIGADOR", investigador.getId());
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteInvestigadorCategorizacion.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteParticipacionesProyectos(Proyecto proyecto) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            parameters.put("ID_PROYECTO", proyecto.getId());
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteParticipacionesProyectosID.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Investigadores");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void mostrarReporteParticionesUnInvestigador() {
        diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
        busquedaSimple.setTitle("Seleccione Investigador para ver roles");
        busquedaSimple.setVisible(true);
        Investigador investigador = busquedaSimple.getInvestigador();
        if (investigador != null) {
            reporteParticipacionesInvestigador(investigador);
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
        }
    }

    public void mostrarReporteCategorizacionInvestigador() {
        diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
        busquedaSimple.setTitle("Seleccione Investigador para ver sus categorizaciones");
        busquedaSimple.setVisible(true);
        Investigador investigador = busquedaSimple.getInvestigador();
        if (investigador != null) {
            reporteCategorizacionInvestigador(investigador);
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
        }
    }

    public void mostrarReporteParticipacionProyecto() {
        diagProyectoBusquedaSimple busquedaSimple = new diagProyectoBusquedaSimple(null, true);
        busquedaSimple.setTitle("Seleccione un proyecto para ver sus integrantes");
        busquedaSimple.setVisible(true);
        Proyecto proyecto = busquedaSimple.getProyecto();
        if (proyecto != null) {
            reporteParticipacionesProyectos(proyecto);
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún proyecto");
        }
    }

    public void reporteProyectoVinculacion(ProyectoVinculacion p) {
        try {
            Map parameters = new HashMap();
            parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            parameters.put("ID_PROYECTO", p.getId());
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteProyectosVinculacion.jasper"), parameters);
            dialogoReporte(jasperPrint, "Listado de Proyectos de Vinculación");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteCronogramaDesembolsos(List<Desembolso> lista, String titulo) {
        try {
            Map parameters = new HashMap();

            // parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            //parameters.put("ID_FINANCIACION", l);
            parameters.put("TITULO_PROYECTO", titulo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteCronogramaDesembolsos.jasper"), parameters, new JRBeanCollectionDataSource(lista));

            //   JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteCronogramaDesembolsos.jasper"), parameters);
            dialogoReporte(jasperPrint, "Cronograma de Desembolsos");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void reporteConvenio1(Anexo1 a, String titulo) throws JRException {

        Map parameters = new HashMap();
        parameters.put("titulo", titulo);
        List<Anexo1> lista = new ArrayList<>();
        lista.add(a);
        JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteAnexo1Convenio1.jasper"), parameters, new JRBeanCollectionDataSource(lista));
        dialogoReporte(jasperPrint, "Reporte 1 Anexo Convenio");

    }

    public void reporteConvenio2(String titulo, List<Anexo2> lista) {
        try {

            Map parameters = new HashMap();
            parameters.put("titulo", titulo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/reporteAnexo1Convenio2.jasper"), parameters, new JRBeanCollectionDataSource(lista));
            dialogoReporte(jasperPrint, "Reporte 2 Anexo Convenio");
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportePicto(String titulo, List<Item> lista, Maestro m) {
        try {
            Map parametros = new HashMap();
            parametros.put("titulo", titulo);
            parametros.put("areatematica", m.getAreatematica());
            parametros.put("categoria", m.getCategoria());
            parametros.put("duracion", m.getDuracion());
            parametros.put("institucionbeneficiaria", m.getInstitucionbeneficiaria());
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/reportPict-o.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
            dialogoReporte(jasperPrint, "Financiacion PICTO");

        } catch (Exception ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public void reportePict(String titulo, List<Item> lista, Maestro m) {
        try {
            Map parametros = new HashMap();
            parametros.put("titulo", titulo);
            parametros.put("areatematica", m.getAreatematica());
            parametros.put("categoria", m.getCategoria());
            parametros.put("duracion", m.getDuracion());
            parametros.put("institucionbeneficiaria", m.getInstitucionbeneficiaria());
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/reportPict-o.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
            dialogoReporte(jasperPrint, "Financiacion PICT");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void reporteSeguimientoNi(String titulo, List<NotaIn> lista) {
        try {
            Map parametros = new HashMap();
            parametros.put("titulo", titulo);
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/ReportNotaIn.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
            dialogoReporte(jasperPrint, "Seguimiento");
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }

    public void reporteSeguimientoNe(String titulo, List<NotaEx> lista) {
        try {
            Map parametros = new HashMap();
            parametros.put("titulo", titulo);
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/reportNotaEx.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
            dialogoReporte(jasperPrint, "Seguimiento");
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void reporteCvAr(String cuil) {
        try {
            String directorio = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src"
                    + System.getProperty("file.separator") + "reportes"
                    + System.getProperty("file.separator") + "CV_jasper"
                    + System.getProperty("file.separator");
            Map parametros = new HashMap();
            parametros.put("SUBREPORT_DIR", directorio);
            parametros.put("STYLE_DIR", directorio);
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/CV_jasper/CV_Incentivos.jasper"));
            String xml = new CvarWsClient().armarFinal(cuil);
            org.w3c.dom.Document doc = convertStringToDocument(xml);
            System.out.println(doc.getDocumentElement());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,
                    new JRXmlDataSource(doc, "datos//datos"));
            dialogoReporte(jasperPrint, "Seguimiento");
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    private static org.w3c.dom.Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //factory.setNamespaceAware(true);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error de conversion");
        }
        return null;
    }
}
