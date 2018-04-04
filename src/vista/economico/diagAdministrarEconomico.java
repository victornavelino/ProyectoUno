/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagAdministrarEconomico.java
 *
 * Created on 03/08/2012, 12:15:11
 */
package vista.economico;

import entidades.economico.BienConsumo;
import entidades.economico.BienNoPersonal;
import entidades.economico.BienUso;
import entidades.economico.Cobro;
import entidades.economico.GastoViaje;
import entidades.economico.ModificacionPresupuesto;
import entidades.economico.PagoEconomico;
import entidades.economico.Presupuesto;
import entidades.economico.Rendicion;
import entidades.economico.RendicionDetalle;
import entidades.operaciones.Operacion;
import entidades.proyecto.Participacion;
import entidades.proyecto.Prorroga;
import entidades.proyecto.Proyecto;
import entidades.usuario.Usuario;
import facade.OperacionFacade;
import facade.ParticipacionFacade;
import includes.Comunes;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author wbivanco
 */
public class diagAdministrarEconomico extends javax.swing.JDialog {

    List<PagoEconomico> listaPago = new ArrayList<PagoEconomico>();
    List<Cobro> listaCobros = new ArrayList<Cobro>();
    List<Cobro> listaCobrosTotales = new ArrayList<Cobro>();
    List<Rendicion> listaRendiciones = new ArrayList<>();
    List<ModificacionPresupuesto> listaModificacionPresu = new ArrayList<ModificacionPresupuesto>();
    // Formateo las fechas
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    // Declaro atributo para poder usarlo en el formulario
    private Proyecto proyectoElegido = new Proyecto();
    private Usuario usuario;

    public Proyecto getProyectoElegido() {
        return proyectoElegido;
    }

    public void setProyectoElegido(Proyecto proyectoElegido) {
        this.proyectoElegido = proyectoElegido;
    }

    public List<ModificacionPresupuesto> getListaModificacionPresu() {
        return listaModificacionPresu;
    }

