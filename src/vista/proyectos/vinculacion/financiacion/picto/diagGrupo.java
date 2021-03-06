/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagGrupo.java
 *
 * Created on 15/08/2012, 15:35:25
 */
package vista.proyectos.vinculacion.financiacion.picto;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Rol;
import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPICTO;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPict;
import entidades.proyecto.vinculacion.financiacion.picto.GrupoColaborador;
import entidades.proyecto.vinculacion.financiacion.picto.GrupoResponsable;
import entidades.usuario.Usuario;
import facade.InvestigadorFacade;
import facade.RolFacade;
import includes.Comunes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vistas.investigadores.diagInvestigador;

/**
 *
 * @author ruben
 */
public class diagGrupo extends javax.swing.JDialog {
    
    private Usuario usuario;
    private List<GrupoResponsable> grupor=new ArrayList<>();
    private List<GrupoColaborador> grupoc=new ArrayList<>(); 
    private String tipo;
    private FinanciacionPict pict;
    private FinanciacionPICTO picto;
    
    public diagGrupo(java.awt.Frame parent, boolean modal,Usuario usuario, String tipo, FinanciacionPict pict) {
        super(parent, modal);
        this.usuario=usuario;
        this.tipo=tipo;
        if(pict!=null){
        this.grupor=pict.getGr();
        this.grupoc=pict.getGc();
        }
        initComponents();
        inicializarComponentes();
    }
    
    public diagGrupo(java.awt.Frame parent, boolean modal,Usuario usuario, String tipo, FinanciacionPICTO picto) {
        super(parent, modal);
        this.usuario=usuario;
        this.tipo=tipo;
        if(picto!=null){
        this.grupoc=picto.getGc();
        this.grupor=picto.getGr();
        }
        initComponents();
        inicializarComponentes();
    }
    
