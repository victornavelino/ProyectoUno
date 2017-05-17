/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.proyectos.vinculacion.financiacion;

import entidades.proyecto.vinculacion.FondosySaldos;
import entidades.proyecto.vinculacion.Movimiento;
import entidades.proyecto.vinculacion.NotaExterna;
import entidades.proyecto.vinculacion.NotaInterna;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionDetem;
import entidades.proyecto.vinculacion.financiacion.pfip.*;
import entidades.proyecto.vinculacion.financiacion.picto.ImportarExcel;
import entidades.usuario.Usuario;
import includes.Comunes;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.jasperreports.engine.JRException;
import reportes.Anexo1;
import reportes.Anexo2;
import reportes.Desembolso;
import vista.proyectos.vinculacion.financiacion.pfip.*;
import vista.proyectos.vinculacion.seguimiento.diagNotaExterna;
import vista.proyectos.vinculacion.seguimiento.diagNotaInterna;

/**
 *
 * @author Panchi
 */
public class diagDetem extends javax.swing.JDialog {

    private String tipoOperacion;
    private Usuario usuario;
    private List<Etapa> etapas = new ArrayList<>();
    private List<BienDeCapital> bienesdisponibles = new ArrayList<BienDeCapital>();
    private List<BienDeCapitalAAdquirir> bienesaadquirir = new ArrayList<BienDeCapitalAAdquirir>();
    private List<RecursoHumanoDisponible> recursosdisponibles = new ArrayList<RecursoHumanoDisponible>();
    private List<RecursoHumanoAdquirir> recursosadquirir = new ArrayList<RecursoHumanoAdquirir>();
    private List<Consultoria> consultorias = new ArrayList<Consultoria>();
    private List<Material> materiales = new ArrayList<Material>();
    private List<OtroRecursoDisponible> otrosrecursosdisponibles = new ArrayList<OtroRecursoDisponible>();
    private List<OtroRecursoAdquirir> otrosrecursosadquirir = new ArrayList<>();
    private List<CronogramaDePago> cronogramas = new ArrayList<>();
    private DefaultTableModel modeloBienesDisponibles;
    private DefaultTableModel modeloBienesAdquirir;
    private DefaultTableModel modeloRecursosDisponibles;
    private DefaultTableModel modeloRecursosAdquirir;
    private DefaultTableModel modeloConsultorias;
    private DefaultTableModel modeloMateriales;
    private DefaultTableModel modeloOtrosRecursosDisponibles;
    private DefaultTableModel modeloOtrosRecursosAdquirir;
    private DefaultTableModel modeloCronograma;
    private DefaultTableModel modeloTotalMincyt;
    private DefaultTableModel modeloTotalContraparte;
    private DefaultTableModel modeloNotasExternas;
    private DefaultTableModel modeloNotasInternas;
    private FinanciacionDetem detem;
    private ProyectoVinculacion proyectoVinculacion;
    private List<NotaExterna> notasExternas = new ArrayList<>();
    private List<NotaInterna> notasInternas = new ArrayList<>();
    private String patron = "dd/MM/yyyy";
    private SimpleDateFormat formato = new SimpleDateFormat(patron);
    private String rutaArchivo;
    private RendicionPfipDetem rendiciondecuentas;
    private List<RendicionPfipDetem> listaCuentas =new ArrayList();
    private DefaultTableModel modelorendicion;
    private DefaultTableModel modelodetalle;
    private Movimiento op;
    private FondosySaldos fys;
    private DefaultTableModel tablaMovimientos;
    private List<FondosySaldos> fondos=new ArrayList();

    public diagDetem(java.awt.Frame parent, boolean modal, String tipoOperacion,
            Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;

        this.usuario = usuario;
        initComponents();
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

        inicializarComponentes();

    }
//modificacion

    public diagDetem(java.awt.Frame parent, boolean modal, String tipoOperacion,
            FinanciacionDetem financiacionDetem, Usuario usuario, ProyectoVinculacion proyectoVinculacion) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.detem = financiacionDetem;
        this.proyectoVinculacion = proyectoVinculacion;
        this.usuario = usuario;


        initComponents();
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

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

        jButton2 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jtfTipo = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTableBienesDisponibles = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTableBienesAAdquirir = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jtfbdcdC = new javax.swing.JTextField();
        jtfbdcfc = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jtfbdcfm = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jtfbdctotal = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableRecursosDisponibles = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTableRecursosAdqurir = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jtfrhdc = new javax.swing.JTextField();
        jtfrhfc = new javax.swing.JTextField();
        jtfrhfm = new javax.swing.JTextField();
        jtfrhtotal = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTableConsultorias = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtfConsultoriasC = new javax.swing.JTextField();
        jtfConsultoriasM = new javax.swing.JTextField();
        jtfConsultoriasTotal = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMateriales = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfMaterialesC = new javax.swing.JTextField();
        jtfMaterialesM = new javax.swing.JTextField();
        jtfMaterialesTotal = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOtrosRecursosDisponibles = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jtfRecDC = new javax.swing.JTextField();
        jtfRecFC = new javax.swing.JTextField();
        jtfRecFM = new javax.swing.JTextField();
        jtfRecTotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableOtrosRecursosAdquirir = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jListEtapas = new javax.swing.JList();
        jLabel26 = new javax.swing.JLabel();
        jtfDuracionTotalEtapa = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbTotalesFM = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtbTotalesFC = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCronograma = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtftotaldesembolsos = new javax.swing.JTextField();
        jtftotalcostos = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableNotasExternas = new javax.swing.JTable();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jBtnReporteNe = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableNotasInternas = new javax.swing.JTable();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jBtnReporteNi = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableRendicion = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jListRendicion = new javax.swing.JList();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTableDetalleRendicion = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jBtnBuscarRendicion = new javax.swing.JButton();
        jBtnAceptarRendicion = new javax.swing.JButton();
        jBtnCancelarRendicion = new javax.swing.JButton();
        jTfRendicionCuenta = new javax.swing.JTextField();
        jBtnEliminarRendicion = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jBtnBuscarArchivo = new javax.swing.JButton();
        jBtnAceptarArchivo = new javax.swing.JButton();
        jBtnCancelarArchivo = new javax.swing.JButton();
        jTfrutaArchivo = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTableMovimientos = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jListFondos = new javax.swing.JList();
        jLabel11 = new javax.swing.JLabel();
        jBtnEliminarFondo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton2.text_5")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane9.setPreferredSize(new java.awt.Dimension(1366, 600));

        jPanel10.setPreferredSize(new java.awt.Dimension(1213, 867));

        jLabel9.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel9.text_5")); // NOI18N

