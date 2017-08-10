/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagModificarCobro.java
 *
 * Created on 25/09/2012, 12:26:56
 */
package vista.economico;

import entidades.economico.Cobro;
import entidades.operaciones.Operacion;
import entidades.usuario.Usuario;
import facade.OperacionFacade;
import includes.Comunes;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author walter
 */
public class diagModificarCobro extends javax.swing.JDialog {

    Cobro cobro = new Cobro();
    private Usuario usuario;

    public Cobro getCobro() {
        return cobro;
    }

    public void setCobro(Cobro cobro) {
        this.cobro = cobro;
    }

    /**
     * Creates new form diagModificarCobro
     */
    public diagModificarCobro(java.awt.Frame parent, boolean modal, Cobro cobroSeleccionado, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
        setCobro(cobroSeleccionado);
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

        jLabel1 = new javax.swing.JLabel();
        tfMonto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfNumeroCheque = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dpFechaCobro = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.title")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.jLabel1.text")); // NOI18N

        tfMonto.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.tfMonto.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.jLabel2.text")); // NOI18N

        tfNumeroCheque.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.tfNumeroCheque.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.jLabel4.text")); // NOI18N

        dpFechaCobro.setFormats("dd/MM/yyyy");

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.jLabel5.text")); // NOI18N

        taObservacion.setColumns(20);
        taObservacion.setRows(5);
        jScrollPane1.setViewportView(taObservacion);

        btnGuardar.setText(org.openide.util.NbBundle.getMessage(diagModificarCobro.class, "diagModificarCobro.btnGuardar.text")); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel2))
                            .addComponent(dpFechaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(25, 25, 25))))
            .addGroup(layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(btnGuardar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dpFechaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCobro();
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(diagModificarCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagModificarCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagModificarCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagModificarCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagModificarCobro dialog = new diagModificarCobro(new javax.swing.JFrame(), true, new Cobro(), new Usuario());
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
    private javax.swing.JButton btnGuardar;
    private org.jdesktop.swingx.JXDatePicker dpFechaCobro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taObservacion;
    private javax.swing.JTextField tfMonto;
    private javax.swing.JTextField tfNumeroCheque;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnGuardar);
        cargarComponentes();
    }

    private void cargarComponentes() {
        tfMonto.setText(String.valueOf(cobro.getMontoCobrado()));
        tfNumeroCheque.setText(cobro.getNumeroCheque());
        taObservacion.setText(cobro.getObservacion());
        dpFechaCobro.setDate(cobro.getFechaCobro());        
    }

    private void guardarCobro() {
        validarCampos();
    }

    private void validarCampos() {
        boolean bandera = true;
        String error = "Error: \n";

        /*if (tfMonto.getText().isEmpty()) {
            bandera = false;
            error += "Debe ingresar un Monto \n";
        }

        if (tfNumeroCheque.getText().isEmpty()) {
            bandera = false;
            error += "Debe ingresar un Número de Cheque \n";
        }

        if (dpFechaCobro.getDate() == null) {
            bandera = false;
            error += "Debe elegir una Fecha de Cobro \n";
        }   
        */

        if (bandera == false) {
            JOptionPane.showMessageDialog(null, error);
        } else {
            guardarRegistro();
        }
    }

    private void guardarRegistro() {
        Cobro cobroModificado = getCobro();

        if(Comunes.validarBigDecimal(tfMonto.getText())){
            cobroModificado.setMontoCobrado(new BigDecimal(tfMonto.getText()));
        }else{
            cobroModificado.setMontoCobrado(new BigDecimal("0"));
        }
        cobroModificado.setNumeroCheque(tfNumeroCheque.getText());
        cobroModificado.setObservacion(taObservacion.getText());
        cobroModificado.setFechaCobro(dpFechaCobro.getDate());

        facade.CobroFacade.getInstance().modificarCobro(cobroModificado);
        
        Operacion operacion = new Operacion();
        operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
        operacion.setOperacion("Modificación de Cobro (Economico)");
        operacion.setUsuario(usuario);
        OperacionFacade.getInstance().alta(operacion);

        JOptionPane.showMessageDialog(null, "Datos Guardados");
        this.dispose();
    }
}
