/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagCampoAplicacionAlta.java
 *
 * Created on 22/02/2011, 09:43:38
 */
package vista;

import entidades.proyecto.CampoAplicacion;
import facade.CampoAplicacionFacade;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class diagCampoAplicacionAlta extends javax.swing.JDialog {

    // Atributos

    /** Creates new form diagCampoAplicacionAlta */
    public diagCampoAplicacionAlta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagCampoAplicacionAlta.class, "diagCampoAplicacionAlta.title")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagCampoAplicacionAlta.class, "diagCampoAplicacionAlta.jLabel1.text")); // NOI18N

        tfDescripcion.setText(org.openide.util.NbBundle.getMessage(diagCampoAplicacionAlta.class, "diagCampoAplicacionAlta.tfDescripcion.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagCampoAplicacionAlta.class, "diagCampoAplicacionAlta.jButton1.text")); // NOI18N
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
                        .addComponent(tfDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jButton1)))
                .addContainerGap())
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagCampoAplicacionAlta dialog = new diagCampoAplicacionAlta(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tfDescripcion;
    // End of variables declaration//GEN-END:variables

    private void guardar() {
        if (validar()) {
            CampoAplicacion campoAplicacion = new CampoAplicacion();
            campoAplicacion.setDescripcion(tfDescripcion.getText());
            CampoAplicacionFacade.getInstance().alta(campoAplicacion);
            JOptionPane.showMessageDialog(null, "El CampoAplicacion \"" +
                    campoAplicacion.getDescripcion() + "\" ha sido guardado "
                    + "exitosamenta");
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una descripción válida",
                    "Error", JOptionPane.ERROR_MESSAGE);
            inicializarComponentes();
        }
    }

    private boolean validar() {
        if(!tfDescripcion.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void inicializarComponentes() {
        tfDescripcion.requestFocus();
        tfDescripcion.selectAll();
    }
}
