/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.becas.evaluacion;


import entidades.becas.evaluacion.BecasEvaluacionWeb;
import facade.becas.evaluacion.BecasEvaluacionWebFacade;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AFerSor
 */
public class DgBecasEvaluacionWebFind extends javax.swing.JDialog {

    private List<BecasEvaluacionWeb> lstBecasEvaluacionWeb;
    private List<BecasEvaluacionWeb> lstBecasEvaluacionWebSelect;
    
    public DgBecasEvaluacionWebFind(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        this.setLocationRelativeTo(parent);
        initComponents();
        this.setTitle("BecasEvaluacionWeb");
        jLBecasEvaluacionWeb.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    public List<BecasEvaluacionWeb> getLstBecasEvaluacionWebSelect() {
        return lstBecasEvaluacionWebSelect;
    }

    public void setLstBecasEvaluacionWebSelect(List<BecasEvaluacionWeb> lstBecasEvaluacionWebSelect) {
        this.lstBecasEvaluacionWebSelect = lstBecasEvaluacionWebSelect;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jbBuscar = new javax.swing.JButton();
        jtfNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLBecasEvaluacionWeb = new javax.swing.JList();
        jbSeleccionar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jbBuscar.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jbBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar2.png"))); // NOI18N
        jbBuscar.setText("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jtfNombre.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N

        jLBecasEvaluacionWeb.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(jLBecasEvaluacionWeb);

        jbSeleccionar.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jbSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept.png"))); // NOI18N
        jbSeleccionar.setText("Seleccionar");
        jbSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSeleccionarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Vrinda", 0, 22)); // NOI18N
        jLabel3.setText("   Becas - Evaluación");
        jLabel3.setToolTipText("");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(86, 112, 66), 1, true));
        jLabel3.setOpaque(true);

        jLabel1.setText("Se puede buscar por nombre de la beca, nombre o apellido del postulante");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jbSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbBuscar)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbSeleccionar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        this.cargarLista();
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSeleccionarActionPerformed
        this.seleccionarComponentes();
    }//GEN-LAST:event_jbSeleccionarActionPerformed

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
            java.util.logging.Logger.getLogger(DgBecasEvaluacionWebFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DgBecasEvaluacionWebFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DgBecasEvaluacionWebFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DgBecasEvaluacionWebFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DgBecasEvaluacionWebFind dialog = new DgBecasEvaluacionWebFind(new javax.swing.JDialog(), true);
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
    private javax.swing.JList jLBecasEvaluacionWeb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbSeleccionar;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables

     public void cargarLista(){
         try{
             lstBecasEvaluacionWeb= this.cargarBecasEvaluacionWebLike();
             
             
            // this.limpiarTabla();
             if(!lstBecasEvaluacionWeb.isEmpty()){
                 DefaultListModel dlm = new DefaultListModel();
                 for(BecasEvaluacionWeb um : lstBecasEvaluacionWeb){
                     BecasEvaluacionWeb ume = new BecasEvaluacionWeb(){

                         @Override
                         public String toString() {
                             return "{" + '}';
                         }
                         
                     };
                     dlm.addElement(um);
                 }//fin for
                 
                 jLBecasEvaluacionWeb.setModel(dlm);
             }else{
                 JOptionPane.showMessageDialog(this,"No se encontraron datos", "Mensaje", JOptionPane.ERROR_MESSAGE);
             }
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error al cargar la tabla", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//fin cargarTabla
     
    private List <BecasEvaluacionWeb> cargarBecasEvaluacionWebLike() throws Exception{
        return BecasEvaluacionWebFacade.getInstance().findBecasEvaluacionWebLike(jtfNombre.getText());
 
    }//fin cargarUnidadesMedida
    
    private void seleccionarComponentes(){
        this.setLstBecasEvaluacionWebSelect((List<BecasEvaluacionWeb>)jLBecasEvaluacionWeb.getSelectedValuesList());
        this.setVisible(Boolean.FALSE);
    }
}
