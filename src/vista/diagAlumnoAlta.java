/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagInvestigadorAlta.java
 *
 * Created on 14/02/2011, 11:37:39
 */

package vista;

import javax.swing.JOptionPane;
import org.apache.commons.validator.routines.LongValidator;

/**
 *
 * @author Carlos
 */
public class diagAlumnoAlta extends javax.swing.JDialog {

    /** Creates new form diagInvestigadorAlta */
    public diagAlumnoAlta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfApellidoNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfDni = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTitulosGrado = new javax.swing.JList();
        btAgregarTituloGrado = new javax.swing.JButton();
        btModificarTituloGrado = new javax.swing.JButton();
        EliminarTituloGrado = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.title")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jLabel1.text")); // NOI18N

        tfApellidoNombre.setEditable(false);
        tfApellidoNombre.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.tfApellidoNombre.text")); // NOI18N
        tfApellidoNombre.setFocusable(false);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jLabel2.text")); // NOI18N

        tfDni.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.tfDni.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jLabel6.text")); // NOI18N

        jScrollPane1.setViewportView(jListTitulosGrado);

        btAgregarTituloGrado.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.btAgregarTituloGrado.text")); // NOI18N

        btModificarTituloGrado.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.btModificarTituloGrado.text")); // NOI18N

        EliminarTituloGrado.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.EliminarTituloGrado.text")); // NOI18N

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jButton2.text")); // NOI18N

        jButton3.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jButton3.text")); // NOI18N

        jButton4.setText(org.openide.util.NbBundle.getMessage(diagAlumnoAlta.class, "diagAlumnoAlta.jButton4.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(tfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4)
                            .addGap(52, 52, 52))
                        .addComponent(tfApellidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btAgregarTituloGrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EliminarTituloGrado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btModificarTituloGrado, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))))
                .addGap(70, 70, 70))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jButton1)
                .addContainerGap(287, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfApellidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btAgregarTituloGrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModificarTituloGrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarTituloGrado)))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                diagAlumnoAlta dialog = new diagAlumnoAlta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton EliminarTituloGrado;
    private javax.swing.JButton btAgregarTituloGrado;
    private javax.swing.JButton btModificarTituloGrado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jListTitulosGrado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfApellidoNombre;
    private javax.swing.JTextField tfDni;
    // End of variables declaration//GEN-END:variables

    private void guardar() {
        if(validar()) {
//            Investigador investigador = new Investigador();
//            investigador.setApellidoNombre(tfApellidoNombre.getText());
//            investigador.setDni(Long.parseLong(tfDni.getText()));
//            InvestigadorFacade investigadorFacade = new InvestigadorFacade();
//            investigadorFacade.alta(investigador);
//            JOptionPane.showMessageDialog(null, "Se ha creado exitosamente el "
//                    + "investigador con el id " + investigador.getId());
        }
    }

    private boolean validar() {
        boolean flag = false;
        if(LongValidator.getInstance().isValid(tfDni.getText())) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "El D.N.I. ingresado es incorrecto",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }

}
