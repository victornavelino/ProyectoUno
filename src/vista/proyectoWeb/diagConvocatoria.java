/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.proyectoWeb;

import entidades.Documento;
import entidades.Resolucion;
import entidades.categorizacion.ValorCategoria;
import entidades.proyectoWeb.ArchivoWeb;
import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.ProyectoWeb;
import facade.ProyectoWebFacade;
import facade.ValorCategoriaFacade;
import includes.Comunes;
import includes.ModeloTablaNoEditable;
import includes.SuperDialog;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author franco
 */
public class diagConvocatoria extends SuperDialog {

    List<Convocatoria> list = new ArrayList<>();
    Convocatoria convocatoriaSeleccionada;
    ModeloTablaNoEditable modeloTabla = new ModeloTablaNoEditable();
    List<ValorCategoria> categorias = new ArrayList<>();

    /**
     * Creates new form diagConvocatoria
     */
    public diagConvocatoria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarComponentes();
        super.setTitle("Gestión de Convocatorias");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreLabel = new javax.swing.JLabel();
        monotoMaximoLabel = new javax.swing.JLabel();
        descripcionField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        montoMaximoField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        descripcionLabel = new javax.swing.JLabel();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        nombreField = new javax.swing.JTextField();
        fechaFinLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        fechaIniLabel = new javax.swing.JLabel();
        dateFechaIni = new org.jdesktop.swingx.JXDatePicker();
        dateFechaFin = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        pnCategorias = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCategoriasDisponibles = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCategoriasConvocatoria = new javax.swing.JList();
        btnAgregar = new javax.swing.JButton();
        btQuitar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nombreLabel.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.nombreLabel.text")); // NOI18N

        monotoMaximoLabel.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.monotoMaximoLabel.text")); // NOI18N

