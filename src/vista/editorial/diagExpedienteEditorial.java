/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.editorial;

import entidades.operaciones.Operacion;
import entidades.proyecto.editorial.EditorialCientifica;
import entidades.proyecto.editorial.ExpedienteEditorial;
import entidades.usuario.Usuario;
import facade.EditorialCientificaFacade;
import facade.ExpedienteEditorialFacade;
import facade.OperacionFacade;
import includes.Comunes;
import javax.swing.JOptionPane;

/**
 *
 * @author francisco
 */
public class diagExpedienteEditorial extends javax.swing.JDialog {

    private Usuario usuario;
    private EditorialCientifica editorialCientifica;
    private String tipoOperacion;
    private ExpedienteEditorial expediente;
            /**
             * Creates new form diagExpedienteEditorial
             */

    public diagExpedienteEditorial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public diagExpedienteEditorial(java.awt.Frame parent, boolean modal,
            EditorialCientifica publicacionCientifica, Usuario usuario, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
        this.editorialCientifica = publicacionCientifica;
        this.tipoOperacion = tipoOperacion;
        inicializarComponentes();

    }

    public diagExpedienteEditorial(java.awt.Frame parent, boolean modal,
            ExpedienteEditorial expediente,
            EditorialCientifica publicacionCientifica, Usuario usuario, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
        this.editorialCientifica = publicacionCientifica;
        this.tipoOperacion = tipoOperacion;
        this.expediente = expediente;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfIniciador = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfPublicacion = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.jPanel1.border.title"))); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.jLabel2.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.jLabel4.text")); // NOI18N

        tfCodigo.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.tfCodigo.text")); // NOI18N
        tfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCodigoKeyTyped(evt);
            }
        });

        tfIniciador.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.tfIniciador.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.jLabel5.text")); // NOI18N

        tfPublicacion.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.tfPublicacion.text")); // NOI18N

        btnCancelar.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.btnCancelar.text")); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagExpedienteEditorial.class, "diagExpedienteEditorial.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfPublicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(tfIniciador)
                    .addComponent(tfCodigo))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfIniciador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(jButton2))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyTyped
        // escribir solo numeros
        //char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        //if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
        //    evt.consume();  // ignorar el evento de teclado
        //}
        Comunes.soloNumeros(tfCodigo, evt);
    }//GEN-LAST:event_tfCodigoKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(diagExpedienteEditorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagExpedienteEditorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagExpedienteEditorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagExpedienteEditorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagExpedienteEditorial dialog = new diagExpedienteEditorial(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfIniciador;
    private javax.swing.JTextField tfPublicacion;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        switch (tipoOperacion) {
            case "Alta":
                btnCancelar.setEnabled(false);
                tfPublicacion.setEnabled(false);
                tfPublicacion.setText(editorialCientifica.getTitulo());
            case "Modificación":

                try {
                    if (expediente.getPublicacion().getTitulo() != null) {
                        tfPublicacion.setText(expediente.getPublicacion().getTitulo());

                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (expediente.getCodigo() != null) {
                        tfCodigo.setText(expediente.getCodigo());

                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (expediente.getIniciador() != null) {
                        tfIniciador.setText(expediente.getIniciador());

                    }
                } catch (NullPointerException ex) {
                }
        }

    }

    public void guardar() {
        if (validar()) {
           switch (tipoOperacion) {
            case "Alta":
             expediente = new ExpedienteEditorial();
                asignarDatos();
                altaExpediente();
                this.dispose();
            case "Modificación":
                asignarDatos();
                modificarExpediente();
                this.dispose();
        }
    }

}
private boolean validar() {
        boolean flag = false;
        if (!tfCodigo.getText().equals("")) {
            if (!tfIniciador.getText().equals("")) {
                

                    flag = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el código del expediente", "Error", JOptionPane.ERROR_MESSAGE);
                    tfCodigo.requestFocus();
            }

            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar el iniciador del expediente", "Error", JOptionPane.ERROR_MESSAGE);
                tfIniciador.requestFocus();
            }
         
        return flag;
    }


    private void asignarDatos() {
            expediente.setCodigo(tfCodigo.getText());

            expediente.setIniciador(tfIniciador.getText());
            expediente.setPublicacion(editorialCientifica);
    }

    private void altaExpediente() {
    try {
                ExpedienteEditorialFacade.getInstance().alta(expediente);
                Operacion operacion = new Operacion();
                operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                operacion.setOperacion("Alta de expediente");
                operacion.setUsuario(usuario);
                OperacionFacade.getInstance().alta(operacion);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error creando expediente\n " + ex);
            }    
    }

    private void modificarExpediente() {
 try {
                ExpedienteEditorialFacade.getInstance().modificar(expediente);
                Operacion operacion = new Operacion();
                operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                operacion.setOperacion("Modificación de expediente");
                operacion.setUsuario(usuario);
                OperacionFacade.getInstance().alta(operacion);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error modificando expediente\n " + ex);
            }    }
}
