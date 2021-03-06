/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.panelControl;

import entidades.DedicacionDocente;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.Universidad;
import entidades.persona.investigador.CategoriaDocente;
import entidades.persona.investigador.DepartamentoDocente;
import entidades.persona.investigador.EspecialidadActividadAcademica;
import entidades.persona.investigador.EspecialidadInvestigacion;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.ModoObtencionCargo;
import entidades.persona.investigador.actividadConduccion.CargoConduccion;
import entidades.persona.investigador.actividadConduccion.DedicacionConduccion;
import entidades.persona.investigador.curso.CarreraAsignatura;
import entidades.persona.investigador.curso.TipoAsignatura;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.Programa;
import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.titulo.TituloGrado;
import entidades.titulo.TituloOtro;
import entidades.titulo.TituloPosgrado;
import facade.CategorizacionFacade;
import facade.InvestigadorFacade;
import facade.ProyectoFacade;
import facade.ProyectoVinculacionFacade;
import includes.Comunes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vista.categorizacion.diagCategorizacionModificacion;
import vista.categorizacion.diagInvestigadorBusqueda;
import vista.proyectos.diagProyecto;
import vista.proyectos.vinculacion.diagProyectoVinculacion;
import vistas.investigadores.diagInvestigador;

/**
 *
 * @author hugo
 */
public class diagInvestigadorPModificar extends javax.swing.JDialog {
    
    
    private  Object objeto;
    /**
     * Creates new form diagProyectoPModificar
     */
    public diagInvestigadorPModificar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public diagInvestigadorPModificar(java.awt.Frame parent, boolean modal, Object objeto) {
        super(parent, modal);
        initComponents();
        this.objeto=objeto;
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
        jlistInvestigadores = new javax.swing.JList();
        btnModificarProyecto = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagInvestigadorPModificar.class, "diagInvestigadorPModificar.jPanel1.border.title"))); // NOI18N

        jlistInvestigadores.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlistInvestigadores);

        btnModificarProyecto.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorPModificar.class, "diagInvestigadorPModificar.btnModificarProyecto.text")); // NOI18N
        btnModificarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProyectoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnModificarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnModificarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        btnCerrar.setText(org.openide.util.NbBundle.getMessage(diagInvestigadorPModificar.class, "diagInvestigadorPModificar.btnCerrar.text")); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnModificarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProyectoActionPerformed
        modificarInvestigador();
    }//GEN-LAST:event_btnModificarProyectoActionPerformed

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
            java.util.logging.Logger.getLogger(diagInvestigadorPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagInvestigadorPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagInvestigadorPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagInvestigadorPModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagInvestigadorPModificar dialog = new diagInvestigadorPModificar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnModificarProyecto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jlistInvestigadores;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
      switch(objeto.getClass().getSimpleName().toString()){
          case "TituloPosgrado":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXPostgrado((TituloPosgrado)objeto));  
              break;
          case "TituloGrado":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXGrado((TituloGrado)objeto));
              break;
          case "TituloOtro":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXOtroTitulo((TituloOtro)objeto));
              break;
          case "Universidad":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXUniversidad((Universidad)objeto));
              break;
          case "DepartamentoDocente":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXDptoDocente((DepartamentoDocente)objeto));
              break;
          case "CategoriaDocente":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXCategoriaDocente((CategoriaDocente)objeto));
              break;
          case "DedicacionDocente":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXDedicacionDocente((DedicacionDocente)objeto));
              break;
          case "ModoObtencionCargo":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXModoObtencionCargo((ModoObtencionCargo)objeto));
              break;
          case "CargoConduccion":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXCargoConduccion((CargoConduccion)objeto));
              break;
          case "DedicacionConduccion":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXDedicacionConduccion((DedicacionConduccion)objeto));
              break;
          case "EspecialidadInvestigacion":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXEspecialidadInvestigacion((EspecialidadInvestigacion)objeto));
              break;
          case "EspecialidadActividadAcademica":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXEspecialidadActividadAcademica((EspecialidadActividadAcademica)objeto));
              break;
          case "TipoDuracionAsignatura":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXTipoDuracionAsignatura((TipoDuracionAsignatura)objeto));
              break;
          case "TipoAsignatura":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXTipoAsignatura((TipoAsignatura)objeto));
          break;
          case "CarreraAsignatura":
              Comunes.cargarJList(jlistInvestigadores, InvestigadorFacade.getInstance().getInvestigadoresXCarreraAsignatura((CarreraAsignatura)objeto));
          break;
          
         }      
     

    }

    private void modificarInvestigador() {
       if(jlistInvestigadores.getSelectedIndex()!=-1){
           diagInvestigador diagInvestigador= new diagInvestigador(null, true, "Modificación", (Investigador)jlistInvestigadores.getSelectedValue(), null);
           diagInvestigador.setLocation(Comunes.centrarDialog(diagInvestigador));
           diagInvestigador.setVisible(true);
           inicializarComponentes();
       }else{
           JOptionPane.showMessageDialog(null, "Debe seleccionar un Investigador de la lista \n"
                   + "para poder modificarlo!");
       }
    }

    }
