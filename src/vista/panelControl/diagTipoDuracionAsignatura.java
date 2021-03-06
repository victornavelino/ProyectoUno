/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagInstitucion.java
 *
 * Created on 14/11/2011, 21:44:50
 */
package vista.panelControl;

import entidades.Institucion;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
import facade.InstitucionFacade;
import facade.persona.investigador.curso.TipoDuracionAsignaturaFacade;

/**
 *
 * @author hugo
 */
public class diagTipoDuracionAsignatura extends javax.swing.JDialog {

    private TipoDuracionAsignatura tipoDuracionAsignatura;
    private String tipoOperacion;

    /** Creates new form diagInstitucionAlta */
    public diagTipoDuracionAsignatura(java.awt.Frame parent, boolean modal, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.tipoOperacion = tipoOperacion;
        inicializarComponentes();
    }

    public diagTipoDuracionAsignatura(java.awt.Frame parent, boolean modal, String tipoOperacion, TipoDuracionAsignatura tipoDuracionAsignatura) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.tipoDuracionAsignatura = tipoDuracionAsignatura;
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

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagTipoDuracionAsignatura.class, "diagTipoDuracionAsignatura.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagTipoDuracionAsignatura.class, "diagTipoDuracionAsignatura.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aceptar();
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
            java.util.logging.Logger.getLogger(diagTipoDuracionAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagTipoDuracionAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagTipoDuracionAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagTipoDuracionAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagTipoDuracionAsignatura dialog = new diagTipoDuracionAsignatura(new javax.swing.JFrame(), true, new String());
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tfDescripcion;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        if (tipoOperacion.equals("Alta")) {
            tfDescripcion.setText("");
            tfDescripcion.requestFocus();
        } else if (tipoOperacion.equals("Consulta")) {
            tfDescripcion.setText(tipoDuracionAsignatura.getDescripcion());
            tfDescripcion.setEditable(false);
        } else if (tipoOperacion.equals("Modificación")) {
            tfDescripcion.setText(tipoDuracionAsignatura.getDescripcion());
        }

    }

    private void aceptar() {
        if (tipoOperacion.equals("Alta")) {
            tipoDuracionAsignatura = new TipoDuracionAsignatura();
            tipoDuracionAsignatura.setDescripcion(tfDescripcion.getText().toUpperCase());
            TipoDuracionAsignaturaFacade.getInstance().alta(tipoDuracionAsignatura);
        } else if (tipoOperacion.equals("Modificación")) {
            tipoDuracionAsignatura.setDescripcion(tfDescripcion.getText().toUpperCase());
            TipoDuracionAsignaturaFacade.getInstance().modificar(tipoDuracionAsignatura);
        }
        this.dispose();
    }

    public TipoDuracionAsignatura getTipoDuracionAsignatura() {
        return tipoDuracionAsignatura;
    }


}


