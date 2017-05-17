/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.proyectos;

import entidades.proyecto.EntidadAcreditadora;
import facade.EntidadAcreditadoraFacade;
import javax.swing.JOptionPane;

/**
 *
 * @author hugo
 */
public class diagEntidadAcreditadora extends javax.swing.JDialog {

    /**
     * Creates new form diagEntidadAcreditadora
     */
    private String tipoOperacion;
    private EntidadAcreditadora entidadAcreditadora;

    public EntidadAcreditadora getEntidadAcreditadora() {
        return entidadAcreditadora;
    }

    public void setEntidadAcreditadora(EntidadAcreditadora entidadAcreditadora) {
        this.entidadAcreditadora = entidadAcreditadora;
    }

    public diagEntidadAcreditadora(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarComponentes();
    }

    public diagEntidadAcreditadora(java.awt.Frame parent, boolean modal, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.tipoOperacion=tipoOperacion;
        inicializarComponentes();
    }
    public diagEntidadAcreditadora(java.awt.Frame parent, boolean modal, String tipoOperacion,EntidadAcreditadora entidadAcreditadora) {
        super(parent, modal);
        initComponents();
        this.tipoOperacion=tipoOperacion;
        this.entidadAcreditadora=entidadAcreditadora;
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
        tfDescripcion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagEntidadAcreditadora.class, "diagEntidadAcreditadora.jLabel1.text")); // NOI18N

        tfDescripcion.setText(org.openide.util.NbBundle.getMessage(diagEntidadAcreditadora.class, "diagEntidadAcreditadora.tfDescripcion.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagEntidadAcreditadora.class, "diagEntidadAcreditadora.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(diagEntidadAcreditadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagEntidadAcreditadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagEntidadAcreditadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagEntidadAcreditadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagEntidadAcreditadora dialog = new diagEntidadAcreditadora(new javax.swing.JFrame(), true);
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

    private void guardar() {
        if (validar()) {
            if (tipoOperacion.equals("Alta")) {
                entidadAcreditadora = new EntidadAcreditadora();
                entidadAcreditadora.setDescripcion(tfDescripcion.getText());
                EntidadAcreditadoraFacade.getInstance().alta(entidadAcreditadora);
                JOptionPane.showMessageDialog(null, "La Entidad Acreditadora \""
                        + entidadAcreditadora.getDescripcion() + "\" ha sido guardada "
                        + "exitosamenta");
                this.dispose();
            }
            if (tipoOperacion.equals("Modificación")) {
                entidadAcreditadora.setDescripcion(tfDescripcion.getText());
                EntidadAcreditadoraFacade.getInstance().modificar(entidadAcreditadora);
                JOptionPane.showMessageDialog(null, "La Entidad Acreditadora \""
                        + entidadAcreditadora.getDescripcion() + "\" ha sido modificada "
                        + "exitosamenta");
                this.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una descripción válida",
                    "Error", JOptionPane.ERROR_MESSAGE);
            inicializarComponentes();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tfDescripcion;
    // End of variables declaration//GEN-END:variables
    private void inicializarComponentes() {
        this.setTitle("Entidad Acreditadora");
        tfDescripcion.requestFocus();
        tfDescripcion.selectAll();
        if (tipoOperacion.equals("Modificación")) {
            tfDescripcion.setText(entidadAcreditadora.getDescripcion());
        }
    }

    private boolean validar() {
        return !tfDescripcion.getText().equals("");
    }

}