     public diagGrupo(java.awt.Frame parent, boolean modal) {
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
        jTfbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListinvestigadores = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jCboxrol = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListgr = new javax.swing.JList();
        jBtnagregar = new javax.swing.JButton();
        jBtnquitar = new javax.swing.JButton();
        jBtnnuevoinv = new javax.swing.JButton();
        jBtnaceptar = new javax.swing.JButton();
        jBtnrefresco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.title")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jLabel1.text")); // NOI18N

        jTfbuscar.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jTfbuscar.text")); // NOI18N
        jTfbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTfbuscarKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jListinvestigadores);

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTfbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jCboxrol, 0, 276, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTfbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCboxrol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jLabel2.text")); // NOI18N

        jScrollPane2.setViewportView(jListgr);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );

        jBtnagregar.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jBtnagregar.text")); // NOI18N
        jBtnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnagregarActionPerformed(evt);
            }
        });

        jBtnquitar.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jBtnquitar.text")); // NOI18N
        jBtnquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnquitarActionPerformed(evt);
            }
        });

        jBtnnuevoinv.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jBtnnuevoinv.text")); // NOI18N
        jBtnnuevoinv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnnuevoinvActionPerformed(evt);
            }
        });

        jBtnaceptar.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jBtnaceptar.text")); // NOI18N
        jBtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnaceptarActionPerformed(evt);
            }
        });

        jBtnrefresco.setText(org.openide.util.NbBundle.getMessage(diagGrupo.class, "diagGrupo.jBtnrefresco.text")); // NOI18N
        jBtnrefresco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnrefrescoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnagregar)
                            .addComponent(jBtnquitar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnnuevoinv)
                        .addGap(19, 19, 19)
                        .addComponent(jBtnrefresco, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnaceptar)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jBtnagregar)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnquitar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnaceptar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnnuevoinv)
                        .addComponent(jBtnrefresco)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTfbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfbuscarKeyPressed
        Comunes.cargarJList(jListinvestigadores, InvestigadorFacade.getInstance().buscarPorApellidoNombre(jTfbuscar.getText()));
    }//GEN-LAST:event_jTfbuscarKeyPressed

    private void jBtnnuevoinvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnnuevoinvActionPerformed
        diagInvestigador nuevo=new diagInvestigador(null, true, "Alta", usuario);
        nuevo.setLocationRelativeTo(this);
        nuevo.setVisible(true);
    }//GEN-LAST:event_jBtnnuevoinvActionPerformed

    private void jBtnrefrescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnrefrescoActionPerformed
        Comunes.cargarJList(jListinvestigadores, InvestigadorFacade.getInstance().getTodosInvestigador());
    }//GEN-LAST:event_jBtnrefrescoActionPerformed

    private void jBtnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnagregarActionPerformed
        agregarAlGrupo();
    }//GEN-LAST:event_jBtnagregarActionPerformed

    private void jBtnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnquitarActionPerformed
        quitarDelGrupo();
    }//GEN-LAST:event_jBtnquitarActionPerformed

    private void jBtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnaceptarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnaceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagGrupo dialog = new diagGrupo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBtnaceptar;
    private javax.swing.JButton jBtnagregar;
    private javax.swing.JButton jBtnnuevoinv;
    private javax.swing.JButton jBtnquitar;
    private javax.swing.JButton jBtnrefresco;
    private javax.swing.JComboBox jCboxrol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListgr;
    private javax.swing.JList jListinvestigadores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTfbuscar;
    // End of variables declaration//GEN-END:variables

    private void agregarAlGrupo(){
        if(tipo.equals("gr")){
            if(jListinvestigadores.getSelectedIndex()== -1){
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un investigador ", "Error", JOptionPane.ERROR_MESSAGE);
            }else if(jCboxrol.getSelectedIndex()== -1){
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un rol ", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                Investigador inv=(Investigador) jListinvestigadores.getSelectedValue();
                Rol rol=(Rol) jCboxrol.getSelectedItem();
                GrupoResponsable gr=new GrupoResponsable();
                gr.setInvestigador(inv);
                gr.setRol(rol);
                grupor.add(gr);
                Comunes.cargarJList(jListgr, grupor);
            }
        }else if(tipo.equals("gc")){
            if(jListinvestigadores.getSelectedIndex()== -1 ){
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un investigador ", "Error", JOptionPane.ERROR_MESSAGE);   
            }else if(jCboxrol.getSelectedIndex()== -1){
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un investigador ", "Error", JOptionPane.ERROR_MESSAGE);      
            }else{
                Investigador inv=(Investigador) jListinvestigadores.getSelectedValue();
                Rol rol=(Rol) jCboxrol.getSelectedItem();
                GrupoColaborador gc=new GrupoColaborador();
                gc.setInvestigador(inv);
                gc.setRol(rol);
                grupoc.add(gc);
                Comunes.cargarJList(jListgr, grupoc);
            }
        }
    }
    private void quitarDelGrupo(){
        if(tipo.equals("gr")){
            GrupoResponsable gr=(GrupoResponsable) jListgr.getSelectedValue();
            grupor.remove(gr);
            Comunes.cargarJList(jListgr, grupor);
        }else if (tipo.equals("gc")){
            GrupoColaborador gc=(GrupoColaborador) jListgr.getSelectedValue();
            grupoc.remove(gc);
            Comunes.cargarJList(jListgr, grupoc);
        }
    }
    
    public List<GrupoResponsable> getListaResponsables(){
        return grupor;
    }
    
    public List<GrupoColaborador> getListaColaboradores(){
        return grupoc;
    }
    
    private void inicializarComponentes(){
        Comunes.cargarJList(jListinvestigadores, InvestigadorFacade.getInstance().getTodosInvestigador());
        Comunes.cargarJCombo(jCboxrol, new RolFacade().getTodos() );
        if(tipo.equals("gr") && !grupor.isEmpty()){
            
            Comunes.cargarJList(jListgr, grupor);
        }
        if(tipo.equals("gc") && !grupoc.isEmpty()){
            
            Comunes.cargarJList(jListgr, grupoc);
        }
    }
}