    public void setListaModificacionPresu(List<ModificacionPresupuesto> listaModificacionPresu) {
        this.listaModificacionPresu = listaModificacionPresu;
    }
    // Defino aqui el modelos de las tablas para que lo cree una sola vez
    DefaultTableModel modeloTablaPresupuestoModificado = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaPresupuestoProyecto = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaPagos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaCobros = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaRendiciones = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaPresupuesto = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaDiferenciaPresupuesto = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaPresupuestoModificadoDesvios = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaRendicionesDesvios = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaHistorialModificacionesPresupuesto = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    DefaultTableModel modeloTablaDesvios = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    /**
     * Creates new form diagAdministrarEconomico
     */
    public diagAdministrarEconomico(java.awt.Frame parent, boolean modal, Usuario usuario) {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatosPagos = new javax.swing.JTable();
        btnAgregarPago = new javax.swing.JButton();
        btnVerDetallePago = new javax.swing.JButton();
        btnModificarPago = new javax.swing.JButton();
        lblTotalPa = new javax.swing.JLabel();
        lblTotalPago = new javax.swing.JLabel();
        btnEliminarPago = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblEntidadAcreditadora = new javax.swing.JLabel();
        lblEntidadFinanciadora = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDatosCobros = new javax.swing.JTable();
        btnAgregarCobro = new javax.swing.JButton();
        btnVerDetalleCobro = new javax.swing.JButton();
        btnModificarCobro = new javax.swing.JButton();
        lblTotalCo = new javax.swing.JLabel();
        lblTotalCobro = new javax.swing.JLabel();
        btnImportarCobro = new javax.swing.JButton();
        btnEliminarCobro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDatosRendiciones = new javax.swing.JTable();
        btnAgregarRendicion = new javax.swing.JButton();
        btnVerDetalleRendicion = new javax.swing.JButton();
        btnModificarRendicion = new javax.swing.JButton();
        lblTotalRe = new javax.swing.JLabel();
        lblTotalRendicion = new javax.swing.JLabel();
        btnImportarRendicion = new javax.swing.JButton();
        btnEliminarRendicion = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDatosPresupuestoProyecto = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosPresupuestoModificado = new javax.swing.JTable();
        btnAgregarPresupuesto = new javax.swing.JButton();
        btnImportarPresupuesto = new javax.swing.JButton();
        btnModificarPresupuesto = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHistorialModificacionesPresupuesto = new javax.swing.JTable();
        btnVerDetalleModificacionPresupuesto = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDatosPresupuestoAsignado = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDatosPresupuestoModificadoDesvios = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblDatosRendicionesDesvios = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDatosDesvios = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblNombreProyecto = new javax.swing.JLabel();
        lblCodigoProyecto = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblUnidadAcademica = new javax.swing.JLabel();
        btnCargarProyecto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDirector = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProrroga = new javax.swing.JLabel();
        btnVerMasDatos = new javax.swing.JButton();
        lblTotalP = new javax.swing.JLabel();
        lblTotalPagado = new javax.swing.JLabel();
        lblTotalC = new javax.swing.JLabel();
        lblTotalCobrado = new javax.swing.JLabel();
        lblTotalR = new javax.swing.JLabel();
        lblTotalRendido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.title")); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel7.border.title"))); // NOI18N

        tblDatosPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDatosPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosPagosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDatosPagos);

        btnAgregarPago.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnAgregarPago.text")); // NOI18N
        btnAgregarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPagoActionPerformed(evt);
            }
        });

        btnVerDetallePago.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnVerDetallePago.text")); // NOI18N
        btnVerDetallePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallePagoActionPerformed(evt);
            }
        });

        btnModificarPago.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnModificarPago.text")); // NOI18N
        btnModificarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPagoActionPerformed(evt);
            }
        });

        lblTotalPa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalPa.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalPa.text")); // NOI18N

        lblTotalPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalPago.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalPago.text")); // NOI18N

        btnEliminarPago.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnEliminarPago.text")); // NOI18N
        btnEliminarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerDetallePago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalPa)
                .addGap(37, 37, 37)
                .addComponent(lblTotalPago)
                .addGap(198, 198, 198))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarPago)
                        .addGap(15, 15, 15)
                        .addComponent(btnVerDetallePago))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalPa)
                    .addComponent(lblTotalPago))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel9.text")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel10.text")); // NOI18N

        lblEntidadAcreditadora.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblEntidadAcreditadora.text")); // NOI18N

        lblEntidadFinanciadora.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblEntidadFinanciadora.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEntidadAcreditadora)
                            .addComponent(lblEntidadFinanciadora))))
                .addContainerGap(384, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lblEntidadAcreditadora)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEntidadFinanciadora)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel8.border.title"))); // NOI18N

        tblDatosCobros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDatosCobros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosCobrosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDatosCobros);

        btnAgregarCobro.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnAgregarCobro.text")); // NOI18N
        btnAgregarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCobroActionPerformed(evt);
            }
        });

        btnVerDetalleCobro.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnVerDetalleCobro.text")); // NOI18N
        btnVerDetalleCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleCobroActionPerformed(evt);
            }
        });

        btnModificarCobro.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnModificarCobro.text")); // NOI18N
        btnModificarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCobroActionPerformed(evt);
            }
        });

        lblTotalCo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalCo.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalCo.text")); // NOI18N

        lblTotalCobro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalCobro.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalCobro.text")); // NOI18N

        btnImportarCobro.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnImportarCobro.text")); // NOI18N

        btnEliminarCobro.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnEliminarCobro.text")); // NOI18N
        btnEliminarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCobroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(lblTotalCo)
                        .addGap(35, 35, 35)
                        .addComponent(lblTotalCobro))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerDetalleCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificarCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImportarCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalCobro)
                            .addComponent(lblTotalCo)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnImportarCobro)
                        .addGap(32, 32, 32)
                        .addComponent(btnAgregarCobro)
                        .addGap(5, 5, 5)
                        .addComponent(btnModificarCobro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarCobro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerDetalleCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(567, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel9.border.title"))); // NOI18N

        tblDatosRendiciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDatosRendiciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosRendicionesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDatosRendiciones);

        btnAgregarRendicion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnAgregarRendicion.text")); // NOI18N
        btnAgregarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRendicionActionPerformed(evt);
            }
        });

        btnVerDetalleRendicion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnVerDetalleRendicion.text")); // NOI18N
        btnVerDetalleRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleRendicionActionPerformed(evt);
            }
        });

        btnModificarRendicion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnModificarRendicion.text")); // NOI18N
        btnModificarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarRendicionActionPerformed(evt);
            }
        });

        lblTotalRe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalRe.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalRe.text")); // NOI18N

        lblTotalRendicion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalRendicion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalRendicion.text")); // NOI18N

        btnImportarRendicion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnImportarRendicion.text")); // NOI18N

        btnEliminarRendicion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnEliminarRendicion.text")); // NOI18N
        btnEliminarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRendicionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblTotalRe)
                        .addGap(26, 26, 26)
                        .addComponent(lblTotalRendicion))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImportarRendicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarRendicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerDetalleRendicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarRendicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarRendicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalRe)
                            .addComponent(lblTotalRendicion)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnImportarRendicion)
                        .addGap(26, 26, 26)
                        .addComponent(btnAgregarRendicion)
                        .addGap(8, 8, 8)
                        .addComponent(btnModificarRendicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarRendicion)
                        .addGap(17, 17, 17)
                        .addComponent(btnVerDetalleRendicion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(548, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel11.border.title"))); // NOI18N

        tblDatosPresupuestoProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblDatosPresupuestoProyecto);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(542, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel6.border.title"))); // NOI18N

        tblDatosPresupuestoModificado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDatosPresupuestoModificado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosPresupuestoModificadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatosPresupuestoModificado);

        btnAgregarPresupuesto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnAgregarPresupuesto.text")); // NOI18N
        btnAgregarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPresupuestoActionPerformed(evt);
            }
        });

        btnImportarPresupuesto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnImportarPresupuesto.text")); // NOI18N
        btnImportarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarPresupuestoActionPerformed(evt);
            }
        });

        btnModificarPresupuesto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnModificarPresupuesto.text")); // NOI18N
        btnModificarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPresupuestoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImportarPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnModificarPresupuesto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btnImportarPresupuesto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarPresupuesto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel13.border.title"))); // NOI18N

        tblHistorialModificacionesPresupuesto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblHistorialModificacionesPresupuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistorialModificacionesPresupuestoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblHistorialModificacionesPresupuesto);

        btnVerDetalleModificacionPresupuesto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnVerDetalleModificacionPresupuesto.text")); // NOI18N
        btnVerDetalleModificacionPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleModificacionPresupuestoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerDetalleModificacionPresupuesto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnVerDetalleModificacionPresupuesto)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel14.border.title"))); // NOI18N

        tblDatosPresupuestoAsignado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tblDatosPresupuestoAsignado);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel15.border.title"))); // NOI18N

        tblDatosPresupuestoModificadoDesvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tblDatosPresupuestoModificadoDesvios);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel16.border.title"))); // NOI18N

        tblDatosRendicionesDesvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tblDatosRendicionesDesvios);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel17.border.title"))); // NOI18N

        tblDatosDesvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(tblDatosDesvios);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel12.TabConstraints.tabTitle"), jPanel12); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jPanel4.border.title"))); // NOI18N

        lblNombreProyecto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNombreProyecto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblNombreProyecto.text")); // NOI18N

        lblCodigoProyecto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigoProyecto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblCodigoProyecto.text")); // NOI18N

        lblFechaInicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFechaInicio.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblFechaInicio.text")); // NOI18N

        lblFechaFin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFechaFin.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblFechaFin.text")); // NOI18N

        lblDuracion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDuracion.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblDuracion.text")); // NOI18N

        lblUnidadAcademica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUnidadAcademica.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblUnidadAcademica.text")); // NOI18N

        btnCargarProyecto.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnCargarProyecto.text")); // NOI18N
        btnCargarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarProyectoActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel6.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel7.text")); // NOI18N

        lblDirector.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDirector.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblDirector.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.jLabel8.text")); // NOI18N

        lblProrroga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblProrroga.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblProrroga.text")); // NOI18N

        btnVerMasDatos.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.btnVerMasDatos.text")); // NOI18N
        btnVerMasDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMasDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblFechaInicio)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblFechaFin)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel6)
                        .addGap(10, 10, 10)
                        .addComponent(lblDuracion)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lblUnidadAcademica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lblCodigoProyecto)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(77, 77, 77))
                                .addComponent(lblProrroga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerMasDatos)
                .addGap(35, 35, 35))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(btnCargarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(btnCargarProyecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblCodigoProyecto)
                    .addComponent(jLabel2)
                    .addComponent(lblNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnidadAcademica)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(lblDirector))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaFin)
                    .addComponent(lblFechaInicio)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(lblDuracion)
                    .addComponent(jLabel8)
                    .addComponent(lblProrroga)
                    .addComponent(btnVerMasDatos))
                .addContainerGap())
        );

        lblTotalP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalP.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalP.text")); // NOI18N

        lblTotalPagado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalPagado.setForeground(new java.awt.Color(0, 0, 255));
        lblTotalPagado.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalPagado.text")); // NOI18N

        lblTotalC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalC.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalC.text")); // NOI18N

        lblTotalCobrado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalCobrado.setForeground(new java.awt.Color(0, 0, 255));
        lblTotalCobrado.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalCobrado.text")); // NOI18N

        lblTotalR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalR.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalR.text")); // NOI18N

        lblTotalRendido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalRendido.setForeground(new java.awt.Color(0, 0, 255));
        lblTotalRendido.setText(org.openide.util.NbBundle.getMessage(diagAdministrarEconomico.class, "diagAdministrarEconomico.lblTotalRendido.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTotalP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalPagado)
                        .addGap(59, 59, 59)
                        .addComponent(lblTotalC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalCobrado)
                        .addGap(43, 43, 43)
                        .addComponent(lblTotalR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalRendido))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalP)
                    .addComponent(lblTotalPagado)
                    .addComponent(lblTotalC)
                    .addComponent(lblTotalCobrado)
                    .addComponent(lblTotalR)
                    .addComponent(lblTotalRendido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarProyectoActionPerformed
        cargarProyecto();
    }//GEN-LAST:event_btnCargarProyectoActionPerformed

    private void btnVerMasDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMasDatosActionPerformed
        verMasDatosProyecto();
    }//GEN-LAST:event_btnVerMasDatosActionPerformed

    private void btnModificarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPresupuestoActionPerformed
        modificarPresupuesto();
    }//GEN-LAST:event_btnModificarPresupuestoActionPerformed

    private void btnImportarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarPresupuestoActionPerformed
        importarPresupuesto();
    }//GEN-LAST:event_btnImportarPresupuestoActionPerformed

    private void btnAgregarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPresupuestoActionPerformed
        agregarPresupuesto();
    }//GEN-LAST:event_btnAgregarPresupuestoActionPerformed

    private void btnEliminarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRendicionActionPerformed
        eliminarRendicion();
        listaRendiciones.clear();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
        btnEliminarRendicion.setEnabled(false);
        btnModificarRendicion.setEnabled(false);
        btnVerDetalleRendicion.setEnabled(false);
    }//GEN-LAST:event_btnEliminarRendicionActionPerformed

    private void btnModificarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarRendicionActionPerformed
        modificarRendicion();
        listaRendiciones.clear();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
    }//GEN-LAST:event_btnModificarRendicionActionPerformed

    private void btnVerDetalleRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleRendicionActionPerformed
        verDetalleRendicion();
    }//GEN-LAST:event_btnVerDetalleRendicionActionPerformed

    private void btnAgregarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRendicionActionPerformed
        agregarRendicion();
    }//GEN-LAST:event_btnAgregarRendicionActionPerformed

    private void tblDatosRendicionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosRendicionesMouseClicked
        seleccionarFilaRendicion();
    }//GEN-LAST:event_tblDatosRendicionesMouseClicked

    private void btnEliminarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCobroActionPerformed
        eliminarCobro();
        limpiarTablaCobros();
        cargarCuerpoTablaCobros();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
        btnEliminarCobro.setEnabled(false);
        btnModificarCobro.setEnabled(false);
        btnVerDetalleCobro.setEnabled(false);
    }//GEN-LAST:event_btnEliminarCobroActionPerformed

    private void btnModificarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCobroActionPerformed
        modificarCobro();
        limpiarTablaCobros();
        cargarCuerpoTablaCobros();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
    }//GEN-LAST:event_btnModificarCobroActionPerformed

    private void btnVerDetalleCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleCobroActionPerformed
        verDetalleCobro();
    }//GEN-LAST:event_btnVerDetalleCobroActionPerformed

    private void btnAgregarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCobroActionPerformed
        agregarCobro();
    }//GEN-LAST:event_btnAgregarCobroActionPerformed

    private void tblDatosCobrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosCobrosMouseClicked
        seleccionarFilaCobro();
    }//GEN-LAST:event_tblDatosCobrosMouseClicked

    private void btnEliminarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPagoActionPerformed
        eliminarPago();
        limpiarTablaPagos();
        cargarCuerpoTablaPagos();
        limpiarTablaCobros();
        cargarCuerpoTablaCobros();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
        btnVerDetallePago.setEnabled(false);
        btnModificarPago.setEnabled(false);
        btnEliminarPago.setEnabled(false);
    }//GEN-LAST:event_btnEliminarPagoActionPerformed

    private void btnModificarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPagoActionPerformed
        modificarPago();
        limpiarTablaPagos();
        cargarCuerpoTablaPagos();
        limpiarTablaCobros();
        cargarCuerpoTablaCobros();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
    }//GEN-LAST:event_btnModificarPagoActionPerformed

    private void btnVerDetallePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallePagoActionPerformed
        verDetallePago();
    }//GEN-LAST:event_btnVerDetallePagoActionPerformed

    private void btnAgregarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPagoActionPerformed
        agregarPago();
    }//GEN-LAST:event_btnAgregarPagoActionPerformed

    private void tblDatosPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosPagosMouseClicked
        seleccionarFilaPago();
    }//GEN-LAST:event_tblDatosPagosMouseClicked

    private void btnVerDetalleModificacionPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleModificacionPresupuestoActionPerformed
        verDetalleModificacionPresupuesto();
    }//GEN-LAST:event_btnVerDetalleModificacionPresupuestoActionPerformed

    private void tblHistorialModificacionesPresupuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistorialModificacionesPresupuestoMouseClicked
        habilitarDetalleModificacionPresupuesto();
    }//GEN-LAST:event_tblHistorialModificacionesPresupuestoMouseClicked

    private void tblDatosPresupuestoModificadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosPresupuestoModificadoMouseClicked
        habilitarModificacionPresupuesto();
    }//GEN-LAST:event_tblDatosPresupuestoModificadoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagAdministrarEconomico dialog = new diagAdministrarEconomico(new javax.swing.JFrame(), true, new Usuario());
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
    private javax.swing.JButton btnAgregarCobro;
    private javax.swing.JButton btnAgregarPago;
    private javax.swing.JButton btnAgregarPresupuesto;
    private javax.swing.JButton btnAgregarRendicion;
    private javax.swing.JButton btnCargarProyecto;
    private javax.swing.JButton btnEliminarCobro;
    private javax.swing.JButton btnEliminarPago;
    private javax.swing.JButton btnEliminarRendicion;
    private javax.swing.JButton btnImportarCobro;
    private javax.swing.JButton btnImportarPresupuesto;
    private javax.swing.JButton btnImportarRendicion;
    private javax.swing.JButton btnModificarCobro;
    private javax.swing.JButton btnModificarPago;
    private javax.swing.JButton btnModificarPresupuesto;
    private javax.swing.JButton btnModificarRendicion;
    private javax.swing.JButton btnVerDetalleCobro;
    private javax.swing.JButton btnVerDetalleModificacionPresupuesto;
    private javax.swing.JButton btnVerDetallePago;
    private javax.swing.JButton btnVerDetalleRendicion;
    private javax.swing.JButton btnVerMasDatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCodigoProyecto;
    private javax.swing.JLabel lblDirector;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEntidadAcreditadora;
    private javax.swing.JLabel lblEntidadFinanciadora;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblNombreProyecto;
    private javax.swing.JLabel lblProrroga;
    private javax.swing.JLabel lblTotalC;
    private javax.swing.JLabel lblTotalCo;
    private javax.swing.JLabel lblTotalCobrado;
    private javax.swing.JLabel lblTotalCobro;
    private javax.swing.JLabel lblTotalP;
    private javax.swing.JLabel lblTotalPa;
    private javax.swing.JLabel lblTotalPagado;
    private javax.swing.JLabel lblTotalPago;
    private javax.swing.JLabel lblTotalR;
    private javax.swing.JLabel lblTotalRe;
    private javax.swing.JLabel lblTotalRendicion;
    private javax.swing.JLabel lblTotalRendido;
    private javax.swing.JLabel lblUnidadAcademica;
    private javax.swing.JTable tblDatosCobros;
    private javax.swing.JTable tblDatosDesvios;
    private javax.swing.JTable tblDatosPagos;
    private javax.swing.JTable tblDatosPresupuestoAsignado;
    private javax.swing.JTable tblDatosPresupuestoModificado;
    private javax.swing.JTable tblDatosPresupuestoModificadoDesvios;
    private javax.swing.JTable tblDatosPresupuestoProyecto;
    private javax.swing.JTable tblDatosRendiciones;
    private javax.swing.JTable tblDatosRendicionesDesvios;
    private javax.swing.JTable tblHistorialModificacionesPresupuesto;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnCargarProyecto);
        cargarEncabezadoTablaPresupuestoModificado();
        cargarEncabezadoTablaPresupuestoProyecto();
        cargarEncabezadoTablaPresupuesto();
        cargarEncabezadoTablaPagos();
        cargarEncabezadoTablaCobros();
        cargarEncabezadosRendiciones();
        cargarEncabezadoHistorialModificacionesPresupuesto();
        cargarEncabezadoTablaPresupuestoModificadoDesvios();
        cargarEncabezadoTablaRendicionesDesvios();
        cargarEncabezadoTablaDesvios();
        visualizarComponentes(false);
        lblTotalCobrado.setText("0");
        lblTotalPagado.setText("0");
        lblTotalRendido.setText("0");
        habilitarComponentes(false);
        btnImportarCobro.setVisible(false);
        btnImportarRendicion.setVisible(false);
        btnImportarPresupuesto.setVisible(false);
    }

    private void visualizarComponentes(boolean valor) {
        lblCodigoProyecto.setVisible(valor);
        lblDuracion.setVisible(valor);
        lblFechaFin.setVisible(valor);
        lblFechaInicio.setVisible(valor);
        lblNombreProyecto.setVisible(valor);
        lblUnidadAcademica.setVisible(valor);
        lblDirector.setVisible(valor);
        lblTotalCobro.setVisible(valor);
        lblTotalPago.setVisible(valor);
        lblTotalRendicion.setVisible(valor);
        lblTotalCobrado.setVisible(valor);
        lblTotalPagado.setVisible(valor);
        lblTotalRendido.setVisible(valor);
        lblTotalPa.setVisible(valor);
        lblTotalCo.setVisible(valor);
        lblTotalRe.setVisible(valor);
        lblTotalC.setVisible(valor);
        lblTotalP.setVisible(valor);
        lblTotalR.setVisible(valor);
        lblProrroga.setVisible(valor);
        btnModificarPresupuesto.setVisible(valor);
        btnAgregarPresupuesto.setVisible(false);
        btnAgregarPago.setVisible(false);
        btnEliminarPago.setVisible(false);
        lblEntidadAcreditadora.setVisible(valor);
        lblEntidadFinanciadora.setVisible(valor);
    }

    private void habilitarComponentes(boolean valor) {
        btnVerDetallePago.setEnabled(valor);
        btnModificarPago.setEnabled(valor);
        btnEliminarPago.setEnabled(valor);
        btnVerDetalleCobro.setEnabled(valor);
        btnModificarCobro.setEnabled(valor);
        btnEliminarCobro.setEnabled(valor);
        btnVerDetalleRendicion.setEnabled(valor);
        btnModificarRendicion.setEnabled(valor);
        btnEliminarRendicion.setEnabled(valor);
        btnAgregarCobro.setEnabled(valor);
        btnAgregarPago.setEnabled(valor);
        btnAgregarPresupuesto.setEnabled(valor);
        btnAgregarRendicion.setEnabled(valor);
        btnVerMasDatos.setEnabled(valor);
        btnImportarPresupuesto.setEnabled(valor);
        btnVerDetalleModificacionPresupuesto.setEnabled(false);
        btnModificarPresupuesto.setEnabled(valor);
        //btnImportarCobro.setEnabled(valor);
        //btnImportarRendicion.setEnabled(valor);
    }

    private void cargarProyecto() {
        diagBuscarProyecto buscarProyecto = new diagBuscarProyecto(null, true);
        buscarProyecto.setVisible(true);

        if (buscarProyecto.getProyectoSeleccionado() != null) {
            ParticipacionFacade parti = new ParticipacionFacade();

            setProyectoElegido(buscarProyecto.getProyectoSeleccionado());
            lblCodigoProyecto.setForeground(Color.blue);
            lblNombreProyecto.setForeground(Color.blue);
            lblDuracion.setForeground(Color.blue);
            lblFechaFin.setForeground(Color.blue);
            lblFechaInicio.setForeground(Color.blue);
            lblUnidadAcademica.setForeground(Color.blue);
            lblDirector.setForeground(Color.blue);
            lblProrroga.setForeground(Color.blue);
            lblCodigoProyecto.setText(proyectoElegido.getCodigoIncentivos());
            lblNombreProyecto.setText(proyectoElegido.getTitulo());
            try {
                lblUnidadAcademica.setText(proyectoElegido.getUnidadAcademica().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                lblUnidadAcademica.setText("-");
            }
            lblFechaInicio.setText(formato.format(proyectoElegido.getFechaInicio()));
            lblFechaFin.setText(formato.format(proyectoElegido.getFechaFinalizacion()));
            lblDuracion.setText(String.valueOf(proyectoElegido.getDuracionTeorica()));
            
            List<Prorroga> listaProrrogas = proyectoElegido.getProrrogas();
            if (listaProrrogas.size() > 0) {
                lblProrroga.setText("SI");
            } else {
                lblProrroga.setText("NO");
            }

            Participacion p = parti.getDirector(proyectoElegido);
            try {
                if (p == null) {
                    lblDirector.setText("-");
                } else {
                    lblDirector.setText(p.getInvestigador().toString());
                }
            } catch (Exception ex) {
                lblDirector.setText("Error cargando Director");
            }
            visualizarComponentes(true);

            btnAgregarPago.setEnabled(true);
            btnVerMasDatos.setEnabled(true);

            verificarPresupuesto(proyectoElegido);
            
            if(proyectoElegido.getEntidadAcreditadora() != null){
                lblEntidadAcreditadora.setText(proyectoElegido.
                        getEntidadAcreditadora().getDescripcion());
                lblEntidadAcreditadora.setVisible(true);
            }else{
                lblEntidadAcreditadora.setText("Sin Datos Cargados");
            }
            if(proyectoElegido.getEntidadFinanciadora() != null){
                lblEntidadFinanciadora.setText(proyectoElegido.
                        getEntidadFinanciadora().getDescripcion());
                lblEntidadFinanciadora.setVisible(true);
            }else{
                lblEntidadFinanciadora.setText("Sin Datos Cargados");
            }
            
            limpiarTablaPagos();
            cargarCuerpoTablaPagos();
            limpiarTablaCobros();
            cargarCuerpoTablaCobros();
            limpiarTablaRendicion();
            cargarCuerpoTablaRendiciones();
            limpiarTablaPresuspuestoModificado();
            cargarCuerpoTablaPresupuestoModificado();
            limpiarTablaPresuspuestoProyecto();
            cargarCuerpoTablaPresupuestoProyecto();
            limpiarTablaPresupuesto();
            cargarCuerpoTablaPresupuesto();
            limpiarTablaHistorialModificacionesPresupuesto();
            cargarCuerpoTablaHistorialModificacionesPresupuesto();
            limpiarTablaPresuspuestoModificadoDesvios();
            cargarCuerpoTablaPresupuestoModificadoDesvios();
            limpiarTablaRendicionesDesvios();
            cargarCuerpoTablaRendicionesDesvios();
            limpiarTablaDesvios();
            cargarCuerpoTablaDesvios();
        }
    }

    private void verMasDatosProyecto() {
        diagVerDatosProyecto datosProyecto = new diagVerDatosProyecto(null, true, proyectoElegido);
        datosProyecto.setVisible(true);
    }

    //  INICIO PAGOS
    private void cargarEncabezadoTablaPagos() {
        modeloTablaPagos.addColumn("Número de Expediente");
        modeloTablaPagos.addColumn("Monto");
        modeloTablaPagos.addColumn("Resolución Rectoral");
        modeloTablaPagos.addColumn("Fecha Rectoral");
        modeloTablaPagos.addColumn("Resolución Consejo Superior");
        modeloTablaPagos.addColumn("Fecha Consejo Superior");
        tblDatosPagos.setModel(modeloTablaPagos);
    }

    private void cargarCuerpoTablaPagos() {
        Proyecto proye = facade.PagoEconomicoFacade.getInstance().pagosxProyecto(proyectoElegido);
        if (proye != null) {
            listaPago = proye.getPagos();
            BigDecimal totalPagos = new BigDecimal(0);
            for (PagoEconomico pago : listaPago) {
                Object[] fila = new Object[6];
                fila[0] = pago.getNumeroExpediente();
                fila[1] = pago.getMonto();
                fila[2] = pago.getResolucionRectoral();
                if (pago.getResolucionRectoral().getFechaResolucion() != null) {
                    fila[3] = formato.format(pago.getResolucionRectoral().getFechaResolucion());
                }
                fila[4] = pago.getResolucionConsejoSuperior();
                if (pago.getResolucionConsejoSuperior().getFechaResolucion() != null) {
                    fila[5] = formato.format(pago.getResolucionConsejoSuperior().getFechaResolucion());
                }
                totalPagos = totalPagos.add(pago.getMonto());
                modeloTablaPagos.addRow(fila);
            }
            tblDatosPagos.setModel(modeloTablaPagos);
            lblTotalPago.setText(String.valueOf(totalPagos));
            lblTotalPagado.setText(String.valueOf(totalPagos));
            // Si hay pagos habilito los botones de cobro
            if (listaPago.size() > 0) {
                btnAgregarCobro.setEnabled(true);
                btnAgregarRendicion.setEnabled(true);
            }
        }
    }

    private void limpiarTablaPagos() {
        // Este mecanismo de limpieza tambien funciona
//        int filas = modeloTablaPagos.getRowCount(); //obtienes el n de filas
//        int x = 0; // inicias este contador
//        int tmp = filas; // esta variable sera la que lleve el conteo de la nueva ultima fila, pues al eliminar una fila cambian los indices
//        while(x < filas){ 
//            modeloTablaPagos.removeRow(tmp - 1); //eliminas la ultima fila 
//            x++; // contador avanza 
//            tmp--; //sale un indice
//        }        
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosPagos.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosPagos.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosPagos.updateUI();//actualizas la tabla
    }

    private void agregarPago() {
        diagAltaPago altaPago = new diagAltaPago(null, true, proyectoElegido, usuario);
        altaPago.setVisible(true);
        limpiarTablaPagos();
        cargarCuerpoTablaPagos();
        btnVerDetallePago.setEnabled(false);
        btnModificarPago.setEnabled(false);
        btnEliminarPago.setEnabled(false);
    }

    private void seleccionarFilaPago() {
        btnVerDetallePago.setEnabled(true);
        btnModificarPago.setEnabled(true);
        btnEliminarPago.setEnabled(true);
    }

    private void verDetallePago() {
        PagoEconomico pagoElegido = new PagoEconomico();
        int filaElegida = tblDatosPagos.getSelectedRow();
        pagoElegido = listaPago.get(filaElegida);

        diagVerDetallePago verDetallePago = new diagVerDetallePago(null, true, pagoElegido);
        verDetallePago.setVisible(true);
    }

    private void modificarPago() {
        PagoEconomico pagoElegido = new PagoEconomico();
        int filaElegida = tblDatosPagos.getSelectedRow();
        pagoElegido = listaPago.get(filaElegida);

        diagModificarPago modificarPago = new diagModificarPago(null, true, pagoElegido, usuario);
        modificarPago.setVisible(true);

        btnVerDetallePago.setEnabled(false);
        btnModificarPago.setEnabled(false);
        btnEliminarPago.setEnabled(false);
    }

    private void eliminarPago() {
        int filaElegida = tblDatosPagos.getSelectedRow();
        PagoEconomico pagoElegido = listaPago.get(filaElegida);

        int eleccion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro "
                + "que desea eliminar el pago ?",
                "Confirma Eliminacion?", JOptionPane.YES_NO_OPTION);
        if (eleccion == 0) {
            // Creo una lista con los pagos a guardar y borro el pago seleccionado
            List<PagoEconomico> al = listaPago;
            al.remove(pagoElegido);
            // Seteo los pagos que no van a ser eliminados al proyecto elegido
            proyectoElegido.setPagos(al);
            facade.PagoEconomicoFacade.getInstance().eliminarPago(pagoElegido.getId());
            facade.ProyectoFacade.getInstance().modificar(proyectoElegido);

            JOptionPane.showMessageDialog(rootPane, "El pago fue eliminado");
            Operacion operacion = new Operacion();
            operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
            operacion.setOperacion("Baja de Pago");
            operacion.setUsuario(usuario);
            OperacionFacade.getInstance().alta(operacion);
        }
    }
    // FIN PAGOS

    // INICIO RETIROS
    private void cargarEncabezadoTablaCobros() {
        modeloTablaCobros.addColumn("Fecha");
        modeloTablaCobros.addColumn("Monto");
        modeloTablaCobros.addColumn("Número de Cheque");
        modeloTablaCobros.addColumn("Pago");
        tblDatosCobros.setModel(modeloTablaCobros);
    }

    private void cargarCuerpoTablaCobros() {
        Proyecto proye = facade.PagoEconomicoFacade.getInstance().pagosxProyecto(proyectoElegido);
        if (proye != null) {
            List<PagoEconomico> listaPagos = proye.getPagos();
            BigDecimal totalCobros = new BigDecimal(0);
            listaCobrosTotales.clear();
            for (PagoEconomico pago : listaPagos) {
                listaCobros = pago.getCobros();
                for (Cobro cobro : listaCobros) {
                    if (!listaCobros.isEmpty()) {
                        listaCobrosTotales.add(cobro);
                        btnAgregarRendicion.setEnabled(true);
                    }
                    Object[] fila = new Object[4];
                    fila[0] = formato.format(cobro.getFechaCobro());
                    fila[1] = cobro.getMontoCobrado();
                    fila[2] = cobro.getNumeroCheque();
                    fila[3] = pago.toString();
                    totalCobros = totalCobros.add(cobro.getMontoCobrado());
                    modeloTablaCobros.addRow(fila);
                }
            }
            tblDatosCobros.setModel(modeloTablaCobros);
            lblTotalCobro.setText(String.valueOf(totalCobros));
            lblTotalCobrado.setText(String.valueOf(totalCobros));
        }
    }

    private void limpiarTablaCobros() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosCobros.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosCobros.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosCobros.updateUI();//actualizas la tabla
    }

    private void agregarCobro() {
        diagAltaCobro altaCobro = new diagAltaCobro(null, true, proyectoElegido, usuario);
        altaCobro.setVisible(true);
        limpiarTablaCobros();
        cargarCuerpoTablaCobros();
        btnEliminarCobro.setEnabled(false);
        btnModificarCobro.setEnabled(false);
        btnVerDetalleCobro.setEnabled(false);
    }

    private void seleccionarFilaCobro() {
        btnVerDetalleCobro.setEnabled(true);
        btnModificarCobro.setEnabled(true);
        btnEliminarCobro.setEnabled(true);
    }

    private void verDetalleCobro() {
        Cobro cobroElegido = new Cobro();
        int filaElegida = tblDatosCobros.getSelectedRow();
        cobroElegido = listaCobrosTotales.get(filaElegida);

        diagVerDetalleCobro verDetalleCobro = new diagVerDetalleCobro(null, true, cobroElegido);
        verDetalleCobro.setVisible(true);
    }

    private void modificarCobro() {
        Cobro cobroElegido = new Cobro();
        int filaElegida = tblDatosCobros.getSelectedRow();
        cobroElegido = listaCobrosTotales.get(filaElegida);

        diagModificarCobro modificarCobro = new diagModificarCobro(null, true, cobroElegido, usuario);
        modificarCobro.setVisible(true);
        btnEliminarCobro.setEnabled(false);
        btnModificarCobro.setEnabled(false);
        btnVerDetalleCobro.setEnabled(false);
    }

    private void eliminarCobro() {
        Cobro cobroElegido = new Cobro();
        int filaElegida = tblDatosCobros.getSelectedRow();
        //JOptionPane.showMessageDialog(null, "fila " + filaElegida);
        cobroElegido = listaCobrosTotales.get(filaElegida);
        //JOptionPane.showMessageDialog(null, "id " + cobroElegido.getId());
        int eleccion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro "
                + "que desea eliminar el Retiro ?",
                "Confirma Eliminacion?", JOptionPane.YES_NO_OPTION);
        if (eleccion == 0) {
            facade.CobroFacade.getInstance().eliminarCobro(cobroElegido.getId());

            JOptionPane.showMessageDialog(rootPane, "El Retiro fue eliminado");
            Operacion operacion = new Operacion();
            operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
            operacion.setOperacion("Baja de Cobro");
            operacion.setUsuario(usuario);
            OperacionFacade.getInstance().alta(operacion);
        }
    }
    // FIN RETIROS

    // INICIO RENDICIONES
    private void cargarEncabezadosRendiciones() {
        modeloTablaRendiciones.addColumn("Fecha");
        modeloTablaRendiciones.addColumn("Monto");
        modeloTablaRendiciones.addColumn("Año Rendido");
        modeloTablaRendiciones.addColumn("Libre Deuda");
        modeloTablaRendiciones.addColumn("Trámite");
        tblDatosRendiciones.setModel(modeloTablaRendiciones);
    }

    private void cargarCuerpoTablaRendiciones() {
        Proyecto proye = null;
        proye = facade.PagoEconomicoFacade.getInstance().pagosxProyecto(proyectoElegido);
        if (proye != null) {
            List<PagoEconomico> listaPagos = null;
            listaPagos = proye.getPagos();
            BigDecimal totalRendido = new BigDecimal(0);
            for (PagoEconomico pago : listaPagos) {
                List<Rendicion> listaRendi = null;
                listaRendi = pago.getRendiciones();
                for (Rendicion rendi : listaRendi) {
                    listaRendiciones.add(rendi);
                    Object[] fila = new Object[5];
                    if (rendi.getFechaRendicion() != null) {
                        fila[0] = formato.format(rendi.getFechaRendicion());
                    }
                    fila[1] = rendi.getMontoRendido();
                    fila[2] = rendi.getAnioRendido();
                    fila[3] = rendi.getLibreDeuda();
                    fila[4] = rendi.getTramiteRendicion();
                    
                    totalRendido = totalRendido.add(rendi.getMontoRendido());

                    modeloTablaRendiciones.addRow(fila);
                }
            }
            tblDatosRendiciones.setModel(modeloTablaRendiciones);
            lblTotalRendicion.setText(String.valueOf(totalRendido));
            lblTotalRendido.setText(String.valueOf(totalRendido));
        }
    }

    private void limpiarTablaRendicion() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosRendiciones.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosRendiciones.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosRendiciones.updateUI();//actualizas la tabla
    }

    private void agregarRendicion() {
        diagAltaRendicion altaRendicion = new diagAltaRendicion(null, true, proyectoElegido, usuario);
        altaRendicion.setVisible(true);
        listaRendiciones.clear();
        limpiarTablaRendicion();
        cargarCuerpoTablaRendiciones();
        btnEliminarRendicion.setEnabled(false);
        btnModificarRendicion.setEnabled(false);
        btnVerDetalleRendicion.setEnabled(false);
    }

    private void seleccionarFilaRendicion() {
        btnVerDetalleRendicion.setEnabled(true);
        btnModificarRendicion.setEnabled(true);
        btnEliminarRendicion.setEnabled(true);
    }

    private void verDetalleRendicion() {
        int filaElegida = tblDatosRendiciones.getSelectedRow();
        Rendicion rendicionElegida = listaRendiciones.get(filaElegida);

        diagVerDetalleRendicion verDetalleRendicion = new diagVerDetalleRendicion(null, true,
                rendicionElegida);
        verDetalleRendicion.setVisible(true);
    }

    private void modificarRendicion() {
        int filaElegida = tblDatosRendiciones.getSelectedRow();
        Rendicion rendicionElegida = listaRendiciones.get(filaElegida);

        diagModificarRendicion modificarRendicion = new diagModificarRendicion(null, true, rendicionElegida, usuario);
        modificarRendicion.setVisible(true);
        btnEliminarRendicion.setEnabled(false);
        btnModificarRendicion.setEnabled(false);
        btnVerDetalleRendicion.setEnabled(false);
    }

    private void eliminarRendicion() {
        int filaElegida = tblDatosRendiciones.getSelectedRow();
        Rendicion rendicionElegida = listaRendiciones.get(filaElegida);

        int eleccion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro "
                + "que desea eliminar la rendición ?",
                "Confirma Eliminacion?", JOptionPane.YES_NO_OPTION);
        if (eleccion == 0) {
            rendicionElegida.setArchivoRendicion(null);
            facade.RendicionFacade.getInstance().modificarRendicion(rendicionElegida);
            facade.RendicionFacade.getInstance().eliminarRendicion(rendicionElegida.getId());

            PagoEconomico PagoSeleccionado = new PagoEconomico();
            PagoSeleccionado = listaPago.get(filaElegida);
            facade.PagoEconomicoFacade.getInstance().modificarPago(PagoSeleccionado);

            JOptionPane.showMessageDialog(rootPane, "La rendición fue eliminada");
            Operacion operacion = new Operacion();
            operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
            operacion.setOperacion("Baja de Rendición");
            operacion.setUsuario(usuario);
            OperacionFacade.getInstance().alta(operacion);
        }
    }
    // FIN RENDICIONES

    //INICIO PRESUPUESTOS ASIGNADO
    private void cargarEncabezadoTablaPresupuestoProyecto() {
        modeloTablaPresupuestoProyecto.addColumn("Año");
        modeloTablaPresupuestoProyecto.addColumn("Consumo");
        modeloTablaPresupuestoProyecto.addColumn("No Personales");
        modeloTablaPresupuestoProyecto.addColumn("Gastos de Viaje");
        modeloTablaPresupuestoProyecto.addColumn("Capital");
        modeloTablaPresupuestoProyecto.addColumn("Totales Presup.");
        tblDatosPresupuestoProyecto.setModel(modeloTablaPresupuestoProyecto);;
    }

    private void limpiarTablaPresuspuestoProyecto() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosPresupuestoProyecto.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosPresupuestoProyecto.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosPresupuestoProyecto.updateUI();//actualizas la tabla
    }

    private void cargarCuerpoTablaPresupuestoProyecto() {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);
        if (presu != null) {
            int tamanio = presu.getBienConsumo().size();
            Object[] fila = new Object[6];
            BigDecimal totalBU = new BigDecimal(0);
            BigDecimal totalBNP = new BigDecimal(0);
            BigDecimal totalBC = new BigDecimal(0);
            BigDecimal totalGV = new BigDecimal(0);
            BigDecimal totalGral = new BigDecimal(0);
            List<BienUso> listaBU = presu.getBienUso();
            List<BienNoPersonal> listaBNP = presu.getBienNoPersonal();
            List<BienConsumo> listaBC = presu.getBienConsumo();
            List<GastoViaje> listaGV = presu.getGastosViaje();
            
            /* No me funciono este ordenamiento por defecto que ofrece java, por
            eso hago el ordenamiento por burbuja*/
            /*Collections.sort(listaBU);
            Collections.sort(listaBNP);
            Collections.sort(listaBC);
            Collections.sort(listaGV);*/
            
            /* Ordenamientos por burbuja */
            for(int i = 0; i < (listaBU.size()-1); i++){
                for(int j = i+1; j < listaBU.size(); j++){
                    if(listaBU.get(i).getAnio().compareTo(listaBU.get(j).getAnio()) > 0){                        
                        String aux = listaBU.get(i).getAnio();
                        BigDecimal valor = listaBU.get(i).getValor();
                        listaBU.get(i).setAnio(listaBU.get(j).getAnio());
                        listaBU.get(i).setValor(listaBU.get(j).getValor());
                        listaBU.get(j).setAnio(aux);
                        listaBU.get(j).setValor(valor);                        
                    }
                    
                }
            }
            
            for(int i = 0; i < (listaBNP.size()-1); i++){
                for(int j = i+1; j < listaBNP.size(); j++){
                    if(listaBNP.get(i).getAnio().compareTo(listaBNP.get(j).getAnio()) > 0){                        
                        String aux = listaBNP.get(i).getAnio();
                        BigDecimal valor = listaBNP.get(i).getValor();
                        listaBNP.get(i).setAnio(listaBNP.get(j).getAnio());
                        listaBNP.get(i).setValor(listaBNP.get(j).getValor());
                        listaBNP.get(j).setAnio(aux);
                        listaBNP.get(j).setValor(valor);                        
                    }
                    
                }
            }
               
            for(int i = 0; i < (listaBC.size()-1); i++){
                for(int j = i+1; j < listaBC.size(); j++){
                    if(listaBC.get(i).getAnio().compareTo(listaBC.get(j).getAnio()) > 0){                        
                        String aux = listaBC.get(i).getAnio();
                        BigDecimal valor = listaBC.get(i).getValor();
                        listaBC.get(i).setAnio(listaBC.get(j).getAnio());
                        listaBC.get(i).setValor(listaBC.get(j).getValor());
                        listaBC.get(j).setAnio(aux);
                        listaBC.get(j).setValor(valor);                        
                    }
                    
                }
            }
            
            for (int i = 0; i < tamanio; i++) {
                fila[0] = i + 1;
                fila[1] = listaBC.get(i).getValor();
                fila[2] = listaBNP.get(i).getValor();
                fila[3] = listaGV.get(i).getValor();
                fila[4] = listaBU.get(i).getValor();
                fila[5] = presu.getBienUso().get(i).getValor().add(
                        presu.getBienConsumo().get(i).getValor().add(
                                presu.getBienNoPersonal().get(i).getValor().add(
                                        presu.getGastosViaje().get(i).getValor())));
                modeloTablaPresupuestoProyecto.addRow(fila);
                totalBU = totalBU.add(presu.getBienUso().get(i).getValor());
                totalBNP = totalBNP.add(presu.getBienNoPersonal().get(i).getValor());
                totalBC = totalBC.add(presu.getBienConsumo().get(i).getValor());
                totalGV = totalGV.add(presu.getGastosViaje().get(i).getValor());
            }
            totalGral = totalGral.add(totalBC.add(totalBNP.add(totalBU.add(totalGV))));
            fila[0] = "Totales ";
            fila[1] = " $ " + totalBC;
            fila[2] = " $ " + totalBNP;
            fila[3] = " $ " + totalGV;
            fila[4] = " $ " + totalBU;
            fila[5] = "Total Gral.: $ " + totalGral;
            modeloTablaPresupuestoProyecto.addRow(fila);
            tblDatosPresupuestoProyecto.setModel(modeloTablaPresupuestoProyecto);
        }
    }

    private void verificarPresupuesto(Proyecto proyectoElegido) {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);

        btnImportarPresupuesto.setVisible(false);
        if (presu == null) {
            //btnAgregarPresupuesto.setEnabled(true);  
            //btnAgregarPresupuesto.setVisible(true);
            btnModificarPresupuesto.setVisible(false);
        } else {
            btnAgregarPresupuesto.setVisible(false);
            btnModificarPresupuesto.setVisible(true);
//            btnModificarPresupuesto.setEnabled(true);
        }
    }

    private void importarPresupuesto() {
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(chooser);
            File selectedFile = chooser.getSelectedFile();

            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            //InputStream in = new FileInputStream("d:\\descargas\\archivo_presupuesto.xls");
            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            //HSSFWorkbook workBook = new HSSFWorkbook(in);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Call the printToConsole method to print the cell data in the console.
         */
        almacenarPresupuesto(cellDataList);
    }

    private void almacenarPresupuesto(List cellDataList) {
        ArrayList<BienConsumo> listaBienConsumo = new ArrayList<BienConsumo>();
        ArrayList<BienNoPersonal> listaBienNoPersonal = new ArrayList<BienNoPersonal>();
        ArrayList<BienUso> listaBienUso = new ArrayList<BienUso>();
        int j = 0;
        for (int i = 7; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            j++;
            // NumberFormat numberFormat = NumberFormat.getInstance();
            // trunca a 0 digitos
            // numberFormat.setMaximumFractionDigits(0);
            // le decimos al NumberFormat que el redondeado sea mediante truncamiento.
            // numberFormat.setRoundingMode(RoundingMode.DOWN);
            // String stringCellValue = numberFormat.format(Double.parseDouble(hssfCell.toString()));
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(1);
            String stringCellValue = hssfCell.toString();
            //System.out.print("Bien de Uso " + stringCellValue + "--" +  j + "\t");            
            BienUso bienUsoImportado = new BienUso();
            bienUsoImportado.setAnio(String.valueOf(j));
            bienUsoImportado.setValor(new BigDecimal(stringCellValue));
            listaBienUso.add(bienUsoImportado);
            //1        
            hssfCell = (HSSFCell) cellTempList.get(2);
            stringCellValue = hssfCell.toString();
            //System.out.print("Bien de Consumo " + stringCellValue + "--" +  j + "\t");            
            BienConsumo bienConsumoImportado = new BienConsumo();
            bienConsumoImportado.setAnio(String.valueOf(j));
            bienConsumoImportado.setValor(new BigDecimal(stringCellValue));
            listaBienConsumo.add(bienConsumoImportado);
            //2          
            hssfCell = (HSSFCell) cellTempList.get(3);
            stringCellValue = hssfCell.toString();
            //System.out.print("Bien No Personal " + stringCellValue + "--" +  j + "\t");
            //System.out.println();
            BienNoPersonal bienNoPersonalImportado = new BienNoPersonal();
            bienNoPersonalImportado.setAnio(String.valueOf(j));
            bienNoPersonalImportado.setValor(new BigDecimal(stringCellValue));
            listaBienNoPersonal.add(bienNoPersonalImportado);
            //3                           

        }
        Presupuesto presuImportado = new Presupuesto();
        presuImportado.setBienUso(listaBienUso);
        presuImportado.setBienNoPersonal(listaBienNoPersonal);
        presuImportado.setBienConsumo(listaBienConsumo);
        presuImportado.setProyecto(getProyectoElegido());
        facade.PresupuestoFacade.getInstance().altaPresupuesto(presuImportado);

        JOptionPane.showMessageDialog(null, "Importación Realizada con Exito");
    }
    // FIN PRESUPUESTO

    // INICIO MODIFICACIONES PRESUPUESTARIAS        
    private void cargarEncabezadoTablaPresupuesto() {
        modeloTablaPresupuesto.addColumn("Año");
        modeloTablaPresupuesto.addColumn("Consumo");
        modeloTablaPresupuesto.addColumn("No Personales");
        modeloTablaPresupuesto.addColumn("Gastos de Viaje");
        modeloTablaPresupuesto.addColumn("Capital");
        modeloTablaPresupuesto.addColumn("Totales Presup.");
        tblDatosPresupuestoAsignado.setModel(modeloTablaPresupuesto);;
    }

    private void limpiarTablaPresupuesto() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosPresupuestoAsignado.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosPresupuestoAsignado.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosPresupuestoAsignado.updateUI();//actualizas la tabla
    }

    private void cargarCuerpoTablaPresupuesto() {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);
        if (presu != null) {
            int tamanio = presu.getBienConsumo().size();
            Object[] fila = new Object[6];
            BigDecimal totalBU = new BigDecimal(0);
            BigDecimal totalBNP = new BigDecimal(0);
            BigDecimal totalBC = new BigDecimal(0);
            BigDecimal totalGV = new BigDecimal(0);
            BigDecimal totalGral = new BigDecimal(0);
            List<BienUso> listaBU = presu.getBienUso();
            List<BienNoPersonal> listaBNP = presu.getBienNoPersonal();
            List<BienConsumo> listaBC = presu.getBienConsumo();
            List<GastoViaje> listaGV = presu.getGastosViaje();
            Collections.sort(listaBC);
            Collections.sort(listaBNP);
            Collections.sort(listaBU);
            Collections.sort(listaGV);
            for (int i = 0; i < tamanio; i++) {
                fila[0] = i + 1;
                fila[1] = listaBC.get(i).getValor();
                fila[2] = listaBNP.get(i).getValor();
                fila[3] = listaGV.get(i).getValor();
                fila[4] = listaBU.get(i).getValor();
                fila[5] = presu.getBienUso().get(i).getValor().add(
                        presu.getBienConsumo().get(i).getValor().add(
                                presu.getBienNoPersonal().get(i).getValor().add(
                                        presu.getGastosViaje().get(i).getValor())));
                modeloTablaPresupuesto.addRow(fila);
                totalBU = totalBU.add(presu.getBienUso().get(i).getValor());
                totalBNP = totalBNP.add(presu.getBienNoPersonal().get(i).getValor());
                totalBC = totalBC.add(presu.getBienConsumo().get(i).getValor());
                totalGV = totalGV.add(presu.getGastosViaje().get(i).getValor());
            }
            totalGral = totalGral.add(totalBC.add(totalBNP.add(totalBU.add(totalGV))));
            fila[0] = "Totales ";
            fila[1] = " $ " + totalBC;
            fila[2] = " $ " + totalBNP;
            fila[3] = " $ " + totalGV;
            fila[4] = " $ " + totalBU;
            fila[5] = totalGral;
            modeloTablaPresupuesto.addRow(fila);
            tblDatosPresupuestoAsignado.setModel(modeloTablaPresupuesto);
        }
    }

    private void cargarEncabezadoTablaPresupuestoModificado() {
        modeloTablaPresupuestoModificado.addColumn("Año");
        modeloTablaPresupuestoModificado.addColumn("Consumo");
        modeloTablaPresupuestoModificado.addColumn("No Personales");
        modeloTablaPresupuestoModificado.addColumn("Gastos de Viaje");
        modeloTablaPresupuestoModificado.addColumn("Capital");
        modeloTablaPresupuestoModificado.addColumn("Totales Presup.");
        tblDatosPresupuestoModificado.setModel(modeloTablaPresupuestoModificado);
    }

    private void cargarCuerpoTablaPresupuestoModificado() {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);
        if (presu != null) {
            int tamanio = presu.getBienConsumo().size();
            Object[] fila = new Object[6];
            BigDecimal totalBU = new BigDecimal(0);
            BigDecimal totalBNP = new BigDecimal(0);
            BigDecimal totalBC = new BigDecimal(0);
            BigDecimal totalGV = new BigDecimal(0);
            BigDecimal totalGral = new BigDecimal(0);
            List<BienUso> listaBU = presu.getBienUso();
            List<BienNoPersonal> listaBNP = presu.getBienNoPersonal();
            List<BienConsumo> listaBC = presu.getBienConsumo();
            List<GastoViaje> listaGV = presu.getGastosViaje();
            Collections.sort(listaBC);
            Collections.sort(listaBNP);
            Collections.sort(listaBU);
            Collections.sort(listaGV);
            for (int i = 0; i < tamanio; i++) {
                fila[0] = i + 1;
                fila[1] = listaBC.get(i).getValorA();
                fila[2] = listaBNP.get(i).getValorA();
                fila[3] = listaGV.get(i).getValorA();
                fila[4] = listaBU.get(i).getValorA();
                fila[5] = presu.getBienUso().get(i).getValorA().add(
                        presu.getBienConsumo().get(i).getValorA().add(
                                presu.getBienNoPersonal().get(i).getValorA().add(
                                        presu.getGastosViaje().get(i).getValorA())));
                modeloTablaPresupuestoModificado.addRow(fila);
                totalBU = totalBU.add(presu.getBienUso().get(i).getValorA());
                totalBNP = totalBNP.add(presu.getBienNoPersonal().get(i).getValorA());
                totalBC = totalBC.add(presu.getBienConsumo().get(i).getValorA());
                totalGV = totalGV.add(presu.getGastosViaje().get(i).getValorA());
            }
            totalGral = totalGral.add(totalBC.add(totalBNP.add(totalBU.add(totalGV))));
            fila[0] = "Totales ";
            fila[1] = " $ " + totalBC;
            fila[2] = " $ " + totalBNP;
            fila[3] = " $ " + totalGV;
            fila[4] = " $ " + totalBU;
            fila[5] = totalGral;
            modeloTablaPresupuestoModificado.addRow(fila);
            tblDatosPresupuestoModificado.setModel(modeloTablaPresupuestoModificado);
        }
    }

    private void limpiarTablaPresuspuestoModificado() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosPresupuestoModificado.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosPresupuestoModificado.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosPresupuestoModificado.updateUI();//actualizas la tabla
    }

    private void cargarEncabezadoHistorialModificacionesPresupuesto() {
        modeloTablaHistorialModificacionesPresupuesto.addColumn("Año");
        modeloTablaHistorialModificacionesPresupuesto.addColumn("Consumo");
        modeloTablaHistorialModificacionesPresupuesto.addColumn("No Personales");
        modeloTablaHistorialModificacionesPresupuesto.addColumn("Gastos de Viaje");
        modeloTablaHistorialModificacionesPresupuesto.addColumn("Capital");
//        modeloTablaHistorialModificacionesPresupuesto.addColumn("Totales Presup.");
        tblHistorialModificacionesPresupuesto.setModel(modeloTablaHistorialModificacionesPresupuesto);;
    }

    private void limpiarTablaHistorialModificacionesPresupuesto() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblHistorialModificacionesPresupuesto.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblHistorialModificacionesPresupuesto.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblHistorialModificacionesPresupuesto.updateUI();//actualizas la tabla
    }

    private void cargarCuerpoTablaHistorialModificacionesPresupuesto() {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);
        List<ModificacionPresupuesto> listaModi = facade.ModificacionPresupuestoFacade.getInstance().
                modificacionesxPresupuesto(presu);

        if (presu != null) {
            Object[] fila = new Object[5];
            listaModificacionPresu.clear();
            for (ModificacionPresupuesto modi : listaModi) {
                listaModificacionPresu.add(modi);
                fila[0] = modi.getAnioModificado();
                fila[1] = modi.getMontoBC();
                fila[2] = modi.getMontoBNP();
                fila[3] = modi.getMontoGV();
                fila[4] = modi.getMontoBU();
//                fila[5] = modi.getMontoBC().add(modi.getMontoBNP()).
//                        add(modi.getMontoBU()).add(modi.getMontoGV());  
                modeloTablaHistorialModificacionesPresupuesto.addRow(fila);
            }
            setListaModificacionPresu(listaModificacionPresu);
            tblHistorialModificacionesPresupuesto.setModel(modeloTablaHistorialModificacionesPresupuesto);
        }
    }

    private void agregarPresupuesto() {
        diagAltaPresupuesto diaAltaPresupuesto = new diagAltaPresupuesto(null, true, proyectoElegido, usuario);
        diaAltaPresupuesto.setVisible(true);
        limpiarTablaPresuspuestoModificado();
        cargarCuerpoTablaPresupuestoModificado();
        limpiarTablaHistorialModificacionesPresupuesto();
        cargarCuerpoTablaHistorialModificacionesPresupuesto();
        limpiarTablaPresuspuestoModificadoDesvios();
        cargarCuerpoTablaPresupuestoModificadoDesvios();
        verificarPresupuesto(proyectoElegido);
    }

    private void modificarPresupuesto() {
        int anio = tblDatosPresupuestoModificado.getSelectedRow() + 1;        
        Presupuesto presupuestoElegido = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);        
        diagModificarPresupuesto modificarPresupuesto = new diagModificarPresupuesto(null, true, presupuestoElegido, usuario, anio);
        modificarPresupuesto.setVisible(true);
        limpiarTablaPresuspuestoModificado();
        cargarCuerpoTablaPresupuestoModificado();
        limpiarTablaHistorialModificacionesPresupuesto();
        cargarCuerpoTablaHistorialModificacionesPresupuesto();
        limpiarTablaPresuspuestoModificadoDesvios();
        cargarCuerpoTablaPresupuestoModificadoDesvios();
    }

    private void verDetalleModificacionPresupuesto() {
        int filaElegida = tblHistorialModificacionesPresupuesto.getSelectedRow();
        ModificacionPresupuesto modificacionPresupuestoElegida = getListaModificacionPresu().get(filaElegida);

        diagVerDetalleModificacionPrespuesto detalleModificacionPrespuesto
                = new diagVerDetalleModificacionPrespuesto(null, true, modificacionPresupuestoElegida);
        detalleModificacionPrespuesto.setVisible(true);
    }

    private void habilitarDetalleModificacionPresupuesto() {
        btnVerDetalleModificacionPresupuesto.setEnabled(true);
    }
    
    private void habilitarModificacionPresupuesto() {
        btnModificarPresupuesto.setEnabled(true);        
    }
    // FIN MODIFICACIONES PRESUPUESTARIAS        

    // INICIO DESVIOS
    private void cargarEncabezadoTablaPresupuestoModificadoDesvios() {
        modeloTablaPresupuestoModificadoDesvios.addColumn("Año");
        modeloTablaPresupuestoModificadoDesvios.addColumn("Consumo");
        modeloTablaPresupuestoModificadoDesvios.addColumn("No Personales");
        modeloTablaPresupuestoModificadoDesvios.addColumn("Gastos de Viaje");
        modeloTablaPresupuestoModificadoDesvios.addColumn("Capital");
        modeloTablaPresupuestoModificadoDesvios.addColumn("Totales Presup.");
        tblDatosPresupuestoModificadoDesvios.setModel(modeloTablaPresupuestoModificadoDesvios);
    }

    private void cargarCuerpoTablaPresupuestoModificadoDesvios() {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);
        if (presu != null) {
            int tamanio = presu.getBienConsumo().size();
            Object[] fila = new Object[6];
            BigDecimal totalBU = new BigDecimal(0);
            BigDecimal totalBNP = new BigDecimal(0);
            BigDecimal totalBC = new BigDecimal(0);
            BigDecimal totalGV = new BigDecimal(0);
            BigDecimal totalGral = new BigDecimal(0);
            List<BienUso> listaBU = presu.getBienUso();
            List<BienNoPersonal> listaBNP = presu.getBienNoPersonal();
            List<BienConsumo> listaBC = presu.getBienConsumo();
            List<GastoViaje> listaGV = presu.getGastosViaje();
            Collections.sort(listaBC);
            Collections.sort(listaBNP);
            Collections.sort(listaBU);
            Collections.sort(listaGV);
            for (int i = 0; i < tamanio; i++) {
                fila[0] = i + 1;
                fila[1] = listaBC.get(i).getValorA();
                fila[2] = listaBNP.get(i).getValorA();
                fila[3] = listaGV.get(i).getValorA();
                fila[4] = listaBU.get(i).getValorA();
                fila[5] = presu.getBienUso().get(i).getValorA().add(
                        presu.getBienConsumo().get(i).getValorA().add(
                                presu.getBienNoPersonal().get(i).getValorA().add(
                                        presu.getGastosViaje().get(i).getValorA())));
                modeloTablaPresupuestoModificadoDesvios.addRow(fila);
                totalBU = totalBU.add(presu.getBienUso().get(i).getValorA());
                totalBNP = totalBNP.add(presu.getBienNoPersonal().get(i).getValorA());
                totalBC = totalBC.add(presu.getBienConsumo().get(i).getValorA());
                totalGV = totalGV.add(presu.getGastosViaje().get(i).getValorA());
            }
            totalGral = totalGral.add(totalBC.add(totalBNP.add(totalBU.add(totalGV))));
            fila[0] = "Totales ";
            fila[1] = " $ " + totalBC;
            fila[2] = " $ " + totalBNP;
            fila[3] = " $ " + totalGV;
            fila[4] = " $ " + totalBU;
            fila[5] = totalGral;
            modeloTablaPresupuestoModificadoDesvios.addRow(fila);
            tblDatosPresupuestoModificadoDesvios.setModel(modeloTablaPresupuestoModificadoDesvios);
        }
    }

    private void limpiarTablaPresuspuestoModificadoDesvios() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosPresupuestoModificadoDesvios.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosPresupuestoModificadoDesvios.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosPresupuestoModificadoDesvios.updateUI();//actualizas la tabla
    }

    private void cargarEncabezadoTablaRendicionesDesvios() {
        modeloTablaRendicionesDesvios.addColumn("Fecha Rendición");
        modeloTablaRendicionesDesvios.addColumn("Año Rendido");
        modeloTablaRendicionesDesvios.addColumn("Bienes Consumo");
        modeloTablaRendicionesDesvios.addColumn("Gastos No Personales");
        modeloTablaRendicionesDesvios.addColumn("Gastos de Viaje");
        modeloTablaRendicionesDesvios.addColumn("Bienes Capital");
        modeloTablaRendicionesDesvios.addColumn("Totales Presupuestado");
        tblDatosRendicionesDesvios.setModel(modeloTablaRendicionesDesvios);
    }

    private void cargarCuerpoTablaRendicionesDesvios() {
        Proyecto proye = facade.PagoEconomicoFacade.getInstance().pagosxProyecto(proyectoElegido);
        if (proye != null) {
            List<PagoEconomico> listaPagos = null;
            listaPagos = proye.getPagos();
            BigDecimal totalRendido = new BigDecimal(0);
            for (PagoEconomico pago : listaPagos) {
                List<Rendicion> listaRendi = null;
                listaRendi = pago.getRendiciones();
                List<RendicionDetalle> listaRendicionDetalle = null;

                for (Rendicion rendi : listaRendi) {
                    listaRendicionDetalle = rendi.getRendicionDetalle();
                    Object[] fila = new Object[7];
                    fila[0] = formato.format(rendi.getFechaRendicion());
                    fila[1] = rendi.getAnioRendido();
                    BigDecimal totalBCo = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal totalSNP = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal totalGV = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal totalBCa = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                    for (RendicionDetalle rendiDetalle : listaRendicionDetalle) {
                        if (rendiDetalle.getTipoBien().equals("Bien de Consumo")) {
                            totalBCo = totalBCo.add(rendiDetalle.getPrecioUnitario());
                        }
                        if (rendiDetalle.getTipoBien().equals("Servicio No Personal")) {
                            totalSNP = totalSNP.add(rendiDetalle.getPrecioUnitario());
                        }
                        if (rendiDetalle.getTipoBien().equals("Gastos de Viaje")) {
                            totalGV = totalGV.add(rendiDetalle.getPrecioUnitario());
                        }
                        if (rendiDetalle.getTipoBien().equals("Bien de Capital")) {
                            totalBCa = totalBCa.add(rendiDetalle.getPrecioUnitario());
                        }
                    }
                    fila[2] = totalBCo;
                    fila[3] = totalSNP;
                    fila[4] = totalGV;
                    fila[5] = totalBCa;
                    fila[6] = totalBCa.add(totalGV.add(totalSNP.add(totalBCo)));
                    modeloTablaRendicionesDesvios.addRow(fila);
                }
            }
            tblDatosRendiciones.setModel(modeloTablaRendiciones);
        }
    }

    private void limpiarTablaRendicionesDesvios() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosRendicionesDesvios.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosRendicionesDesvios.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosRendicionesDesvios.updateUI();//actualizas la tabla
    }

    private void cargarEncabezadoTablaDesvios() {
        modeloTablaDesvios.addColumn("Año");
        modeloTablaDesvios.addColumn("Consumo");
        modeloTablaDesvios.addColumn("No Personales");
        modeloTablaDesvios.addColumn("Gastos de Viaje");
        modeloTablaDesvios.addColumn("Capital");
        modeloTablaDesvios.addColumn("Totales Presup.");
        tblDatosDesvios.setModel(modeloTablaDesvios);
    }

    private void cargarCuerpoTablaDesvios() {
        Presupuesto presu = facade.PresupuestoFacade.getInstance().presupuestoxProyecto(proyectoElegido);
        Proyecto proye = facade.PagoEconomicoFacade.getInstance().pagosxProyecto(proyectoElegido);

        if (proye != null) {
            List<PagoEconomico> listaPagos = null;
            listaPagos = proye.getPagos();
            BigDecimal totalRendido = new BigDecimal(0);
            for (PagoEconomico pago : listaPagos) {
                List<Rendicion> listaRendi = null;
                listaRendi = pago.getRendiciones();
                List<RendicionDetalle> listaRendicionDetalle = null;
                int tamanioListaRendi = listaRendi.size();
                int i = 0;
                while (i < tamanioListaRendi) {
                    listaRendicionDetalle = listaRendi.get(i).getRendicionDetalle();
                    Object[] fila = new Object[7];
                    fila[0] = listaRendi.get(i).getAnioRendido();

                    if (presu != null) {
                        List<BienUso> listaBU = presu.getBienUso();
                        List<BienNoPersonal> listaBNP = presu.getBienNoPersonal();
                        List<BienConsumo> listaBC = presu.getBienConsumo();
                        List<GastoViaje> listaGV = presu.getGastosViaje();
                        Collections.sort(listaBC);
                        Collections.sort(listaBNP);
                        Collections.sort(listaBU);
                        Collections.sort(listaGV);

                        while (i < tamanioListaRendi) {
                            BigDecimal totalBCo = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                            BigDecimal totalSNP = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                            BigDecimal totalGV = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                            BigDecimal totalBCa = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
                            for (RendicionDetalle rendiDetalle : listaRendicionDetalle) {
                                if (rendiDetalle.getTipoBien().equals("Bien de Consumo")) {
                                    totalBCo = totalBCo.add(rendiDetalle.getPrecioUnitario());
                                }
                                if (rendiDetalle.getTipoBien().equals("Servicio No Personal")) {
                                    totalSNP = totalSNP.add(rendiDetalle.getPrecioUnitario());
                                }
                                if (rendiDetalle.getTipoBien().equals("Gastos de Viaje")) {
                                    totalGV = totalGV.add(rendiDetalle.getPrecioUnitario());
                                }
                                if (rendiDetalle.getTipoBien().equals("Bien de Capital")) {
                                    totalBCa = totalBCa.add(rendiDetalle.getPrecioUnitario());
                                }
                            }

                            totalBCo = totalBCo.subtract(listaBC.get(i).getValorA());
                            totalSNP = totalSNP.subtract(listaBNP.get(i).getValorA());
                            totalGV = totalGV.subtract(listaGV.get(i).getValorA());
                            totalBCa = totalBCa.subtract(listaBU.get(i).getValorA());
                            fila[1] = totalBCo;
                            fila[2] = totalSNP;
                            fila[3] = totalGV;
                            fila[4] = totalBCa;
                            fila[5] = totalBCa.add(totalGV.add(totalSNP.add(totalBCo)));
                            modeloTablaDesvios.addRow(fila);
                            break;
                        }
                    }
                    i++;
                }
            }
        }
    }

    private void limpiarTablaDesvios() {
        DefaultTableModel modeloTablaLimpia = (DefaultTableModel) tblDatosDesvios.getModel();
        modeloTablaLimpia.getDataVector().clear(); //limpias el contenido
        tblDatosDesvios.setModel(modeloTablaLimpia);//asignas el nuevo modelo de la tabla
        tblDatosDesvios.updateUI();//actualizas la tabla
    }
    // FIN DESVIOSS        

    

}