        deleteButton.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.deleteButton.text")); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        refreshButton.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.refreshButton.text")); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        newButton.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.newButton.text")); // NOI18N
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        descripcionLabel.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.descripcionLabel.text")); // NOI18N

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Descripcion", "Inicio", "Fin", "Monto Maximo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        masterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterTableMouseClicked(evt);
            }
        });
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.masterTable.columnModel.title0")); // NOI18N
            masterTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.masterTable.columnModel.title5")); // NOI18N
            masterTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.masterTable.columnModel.title1")); // NOI18N
            masterTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.masterTable.columnModel.title2")); // NOI18N
            masterTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.masterTable.columnModel.title3")); // NOI18N
            masterTable.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.masterTable.columnModel.title4")); // NOI18N
        }

        fechaFinLabel.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.fechaFinLabel.text")); // NOI18N

        saveButton.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.saveButton.text")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        fechaIniLabel.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.fechaIniLabel.text")); // NOI18N

        dateFechaIni.setFormats("dd/MM/yyyy");

        dateFechaFin.setFormats("dd/MM/yyyy");

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.jLabel1.text")); // NOI18N

        pnCategorias.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.pnCategorias.border.title"))); // NOI18N

        lstCategoriasDisponibles.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstCategoriasDisponibles);

        lstCategoriasConvocatoria.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstCategoriasConvocatoria);

        btnAgregar.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.btnAgregar.text")); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btQuitar.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.btQuitar.text")); // NOI18N
        btQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCategoriasLayout = new javax.swing.GroupLayout(pnCategorias);
        pnCategorias.setLayout(pnCategoriasLayout);
        pnCategoriasLayout.setHorizontalGroup(
            pnCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCategoriasLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addComponent(btQuitar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        pnCategoriasLayout.setVerticalGroup(
            pnCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCategoriasLayout.createSequentialGroup()
                .addGroup(pnCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnCategoriasLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btQuitar)))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagConvocatoria.class, "diagConvocatoria.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionLabel)
                            .addComponent(fechaFinLabel)
                            .addComponent(fechaIniLabel)
                            .addComponent(monotoMaximoLabel)
                            .addComponent(nombreLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(descripcionField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(dateFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFechaIni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreField)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(montoMaximoField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(pnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(masterScrollPane))
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreLabel)
                            .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descripcionLabel)
                            .addComponent(descripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaIniLabel)
                            .addComponent(dateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaFinLabel)
                            .addComponent(dateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monotoMaximoLabel)
                            .addComponent(montoMaximoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(pnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton)
                            .addComponent(refreshButton)
                            .addComponent(deleteButton)
                            .addComponent(newButton))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            ProyectoWebFacade.getInstance().eliminar(convocatoriaSeleccionada);
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            inicializarComponentes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "No se pudo borrar la convocatoria");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        inicializarComponentes();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        nuevaConvocatoria();

    }//GEN-LAST:event_newButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        guardarConvocatoria(convocatoriaSeleccionada);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        seleccionar();
    }//GEN-LAST:event_masterTableMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarActionPerformed
        quitar();
    }//GEN-LAST:event_btQuitarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verProyectos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        exportarCVarConvocatoria();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(diagConvocatoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagConvocatoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagConvocatoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagConvocatoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagConvocatoria dialog = new diagConvocatoria(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btQuitar;
    private javax.swing.JButton btnAgregar;
    private org.jdesktop.swingx.JXDatePicker dateFechaFin;
    private org.jdesktop.swingx.JXDatePicker dateFechaIni;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField descripcionField;
    private javax.swing.JLabel descripcionLabel;
    private javax.swing.JLabel fechaFinLabel;
    private javax.swing.JLabel fechaIniLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstCategoriasConvocatoria;
    private javax.swing.JList lstCategoriasDisponibles;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel monotoMaximoLabel;
    private javax.swing.JTextField montoMaximoField;
    private javax.swing.JButton newButton;
    private javax.swing.JTextField nombreField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JPanel pnCategorias;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        java.util.Collection listaConvocatorias = ProyectoWebFacade.getInstance().listarConvocatorias();
        list.clear();
        list.addAll(listaConvocatorias);
        cargar(list);
        limpiarFields();
        deshabilitarFields();
        cargarCategorias();
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        lstCategoriasConvocatoria.setEnabled(false);
        lstCategoriasDisponibles.setEnabled(false);
        btQuitar.setEnabled(false);
        btnAgregar.setEnabled(false);
    }

    private void habilitarFields() {
        nombreField.setEnabled(true);
        descripcionField.setEnabled(true);
        dateFechaIni.setEnabled(true);
        dateFechaFin.setEnabled(true);
        montoMaximoField.setEnabled(true);

    }

    private void deshabilitarFields() {
        nombreField.setEnabled(false);
        descripcionField.setEnabled(false);
        dateFechaIni.setEnabled(false);
        dateFechaFin.setEnabled(false);
        montoMaximoField.setEnabled(false);
    }

    private void limpiarFields() {
        nombreField.setText("");
        descripcionField.setText("");
        montoMaximoField.setText("");
        dateFechaIni.setDate(new Date());
        dateFechaFin.setDate(new Date());
    }

    private void cargarEncabezadosTabla(ModeloTablaNoEditable modeloTablaMensajes) {
        modeloTablaMensajes.addColumn("Id");
        modeloTablaMensajes.addColumn("Nombre");
        modeloTablaMensajes.addColumn("Descripcion");
        modeloTablaMensajes.addColumn("Fecha Inicio");
        modeloTablaMensajes.addColumn("Fecha Fin");
        modeloTablaMensajes.addColumn("Monto Máximo");
        modeloTablaMensajes.addColumn("Categorias Permitidas");

        masterTable.setModel(modeloTablaMensajes);
    }

    private void cargar(List<Convocatoria> lista) {
        modeloTabla = new ModeloTablaNoEditable();
        cargarEncabezadosTabla(modeloTabla);
        try {
            for (Convocatoria m : lista) {
                modeloTabla.addRow(cargarMensaje(m));

            }
            masterTable.setModel(modeloTabla);
        } catch (Exception ex) {
        }

    }

    private Object[] cargarMensaje(Convocatoria c) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Object[] fila = new Object[7];
        fila[0] = c.getId();
        fila[1] = c.getNombre();
        fila[2] = c.getDescripcion();
        try {
            fila[3] = formato.format(c.getFechaIni());
        } catch (Exception ex) {
        }
        try {
            fila[4] = formato.format(c.getFechaFin());
        } catch (Exception ex) {
        }

        fila[5] = c.getMontoMaximo();
        if (c.getCategorias().isEmpty()) {
            fila[6] = "Todas";
        } else {
            String fila6 = "";
            for (ValorCategoria val : c.getCategorias()) {
                fila6 += val + " - ";
            }
            fila[6] = fila6.substring(0, fila6.length() - 2);
        }
        return fila;

    }

    private void seleccionar() {
        try {
            convocatoriaSeleccionada = ProyectoWebFacade.getInstance().buscarConvocatoria((Long) masterTable.getValueAt(masterTable.getSelectedRow(), 0));
            categorias = convocatoriaSeleccionada.getCategorias();
            cargarCampos();
            saveButton.setEnabled(true);
            deleteButton.setEnabled(true);
            newButton.setEnabled(false);
            lstCategoriasConvocatoria.setEnabled(true);
            lstCategoriasDisponibles.setEnabled(true);
            btQuitar.setEnabled(true);
            btnAgregar.setEnabled(true);
            habilitarFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo seleccionar la convocatoria");
            inicializarComponentes();
        }
    }

    private void cargarCampos() {
        nombreField.setText(convocatoriaSeleccionada.getNombre());
        descripcionField.setText(convocatoriaSeleccionada.getDescripcion());
        montoMaximoField.setText(convocatoriaSeleccionada.getMontoMaximo().toString());
        dateFechaIni.setDate(convocatoriaSeleccionada.getFechaIni());
        dateFechaFin.setDate(convocatoriaSeleccionada.getFechaFin());
        cargarCategorias();
    }

    private void guardarConvocatoria(Convocatoria c) {
        try {
            if (dateFechaFin.getDate().compareTo(dateFechaIni.getDate()) > 0) {
                if (!"".equals(descripcionField.getText())) {
                    if (!"".equals(nombreField.getText())) {
                        c.setNombre(nombreField.getText());
                        c.setDescripcion(descripcionField.getText());
                        c.setMontoMaximo(new BigDecimal(montoMaximoField.getText()));
                        c.setFechaIni(dateFechaIni.getDate());
                        c.setFechaFin(dateFechaFin.getDate());
                        c.setCategorias(categorias);
                        if (convocatoriaSeleccionada.getId() != null) {
                            ProyectoWebFacade.getInstance().editar(c);
                        } else {
                            ProyectoWebFacade.getInstance().alta(c);
                        }
                        inicializarComponentes();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el nombre de la convocatoria");
                        nombreField.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la descripción de la convocatoria");
                    descripcionField.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "La fecha de fin debe ser mayor");
                dateFechaFin.requestFocus();
            }
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese el monto máximo correctamente");
            montoMaximoField.requestFocus();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se puede guardar la convocatoria");
        }
    }

    private void nuevaConvocatoria() {
        convocatoriaSeleccionada = new Convocatoria();
        categorias = new ArrayList<>();
        habilitarFields();
        newButton.setEnabled(false);
        refreshButton.setEnabled(false);
        deleteButton.setEnabled(false);
        saveButton.setEnabled(true);
        lstCategoriasConvocatoria.setEnabled(true);
        lstCategoriasDisponibles.setEnabled(true);
        btQuitar.setEnabled(true);
        btnAgregar.setEnabled(true);
        nombreField.requestFocus();
    }

    private void cargarCategorias() {
        List<ValorCategoria> categoriasDisponibles = ValorCategoriaFacade.getInstance().listarTodosValorCategoria();
        categoriasDisponibles.removeAll(categorias);
        Comunes.cargarJList(lstCategoriasDisponibles, categoriasDisponibles);
        Comunes.cargarJList(lstCategoriasConvocatoria, categorias);
    }

    private void agregar() {
        if (!lstCategoriasDisponibles.getSelectedValuesList().isEmpty()) {
            categorias.addAll(lstCategoriasDisponibles.getSelectedValuesList());
        }
        cargarCategorias();
    }

    private void quitar() {
        if (!lstCategoriasConvocatoria.getSelectedValuesList().isEmpty()) {
            categorias.removeAll(lstCategoriasConvocatoria.getSelectedValuesList());
        }
        cargarCategorias();

    }

    private void verProyectos() {
        if (convocatoriaSeleccionada != null) {
            if (convocatoriaSeleccionada.getId() != null) {
//                try {
                DiagProyectosWeb datosProyecto = new DiagProyectosWeb(null, true, convocatoriaSeleccionada);
                datosProyecto.setLocation(Comunes.centrarDialog(datosProyecto));
                datosProyecto.setVisible(true);
//                } catch (Exception ex) {
//                    System.out.println("Error cargando proyectos: "+ ex);
//                    JOptionPane.showMessageDialog(rootPane, "No se pudiero ver los proyectos");
//                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "No selecciono una convocatoria");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "No selecciono una convocatoria");
        }
    }

    private void exportarCVarConvocatoria() {
        if (convocatoriaSeleccionada != null) {
            if (convocatoriaSeleccionada.getId() != null) {

                try {
                    for (ProyectoWeb proyectoWeb : ProyectoWebFacade.getInstance().listar(convocatoriaSeleccionada)) {
                        // create byte buffer
                        String zipFile = "/home/hugo/" + proyectoWeb.getParticipacionesWeb().get(0).getInvestigador().toString() + ".zip";
                        FileOutputStream fos = new FileOutputStream(zipFile);
                        ZipOutputStream zos = new ZipOutputStream(fos);
                        
                        for (ArchivoWeb archivoWeb : proyectoWeb.getLstArchivoWeb()) {

                            byte[] archivoInterno = archivoWeb.getContenidoArchivo();
                            File srcFile = File.createTempFile("tmp", archivoWeb.getNombre());
                            FileInputStream fis = new FileInputStream(srcFile);
                            zos.putNextEntry(new ZipEntry(srcFile.getName()));
                            zos.write(archivoInterno);
                            zos.closeEntry();
                            fis.close();

                        }

                    }

                } catch (IOException ex) {
                        Comunes.mensajeError(ex, "Error creating zip file: ");
                } catch (Exception ex) {
                    Comunes.mensajeError(ex, "No se pudo abrir el documento seleccionado");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "No selecciono una convocatoria");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "No selecciono una convocatoria");
        }
    }

}