        jtfTipo.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfTipo.text_5")); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1748, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(4340, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel10.TabConstraints.tabTitle_5"), jPanel10); // NOI18N

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel26.border.title_5"))); // NOI18N

        jTableBienesDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descripcion", "Valor"
            }
        ));
        jScrollPane21.setViewportView(jTableBienesDisponibles);

        jButton18.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton18.text_5")); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton4.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton4.text_1_3")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18)
                    .addComponent(jButton4))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel27.border.title_5"))); // NOI18N

        jTableBienesAAdquirir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Descripcion", "Entidad a la que se destina", "Costo total", "A financiar por MINCYT", "A financiar por Contraparte"
            }
        ));
        jScrollPane22.setViewportView(jTableBienesAAdquirir);
        if (jTableBienesAAdquirir.getColumnModel().getColumnCount() > 0) {
            jTableBienesAAdquirir.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableBienesAAdquirir.columnModel.title0_4")); // NOI18N
            jTableBienesAAdquirir.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableBienesAAdquirir.columnModel.title1_4")); // NOI18N
            jTableBienesAAdquirir.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableBienesAAdquirir.columnModel.title2_4")); // NOI18N
            jTableBienesAAdquirir.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableBienesAAdquirir.columnModel.title3_4")); // NOI18N
            jTableBienesAAdquirir.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableBienesAAdquirir.columnModel.title4_4")); // NOI18N
        }

        jButton19.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton19.text_5")); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel38.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel38.text_5")); // NOI18N

        jtfbdcdC.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfbdcdC.text_5")); // NOI18N

        jtfbdcfc.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfbdcfc.text_5")); // NOI18N

        jLabel39.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel39.text_5")); // NOI18N

        jtfbdcfm.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfbdcfm.text_5")); // NOI18N

        jLabel40.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel40.text_5")); // NOI18N

        jtfbdctotal.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfbdctotal.text_5")); // NOI18N

        jLabel41.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel41.text_5")); // NOI18N

        jButton5.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton5.text_1_3")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton19)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jLabel38))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfbdctotal, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jtfbdcfm, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jtfbdcfc, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jtfbdcdC))
                        .addGap(0, 462, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton19)
                        .addGap(8, 8, 8)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfbdcdC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jtfbdcfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jtfbdcfm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jtfbdctotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1137, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3991, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bienes de Capital", jPanel25);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel15.border.title_5"))); // NOI18N

        jTableRecursosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(jTableRecursosDisponibles);

        jButton11.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton11.text_5")); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton6.text_1_3")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel16.border.title_5"))); // NOI18N

        jTableRecursosAdqurir.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane14.setViewportView(jTableRecursosAdqurir);

        jButton12.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton12.text_5")); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton16.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton16.text_2")); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel17.border.title_5"))); // NOI18N

        jLabel12.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel12.text_4")); // NOI18N

        jLabel13.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel13.text_4")); // NOI18N

        jLabel14.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel14.text_4")); // NOI18N

        jLabel15.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel15.text_4")); // NOI18N

        jtfrhdc.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfrhdc.text_4")); // NOI18N

        jtfrhfc.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfrhfc.text_4")); // NOI18N

        jtfrhfm.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfrhfm.text_4")); // NOI18N

        jtfrhtotal.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfrhtotal.text_4")); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfrhdc)
                    .addComponent(jtfrhfc)
                    .addComponent(jtfrhfm)
                    .addComponent(jtfrhtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(488, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtfrhdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtfrhfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtfrhfm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jtfrhtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1138, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(4023, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Recursos Humanos", jPanel14);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel19.border.title_5"))); // NOI18N

        jTableConsultorias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(jTableConsultorias);

        jLabel16.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel16.text_5")); // NOI18N

        jLabel17.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel17.text_5")); // NOI18N

        jLabel18.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel18.text_5")); // NOI18N

        jtfConsultoriasC.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfConsultoriasC.text_5")); // NOI18N

        jtfConsultoriasM.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfConsultoriasM.text_5")); // NOI18N

        jtfConsultoriasTotal.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfConsultoriasTotal.text_5")); // NOI18N

        jButton13.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton13.text_5")); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton21.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton21.text_2")); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfConsultoriasM, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfConsultoriasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfConsultoriasC, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jtfConsultoriasC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jtfConsultoriasM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jtfConsultoriasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1113, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4215, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultorias y Servicios", jPanel18);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel1.border.title_5"))); // NOI18N

        jTableMateriales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableMateriales);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel1.text_5")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel2.text_5")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel3.text_5")); // NOI18N

        jtfMaterialesC.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfMaterialesC.text_5")); // NOI18N

        jtfMaterialesM.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfMaterialesM.text_5")); // NOI18N

        jtfMaterialesTotal.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfMaterialesTotal.text_5")); // NOI18N

        jButton14.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton14.text_5")); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton22.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton22.text_2")); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfMaterialesTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jtfMaterialesC)
                            .addComponent(jtfMaterialesM)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfMaterialesC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfMaterialesM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfMaterialesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1140, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4227, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel20.TabConstraints.tabTitle_5"), jPanel20); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel3.border.title_5"))); // NOI18N

        jTableOtrosRecursosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableOtrosRecursosDisponibles);

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton1.text_5")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton23.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton23.text_2")); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel21.border.title_5"))); // NOI18N

        jLabel19.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel19.text_5")); // NOI18N

        jLabel20.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel20.text_5")); // NOI18N

        jLabel21.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel21.text_5")); // NOI18N

        jLabel22.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel22.text_5")); // NOI18N

        jtfRecDC.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfRecDC.text_5")); // NOI18N

        jtfRecFC.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfRecFC.text_5")); // NOI18N

        jtfRecFM.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfRecFM.text_5")); // NOI18N

        jtfRecTotal.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfRecTotal.text_5")); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfRecDC)
                    .addComponent(jtfRecFC)
                    .addComponent(jtfRecFM)
                    .addComponent(jtfRecTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(464, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jtfRecDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jtfRecFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jtfRecFM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jtfRecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel4.border.title_5"))); // NOI18N

        jTableOtrosRecursosAdquirir.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableOtrosRecursosAdquirir);

        jButton3.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton3.text_5")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton24.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton24.text_2")); // NOI18N
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1149, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3973, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel2.TabConstraints.tabTitle_5"), jPanel2); // NOI18N

        jLabel25.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel25.text_5")); // NOI18N

        jScrollPane11.setViewportView(jListEtapas);

        jLabel26.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel26.text_5")); // NOI18N

        jtfDuracionTotalEtapa.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtfDuracionTotalEtapa.text_5")); // NOI18N

        jButton9.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton9.text_5")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton10.text_5")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton15.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton15.text_5")); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(jtfDuracionTotalEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1499, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jtfDuracionTotalEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(4179, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Etapas", jPanel13);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel5.border.title_5"))); // NOI18N

        jtbTotalesFM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jtbTotalesFM);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel11.border.title_5"))); // NOI18N

        jtbTotalesFC.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jtbTotalesFC);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1140, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4174, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel6.TabConstraints.tabTitle_5"), jPanel6); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel12.border.title_5"))); // NOI18N

        jTableCronograma.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTableCronograma);
        if (jTableCronograma.getColumnModel().getColumnCount() > 0) {
            jTableCronograma.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableCronograma.columnModel.title0_4")); // NOI18N
            jTableCronograma.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableCronograma.columnModel.title1_4")); // NOI18N
            jTableCronograma.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableCronograma.columnModel.title2_4")); // NOI18N
            jTableCronograma.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTableCronograma.columnModel.title3_4")); // NOI18N
        }

        jButton7.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton7.text_5")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel6.text_5")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel7.text_5")); // NOI18N

        jtftotaldesembolsos.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtftotaldesembolsos.text_5")); // NOI18N

        jtftotalcostos.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jtftotalcostos.text_5")); // NOI18N

        jButton25.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton25.text_2")); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtftotaldesembolsos)
                            .addComponent(jtftotalcostos, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtftotaldesembolsos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtftotalcostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1138, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4226, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel7.TabConstraints.tabTitle_5"), jPanel7); // NOI18N

        jPanel8.setMaximumSize(new java.awt.Dimension(500, 3000));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 1822));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel22.border.title_5"))); // NOI18N

        jButton8.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton8.text_5")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel8.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel8.text_5")); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addGap(73, 73, 73)
                .addComponent(jButton8)
                .addGap(90, 90, 90))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton8))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel23.border.title_5"))); // NOI18N

        jButton17.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton17.text_5")); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel23.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel23.text_5")); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addGap(86, 86, 86))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jButton17))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel24.border.title_5"))); // NOI18N

        jButton20.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton20.text_4")); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel24.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel24.text_5")); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(jButton20)
                .addGap(87, 87, 87))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jButton20))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1492, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(4235, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel8.TabConstraints.tabTitle_5"), jPanel8); // NOI18N

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel28.border.title_2"))); // NOI18N

        jTableNotasExternas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTableNotasExternas);

        jButton26.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton26.text_2")); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton27.text_2")); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jBtnReporteNe.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnReporteNe.text")); // NOI18N
        jBtnReporteNe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReporteNeActionPerformed(evt);
            }
        });

        jButton30.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton30.text")); // NOI18N
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addGap(22, 22, 22)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnReporteNe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 81, Short.MAX_VALUE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jButton26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnReporteNe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel29.border.title_2"))); // NOI18N

        jTableNotasInternas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTableNotasInternas);

        jButton28.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton28.text_2")); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton29.text_2")); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jBtnReporteNi.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnReporteNi.text")); // NOI18N
        jBtnReporteNi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReporteNiActionPerformed(evt);
            }
        });

        jButton31.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jButton31.text")); // NOI18N
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnReporteNi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jButton28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnReporteNi))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, Short.MAX_VALUE))
                .addContainerGap(989, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4050, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel9.TabConstraints.tabTitle_1"), jPanel9); // NOI18N

        jTableRendicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRendicionMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTableRendicion);

        jListRendicion.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListRendicionValueChanged(evt);
            }
        });
        jScrollPane12.setViewportView(jListRendicion);

        jScrollPane16.setViewportView(jTableDetalleRendicion);

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel4.text_1")); // NOI18N

        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnBuscarRendicion.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnBuscarRendicion.text")); // NOI18N
        jBtnBuscarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarRendicionActionPerformed(evt);
            }
        });

        jBtnAceptarRendicion.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnAceptarRendicion.text")); // NOI18N
        jBtnAceptarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAceptarRendicionActionPerformed(evt);
            }
        });

        jBtnCancelarRendicion.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnCancelarRendicion.text")); // NOI18N
        jBtnCancelarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarRendicionActionPerformed(evt);
            }
        });

        jTfRendicionCuenta.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTfRendicionCuenta.text")); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBtnAceptarRendicion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCancelarRendicion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnBuscarRendicion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTfRendicionCuenta)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnBuscarRendicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAceptarRendicion)
                    .addComponent(jTfRendicionCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnCancelarRendicion)
                .addContainerGap())
        );

        jBtnEliminarRendicion.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnEliminarRendicion.text")); // NOI18N
        jBtnEliminarRendicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarRendicionActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel5.text_1")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel10.text_2")); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnEliminarRendicion)
                        .addGap(72, 72, 72)))
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(497, 497, 497))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnEliminarRendicion)
                                .addGap(54, 54, 54))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jLabel10)
                        .addGap(3873, 3873, 3873)
                        .addComponent(jLabel5)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel30.TabConstraints.tabTitle"), jPanel30); // NOI18N

        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnBuscarArchivo.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnBuscarArchivo.text")); // NOI18N
        jBtnBuscarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarArchivoActionPerformed(evt);
            }
        });

        jBtnAceptarArchivo.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnAceptarArchivo.text")); // NOI18N
        jBtnAceptarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAceptarArchivoActionPerformed(evt);
            }
        });

        jBtnCancelarArchivo.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnCancelarArchivo.text")); // NOI18N
        jBtnCancelarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarArchivoActionPerformed(evt);
            }
        });

        jTfrutaArchivo.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jTfrutaArchivo.text")); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBtnCancelarArchivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnAceptarArchivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnBuscarArchivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTfrutaArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnBuscarArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAceptarArchivo)
                    .addComponent(jTfrutaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnCancelarArchivo)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane17.setViewportView(jTableMovimientos);

        jListFondos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListFondosValueChanged(evt);
            }
        });
        jScrollPane18.setViewportView(jListFondos);

        jLabel11.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jLabel11.text_2")); // NOI18N

        jBtnEliminarFondo.setText(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jBtnEliminarFondo.text")); // NOI18N
        jBtnEliminarFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarFondoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jBtnEliminarFondo)))
                .addContainerGap(1198, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEliminarFondo)))
                .addContainerGap(3934, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagDetem.class, "diagDetem.jPanel32.TabConstraints.tabTitle"), jPanel32); // NOI18N

        jScrollPane9.setViewportView(jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(83, 83, 83))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        agregarEtapa();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        agregarBienDeCapitalDisponible();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        agregarBienDeCapitalAAdquirir();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        agregarRecursoHumanoDisponible();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        agregarRecursosHumanoAdquirir();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        agregarConsultoria();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        agregarMaterial();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarOtrosRecursosDisponibles(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        agregarOtrosRecursosAdquirir();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        agregarCronograma();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aceptar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        generarReporteConvenio1();


    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        generarReporteCronogramaDesembolsos();

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        detallesEtapa();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        generarReporteConvenio2();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        eliminarBienDeCapitalDisponible();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        eliminarRecursoHumanoDisponible();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        eliminarBienDeCapitalAAdquirir();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        eliminarRecursoHumanoAAdquirir();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        eliminarConsultoria();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        eliminarMateriales();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        eliminarOtrosCostosDisponible();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        eliminarOtrosCostosAAdquirir();// TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        eliminarCronograma();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        agregarNotaExterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        eliminarNotaExterna();            // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        agregarNotaInterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        eliminarNotaInterna();// TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        quitarEtapa();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jBtnReporteNeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReporteNeActionPerformed
        if(!notasExternas.isEmpty()){
            reporteSeguimientoNe();
        }
    }//GEN-LAST:event_jBtnReporteNeActionPerformed

    private void jBtnReporteNiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReporteNiActionPerformed
        if(!notasInternas.isEmpty()){
            reporteSeguimientoNi();
        }
    }//GEN-LAST:event_jBtnReporteNiActionPerformed

    private void jBtnBuscarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarRendicionActionPerformed
        buscarArchivoRendicion();
    }//GEN-LAST:event_jBtnBuscarRendicionActionPerformed

    private void jBtnAceptarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAceptarRendicionActionPerformed
        if(rutaArchivo!=null){
            ImportarRendicion.getInstance().importar(rutaArchivo);
            rendiciondecuentas=ImportarRendicion.getInstance().getRendicionPfipDetem();
            if(rendiciondecuentas!=null){
                listaCuentas.add(rendiciondecuentas);
                cargarRendiciones();
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe buscar el archivo a importar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnAceptarRendicionActionPerformed

    private void jBtnCancelarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarRendicionActionPerformed
        jTfRendicionCuenta.setText("");
        rutaArchivo="";
    }//GEN-LAST:event_jBtnCancelarRendicionActionPerformed

    private void jBtnEliminarRendicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarRendicionActionPerformed
        eliminarRendicion();
    }//GEN-LAST:event_jBtnEliminarRendicionActionPerformed

    private void jListRendicionValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListRendicionValueChanged
        mostrarRendiciones();
    }//GEN-LAST:event_jListRendicionValueChanged

    private void jTableRendicionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRendicionMouseClicked
        mostrarDetalles();
    }//GEN-LAST:event_jTableRendicionMouseClicked

    private void jBtnBuscarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarArchivoActionPerformed
        buscarArchivo();
    }//GEN-LAST:event_jBtnBuscarArchivoActionPerformed

    private void jBtnAceptarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAceptarArchivoActionPerformed
        if(rutaArchivo!=null){
        Importar();
        cargarFondos();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe buscar el archivo a importar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnAceptarArchivoActionPerformed

    private void jBtnCancelarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarArchivoActionPerformed
        jTfrutaArchivo.setText("");
        rutaArchivo="";
    }//GEN-LAST:event_jBtnCancelarArchivoActionPerformed

    private void jListFondosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListFondosValueChanged
        mostrarMovimientos();
    }//GEN-LAST:event_jListFondosValueChanged

    private void jBtnEliminarFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarFondoActionPerformed
        eliminarFondos();
    }//GEN-LAST:event_jBtnEliminarFondoActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        modificarNotaExterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        modificarNotaInterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(diagDetem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagDetem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagDetem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagDetem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                diagDetem dialog = new diagDetem(new javax.swing.JFrame(), true, new String(), new Usuario());
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
    private javax.swing.JButton jBtnAceptarArchivo;
    private javax.swing.JButton jBtnAceptarRendicion;
    private javax.swing.JButton jBtnBuscarArchivo;
    private javax.swing.JButton jBtnBuscarRendicion;
    private javax.swing.JButton jBtnCancelarArchivo;
    private javax.swing.JButton jBtnCancelarRendicion;
    private javax.swing.JButton jBtnEliminarFondo;
    private javax.swing.JButton jBtnEliminarRendicion;
    private javax.swing.JButton jBtnReporteNe;
    private javax.swing.JButton jBtnReporteNi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListEtapas;
    private javax.swing.JList jListFondos;
    private javax.swing.JList jListRendicion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableBienesAAdquirir;
    private javax.swing.JTable jTableBienesDisponibles;
    private javax.swing.JTable jTableConsultorias;
    private javax.swing.JTable jTableCronograma;
    private javax.swing.JTable jTableDetalleRendicion;
    private javax.swing.JTable jTableMateriales;
    private javax.swing.JTable jTableMovimientos;
    private javax.swing.JTable jTableNotasExternas;
    private javax.swing.JTable jTableNotasInternas;
    private javax.swing.JTable jTableOtrosRecursosAdquirir;
    private javax.swing.JTable jTableOtrosRecursosDisponibles;
    private javax.swing.JTable jTableRecursosAdqurir;
    private javax.swing.JTable jTableRecursosDisponibles;
    private javax.swing.JTable jTableRendicion;
    private javax.swing.JTextField jTfRendicionCuenta;
    private javax.swing.JTextField jTfrutaArchivo;
    private javax.swing.JTable jtbTotalesFC;
    private javax.swing.JTable jtbTotalesFM;
    private javax.swing.JTextField jtfConsultoriasC;
    private javax.swing.JTextField jtfConsultoriasM;
    private javax.swing.JTextField jtfConsultoriasTotal;
    private javax.swing.JTextField jtfDuracionTotalEtapa;
    private javax.swing.JTextField jtfMaterialesC;
    private javax.swing.JTextField jtfMaterialesM;
    private javax.swing.JTextField jtfMaterialesTotal;
    private javax.swing.JTextField jtfRecDC;
    private javax.swing.JTextField jtfRecFC;
    private javax.swing.JTextField jtfRecFM;
    private javax.swing.JTextField jtfRecTotal;
    private javax.swing.JTextField jtfTipo;
    private javax.swing.JTextField jtfbdcdC;
    private javax.swing.JTextField jtfbdcfc;
    private javax.swing.JTextField jtfbdcfm;
    private javax.swing.JTextField jtfbdctotal;
    private javax.swing.JTextField jtfrhdc;
    private javax.swing.JTextField jtfrhfc;
    private javax.swing.JTextField jtfrhfm;
    private javax.swing.JTextField jtfrhtotal;
    private javax.swing.JTextField jtftotalcostos;
    private javax.swing.JTextField jtftotaldesembolsos;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        //  this.setSize(screenSize.width, screenSize.height);  
      try{
        cargarTipoFinanciacion();
        cargarBienesDisponibles();
        cargarBienesAdquirir();
        cargarConsultorias();
        cargarCronogramas();
        cargarMateriales();
        cargarOtrosRecursosAdquirir();
        cargarOtrosRecursosDisponibles();
        cargarRecursosAdquirir();
        cargarRecursosDisponibles();
        cargarDistribucionC();
        cargarDistribucionM();
        cargarNotasExternas();
        cargarNotasInternas();
        cargarRendiciones();
        cargarFondos();
        jTabbedPane2.setEnabledAt(10,false);

        switch (tipoOperacion) {
            case "Alta":
                setearTextField();
                break;
            case "Modificacion":
                setearTextField();
                try {
                    if (detem.getEtapas() != null) {
                   
                        etapas = detem.getEtapas();
                        cargarEtapas();
                        cargarCostosTotales();
                    }
                } catch (NullPointerException ex) {
                }
//                if (detem.getEtapas() != null) {
//                    etapas = detem.getEtapas();
//                    cargarEtapas();
//                    cargarCostosTotales();
//                }

                if (detem.getBienesdecapital() != null) {
                    bienesdisponibles = detem.getBienesdecapital();
                    cargarBienesDisponibles();
                    cargarBienDeCapitalDisponibleTotal();
                    cambiartotalBienes();

                }
                if (detem.getBienesdecapitaladquirir() != null) {
                    bienesaadquirir = detem.getBienesdecapitaladquirir();
                    cargarBienesAdquirir();
                    cargarBienDeCapitalAFinanciarContraparteTotal();
                    cargarBienDeCapitalAFinanciarMincytTotal();

                    cambiartotalBienes();
                }
                if (detem.getRecursosHumanosDisponibles() != null) {
                    recursosdisponibles = detem.getRecursosHumanosDisponibles();
                    cargarRecursosDisponibles();
                    cargarRHDisponibleContraparteTotal();
                    cambiarTotalRecursos();


                }
                if (detem.getRecursosHumanosAdquirir() != null) {
                    recursosadquirir = detem.getRecursosHumanosAdquirir();
                    cargarRecursosAdquirir();
                    cargarRHAFinanciarContraparteTotal();
                    cargarRHAFinanciarMincytTotal();

                    cambiarTotalRecursos();
                }
                if (detem.getConsultorias() != null) {
                    consultorias = detem.getConsultorias();
                    cargarConsultorias();
                    cargarConsultoriasC();
                    cargarConsultoriasM();
                    cargarConsultoriasTotal();

                }
                if (detem.getMateriales() != null) {
                    materiales = detem.getMateriales();
                    cargarMateriales();
                    cargarMaterialesC();
                    cargarMaterialesM();
                    cargarMaterialesTotal();
                }
                if (detem.getOtrosRecursoDisponibles() != null) {
                    otrosrecursosdisponibles = detem.getOtrosRecursoDisponibles();
                    cargarOtrosRecursosDisponibles();
                    cargarRecursosDisponiblesC();
                    cambiarOtrosRecursosTotal();
                }
                if (detem.getOtroRecursoAdquirir() != null) {
                    otrosrecursosadquirir = detem.getOtroRecursoAdquirir();
                    cargarOtrosRecursosAdquirir();
                    cargarRecursosAdquirirC();
                    cargarRecursosAdquirirM();
                    cambiarOtrosRecursosTotal();
                }
                if (detem.getCronogramasdepagos() != null) {
                    cronogramas = detem.getCronogramasdepagos();
                    cargarCronogramas();
                    cargarDesembolsoTotal();
                    cargarCostoTotal();

                }
                if (detem.getNotasExternas() != null) {
                    notasExternas = detem.getNotasExternas();
                    cargarNotasExternas();
                }
                if (detem.getNotasInternas() != null) {
                    notasInternas = detem.getNotasInternas();
                    cargarNotasInternas();
                }
                if(detem.getRendicioncuenta()!= null){
                    listaCuentas= detem.getRendicioncuenta();
                    
                }
                if(detem.getRetiros() !=null  ){
                    fondos=detem.getRetiros();
                }

                break;
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    }

    private void cargarEtapas() {
        Comunes.cargarJList(jListEtapas, etapas);
        cargarDuracionTotal();
    }

    private void agregarEtapa() {
        diagEtapa campoEtapa = new diagEtapa(null, true, etapas, "Alta");
        campoEtapa.setLocation(Comunes.centrarDialog(campoEtapa));
        campoEtapa.setVisible(true);
        if (campoEtapa.getCampoEtapa() != null) {
            etapas.add(campoEtapa.getCampoEtapa());
            cargarEtapas();
            cargarCostosTotales();
        }
    }

    private void cargarBienesDisponibles() {
        Object[] fila = new Object[2];
        cargarTablaEncabezadosBienesDisponibles();
        if (!bienesdisponibles.isEmpty()) {

            for (int i = 0; i < bienesdisponibles.size(); i++) {


                fila[0] = bienesdisponibles.get(i).getDescripcion();
                fila[1] = bienesdisponibles.get(i).getValor();
                modeloBienesDisponibles.addRow(fila);
            }
        }
    }

    private void cargarBienesAdquirir() {
        Object[] fila = new Object[5];
        cargarTablaEncabezadosBienesAdquirir();
        if (!bienesaadquirir.isEmpty()) {

            for (int i = 0; i < bienesaadquirir.size(); i++) {


                fila[0] = bienesaadquirir.get(i).getDescripcion();
                fila[1] = bienesaadquirir.get(i).getDestino();
                fila[2] = bienesaadquirir.get(i).getCosto();
                fila[3] = bienesaadquirir.get(i).getParteMincyt();
                fila[4] = bienesaadquirir.get(i).getParteContraparte();

                modeloBienesAdquirir.addRow(fila);
            }
        }
    }

    private void cargarRecursosDisponibles() {
        Object[] fila = new Object[7];
        cargarTablaEncabezadosRecursosDisponibles();
        if (!recursosdisponibles.isEmpty()) {

            for (int i = 0; i < recursosdisponibles.size(); i++) {

                fila[0] = recursosdisponibles.get(i).getNombreApellido();

                fila[1] = recursosdisponibles.get(i).getEspecialidad();
                fila[2] = recursosdisponibles.get(i).getFuncion();
                fila[3] = recursosdisponibles.get(i).getSueldo();
                fila[4] = recursosdisponibles.get(i).getDedicacion();
                fila[5] = recursosdisponibles.get(i).getMesesParticipacion();
                fila[6] = recursosdisponibles.get(i).getFondosDisponibles();


                modeloRecursosDisponibles.addRow(fila);
            }
        }
    }

    private void cargarRecursosAdquirir() {

        Object[] fila = new Object[8];

        cargarTablaEncabezadosRecursosAdquirir();
        if (!recursosadquirir.isEmpty()) {

            for (int i = 0; i < recursosadquirir.size(); i++) {


                fila[0] = recursosadquirir.get(i).getEspecialidad();
                fila[1] = recursosadquirir.get(i).getFuncion();
                fila[2] = recursosadquirir.get(i).getCostoTotalMensual();

                fila[3] = recursosadquirir.get(i).getMesesParticipacion();
                fila[4] = recursosadquirir.get(i).getDedicacion();
                fila[5] = recursosadquirir.get(i).getCostoTotal();

                fila[6] = recursosadquirir.get(i).getFinanciaM();
                fila[7] = recursosadquirir.get(i).getFinanciaC();
                modeloRecursosAdquirir.addRow(fila);
            }
        }
    }

    private void cargarConsultorias() {
        Object[] fila = new Object[6];

        cargarTablaEncabezadosConsultorias();
        if (!consultorias.isEmpty()) {
            for (int i = 0; i < consultorias.size(); i++) {
                fila[0] = consultorias.get(i).getDescripcion();
                fila[1] = formato.format(consultorias.get(i).getFechaInicio());
                fila[2] = formato.format(consultorias.get(i).getFechaFin());
                fila[3] = consultorias.get(i).getCostoTotal();
                fila[4] = consultorias.get(i).getFinanciaM();
                fila[5] = consultorias.get(i).getFinanciaC();
                modeloConsultorias.addRow(fila);
            }
        }
    }

    private void cargarMateriales() {
        Object[] fila = new Object[6];

        cargarTablaEncabezadosMateriales();
        if (!materiales.isEmpty()) {

            for (int i = 0; i < materiales.size(); i++) {
                fila[0] = materiales.get(i).getDetalle();
                fila[1] = materiales.get(i).getCantidad();
                fila[2] = materiales.get(i).getCostoUnitario();
                fila[3] = materiales.get(i).getCostoTotal();
                fila[4] = materiales.get(i).getFinanciaM();
                fila[5] = materiales.get(i).getFinanciaC();
                modeloMateriales.addRow(fila);
            }
        }
    }

    private void cargarOtrosRecursosDisponibles() {
        Object[] fila = new Object[2];
        cargarTablaEncabezadosOtrosRecursosDisponibles();
        if (!otrosrecursosdisponibles.isEmpty()) {

            for (int i = 0; i < otrosrecursosdisponibles.size(); i++) {



                fila[0] = otrosrecursosdisponibles.get(i).getDescripcion();
                fila[1] = otrosrecursosdisponibles.get(i).getValor();
                modeloOtrosRecursosDisponibles.addRow(fila);
            }
        }
    }

    private void cargarOtrosRecursosAdquirir() {
        Object[] fila = new Object[4];
        cargarTablaEncabezadosOtrosRecursosAdquirir();
        if (!otrosrecursosadquirir.isEmpty()) {
            for (int i = 0; i < otrosrecursosadquirir.size(); i++) {
                fila[0] = otrosrecursosadquirir.get(i).getDescripcion();
                fila[1] = otrosrecursosadquirir.get(i).getPrecioTotal();
                fila[2] = otrosrecursosadquirir.get(i).getFinanciaM();
                fila[3] = otrosrecursosadquirir.get(i).getFinanciaC();

                modeloOtrosRecursosAdquirir.addRow(fila);
            }
        }
    }

    private void cargarCronogramas() {
        Object[] fila = new Object[5];
        cargarTablaEncabezadosCronogramas();
        if (!cronogramas.isEmpty()) {
            for (int i = 0; i < cronogramas.size(); i++) {

                fila[0] = cronogramas.get(i).getEtapaDeCronograma();
                fila[1] = cronogramas.get(i).getDesembolso();
                fila[2] = cronogramas.get(i).getEtapaAEjecutar();
                fila[3] = cronogramas.get(i).getCostoEjecucion();
                fila[4] = cronogramas.get(i).getSaldo();
                //        fila[5] = cronogramas.get(i).getControl();
                modeloCronograma.addRow(fila);
            }
        }
    }

    private void cargarDistribucionM() {
        Object[] fila = new Object[7];
        cargarTablaEncabezadosDistribucionMincyt();
        if (!etapas.isEmpty()) {
            for (Etapa e : etapas) {
                fila[0] = e.getNroEtapa();
                fila[1] = e.getBienCapitalFM();
                fila[2] = e.getRecHumFM();
                fila[3] = e.getConsultoriasFM();
                fila[4] = e.getMaterialesFM();
                fila[5] = e.getOtrosRecursosFM();
                fila[6] = totalDistribucionMEtapa(e);
                modeloTotalMincyt.addRow(fila);
            }

        }
    }

    private void cargarDistribucionC() {
        Object[] fila = new Object[7];
        cargarTablaEncabezadosDistribucionContraparte();
        if (!etapas.isEmpty()) {
            for (Etapa e : etapas) {
                fila[0] = e.getNroEtapa();
                fila[1] = e.getBienCapitalFC();
                fila[2] = e.getRecHumFC();
                fila[3] = e.getConsultoriasFC();
                fila[4] = e.getMaterialesFC();
                fila[5] = e.getOtrosRecursosFC();
                fila[6] = totalDistribucionCEtapa(e);
                modeloTotalContraparte.addRow(fila);
            }

        }
    }

    private void cargarNotasExternas() {
        Object[] fila = new Object[6];
        cargarTablaEncabezadosNotasExternas();
        if (!notasExternas.isEmpty()) {
            Collections.sort(notasExternas,comparadorNe);
            for (NotaExterna n : notasExternas) {

                fila[0] = formato.format(n.getFecha());
                fila[1] = n.getNroNota();
                fila[2] = n.getMotivo();
                fila[3] = formato.format(n.getFechaDeRecibidoMesaDeEntrada());
                fila[4] = formato.format(n.getFechaAprobacion());
                fila[5] = formato.format(n.getFechaDesembolso());
                modeloNotasExternas.addRow(fila);

            }
        }

    }

    private void cargarNotasInternas() {
        Object[] fila = new Object[5];
        cargarTablaEncabezadosNotasInternas();
        if (!notasInternas.isEmpty()) {
            Collections.sort(notasInternas,comparadorNi);
            for (NotaInterna n : notasInternas) {

                fila[0] = formato.format(n.getFecha());
                fila[1] = n.getNroNota();
                fila[2] = n.getMotivo();
                fila[3] = formato.format(n.getFechaDeRecibidoMesaDeEntrada());
                fila[4] = formato.format(n.getFechaDesembolso());
                modeloNotasInternas.addRow(fila);

            }
        }
    }

    private void cargarTablaEncabezadosNotasExternas() {
        modeloNotasExternas = new DefaultTableModel();
        modeloNotasExternas.addColumn("Fecha");
        modeloNotasExternas.addColumn("Nro. de Nota");
        modeloNotasExternas.addColumn("Motivo");
        modeloNotasExternas.addColumn("Recibimiento en Mesa de Entrada");
        modeloNotasExternas.addColumn("Fecha de Aprobación");
        modeloNotasExternas.addColumn("Fecha de Desembolso");
        jTableNotasExternas.setModel(modeloNotasExternas);
    }

    private void cargarTablaEncabezadosNotasInternas() {
        modeloNotasInternas = new DefaultTableModel();
        modeloNotasInternas.addColumn("Fecha");
        modeloNotasInternas.addColumn("Nro. de Nota");
        modeloNotasInternas.addColumn("Motivo");
        modeloNotasInternas.addColumn("Recibimiento en Mesa de Entrada");
        modeloNotasInternas.addColumn("Fecha de Desembolso");
        jTableNotasInternas.setModel(modeloNotasInternas);
    }

    private void cargarTablaEncabezadosBienesDisponibles() {
        modeloBienesDisponibles = new DefaultTableModel();

        modeloBienesDisponibles.addColumn("Descripcion");
        modeloBienesDisponibles.addColumn("Valor");
        jTableBienesDisponibles.setModel(modeloBienesDisponibles);

    }

    private void cargarTablaEncabezadosBienesAdquirir() {
        modeloBienesAdquirir = new DefaultTableModel();

        modeloBienesAdquirir.addColumn("Descripcion");
        modeloBienesAdquirir.addColumn("Entidad Destino");
        modeloBienesAdquirir.addColumn("Costo Total");
        modeloBienesAdquirir.addColumn("A financiar por MINCYT");
        modeloBienesAdquirir.addColumn("A financiar por CONTRAPARTE");
        jTableBienesAAdquirir.setModel(modeloBienesAdquirir);

    }

    private void cargarTablaEncabezadosRecursosDisponibles() {
        modeloRecursosDisponibles = new DefaultTableModel();
        modeloRecursosDisponibles.addColumn("Nombre y Apellido");

        modeloRecursosDisponibles.addColumn("Especialidad");
        modeloRecursosDisponibles.addColumn("Función");
        modeloRecursosDisponibles.addColumn("Sueldo Mensual");
        modeloRecursosDisponibles.addColumn("% Dedicacion");
        modeloRecursosDisponibles.addColumn("Meses de Participación");
        modeloRecursosDisponibles.addColumn("Disponibles por CONTRAPARTE");
        jTableRecursosDisponibles.setModel(modeloRecursosDisponibles);

    }

    private void cargarTablaEncabezadosRecursosAdquirir() {
        modeloRecursosAdquirir = new DefaultTableModel();
        modeloRecursosAdquirir.addColumn("Especialidad");
        modeloRecursosAdquirir.addColumn("Función");
        modeloRecursosAdquirir.addColumn("Costo Total Mensual");

        modeloRecursosAdquirir.addColumn("Meses de Participación");
        modeloRecursosAdquirir.addColumn("% Dedicacion");
        modeloRecursosAdquirir.addColumn("Costo Total");

        modeloRecursosAdquirir.addColumn("A financiar por MINCYT");

        modeloRecursosAdquirir.addColumn("A financiar por CONTRAPARTE");
        jTableRecursosAdqurir.setModel(modeloRecursosAdquirir);

    }

    private void cargarTablaEncabezadosConsultorias() {
        modeloConsultorias = new DefaultTableModel();
        modeloConsultorias.addColumn("Descripcion");
        modeloConsultorias.addColumn("Fecha de Inicio");
        modeloConsultorias.addColumn("Fecha de Fin");
        modeloConsultorias.addColumn("Costo Total");
        modeloConsultorias.addColumn("A financiar por MINCYT");
        modeloConsultorias.addColumn("A financiar por CONTRAPARTE");
        jTableConsultorias.setModel(modeloConsultorias);
    }

    private void cargarTablaEncabezadosMateriales() {
        modeloMateriales = new DefaultTableModel();
        modeloMateriales.addColumn("Detalle");
        modeloMateriales.addColumn("Cantidad");
        modeloMateriales.addColumn("Costo Unitario");
        modeloMateriales.addColumn("Costo Total");
        modeloMateriales.addColumn("A financiar por MINCYT");
        modeloMateriales.addColumn("A financiar por CONTRAPARTE");
        jTableMateriales.setModel(modeloMateriales);
    }

    private void cargarTablaEncabezadosOtrosRecursosDisponibles() {
        modeloOtrosRecursosDisponibles = new DefaultTableModel();
        modeloOtrosRecursosDisponibles.addColumn("Descripcion");
        modeloOtrosRecursosDisponibles.addColumn("Valor");
        jTableOtrosRecursosDisponibles.setModel(modeloOtrosRecursosDisponibles);
    }

    private void cargarTablaEncabezadosOtrosRecursosAdquirir() {
        modeloOtrosRecursosAdquirir = new DefaultTableModel();
        modeloOtrosRecursosAdquirir.addColumn("Descripcion");
        modeloOtrosRecursosAdquirir.addColumn("Precio Total");

        modeloOtrosRecursosAdquirir.addColumn("A financiar por MINCYT");
        modeloOtrosRecursosAdquirir.addColumn("A financiar por CONTRAPARTE");
        jTableOtrosRecursosAdquirir.setModel(modeloOtrosRecursosAdquirir);
    }

    private void cargarTablaEncabezadosCronogramas() {
        modeloCronograma = new DefaultTableModel();
        modeloCronograma.addColumn("Etapa");

        modeloCronograma.addColumn("Cronograma de desembolsos");
        modeloCronograma.addColumn("Etapa a financiar");
        modeloCronograma.addColumn("Costo de ejecucion");
        modeloCronograma.addColumn("Saldo E/ Desembolso y Costo");
        //       modeloCronograma.addColumn("Control Financiero entre Desembolso y Costo");
        jTableCronograma.setModel(modeloCronograma);
    }

    private void cargarTablaEncabezadosDistribucionMincyt() {
        modeloTotalMincyt = new DefaultTableModel();
        modeloTotalMincyt.addColumn("Etapa");
        modeloTotalMincyt.addColumn("Bienes de Capital");
        modeloTotalMincyt.addColumn("Recursos Humanos");
        modeloTotalMincyt.addColumn("Consultorias");
        modeloTotalMincyt.addColumn("Materiales");
        modeloTotalMincyt.addColumn("Otros Recursos");
        modeloTotalMincyt.addColumn("Total");
        jtbTotalesFM.setModel(modeloTotalMincyt);

    }

    private void cargarTablaEncabezadosDistribucionContraparte() {
        modeloTotalContraparte = new DefaultTableModel();
        modeloTotalContraparte.addColumn("Etapa");
        modeloTotalContraparte.addColumn("Bienes de Capital");
        modeloTotalContraparte.addColumn("Recursos Humanos");
        modeloTotalContraparte.addColumn("Consultorias");
        modeloTotalContraparte.addColumn("Materiales");
        modeloTotalContraparte.addColumn("Otros Recursos");
        modeloTotalContraparte.addColumn("Total");
        jtbTotalesFC.setModel(modeloTotalContraparte);
    }

    private void agregarBienDeCapitalDisponible() {
        diagBienDeCapitalDisponible biendisponiblecreado = new diagBienDeCapitalDisponible(null, true);
        biendisponiblecreado.setLocation(Comunes.centrarDialog(biendisponiblecreado));
        biendisponiblecreado.setVisible(true);
        if (biendisponiblecreado.getBienDeCapitalCreado() != null) {
            bienesdisponibles.add(biendisponiblecreado.getBienDeCapitalCreado());
            cargarBienesDisponibles();
            cargarBienDeCapitalDisponibleTotal();
            cambiartotalBienes();
        }

    }

    private void agregarBienDeCapitalAAdquirir() {
        diagBienDeCapitalAAdquirir bienadquirircreado = new diagBienDeCapitalAAdquirir(null, true);
        bienadquirircreado.setLocation(Comunes.centrarDialog(bienadquirircreado));
        bienadquirircreado.setVisible(true);
        if (bienadquirircreado.getBienDeCapitalAAdquirirCreado() != null) {
            bienesaadquirir.add(bienadquirircreado.getBienDeCapitalAAdquirirCreado());
            cargarBienesAdquirir();
            cargarBienDeCapitalAFinanciarContraparteTotal();
            cargarBienDeCapitalAFinanciarMincytTotal();
            // cargarBienDeCapitalTotal();
            cambiartotalBienes();
        }
    }

    private void agregarRecursoHumanoDisponible() {
        diagRecursoHumanoDisponible recursoCreado = new diagRecursoHumanoDisponible(null, true);
        recursoCreado.setLocation(Comunes.centrarDialog(recursoCreado));
        recursoCreado.setVisible(true);
        if (recursoCreado.getRecursoCreado() != null) {
            recursosdisponibles.add(recursoCreado.getRecursoCreado());
            cargarRecursosDisponibles();
            cargarRHDisponibleContraparteTotal();
            cambiarTotalRecursos();

        }
    }

    private void agregarRecursosHumanoAdquirir() {
        diagRecursoHumanoAdquirir recursoCreado = new diagRecursoHumanoAdquirir(null, true);
        recursoCreado.setLocation(Comunes.centrarDialog(recursoCreado));
        recursoCreado.setVisible(true);
        if (recursoCreado.getRecursoCreado() != null) {
            recursosadquirir.add(recursoCreado.getRecursoCreado());
            cargarRecursosAdquirir();
            cargarRHAFinanciarContraparteTotal();
            cargarRHAFinanciarMincytTotal();

            cambiarTotalRecursos();
        }
    }

    private void agregarConsultoria() {
        diagConsultoria consCreada = new diagConsultoria(null, true);
        consCreada.setLocation(Comunes.centrarDialog(consCreada));
        consCreada.setVisible(true);
        if (consCreada.getConsultoriaCreada() != null) {
            consultorias.add(consCreada.getConsultoriaCreada());
            cargarConsultorias();
            cargarConsultoriasC();
            cargarConsultoriasM();
            cargarConsultoriasTotal();
        }
    }

    private void agregarMaterial() {
        diagMateriales m = new diagMateriales(null, true);
        m.setLocation(Comunes.centrarDialog(m));
        m.setVisible(true);
        if (m.getMaterialCreado() != null) {
            materiales.add(m.getMaterialCreado());
            cargarMateriales();
            cargarMaterialesC();
            cargarMaterialesM();
            cargarMaterialesTotal();
        }
    }

    private void agregarOtrosRecursosDisponibles() {
        diagOtroRecursoDisponible ord = new diagOtroRecursoDisponible(null, true);
        ord.setLocation(Comunes.centrarDialog(ord));
        ord.setVisible(true);
        if (ord.getRecursoCreado() != null) {
            otrosrecursosdisponibles.add(ord.getRecursoCreado());
            cargarOtrosRecursosDisponibles();
            cargarRecursosDisponiblesC();
            cambiarOtrosRecursosTotal();
        }

    }

    private void agregarOtrosRecursosAdquirir() {
        diagOtroRecursoAdquirir ord = new diagOtroRecursoAdquirir(null, true);
        ord.setLocation(Comunes.centrarDialog(ord));
        ord.setVisible(true);
        if (ord.getRecursoCreado() != null) {
            otrosrecursosadquirir.add(ord.getRecursoCreado());
            cargarOtrosRecursosAdquirir();
            cargarRecursosAdquirirC();
            cargarRecursosAdquirirM();
            cambiarOtrosRecursosTotal();
        }

    }

    private void agregarCronograma() {

        diagCronograma crono = new diagCronograma(null, true, etapas, cronogramas);
        crono.setLocation(Comunes.centrarDialog(crono));
        crono.setVisible(true);
        if (crono.getCronoCreado() != null) {

            cronogramas.add(crono.getCronoCreado());
            cargarCronogramas();
            cargarDesembolsoTotal();
            cargarCostoTotal();
        }
    }

    private void aceptar() {
        if (validar()) {
            switch (tipoOperacion) {
                case "Alta":
                    detem = new FinanciacionDetem();
                    cargarDatos();
                    break;
                case "Modificacion":
                    cargarDatos();
                    break;
            }
        }




        this.dispose();
    }

    public FinanciacionDetem getDetemCreado() {
        return detem;
    }

    private void cargarTipoFinanciacion() {
        jtfTipo.setText("DETEM");
    }

    private boolean validar() {
        return true;
    }

    private void cargarDatos() {
        if (jtfTipo.getText() != null) {
            detem.setDescripcion(jtfTipo.getText());

        } else {
            detem.setDescripcion("DETEM");
        }
        if (etapas.size() > 0) {
            detem.setEtapas(etapas);

        } else {
            detem.setEtapas(null);
        }
        if (bienesdisponibles.size() > 0) {
            detem.setBienesdecapital(bienesdisponibles);
        } else {
            detem.setBienesdecapital(null);
        }

        if (bienesaadquirir.size() > 0) {
            detem.setBienesdecapitaladquirir(bienesaadquirir);
        } else {
            detem.setBienesdecapitaladquirir(null);
        }
        if (consultorias.size() > 0) {
            detem.setConsultorias(consultorias);

        } else {
            detem.setConsultorias(null);

        }
        if (cronogramas.size() > 0) {
            agregarDetemACronograma();
            detem.setCronogramasdepagos(cronogramas);

        } else {
            detem.setCronogramasdepagos(null);

        }
        if (materiales.size() > 0) {
            detem.setMateriales(materiales);

        } else {
            detem.setMateriales(null);

        }
        if (otrosrecursosadquirir.size() > 0) {
            detem.setOtroRecursoAdquirir(otrosrecursosadquirir);

        } else {
            detem.setOtroRecursoAdquirir(null);

        }
        if (otrosrecursosdisponibles.size() > 0) {
            detem.setOtrosRecursoDisponibles(otrosrecursosdisponibles);

        } else {
            detem.setOtrosRecursoDisponibles(null);

        }
        if (recursosadquirir.size() > 0) {
            detem.setRecursosHumanosAdquirir(recursosadquirir);

        } else {
            detem.setRecursosHumanosAdquirir(null);

        }
        if (recursosdisponibles.size() > 0) {
            detem.setRecursosHumanosDisponibles(recursosdisponibles);

        } else {
            detem.setRecursosHumanosDisponibles(null);

        }
        if (notasExternas.size() > 0) {
            detem.setNotasExternas(notasExternas);
        } else {
            detem.setNotasExternas(null);
        }

        if (notasInternas.size() > 0) {
            detem.setNotasInternas(notasInternas);
        } else {
            detem.setNotasInternas(null);
        }
        if(listaCuentas.size() >0){
            detem.setRendicioncuenta(listaCuentas);
        }else{
            detem.setRendicioncuenta(null);
        }
        if(fondos.size() >0){
            detem.setRetiros(fondos);
        }else{
            detem.setRetiros(null); 
        }
    }

    private void cargarDuracionTotal() {
        if (etapas.size() > 0) {
            int i = etapas.size() - 1;
            Etapa e = etapas.get(i);
            jtfDuracionTotalEtapa.setText(e.getMesFin().toString());
        } else {
            jtfDuracionTotalEtapa.setText("0");
        }
    }

    private void cargarBienDeCapitalDisponibleTotal() {
        if (bienesdisponibles.size() > 0) {
            Float suma = new Float("0");
            for (BienDeCapital b : bienesdisponibles) {
                suma = suma + b.getValor();
            }
            jtfbdcdC.setText(suma.toString());
        } else {
            jtfbdcdC.setText("0");

        }
    }

    private void cargarBienDeCapitalAFinanciarContraparteTotal() {
        if (bienesaadquirir.size() > 0) {
            Float suma = new Float("0");
            for (BienDeCapitalAAdquirir b : bienesaadquirir) {
                suma = suma + b.getParteContraparte();
            }
            jtfbdcfc.setText(suma.toString());
        } else {
            jtfbdcfc.setText("0");

        }
    }

    private void cargarBienDeCapitalAFinanciarMincytTotal() {
        if (bienesaadquirir.size() > 0) {
            Float suma = new Float("0");
            for (BienDeCapitalAAdquirir b : bienesaadquirir) {
                suma = suma + b.getParteMincyt();
            }
            jtfbdcfm.setText(suma.toString());
        } else {
            jtfbdcfm.setText("0");

        }
    }

    private void cargarRHDisponibleContraparteTotal() {
        if (recursosdisponibles.size() > 0) {
            Float suma = new Float("0");
            for (RecursoHumanoDisponible b : recursosdisponibles) {
                suma = suma + b.getFondosDisponibles();
            }
            jtfrhdc.setText(suma.toString());
        } else {
            jtfrhdc.setText("0");

        }
    }

    private void cargarRHAFinanciarContraparteTotal() {
        if (recursosadquirir.size() > 0) {
            Float suma = new Float("0");
            for (RecursoHumanoAdquirir b : recursosadquirir) {
                suma = suma + b.getFinanciaC();
            }
            jtfrhfc.setText(suma.toString());
        } else {
            jtfrhfc.setText("0");

        }
    }

    private void cargarRHAFinanciarMincytTotal() {
        if (recursosadquirir.size() > 0) {
            Float suma = new Float("0");
            for (RecursoHumanoAdquirir b : recursosadquirir) {
                suma = suma + b.getFinanciaM();
            }
            jtfrhfm.setText(suma.toString());
        } else {
            jtfrhfm.setText("0");

        }
    }

    private void cargarConsultoriasC() {
        if (consultorias.size() > 0) {
            Float suma = new Float("0");
            for (Consultoria b : consultorias) {
                suma = suma + b.getFinanciaC();
            }
            jtfConsultoriasC.setText(suma.toString());
        } else {
            jtfConsultoriasC.setText("0");

        }
    }

    private void cargarConsultoriasM() {
        if (consultorias.size() > 0) {
            Float suma = new Float("0");
            for (Consultoria b : consultorias) {
                suma = suma + b.getFinanciaM();
            }
            jtfConsultoriasM.setText(suma.toString());
        } else {
            jtfConsultoriasM.setText("0");

        }
    }

    private void cargarConsultoriasTotal() {
        if (consultorias.size() > 0) {
            Float suma = new Float("0");
            for (Consultoria b : consultorias) {
                suma = suma + b.getCostoTotal();
            }
            jtfConsultoriasTotal.setText(suma.toString());
        } else {
            jtfConsultoriasTotal.setText("0");

        }
    }

    private void cargarMaterialesC() {
        if (materiales.size() > 0) {
            Float suma = new Float("0");
            for (Material b : materiales) {
                suma = suma + b.getFinanciaC();
            }
            jtfMaterialesC.setText(suma.toString());
        } else {
            jtfMaterialesC.setText("0");

        }
    }

    private void cargarMaterialesM() {
        if (materiales.size() > 0) {
            Float suma = new Float("0");
            for (Material b : materiales) {
                suma = suma + b.getFinanciaM();
            }
            jtfMaterialesM.setText(suma.toString());
        } else {
            jtfMaterialesM.setText("0");

        }
    }

    private void cargarMaterialesTotal() {
        if (materiales.size() > 0) {
            Float suma = new Float("0");
            for (Material b : materiales) {
                suma = suma + b.getCostoTotal();
            }
            jtfMaterialesTotal.setText(suma.toString());
        } else {
            jtfMaterialesTotal.setText("0");

        }
    }

    private void cargarRecursosDisponiblesC() {
        if (otrosrecursosdisponibles.size() > 0) {
            Float suma = new Float("0");
            for (OtroRecursoDisponible b : otrosrecursosdisponibles) {
                suma = suma + b.getValor();
            }
            jtfRecDC.setText(suma.toString());
        } else {
            jtfRecDC.setText("0");

        }
    }

    private void cargarRecursosAdquirirC() {
        if (otrosrecursosadquirir.size() > 0) {
            Float suma = new Float("0");
            for (OtroRecursoAdquirir b : otrosrecursosadquirir) {
                suma = suma + b.getFinanciaC();
            }
            jtfRecFC.setText(suma.toString());
        } else {
            jtfRecFC.setText("0");

        }
    }

    private void cargarRecursosAdquirirM() {
        if (otrosrecursosadquirir.size() > 0) {
            Float suma = new Float("0");
            for (OtroRecursoAdquirir b : otrosrecursosadquirir) {
                suma = suma + b.getFinanciaM();
            }
            jtfRecFM.setText(suma.toString());
        } else {
            jtfRecFM.setText("0");

        }
    }

    private Float totalDistribucionMEtapa(Etapa e) {
        return e.getBienCapitalFM() + e.getRecHumFM() + e.getConsultoriasFM()
                + e.getMaterialesFM() + e.getOtrosRecursosFM();
    }

    private Float totalDistribucionCEtapa(Etapa e) {
        return e.getBienCapitalFC() + e.getRecHumFC() + e.getConsultoriasFC()
                + e.getMaterialesFC() + e.getOtrosRecursosFC();
    }

    private void cargarCostosTotales() {
        cargarDistribucionC();
        cargarDistribucionM();

    }

    private void agregarDetemACronograma() {
        for (CronogramaDePago c : cronogramas) {
            c.setFinanciacionId(detem.getId());
        }
    }

    private void cambiartotalBienes() {
        jtfbdctotal.setText(sumarTotalBienes());
    }

    private String sumarTotalBienes() {
        Float total = Float.parseFloat(jtfbdcdC.getText()) + Float.parseFloat(jtfbdcfc.getText())
                + Float.parseFloat(jtfbdcfm.getText());
        return total.toString();
    }

    private void cambiarTotalRecursos() {
        jtfrhtotal.setText(sumarTotalRecursos());
    }

    private String sumarTotalRecursos() {
        Float total = Float.parseFloat(jtfrhdc.getText()) + Float.parseFloat(jtfrhfc.getText())
                + Float.parseFloat(jtfrhfm.getText());
        return total.toString();
    }

    private void cambiarOtrosRecursosTotal() {
        jtfRecTotal.setText(sumarTotalOtrosRecursos());
    }

    private String sumarTotalOtrosRecursos() {
        Float total = Float.parseFloat(jtfRecDC.getText()) + Float.parseFloat(jtfRecFC.getText())
                + Float.parseFloat(jtfRecFM.getText());
        return total.toString();
    }

    private void setearTextField() {
        jtfConsultoriasC.setText("0.0");
        jtfConsultoriasM.setText("0.0");
        jtfConsultoriasTotal.setText("0.0");
        jtfDuracionTotalEtapa.setText("0");
        jtfMaterialesC.setText("0.0");
        jtfMaterialesM.setText("0.0");
        jtfMaterialesTotal.setText("0.0");
        jtfRecDC.setText("0.0");
        jtfRecFC.setText("0.0");
        jtfRecFM.setText("0.0");
        jtfRecTotal.setText("0.0");
        jtfbdcdC.setText("0.0");
        jtfbdcfc.setText("0.0");
        jtfbdcfm.setText("0.0");
        jtfbdctotal.setText("0.0");
        jtfrhdc.setText("0.0");
        jtfrhfc.setText("0.0");
        jtfrhfm.setText("0.0");
        jtfrhtotal.setText("0.0");
        jtftotalcostos.setText("0.0");
        jtftotaldesembolsos.setText("0.0");


    }

    private void cargarDesembolsoTotal() {
        Float total = new Float(0f);
        for (CronogramaDePago c : cronogramas) {
            total = total + c.getDesembolso();

        }

        jtftotaldesembolsos.setText(total.toString());
    }

    private void cargarCostoTotal() {
        Float total = new Float(0f);
        for (CronogramaDePago c : cronogramas) {
            total = total + c.getCostoEjecucion();
            jtftotalcostos.setText(total.toString());
        }
    }

    private void detallesEtapa() {
        if (jListEtapas.getSelectedValue() != null) {
            diagEtapa d = new diagEtapa(null, true, (Etapa) jListEtapas.getSelectedValue(), "Modificacion");
            d.setLocation(Comunes.centrarDialog(d));
            d.setVisible(true);
            cargarEtapas();
            cargarCostosTotales();
        }
    }

    private void generarReporteConvenio1() {
        if (proyectoVinculacion != null) {
            String titulo = proyectoVinculacion.getTitulo();
            Float bcm = null;
            Float bcTotal = null;
            Float rhm = null;
            Float rhTotal = null;
            Float matm = null;

            Float matTotal = null;

            Float consm = null;
            Float consTotal = null;
            Float otrosm = null;
            Float otrosTotal = null;
            try {
                bcm = Float.valueOf(jtfbdcfm.getText());
                bcTotal = Float.valueOf(jtfbdctotal.getText());
                rhm = Float.valueOf(jtfrhfm.getText());
                rhTotal = Float.valueOf(jtfrhtotal.getText());
                matm = Float.valueOf(jtfMaterialesM.getText());

                matTotal = Float.valueOf(jtfMaterialesTotal.getText());

                consm = Float.valueOf(jtfConsultoriasM.getText());
                consTotal = Float.valueOf(jtfConsultoriasTotal.getText());
                otrosm = Float.valueOf(jtfRecFM.getText());
                otrosTotal = Float.valueOf(jtfRecTotal.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Numeros mal formados", "Error", JOptionPane.ERROR_MESSAGE);

            }
            Anexo1 a = new Anexo1();
            a.setBcM(bcm);
            a.setBcTotal(bcTotal);
            a.setConsM(consm);
            a.setConsTotal(consTotal);
            a.setMatM(matm);
            a.setMatTotal(matTotal);
            a.setOtrosM(otrosm);
            a.setOtrosTotal(otrosTotal);
            a.setRhM(rhm);
            a.setRhTotal(rhTotal);
            try {
                new reportes.Reporte().reporteConvenio1(a, titulo);
            } catch (JRException ex) {
                Logger.getLogger(diagDetem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe guardar el formulario primero");
        }

    }

    private void generarReporteConvenio2() {
        if (detem != null && proyectoVinculacion != null) {
            String titulo = proyectoVinculacion.getTitulo();
            List<Anexo2> lista = new ArrayList<Anexo2>();
            for (Etapa e : detem.getEtapas()) {
                Anexo2 a = new Anexo2();
                a.setNroEtapa(e.getNroEtapa());
                a.setMesInicio(e.getMesInicio());
                a.setMesFin(e.getMesFin());
                a.setMontoMincyt(sumarMontoMincyt(e));
                a.setMontoContra(sumarMontoContra(e));
                lista.add(a);

            }
            new reportes.Reporte().reporteConvenio2(titulo, lista);
        }
    }

    private Float sumarMontoMincyt(Etapa e) {
        Float total = e.getBienCapitalFM() + e.getConsultoriasFM()
                + e.getMaterialesFM() + e.getOtrosRecursosFM() + e.getRecHumFM();
        return total;
    }

    private Float sumarMontoContra(Etapa e) {
        Float total = e.getBienCapitalFC() + e.getConsultoriasFC()
                + e.getMaterialesFC() + e.getOtrosRecursosFC() + e.getRecHumFC();
        return total;
    }

    private void generarReporteCronogramaDesembolsos() {
        if (detem != null && proyectoVinculacion != null) {
            List<Desembolso> lista = new ArrayList<>();
            for (CronogramaDePago c : detem.getCronogramasdepagos()) {
                Desembolso d = new Desembolso();
                d.setId(c.getId());
                d.setEtapaDeCronograma(c.getEtapaDeCronograma());
                d.setDesembolso(c.getDesembolso());
                d.setEtapaAEjecutar(c.getEtapaAEjecutar());
                d.setCostoEjecucion(c.getCostoEjecucion());
                d.setSaldo(c.getSaldo());
                lista.add(d);
            }

            new reportes.Reporte().reporteCronogramaDesembolsos(lista, proyectoVinculacion.getTitulo());
            // new reportes.Reporte().reporteFinanciacionCronograma(detem, proyectoVinculacion);
        } else {
            JOptionPane.showMessageDialog(null, "Debe guardar el formulario primero");
        } // TODO add your handling code here:
    }

    private void eliminarBienDeCapitalDisponible() {
        if (jTableBienesDisponibles.getSelectedRow() != -1) {
            int i = jTableBienesDisponibles.getSelectedRow();
            bienesdisponibles.remove(i);
            cargarBienesDisponibles();
            cargarBienDeCapitalDisponibleTotal();
            cambiartotalBienes();
        }
    }

    private void eliminarBienDeCapitalAAdquirir() {
        if (jTableBienesAAdquirir.getSelectedRow() != -1) {
            int i = jTableBienesAAdquirir.getSelectedRow();
            bienesaadquirir.remove(i);
            cargarBienesAdquirir();
            cargarBienDeCapitalAFinanciarContraparteTotal();
            cargarBienDeCapitalAFinanciarMincytTotal();
            cambiartotalBienes();
        }
    }

    private void eliminarRecursoHumanoDisponible() {
        if (jTableRecursosDisponibles.getSelectedRow() != -1) {
            int i = jTableRecursosDisponibles.getSelectedRow();
            recursosdisponibles.remove(i);
            cargarRecursosDisponibles();
            cargarRHDisponibleContraparteTotal();
            cambiarTotalRecursos();
        }
    }

    private void eliminarRecursoHumanoAAdquirir() {
        if (jTableRecursosAdqurir.getSelectedRow() != -1) {
            int i = jTableRecursosAdqurir.getSelectedRow();
            recursosadquirir.remove(i);
            cargarRecursosAdquirir();
            cargarRHAFinanciarContraparteTotal();
            cargarRHAFinanciarMincytTotal();

            cambiarTotalRecursos();
        }
    }

    private void eliminarConsultoria() {
        if (jTableConsultorias.getSelectedRow() != -1) {
            int i = jTableConsultorias.getSelectedRow();
            consultorias.remove(i);
            cargarConsultorias();
            cargarConsultoriasC();
            cargarConsultoriasM();
            cargarConsultoriasTotal();
        }
    }

    private void eliminarOtrosCostosAAdquirir() {
        if (jTableOtrosRecursosAdquirir.getSelectedRow() != -1) {

            int i = jTableOtrosRecursosAdquirir.getSelectedRow();
            otrosrecursosadquirir.remove(i);
            cargarOtrosRecursosAdquirir();
            cargarRecursosAdquirirC();
            cargarRecursosAdquirirM();
            cambiarOtrosRecursosTotal();
        }
    }

    private void eliminarMateriales() {
        if (jTableMateriales.getSelectedRow() != -1) {
            int i = jTableMateriales.getSelectedRow();
            materiales.remove(i);
            cargarMateriales();
            cargarMaterialesC();
            cargarMaterialesM();
            cargarMaterialesTotal();
        }
    }

    private void eliminarOtrosCostosDisponible() {
        if (jTableOtrosRecursosDisponibles.getSelectedRow() != -1) {
            int i = jTableOtrosRecursosDisponibles.getSelectedRow();
            otrosrecursosdisponibles.remove(i);
            cargarOtrosRecursosDisponibles();
            cargarRecursosDisponiblesC();
            cambiarOtrosRecursosTotal();

        }

    }

    private void eliminarCronograma() {
        if (jTableCronograma.getSelectedRow() != -1) {
            int i = jTableCronograma.getSelectedRow();
            cronogramas.remove(i);
            cargarCronogramas();
            cargarDesembolsoTotal();
            cargarCostoTotal();
        }
    }

    private void agregarNotaExterna() {
                NotaExterna ne = notasExternas.get(jTableNotasExternas.getSelectedRow());

        diagNotaExterna notaExterna = new diagNotaExterna(null, true, "Alta", usuario);
        notaExterna.setLocation(Comunes.centrarDialog(notaExterna));
        notaExterna.setVisible(true);
        if (notaExterna.getNotaExternaCreada() != null) {
            notasExternas.add(notaExterna.getNotaExternaCreada());
            cargarNotasExternas();
        }
    }

    private void eliminarNotaExterna() {
        if (jTableNotasExternas.getSelectedRow() != -1) {
            int i = jTableNotasExternas.getSelectedRow();
            notasExternas.remove(i);
            cargarNotasExternas();
        }
    }


    private void modificarNotaExterna() {
        if (jTableNotasExternas.getSelectedRow() != -1) {
        
        //String nroNota = (Object) jTableNotasExternas.getValueAt(jTableNotasExternas.getSelectedRow(), selectedColumnIndex);
        NotaExterna ne = notasExternas.get(jTableNotasExternas.getSelectedRow());

        diagNotaExterna notaExterna = new diagNotaExterna(null, true, "Modificación", usuario, ne);
        notaExterna.setLocation(Comunes.centrarDialog(notaExterna));
        notaExterna.setVisible(true);
        if (notaExterna.getNotaExternaCreada() != null) {
          notasExternas.remove(ne);
                      
            notasExternas.add(notaExterna.getNotaExternaCreada());
            cargarNotasExternas();
        }
    }
    }

    private void modificarNotaInterna() {
        if (jTableNotasInternas.getSelectedRow() != -1) {
        
        //String nroNota = (Object) jTableNotasInternas.getValueAt(jTableNotasInternas.getSelectedRow(), selectedColumnIndex);
        NotaInterna ne = notasInternas.get(jTableNotasInternas.getSelectedRow());
        diagNotaInterna notaInterna = new diagNotaInterna(null, true, "Modificación", usuario, ne);
        notaInterna.setLocation(Comunes.centrarDialog(notaInterna));
        notaInterna.setVisible(true);
        if (notaInterna.getNotaInternaCreada() != null) {
            notasInternas.remove(ne);
            notasInternas.add(notaInterna.getNotaInternaCreada());
            cargarNotasInternas();
        }
    }
    }

    private void agregarNotaInterna() {
        diagNotaInterna notaInterna = new diagNotaInterna(null, true, "Alta", usuario);
        notaInterna.setLocation(Comunes.centrarDialog(notaInterna));
        notaInterna.setVisible(true);
        if (notaInterna.getNotaInternaCreada() != null) {
            notasInternas.add(notaInterna.getNotaInternaCreada());
            cargarNotasInternas();
        }
    }

    private void eliminarNotaInterna() {
        if (jTableNotasInternas.getSelectedRow() != -1) {
            int i = jTableNotasInternas.getSelectedRow();
            notasInternas.remove(i);
            cargarNotasInternas();
        }

    }

    private void quitarEtapa() {
        if (jListEtapas.getSelectedIndex() != -1) {
            etapas.remove(jListEtapas.getSelectedValue());
            cargarEtapas();
            
        }
    }
    
    public void reporteSeguimientoNi (){
        List<reportes.NotaIn> lista = new ArrayList();
        List<NotaInterna> listaordenada=detem.getNotasInternas();
        Collections.sort(listaordenada, comparadorNi);
        for(NotaInterna ni : listaordenada){
            reportes.NotaIn nota = new reportes.NotaIn();
            nota.setFecha(ni.getFecha());
            nota.setNronota(ni.getNroNota());
            nota.setMotivo(ni.getMotivo());
            nota.setFechaentrada(ni.getFechaDeRecibidoMesaDeEntrada());
            nota.setFechadesembolso(ni.getFechaDesembolso());
            lista.add(nota);
        }
        new reportes.Reporte().reporteSeguimientoNi(proyectoVinculacion.getTitulo(), lista);
    }

    private void reporteSeguimientoNe() {
        List<reportes.NotaEx> lista = new ArrayList();
        List<NotaExterna> listaordenada=detem.getNotasExternas();
        Collections.sort(listaordenada, comparadorNe);
        for(NotaExterna ne : listaordenada){
            reportes.NotaEx nota = new reportes.NotaEx();
            nota.setFecha(ne.getFecha());
            nota.setNronota(ne.getNroNota());
            nota.setMotivo(ne.getMotivo());
            nota.setFechaentrada(ne.getFechaDeRecibidoMesaDeEntrada());
            nota.setFechadesembolso(ne.getFechaDesembolso());
            nota.setFechaaprovacion(ne.getFechaAprobacion());
            lista.add(nota);
        }
        new reportes.Reporte().reporteSeguimientoNe(proyectoVinculacion.getTitulo(), lista);
    }
    
    private void buscarArchivoRendicion() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            rutaArchivo = fileChooser.getSelectedFile().getPath();
            if(rutaArchivo.endsWith("xls") || rutaArchivo.endsWith("xlsx")){
                jTfRendicionCuenta.setText(rutaArchivo);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe escoger el archivo excel correspondiente", "Error", JOptionPane.ERROR_MESSAGE);
                rutaArchivo="";
                jTfRendicionCuenta.setText(rutaArchivo);
            }
        }
    }

    private void cargarRendiciones() {
        try{
        if (detem != null) {
            if (!detem.getRendicioncuenta().isEmpty()) {
                Comunes.cargarJList(jListRendicion , detem.getRendicioncuenta());
                listaCuentas = detem.getRendicioncuenta();
            }else{
                Comunes.cargarJList(jListRendicion, listaCuentas);
            }
        } else {
            Comunes.cargarJList(jListRendicion, listaCuentas);
        }
        if(listaCuentas!= null){
            Comunes.cargarJList(jListRendicion, listaCuentas);
        }
        }catch(Exception ex){
            ex.getMessage();
        }
    }
    
    private void eliminarRendicion() {
        try{
            if(jListRendicion.getSelectedIndex()!=-1){
                RendicionPfipDetem rdc=(RendicionPfipDetem) jListRendicion.getSelectedValue();
                listaCuentas.remove(rdc);
                Comunes.cargarJList(jListRendicion , listaCuentas);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un item de la lista superior", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(Exception ex){
            
        }
    }
    
    private void mostrarRendiciones(){
        try{
            Object[] fila=new Object[4];
            modeloTablaRendicionCuenta();
            RendicionPfipDetem rendicion= (RendicionPfipDetem) jListRendicion.getSelectedValue();
            //Collections.sort(rendicion.getResumen(),comparadorresumen);
            for(ResumenRendicion m:rendicion.getResumen()){
                fila[0]=m.getNumeroplanilla();
                fila[1]=m.getRubro();
                fila[2]=m.getTotal();
                fila[3]=m.getAfinanciarSCTIP();
                modelorendicion.addRow(fila);
            }
            jTableRendicion.setModel(modelorendicion); 
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void modeloTablaRendicionCuenta() {
        modelorendicion = new DefaultTableModel();
        modelorendicion.addColumn("Número Planilla");
        modelorendicion.addColumn("Rubro");
        modelorendicion.addColumn("Monto Total");
        modelorendicion.addColumn("A Financiar SCTIP");
    }
    
    private void modeloTablaDetalle(){
        modelodetalle=new DefaultTableModel();
        modelodetalle.addColumn("Proveedor/ Beneficiario");
        modelodetalle.addColumn("Concepto del Gasto");
        modelodetalle.addColumn("Número de Comprobante");
        modelodetalle.addColumn("Fecha");
        modelodetalle.addColumn("Total");
        modelodetalle.addColumn("Financiado");
    }
    
    private void mostrarDetalles() {
        if(jListRendicion.getSelectedIndex()!=-1){
            if(jTableRendicion.getSelectedRow()!=-1){
                RendicionPfipDetem rendicion=(RendicionPfipDetem) jListRendicion.getSelectedValue();
                TableModel modelo=jTableRendicion.getModel();
                String aux=(String)modelo.getValueAt(jTableRendicion.getSelectedRow(), 0);
                modeloTablaDetalle();
                Object[] fila=new Object[6];
                switch (aux) {
                    case "1":                    
                        for(BienesDeCapital b:rendicion.getBienesdecapital()){
                            fila[0]=b.getProveedor();
                            fila[1]=b.getConcepto();
                            fila[2]=b.getNumerocomprobante();
                            fila[3]=b.getFecha();
                            fila[4]=b.getTotal();
                            fila[5]=b.getFinanciado();
                            modelodetalle.addRow(fila);
                        }
                        jTableDetalleRendicion.setModel(modelodetalle);
                        break;
                    case "2":
                        for(RecursosHumanos b:rendicion.getRecursoshumanos()){
                            fila[0]=b.getProveedor();
                            fila[1]=b.getConcepto();
                            fila[2]=b.getNumerocomprobante();
                            fila[3]=b.getFecha();
                            fila[4]=b.getTotal();
                            fila[5]=b.getFinanciado();
                            modelodetalle.addRow(fila);
                        }
                        jTableDetalleRendicion.setModel(modelodetalle);
                        break;
                    case "3":
                        for(Consultorias b:rendicion.getConsultorias()){
                            fila[0]=b.getProveedor();
                            fila[1]=b.getConcepto();
                            fila[2]=b.getNumerocomprobante();
                            fila[3]=b.getFecha();
                            fila[4]=b.getTotal();
                            fila[5]=b.getFinanciado();
                            modelodetalle.addRow(fila);
                        }
                        jTableDetalleRendicion.setModel(modelodetalle);
                        break;
                    case "4":
                        for(MaterialesInsumos b:rendicion.getMateriales()){
                            fila[0]=b.getProveedor();
                            fila[1]=b.getConcepto();
                            fila[2]=b.getNumerocomprobante();
                            fila[3]=b.getFecha();
                            fila[4]=b.getTotal();
                            fila[5]=b.getFinanciado();
                            modelodetalle.addRow(fila);
                        }
                        jTableDetalleRendicion.setModel(modelodetalle);
                        break;
                    case "5":
                        for(Otros b:rendicion.getOtros()){
                            fila[0]=b.getProveedor();
                            fila[1]=b.getConcepto();
                            fila[2]=b.getNumerocomprobante();
                            fila[3]=b.getFecha();
                            fila[4]=b.getTotal();
                            fila[5]=b.getFinanciado();
                            modelodetalle.addRow(fila);
                        }
                        jTableDetalleRendicion.setModel(modelodetalle);
                        break;
                }
            }
        }
    }
    
    private void buscarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            rutaArchivo = fileChooser.getSelectedFile().getPath();
            if(rutaArchivo.endsWith("xls") || rutaArchivo.endsWith("xlsx")){
                jTfrutaArchivo.setText(rutaArchivo);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe escoger el archivo excel correspondiente", "Error", JOptionPane.ERROR_MESSAGE);
                rutaArchivo="";
                jTfrutaArchivo.setText(rutaArchivo);
            }
        }
    }
    
    private void modeloTablaMovimientos(){
        tablaMovimientos= new DefaultTableModel();
        tablaMovimientos.addColumn("N° de operación");
        tablaMovimientos.addColumn("Fecha");
        tablaMovimientos.addColumn("Monto");
        tablaMovimientos.addColumn("Saldo");
        tablaMovimientos.addColumn("Comentarios");
    }
    
    private void Importar() {
        boolean flag=true;
        InputStream in=null;
        try {
            in = new FileInputStream(rutaArchivo);
           
                Workbook workbook= Workbook.getWorkbook(in);
                Sheet sheet=workbook.getSheet(0);
               // System.out.println(sheet.getRows());
               // System.out.println(rutaArchivo);
                fys= new FondosySaldos();
                Cell celdan= sheet.getCell(1  , 1);
                String nombre=celdan.getContents();
               // System.out.println(nombre);
                fys.setNombre(nombre);
               // System.out.println(celdan.getContents());
                Cell celdap= sheet.getCell(1 , 2);
                fys.setProyecto(celdap.getContents());
                Cell celdac= sheet.getCell(1 , 3);
                fys.setCodigo(celdac.getContents());
                Cell celdaf= sheet.getCell(1 , 4);
                fys.setFechaEntrada(celdaf.getContents());
                Cell celdae= sheet.getCell(1 , 5);
                fys.setExpediente(celdae.getContents());
                List<Movimiento> lista= new ArrayList();
                for(int i=8 ; i<sheet.getRows() ; i++){
                    op= new  Movimiento();
                    Cell celda1= sheet.getCell(0 , i);
                    op.setNumeroOperacion(celda1.getContents());                   
                    Cell celda2= sheet.getCell(1 , i);
                    op.setFecha(celda2.getContents());                  
                    Cell celda3= sheet.getCell(2 , i);
                    if(!celda3.getContents().equals(""))
                    op.setMonto(Float.valueOf(ImportarExcel.getInstance().quitarComas(celda3.getContents())));                                       
                    Cell celda4=sheet.getCell(4 , i);
                    if(!celda4.getContents().equals(""))
                    op.setSaldo(Float.valueOf(ImportarExcel.getInstance().quitarComas(celda4.getContents())));               
                    Cell celda5=sheet.getCell(6 , i);
                    op.setComentarios(celda5.getContents());                    
                    lista.add(op);
                }
                fys.setMovimientos(lista);
                //new FondosySaldosJpaController(emf).create(fys);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
                flag=false;
            } catch (BiffException ex) {
                JOptionPane.showMessageDialog(rootPane, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
                flag=false;
            }catch (Exception ex){
                JOptionPane.showMessageDialog(rootPane, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
                flag=false;
            }finally{
            try {
                in.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        if(flag){
            JOptionPane.showMessageDialog(rootPane, "Carga exitosa", "carga", JOptionPane.INFORMATION_MESSAGE);
            fondos.add(fys);
        }
    }
    
    private void cargarFondos() {
        try{
        if (detem != null) {
            if (!detem.getRetiros().isEmpty()) {
                Comunes.cargarJList(jListFondos , detem.getRetiros());
                fondos = detem.getRetiros();
            }else{
                Comunes.cargarJList(jListFondos, fondos);
            }
        } else {
            Comunes.cargarJList(jListFondos, fondos);
        }
        }catch(Exception ex){
            ex.getMessage();
        }
    }
    
    private void mostrarMovimientos() {
        try{
            Object[] fila=new Object[5];
            modeloTablaMovimientos();
            FondosySaldos fondo= (FondosySaldos) jListFondos.getSelectedValue();
            Collections.sort(fondo.getMovimientos(),comparador);
            for(Movimiento m:fondo.getMovimientos()){
                fila[0]=m.getNumeroOperacion();
                fila[1]=m.getFecha();
                fila[2]=m.getMonto();
                fila[3]=m.getSaldo();
                fila[4]=m.getComentarios();
                tablaMovimientos.addRow(fila);
            }
            jTableMovimientos.setModel(tablaMovimientos); 
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    Comparator<Movimiento> comparador=new Comparator<Movimiento>(){
       
        @Override
        public int compare(Movimiento a,Movimiento b){
            if(!a.getNumeroOperacion().equals("") && !b.getNumeroOperacion().equals("")){
                return(Integer.decode(a.getNumeroOperacion())-Integer.decode(b.getNumeroOperacion()));
            }else{
                return -1;
            }
        }

    };
    
    private void eliminarFondos() {
        try{
            if(jListFondos.getSelectedIndex()!=-1){
                FondosySaldos fys1=(FondosySaldos) jListFondos.getSelectedValue();
                fondos.remove(fys1);
                Comunes.cargarJList(jListFondos, fondos);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe elegir un item de la lista superior", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(Exception ex){
            
        }
    }
    
    Comparator<ResumenRendicion> comparadorresumen=new Comparator<ResumenRendicion>(){
       
        public int compare(ResumenRendicion a,ResumenRendicion b){
            
            return(Integer.decode(a.getNumeroplanilla())-Integer.decode(b.getNumeroplanilla()));
        }

    };
    
    Comparator<NotaExterna> comparadorNe=new Comparator<NotaExterna>(){
       
        public int compare(NotaExterna a,NotaExterna b){
            return(a.getFecha().compareTo(b.getFecha()));
        }

    };
    
    Comparator<NotaInterna> comparadorNi=new Comparator<NotaInterna>(){
       
        public int compare(NotaInterna a,NotaInterna b){
            return(a.getFecha().compareTo(b.getFecha()));
        }

    };
}
