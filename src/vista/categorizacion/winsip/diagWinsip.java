/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.categorizacion.winsip;

import entidades.categorizacion.Winsip;
import entidades.proyecto.Proyecto;
import entidades.usuario.Usuario;
import facade.ProyectoFacade;
import facade.WinsipFacade;
import includes.Comunes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import vista.diagProyectoBusquedaSimple;

/**
 *
 * @author panchi
 */
public class diagWinsip extends javax.swing.JDialog {

    private Usuario usuario;
    private Proyecto proyecto;
    private ArrayList<Object> listaAño;

    /**
     * Creates new form diagWinsip2
     */
    public diagWinsip(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarComponentes();
    }

    public diagWinsip(java.awt.Frame parent, boolean modal, Usuario usuario) {
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        taProyecto = new javax.swing.JTextArea();
        btnBuscarProyecto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstWinsip = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        checkHabilitado = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlstAnio = new javax.swing.JList();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jPanel1.border.title"))); // NOI18N

        jButton5.setText(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jPanel6.border.title"))); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jLabel3.text")); // NOI18N

        taProyecto.setEditable(false);
        taProyecto.setColumns(20);
        taProyecto.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        taProyecto.setRows(5);
        jScrollPane5.setViewportView(taProyecto);

        btnBuscarProyecto.setText(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.btnBuscarProyecto.text")); // NOI18N
        btnBuscarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProyectoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarProyecto)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProyecto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lstWinsip.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstWinsip);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.jPanel2.border.title"))); // NOI18N

        checkHabilitado.setText(org.openide.util.NbBundle.getMessage(diagWinsip.class, "diagWinsip.checkHabilitado.text")); // NOI18N
        checkHabilitado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkHabilitadoMouseClicked(evt);
            }
        });

        jlstAnio.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlstAnio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlstAnioMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jlstAnio);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkHabilitado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(checkHabilitado)
                .addGap(0, 39, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        nuevoWinsip();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        modificarWinsip();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnBuscarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProyectoActionPerformed
        inicializarComponentes();
        buscarProyecto();

    }//GEN-LAST:event_btnBuscarProyectoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkHabilitadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkHabilitadoMouseClicked
        accionCheckBox();
    }//GEN-LAST:event_checkHabilitadoMouseClicked

    private void jlstAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstAnioMouseClicked
        cargarCheckBox();
    }//GEN-LAST:event_jlstAnioMouseClicked

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
            java.util.logging.Logger.getLogger(diagWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagWinsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagWinsip dialog = new diagWinsip(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscarProyecto;
    private javax.swing.JCheckBox checkHabilitado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JList jlstAnio;
    private javax.swing.JList lstWinsip;
    private javax.swing.JTextArea taProyecto;
    // End of variables declaration//GEN-END:variables

    private void nuevoWinsip() {

        if (proyecto != null) {
            diagWinsipAlta winsipAlta = new diagWinsipAlta(null, true, "Alta", usuario, proyecto);
            winsipAlta.setLocation(Comunes.centrarDialog(winsipAlta));
            winsipAlta.setVisible(true);
            cargarWinsips();

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }

    }

    private void modificarWinsip() {
        if (proyecto != null) {
            if (lstWinsip.getSelectedIndex() != -1) {
                modificarWinsipSeleccionado();
                cargarWinsips();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un winsip");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }
    }

    private void modificarWinsipSeleccionado() {

        try {
            if (proyecto != null) {
                Winsip win = (Winsip) lstWinsip.getSelectedValue();
                diagWinsipAlta winsipModificacion = new diagWinsipAlta(null, true, "Modificación", usuario, proyecto, win);
                winsipModificacion.setLocation(Comunes.centrarDialog(winsipModificacion));
                winsipModificacion.setVisible(true);
            }
        } catch (javax.persistence.NoResultException ex) {
            JOptionPane.showMessageDialog(null, "El proyecto no tiene cargado winsip");

        }
    }

    private void eliminarWinsipSeleccionado() {

        try {
            if (proyecto != null) {
                Winsip win = (Winsip) lstWinsip.getSelectedValue();
                WinsipFacade.getInstance().eliminar(win.getId());
            }
        } catch (javax.persistence.NoResultException ex) {
            JOptionPane.showMessageDialog(null, "El proyecto no tiene cargado winsip");

        }
    }

    private void inicializarComponentes() {
        cargarListaAño();
        Comunes.cargarJList(jlstAnio, listaAño);
        Comunes.cargarJList(lstWinsip, new ArrayList<>());
    }

    private void buscarProyecto() {
        diagProyectoBusquedaSimple selecproyecto = new diagProyectoBusquedaSimple(null, true);
        selecproyecto.setLocation(Comunes.centrarDialog(selecproyecto));
        selecproyecto.setVisible(true);
        if (selecproyecto.getProyecto() != null) {
            proyecto = selecproyecto.getProyecto();
            taProyecto.setText(proyecto.toString());
            cargarWinsips();

        }

    }

    private void cargarWinsips() {
        Comunes.cargarJList(lstWinsip, WinsipFacade.getInstance().listar(proyecto));
    }

    private void eliminar() {
        if (proyecto != null) {
            if (lstWinsip.getSelectedIndex() != -1) {
                eliminarWinsipSeleccionado();
                cargarWinsips();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un winsip");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }//To change body of generated methods, choose Tools | Templates.
    }

    private void accionCheckBox() {
        if (jlstAnio.getSelectedIndex() != -1) {
            cambiarEstadoCheckBox();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un winsip");
        }
    }

    private void cambiarEstadoCheckBox() {
        if (checkHabilitado.isSelected()) {
            for (Winsip w : (List<Winsip>) WinsipFacade.getInstance().getWinsipAño(jlstAnio.getSelectedValuesList())) {
                w.setHabilitado(true);
                WinsipFacade.getInstance().modificar(w);
            }
//            Winsip win = (Winsip) lstWinsip.getSelectedValue();
//            win.setHabilitado(true);
//            WinsipFacade.getInstance().modificar(win);
        } else {
            for (Winsip w : (List<Winsip>) WinsipFacade.getInstance().getWinsipAño(jlstAnio.getSelectedValuesList())) {
                w.setHabilitado(false);
                WinsipFacade.getInstance().modificar(w);
            }
//            Winsip win = (Winsip) lstWinsip.getSelectedValue();
//            win.setHabilitado(false);
//            WinsipFacade.getInstance().modificar(win);
        }

    }

    private void cargarCheckBox() {
        if (jlstAnio.getSelectedIndex() != -1) {
            Winsip win = WinsipFacade.getInstance().getEstado((int) jlstAnio.getSelectedValue());
            checkHabilitado.setSelected(!win.isHabilitado());

        }

    }

    private void cargarListaAño() {
        SimpleDateFormat formatoAño = new SimpleDateFormat("yyyy");
        String fecha = formatoAño.format(Comunes.obtenerFechaActualDesdeDB());
        Integer añoMaximo = Integer.valueOf(fecha);
        listaAño = new ArrayList<>();
        int año;
        for (año = 1990; año <= añoMaximo; año = año + 1) {
            listaAño.add(año);
        }
    }
}
