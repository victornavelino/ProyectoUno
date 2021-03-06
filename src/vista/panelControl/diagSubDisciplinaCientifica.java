/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagSubDisciplinaCientificaAlta.java
 *
 * Created on 03/07/2011, 11:07:21
 */
package vista.panelControl;

import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.SubDisciplinaCientifica;
import facade.DisciplinaCientificaFacade;
import facade.SubDisciplinaCientificaFacade;
import includes.Comunes;


/**
 *
 * @author carlos
 */
public class diagSubDisciplinaCientifica extends javax.swing.JDialog {

    private SubDisciplinaCientifica subDisciplinaCientifica;
    private String tipoOperacion;

    /** Creates new form diagSubDisciplinaCientificaAlta */
    public diagSubDisciplinaCientifica(java.awt.Frame parent, boolean modal, String tipoOperacion) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        initComponents();
        inicializarComponentes();
    }

    public diagSubDisciplinaCientifica(java.awt.Frame parent, boolean modal, String tipoOperacion, SubDisciplinaCientifica subDisciplinaCientifica) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.subDisciplinaCientifica = subDisciplinaCientifica;
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
        jLabel1 = new javax.swing.JLabel();
        cboDisciplinaCientifica = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Descripción");

        jLabel1.setText("Disciplina Científica");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDisciplinaCientifica, 0, 237, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboDisciplinaCientifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                diagSubDisciplinaCientifica dialog = new diagSubDisciplinaCientifica(new javax.swing.JFrame(), true, new String());
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
    private javax.swing.JComboBox cboDisciplinaCientifica;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tfDescripcion;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        cargarAreasTematicas();
        if (tipoOperacion.equals("Alta")) {
            tfDescripcion.setText("");
            tfDescripcion.requestFocus();
        } else if (tipoOperacion.equals("Consulta")) {
            tfDescripcion.setText(subDisciplinaCientifica.getDescripcion());
            cboDisciplinaCientifica.setSelectedItem(subDisciplinaCientifica.getDisciplinaCientifica());
            tfDescripcion.setEditable(false);
            cboDisciplinaCientifica.setEnabled(false);
        } else if (tipoOperacion.equals("Modificación")) {
            tfDescripcion.setText(subDisciplinaCientifica.getDescripcion());
            cboDisciplinaCientifica.setSelectedItem(subDisciplinaCientifica.getDisciplinaCientifica());
        }

    }

    private void aceptar() {
        if (tipoOperacion.equals("Alta")) {
            subDisciplinaCientifica = new SubDisciplinaCientifica();
            subDisciplinaCientifica.setDescripcion(tfDescripcion.getText());
            subDisciplinaCientifica.setDisciplinaCientifica((DisciplinaCientifica) cboDisciplinaCientifica.getSelectedItem());
            SubDisciplinaCientificaFacade.getInstance().alta(subDisciplinaCientifica);
        } else if (tipoOperacion.equals("Modificación")) {
            subDisciplinaCientifica.setDescripcion(tfDescripcion.getText());
            subDisciplinaCientifica.setDisciplinaCientifica((DisciplinaCientifica) cboDisciplinaCientifica.getSelectedItem());
            SubDisciplinaCientificaFacade.getInstance().modificar(subDisciplinaCientifica);
        }
        this.dispose();
    }

    public SubDisciplinaCientifica getSubDisciplinaCientificaCreada() {
        return subDisciplinaCientifica;
    }

    private void cargarAreasTematicas() {
        Comunes.cargarJCombo(cboDisciplinaCientifica, DisciplinaCientificaFacade.getInstance().getTodosDisciplinaCientifica());
    }
}
