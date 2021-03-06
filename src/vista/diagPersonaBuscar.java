/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagInvestigadorAlta.java
 *
 * Created on 14/02/2011, 11:37:39
 */
package vista;

import entidades.persona.investigador.Investigador;
import entidades.persona.Persona;
import facade.InvestigadorFacade;
import facade.PersonaFacade;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.apache.commons.validator.routines.LongValidator;

/**
 *
 * @author Carlos
 */
public class diagPersonaBuscar extends javax.swing.JDialog {

    Persona personaSeleccionada;

    /** Creates new form diagInvestigadorAlta */
    public diagPersonaBuscar(java.awt.Frame parent, boolean modal) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfApellidoNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfDni = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfCalle = new javax.swing.JTextField();
        tfNumero = new javax.swing.JTextField();
        tfPiso = new javax.swing.JTextField();
        tfDpto = new javax.swing.JTextField();
        tfEntreCalles = new javax.swing.JTextField();
        tfReferencia = new javax.swing.JTextField();
        tfLocalidad = new javax.swing.JTextField();
        tfProvincia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tfBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListResultados = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.title")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel1.text")); // NOI18N

        tfApellidoNombre.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfApellidoNombre.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel2.text")); // NOI18N

        tfDni.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfDni.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel3.text")); // NOI18N

        tfTelefono.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfTelefono.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel4.text")); // NOI18N

        tfEmail.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfEmail.text")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jPanel2.border.title"))); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel6.text")); // NOI18N

        tfCalle.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfCalle.text")); // NOI18N

        tfNumero.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfNumero.text")); // NOI18N

        tfPiso.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfPiso.text")); // NOI18N

        tfDpto.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfDpto.text")); // NOI18N

        tfEntreCalles.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfEntreCalles.text")); // NOI18N

        tfReferencia.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfReferencia.text")); // NOI18N

        tfLocalidad.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfLocalidad.text")); // NOI18N

        tfProvincia.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfProvincia.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel7.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel8.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel9.text")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel10.text")); // NOI18N

        jLabel11.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel11.text")); // NOI18N

        jLabel12.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel12.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfProvincia)
                    .addComponent(tfLocalidad)
                    .addComponent(tfReferencia)
                    .addComponent(tfEntreCalles)
                    .addComponent(tfNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(tfDpto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPiso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEntreCalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfApellidoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(tfDni, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                .addGap(121, 121, 121))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfApellidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jPanel3.border.title"))); // NOI18N

        jLabel13.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel13.text")); // NOI18N

        tfBusqueda.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.tfBusqueda.text")); // NOI18N
        tfBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaKeyReleased(evt);
            }
        });

        jListResultados.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListResultadosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListResultados);

        jLabel14.setText(org.openide.util.NbBundle.getMessage(diagPersonaBuscar.class, "diagPersonaBuscar.jLabel14.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    private void tfBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaKeyReleased
        buscar();
    }//GEN-LAST:event_tfBusquedaKeyReleased

    private void jListResultadosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListResultadosValueChanged
        mostrarDatos();
    }//GEN-LAST:event_jListResultadosValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagPersonaBuscar dialog = new diagPersonaBuscar(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListResultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfApellidoNombre;
    private javax.swing.JTextField tfBusqueda;
    private javax.swing.JTextField tfCalle;
    private javax.swing.JTextField tfDni;
    private javax.swing.JTextField tfDpto;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEntreCalles;
    private javax.swing.JTextField tfLocalidad;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfPiso;
    private javax.swing.JTextField tfProvincia;
    private javax.swing.JTextField tfReferencia;
    private javax.swing.JTextField tfTelefono;
    // End of variables declaration//GEN-END:variables

    private boolean validar() {
        boolean flag = false;
        if (LongValidator.getInstance().isValid(tfDni.getText())) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "El D.N.I. ingresado es incorrecto",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }

    private void buscar() {
        cargarJListResultados(new PersonaFacade().buscarDniApellido(tfBusqueda.getText()));
    }

    private void cargarJListResultados(List<Persona> personasEncontradas) {
        DefaultListModel modeloLista = new DefaultListModel();
        if (!tfBusqueda.getText().equals("")) {
            if (!personasEncontradas.isEmpty()) {
                for (Persona persona : personasEncontradas) {
                    modeloLista.addElement(persona);
                }
            } else {
                modeloLista.addElement("No se encontraron resultados");
            }
        } else {
            List<Persona> todasPersonas = new PersonaFacade().getTodasPersonas();
            for (Persona persona : todasPersonas) {
                modeloLista.addElement(persona);
            }
        }
        jListResultados.setModel(modeloLista);
    }

    private void inicializarComponentes() {
        cargarJListResultados(new PersonaFacade().getTodasPersonas());
    }

    private void mostrarDatos() {
        if (jListResultados.getSelectedIndex() != -1) {
            personaSeleccionada = (Persona) jListResultados.getSelectedValue();
            tfApellidoNombre.setText(personaSeleccionada.toString());
            tfCalle.setText(personaSeleccionada.getDomicilio().getCalle());
            if (personaSeleccionada.getDocumentoIdentidad() != null) {
                tfDni.setText(((Long) personaSeleccionada.getDocumentoIdentidad().getNumero()).toString());
            }
            tfDpto.setText(personaSeleccionada.getDomicilio().getDpto());
            tfEmail.setText(personaSeleccionada.getCorreosElectronicos().toString());
            tfEntreCalles.setText(personaSeleccionada.getDomicilio().getEntreCalles());
            tfLocalidad.setText(personaSeleccionada.getDomicilio().getLocalidad().toString());
            tfNumero.setText((personaSeleccionada.getDomicilio().getNumero()).toString());
            tfPiso.setText(personaSeleccionada.getDomicilio().getPiso());
            tfReferencia.setText(personaSeleccionada.getDomicilio().getReferencia());
            tfTelefono.setText(personaSeleccionada.getTelefonos().toString());
        }
    }

    public Persona getPersona() {
        return personaSeleccionada;
    }
}
