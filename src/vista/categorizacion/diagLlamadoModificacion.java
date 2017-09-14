/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagLlamadoModificacion.java
 *
 * Created on 21/11/2011, 12:08:55
 */
package vista.categorizacion;

import entidades.categorizacion.Llamado;
import entidades.operaciones.Operacion;
import entidades.usuario.Usuario;
import facade.LlamadoFacade;
import facade.OperacionFacade;
import includes.Comunes;
import javax.swing.JOptionPane;

/**
 *
 * @author wbivanco
 */
public class diagLlamadoModificacion extends javax.swing.JDialog {

    private Usuario usuario;
    /** Creates new form diagLlamadoModificacion */
    public diagLlamadoModificacion(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        this.usuario = usuario;
        initComponents();
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
        tfDescripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dpFecha = new org.jdesktop.swingx.JXDatePicker();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagLlamadoModificacion.class, "diagLlamadoModificacion.title")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagLlamadoModificacion.class, "diagLlamadoModificacion.jLabel1.text")); // NOI18N

        tfDescripcion.setText(org.openide.util.NbBundle.getMessage(diagLlamadoModificacion.class, "diagLlamadoModificacion.tfDescripcion.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagLlamadoModificacion.class, "diagLlamadoModificacion.jLabel2.text")); // NOI18N

        dpFecha.setFormats("dd/MM/yyyy");

        btnGuardar.setText(org.openide.util.NbBundle.getMessage(diagLlamadoModificacion.class, "diagLlamadoModificacion.btnGuardar.text")); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(btnGuardar)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
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
            java.util.logging.Logger.getLogger(diagLlamadoModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagLlamadoModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagLlamadoModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagLlamadoModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagLlamadoModificacion dialog = new diagLlamadoModificacion(new javax.swing.JFrame(), true, new Usuario());
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
    private org.jdesktop.swingx.JXDatePicker dpFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tfDescripcion;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        this.setLocationRelativeTo(null);
        cargarComponentes();
        traerLlamado();
    }

    private void cargarComponentes() {        
        tfDescripcion.setEnabled(true);
        dpFecha.setEnabled(true);
    }
    
    private boolean validar() {
        if(!tfDescripcion.getText().equals("")) {
            return true;
        } else {
            return false;
        }        
    }
    
    private void guardar() {
        if (validar()) {
            Llamado llamado = diagLlamadoBusqueda.llamadoSeleccionado;
            llamado.setDescripcion(tfDescripcion.getText());
            llamado.setFecha(dpFecha.getDate());
            LlamadoFacade.getInstance().modificacion(llamado);
            JOptionPane.showMessageDialog(null, "El Valor de Llamado \"" +
                    llamado.getDescripcion() + "\" ha sido guardado "
                    + "exitosamente");
            Operacion operacion = new Operacion();
            operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
            operacion.setOperacion("Modificación de Llamado");
            operacion.setUsuario(usuario);
            OperacionFacade.getInstance().alta(operacion);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una descripción válida",
                    "Error", JOptionPane.ERROR_MESSAGE);
            inicializarComponentes();
        }
        this.dispose();
    }

    private void traerLlamado() {                
        Llamado llamadoBuscado = diagLlamadoBusqueda.llamadoSeleccionado;
        
        tfDescripcion.setText(llamadoBuscado.getDescripcion());
        dpFecha.setDate(llamadoBuscado.getFecha());
        
        tfDescripcion.setEnabled(true);
        dpFecha.setEnabled(true);
    }
}
