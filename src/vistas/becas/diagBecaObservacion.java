/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.becas;

import entidades.becas.Observacion;
import entidades.becas.PostulacionBeca;
import facade.ObservacionFacade;
import includes.Comunes;
import javax.swing.JOptionPane;

/**
 *
 * @author huguito
 */
public class diagBecaObservacion extends javax.swing.JDialog {

    private String tipoOperacion;
    private Observacion observacion;
    private PostulacionBeca postulacion;

    public Observacion getObservacion() {
        return observacion;
    }

    /**
     * Creates new form diagBecaObservacion
     */
    public diagBecaObservacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarComponentes();
    }

    public diagBecaObservacion(java.awt.Frame parent, boolean modal, String tipoOperacion, PostulacionBeca postulacion) {
        super(parent, modal);
        initComponents();
        this.postulacion = postulacion;
        this.tipoOperacion = tipoOperacion;
        inicializarComponentes();
    }

    public diagBecaObservacion(java.awt.Frame parent, boolean modal, String tipoOperacion, PostulacionBeca postulacion, Observacion observacion) {
        super(parent, modal);
        initComponents();
        this.postulacion = postulacion;
        this.observacion = observacion;
        this.tipoOperacion = tipoOperacion;
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
        jxdpFechaObs = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacion = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagBecaObservacion.class, "diagBecaObservacion.jPanel1.border.title"))); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagBecaObservacion.class, "diagBecaObservacion.jLabel1.text")); // NOI18N

        taObservacion.setColumns(20);
        taObservacion.setRows(5);
        jScrollPane1.setViewportView(taObservacion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jxdpFechaObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jxdpFechaObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAceptar.setText(org.openide.util.NbBundle.getMessage(diagBecaObservacion.class, "diagBecaObservacion.btnAceptar.text")); // NOI18N
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(diagBecaObservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagBecaObservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagBecaObservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagBecaObservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagBecaObservacion dialog = new diagBecaObservacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jxdpFechaObs;
    private javax.swing.JTextArea taObservacion;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        Comunes.formatearJXdatePicker(jxdpFechaObs);
        if (tipoOperacion.equals("Modificación")) {
            if (observacion.getFecha() != null) {
                jxdpFechaObs.setDate(observacion.getFecha());
            } else {
                jxdpFechaObs.setDate(null);
            }
            if (observacion.getObservacion() != null) {
                taObservacion.setText(observacion.getObservacion());
            } else {
                taObservacion.setText("");
            }


        }

    }

    private void aceptar() {

        if (tipoOperacion.equals("Alta")) {
            observacion = new Observacion();
            observacion.setFecha(jxdpFechaObs.getDate());
            if (!taObservacion.getText().equals("")) {
                observacion.setObservacion(taObservacion.getText());
            } else {
                observacion.setObservacion("");
            }
            observacion.setPostulacionBeca(postulacion);
            ObservacionFacade.getInstance().alta(observacion);
            JOptionPane.showMessageDialog(null, "Operación realizada!");
            this.dispose();
        }
        if (tipoOperacion.equals("Modificación")) {
            observacion.setFecha(jxdpFechaObs.getDate());
            if (!taObservacion.getText().equals("")) {
                observacion.setObservacion(taObservacion.getText());
            } else {
                observacion.setObservacion("");
            }
            observacion.setPostulacionBeca(postulacion);
            ObservacionFacade.getInstance().modificar(observacion);
            JOptionPane.showMessageDialog(null, "Modificación realizada!");
            this.dispose();
        }

    }
}
