/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.panelControl;

import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.ObjetivoSocioeconomico;
import entidades.proyecto.Programa;
import entidades.proyecto.Proyecto;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import facade.CategorizacionFacade;
import facade.DisciplinaCientificaFacade;
import facade.ProyectoFacade;
import facade.ProyectoVinculacionFacade;
import facade.SubDisciplinaCientificaFacade;
import includes.Comunes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vista.categorizacion.diagCategorizacionModificacion;
import vista.categorizacion.diagInvestigadorBusqueda;
import vista.proyectos.diagProyecto;
import vista.proyectos.vinculacion.diagProyectoVinculacion;

/**
 *
 * @author hugo
 */
public class diagProyectoDisciplinaPModificar extends javax.swing.JDialog {
    
    
    private DisciplinaCientifica disciplina;
    /**
     * Creates new form diagProyectoPModificar
     */
    public diagProyectoDisciplinaPModificar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public diagProyectoDisciplinaPModificar(java.awt.Frame parent, boolean modal, DisciplinaCientifica disciplina) {
        super(parent, modal);
        initComponents();
        this.disciplina=disciplina;
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

        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlistSubdisciplinas = new javax.swing.JList();
        btnModificarProyecto1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCerrar.setText(org.openide.util.NbBundle.getMessage(diagProyectoDisciplinaPModificar.class, "diagProyectoDisciplinaPModificar.btnCerrar.text")); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyectoDisciplinaPModificar.class, "diagProyectoDisciplinaPModificar.jPanel2.border.title"))); // NOI18N

        jlistSubdisciplinas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlistSubdisciplinas);

        btnModificarProyecto1.setText(org.openide.util.NbBundle.getMessage(diagProyectoDisciplinaPModificar.class, "diagProyectoDisciplinaPModificar.btnModificarProyecto1.text")); // NOI18N
        btnModificarProyecto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProyecto1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificarProyecto1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(btnModificarProyecto1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnModificarProyecto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProyecto1ActionPerformed
        eliminarSubdisciplina();
    }//GEN-LAST:event_btnModificarProyecto1ActionPerformed

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
            java.util.logging.Logger.getLogger(diagProyectoDisciplinaPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagProyectoDisciplinaPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagProyectoDisciplinaPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagProyectoDisciplinaPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagProyectoDisciplinaPModificar dialog = new diagProyectoDisciplinaPModificar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnModificarProyecto1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList jlistSubdisciplinas;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
      
      Comunes.cargarJList(jlistSubdisciplinas, SubDisciplinaCientificaFacade.getInstance().getSubDisciplinas(disciplina));
    }

    
    private void eliminarSubdisciplina() {
        if(jlistSubdisciplinas.getSelectedIndex()!=-1){
            SubDisciplinaCientifica subdisciplina1 = (SubDisciplinaCientifica) jlistSubdisciplinas.getSelectedValue();
                try{
                    SubDisciplinaCientificaFacade.getInstance().eliminar(subdisciplina1.getId());
                    inicializarComponentes();
                }
                    
                catch(Exception ex){
                    int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                            + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                    if (resolver == JOptionPane.YES_OPTION) {
                        diagProyectoSubDisciplinaPModificar diagProyectoMod = new diagProyectoSubDisciplinaPModificar(null, true, subdisciplina1);
                        diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                        diagProyectoMod.setVisible(true);
                        inicializarComponentes();
                    }
                }
            
            
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Subdisciplina para eliminar");
        }
        
    }

    
}
