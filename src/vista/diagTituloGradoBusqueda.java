/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagTituloGradoAlta.java
 *
 * Created on 22/02/2011, 09:43:38
 */
package vista;

import entidades.titulo.TituloGrado;
import facade.TituloFacade;
import facade.TituloGradoFacade;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class diagTituloGradoBusqueda extends javax.swing.JDialog {

    // Atributos
    TituloGrado tituloGrado;

    /** Creates new form diagTituloGradoAlta */
    public diagTituloGradoBusqueda(java.awt.Frame parent, boolean modal) {
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListResultados = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagTituloGradoBusqueda.class, "diagTituloGradoBusqueda.title")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagTituloGradoBusqueda.class, "diagTituloGradoBusqueda.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagTituloGradoBusqueda.class, "diagTituloGradoBusqueda.jPanel1.border.title"))); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagTituloGradoBusqueda.class, "diagTituloGradoBusqueda.jLabel2.text")); // NOI18N

        tfDescripcion.setText(org.openide.util.NbBundle.getMessage(diagTituloGradoBusqueda.class, "diagTituloGradoBusqueda.tfDescripcion.text")); // NOI18N
        tfDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDescripcionKeyReleased(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagTituloGradoBusqueda.class, "diagTituloGradoBusqueda.jLabel3.text")); // NOI18N

        jListResultados.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListResultadosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListResultados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescripcionKeyReleased
        filtrarJListTitulosGrado();
    }//GEN-LAST:event_tfDescripcionKeyReleased

    private void jListResultadosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListResultadosValueChanged
        if(jListResultados.getSelectedIndex() != -1) {
            this.tituloGrado = (TituloGrado) jListResultados.getSelectedValue();
        }
    }//GEN-LAST:event_jListResultadosValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagTituloGradoBusqueda dialog = new diagTituloGradoBusqueda(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListResultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfDescripcion;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        cargarJListTitulosGrado(TituloGradoFacade.getInstance().getTodosTituloGrado());
    }

    private void cargarJListTitulosGrado(List<TituloGrado> titulosGrado) {
        DefaultListModel modeloLista = new DefaultListModel();
        for(TituloGrado tituloGradop : titulosGrado) {
            modeloLista.addElement(tituloGradop);
        }
        jListResultados.setModel(modeloLista);
    }

    private void filtrarJListTitulosGrado() {
        if(!tfDescripcion.getText().equals("")) {
            cargarJListTitulosGrado(TituloGradoFacade.getInstance().getTituloGrado(tfDescripcion.getText()));
        } else {
            cargarJListTitulosGrado(TituloGradoFacade.getInstance().getTodosTituloGrado());
        }
    }

    public TituloGrado getTituloGrado() {
        return tituloGrado;
    }

}
