/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagroles.java
 *
 * Created on 14/11/2011, 20:21:29
 */
package vistas.becas;

import entidades.becas.Becas;
import entidades.becas.TipoBeneficiarioBecas;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import entidades.usuario.Usuario;
import includes.Comunes;
import facade.InvestigadorFacade;
import facade.ProyectoFacade;
import java.util.List;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import vista.incentivos.diagInvestigador;

/**
 *
 * @author huguito
 */
public class diagpostulante extends javax.swing.JDialog {

    private Proyecto proyecto;
    private Becas beca = new Becas();
    private ProyectoFacade proyectoFacade = ProyectoFacade.getInstance();
    private InvestigadorFacade investigadorfac = InvestigadorFacade.getInstance();
    private JTextField tfpostulante;
    private JList jlasesor;
    private Investigador postulante;
    private TipoBeneficiarioBecas tipobeneficiario;
    private Usuario usuario;

    /**
     * Creates new form diagroles
     */
    public diagpostulante(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        this.usuario = usuario;
        initComponents();
        inicializarcomponentes();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jListpostulantes = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 332));

        jListpostulantes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListpostulantes);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagpostulante.class, "diagpostulante.jLabel1.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(diagpostulante.class, "diagpostulante.jTextField1.text")); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagpostulante.class, "diagpostulante.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnNuevo.setText(org.openide.util.NbBundle.getMessage(diagpostulante.class, "diagpostulante.btnNuevo.text")); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnNuevo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton6.setText(org.openide.util.NbBundle.getMessage(diagpostulante.class, "diagpostulante.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagpostulante.class, "diagpostulante.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        guardarPostulante();


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        buscarpostulante();
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jListpostulantes.getSelectedIndex() != -1) {
            vistas.investigadores.diagInvestigador investigadorModificacion = new vistas.investigadores.diagInvestigador(null, true, "Modificación", (Investigador) jListpostulantes.getSelectedValue(), usuario);
            investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
            investigadorModificacion.setVisible(true);
            postulante = new Investigador();
            this.postulante = ((Investigador) jListpostulantes.getSelectedValue());

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger un postulante", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        agregarNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jListpostulantes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void inicializarcomponentes() {
        //if(beca.getTipobeneficiario().equals("Posgrado")){}
        //Comunes.cargarJList(jListpostulantes, investigadorfac.);

        Comunes.cargarJList(jListpostulantes, investigadorfac.getTodosInvestigador());
    }

    private void buscarpostulante() {
        if (!jTextField1.getText().equals("")) {
            Comunes.cargarJList(jListpostulantes, investigadorfac.buscarPorApellidoNombre(jTextField1.getText()));
        } else {
            Comunes.cargarJList(jListpostulantes, InvestigadorFacade.getInstance().getTodosInvestigador());
        }
    }

    private void guardarPostulante() {
        if (jListpostulantes.getSelectedValue() != null) {

            postulante = new Investigador();
            this.postulante = ((Investigador) jListpostulantes.getSelectedValue());

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger un postulante", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Investigador getPostulante() {
        return postulante;
    }

    private void agregarNuevo() {
        vistas.investigadores.diagInvestigador investigadorAlta = new vistas.investigadores.diagInvestigador(null, true, "Alta", usuario);
        investigadorAlta.setLocation(Comunes.centrarDialog(investigadorAlta));
        investigadorAlta.setVisible(true);
        this.postulante = investigadorAlta.getInvestigador();
        if (this.postulante != null) {
            this.dispose();
        }
    }
}
