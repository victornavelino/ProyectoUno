/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagInvestigadorBusquedaSimple.java
 *
 * Created on 05/06/2011, 17:55:57
 */
package vista;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Rol;
import facade.InvestigadorFacade;
import facade.ProrrogaFacade;
import facade.ProyectoFacade;
import facade.RolFacade;
import includes.Comunes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class diagInvestigadorBusquedaMultiple2 extends javax.swing.JDialog {

    Investigador investigador;
    List<Investigador> listaInvestigador = new ArrayList<>();
    List<Investigador> listaInvestigadorNo = new ArrayList<>();
    int[] lista = null;
    private RolFacade rolFacade = new RolFacade();

    public List<Investigador> getListaInvestigador() {
        return listaInvestigador;
    }

    public void setListaInvestigador(List<Investigador> listaInvestigador) {
        this.listaInvestigador = listaInvestigador;
    }

    public List<Investigador> getListaInvestigadorNo() {
        return listaInvestigadorNo;
    }

    public void setListaInvestigadorNo(List<Investigador> listaInvestigadorNo) {
        this.listaInvestigadorNo = listaInvestigadorNo;
    }

    /**
     * Creates new form diagInvestigadorBusquedaSimple
     */
    public diagInvestigadorBusquedaMultiple2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        tfBusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        dpFinalizacion = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        dpProrroga = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        btnProrroga = new javax.swing.JButton();
        btnFinalizacion = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboRol = new javax.swing.JComboBox<>();
        jBtnRol = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jBtnVigentes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListInvestigadoresSi = new javax.swing.JList();
        btnSeleccionarTodos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlistInvestigadoresNo = new javax.swing.JList();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnSeleccionarNinguno = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.title")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jPanel1.border.title"))); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel1.text")); // NOI18N

        tfBusqueda.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.tfBusqueda.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel5.text")); // NOI18N

        btnProrroga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar2.png"))); // NOI18N
        btnProrroga.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.btnProrroga.text")); // NOI18N
        btnProrroga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProrrogaActionPerformed(evt);
            }
        });

        btnFinalizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar2.png"))); // NOI18N
        btnFinalizacion.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.btnFinalizacion.text")); // NOI18N
        btnFinalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizacionActionPerformed(evt);
            }
        });

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel6.text")); // NOI18N

        jBtnRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar2.png"))); // NOI18N
        jBtnRol.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jBtnRol.text")); // NOI18N
        jBtnRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRolActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel7.text")); // NOI18N

        jBtnVigentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar2.png"))); // NOI18N
        jBtnVigentes.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jBtnVigentes.text")); // NOI18N
        jBtnVigentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVigentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(tfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnVigentes)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dpFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFinalizacion))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboRol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dpProrroga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnProrroga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBtnRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(dpFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(dpProrroga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnProrroga, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jBtnRol, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboRol, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jBtnVigentes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel2.text")); // NOI18N

        jScrollPane1.setViewportView(jListInvestigadoresSi);

        btnSeleccionarTodos.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.btnSeleccionarTodos.text")); // NOI18N
        btnSeleccionarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarTodosActionPerformed(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jLabel3.text")); // NOI18N

        jScrollPane2.setViewportView(jlistInvestigadoresNo);

        btnAgregar.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.btnAgregar.text")); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.btnQuitar.text")); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnSeleccionarNinguno.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.btnSeleccionarNinguno.text")); // NOI18N
        btnSeleccionarNinguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarNingunoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSeleccionarNinguno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSeleccionarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnSeleccionarTodos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnQuitar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSeleccionarNinguno)
                                .addGap(0, 68, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorBusquedaMultiple2.class, "diagInvestigadorBusquedaMultiple2.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarInvestigador(tfBusqueda.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        escogerInvestigador();
        //this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        investigador = null;
        listaInvestigador = null;
        listaInvestigadorNo = null;
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSeleccionarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarTodosActionPerformed
        seleccionarTodosInvestigadores();
    }//GEN-LAST:event_btnSeleccionarTodosActionPerformed

    private void btnSeleccionarNingunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarNingunoActionPerformed
        seleccionarNingunInvestigador();
    }//GEN-LAST:event_btnSeleccionarNingunoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarInvestigador();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        quitarInvestigador();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnFinalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizacionActionPerformed
        buscarPorFechasDeParticipacionProyectos();
    }//GEN-LAST:event_btnFinalizacionActionPerformed

    private void btnProrrogaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProrrogaActionPerformed
      buscarPorFechasDeParticipacionProyectosProrroga();
    }//GEN-LAST:event_btnProrrogaActionPerformed

    private void jBtnVigentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVigentesActionPerformed
        buscarVigentes();
    }//GEN-LAST:event_jBtnVigentesActionPerformed

    private void jBtnRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRolActionPerformed
        buscarXRol();
    }//GEN-LAST:event_jBtnRolActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagInvestigadorBusquedaMultiple2 dialog = new diagInvestigadorBusquedaMultiple2(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnFinalizacion;
    private javax.swing.JButton btnProrroga;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSeleccionarNinguno;
    private javax.swing.JButton btnSeleccionarTodos;
    private org.jdesktop.swingx.JXDatePicker dpFinalizacion;
    private org.jdesktop.swingx.JXDatePicker dpProrroga;
    private javax.swing.JButton jBtnRol;
    private javax.swing.JButton jBtnVigentes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jListInvestigadoresSi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList jlistInvestigadoresNo;
    private javax.swing.JTextField tfBusqueda;
    // End of variables declaration//GEN-END:variables

    private void buscarInvestigador(String text) {
        List<Investigador> listaBusqueda = new ArrayList<>();
        if (!tfBusqueda.getText().isEmpty()) {
            try {
                for (Investigador inv : listaInvestigadorNo) {
                    if (inv.getPersona().getApellido().contains(text.toUpperCase())) {
                        listaBusqueda.add(inv);
                    }

                }
                Comunes.cargarJList(jlistInvestigadoresNo, listaBusqueda);
            } catch (Exception ex) {
                System.out.println("error");
            }
        }

    }

    private void escogerInvestigador() {
        if (!listaInvestigador.isEmpty()) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger al menos un investigador de la lista",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Investigador getInvestigador() {
        return this.investigador;
    }

    private void seleccionarTodosInvestigadores() {
        this.setListaInvestigador(InvestigadorFacade.getInstance().getTodosInvestigador());
        this.setListaInvestigadorNo(new ArrayList<Investigador>());
        Comunes.cargarJList(jListInvestigadoresSi, this.getListaInvestigador());
        Comunes.cargarJList(jlistInvestigadoresNo, this.getListaInvestigadorNo());
    }

    private void inicializarComponentes() {
        dpFinalizacion.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        dpProrroga.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        jlistInvestigadoresNo.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jListInvestigadoresSi.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setListaInvestigadorNo(InvestigadorFacade.getInstance().getTodosInvestigador());
        Comunes.cargarJList(jlistInvestigadoresNo, this.getListaInvestigadorNo());
        Comunes.cargarJCombo(jComboRol, rolFacade.getTodos());
    }

    private void seleccionarNingunInvestigador() {
        this.setListaInvestigador(new ArrayList<Investigador>());
        this.setListaInvestigadorNo(InvestigadorFacade.getInstance().getTodosInvestigador());
        Comunes.cargarJList(jlistInvestigadoresNo, this.getListaInvestigadorNo());
        Comunes.cargarJList(jListInvestigadoresSi, this.getListaInvestigador());

    }

    private void agregarInvestigador() {
        if (jlistInvestigadoresNo.getSelectedIndex() != -1) {
            for (Object list : jlistInvestigadoresNo.getSelectedValuesList()) {
                listaInvestigador.add((Investigador) list);

            }
            //listaInvestigador.add((Investigador) jlistInvestigadoresNo.getSelectedValue());
            listaInvestigadorNo.removeAll(listaInvestigador);
            Comunes.cargarJList(jListInvestigadoresSi, listaInvestigador);
            Comunes.cargarJList(jlistInvestigadoresNo, listaInvestigadorNo);
        }

    }

    private void quitarInvestigador() {
        if (jListInvestigadoresSi.getSelectedIndex() != -1) {
            for (Object list : jListInvestigadoresSi.getSelectedValuesList()) {
                listaInvestigadorNo.add((Investigador) list);

            }
//            listaInvestigadorNo.add((Investigador) jListInvestigadoresSi.getSelectedValue());
            listaInvestigador.removeAll(listaInvestigadorNo);
            Comunes.cargarJList(jListInvestigadoresSi, listaInvestigador);
            Comunes.cargarJList(jlistInvestigadoresNo, listaInvestigadorNo);
        }
    }

    private void buscarPorFechasDeParticipacionProyectos() {
        this.setListaInvestigadorNo(InvestigadorFacade.getInstance().getTodosInvestigadoresVigentes(dpFinalizacion.getDate()));
        Comunes.cargarJList(jlistInvestigadoresNo, listaInvestigadorNo);
        listaInvestigador = new ArrayList<>();
        Comunes.cargarJList(jListInvestigadoresSi, listaInvestigador);

    }

    private void buscarPorFechasDeParticipacionProyectosProrroga() {
        this.setListaInvestigadorNo(InvestigadorFacade.getInstance().getTodosInvestigadoresVigentesProrrogas(dpProrroga.getDate()));
        Comunes.cargarJList(jlistInvestigadoresNo, listaInvestigadorNo);
        listaInvestigador = new ArrayList<>();
        Comunes.cargarJList(jListInvestigadoresSi, listaInvestigador);

    }
    
    private void buscarVigentes() {
        this.setListaInvestigadorNo(InvestigadorFacade.getInstance().getTodosInvestigadoresProyectosVigentes());
        Comunes.cargarJList(jlistInvestigadoresNo, listaInvestigadorNo);
        listaInvestigador = new ArrayList<>();
        Comunes.cargarJList(jListInvestigadoresSi, listaInvestigador);

    }
    
    private void buscarXRol() {
        Rol rol = (Rol) jComboRol.getSelectedItem();
        this.setListaInvestigadorNo(InvestigadorFacade.getInstance().getTodosInvestigadoresXRol(rol));
        Comunes.cargarJList(jlistInvestigadoresNo, listaInvestigadorNo);
        listaInvestigador = new ArrayList<>();
        Comunes.cargarJList(jListInvestigadoresSi, listaInvestigador);

    }

}
