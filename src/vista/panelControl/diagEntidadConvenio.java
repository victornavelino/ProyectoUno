/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagEntidadConvenioAlta.java
 *
 * Created on 03/07/2011, 11:07:21
 */
package vista.panelControl;

import entidades.proyecto.EntidadConvenio;
import facade.EntidadConvenioFacade;


/**
 *
 * @author carlos
 */
public class diagEntidadConvenio extends javax.swing.JDialog {

    private EntidadConvenio entidadConvenio;
    private String tipoOperacion;

    /** Creates new form diagEntidadConvenioAlta */
    public diagEntidadConvenio(java.awt.Frame parent, boolean modal, String tipoOperacion) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        initComponents();
        inicializarComponentes();
    }

    public diagEntidadConvenio(java.awt.Frame parent, boolean modal, String tipoOperacion, EntidadConvenio entidadConvenio) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.entidadConvenio = entidadConvenio;
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

        jLabel3.setText("Descripción");

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

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagEntidadConvenio dialog = new diagEntidadConvenio(new javax.swing.JFrame(), true, new String());
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
            tfDescripcion.setText(entidadConvenio.toString());
            tfDescripcion.setEditable(false);
        } else if (tipoOperacion.equals("Modificación")) {
            tfDescripcion.setText(entidadConvenio.toString());
        }

    }

    private void aceptar() {
        if (tipoOperacion.equals("Alta")) {
            entidadConvenio = new EntidadConvenio();
            entidadConvenio.setDescripcion(tfDescripcion.getText());
            EntidadConvenioFacade.getInstance().alta(entidadConvenio);
        } else if (tipoOperacion.equals("Modificación")) {
            entidadConvenio.setDescripcion(tfDescripcion.getText());
            EntidadConvenioFacade.getInstance().modificar(entidadConvenio);
        }
        this.dispose();
    }

    public EntidadConvenio getEntidadConvenioCreada() {
        return entidadConvenio;
    }
}
