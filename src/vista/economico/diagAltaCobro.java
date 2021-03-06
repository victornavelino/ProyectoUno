/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagAltaCobro.java
 *
 * Created on 14/08/2012, 09:32:21
 */
package vista.economico;

import entidades.economico.Cobro;
import entidades.economico.PagoEconomico;
import entidades.operaciones.Operacion;
import entidades.proyecto.Proyecto;
import entidades.usuario.Usuario;
import facade.OperacionFacade;
import includes.Comunes;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author walter
 */
public class diagAltaCobro extends javax.swing.JDialog {
    
    Proyecto proyectoSeleccionado = new Proyecto();
    private Usuario usuario;

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    /** Creates new form diagAltaCobro */
    public diagAltaCobro(java.awt.Frame parent, boolean modal, Proyecto proye, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.usuario=usuario;
        setProyectoSeleccionado(proye);
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
        tfMonto = new javax.swing.JTextField();
        dpFechaCobro = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfNumeroCheque = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbPagos = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.title")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.jLabel1.text")); // NOI18N

        tfMonto.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.tfMonto.text")); // NOI18N

        dpFechaCobro.setFormats("dd/MM/yyyy");

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.jLabel3.text")); // NOI18N

        tfNumeroCheque.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.tfNumeroCheque.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.jLabel4.text")); // NOI18N

        taObservacion.setColumns(20);
        taObservacion.setRows(5);
        jScrollPane1.setViewportView(taObservacion);

        btnGuardar.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.btnGuardar.text")); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagAltaCobro.class, "diagAltaCobro.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(dpFechaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(tfNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(dpFechaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar))
                    .addComponent(jLabel4))
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(diagAltaCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagAltaCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagAltaCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagAltaCobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagAltaCobro dialog = new diagAltaCobro(new javax.swing.JFrame(), true, new Proyecto(),new Usuario());
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
    private javax.swing.JComboBox cmbPagos;
    private org.jdesktop.swingx.JXDatePicker dpFechaCobro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
        cargarComboPagos();
    }

    private void cargarComboPagos() {
        Proyecto proyecto = facade.PagoEconomicoFacade.getInstance().pagosxProyecto(proyectoSeleccionado);
        List<PagoEconomico> pagos = proyecto.getPagos();
        if(pagos.size()>0){            
            Comunes.cargarJComboConBlanco(cmbPagos, pagos);            
        }
    }

    private void guardarCobro() {
        validarCampos();
    }

    private void validarCampos() {
        boolean bandera = true;
        String error = "Error: \n";
        
        /*if(tfMonto.getText().isEmpty()){
            bandera = false;
            error += "Debe ingresar un Monto \n";
        }
        
        if(tfNumeroCheque.getText().isEmpty()){
            bandera = false;
            error += "Debe ingresar un Número de Cheque \n";
        }
        
        if(dpFechaCobro.getDate() == null){
            bandera = false;
            error += "Debe elegir una Fecha de Cobro \n";
        }
        
        if(tfOrdenPago.getText().isEmpty()){
            bandera = false;
            error += "Debe ingresar un Número de Orden de Pago \n";
        }*/              
        
        if(bandera == false){
            JOptionPane.showMessageDialog(null, error);
        }else{
            guardarRegistro();
        }       
        
    }

    private void guardarRegistro() {
        if(cmbPagos.getSelectedItem().equals("--Seleccione--")){
            JOptionPane.showMessageDialog(null, "Debe elegir un pago");
        }else{
            Cobro cobro = new Cobro();
            PagoEconomico pagoElegido = (PagoEconomico) cmbPagos.getSelectedItem();
            
            if(Comunes.validarBigDecimal(tfMonto.getText())){
                cobro.setMontoCobrado(new BigDecimal(tfMonto.getText()));
            }else{
                cobro.setMontoCobrado(new BigDecimal("0"));
            }
            cobro.setNumeroCheque(tfNumeroCheque.getText());
            cobro.setFechaCobro(dpFechaCobro.getDate());
            cobro.setObservacion(taObservacion.getText());                    
            cobro.setPago(pagoElegido);

            facade.CobroFacade.getInstance().altaCobro(cobro);
            
            Operacion operacion = new Operacion();
            operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
            operacion.setOperacion("Alta de Cobro (Economico)");
            operacion.setUsuario(usuario);
            OperacionFacade.getInstance().alta(operacion);

            JOptionPane.showMessageDialog(null, "Datos Guardados");
            this.dispose();
        }
    }
}
