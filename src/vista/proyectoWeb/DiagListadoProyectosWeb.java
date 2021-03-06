/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.proyectoWeb;

import controladores.ConvocatoriaJpaController;
import entidades.persona.CorreoElectronico;
import entidades.proyecto.CampoAplicacion;
import entidades.proyecto.Participacion;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.UnidadInvestigacion;
import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.ParticipacionWeb;
import entidades.proyectoWeb.ProyectoWeb;
import facade.ConexionFacade;
import facade.ParticipacionWebFacade;
import facade.ProyectoWebFacade;
import includes.Comunes;
import includes.ExportarExcel;
import includes.ModeloTablaNoEditable;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author franco
 */
public class DiagListadoProyectosWeb extends javax.swing.JDialog {

    private ModeloTablaNoEditable modeloTablaProyectosWeb;
    private Vector headers = new Vector();
    private Vector data = new Vector();

    /**
     * Creates new form DiagListadoVentas
     *
     * @param parent
     * @param modal
     */
    public DiagListadoProyectosWeb(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarComponentes();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProyectosWeb = new javax.swing.JTable();
        btnExportarArt = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnCargarConvocatorias = new javax.swing.JButton();
        cboConvocatoria = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblProyectosWeb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblProyectosWeb);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExportarArt.setText(org.openide.util.NbBundle.getMessage(DiagListadoProyectosWeb.class, "DiagListadoProyectosWeb.btnExportarArt.text")); // NOI18N
        btnExportarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarArtActionPerformed(evt);
            }
        });

        btnCerrar.setText(org.openide.util.NbBundle.getMessage(DiagListadoProyectosWeb.class, "DiagListadoProyectosWeb.btnCerrar.text")); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnCargarConvocatorias.setText(org.openide.util.NbBundle.getMessage(DiagListadoProyectosWeb.class, "DiagListadoProyectosWeb.btnCargarConvocatorias.text")); // NOI18N
        btnCargarConvocatorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarConvocatoriasActionPerformed(evt);
            }
        });

        cboConvocatoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(DiagListadoProyectosWeb.class, "DiagListadoProyectosWeb.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboConvocatoria, 0, 547, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCargarConvocatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(389, 389, 389))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(538, 538, 538))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarConvocatorias)
                    .addComponent(cboConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExportarArt, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarArtActionPerformed
        Exportar();
    }//GEN-LAST:event_btnExportarArtActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCargarConvocatoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarConvocatoriasActionPerformed
        cargarConvocatorias();
    }//GEN-LAST:event_btnCargarConvocatoriasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DiagListadoProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiagListadoProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiagListadoProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiagListadoProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiagListadoProyectosWeb dialog = new DiagListadoProyectosWeb(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarConvocatorias;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnExportarArt;
    private javax.swing.JComboBox cboConvocatoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProyectosWeb;
    // End of variables declaration//GEN-END:variables

    private void Exportar() {
        new ExportarExcel().crearExcelJtable(tblProyectosWeb, "Listado de ProyectosWeb");
    }

    private void cargarTablaProyectosWeb(List<ProyectoWeb> proyectoWeb) {
        modeloTablaProyectosWeb = new ModeloTablaNoEditable();
        cargarEncabezadosTablaProyectosWeb(modeloTablaProyectosWeb);
        configurarTabla(tblProyectosWeb);
        try {
            cargarProyectosWeb(proyectoWeb);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex);
        }
    }

    private void cargarEncabezadosTablaProyectosWeb(ModeloTablaNoEditable modeloTablaProyectosWeb) {
        modeloTablaProyectosWeb.addColumn("Nombre Proyecto");
        modeloTablaProyectosWeb.addColumn("Financiamiento");
        modeloTablaProyectosWeb.addColumn("Duracion (Fecha Inicio)");
        modeloTablaProyectosWeb.addColumn("Duracion (Fecha Fin)");
        modeloTablaProyectosWeb.addColumn("Participa en Programa");
        modeloTablaProyectosWeb.addColumn("Director");
        modeloTablaProyectosWeb.addColumn("Sub-Director");
        modeloTablaProyectosWeb.addColumn("Unidad Academica");
        modeloTablaProyectosWeb.addColumn("Unidad Ejecutora");
        modeloTablaProyectosWeb.addColumn("Localizacion Fisica Proyecto");
        modeloTablaProyectosWeb.addColumn("Linea Prioritaria");
        modeloTablaProyectosWeb.addColumn("Sector Prioritario");
        modeloTablaProyectosWeb.addColumn("Campo Aplicacion");
        modeloTablaProyectosWeb.addColumn("Objetivo SocioEconomico");
        modeloTablaProyectosWeb.addColumn("Tipo Actividad");
        modeloTablaProyectosWeb.addColumn("Area Tematica");
        modeloTablaProyectosWeb.addColumn("Disciplina Cientifica");
        modeloTablaProyectosWeb.addColumn("SubDisciplina Cientifica");
        modeloTablaProyectosWeb.addColumn("Proyecto Orientado");
        modeloTablaProyectosWeb.addColumn("Finalizado");
        modeloTablaProyectosWeb.addColumn("Correos Electronicos");

        tblProyectosWeb.setModel(modeloTablaProyectosWeb);
    }

    private void cargarProyectoWeb(ProyectoWeb proyectoWeb) {
        // JOptionPane.showMessageDialog(null, "va "+va.getCantidadPeso());
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        Object[] fila = new Object[21];
        int n = 0;
        try {
            fila[n++] = proyectoWeb.getTitulo();
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        if (proyectoWeb.getTipoFinanciamiento() != null) {
            fila[n++] = proyectoWeb.getTipoFinanciamiento().getName();
        } else {
            fila[n++] = "n/n";
        }

        if (proyectoWeb.getFechaInicio() != null) {
            fila[n++] = formats.format(proyectoWeb.getFechaInicio());
        } else {
            fila[n++] = "n/n";
        }
        if (proyectoWeb.getFechaFinalizacion() != null) {
            fila[n++] = formats.format(proyectoWeb.getFechaFinalizacion());
        } else {
            fila[n++] = "n/n";
        }
        try {
            if (proyectoWeb.getParticipaEnPrograma()) {
                fila[n++] = "SI";
            } else {
                fila[n++] = "NO";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        for (ParticipacionWeb participacionWeb : proyectoWeb.getParticipacionesWeb()) {
            if (participacionWeb.getRol().getDescripcion().equals("Director")) {
                fila[n++] = participacionWeb.getInvestigador();
            }
        }
        for (ParticipacionWeb participacionWeb : proyectoWeb.getParticipacionesWeb()) {
            if (participacionWeb.getRol().getDescripcion().equals("Sub-Director")) {
                fila[n++] = participacionWeb.getInvestigador();
            }
        }

        if (proyectoWeb.getUnidadAcademica() != null) {
            fila[n++] = proyectoWeb.getUnidadAcademica();
        } else {
            fila[n++] = "n/n";
        }
        try {
            if (proyectoWeb.getUnidadEjecutora() != null) {
                fila[n++] = proyectoWeb.getUnidadEjecutora();
            } else {
                fila[n++] = "n/n";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        if (!proyectoWeb.getLocalizaciones().isEmpty()) {
            for (UnidadInvestigacion unidadInvestigacion : proyectoWeb.getLocalizaciones()) {
                fila[n++] = unidadInvestigacion + "; ";
            }
        } else {
            fila[n++] = "n/n";
        }
        try {
            if (proyectoWeb.getLineaPrioritaria() != null) {
                fila[n++] = proyectoWeb.getLineaPrioritaria();
            } else {
                fila[n++] = "n/n";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }
        try {
            if (proyectoWeb.getSectorPrioritario() != null) {
                fila[n++] = proyectoWeb.getSectorPrioritario();
            } else {
                fila[n++] = "n/n";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        try {
            if (!proyectoWeb.getCamposAplicacion().isEmpty()) {
                fila[n++] = proyectoWeb.getCamposAplicacion().get(0);
            } else {
                fila[n++] = "n/n";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        try {
            if (proyectoWeb.getObjetivoSocioeconomico() != null) {
                fila[n++] = proyectoWeb.getObjetivoSocioeconomico();
            } else {
                fila[n++] = "n/n";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        try {
            if (proyectoWeb.getTipoProyecto() != null) {
                fila[n++] = proyectoWeb.getTipoProyecto();
            } else {
                fila[n++] = "n/n";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        if (!proyectoWeb.getSubDisciplinasCientificas().isEmpty()) {
            //PRIMER IF
            System.err.println("ENTRO SUBDISCIPLINA");
            try {

                for (SubDisciplinaCientifica subDisciplinasCientifica : proyectoWeb.getSubDisciplinasCientificas()) {
                    if (subDisciplinasCientifica.getDisciplinaCientifica().getAreaTematica() != null) {
                        fila[n++] = subDisciplinasCientifica.getDisciplinaCientifica().getAreaTematica() + "; ";
                    }
                }
            } catch (Exception e) {
                fila[n++] = "n/n";
            }

            //SERGUDNO IF
            try {
                for (SubDisciplinaCientifica subDisciplinasCientifica : proyectoWeb.getSubDisciplinasCientificas()) {
                    fila[n++] = subDisciplinasCientifica.getDisciplinaCientifica() + "; ";
                }
            } catch (Exception e) {
                fila[n++] = "n/n";
            }
            ////TERCER IF
            try {
                for (SubDisciplinaCientifica subDisciplinasCientifica : proyectoWeb.getSubDisciplinasCientificas()) {
                    fila[n++] = subDisciplinasCientifica + "; ";
                }
            } catch (Exception e) {
                fila[n++] = "n/n";
            }
        } else {
            //System.err.println("ELSE SUBDISCIPLINA");
            fila[n++] = "n/n";
            fila[n++] = "n/n";
            fila[n++] = "n/n";
        }
        try {
            if (proyectoWeb.getProyectoOrientado()) {
                fila[n++] = "SI";
            } else {
                fila[n++] = "NO";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";
        }

        try {
            if (proyectoWeb.getFinalizado()) {
                fila[n++] = "SI";
            } else {
                fila[n++] = "NO";
            }
        } catch (Exception e) {
            fila[n++] = "n/n";

        }
        try {
            List<CorreoElectronico> correosElectronicos = new ArrayList<>();
            correosElectronicos = ParticipacionWebFacade.getInstance().getDirector(proyectoWeb).getInvestigador().getPersona().getCorreosElectronicos();
            
                fila[n++] = correosElectronicos.get(0).getDireccion();
            
        } catch (Exception e) {
            fila[n++] = "n/n";

        }

        //
        modeloTablaProyectosWeb.addRow(fila);
    }

    private void configurarTabla(JTable tbl) {
        JViewport scroll = (JViewport) tbl.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tbl.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (1 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    anchoColumna = (5 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
            tbl.setColumnModel(modeloColumna);
        }
        tbl.getTableHeader().setFont(new java.awt.Font("Dialog",
                java.awt.Font.PLAIN, 10));
        tbl.getTableHeader().setBackground(java.awt.Color.WHITE);
        tbl.getTableHeader().setForeground(Color.BLACK);
        //Si le queremos cambiar el tamaño a la tablita
        tbl.setFont(new java.awt.Font("Dialog",
                java.awt.Font.PLAIN, 10));
    }

    private void cargarProyectosWeb(List<ProyectoWeb> proyectoWebs) {
        try {
            modeloTablaProyectosWeb = new ModeloTablaNoEditable();
            cargarEncabezadosTablaProyectosWeb(modeloTablaProyectosWeb);
            for (ProyectoWeb proyectoWeb : proyectoWebs) {
                cargarProyectoWeb(proyectoWeb);
            }
            tblProyectosWeb.setModel(modeloTablaProyectosWeb);
            // Comunes.setOcultarColumnasJTable(tblVentas, 0);

        } catch (Exception ex) {
            Logger.getLogger(DiagListadoProyectosWeb.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicializarComponentes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        Comunes.cargarJCombo(cboConvocatoria, new ConvocatoriaJpaController(emf).findConvocatoriaEntities());
    }

    private void cargarConvocatorias() {
        try {
            cargarTablaProyectosWeb(ProyectoWebFacade.getInstance().listar(((Convocatoria) cboConvocatoria.getSelectedItem())));

        } catch (Exception ex) {
            Logger.getLogger(DiagListadoProyectosWeb.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
