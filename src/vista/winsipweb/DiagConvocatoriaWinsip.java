/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.winsipweb;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.Proyecto;
import facade.ConvocatoriaWinsipFacade;
import facade.ProyectoFacade;
import includes.Comunes;
import includes.ModeloTablaNoEditable;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nago
 */
public class DiagConvocatoriaWinsip extends javax.swing.JDialog {

    // private ConvocatoriaWinsip convocatoriaWinsip;
    private ConvocatoriaWinsip convocatoriaWinsipSeleccionada;
    ProyectoFacade proyectoFacade = ProyectoFacade.getInstance();
    private List<Proyecto> proyectos;
    List<ConvocatoriaWinsip> listTablaWinsip = new ArrayList<>();
    private ModeloTablaNoEditable modeloTabla;

    /**
     * Creates new form ConvocatoriaWinsip
     */
    public DiagConvocatoriaWinsip(java.awt.Frame parent, boolean modal) {
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
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        dpApertura = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dpCierre = new org.jdesktop.swingx.JXDatePicker();
        pnProyectos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProyectosDisponibles = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProyectosConvocatoria = new javax.swing.JList();
        btnAgregar = new javax.swing.JButton();
        btQuitar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfBuscarProyectos = new javax.swing.JTextField();
        btnNuevoConvocatoria = new javax.swing.JButton();
        btnEliminarConvocatoria = new javax.swing.JButton();
        btnGuardarConvocatoria = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jPanel1.border.title"))); // NOI18N

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Descripcion", "Apertura", "Cierre", "Habilitada"
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

        jLabel1.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jLabel1.text")); // NOI18N

        tfNombre.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.tfNombre.text")); // NOI18N
        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jLabel2.text")); // NOI18N

        tfDescripcion.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.tfDescripcion.text")); // NOI18N
        tfDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDescripcionActionPerformed(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jLabel4.text")); // NOI18N

        dpCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpCierreActionPerformed(evt);
            }
        });

        pnProyectos.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.pnProyectos.border.title"))); // NOI18N

        jScrollPane1.setViewportView(lstProyectosDisponibles);

        jScrollPane2.setViewportView(lstProyectosConvocatoria);

        btnAgregar.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.btnAgregar.text")); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btQuitar.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.btQuitar.text")); // NOI18N
        btQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jLabel7.text")); // NOI18N

        tfBuscarProyectos.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.tfBuscarProyectos.text")); // NOI18N
        tfBuscarProyectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBuscarProyectosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnProyectosLayout = new javax.swing.GroupLayout(pnProyectos);
        pnProyectos.setLayout(pnProyectosLayout);
        pnProyectosLayout.setHorizontalGroup(
            pnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnProyectosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnProyectosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfBuscarProyectos))
                    .addGroup(pnProyectosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnProyectosLayout.setVerticalGroup(
            pnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnProyectosLayout.createSequentialGroup()
                .addGroup(pnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfBuscarProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnProyectosLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btQuitar)))
                .addContainerGap())
        );

        btnNuevoConvocatoria.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.btnNuevoConvocatoria.text")); // NOI18N
        btnNuevoConvocatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoConvocatoriaActionPerformed(evt);
            }
        });

        btnEliminarConvocatoria.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.btnEliminarConvocatoria.text")); // NOI18N
        btnEliminarConvocatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarConvocatoriaActionPerformed(evt);
            }
        });

        btnGuardarConvocatoria.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.btnGuardarConvocatoria.text")); // NOI18N
        btnGuardarConvocatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarConvocatoriaActionPerformed(evt);
            }
        });

        btnCancelar.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.btnCancelar.text")); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(DiagConvocatoriaWinsip.class, "DiagConvocatoriaWinsip.jCheckBox1.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dpApertura, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dpCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1))))
                        .addGap(50, 50, 50)
                        .addComponent(pnProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(btnGuardarConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarConvocatoria)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevoConvocatoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarConvocatoria)
                        .addGap(60, 60, 60))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        seleccionar();
    }//GEN-LAST:event_masterTableMouseClicked

    private void tfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreActionPerformed

    private void tfDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDescripcionActionPerformed

    private void dpCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpCierreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dpCierreActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarProyecto();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarActionPerformed
        quitarProyecto();
    }//GEN-LAST:event_btQuitarActionPerformed

    private void btnNuevoConvocatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoConvocatoriaActionPerformed
        agregarConvocatoria();
    }//GEN-LAST:event_btnNuevoConvocatoriaActionPerformed

    private void btnGuardarConvocatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarConvocatoriaActionPerformed
        guardarConvocatoriaWinsip(convocatoriaWinsipSeleccionada);
    }//GEN-LAST:event_btnGuardarConvocatoriaActionPerformed

    private void tfBuscarProyectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBuscarProyectosActionPerformed
        buscarProyecto(tfBuscarProyectos.getText());
    }//GEN-LAST:event_tfBuscarProyectosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarConvocatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarConvocatoriaActionPerformed
        if (masterTable.getSelectedRow() != -1) {
            int resp = JOptionPane.showConfirmDialog(null, "Desea eliminar, esta seguro?");
            if (JOptionPane.OK_OPTION == resp) {
                eliminarConvocatoriaWinsip();
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un registro");
        }


    }//GEN-LAST:event_btnEliminarConvocatoriaActionPerformed

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
            java.util.logging.Logger.getLogger(DiagConvocatoriaWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiagConvocatoriaWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiagConvocatoriaWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiagConvocatoriaWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiagConvocatoriaWinsip dialog = new DiagConvocatoriaWinsip(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarConvocatoria;
    private javax.swing.JButton btnGuardarConvocatoria;
    private javax.swing.JButton btnNuevoConvocatoria;
    private org.jdesktop.swingx.JXDatePicker dpApertura;
    private org.jdesktop.swingx.JXDatePicker dpCierre;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstProyectosConvocatoria;
    private javax.swing.JList lstProyectosDisponibles;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JPanel pnProyectos;
    private javax.swing.JTextField tfBuscarProyectos;
    private javax.swing.JTextField tfDescripcion;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        proyectos = new ArrayList<>();
        deshabilitarComponentes();
    }

    private void deshabilitarComponentes() {
        java.util.Collection listaConvocatorias = ConvocatoriaWinsipFacade.getInstance().getTodos();
        listTablaWinsip.clear();
        listTablaWinsip.addAll(listaConvocatorias);
        cargarTablaWinsip(listTablaWinsip);

        tfNombre.setEnabled(false);
        tfDescripcion.setEnabled(false);
        dpApertura.setEnabled(false);
        dpCierre.setEnabled(false);
        btnGuardarConvocatoria.setEnabled(false);
        btnCancelar.setEnabled(false);
        jCheckBox1.setEnabled(false);
        habilitarJPanel(false);
        Comunes.cargarJList(lstProyectosConvocatoria, new ArrayList());
        Comunes.cargarJList(lstProyectosDisponibles, new ArrayList());
        btnAgregar.setVisible(true);
        btnEliminarConvocatoria.setEnabled(true);
        btnNuevoConvocatoria.setEnabled(true);
    }
    private void agregarConvocatoria() {
        habilitarComponentes();
        convocatoriaWinsipSeleccionada = new ConvocatoriaWinsip();
        proyectos=new ArrayList<>();
    }

    private void habilitarComponentes() {

        tfNombre.setEnabled(true);
        tfDescripcion.setEnabled(true);
        dpApertura.setEnabled(true);
        dpCierre.setEnabled(true);
        btnGuardarConvocatoria.setEnabled(true);
        btnCancelar.setEnabled(true);
        jCheckBox1.setEnabled(true);
        habilitarJPanel(true);
    }

    private void buscarProyecto(String text) {
        if (tfBuscarProyectos.getText().trim().length() >= 3) {
            List<Proyecto> proyectosEncontrados = proyectoFacade.buscar(text);
            Comunes.cargarJList(lstProyectosDisponibles, proyectosEncontrados);
        }
    }

    private void agregarProyecto() {
        if (!lstProyectosDisponibles.getSelectedValuesList().isEmpty()) {
            proyectos.addAll(lstProyectosDisponibles.getSelectedValuesList());
        }
        cargarProyectos();
    }

    private void cargarProyectos() {

        List<Proyecto> proyectosDisponibles = proyectoFacade.getTodos();
        proyectosDisponibles.removeAll(proyectos);
        Comunes.cargarJList(lstProyectosDisponibles, proyectosDisponibles);
        Comunes.cargarJList(lstProyectosConvocatoria, proyectos);

    }

    private void quitarProyecto() {
        if (!lstProyectosConvocatoria.getSelectedValuesList().isEmpty()) {
            proyectos.removeAll(lstProyectosConvocatoria.getSelectedValuesList());
        }
        cargarProyectos();
    }

    private void habilitarJPanel(Boolean estado) {
        Component[] com = pnProyectos.getComponents();
        for (int a = 0; a < com.length; a++) {
            com[a].setEnabled(estado);
        }
    }

    private void cancelar() {
        tfNombre.setText("");
        tfDescripcion.setText("");
        tfBuscarProyectos.setText("");
        Comunes.cargarJList(lstProyectosConvocatoria, new ArrayList());
        Comunes.cargarJList(lstProyectosDisponibles, new ArrayList());
        proyectos = null;
        dpApertura.setDate(new Date());
        dpCierre.setDate(new Date());
        jCheckBox1.setSelected(false);
        deshabilitarComponentes();
        btnAgregar.setEnabled(true);

    }

    private void guardarConvocatoriaWinsip(ConvocatoriaWinsip winsip) {
        if (validarDatos()) {
            winsip.setNombre(tfNombre.getText());
            winsip.setDescripcion(tfDescripcion.getText());
            winsip.setHabilitada(jCheckBox1.isSelected());
            winsip.setApertura(dpApertura.getDate());
            winsip.setCierre(dpCierre.getDate());
            winsip.setProyectos(proyectos);
            if (winsip.getId() != null) {
                ConvocatoriaWinsipFacade.getInstance().modificar(winsip);
                JOptionPane.showMessageDialog(rootPane, "Modificacion Correcta");
            } else {
                ConvocatoriaWinsipFacade.getInstance().alta(winsip);
                JOptionPane.showMessageDialog(rootPane, "Alta Correcta");
            }

            inicializarComponentes();
        }

    }

    private boolean validarDatos() {
        Boolean valido = true;
        if (tfNombre.getText().isEmpty()) {
            valido = false;
            JOptionPane.showMessageDialog(rootPane, "EL nombre no puede estar vacio");
        }
        if (proyectos.isEmpty()) {
            valido = false;
            JOptionPane.showMessageDialog(rootPane, "La lista de proyectos no debe estar vacia");
        }
        return valido;
    }

    private void cargarTablaWinsip(List<ConvocatoriaWinsip> listTablaWinsip) {
        modeloTabla = new ModeloTablaNoEditable();
        cargarEncabezadosTabla(modeloTabla);
        try {
            for (ConvocatoriaWinsip m : listTablaWinsip) {
                modeloTabla.addRow(cargarMensaje(m));

            }
            masterTable.setModel(modeloTabla);
        } catch (Exception ex) {
        }

    }

    private void cargarEncabezadosTabla(ModeloTablaNoEditable modeloTabla) {
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Fecha Apertura");
        modeloTabla.addColumn("Fecha Cierre");
        modeloTabla.addColumn("Habilitada");
        masterTable.setModel(modeloTabla);
    }

    private Object[] cargarMensaje(ConvocatoriaWinsip c) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Object[] fila = new Object[7];
        fila[0] = c.getId();
        fila[1] = c.getNombre();
        fila[2] = c.getDescripcion();
        try {
            fila[3] = formato.format(c.getApertura());
        } catch (Exception ex) {
        }
        try {
            fila[4] = formato.format(c.getCierre());
        } catch (Exception ex) {
        }

        fila[5] = c.getHabilitada();
        return fila;

    }

    private void eliminarConvocatoriaWinsip() {
        try {
            ConvocatoriaWinsipFacade.getInstance().eliminar(convocatoriaWinsipSeleccionada);
            btnGuardarConvocatoria.setEnabled(false);
            btnEliminarConvocatoria.setEnabled(false);
            inicializarComponentes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "No se pudo borrar la convocatoria; ya tiene Winsips asociados");
        }
    }

    private void seleccionar() {
        try {
            convocatoriaWinsipSeleccionada = ConvocatoriaWinsipFacade.getInstance().buscar((Long) masterTable.getValueAt(masterTable.getSelectedRow(), 0));
            proyectos = convocatoriaWinsipSeleccionada.getProyectos();
            cargarCampos();
            btnGuardarConvocatoria.setEnabled(true);
            btnEliminarConvocatoria.setEnabled(true);
            btnCancelar.setEnabled(true);
            btnNuevoConvocatoria.setEnabled(false);
            btQuitar.setEnabled(true);
            btnAgregar.setEnabled(true);
//            habilitarcampos de edicion
            habilitarJPanel(true);
            tfNombre.setEnabled(true);
            tfDescripcion.setEnabled(true);
            dpApertura.setEnabled(true);
            dpCierre.setEnabled(true);
            jCheckBox1.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo seleccionar la convocatoria");
            inicializarComponentes();
        }
    }

    private void cargarCampos() {
        tfNombre.setText(convocatoriaWinsipSeleccionada.getNombre());
        tfDescripcion.setText(convocatoriaWinsipSeleccionada.getDescripcion());
        jCheckBox1.setSelected(convocatoriaWinsipSeleccionada.getHabilitada());
        dpApertura.setDate(convocatoriaWinsipSeleccionada.getApertura());
        dpCierre.setDate(convocatoriaWinsipSeleccionada.getCierre());
        cargarProyectos();
    }
}
