/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagVerDetallePago.java
 *
 * Created on 17/08/2012, 08:42:22
 */
package vista.economico;

import entidades.Documento;
import entidades.economico.PagoEconomico;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.categorizacion.diagCategorizacionModificacion;

/**
 *
 * @author walter
 */
public class diagVerDetallePago extends javax.swing.JDialog {
    PagoEconomico pago = new PagoEconomico();

    public PagoEconomico getPago() {
        return pago;
    }

    public void setPago(PagoEconomico pago) {
        this.pago = pago;
    }    

    /** Creates new form diagVerDetallePago */
    public diagVerDetallePago(java.awt.Frame parent, boolean modal, PagoEconomico pagoSeleccionado) {
        super(parent, modal);
        initComponents();
        setPago(pagoSeleccionado);
        inicializarComponentes();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfNumeroExpediente = new javax.swing.JTextField();
        tfResolucionCS = new javax.swing.JTextField();
        dpFechaResolucionCS = new org.jdesktop.swingx.JXDatePicker();
        tfResolucionR = new javax.swing.JTextField();
        dpFechaResolucionR = new org.jdesktop.swingx.JXDatePicker();
        tfMonto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfAnioResolucionCS = new javax.swing.JTextField();
        tfAnioResolucionR = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfResolucionSecyt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfAnioResolucionSecyt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dpFechaResolucionSecyt = new org.jdesktop.swingx.JXDatePicker();
        jLabel13 = new javax.swing.JLabel();
        tfAnioExpediente = new javax.swing.JTextField();
        btnVerResolucionS = new javax.swing.JButton();
        btnVerResolucionR = new javax.swing.JButton();
        btnVerResolucionCS = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tfOrdenPago = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.title")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel6.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel7.text")); // NOI18N

        tfNumeroExpediente.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfNumeroExpediente.text")); // NOI18N

        tfResolucionCS.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfResolucionCS.text")); // NOI18N

        dpFechaResolucionCS.setFormats("dd/MM/yyyy");

        tfResolucionR.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfResolucionR.text")); // NOI18N

        dpFechaResolucionR.setFormats("dd/MM/yyyy");

        tfMonto.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfMonto.text")); // NOI18N

        taObservacion.setColumns(20);
        taObservacion.setRows(5);
        jScrollPane1.setViewportView(taObservacion);

        jLabel8.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel8.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel9.text")); // NOI18N

        tfAnioResolucionCS.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfAnioResolucionCS.text")); // NOI18N

        tfAnioResolucionR.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfAnioResolucionR.text")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel10.text")); // NOI18N

        tfResolucionSecyt.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfResolucionSecyt.text")); // NOI18N

        jLabel11.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel11.text")); // NOI18N

        tfAnioResolucionSecyt.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfAnioResolucionSecyt.text")); // NOI18N

        jLabel12.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel12.text")); // NOI18N

        dpFechaResolucionSecyt.setFormats("dd/MM/yyyy");

        jLabel13.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel13.text")); // NOI18N

        tfAnioExpediente.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfAnioExpediente.text")); // NOI18N

        btnVerResolucionS.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.btnVerResolucionS.text")); // NOI18N
        btnVerResolucionS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerResolucionSActionPerformed(evt);
            }
        });

        btnVerResolucionR.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.btnVerResolucionR.text")); // NOI18N
        btnVerResolucionR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerResolucionRActionPerformed(evt);
            }
        });

        btnVerResolucionCS.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.btnVerResolucionCS.text")); // NOI18N
        btnVerResolucionCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerResolucionCSActionPerformed(evt);
            }
        });

        jLabel14.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.jLabel14.text")); // NOI18N

        tfOrdenPago.setText(org.openide.util.NbBundle.getMessage(diagVerDetallePago.class, "diagVerDetallePago.tfOrdenPago.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNumeroExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfResolucionCS, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dpFechaResolucionCS, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfResolucionR, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dpFechaResolucionR, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfResolucionSecyt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dpFechaResolucionSecyt, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfAnioExpediente, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                            .addComponent(tfAnioResolucionCS, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfAnioResolucionR, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfAnioResolucionSecyt)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnVerResolucionS)
                                            .addComponent(btnVerResolucionR)
                                            .addComponent(btnVerResolucionCS))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfOrdenPago, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNumeroExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfAnioExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfResolucionCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfAnioResolucionCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpFechaResolucionCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVerResolucionCS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfResolucionR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfAnioResolucionR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpFechaResolucionR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVerResolucionR)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfResolucionSecyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(tfAnioResolucionSecyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(dpFechaResolucionSecyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerResolucionS))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfOrdenPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerResolucionCSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerResolucionCSActionPerformed
        verResolucionCS();
    }//GEN-LAST:event_btnVerResolucionCSActionPerformed

    private void btnVerResolucionRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerResolucionRActionPerformed
        verResolucionR();
    }//GEN-LAST:event_btnVerResolucionRActionPerformed

    private void btnVerResolucionSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerResolucionSActionPerformed
        verResolucionSecyt();
    }//GEN-LAST:event_btnVerResolucionSActionPerformed

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
            java.util.logging.Logger.getLogger(diagVerDetallePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagVerDetallePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagVerDetallePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagVerDetallePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagVerDetallePago dialog = new diagVerDetallePago(new javax.swing.JFrame(), true, new PagoEconomico());
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
    private javax.swing.JButton btnVerResolucionCS;
    private javax.swing.JButton btnVerResolucionR;
    private javax.swing.JButton btnVerResolucionS;
    private org.jdesktop.swingx.JXDatePicker dpFechaResolucionCS;
    private org.jdesktop.swingx.JXDatePicker dpFechaResolucionR;
    private org.jdesktop.swingx.JXDatePicker dpFechaResolucionSecyt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taObservacion;
    private javax.swing.JTextField tfAnioExpediente;
    private javax.swing.JTextField tfAnioResolucionCS;
    private javax.swing.JTextField tfAnioResolucionR;
    private javax.swing.JTextField tfAnioResolucionSecyt;
    private javax.swing.JTextField tfMonto;
    private javax.swing.JTextField tfNumeroExpediente;
    private javax.swing.JTextField tfOrdenPago;
    private javax.swing.JTextField tfResolucionCS;
    private javax.swing.JTextField tfResolucionR;
    private javax.swing.JTextField tfResolucionSecyt;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        this.setLocationRelativeTo(null);
        habilitarComponentes(false);        
        cargarComponentes();
    }

    private void habilitarComponentes(boolean b) {
        tfNumeroExpediente.setEditable(b);
        tfResolucionCS.setEditable(b);
        tfResolucionR.setEditable(b);
        tfResolucionSecyt.setEditable(b);
        tfAnioExpediente.setEditable(b);
        tfAnioResolucionCS.setEditable(b);
        tfAnioResolucionR.setEditable(b);
        tfAnioResolucionSecyt.setEditable(b);        
        tfMonto.setEditable(b);
        dpFechaResolucionR.setEditable(b);
        dpFechaResolucionCS.setEditable(b);
        dpFechaResolucionSecyt.setEditable(b);
        taObservacion.setEditable(b);
        tfOrdenPago.setEditable(b);        
        if(getPago().getArchivoResolucionConsejoSuperior() != null){
            btnVerResolucionCS.setEnabled(true);
        }else{
            btnVerResolucionCS.setEnabled(b);
        }
        if(getPago().getArchivoResolucionRectoral() != null){
            btnVerResolucionR.setEnabled(true);
        }else{
            btnVerResolucionR.setEnabled(b);
        }
        if(getPago().getArchivoResolucionSecyt() != null){
            btnVerResolucionS.setEnabled(true);
        }else{
            btnVerResolucionS.setEnabled(b);
        }
    }

    private void cargarComponentes() {
        tfNumeroExpediente.setText(getPago().getNumeroExpediente());
        tfAnioExpediente.setText(String.valueOf(getPago().getAnioExpediente()));
        tfResolucionCS.setText(getPago().getResolucionConsejoSuperior().toString());
        tfAnioResolucionCS.setText(String.valueOf(getPago().getResolucionConsejoSuperior().getAnio()));
        dpFechaResolucionCS.setDate(getPago().getResolucionConsejoSuperior().getFechaResolucion());
        tfResolucionR.setText(getPago().getResolucionRectoral().toString());
        tfAnioResolucionR.setText(String.valueOf(getPago().getResolucionRectoral().getAnio()));
        dpFechaResolucionR.setDate(getPago().getResolucionRectoral().getFechaResolucion());
        tfResolucionSecyt.setText(getPago().getResolucionSecyt().toString());
        tfAnioResolucionSecyt.setText(String.valueOf(getPago().getResolucionSecyt().getAnio()));
        dpFechaResolucionSecyt.setDate(getPago().getResolucionSecyt().getFechaResolucion());
        tfMonto.setText(String.valueOf(getPago().getMonto()));
        taObservacion.setText(getPago().getObservacion());
        tfOrdenPago.setText(getPago().getNumeroOrdenPago());
    }

    private void verResolucionCS() {        
        abrirArchivoResolucion(getPago().getArchivoResolucionConsejoSuperior());
    }
    
    private void verResolucionR() {
        abrirArchivoResolucion(getPago().getArchivoResolucionRectoral());
    }

    private void verResolucionSecyt() {
        abrirArchivoResolucion(getPago().getArchivoResolucionSecyt());
    }

    private void abrirArchivoResolucion(Documento docuSeleccionado) {
        try {
            Documento documento = docuSeleccionado;
            byte[] archivoInterno = documento.getContenidoArchivo();
            File archivo = File.createTempFile("tmp", documento.getNombreArchivo());
            archivo.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                fos.write(archivoInterno);
            }
            if (Desktop.isDesktopSupported() == true) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    if (archivo.exists() == true) {
                        desktop.open(archivo);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontrar el archivo: " + archivo.getAbsolutePath(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException e) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el comando de apertura en este sistema operativo", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(diagVerDetallePago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
