/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagDecretoAlta.java
 *
 * Created on 23/06/2011, 11:06:28
 */
package vista.resoluciones;

import controladores.DocumentoJpaController;
import entidades.Documento;
import entidades.Resolucion;
import facade.ResolucionFacade;
import includes.Comunes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.omg.CORBA.portable.InputStream;

/**
 *
 * @author carlos
 */
public class diagResolucionEleccion extends javax.swing.JDialog {
    
    private Resolucion resolucion;

    /**
     * Creates new form diagDecretoAlta
     */
    public diagResolucionEleccion(java.awt.Frame parent, boolean modal) {
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

        pnBusqueda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListResultados = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar Resolución");

        pnBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Búsqueda"));

        jLabel4.setText("Código");

        tfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodigoActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListResultados);

        jLabel5.setText("Resultados");

        javax.swing.GroupLayout pnBusquedaLayout = new javax.swing.GroupLayout(pnBusqueda);
        pnBusqueda.setLayout(pnBusquedaLayout);
        pnBusquedaLayout.setHorizontalGroup(
            pnBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBusquedaLayout.createSequentialGroup()
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnBusquedaLayout.setVerticalGroup(
            pnBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBusquedaLayout.createSequentialGroup()
                .addGroup(pnBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton2.setText("Escoger");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Agregar Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Abrir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        escoger();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        altaNuevo();
    }//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    abrirDocumento();
}//GEN-LAST:event_jButton4ActionPerformed

    private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
    buscar();
    }//GEN-LAST:event_tfCodigoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagResolucionEleccion dialog = new diagResolucionEleccion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jListResultados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnBusqueda;
    private javax.swing.JTextField tfCodigo;
    // End of variables declaration//GEN-END:variables

    public Resolucion getResolucion() {
        return resolucion;
    }
    
    private void inicializarComponentes() {
        
    }
    
    private void buscar() {
        if (tfCodigo.getText().length() > 2) {
            Comunes.cargarJList(jListResultados, new ResolucionFacade().buscar(tfCodigo.getText()));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ingrese al menos tres caracteres","Error",JOptionPane.ERROR_MESSAGE);
            tfCodigo.requestFocus();
        }
    }
    
    private void escoger() {
        if (jListResultados.getSelectedIndex() != -1) {
            resolucion = (Resolucion) jListResultados.getSelectedValue();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger un decreto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void altaNuevo() {
        diagResolucionAlta resolucionAlta = new diagResolucionAlta(null, true, "Alta");
        resolucionAlta.setLocation(Comunes.centrarDialog(resolucionAlta));
        resolucionAlta.setVisible(true);
        if (resolucionAlta.getResolucion() != null) {
            resolucion = resolucionAlta.getResolucion();
            this.dispose();
        }
    }
    
    private void abrirDocumento() {
        if (jListResultados.getSelectedIndex() != -1) //deberás hacer 
        {
            try {
                Resolucion res = (Resolucion) jListResultados.getSelectedValue();
                Documento documento = res.getDocumento();
                byte[] archivoInterno = documento.getContenidoArchivo();
                File archivo = File.createTempFile("tmp", documento.getNombreArchivo());
                archivo.deleteOnExit();
                try (FileOutputStream fos = new FileOutputStream(archivo)) {
                    fos.write(archivoInterno);
                }
                // Open the file
                if (Desktop.isDesktopSupported() == true) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        if (archivo.exists() == true) {
                            desktop.open(archivo);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontrar el archivo: " + archivo.getAbsolutePath(), "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede ejecutar el comando de apertura en este sistema operativo", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(diagResolucionEleccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
