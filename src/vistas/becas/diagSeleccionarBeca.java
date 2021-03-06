/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagAgregarPostulante.java
 *
 * Created on 08/11/2011, 09:35:02
 */
package vistas.becas;

import entidades.becas.Becas;
import entidades.usuario.Usuario;
import facade.BecasFacade;
import includes.Comunes;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class diagSeleccionarBeca extends javax.swing.JDialog {
    
    private BecasFacade becasFacade= BecasFacade.getInstance();
    private Usuario usuario;
    private Becas beca;
    

    public Becas getBeca() {
        return beca;
    }

    /** Creates new form diagAgregarPostulante */
    public diagSeleccionarBeca(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarcomponentes();
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jListBecas = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        tfBuscarBeca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(jListBecas);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagSeleccionarBeca.class, "diagAgregarPostulante.jLabel2.text")); // NOI18N

        tfBuscarBeca.setText(org.openide.util.NbBundle.getMessage(diagSeleccionarBeca.class, "diagAgregarPostulante.tfBuscarBeca.text")); // NOI18N
        tfBuscarBeca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfBuscarBecaKeyPressed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagSeleccionarBeca.class, "diagAgregarPostulante.jLabel1.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagSeleccionarBeca.class, "diagAgregarPostulante_1.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(tfBuscarBeca, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBuscarBeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    seleccionarbeca();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfBuscarBecaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarBecaKeyPressed
      buscarbeca();
    }//GEN-LAST:event_tfBuscarBecaKeyPressed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jListBecas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfBuscarBeca;
    // End of variables declaration//GEN-END:variables

    

    private void inicializarcomponentes() {
        Comunes.cargarJList(jListBecas, becasFacade.getTodasBecasVigentes());
       
    }

    

    
    private void filtrarbecas() {
        if (!tfBuscarBeca.getText().equals("")) {
            Comunes.cargarJList(jListBecas, becasFacade.getBecas((String)tfBuscarBeca.getText()));
        } else {
            Comunes.cargarJList(jListBecas, becasFacade.getTodasBecasVigentes());
        }
    }

    private void seleccionarbeca() {
        if(jListBecas.getSelectedValue()!=null){
        beca=((Becas)jListBecas.getSelectedValue());
        this.dispose();
        }
        else JOptionPane.showMessageDialog(null, "Debe escoger una Beca", "Error", JOptionPane.ERROR_MESSAGE);
                }

    private void buscarbeca() {
        
         if (!tfBuscarBeca.getText().equals("")) {
            Comunes.cargarJList(jListBecas, becasFacade.getBecas(tfBuscarBeca.getText()));
        } else {
            Comunes.cargarJList(jListBecas, becasFacade.getTodasBecasVigentes());
        }
    
    }

  

   
}
