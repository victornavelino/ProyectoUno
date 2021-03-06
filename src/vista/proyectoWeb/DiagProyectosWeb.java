/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.proyectoWeb;

import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.ProyectoWeb;
import facade.ProyectoWebFacade;
import includes.Comunes;
import includes.SuperDialog;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franco
 */
public class DiagProyectosWeb extends SuperDialog {

    private Convocatoria convocatoria;

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    /**
     * Creates new form DiagProyectosWeb
     */
    public DiagProyectosWeb(java.awt.Frame parent, boolean modal, Convocatoria convocatoria) {
        super(parent, modal);
        this.convocatoria = convocatoria;
        initComponents();
        inializarComponentes();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProyectos = new javax.swing.JList();
        btnVerDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setViewportView(lstProyectos);

        btnVerDatos.setText(org.openide.util.NbBundle.getMessage(DiagProyectosWeb.class, "DiagProyectosWeb.btnVerDatos.text")); // NOI18N
        btnVerDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btnVerDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerDatos))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDatosActionPerformed
        verProyecto();
    }//GEN-LAST:event_btnVerDatosActionPerformed

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
            java.util.logging.Logger.getLogger(DiagProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiagProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiagProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiagProyectosWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerDatos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstProyectos;
    // End of variables declaration//GEN-END:variables

    private void inializarComponentes() {
//        try {
            List<ProyectoWeb> listarFinalNoAprob = ProyectoWebFacade.getInstance().listarFinalNoAprob(convocatoria);
            Comunes.cargarJList(lstProyectos, listarFinalNoAprob);
//        } catch (Exception ex) {
//            System.out.println("Error cargando proyectos: " + ex);
//            JOptionPane.showMessageDialog(null, "No se pudieron cargar los proyectos");
//        }
    }

    private void verDatos() {
        try {
            if (lstProyectos.getSelectedIndex() != -1) {
                new reportes.Reporte().reporteProyectoConvocatoria((ProyectoWeb) lstProyectos.getSelectedValue());
            } else {
                JOptionPane.showMessageDialog(null, "No seleccionó ningún proyecto");
            }
        } catch (Exception ex) {
            System.out.println("Error cargando proyectos: " + ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar el proyecto");
        }

    }

    private void verProyecto() {
        try {
            if (lstProyectos.getSelectedIndex() != -1) {
                diagVistaProyectoWeb vistaProyectoWeb = new diagVistaProyectoWeb(null, true, (ProyectoWeb) lstProyectos.getSelectedValue());
                vistaProyectoWeb.setLocation(Comunes.centrarDialog(vistaProyectoWeb));
                vistaProyectoWeb.setVisible(true);
                inializarComponentes();
                //new reportes.Reporte().reporteProyectoConvocatoria((ProyectoWeb) lstProyectos.getSelectedValue());
            } else {
                JOptionPane.showMessageDialog(null, "No seleccionó ningún proyecto");
            }
        } catch (Exception ex) {
           System.out.println("Error cargando proyectos: " + ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar el proyecto");
        }

    }
}
