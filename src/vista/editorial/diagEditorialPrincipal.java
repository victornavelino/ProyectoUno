/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.editorial;

import entidades.proyecto.editorial.EditorialCientifica;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.usuario.Usuario;
import facade.EditorialCientificaFacade;
import facade.ProyectoVinculacionFacade;
import includes.Comunes;
import javax.swing.JOptionPane;
import vista.proyectos.vinculacion.diagProyectoVinculacion;

/**
 *
 * @author francisco
 */
public class diagEditorialPrincipal extends javax.swing.JDialog {

    private EditorialCientificaFacade editorialCientificaFacade = EditorialCientificaFacade.getInstance();
    private EditorialCientifica editorialCientifica;
    private Usuario usuario;

    /**
     * Creates new form diagEditorialPrincipal
     *
     * @param parent
     * @param modal
     */
    public diagEditorialPrincipal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarComponentes();

    }

    public diagEditorialPrincipal(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListEditoriales = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        tfFiltroEditorial = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tfFiltroAutor = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tfFiltroKeywords = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfFiltroISBN = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jPanel1.border.title"))); // NOI18N

        jScrollPane1.setViewportView(jListEditoriales);

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jLabel4.text")); // NOI18N

        tfFiltroEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFiltroEditorialActionPerformed(evt);
            }
        });

        jButton9.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton9.text")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton4.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jLabel5.text")); // NOI18N

        tfFiltroAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFiltroAutorActionPerformed(evt);
            }
        });

        jButton5.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton10.text")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jLabel6.text")); // NOI18N

        tfFiltroKeywords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFiltroKeywordsActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jLabel7.text")); // NOI18N

        tfFiltroISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFiltroISBNActionPerformed(evt);
            }
        });

        jButton7.setText(org.openide.util.NbBundle.getMessage(diagEditorialPrincipal.class, "diagEditorialPrincipal.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfFiltroKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfFiltroEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfFiltroAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfFiltroISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfFiltroEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfFiltroAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tfFiltroKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(tfFiltroISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10)))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFiltroEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroEditorialActionPerformed
//        filtrarProyecto();
    }//GEN-LAST:event_tfFiltroEditorialActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        modificarEditorial();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        agregarNuevoEditorial();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        filtrarPorTitulo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tfFiltroAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroAutorActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        filtrarPorAutor();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        eliminarEditorial();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        filtrarPorKeywords();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tfFiltroKeywordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroKeywordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroKeywordsActionPerformed

    private void tfFiltroISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroISBNActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        filtrarPorISBN();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(diagEditorialPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagEditorialPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagEditorialPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagEditorialPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagEditorialPrincipal dialog = new diagEditorialPrincipal(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jListEditoriales;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfFiltroAutor;
    private javax.swing.JTextField tfFiltroEditorial;
    private javax.swing.JTextField tfFiltroISBN;
    private javax.swing.JTextField tfFiltroKeywords;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        cargarEditorialesJList();
    }

    private void cargarEditorialesJList() {
        Comunes.cargarJList(jListEditoriales, editorialCientificaFacade.getTodosEditorial());

    }

    private void agregarNuevoEditorial() {
        diagEditorial editorial = new diagEditorial(null, true, "Alta", usuario);
        editorial.setLocation(Comunes.centrarDialog(editorial));
        editorial.setVisible(true);
        cargarEditorialesJList();
    }

    private void modificarEditorial() {
        if (jListEditoriales.getSelectedIndex() != -1) {
            modificarEditorialSeleccionado();
        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger una publicación", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarEditorialSeleccionado() {
        diagEditorial editorial = new diagEditorial(null, true, "Modificación", (EditorialCientifica) jListEditoriales.getSelectedValue(), usuario);
        editorial.setLocation(Comunes.centrarDialog(editorial));
        editorial.setVisible(true);
        cargarEditorialesJList();
    }

    private void eliminarEditorial() {
        if (jListEditoriales.getSelectedIndex() != -1) {
            EditorialCientifica p = (EditorialCientifica) jListEditoriales.getSelectedValue();
            Object[] opciones = {"Aceptar", "Cancelar"};
            JOptionPane nombreDelDialogo = new JOptionPane();

            int eleccion = JOptionPane.showOptionDialog(this,
                    "Esta seguro que quiere eliminar la publicación con el nombre:" + p.getTitulo(),
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    "Aceptar");

            if (eleccion == JOptionPane.YES_OPTION) {

                EditorialCientificaFacade.getInstance().eliminar((EditorialCientifica) jListEditoriales.getSelectedValue());
                cargarEditorialesJList();

            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger una publicación", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void filtrarPorTitulo() {
        if (!tfFiltroEditorial.getText().equals("")) {
            Comunes.cargarJList(jListEditoriales, editorialCientificaFacade.buscarPorTitulo(tfFiltroEditorial.getText()));
        } else {
            cargarEditorialesJList();
        }
    }

    private void filtrarPorAutor() {
        if (!tfFiltroAutor.getText().equals("")) {
            Comunes.cargarJList(jListEditoriales, editorialCientificaFacade.buscarPorAutor(tfFiltroAutor.getText()));
        } else {
            cargarEditorialesJList();
        }
    }

    private void filtrarPorKeywords() {
        if (!tfFiltroKeywords.getText().equals("")) {
            Comunes.cargarJList(jListEditoriales, editorialCientificaFacade.buscarPorTitulo(tfFiltroKeywords.getText()));
        } else {
            cargarEditorialesJList();
        }
    }

    private void filtrarPorISBN() {
        if (!tfFiltroISBN.getText().equals("")) {
            if (!editorialCientificaFacade.buscarPorISBN(tfFiltroISBN.getText()).isEmpty()) {
                Comunes.cargarJList(jListEditoriales, editorialCientificaFacade.buscarPorISBN(tfFiltroISBN.getText()));

            }
            else{
                            JOptionPane.showMessageDialog(null, "No existen resultados para ISBN: '"+tfFiltroISBN.getText()+"'", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } else {
            cargarEditorialesJList();
        }
    }

}
