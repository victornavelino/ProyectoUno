/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.proyectos.vinculacion;

import controladores.DocumentoJpaController;
import entidades.Documento;
import entidades.Resolucion;
import entidades.TipoProyecto;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.operaciones.Operacion;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.*;
import entidades.proyecto.resultado.ArticuloRevista;
import entidades.proyecto.resultado.CapituloLibro;
import entidades.proyecto.resultado.Congreso;
import entidades.proyecto.resultado.Contrato;
import entidades.proyecto.resultado.FormacionRRHH;
import entidades.proyecto.resultado.Industrial;
import entidades.proyecto.resultado.Intelectual;
import entidades.proyecto.resultado.Libro;
import entidades.proyecto.resultado.Propiedad;
import entidades.proyecto.resultado.Publicacion;
import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.NotaExterna;
import entidades.proyecto.vinculacion.NotaInterna;
import entidades.proyecto.vinculacion.ParticipacionVinculacion;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionDetem;
import entidades.proyecto.vinculacion.financiacion.FinanciacionGenerica;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPICTO;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPict;
import entidades.proyecto.vinculacion.financiacion.pfip.BienDeCapital;
import entidades.proyecto.vinculacion.financiacion.pfip.BienDeCapitalAAdquirir;
import entidades.proyecto.vinculacion.financiacion.pfip.Consultoria;
import entidades.proyecto.vinculacion.financiacion.pfip.Etapa;
import entidades.proyecto.vinculacion.financiacion.pfip.Material;
import entidades.proyecto.vinculacion.financiacion.pfip.OtroRecursoAdquirir;
import entidades.proyecto.vinculacion.financiacion.pfip.RecursoHumanoAdquirir;
import entidades.usuario.Usuario;
import facade.*;
import includes.Comunes;
import includes.ModeloTablaNoEditable;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.commons.validator.routines.IntegerValidator;
import vista.diagProyectoBusquedaSimple;
import vista.panelControl.*;
import vista.participaciones.diagParticipacion;
import vista.proyectos.diagSubDisciplinaEleccion;
import vista.proyectos.resultado.diagProyectoResultadoArticuloRevista;
import vista.proyectos.resultado.diagProyectoResultadoCapitulo;
import vista.proyectos.resultado.diagProyectoResultadoCongreso;
import vista.proyectos.resultado.diagProyectoResultadoContrato;
import vista.proyectos.resultado.diagProyectoResultadoFormacionRRHH;
import vista.proyectos.resultado.diagProyectoResultadoLibro;
import vista.proyectos.resultado.diagProyectoResultadoPIndustrial;
import vista.proyectos.resultado.diagProyectoResultadoPIntelectual;
import vista.proyectos.vinculacion.financiacion.diagDetem;
import vista.proyectos.vinculacion.financiacion.diagGenerica2;
import vista.proyectos.vinculacion.financiacion.diagPfip;
import vista.proyectos.vinculacion.financiacion.diagPict;
import vista.proyectos.vinculacion.financiacion.diagPicto;
import vista.proyectos.vinculacion.financiacion.pfip.diagSubirArchivo;
import vista.proyectos.vinculacion.seguimiento.diagNotaExterna;
import vista.proyectos.vinculacion.seguimiento.diagNotaInterna;
import vista.resoluciones.diagResolucionEleccion;

/**
 *
 * @author Panchi
 */
public class diagProyectoVinculacion extends javax.swing.JDialog {

    // Producción
    ModeloTablaNoEditable modeloTablaLibros = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaCapitulos = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaArticuloRevista = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaCongreso = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaContrato = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaPIntelectual = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaPIndustrial = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaFormacionRRHH = new ModeloTablaNoEditable();
    private Congreso congreso;
    private Contrato contrato;
    private Intelectual pIntelectual;
    private Industrial pIndustrial;
    //  private Proyecto proyecto;
    private Libro libro;
    private CapituloLibro capituloLibro;
    private ArticuloRevista articuloRevista;
    private FormacionRRHH formacionRRHH;

    /* ----------------------------------------------------- */
    private ProyectoVinculacion proyectoVinculacion;
    private Usuario usuario;
    private List<Prorroga> prorrogas = new ArrayList<>();
    private List<SubDisciplinaCientifica> subDisciplinasCientificas = new ArrayList<SubDisciplinaCientifica>();
    private List<DisciplinaCientifica> disciplinasCientificas = new ArrayList<DisciplinaCientifica>();
    private List<AreaTematica> areasTematicas = new ArrayList<AreaTematica>();
    private UnidadAcademica unidadAcademica;
    private String tipoOperacion;
    private LineaInvestigacion lineaInvestigacion;
    private UnidadEjecutora unidadEjecutora;
    private Programa programa;
    private List<Financiacion> financiaciones = new ArrayList<Financiacion>();
    private EntidadEvaluadora entidadEvaluadora;
    private List<String> palabrasClaves = new ArrayList<String>();
    private List<ParticipacionVinculacion> participaciones = new ArrayList<ParticipacionVinculacion>();
    private List<Documento> archivosDigitales = new ArrayList<Documento>();
    private List<Documento> informesEtapa = new ArrayList<Documento>();

    private DefaultTableModel modeloNotasExternas;
    private DefaultTableModel modeloNotasInternas;
    private List<NotaExterna> notasExternas = new ArrayList<>();
    private List<NotaInterna> notasInternas = new ArrayList<>();
    private String patron = "dd/MM/yyyy";
    private SimpleDateFormat formato = new SimpleDateFormat(patron);

    /**
     * Creates new form diagProyectoVinculacion
     */
    public diagProyectoVinculacion(java.awt.Frame parent, boolean modal, String tipoOperacion, Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.usuario = usuario;
        //modeloTabla = new DefaultTableModel();
        initComponents();
        inicializarComponentes();

    }

    public diagProyectoVinculacion(java.awt.Frame parent, boolean modal,
            String tipoOperacion, ProyectoVinculacion proyectoVinculacion, Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.proyectoVinculacion = proyectoVinculacion;
        // modeloTabla = new DefaultTableModel();
        this.usuario = usuario;
        initComponents();
        inicializarComponentes();
        inicializarComponentesProduccion();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfCodigoProyecto = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tfNroConvenio = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taTitulo = new javax.swing.JTextArea();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cboUnidadAcademica = new javax.swing.JComboBox();
        btNuevaUnidadAcademica = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        dateFechaInicio = new org.jdesktop.swingx.JXDatePicker();
        jLabel32 = new javax.swing.JLabel();
        tfDuracionTeorica = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        dateFechaFinalizacion = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListProrrogas = new javax.swing.JList();
        btNuevaProrroga = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfResoRectoral = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfResoMinisterial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        taDirector = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jListIntegrantes = new javax.swing.JList();
        jPanel20 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtfFinanciarUnca = new javax.swing.JTextField();
        jtfFinanciarContraparte = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cboPrograma = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jListAreasTematicas = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jListDisciplinasCientificas = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jListSubDisciplinasCientificas = new javax.swing.JList();
        btAgregarSubDisciplinaCientifica = new javax.swing.JButton();
        btQuitarSubDisciplinaCientifica = new javax.swing.JButton();
        btNuevaUnidadEjecutora = new javax.swing.JButton();
        btNuevaEntidadEvaluadora = new javax.swing.JButton();
        btNuevoPrograma = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        cboLineaInvestigacion = new javax.swing.JComboBox();
        btNuevaLineaInvestigacion = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        cboUnidadEjecutora = new javax.swing.JComboBox();
        cboEntidadEvaluadora = new javax.swing.JComboBox();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        jListEtapas = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        jListInformesEtapas = new javax.swing.JList();
        jbtnAgregarInforme = new javax.swing.JButton();
        jbtnVerInforme = new javax.swing.JButton();
        jScrollPane27 = new javax.swing.JScrollPane();
        jListFinanciamientoInformes = new javax.swing.JList();
        jLabel21 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTableNotasInternas = new javax.swing.JTable();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jBtnReporteNi = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTableNotasExternas = new javax.swing.JTable();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jBtnReporteNe = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jListParticipaciones = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResumen = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListPalabrasClaves = new javax.swing.JList();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        taObservaciones = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTAconocimiento = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jListArchivos = new javax.swing.JList();
        jBtnAgregarArchivo = new javax.swing.JButton();
        jBtnEliminarArchivo = new javax.swing.JButton();
        jBtnVerArchivo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tabResultados = new javax.swing.JTabbedPane();
        pnLibros = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblLibros = new javax.swing.JTable();
        btAgregarLibro = new javax.swing.JButton();
        btModificarLibro = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        pnCapitulos = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblCapitulos = new javax.swing.JTable();
        btAgregarCapitulo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminarCapitulo = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblArticulosRevistas = new javax.swing.JTable();
        btAgregarArticuloRevista = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        btnEliminarArticulo = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btAgregarCongreso = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblCongresos = new javax.swing.JTable();
        btnEditarCongreso = new javax.swing.JButton();
        btnEliminarCongreso = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btAgregarContrato = new javax.swing.JButton();
        btnEditarContrato = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblContratos = new javax.swing.JTable();
        btnEliminarContrato = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblPIntelectual = new javax.swing.JTable();
        btAgregarPIntelectual = new javax.swing.JButton();
        btnEditarPIntelectual = new javax.swing.JButton();
        btnEliminarPIntelectual = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblPIndustrial = new javax.swing.JTable();
        btAgregarPIndustrial = new javax.swing.JButton();
        btnEditarPIndustrial = new javax.swing.JButton();
        btnEliminarPIndustrial = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblFormacionRRHH = new javax.swing.JTable();
        btAgregarFormacionRRHH = new javax.swing.JButton();
        btnEditarFormacionRRH = new javax.swing.JButton();
        btnEliminarFormacionRRHH = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jListFinanciaciones = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel4.text")); // NOI18N

        tfCodigoProyecto.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.tfCodigoProyecto.text")); // NOI18N

        jLabel31.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel31.text")); // NOI18N

        tfNroConvenio.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.tfNroConvenio.text")); // NOI18N

        jLabel15.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel15.text")); // NOI18N

        taTitulo.setColumns(20);
        taTitulo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taTitulo.setLineWrap(true);
        taTitulo.setRows(7);
        jScrollPane3.setViewportView(taTitulo);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNroConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(tfCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31)
                        .addComponent(tfNroConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel14.text")); // NOI18N

        cboUnidadAcademica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadAcademicaItemStateChanged(evt);
            }
        });
        cboUnidadAcademica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUnidadAcademicaActionPerformed(evt);
            }
        });

        btNuevaUnidadAcademica.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btNuevaUnidadAcademica.text")); // NOI18N
        btNuevaUnidadAcademica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaUnidadAcademicaActionPerformed(evt);
            }
        });

        jLabel16.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel16.text")); // NOI18N

        dateFechaInicio.setFormats("dd/MM/yyyy");

        jLabel32.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel32.text")); // NOI18N

        tfDuracionTeorica.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.tfDuracionTeorica.text")); // NOI18N
        tfDuracionTeorica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDuracionTeoricaActionPerformed(evt);
            }
        });

        jLabel17.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel17.text")); // NOI18N

        jLabel33.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel33.text")); // NOI18N

        dateFechaFinalizacion.setFormats("dd/MM/yyyy");

        jScrollPane9.setViewportView(jListProrrogas);

        btNuevaProrroga.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btNuevaProrroga.text")); // NOI18N
        btNuevaProrroga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaProrrogaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(85, 85, 85))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateFechaFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNuevaProrroga))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(65, 65, 65)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(cboUnidadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btNuevaUnidadAcademica))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel32)
                                .addGap(31, 31, 31)
                                .addComponent(tfDuracionTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboUnidadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaUnidadAcademica)))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfDuracionTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32))
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btNuevaProrroga)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(dateFechaFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel1.text")); // NOI18N

        jtfResoRectoral.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jtfResoRectoral.text")); // NOI18N
        jtfResoRectoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfResoRectoralActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel7.text")); // NOI18N

        jtfResoMinisterial.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jtfResoMinisterial.text")); // NOI18N
        jtfResoMinisterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfResoMinisterialActionPerformed(evt);
            }
        });

        jLabel8.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel8.text")); // NOI18N

        taDirector.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.taDirector.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel9.text")); // NOI18N

        jScrollPane24.setViewportView(jListIntegrantes);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jtfResoRectoral, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jtfResoMinisterial, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(taDirector))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfResoRectoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(taDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jtfResoMinisterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel10.text")); // NOI18N

        jLabel18.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel18.text")); // NOI18N

        jtfFinanciarUnca.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jtfFinanciarUnca.text")); // NOI18N

        jtfFinanciarContraparte.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jtfFinanciarContraparte.text")); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(32, 32, 32)
                        .addComponent(jtfFinanciarUnca))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfFinanciarContraparte, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 561, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtfFinanciarUnca, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jtfFinanciarContraparte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setToolTipText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel21.toolTipText")); // NOI18N

        jLabel28.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel28.text")); // NOI18N

        jLabel30.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel30.text")); // NOI18N

        cboPrograma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProgramaItemStateChanged(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel11.text")); // NOI18N

        jListAreasTematicas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane17.setViewportView(jListAreasTematicas);

        jLabel12.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel12.text")); // NOI18N

        jListDisciplinasCientificas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane18.setViewportView(jListDisciplinasCientificas);

        jLabel13.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel13.text")); // NOI18N

        jListSubDisciplinasCientificas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane19.setViewportView(jListSubDisciplinasCientificas);

        btAgregarSubDisciplinaCientifica.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarSubDisciplinaCientifica.text")); // NOI18N
        btAgregarSubDisciplinaCientifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarSubDisciplinaCientificaActionPerformed(evt);
            }
        });

        btQuitarSubDisciplinaCientifica.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btQuitarSubDisciplinaCientifica.text")); // NOI18N
        btQuitarSubDisciplinaCientifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarSubDisciplinaCientificaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btQuitarSubDisciplinaCientifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAgregarSubDisciplinaCientifica)))
                    .addComponent(jLabel13))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btAgregarSubDisciplinaCientifica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btQuitarSubDisciplinaCientifica)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btNuevaUnidadEjecutora.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btNuevaUnidadEjecutora.text")); // NOI18N
        btNuevaUnidadEjecutora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaUnidadEjecutoraActionPerformed(evt);
            }
        });

        btNuevaEntidadEvaluadora.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btNuevaEntidadEvaluadora.text")); // NOI18N
        btNuevaEntidadEvaluadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaEntidadEvaluadoraActionPerformed(evt);
            }
        });

        btNuevoPrograma.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btNuevoPrograma.text")); // NOI18N
        btNuevoPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoProgramaActionPerformed(evt);
            }
        });

        jLabel25.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel25.text")); // NOI18N

        cboLineaInvestigacion.setMaximumSize(new java.awt.Dimension(400, 400));
        cboLineaInvestigacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLineaInvestigacionItemStateChanged(evt);
            }
        });

        btNuevaLineaInvestigacion.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btNuevaLineaInvestigacion.text")); // NOI18N
        btNuevaLineaInvestigacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaLineaInvestigacionActionPerformed(evt);
            }
        });

        jLabel26.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel26.text")); // NOI18N

        cboUnidadEjecutora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadEjecutoraItemStateChanged(evt);
            }
        });

        cboEntidadEvaluadora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboEntidadEvaluadoraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboPrograma, javax.swing.GroupLayout.Alignment.LEADING, 0, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboEntidadEvaluadora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboUnidadEjecutora, 0, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboLineaInvestigacion, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btNuevaEntidadEvaluadora)
                            .addComponent(btNuevaLineaInvestigacion)
                            .addComponent(btNuevoPrograma)
                            .addComponent(btNuevaUnidadEjecutora)))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(cboLineaInvestigacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btNuevaLineaInvestigacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cboEntidadEvaluadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaEntidadEvaluadora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(btNuevoPrograma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cboUnidadEjecutora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaUnidadEjecutora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel22.border.title"))); // NOI18N

        jListEtapas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListEtapasValueChanged(evt);
            }
        });
        jScrollPane25.setViewportView(jListEtapas);

        jLabel19.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel19.text")); // NOI18N

        jLabel20.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel20.text")); // NOI18N

        jScrollPane26.setViewportView(jListInformesEtapas);

        jbtnAgregarInforme.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jbtnAgregarInforme.text")); // NOI18N
        jbtnAgregarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarInformeActionPerformed(evt);
            }
        });

        jbtnVerInforme.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jbtnVerInforme.text")); // NOI18N
        jbtnVerInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVerInformeActionPerformed(evt);
            }
        });

        jListFinanciamientoInformes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListFinanciamientoInformesValueChanged(evt);
            }
        });
        jScrollPane27.setViewportView(jListFinanciamientoInformes);

        jLabel21.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel21.text")); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 53, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnVerInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnAgregarInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel20))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane27))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jbtnAgregarInforme)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnVerInforme))
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(jScrollPane26))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1335, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel29.border.title"))); // NOI18N

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
        jScrollPane22.setViewportView(jTableNotasInternas);

        jButton28.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton28.text")); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton29.text")); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jBtnReporteNi.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jBtnReporteNi.text")); // NOI18N
        jBtnReporteNi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReporteNiActionPerformed(evt);
            }
        });

        jButton31.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton31.text")); // NOI18N
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
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(jBtnReporteNi, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnReporteNi)
                .addGap(0, 101, Short.MAX_VALUE))
            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel28.border.title"))); // NOI18N

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
        jScrollPane23.setViewportView(jTableNotasExternas);

        jButton26.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton26.text")); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton27.text")); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jBtnReporteNe.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jBtnReporteNe.text")); // NOI18N
        jBtnReporteNe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReporteNeActionPerformed(evt);
            }
        });

        jButton30.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton30.text")); // NOI18N
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
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnReporteNe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jButton26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27)
                .addGap(4, 4, 4)
                .addComponent(jButton30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnReporteNe))
            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1335, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1734, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        jLabel29.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel29.text")); // NOI18N

        jScrollPane11.setViewportView(jListParticipaciones);

        jButton4.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton9.text")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1544, 1544, 1544))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addComponent(jLabel29)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1943, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel5.text")); // NOI18N

        taResumen.setColumns(20);
        taResumen.setLineWrap(true);
        taResumen.setRows(20);
        jScrollPane2.setViewportView(taResumen);

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel6.text")); // NOI18N

        jScrollPane4.setViewportView(jListPalabrasClaves);

        jLabel23.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel23.text")); // NOI18N

        taObservaciones.setColumns(20);
        taObservaciones.setLineWrap(true);
        taObservaciones.setRows(20);
        jScrollPane5.setViewportView(taObservaciones);

        jButton5.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jLabel3.text")); // NOI18N

        jTAconocimiento.setColumns(20);
        jTAconocimiento.setLineWrap(true);
        jTAconocimiento.setRows(5);
        jScrollPane6.setViewportView(jTAconocimiento);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel23)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1830, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1735, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jScrollPane10.setViewportView(jListArchivos);

        jBtnAgregarArchivo.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jBtnAgregarArchivo.text")); // NOI18N
        jBtnAgregarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarArchivoActionPerformed(evt);
            }
        });

        jBtnEliminarArchivo.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jBtnEliminarArchivo.text")); // NOI18N
        jBtnEliminarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarArchivoActionPerformed(evt);
            }
        });

        jBtnVerArchivo.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jBtnVerArchivo.text")); // NOI18N
        jBtnVerArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVerArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnAgregarArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jBtnEliminarArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jBtnVerArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap(1889, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jBtnAgregarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnEliminarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnVerArchivo)))
                .addContainerGap(1965, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel7.border.title"))); // NOI18N

        tblLibros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tblLibros);

        btAgregarLibro.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarLibro.text")); // NOI18N
        btAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarLibroActionPerformed(evt);
            }
        });

        btModificarLibro.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btModificarLibro.text")); // NOI18N
        btModificarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarLibroActionPerformed(evt);
            }
        });

        btEliminar.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btEliminar.text")); // NOI18N
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLibrosLayout = new javax.swing.GroupLayout(pnLibros);
        pnLibros.setLayout(pnLibrosLayout);
        pnLibrosLayout.setHorizontalGroup(
            pnLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLibrosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btModificarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pnLibrosLayout.setVerticalGroup(
            pnLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLibrosLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(pnLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnLibrosLayout.createSequentialGroup()
                        .addComponent(btAgregarLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModificarLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEliminar))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.pnLibros.TabConstraints.tabTitle"), pnLibros); // NOI18N

        tblCapitulos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tblCapitulos);

        btAgregarCapitulo.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarCapitulo.text")); // NOI18N
        btAgregarCapitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarCapituloActionPerformed(evt);
            }
        });

        btnEditar.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEditar.text")); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminarCapitulo.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarCapitulo.text")); // NOI18N
        btnEliminarCapitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCapituloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCapitulosLayout = new javax.swing.GroupLayout(pnCapitulos);
        pnCapitulos.setLayout(pnCapitulosLayout);
        pnCapitulosLayout.setHorizontalGroup(
            pnCapitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCapitulosLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnCapitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btAgregarCapitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarCapitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnCapitulosLayout.setVerticalGroup(
            pnCapitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCapitulosLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnCapitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnCapitulosLayout.createSequentialGroup()
                        .addComponent(btAgregarCapitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarCapitulo))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.pnCapitulos.TabConstraints.tabTitle"), pnCapitulos); // NOI18N

        tblArticulosRevistas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(tblArticulosRevistas);

        btAgregarArticuloRevista.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarArticuloRevista.text")); // NOI18N
        btAgregarArticuloRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarArticuloRevistaActionPerformed(evt);
            }
        });

        jButton11.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton11.text")); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        btnEliminarArticulo.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarArticulo.text")); // NOI18N
        btnEliminarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarArticuloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btAgregarArticuloRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btAgregarArticuloRevista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarArticulo))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        btAgregarCongreso.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarCongreso.text")); // NOI18N
        btAgregarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarCongresoActionPerformed(evt);
            }
        });

        tblCongresos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(tblCongresos);

        btnEditarCongreso.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEditarCongreso.text")); // NOI18N
        btnEditarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCongresoActionPerformed(evt);
            }
        });

        btnEliminarCongreso.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarCongreso.text")); // NOI18N
        btnEliminarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCongresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditarCongreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarCongreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarCongreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btAgregarCongreso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarCongreso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarCongreso))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel9.TabConstraints.tabTitle"), jPanel9); // NOI18N

        btAgregarContrato.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarContrato.text")); // NOI18N
        btAgregarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarContratoActionPerformed(evt);
            }
        });

        btnEditarContrato.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEditarContrato.text")); // NOI18N
        btnEditarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarContratoActionPerformed(evt);
            }
        });

        tblContratos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane14.setViewportView(tblContratos);

        btnEliminarContrato.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarContrato.text")); // NOI18N
        btnEliminarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(btnEliminarContrato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btAgregarContrato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarContrato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarContrato))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel11.TabConstraints.tabTitle"), jPanel11); // NOI18N

        tblPIntelectual.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(tblPIntelectual);

        btAgregarPIntelectual.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarPIntelectual.text")); // NOI18N
        btAgregarPIntelectual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarPIntelectualActionPerformed(evt);
            }
        });

        btnEditarPIntelectual.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEditarPIntelectual.text")); // NOI18N
        btnEditarPIntelectual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPIntelectualActionPerformed(evt);
            }
        });

        btnEliminarPIntelectual.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarPIntelectual.text")); // NOI18N
        btnEliminarPIntelectual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPIntelectualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarPIntelectual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarPIntelectual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(btnEditarPIntelectual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btAgregarPIntelectual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarPIntelectual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarPIntelectual))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel12.TabConstraints.tabTitle"), jPanel12); // NOI18N

        tblPIndustrial.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane16.setViewportView(tblPIndustrial);

        btAgregarPIndustrial.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarPIndustrial.text")); // NOI18N
        btAgregarPIndustrial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarPIndustrialActionPerformed(evt);
            }
        });

        btnEditarPIndustrial.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEditarPIndustrial.text")); // NOI18N
        btnEditarPIndustrial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPIndustrialActionPerformed(evt);
            }
        });

        btnEliminarPIndustrial.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarPIndustrial.text")); // NOI18N
        btnEliminarPIndustrial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPIndustrialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarPIndustrial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarPIndustrial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarPIndustrial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btAgregarPIndustrial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarPIndustrial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarPIndustrial))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel13.TabConstraints.tabTitle"), jPanel13); // NOI18N

        tblFormacionRRHH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane20.setViewportView(tblFormacionRRHH);

        btAgregarFormacionRRHH.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btAgregarFormacionRRHH.text")); // NOI18N
        btAgregarFormacionRRHH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarFormacionRRHHActionPerformed(evt);
            }
        });

        btnEditarFormacionRRH.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEditarFormacionRRH.text")); // NOI18N
        btnEditarFormacionRRH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFormacionRRHActionPerformed(evt);
            }
        });

        btnEliminarFormacionRRHH.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.btnEliminarFormacionRRHH.text")); // NOI18N
        btnEliminarFormacionRRHH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFormacionRRHHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarFormacionRRHH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarFormacionRRH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarFormacionRRHH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btAgregarFormacionRRHH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarFormacionRRH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarFormacionRRHH))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        tabResultados.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel14.TabConstraints.tabTitle"), jPanel14); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(tabResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(24, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1637, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1813, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel17.border.title"))); // NOI18N

        jScrollPane21.setViewportView(jListFinanciaciones);

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jCheckBox1.text")); // NOI18N

        jButton3.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1871, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1929, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jScrollPane1.setViewportView(jTabbedPane2);

        jButton10.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton10.text")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setText(org.openide.util.NbBundle.getMessage(diagProyectoVinculacion.class, "diagProyectoVinculacion.jButton12.text")); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addComponent(jButton12)
                .addGap(33, 33, 33)
                .addComponent(jButton2)
                .addGap(34, 34, 34)
                .addComponent(jButton10)
                .addContainerGap(280, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton10)
                    .addComponent(jButton12))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNuevoProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoProgramaActionPerformed
        agregarNuevoPrograma();
    }//GEN-LAST:event_btNuevoProgramaActionPerformed

    private void btNuevaEntidadEvaluadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaEntidadEvaluadoraActionPerformed
        agregarNuevaEntidadEvaluadora();
    }//GEN-LAST:event_btNuevaEntidadEvaluadoraActionPerformed

    private void btNuevaUnidadEjecutoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaUnidadEjecutoraActionPerformed
        agregarNuevaUnidadEjecutora();
    }//GEN-LAST:event_btNuevaUnidadEjecutoraActionPerformed

    private void btNuevaProrrogaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaProrrogaActionPerformed
        agregarNuevaProrroga();
    }//GEN-LAST:event_btNuevaProrrogaActionPerformed

    private void tfDuracionTeoricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDuracionTeoricaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDuracionTeoricaActionPerformed

    private void cboProgramaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProgramaItemStateChanged
        cambiarProgramaSeleccionado();
    }//GEN-LAST:event_cboProgramaItemStateChanged

    private void cboEntidadEvaluadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboEntidadEvaluadoraItemStateChanged
        cambiarEntidadEvaluadoraSeleccionada();
    }//GEN-LAST:event_cboEntidadEvaluadoraItemStateChanged

    private void cboUnidadEjecutoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadEjecutoraItemStateChanged
        cambiarUnidadEjecutoraSeleccionada();
    }//GEN-LAST:event_cboUnidadEjecutoraItemStateChanged

    private void btNuevaLineaInvestigacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaLineaInvestigacionActionPerformed
        agregarNuevaLineaInvestigacion();
    }//GEN-LAST:event_btNuevaLineaInvestigacionActionPerformed

    private void cboLineaInvestigacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLineaInvestigacionItemStateChanged
        cambiarLineaInvestigacionSeleccionada();
    }//GEN-LAST:event_cboLineaInvestigacionItemStateChanged

    private void btQuitarSubDisciplinaCientificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarSubDisciplinaCientificaActionPerformed
        quitarSubDisciplinaCientifica();
    }//GEN-LAST:event_btQuitarSubDisciplinaCientificaActionPerformed

    private void btAgregarSubDisciplinaCientificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarSubDisciplinaCientificaActionPerformed
        agregarSubDisciplinaCientifica();
    }//GEN-LAST:event_btAgregarSubDisciplinaCientificaActionPerformed

    private void btNuevaUnidadAcademicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaUnidadAcademicaActionPerformed
        agregarNuevaUnidadAcademica();
    }//GEN-LAST:event_btNuevaUnidadAcademicaActionPerformed

    private void cboUnidadAcademicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadAcademicaItemStateChanged
        cambiarUnidadAcademicaSeleccionada();
    }//GEN-LAST:event_cboUnidadAcademicaItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aceptar();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboUnidadAcademicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUnidadAcademicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadAcademicaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        agregarPalabraClave();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        borrarPalabraClave();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        agregarNuevaParticipacion();           // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        eliminarParticipacion();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarLibroActionPerformed
        agregarLibro();
    }//GEN-LAST:event_btAgregarLibroActionPerformed

    private void btModificarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarLibroActionPerformed
        editarLibro();

    }//GEN-LAST:event_btModificarLibroActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        eliminarRegistroLibro();

    }//GEN-LAST:event_btEliminarActionPerformed

    private void btAgregarCapituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarCapituloActionPerformed
        agregarCapitulo();
    }//GEN-LAST:event_btAgregarCapituloActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editarRegistroCapitulo();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarCapituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCapituloActionPerformed
        eliminarRegistro(tblCapitulos);
    }//GEN-LAST:event_btnEliminarCapituloActionPerformed

    private void btAgregarArticuloRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarArticuloRevistaActionPerformed
        agregarArticuloRevista();
    }//GEN-LAST:event_btAgregarArticuloRevistaActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        editarRegistroArticuloRevista();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnEliminarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarArticuloActionPerformed
        eliminarRegistro(tblArticulosRevistas);
    }//GEN-LAST:event_btnEliminarArticuloActionPerformed

    private void btAgregarCongresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarCongresoActionPerformed
        agregarCongreso();
    }//GEN-LAST:event_btAgregarCongresoActionPerformed

    private void btnEditarCongresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCongresoActionPerformed
        editarCongreso();
    }//GEN-LAST:event_btnEditarCongresoActionPerformed

    private void btnEliminarCongresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCongresoActionPerformed
        eliminarRegistro(tblCongresos);
    }//GEN-LAST:event_btnEliminarCongresoActionPerformed

    private void btAgregarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarContratoActionPerformed
        agregarContrato();
    }//GEN-LAST:event_btAgregarContratoActionPerformed

    private void btnEditarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarContratoActionPerformed
        editarContrato();
    }//GEN-LAST:event_btnEditarContratoActionPerformed

    private void btnEliminarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarContratoActionPerformed
        eliminarRegistroContrato();
    }//GEN-LAST:event_btnEliminarContratoActionPerformed

    private void btAgregarPIntelectualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarPIntelectualActionPerformed
        agregarPIntelectual();
    }//GEN-LAST:event_btAgregarPIntelectualActionPerformed

    private void btnEditarPIntelectualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPIntelectualActionPerformed
        editarPintelectual();
        cargarPIntelectuales();
    }//GEN-LAST:event_btnEditarPIntelectualActionPerformed

    private void btnEliminarPIntelectualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPIntelectualActionPerformed
        eliminarRegistroPropiedad(tblPIntelectual);
    }//GEN-LAST:event_btnEliminarPIntelectualActionPerformed

    private void btAgregarPIndustrialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarPIndustrialActionPerformed
        agregarPIndustrial();
    }//GEN-LAST:event_btAgregarPIndustrialActionPerformed

    private void btnEditarPIndustrialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPIndustrialActionPerformed
        editarPIndustrial();
    }//GEN-LAST:event_btnEditarPIndustrialActionPerformed

    private void btnEliminarPIndustrialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPIndustrialActionPerformed
        eliminarRegistroPropiedad(tblPIndustrial);
    }//GEN-LAST:event_btnEliminarPIndustrialActionPerformed

    private void btAgregarFormacionRRHHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarFormacionRRHHActionPerformed
        agregarFormacionRH();
    }//GEN-LAST:event_btAgregarFormacionRRHHActionPerformed

    private void btnEditarFormacionRRHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFormacionRRHActionPerformed
        modificarFormacionRH();
    }//GEN-LAST:event_btnEditarFormacionRRHActionPerformed

    private void btnEliminarFormacionRRHHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFormacionRRHHActionPerformed
        eliminarFormacionRH();
    }//GEN-LAST:event_btnEliminarFormacionRRHHActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarNuevaFinanciacion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modificarFinanciacion();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        quitarFinanciacion();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jBtnAgregarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarArchivoActionPerformed
        agregarArchivo();
    }//GEN-LAST:event_jBtnAgregarArchivoActionPerformed

    private void jBtnEliminarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarArchivoActionPerformed
        eliminarArchivo();
    }//GEN-LAST:event_jBtnEliminarArchivoActionPerformed

    private void jBtnVerArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVerArchivoActionPerformed
        abrirDocumento();
    }//GEN-LAST:event_jBtnVerArchivoActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        agregarNotaInterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        eliminarNotaInterna();// TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jBtnReporteNiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReporteNiActionPerformed
        if (!notasInternas.isEmpty()) {
            reporteSeguimientoNi();
        }
    }//GEN-LAST:event_jBtnReporteNiActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        agregarNotaExterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        eliminarNotaExterna();            // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jBtnReporteNeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReporteNeActionPerformed
        if (!notasExternas.isEmpty()) {
            reporteSeguimientoNe();
        }
    }//GEN-LAST:event_jBtnReporteNeActionPerformed

    private void jtfResoRectoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfResoRectoralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfResoRectoralActionPerformed

    private void jtfResoMinisterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfResoMinisterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfResoMinisterialActionPerformed

    private void jbtnAgregarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarInformeActionPerformed
        agregarInformeEtapa();        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnAgregarInformeActionPerformed

    private void jListEtapasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListEtapasValueChanged
        cargarDocsDeEtapa();        // TODO add your handling code here:
    }//GEN-LAST:event_jListEtapasValueChanged

    private void jListFinanciamientoInformesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListFinanciamientoInformesValueChanged
        cargarEtapaDeInforme();        // TODO add your handling code here:
    }//GEN-LAST:event_jListFinanciamientoInformesValueChanged

    private void jbtnVerInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVerInformeActionPerformed
        verDocumentoInforme();
    }//GEN-LAST:event_jbtnVerInformeActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        modificarNotaExterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        modificarNotaInterna();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        modificarParticipacion();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        aceptar();// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(diagProyectoVinculacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagProyectoVinculacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagProyectoVinculacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagProyectoVinculacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagProyectoVinculacion dialog = new diagProyectoVinculacion(new javax.swing.JFrame(), true, new String(), new Usuario());
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
    private javax.swing.JButton btAgregarArticuloRevista;
    private javax.swing.JButton btAgregarCapitulo;
    private javax.swing.JButton btAgregarCongreso;
    private javax.swing.JButton btAgregarContrato;
    private javax.swing.JButton btAgregarFormacionRRHH;
    private javax.swing.JButton btAgregarLibro;
    private javax.swing.JButton btAgregarPIndustrial;
    private javax.swing.JButton btAgregarPIntelectual;
    private javax.swing.JButton btAgregarSubDisciplinaCientifica;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btModificarLibro;
    private javax.swing.JButton btNuevaEntidadEvaluadora;
    private javax.swing.JButton btNuevaLineaInvestigacion;
    private javax.swing.JButton btNuevaProrroga;
    private javax.swing.JButton btNuevaUnidadAcademica;
    private javax.swing.JButton btNuevaUnidadEjecutora;
    private javax.swing.JButton btNuevoPrograma;
    private javax.swing.JButton btQuitarSubDisciplinaCientifica;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarCongreso;
    private javax.swing.JButton btnEditarContrato;
    private javax.swing.JButton btnEditarFormacionRRH;
    private javax.swing.JButton btnEditarPIndustrial;
    private javax.swing.JButton btnEditarPIntelectual;
    private javax.swing.JButton btnEliminarArticulo;
    private javax.swing.JButton btnEliminarCapitulo;
    private javax.swing.JButton btnEliminarCongreso;
    private javax.swing.JButton btnEliminarContrato;
    private javax.swing.JButton btnEliminarFormacionRRHH;
    private javax.swing.JButton btnEliminarPIndustrial;
    private javax.swing.JButton btnEliminarPIntelectual;
    private javax.swing.JComboBox cboEntidadEvaluadora;
    private javax.swing.JComboBox cboLineaInvestigacion;
    private javax.swing.JComboBox cboPrograma;
    private javax.swing.JComboBox cboUnidadAcademica;
    private javax.swing.JComboBox cboUnidadEjecutora;
    private org.jdesktop.swingx.JXDatePicker dateFechaFinalizacion;
    private org.jdesktop.swingx.JXDatePicker dateFechaInicio;
    private javax.swing.JButton jBtnAgregarArchivo;
    private javax.swing.JButton jBtnEliminarArchivo;
    private javax.swing.JButton jBtnReporteNe;
    private javax.swing.JButton jBtnReporteNi;
    private javax.swing.JButton jBtnVerArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListArchivos;
    private javax.swing.JList jListAreasTematicas;
    private javax.swing.JList jListDisciplinasCientificas;
    private javax.swing.JList jListEtapas;
    private javax.swing.JList jListFinanciaciones;
    private javax.swing.JList jListFinanciamientoInformes;
    private javax.swing.JList jListInformesEtapas;
    private javax.swing.JList jListIntegrantes;
    private javax.swing.JList jListPalabrasClaves;
    private javax.swing.JList jListParticipaciones;
    private javax.swing.JList jListProrrogas;
    private javax.swing.JList jListSubDisciplinasCientificas;
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
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTAconocimiento;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableNotasExternas;
    private javax.swing.JTable jTableNotasInternas;
    private javax.swing.JButton jbtnAgregarInforme;
    private javax.swing.JButton jbtnVerInforme;
    private javax.swing.JTextField jtfFinanciarContraparte;
    private javax.swing.JTextField jtfFinanciarUnca;
    private javax.swing.JTextField jtfResoMinisterial;
    private javax.swing.JTextField jtfResoRectoral;
    private javax.swing.JPanel pnCapitulos;
    private javax.swing.JPanel pnLibros;
    private javax.swing.JTextField taDirector;
    private javax.swing.JTextArea taObservaciones;
    private javax.swing.JTextArea taResumen;
    private javax.swing.JTextArea taTitulo;
    private javax.swing.JTabbedPane tabResultados;
    private javax.swing.JTable tblArticulosRevistas;
    private javax.swing.JTable tblCapitulos;
    private javax.swing.JTable tblCongresos;
    private javax.swing.JTable tblContratos;
    private javax.swing.JTable tblFormacionRRHH;
    private javax.swing.JTable tblLibros;
    private javax.swing.JTable tblPIndustrial;
    private javax.swing.JTable tblPIntelectual;
    private javax.swing.JTextField tfCodigoProyecto;
    private javax.swing.JTextField tfDuracionTeorica;
    private javax.swing.JTextField tfNroConvenio;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {

        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

        cargarUnidadesAcademicas();
        cargarAreasTematicas();
        cargarDisciplinasCientificas();
        cargarSubDisciplinasCientificas();
        cargarEntidadesEvaluadoras();
        cargarUnidadesEjecutoras();
        cargarEntidadesEvaluadoras();
        cargarLineasInvestigacion();
        cargarProgramas();
        //cargarArchivos();
        cargarNotasExternas();

        cargarNotasInternas();
        switch (tipoOperacion) {
            case "Alta":
                break;
            case "Modificación":
                try {
                    if (proyectoVinculacion.getTitulo() != null) {
                        taTitulo.setText(proyectoVinculacion.getTitulo());
                        // cargarParticipaciones();
                        // participaciones = proyectoVinculacion.getParticipaciones();
                        // cargarParticipaciones();

                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getCodigo() != null) {
                        tfCodigoProyecto.setText(proyectoVinculacion.getCodigo());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getParticipaciones() != null) {

                        //for (ParticipacionVinculacion p : ProyectoVinculacionFacade.getInstance().getParticipacionVinculacionesActuales(proyectoVinculacion)){
                        ArrayList participacionesActuales = new ArrayList();

                        for (ParticipacionVinculacion p : proyectoVinculacion.getParticipaciones()) {
                            if (p.getFechaHasta() == null) {
                                participacionesActuales.add(p);
                            }
                            
                            if (p.getRol().getDescripcion().equals("Director")) {
                                taDirector.setText(p.getInvestigador().getPersona().toString());
                            }
                        }
                        Comunes.cargarJList(jListIntegrantes, participacionesActuales);

                        //Comunes.cargarJList(jListIntegrantes, ProyectoVinculacionFacade.getInstance().getParticipacionVinculacionesActuales(proyectoVinculacion));
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    tfDuracionTeorica.setText(((Integer) proyectoVinculacion.getDuracionTeorica()).toString());
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getFechaInicio() != null) {
                        dateFechaInicio.setDate(proyectoVinculacion.getFechaInicio());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getFechaFinalizacion() != null) {
                        dateFechaFinalizacion.setDate(proyectoVinculacion.getFechaFinalizacion());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getUnidadAcademica() != null) {
                        cargarUnidadesAcademicas();
                        unidadAcademica = proyectoVinculacion.getUnidadAcademica();
                        cboUnidadAcademica.setSelectedItem(unidadAcademica);
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getSubDisciplinasCientificas() != null) {
                        subDisciplinasCientificas = proyectoVinculacion.getSubDisciplinasCientificas();
                        cargarSubDisciplinasCientificas();
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getLineaInvestigacion() != null) {
                        cboLineaInvestigacion.setSelectedItem(proyectoVinculacion.getLineaInvestigacion());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getUnidadEjecutora() != null) {
                        cboUnidadEjecutora.setSelectedItem(proyectoVinculacion.getUnidadEjecutora());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getEntidadEvaluadora() != null) {
                        cboEntidadEvaluadora.setSelectedItem(proyectoVinculacion.getEntidadEvaluadora());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (!proyectoVinculacion.getProrrogas().isEmpty()) {
                        prorrogas = proyectoVinculacion.getProrrogas();
                        cargarProrrogas();
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getNroConvenio() != null) {
                        tfNroConvenio.setText(proyectoVinculacion.getNroConvenio());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getPrograma() != null) {
                        cboPrograma.setSelectedItem(proyectoVinculacion.getPrograma());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getPalabrasClaves() != null) {
                        Comunes.cargarJList(jListPalabrasClaves, proyectoVinculacion.getPalabrasClaves());
                        palabrasClaves = proyectoVinculacion.getPalabrasClaves();
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getResumen() != null) {
                        taResumen.setText(proyectoVinculacion.getResumen());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getTransferenciaConocimiento() != null) {
                        jTAconocimiento.setText(proyectoVinculacion.getTransferenciaConocimiento());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getObservaciones() != null) {
                        taObservaciones.setText(proyectoVinculacion.getObservaciones());
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getNotasExternas() != null) {
                        notasExternas = proyectoVinculacion.getNotasExternas();
                        cargarNotasExternas();
                    }
                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getNotasInternas() != null) {
                        notasInternas = proyectoVinculacion.getNotasInternas();

                        cargarNotasInternas();
                    }

                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getFinanciaciones() != null) {
                        financiaciones = proyectoVinculacion.getFinanciaciones();
                        cargarFinanciaciones();
                        
                    }

                } catch (NullPointerException ex) {
                }
                try {
                    if (proyectoVinculacion.getParticipaciones() != null) {
                        participaciones = proyectoVinculacion.getParticipaciones();
                        Comunes.cargarJList(jListParticipaciones, participaciones);
                    }
                } catch (NullPointerException ex) {
                }
                try{
                    
                if(proyectoVinculacion.getResolucionMinisterial() != null){
                    jtfResoMinisterial.setText(proyectoVinculacion.getResolucionMinisterial());
                            }
                } catch (NullPointerException ex) {
                }
                try{
                    
                if(proyectoVinculacion.getResolucionRectoral() != null){
                    jtfResoRectoral.setText(proyectoVinculacion.getResolucionRectoral());
                            }
                } catch (NullPointerException ex) {
                }
                
                try{
                    
                if(proyectoVinculacion.getArchivosDigitales()!= null){
                    archivosDigitales = proyectoVinculacion.getArchivosDigitales();
                    cargarDocumentosDigitales();
                            }
                } catch (NullPointerException ex) {
                }

                break;
        }
    }

    private void cargarProgramas() {
        Comunes.cargarJComboConBlanco(cboPrograma, ProgramaFacade.getInstance().getTodosPrograma());
    }

    private void cargarUnidadesEjecutoras() {
        Comunes.cargarJComboConBlanco(cboUnidadEjecutora, UnidadEjecutoraFacade.getInstance().getTodosUnidadEjecutora());
    }

    private void cargarUnidadesAcademicas() {
        Comunes.cargarJComboConBlanco(cboUnidadAcademica, UnidadAcademicaFacade.getInstance().getTodasUnidadAcademica());
    }

    private void cargarAreasTematicas() {
        areasTematicas = new ArrayList<AreaTematica>();
        for (DisciplinaCientifica disciplinaCientifica : disciplinasCientificas) {
            if (!areasTematicas.contains(disciplinaCientifica.getAreaTematica())) {
                areasTematicas.add(disciplinaCientifica.getAreaTematica());
            }
        }
        Comunes.cargarJList(jListAreasTematicas, areasTematicas);
    }

    private void cargarDisciplinasCientificas() {
        disciplinasCientificas = new ArrayList<DisciplinaCientifica>();
        for (SubDisciplinaCientifica subDisciplinaCientifica : subDisciplinasCientificas) {
            if (!disciplinasCientificas.contains(subDisciplinaCientifica.getDisciplinaCientifica())) {
                disciplinasCientificas.add(subDisciplinaCientifica.getDisciplinaCientifica());
            }
        }
        Comunes.cargarJList(jListDisciplinasCientificas, disciplinasCientificas);
        cargarAreasTematicas();
    }

    private void cargarSubDisciplinasCientificas() {
        Comunes.cargarJList(jListSubDisciplinasCientificas, subDisciplinasCientificas);
        cargarDisciplinasCientificas();
    }

    private void cargarEntidadesEvaluadoras() {
        Comunes.cargarJComboConBlanco(cboEntidadEvaluadora, EntidadEvaluadoraFacade.getInstance().getTodosEntidadEvaluadora());
    }

    private void cambiarUnidadAcademicaSeleccionada() {
        if (cboUnidadAcademica.getSelectedIndex() > 0) {
            unidadAcademica = (UnidadAcademica) cboUnidadAcademica.getSelectedItem();
        } else {
            unidadAcademica = null;
        }
    }

    private void agregarNuevoPrograma() {
        diagPrograma programaAlta = new diagPrograma(null, true, "Alta");
        programaAlta.setLocation(Comunes.centrarDialog(programaAlta));
        programaAlta.setVisible(true);
        cargarProgramas();
        if (programaAlta.getProgramaCreada() != null) {
            cboPrograma.setSelectedItem(programaAlta.getProgramaCreada());
        }
    }

    private void cambiarLineaInvestigacionSeleccionada() {
        if (cboLineaInvestigacion.getSelectedIndex() > 0) {
            lineaInvestigacion = (LineaInvestigacion) cboLineaInvestigacion.getSelectedItem();
        } else {
            lineaInvestigacion = null;
        }
    }

    private void quitarSubDisciplinaCientifica() {
        if (jListSubDisciplinasCientificas.getSelectedIndex() != -1) {
            subDisciplinasCientificas.remove(jListSubDisciplinasCientificas.getSelectedValue());
            cargarSubDisciplinasCientificas();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una subdisciplina científica", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarNuevaEntidadEvaluadora() {
        diagEntidadEvaluadora entidadEvaluadoraAlta = new diagEntidadEvaluadora(null, true, "Alta");
        entidadEvaluadoraAlta.setLocation(Comunes.centrarDialog(entidadEvaluadoraAlta));
        entidadEvaluadoraAlta.setVisible(true);
        cargarEntidadesEvaluadoras();
        if (entidadEvaluadoraAlta.getEntidadEvaluadoraCreada() != null) {
            cboEntidadEvaluadora.setSelectedItem(entidadEvaluadoraAlta.getEntidadEvaluadoraCreada());
        }
    }

    private void agregarSubDisciplinaCientifica() {
        diagSubDisciplinaEleccion subDisciplinaEleccion = new diagSubDisciplinaEleccion(null, true);
        subDisciplinaEleccion.setLocation(Comunes.centrarDialog(subDisciplinaEleccion));
        subDisciplinaEleccion.setVisible(true);
        if (subDisciplinaEleccion.getSubDisciplinaCientifica() != null) {
            if (!subDisciplinasCientificas.contains(subDisciplinaEleccion.getSubDisciplinaCientifica())) {
                subDisciplinasCientificas.add(subDisciplinaEleccion.getSubDisciplinaCientifica());
                cargarSubDisciplinasCientificas();
            }
        }
    }

    private void agregarNuevaUnidadAcademica() {
        diagUnidadAcademica unidadAcademicaAlta = new diagUnidadAcademica(null, true, "Alta");
        unidadAcademicaAlta.setLocation(Comunes.centrarDialog(unidadAcademicaAlta));
        unidadAcademicaAlta.setVisible(true);
        cargarUnidadesAcademicas();
        if (unidadAcademicaAlta.getUnidadAcademicaCreada() != null) {
            cboUnidadAcademica.setSelectedItem(unidadAcademicaAlta.getUnidadAcademicaCreada());
        }

    }

    private void cambiarProgramaSeleccionado() {
        if (cboPrograma.getSelectedIndex() > 0) {
            programa = (Programa) cboPrograma.getSelectedItem();
        } else {
            programa = null;
        }
    }

    private void cambiarEntidadEvaluadoraSeleccionada() {
        if (cboEntidadEvaluadora.getSelectedIndex() > 0) {
            entidadEvaluadora = (EntidadEvaluadora) cboEntidadEvaluadora.getSelectedItem();
        } else {
            entidadEvaluadora = null;
        }
    }

    private void agregarNuevaLineaInvestigacion() {
        diagLineaInvestigacion lineaInvestigacionAlta = new diagLineaInvestigacion(null, true, "Alta");
        lineaInvestigacionAlta.setLocation(Comunes.centrarDialog(lineaInvestigacionAlta));
        lineaInvestigacionAlta.setVisible(true);
        cargarLineasInvestigacion();
        if (lineaInvestigacionAlta.getLineaInvestigacionCreada() != null) {
            cboLineaInvestigacion.setSelectedItem(lineaInvestigacionAlta.getLineaInvestigacionCreada());
        }
    }

    private void cambiarUnidadEjecutoraSeleccionada() {
        if (cboUnidadEjecutora.getSelectedIndex() > 0) {
            unidadEjecutora = (UnidadEjecutora) cboUnidadEjecutora.getSelectedItem();
        } else {
            unidadEjecutora = null;
        }
    }

    private void agregarNuevaUnidadEjecutora() {
        diagUnidadEjecutora unidadEjecutoraAlta = new diagUnidadEjecutora(null, true, "Alta");
        unidadEjecutoraAlta.setLocation(Comunes.centrarDialog(unidadEjecutoraAlta));
        unidadEjecutoraAlta.setVisible(true);
        cargarUnidadesEjecutoras();
        if (unidadEjecutoraAlta.getUnidadEjecutoraCreada() != null) {
            cboUnidadEjecutora.setSelectedItem(unidadEjecutoraAlta.getUnidadEjecutoraCreada());
        }
    }

    private void agregarNuevaProrroga() {
        diagProrroga prorrogaNueva = new diagProrroga(null, true, "Alta");
        prorrogaNueva.setLocation(Comunes.centrarDialog(prorrogaNueva));
        prorrogaNueva.setVisible(true);
        if (prorrogaNueva.getProrrogaCreada() != null) {
            prorrogas.add(prorrogaNueva.getProrrogaCreada());
            cargarProrrogas();
        }
    }

    private void cargarProrrogas() {
        Comunes.cargarJList(jListProrrogas, prorrogas);
    }

    private void agregarNuevaFinanciacion() {
        diagFinanciacion diagFinanciacion = new diagFinanciacion(null, true, proyectoVinculacion, usuario);
        diagFinanciacion.setLocation(Comunes.centrarDialog(diagFinanciacion));
        diagFinanciacion.setVisible(true);
        if (diagFinanciacion.getFinanciacionCreada() != null) {

            financiaciones.add(diagFinanciacion.getFinanciacionCreada());

            cargarFinanciaciones();
        }
    }

    private void cargarFinanciaciones() {

        cargarTextFieldsEconomicos();
        Comunes.cargarJList(jListFinanciaciones, financiaciones);
        Comunes.cargarJList(jListFinanciamientoInformes, financiaciones);

    }
    private void cargarTextFieldsEconomicos(){
        Float valorContraparte = new Float("0");
                        Float valorMyncit = new Float("0");
                        for (Financiacion f : financiaciones) {
                            String nombre = f.getDescripcion();
                            if (nombre.equals("PFIP")) {
                                /* Clases del pfip */
                                FinanciacionPfip pf = (FinanciacionPfip) f;
                                
                                if(pf.getBienesdecapitaladquirir() != null){
                                List<BienDeCapitalAAdquirir> listaBienDeCapitalAAdquirirs = pf.getBienesdecapitaladquirir();
                                for (BienDeCapitalAAdquirir b : listaBienDeCapitalAAdquirirs) {
                                    valorContraparte = valorContraparte + b.getParteContraparte();
                                    valorMyncit = valorMyncit + b.getParteMincyt();
                                } //Biendecaptailaadquirir
                                }
                                if(pf.getConsultorias() != null){
                                    
                                
                                List<Consultoria> consultorias = pf.getConsultorias();
                                for (Consultoria c : consultorias) {
                                    valorMyncit = valorMyncit  + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                                if(pf.getMateriales() != null){
                                List<Material> materiales = pf.getMateriales();
                                for (Material c : materiales) {
                                    valorMyncit = valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                                if(pf.getRecursosHumanosAdquirir() != null){
                                List<RecursoHumanoAdquirir> recursoHumanoAdquirirs = pf.getRecursosHumanosAdquirir();
                                for (RecursoHumanoAdquirir c : recursoHumanoAdquirirs) {
                                    valorMyncit = valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                                
                                if(pf.getOtroRecursoAdquirir() != null){
                                List<OtroRecursoAdquirir> otroRecursoAdquirirs = pf.getOtroRecursoAdquirir();
                                for (OtroRecursoAdquirir c : otroRecursoAdquirirs) {
                                    valorMyncit = valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                            } else if (nombre.equals("DETEM")) {
                                /* Clases del pfip */
                                FinanciacionDetem pf = (FinanciacionDetem) f;
                                if(pf.getBienesdecapitaladquirir() != null){
                                
                                List<BienDeCapitalAAdquirir> listaBienDeCapitalAAdquirirs = pf.getBienesdecapitaladquirir();
                                for (BienDeCapitalAAdquirir b : listaBienDeCapitalAAdquirirs) {
                                    valorContraparte = valorContraparte + b.getParteContraparte();
                                    valorMyncit =  valorMyncit + b.getParteMincyt();
                                } //Biendecaptailaadquirir
                                }
                                if(pf.getConsultorias() != null){
                                
                                List<Consultoria> consultorias = pf.getConsultorias();
                                for (Consultoria c : consultorias) {
                                    valorMyncit =  valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                                if(pf.getMateriales() != null){
                                List<Material> materiales = pf.getMateriales();
                                for (Material c : materiales) {
                                    valorMyncit = valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }   
                                if(pf.getRecursosHumanosAdquirir() != null){
                                List<RecursoHumanoAdquirir> recursoHumanoAdquirirs = pf.getRecursosHumanosAdquirir();
                                for (RecursoHumanoAdquirir c : recursoHumanoAdquirirs) {
                                    valorMyncit =  valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                                if(pf.getOtroRecursoAdquirir() != null){
                                List<OtroRecursoAdquirir> otroRecursoAdquirirs = pf.getOtroRecursoAdquirir();
                                for (OtroRecursoAdquirir c : otroRecursoAdquirirs) {
                                    valorMyncit = valorMyncit + c.getFinanciaM();
                                    valorContraparte = valorContraparte + c.getFinanciaC();
                                }
                                }
                            } 

                        } // for financiacion in financiacinoes

                        jtfFinanciarUnca.setText(String.valueOf(valorMyncit));
                        jtfFinanciarContraparte.setText(String.valueOf(valorContraparte));
                        
    }
    private void asignarDatos(){
         if (participaciones.size() > 0) {

                    agregarProyectoAParticipacion();
                    proyectoVinculacion.setParticipaciones(participaciones);
                } else {
                    proyectoVinculacion.setParticipaciones(null);
                }
                //setearResoluciones
                //Lo seteamos cuando agregamos una nueva

                if (!tfCodigoProyecto.getText().equals("")) {
                    proyectoVinculacion.setCodigo(tfCodigoProyecto.getText());
                } else {
                    proyectoVinculacion.setCodigo(null);
                }
                if (!tfNroConvenio.getText().equals("")) {
                    proyectoVinculacion.setNroConvenio(tfNroConvenio.getText());
                } else {
                    proyectoVinculacion.setNroConvenio(null);
                }
                if (!taTitulo.getText().equals("")) {
                    proyectoVinculacion.setTitulo(taTitulo.getText());
                } else {
                    proyectoVinculacion.setTitulo(null);
                }
                if (unidadAcademica != null) {
                    proyectoVinculacion.setUnidadAcademica(unidadAcademica);
                } else {
                    proyectoVinculacion.setUnidadAcademica(null);
                }
                if (IntegerValidator.getInstance().isValid(tfDuracionTeorica.getText())) {
                    proyectoVinculacion.setDuracionTeorica(Integer.parseInt(tfDuracionTeorica.getText()));
                } else {
                    proyectoVinculacion.setDuracionTeorica(0);
                }
                if (dateFechaInicio.getDate() != null) {
                    proyectoVinculacion.setFechaInicio(dateFechaInicio.getDate());
                } else {
                    proyectoVinculacion.setFechaInicio(null);
                }
                if (dateFechaFinalizacion.getDate() != null) {
                    proyectoVinculacion.setFechaFinalizacion(dateFechaFinalizacion.getDate());
                } else {
                    proyectoVinculacion.setFechaFinalizacion(null);
                }
                if (prorrogas.size() > 0) {
                    proyectoVinculacion.setProrrogas(prorrogas);
                } else {
                    proyectoVinculacion.setProrrogas(null);
                }
                if (lineaInvestigacion != null) {
                    proyectoVinculacion.setLineaInvestigacion(lineaInvestigacion);
                } else {
                    proyectoVinculacion.setLineaInvestigacion(null);
                }
                if (unidadEjecutora != null) {
                    proyectoVinculacion.setUnidadEjecutora(unidadEjecutora);
                } else {
                    proyectoVinculacion.setUnidadEjecutora(null);
                }

                if (entidadEvaluadora != null) {
                    proyectoVinculacion.setEntidadEvaluadora(entidadEvaluadora);
                } else {
                    proyectoVinculacion.setEntidadEvaluadora(null);
                }
                if (programa != null) {
                    proyectoVinculacion.setPrograma(programa);
                } else {
                    proyectoVinculacion.setPrograma(null);
                }

                if (subDisciplinasCientificas.size() > 0) {
                    proyectoVinculacion.setSubDisciplinasCientificas(subDisciplinasCientificas);
                } else {
                    proyectoVinculacion.setSubDisciplinasCientificas(null);
                }
                if (!taResumen.getText().equals("")) {
                    proyectoVinculacion.setResumen(taResumen.getText());
                } else {
                    proyectoVinculacion.setResumen(null);
                }
                if (!jTAconocimiento.getText().equals("")) {
                    proyectoVinculacion.setTransferenciaConocimiento(jTAconocimiento.getText());
                } else {
                    proyectoVinculacion.setTransferenciaConocimiento(null);
                }
                if (palabrasClaves.size() > 0) {
                    proyectoVinculacion.setPalabrasClaves(palabrasClaves);
                } else {
                    proyectoVinculacion.setPalabrasClaves(null);
                }
                if (!taObservaciones.getText().equals("")) {
                    proyectoVinculacion.setObservaciones(taObservaciones.getText());
                } else {
                    proyectoVinculacion.setObservaciones(null);
                }
                if (financiaciones.size() > 0) {
                    proyectoVinculacion.setFinanciaciones(financiaciones);
                } else {
                    proyectoVinculacion.setFinanciaciones(null);

                }
                if (archivosDigitales.size() > 0) {
                    proyectoVinculacion.setArchivosDigitales(archivosDigitales);
                } else {
                    proyectoVinculacion.setArchivosDigitales(null);

                }
                if (notasExternas.size() > 0) {
                    proyectoVinculacion.setNotasExternas(notasExternas);
                } else {
                    proyectoVinculacion.setNotasExternas(null);
                }

                if (notasInternas.size() > 0) {
                    proyectoVinculacion.setNotasInternas(notasInternas);
                } else {
                    proyectoVinculacion.setNotasInternas(null);
                }
                if(!jtfResoMinisterial.getText().equals("")){
                    proyectoVinculacion.setResolucionMinisterial(jtfResoMinisterial.getText());
                            } else{
                    proyectoVinculacion.setResolucionMinisterial(null);
                    
                }
                
                if(!jtfResoRectoral.getText().equals("")){
                    proyectoVinculacion.setResolucionRectoral(jtfResoRectoral.getText());
                            } else{
                    proyectoVinculacion.setResolucionRectoral(null);
                    
                }
    }
    private void aceptar() {
        if (validar()) {
            Operacion operacion = new Operacion();
            switch(tipoOperacion){
                case "Alta":
                    proyectoVinculacion = new ProyectoVinculacion();
                    asignarDatos();
                try{
                ProyectoVinculacionFacade.getInstance().alta(proyectoVinculacion);
                
                operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                operacion.setOperacion("Alta de proyectoVinculacion de Vinculación");
                operacion.setUsuario(usuario);
                OperacionFacade.getInstance().alta(operacion);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error Modificando proyectoVinculacion\n " + ex);
                }
                this.dispose();
                break;
                case "Modificación":
                    asignarDatos();
                try{
                    ProyectoVinculacionFacade.getInstance().modificar(proyectoVinculacion);
                    operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                    operacion.setOperacion("Modificación de proyectoVinculacion");
                    operacion.setUsuario(usuario);
                    OperacionFacade.getInstance().alta(operacion);
                    System.out.println("Modificado con éxito");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error Modificando proyectoVinculacion\n " + ex);
                }

            }
}
}
           
              

    private boolean validar() {
        boolean flag = false;
        if (!ProyectoVinculacionFacade.getInstance().existeCodigo(tfCodigoProyecto.getText()) || proyectoVinculacion.getCodigo().equals(tfCodigoProyecto.getText())) {
            if (IntegerValidator.getInstance().isValid(tfDuracionTeorica.getText())) {
                if (!taTitulo.getText().equals("")) {
                    flag = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un título de proyecto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Duración teórica incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ProyectoVinculacion proyectoConMismoCodigo = ProyectoVinculacionFacade.getInstance().buscarPorCodigo(tfCodigoProyecto.getText());
            JOptionPane.showMessageDialog(null, "Ya existe un proyecto con el código " + tfCodigoProyecto.getText() + ": "
                    + proyectoConMismoCodigo.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            tfCodigoProyecto.requestFocus();

        }
        return flag;
    }

    private void cargarLineasInvestigacion() {
        Comunes.cargarJComboConBlanco(cboLineaInvestigacion, LineaInvestigacionFacade.getInstance().getTodosLineaInvestigacion());

    }

    private void modificarFinanciacion() {
        try {
            if (jListFinanciaciones.getSelectedValue() != null) {
                Financiacion f = (Financiacion) jListFinanciaciones.getSelectedValue();
                switch (f.getDescripcion()) {
                    case "PFIP":
                        try {
                            diagPfip pfip = new diagPfip(null, true, "Modificacion", (FinanciacionPfip) jListFinanciaciones.getSelectedValue(),
                                    usuario, proyectoVinculacion);

                            pfip.setLocation(Comunes.centrarDialog(pfip));
                            pfip.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        cargarFinanciaciones();
                        break;
                    case "DETEM":
                        try {

                            diagDetem detem = new diagDetem(null, true, "Modificacion", (FinanciacionDetem) jListFinanciaciones.getSelectedValue(),
                                    usuario, proyectoVinculacion);
                            detem.setLocation(Comunes.centrarDialog(detem));
                            detem.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        cargarFinanciaciones();
                        break;
                    case "PICTO":
                        try {
                            diagPicto picto = new diagPicto(null, true, "Modificacion", usuario, (FinanciacionPICTO) jListFinanciaciones.getSelectedValue(),
                                    proyectoVinculacion);
                            picto.setLocation(Comunes.centrarDialog(picto));
                            picto.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        cargarFinanciaciones();
                        break;
                    case "PICT":

                        try {
                            diagPict pict = new diagPict(null, true, "Modificacion", usuario, (FinanciacionPict) jListFinanciaciones.getSelectedValue(),
                                    proyectoVinculacion);
                            pict.setLocation(Comunes.centrarDialog(pict));
                            pict.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        cargarFinanciaciones();
                        break;

                    default:
                        try {
                            diagGenerica2 generica = new diagGenerica2(null, true, "Modificacion", (FinanciacionGenerica) jListFinanciaciones.getSelectedValue(), usuario, proyectoVinculacion);
                            generica.setLocationRelativeTo(this);
                            generica.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        cargarFinanciaciones();
                        break;
                }
            }

        } catch (NullPointerException ex) {
            //le agregue este try catch por que da una excepcion si le da al boton
            //modificar sin antes haber creado el proyecto vinculacion salta esta excepcion.
        }
    }

    private void agregarPalabraClave() {
        palabrasClaves.add(JOptionPane.showInputDialog("Inserte una palabra clave"));
        Comunes.cargarJList(jListPalabrasClaves, palabrasClaves);
    }

    private void borrarPalabraClave() {
        if (jListPalabrasClaves.getSelectedIndex() != -1) {
            palabrasClaves.remove((String) jListPalabrasClaves.getSelectedValue());
            Comunes.cargarJList(jListPalabrasClaves, palabrasClaves);
        }
    }

    private void agregarNuevaParticipacion() {
        diagParticipacion participacion = new diagParticipacion(null, true, usuario, "Alta");
        participacion.setLocation(Comunes.centrarDialog(participacion));
        participacion.setVisible(true);

        if (participacion.getParticipacionVinculacion() != null) {

            participaciones.add(participacion.getParticipacionVinculacion());
            cargarParticipaciones();

        }

    }

    private void modificarParticipacion() {
        if (jListParticipaciones.getSelectedIndex() != -1) {
      
        ParticipacionVinculacion p = (ParticipacionVinculacion)jListParticipaciones.getSelectedValue();
        diagParticipacion participacion = new diagParticipacion(null, true, usuario, "Modificacion",(ParticipacionVinculacion) jListParticipaciones.getSelectedValue());
        participacion.setLocation(Comunes.centrarDialog(participacion));
        participacion.setVisible(true);

        if (participacion.getParticipacionVinculacion() != null) {
            participaciones.remove(p);
            participaciones.add(participacion.getParticipacionVinculacion());
            cargarParticipaciones();

        }
  }
        else{
             JOptionPane.showMessageDialog(null, "Debe seleccionar una participación");

        }
    }

    private void cargarParticipaciones() {
        Comunes.cargarJList(jListParticipaciones, participaciones);
    }

    private void agregarProyectoAParticipacion() {
        for (ParticipacionVinculacion p : participaciones) {
            p.setProyectoVinculacion(proyectoVinculacion);
        }
    }

    private void eliminarParticipacion() {

        if (jListParticipaciones.getSelectedIndex() != -1) {

            participaciones.remove(jListParticipaciones.getSelectedValue());

            //     ProyectoVinculacionFacade.getInstance().eliminarParticipacionVinculacion((ParticipacionVinculacion) jListParticipaciones.getSelectedValue());
        }
        cargarParticipaciones();
    }

    private void quitarFinanciacion() {
        if (jListFinanciaciones.getSelectedIndex() != -1) {
            financiaciones.remove(jListFinanciaciones.getSelectedValue());
            cargarFinanciaciones();

        }

    }

    /**
     * *******************************************************************************
     */
    private void inicializarComponentesProduccion() {
        modeloTablaLibros = new ModeloTablaNoEditable();
        modeloTablaCapitulos = new ModeloTablaNoEditable();
        modeloTablaArticuloRevista = new ModeloTablaNoEditable();
        modeloTablaCongreso = new ModeloTablaNoEditable();
        modeloTablaContrato = new ModeloTablaNoEditable();
        modeloTablaPIntelectual = new ModeloTablaNoEditable();
        modeloTablaPIndustrial = new ModeloTablaNoEditable();
        modeloTablaFormacionRRHH = new ModeloTablaNoEditable();

        cargarEncabezadosTablaLibros(modeloTablaLibros);
        cargarEncabezadosTablaCapitulos(modeloTablaCapitulos);
        cargarEncabezadosTablaArticuloRevista(modeloTablaArticuloRevista);
        cargarEncabezadosTablaCongresos(modeloTablaCongreso);
        cargarEncabezadosTablaContratos(modeloTablaContrato);
        cargarEncabezadosTablaPIntelectual(modeloTablaPIntelectual);
        cargarEncabezadosTablaPIndustrial(modeloTablaPIndustrial);
        cargarEncabezadosTablaFormacionRRHH(modeloTablaFormacionRRHH);

        //    Comunes.autoAjusteTextArea(taProyecto);
        configurarTabla(tblLibros);
        configurarTabla(tblCapitulos);
        configurarTabla(tblArticulosRevistas);
        configurarTabla(tblCongresos);
        configurarTabla(tblContratos);
        configurarTabla(tblPIntelectual);
        configurarTabla(tblPIndustrial);
        configurarTabla(tblFormacionRRHH);

        cargarLibros();
        cargarCapitulos();
        cargarArticulosRevistas();
        cargarCongresos();
        cargarContratos();
        cargarPIntelectuales();
        cargarPIndustriales();
        cargarFormacionRRHH();
    }

    private void agregarLibro() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoLibro resultadoLibro = new diagProyectoResultadoLibro(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoLibro.setLocation(Comunes.centrarDialog(resultadoLibro));
            resultadoLibro.setVisible(true);
            if (resultadoLibro.getLibro() != null) {
                cargarLibro(resultadoLibro.getLibro());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void cargarLibros() {

        modeloTablaLibros = new ModeloTablaNoEditable();
        cargarEncabezadosTablaLibros(modeloTablaLibros);
        if (proyectoVinculacion != null) {
            for (Libro libro : PublicacionFacade.getInstance().getLibrosProyectoVinculacion(proyectoVinculacion)) {
                cargarLibro(libro);

            }
            tblLibros.setModel(modeloTablaLibros);
        }
    }

    private void cargarLibro(Libro libro) {

        Object[] fila = new Object[4];
        fila[0] = libro.getId();
        fila[1] = libro.getTitulo();
        StringBuilder strInvestigadores = new StringBuilder();
        for (Investigador investigador : libro.getInvestigadores()) {
            strInvestigadores.append(investigador.toString()).append(" / ");
        }
        fila[2] = strInvestigadores;
        fila[3] = libro.getEditor();
        modeloTablaLibros.addRow(fila);

    }

    private void cargarEncabezadosTablaLibros(DefaultTableModel modeloTabla) {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Autores");
        modeloTabla.addColumn("Editor");
        tblLibros.setModel(modeloTabla);

    }

    private void configurarTabla(JTable tblProyectos) {
        JViewport scroll = (JViewport) tblProyectos.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblProyectos.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblProyectos.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (1 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    anchoColumna = (5 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
            tblProyectos.setColumnModel(modeloColumna);
        }
        tblProyectos.getTableHeader().setFont(new java.awt.Font("Dialog",
                java.awt.Font.PLAIN, 10));
        tblProyectos.getTableHeader().setBackground(java.awt.Color.WHITE);
        tblProyectos.getTableHeader().setForeground(Color.BLACK);
        //Si le queremos cambiar el tamaño a la tablita
        tblProyectos.setFont(new java.awt.Font("Dialog",
                java.awt.Font.PLAIN, 10));
    }

    private void agregarCapitulo() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoCapitulo resultadoCapitulo = new diagProyectoResultadoCapitulo(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoCapitulo.setLocation(Comunes.centrarDialog(resultadoCapitulo));
            resultadoCapitulo.setVisible(true);
            if (resultadoCapitulo.getCapitulo() != null) {
                cargarCapitulo(resultadoCapitulo.getCapitulo());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void cargarCapitulos() {
        if (proyectoVinculacion != null) {
            for (Publicacion publicacion : PublicacionFacade.getInstance().getCapitulosLibro(proyectoVinculacion)) {
                if (publicacion.getClass().getSimpleName().equals("CapituloLibro")) {
                    cargarCapitulo((CapituloLibro) publicacion);
                }
            }
        }
    }

    private void cargarCapitulo(CapituloLibro capitulolibro) {
        Object[] fila = new Object[5];
        fila[0] = capitulolibro.getId();
        fila[1] = capitulolibro.getTitulo();
        fila[2] = capitulolibro.getNombreLibro();
        StringBuilder strInvestigadores = new StringBuilder();
        for (Investigador investigador : capitulolibro.getInvestigadores()) {
            strInvestigadores.append(investigador.toString()).append(" / ");
        }
        fila[3] = strInvestigadores;
        fila[4] = capitulolibro.getEditor();
        modeloTablaCapitulos.addRow(fila);
    }

    private void cargarEncabezadosTablaCapitulos(ModeloTablaNoEditable modeloTablaCapitulos) {
        modeloTablaCapitulos.addColumn("Codigo");
        modeloTablaCapitulos.addColumn("Capitulo");
        modeloTablaCapitulos.addColumn("Libro");
        modeloTablaCapitulos.addColumn("Autores");
        modeloTablaCapitulos.addColumn("Editor");
        tblCapitulos.setModel(modeloTablaCapitulos);

    }

//    private void buscarProyecto() {
//        diagProyectoBusquedaSimple selecproyecto = new diagProyectoBusquedaSimple(null, true);
//        selecproyecto.setLocation(Comunes.centrarDialog(selecproyecto));
//        selecproyecto.setVisible(true);
//        if (selecproyecto.getProyecto() != null) {
//            proyecto = selecproyecto.getProyecto();
//      //      taProyecto.setText(proyecto.toString());
//
//
//        }
//
//    }
    private void cargarArticulosRevistas() {
        if (proyectoVinculacion != null) {
            for (Publicacion publicacion : PublicacionFacade.getInstance().getArticuloRevista(proyectoVinculacion)) {
                if (publicacion.getClass().getSimpleName().equals("ArticuloRevista")) {
                    cargarArticuloRevista((ArticuloRevista) publicacion);
                }
            }
        }
    }

    private void cargarArticuloRevista(ArticuloRevista articuloRevista) {
        Object[] fila = new Object[5];
        fila[0] = articuloRevista.getId();
        fila[1] = articuloRevista.getTitulo();
        fila[2] = articuloRevista.getNombreRevista();
        StringBuilder strInvestigadores = new StringBuilder();
        for (Investigador investigador : articuloRevista.getInvestigadores()) {
            strInvestigadores.append(investigador.toString()).append(" / ");
        }
        fila[3] = strInvestigadores;
        fila[4] = articuloRevista.getAnioEdicion();
        modeloTablaArticuloRevista.addRow(fila);
    }

    private void cargarEncabezadosTablaArticuloRevista(ModeloTablaNoEditable modeloTablaArticuloRevista) {
        modeloTablaArticuloRevista.addColumn("Codigo");
        modeloTablaArticuloRevista.addColumn("Nombre articulo");
        modeloTablaArticuloRevista.addColumn("Nombre Revista");
        modeloTablaArticuloRevista.addColumn("Autores");
        modeloTablaArticuloRevista.addColumn("Año");
        tblArticulosRevistas.setModel(modeloTablaArticuloRevista);
    }

    private void agregarArticuloRevista() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoArticuloRevista resultadoArticuloRevista = new diagProyectoResultadoArticuloRevista(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoArticuloRevista.setLocation(Comunes.centrarDialog(resultadoArticuloRevista));
            resultadoArticuloRevista.setVisible(true);
            if (!resultadoArticuloRevista.getArticuloRevista().getTitulo().isEmpty()) {
                cargarArticuloRevista(resultadoArticuloRevista.getArticuloRevista());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void agregarCongreso() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoCongreso resultadoCongreso = new diagProyectoResultadoCongreso(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoCongreso.setLocation(Comunes.centrarDialog(resultadoCongreso));
            resultadoCongreso.setVisible(true);
            if (resultadoCongreso.getCongreso() != null) {
                cargarCongreso(resultadoCongreso.getCongreso());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void cargarCongreso(Congreso congreso) {

        Object[] fila = new Object[5];
        fila[0] = congreso.getId();
        fila[1] = congreso.getTitulo();
        fila[2] = congreso.getNombreEvento();
        StringBuilder strInvestigadores = new StringBuilder();
        for (Investigador investigador : congreso.getInvestigadores()) {
            strInvestigadores.append(investigador.toString()).append(" / ");
        }
        fila[3] = strInvestigadores;
        fila[4] = congreso.getLugar();
        modeloTablaCongreso.addRow(fila);
    }

    private void cargarEncabezadosTablaCongresos(ModeloTablaNoEditable modeloTablaCongreso) {
        modeloTablaCongreso.addColumn("Codigo");
        modeloTablaCongreso.addColumn("Congreso");
        modeloTablaCongreso.addColumn("Evento");
        modeloTablaCongreso.addColumn("Participantes");
        modeloTablaCongreso.addColumn("Lugar");
        tblCongresos.setModel(modeloTablaCongreso);
    }

    private void cargarEncabezadosTablaContratos(ModeloTablaNoEditable modeloTablaContrato) {
        modeloTablaContrato.addColumn("Codigo");
        modeloTablaContrato.addColumn("Objeto");
        modeloTablaContrato.addColumn("Tipo Contrato");
        modeloTablaContrato.addColumn("Destinatarios");
        modeloTablaContrato.addColumn("Fecha");
        modeloTablaContrato.addColumn("Monto");
        tblContratos.setModel(modeloTablaContrato);
    }

    private void cargarCongresos() {
        modeloTablaCongreso = new ModeloTablaNoEditable();
        cargarEncabezadosTablaCongresos(modeloTablaCongreso);
        configurarTabla(tblCongresos);
        if (proyectoVinculacion != null) {
            for (Publicacion publicacion : PublicacionFacade.getInstance().getCongresos(proyectoVinculacion)) {
                if (publicacion.getClass().getSimpleName().equals("Congreso")) {
                    cargarCongreso((Congreso) publicacion);
                }
            }
        }
    }

    private void editarCongreso() {
        congreso = new Congreso();

        if (proyectoVinculacion != null) {
            if (tblCongresos.getSelectedRow() != -1) {
                congreso = (Congreso) PublicacionFacade.getInstance().buscar((Long) tblCongresos.getValueAt(tblCongresos.getSelectedRow(), 0));
                diagProyectoResultadoCongreso resultadoCongreso = new diagProyectoResultadoCongreso(null, true, "Modificación", proyectoVinculacion, congreso, usuario);
                resultadoCongreso.setLocation(Comunes.centrarDialog(resultadoCongreso));
                resultadoCongreso.setVisible(true);
                cargarCongresos();
                inicializarComponentesProduccion();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un congreso");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }
    }

    private void cargarContrato(Contrato contrato) {

        Object[] fila = new Object[6];
        fila[0] = contrato.getId();
        fila[1] = contrato.getObjeto();
        fila[2] = contrato.getTipoContrato();
        fila[3] = contrato.getDestinatarios();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fila[4] = formato.format(contrato.getFechaContrato());
        } catch (java.lang.NullPointerException ex) {
            fila[4] = "-";
        }
        fila[5] = contrato.getMontoObtenido();
        modeloTablaContrato.addRow(fila);
    }

    private void cargarContratos() {
        modeloTablaContrato = new ModeloTablaNoEditable();
        cargarEncabezadosTablaContratos(modeloTablaContrato);
        configurarTabla(tblContratos);
        if (proyectoVinculacion != null) {
            for (Contrato contrato1 : ContratoFacade.getInstance().getContratosProyecto(proyectoVinculacion)) {

                cargarContrato(contrato1);
            }

        }
    }

    private void agregarContrato() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoContrato resultadoContrato = new diagProyectoResultadoContrato(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoContrato.setLocation(Comunes.centrarDialog(resultadoContrato));
            resultadoContrato.setVisible(true);
            if (resultadoContrato.getContrato() != null) {
                cargarContrato(resultadoContrato.getContrato());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void editarContrato() {
        contrato = new Contrato();

        if (proyectoVinculacion != null) {
            if (tblContratos.getSelectedRow() != -1) {
                contrato = (Contrato) ContratoFacade.getInstance().buscar((Long) tblContratos.getValueAt(tblContratos.getSelectedRow(), 0));
                diagProyectoResultadoContrato resultadoContrato = new diagProyectoResultadoContrato(null, true, "Modificación", proyectoVinculacion, contrato, usuario);
                resultadoContrato.setLocation(Comunes.centrarDialog(resultadoContrato));
                resultadoContrato.setVisible(true);
                if (resultadoContrato.getContrato() != null) {
                    cargarContratos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un contrato");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }
    }

    private void cargarPIntelectuales() {
        modeloTablaPIntelectual = new ModeloTablaNoEditable();
        cargarEncabezadosTablaPIntelectual(modeloTablaPIntelectual);
        configurarTabla(tblPIntelectual);
        if (proyectoVinculacion != null) {
            for (Propiedad propiedad : PropiedadFacade.getInstance().getIntelectualesProyecto(proyectoVinculacion)) {
                if (propiedad.getClass().getSimpleName().equals("Intelectual")) {
                    cargarPIntelectual((Intelectual) propiedad);
                }

            }
        }
    }

    private void cargarEncabezadosTablaPIntelectual(ModeloTablaNoEditable modeloTablaPIntelectual) {
        modeloTablaPIntelectual.addColumn("Codigo");
        modeloTablaPIntelectual.addColumn("Titulo");
        modeloTablaPIntelectual.addColumn("Tipo de Registro");
        modeloTablaPIntelectual.addColumn("Titulares");
        modeloTablaPIntelectual.addColumn("Integrantes");
        modeloTablaPIntelectual.addColumn("País");
        modeloTablaPIntelectual.addColumn("Fecha");
        tblPIntelectual.setModel(modeloTablaPIntelectual);
    }

    private void cargarPIntelectual(Intelectual intelectual) {
        Object[] fila = new Object[7];
        fila[0] = intelectual.getId();
        fila[1] = intelectual.getTitulo();
        fila[2] = intelectual.getTipoRegistroIntelectual();
        fila[3] = intelectual.getTitulares();
        StringBuilder strInvestigadores = new StringBuilder();
        for (Investigador investigador : intelectual.getInvestigadores()) {
            strInvestigadores.append(investigador.toString()).append(" / ");
        }
        fila[4] = strInvestigadores;
        fila[5] = intelectual.getPais();
        fila[6] = intelectual.getFecha();
        modeloTablaPIntelectual.addRow(fila);
    }

    private void agregarPIntelectual() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoPIntelectual resultadoPIntelectual = new diagProyectoResultadoPIntelectual(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoPIntelectual.setLocation(Comunes.centrarDialog(resultadoPIntelectual));
            resultadoPIntelectual.setVisible(true);
            if (resultadoPIntelectual.getpIntelectual() != null) {
                cargarPIntelectual(resultadoPIntelectual.getpIntelectual());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void editarPintelectual() {
        pIntelectual = new Intelectual();
        if (proyectoVinculacion != null) {
            if (tblPIntelectual.getSelectedRow() != -1) {
                pIntelectual = (Intelectual) PropiedadFacade.getInstance().buscar((Long) tblPIntelectual.getValueAt(tblPIntelectual.getSelectedRow(), 0));
                diagProyectoResultadoPIntelectual resultadoPIntelectual = new diagProyectoResultadoPIntelectual(null, true, "Modificación", proyectoVinculacion, pIntelectual, usuario);
                resultadoPIntelectual.setLocation(Comunes.centrarDialog(resultadoPIntelectual));
                resultadoPIntelectual.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para editar");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void cargarPIndustriales() {
        modeloTablaPIndustrial = new ModeloTablaNoEditable();
        cargarEncabezadosTablaPIndustrial(modeloTablaPIndustrial);
        configurarTabla(tblPIndustrial);
        if (proyectoVinculacion != null) {
            for (Propiedad propiedad : PropiedadFacade.getInstance().getIndustrialesProyectoVinculacion(proyectoVinculacion)) {
                if (propiedad.getClass().getSimpleName().equals("Industrial")) {
                    cargarPIndustrial((Industrial) propiedad);
                }

            }
        }
    }

    private void cargarEncabezadosTablaPIndustrial(ModeloTablaNoEditable modeloTablaPIndustrial) {
        modeloTablaPIndustrial.addColumn("Codigo");
        modeloTablaPIndustrial.addColumn("Titulo");
        modeloTablaPIndustrial.addColumn("Tipo de Registro");
        modeloTablaPIndustrial.addColumn("Titulares");
        modeloTablaPIndustrial.addColumn("Integrantes");
        modeloTablaPIndustrial.addColumn("País");
        modeloTablaPIndustrial.addColumn("Fecha");
        tblPIndustrial.setModel(modeloTablaPIndustrial);
    }

    private void cargarPIndustrial(Industrial industrial) {
        Object[] fila = new Object[7];
        fila[0] = industrial.getId();
        fila[1] = industrial.getTitulo();
        fila[2] = industrial.getTipoRegistroIndustrial();
        fila[3] = industrial.getTitulares();
        StringBuilder strInvestigadores = new StringBuilder();
        for (Investigador investigador : industrial.getInvestigadores()) {
            strInvestigadores.append(investigador.toString()).append(" / ");
        }
        fila[4] = strInvestigadores;
        fila[5] = industrial.getPais();
        fila[6] = industrial.getFecha();
        modeloTablaPIndustrial.addRow(fila);
    }

    private void agregarPIndustrial() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoPIndustrial resultadoPIndustrial = new diagProyectoResultadoPIndustrial(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoPIndustrial.setLocation(Comunes.centrarDialog(resultadoPIndustrial));
            resultadoPIndustrial.setVisible(true);
            if (resultadoPIndustrial.getpIndustrial() != null) {
                cargarPIndustrial(resultadoPIndustrial.getpIndustrial());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void editarPIndustrial() {
        pIndustrial = new Industrial();
        if (proyectoVinculacion != null) {
            if (tblPIndustrial.getSelectedRow() != -1) {
                pIndustrial = (Industrial) PropiedadFacade.getInstance().buscar((Long) tblPIndustrial.getValueAt(tblPIndustrial.getSelectedRow(), 0));
                diagProyectoResultadoPIndustrial resultadoPIndustrial = new diagProyectoResultadoPIndustrial(null, true, "Modificación", proyectoVinculacion, pIndustrial, usuario);
                resultadoPIndustrial.setLocation(Comunes.centrarDialog(resultadoPIndustrial));
                resultadoPIndustrial.setVisible(true);
                if (resultadoPIndustrial.getpIndustrial() != null) {
                    inicializarComponentesProduccion();
                    cargarPIndustriales();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para editar");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void editarLibro() {
        libro = new Libro();

        if (proyectoVinculacion != null) {
            if (tblLibros.getSelectedRow() != -1) {
                libro = (Libro) PublicacionFacade.getInstance().buscar((Long) tblLibros.getValueAt(tblLibros.getSelectedRow(), 0));
                diagProyectoResultadoLibro resultadoLibro = new diagProyectoResultadoLibro(null, true, "Modificación", proyectoVinculacion, libro, usuario);
                resultadoLibro.setLocation(Comunes.centrarDialog(resultadoLibro));
                resultadoLibro.setVisible(true);
                inicializarComponentesProduccion();
                cargarLibros();

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un libro");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }
    }

    private void eliminarRegistro(JTable tabla) {
        if (proyectoVinculacion != null) {
            if (tabla.getSelectedRow() != -1) {
                int i = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {

                    PublicacionFacade.getInstance().eliminar((Long) tabla.getValueAt(tabla.getSelectedRow(), 0));
                    inicializarComponentesProduccion();
                    cargarCapitulos();
                    cargarLibros();
                    cargarArticulosRevistas();
                    cargarCongresos();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Proyecto");
        }
    }

    private void eliminarRegistroLibro() {
        if (proyectoVinculacion != null) {
            if (tblLibros.getSelectedRow() != -1) {
                int i = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    if (validarCapitulos()) {
                        PublicacionFacade.getInstance().eliminar((Long) tblLibros.getValueAt(tblLibros.getSelectedRow(), 0));
                        inicializarComponentesProduccion();
                        cargarLibros();

                    } else {
                        JOptionPane.showMessageDialog(null, "Este Libro posee Capitulos \n debe eliminar los mismos "
                                + "para \n poder realizar esta operacion");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Proyecto");
        }
    }

    private void eliminarRegistroContrato() {
        if (proyectoVinculacion != null) {
            if (tblContratos.getSelectedRow() != -1) {
                int i = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {

                    ContratoFacade.getInstance().eliminar((Long) tblContratos.getValueAt(tblContratos.getSelectedRow(), 0));
                    inicializarComponentes();
                    cargarContratos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Proyecto");
        }
    }

    private void eliminarRegistroPropiedad(JTable tblPropiedad) {
        if (proyectoVinculacion != null) {
            if (tblPropiedad.getSelectedRow() != -1) {
                int i = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {

                    PropiedadFacade.getInstance().eliminar((Long) tblPropiedad.getValueAt(tblPropiedad.getSelectedRow(), 0));

                }
                cargarPIntelectuales();
                cargarPIndustriales();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Proyecto");
        }
    }

    private boolean validarCapitulos() {
        Boolean flag;
        Libro lib = (Libro) PublicacionFacade.getInstance().buscar((Long) tblLibros.getValueAt(tblLibros.getSelectedRow(), 0));
        if (lib.getCapitulos().isEmpty()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    private void editarRegistroCapitulo() {
        capituloLibro = new CapituloLibro();

        if (proyectoVinculacion != null) {
            if (tblCapitulos.getSelectedRow() != -1) {
                capituloLibro = (CapituloLibro) PublicacionFacade.getInstance().buscar((Long) tblCapitulos.getValueAt(tblCapitulos.getSelectedRow(), 0));
                diagProyectoResultadoCapitulo resultadoCapitulo = new diagProyectoResultadoCapitulo(null, true, "Modificación", proyectoVinculacion, capituloLibro, usuario);
                resultadoCapitulo.setLocation(Comunes.centrarDialog(resultadoCapitulo));
                resultadoCapitulo.setVisible(true);
                inicializarComponentesProduccion();
                cargarCapitulos();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un capitulo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }
    }

    private void editarRegistroArticuloRevista() {
        articuloRevista = new ArticuloRevista();

        if (proyectoVinculacion != null) {
            if (tblArticulosRevistas.getSelectedRow() != -1) {
                articuloRevista = (ArticuloRevista) PublicacionFacade.getInstance().buscar((Long) tblArticulosRevistas.getValueAt(tblArticulosRevistas.getSelectedRow(), 0));
                diagProyectoResultadoArticuloRevista resultadoArticuloRevista = new diagProyectoResultadoArticuloRevista(null, true, "Modificación", proyectoVinculacion, articuloRevista, usuario);
                resultadoArticuloRevista.setLocation(Comunes.centrarDialog(resultadoArticuloRevista));
                resultadoArticuloRevista.setVisible(true);
                inicializarComponentesProduccion();
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un capitulo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto");
        }
    }

    private void agregarFormacionRH() {
        if (proyectoVinculacion != null) {
            diagProyectoResultadoFormacionRRHH resultadoFormacionRH = new diagProyectoResultadoFormacionRRHH(null, true, "Alta", proyectoVinculacion, usuario);
            resultadoFormacionRH.setLocation(Comunes.centrarDialog(resultadoFormacionRH));
            resultadoFormacionRH.setVisible(true);
            if (resultadoFormacionRH.getFormacionRRHH() != null) {
                cargarFormacionRRHH(resultadoFormacionRH.getFormacionRRHH());

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void cargarEncabezadosTablaFormacionRRHH(ModeloTablaNoEditable modeloTabla) {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Nro Becarios alumnos");
        modeloTabla.addColumn("Nro Becarios graduados");
        modeloTabla.addColumn("Nro Tesis dirigidas");
        modeloTabla.addColumn("Tesis aprobadas Maestría");
        modeloTabla.addColumn("Tesis aprobadas doctorado");
        modeloTabla.addColumn("Tesinas de grado aprobadas");
        tblFormacionRRHH.setModel(modeloTabla);
    }

    private void cargarFormacionRRHH() {
        modeloTablaFormacionRRHH = new ModeloTablaNoEditable();
        cargarEncabezadosTablaFormacionRRHH(modeloTablaFormacionRRHH);
        if (proyectoVinculacion != null) {
            for (FormacionRRHH formacionRH : FormacionRRHHFacade.getInstance().getFormacionesRRHHProyectoVinculacion(proyectoVinculacion)) {
                cargarFormacionRRHH(formacionRH);

            }
            tblFormacionRRHH.setModel(modeloTablaFormacionRRHH);
        }
    }

    private void cargarFormacionRRHH(FormacionRRHH formacionRH) {
        Object[] fila = new Object[8];
        fila[0] = formacionRH.getId();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fila[1] = formato.format(formacionRH.getFecha());
        } catch (java.lang.NullPointerException ex) {
            fila[1] = "-";
        }
        fila[2] = formacionRH.getBecariosAlumnos();
        fila[3] = formacionRH.getBecariosGraduados();
        fila[4] = formacionRH.getTesisDirigidas();
        fila[5] = formacionRH.getTesisAprobadasMaestria();
        fila[6] = formacionRH.getTesisAprobadasDoctorado();
        fila[7] = formacionRH.getTesinasGradoAprobadas();
        modeloTablaFormacionRRHH.addRow(fila);
    }

    private void modificarFormacionRH() {
        formacionRRHH = new FormacionRRHH();
        if (proyectoVinculacion != null) {
            if (tblFormacionRRHH.getSelectedRow() != -1) {
                formacionRRHH = FormacionRRHHFacade.getInstance().buscar((Long) tblFormacionRRHH.getValueAt(tblFormacionRRHH.getSelectedRow(), 0));
                diagProyectoResultadoFormacionRRHH resultadoFormacionRH = new diagProyectoResultadoFormacionRRHH(null, true, "Modificación", proyectoVinculacion, formacionRRHH, usuario);
                resultadoFormacionRH.setLocation(Comunes.centrarDialog(resultadoFormacionRH));
                resultadoFormacionRH.setVisible(true);
                if (resultadoFormacionRH.getFormacionRRHH() != null) {
                    inicializarComponentesProduccion();
                    cargarFormacionRRHH();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para editar");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proyecto primero");
        }
    }

    private void eliminarFormacionRH() {
        if (proyectoVinculacion != null) {
            if (tblFormacionRRHH.getSelectedRow() != -1) {
                int i = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {

                    FormacionRRHHFacade.getInstance().eliminar((Long) tblFormacionRRHH.getValueAt(tblFormacionRRHH.getSelectedRow(), 0));
                    inicializarComponentesProduccion();
                    cargarFormacionRRHH();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Proyecto");
        }
    }

    private void agregarArchivo() {
        File fichero = null;
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            InputStream is = null;
            try {
                fichero = fileChooser.getSelectedFile();
                Documento d = new Documento();
                d.setNombreArchivo(fichero.getName());
                is = new FileInputStream(fichero);
                byte[] buffer = new byte[(int) fichero.length()]; //creamos el buffer
                is.read(buffer); //leemos el archivo al buffer
                d.setContenidoArchivo(buffer);
                new DocumentoJpaController(Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES)).create(d);
                archivosDigitales.add(d);
                cargarDocumentosDigitales();
            } catch (Exception ex) {
                Logger.getLogger(diagProyectoVinculacion.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(diagProyectoVinculacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void modificarArchivo() {
        if (jListArchivos.getSelectedIndex() != -1) {
            int indiceParaModificar = jListArchivos.getSelectedIndex();
            archivosDigitales.remove(indiceParaModificar);
            cargarDocumentosDigitales();
        }
         else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        if (jListArchivos.getSelectedIndex() != -1) {
//            int indiceParaModificar = jListArchivos.getSelectedIndex();
//            File fichero = null;
//            JFileChooser fileChooser = new JFileChooser();
//            int seleccion = fileChooser.showOpenDialog(null);
//            if (seleccion == JFileChooser.APPROVE_OPTION) {
//                InputStream is = null;
//                try {
//                    fichero = fileChooser.getSelectedFile();
//                    Documento d = new Documento();
//                    d.setNombreArchivo(fichero.getName());
//                    is = new FileInputStream(fichero);
//                    byte[] buffer = new byte[(int) fichero.length()]; //creamos el buffer
//                    is.read(buffer); //leemos el archivo al buffer
//                    d.setContenidoArchivo(buffer);
//                    new DocumentoJpaController(Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES)).create(d);
//                    archivosDigitales.add(indiceParaModificar, d);
//
//                cargarDocumentosDigitales();
//                } catch (Exception ex) {
//                    Logger.getLogger(diagProyectoVinculacion.class.getName()).log(Level.SEVERE, null, ex);
//                } finally {
//                    try {
//                        is.close();
//                    } catch (IOException ex) {
//                        Logger.getLogger(diagProyectoVinculacion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
    private void cargarDocumentosDigitales(){
        Comunes.cargarJList(jListArchivos, archivosDigitales);

    }
    private void eliminarArchivo() {
        try {
            if (jListArchivos.getSelectedIndex() != -1) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el archivo seleccionado?", "Eliminacion", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    int indiceParaModificar = jListArchivos.getSelectedIndex();
                    archivosDigitales.remove(indiceParaModificar);
                    cargarDocumentosDigitales();
                     JOptionPane.showMessageDialog(rootPane, "Documento eliminado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);

                }
              

            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    private void abrirDocumento() {
        if (jListArchivos.getSelectedIndex() != -1) {
            try {
                Documento d = (Documento) jListArchivos.getSelectedValue();
                byte[] archivoInterno = d.getContenidoArchivo();
                File archivo = File.createTempFile("tmp", d.getNombreArchivo());
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
        }else{
           JOptionPane.showMessageDialog(null, "Debe seleccionar un documento.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
    }

    private void cargarArchivos() {
        try {
            Comunes.cargarJList(jListArchivos, proyectoVinculacion.getArchivosDigitales()); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {

        }
    }

    private void agregarNotaExterna() {
        diagNotaExterna notaExterna = new diagNotaExterna(null, true, "Alta", usuario);
        notaExterna.setLocation(Comunes.centrarDialog(notaExterna));
        notaExterna.setVisible(true);
        if (notaExterna.getNotaExternaCreada() != null) {
            notasExternas.add(notaExterna.getNotaExternaCreada());
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

    private void eliminarNotaExterna() {
        if (jTableNotasExternas.getSelectedRow() != -1) {
            int i = jTableNotasExternas.getSelectedRow();
            notasExternas.remove(i);
            cargarNotasExternas();
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

    public void reporteSeguimientoNi() {
        List<reportes.NotaIn> lista = new ArrayList();
        List<NotaInterna> listaordenada = proyectoVinculacion.getNotasInternas();
        Collections.sort(listaordenada, comparadorNi);
        for (NotaInterna ni : listaordenada) {
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
        List<NotaExterna> listaordenada = proyectoVinculacion.getNotasExternas();
        Collections.sort(listaordenada, comparadorNe);
        for (NotaExterna ne : listaordenada) {
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
    Comparator<NotaExterna> comparadorNe = new Comparator<NotaExterna>() {
        public int compare(NotaExterna a, NotaExterna b) {
            return (b.getFecha().compareTo(a.getFecha()));
        }
    };
    Comparator<NotaInterna> comparadorNi = new Comparator<NotaInterna>() {
        public int compare(NotaInterna a, NotaInterna b) {
            return (b.getFecha().compareTo(a.getFecha()));
        }
    };

    private void cargarNotasExternas() {
        Object[] fila = new Object[6];
        cargarTablaEncabezadosNotasExternas();
        if (!notasExternas.isEmpty()) {
            Collections.sort(notasExternas, comparadorNe);
            for (NotaExterna n : notasExternas) {

                fila[0] = formato.format(n.getFecha());
                fila[1] = n.getNroNota();
                fila[2] = n.getMotivo();
                fila[3] = formato.format(n.getFechaDeRecibidoMesaDeEntrada());
                if(n.getFechaAprobacion() != null){
                                    fila[4] = formato.format(n.getFechaAprobacion());

                }else{
                    fila[4] = "Indefinida";

                }
                
                if (n.getFechaDesembolso() != null){
                    fila[5] = formato.format(n.getFechaDesembolso());

                }
                else{
                      fila[5] = "Indefinida";
         
                }
                modeloNotasExternas.addRow(fila);

            }
        }

    }

    private void cargarNotasInternas() {
        Object[] fila = new Object[5];
        cargarTablaEncabezadosNotasInternas();
        if (!notasInternas.isEmpty()) {
            Collections.sort(notasInternas, comparadorNi);
            for (NotaInterna n : notasInternas) {

                fila[0] = formato.format(n.getFecha());
                fila[1] = n.getNroNota();
                fila[2] = n.getMotivo();
                fila[3] = formato.format(n.getFechaDeRecibidoMesaDeEntrada());
                 if (n.getFechaDesembolso() != null){
                    fila[4] = formato.format(n.getFechaDesembolso());

                }
                else{
                      fila[4] = "Indefinida";
         
                }
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

    private void cargarEtapas(List<Etapa> etapas) {

        for (Etapa e : etapas) {
            etapas.add(e);
        }

        Comunes.cargarJList(jListEtapas, etapas);
        //To change body of generated methods, choose Tools | Templates.
    }
    private void agregarInformeEtapa(){
        if(jListEtapas.getSelectedIndex() != -1) {
            Etapa e = (Etapa) jListEtapas.getSelectedValue();
            diagSubirArchivo subirArchivo = new diagSubirArchivo(null, true, "Alta", e);
            subirArchivo.setVisible(true);
            Comunes.cargarJList(jListInformesEtapas, e.getDocumentos());
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una subdisciplina científica", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void cargarEtapaDeInforme(){
        if(jListFinanciamientoInformes.getSelectedIndex() != -1){
            Financiacion f = (Financiacion) jListFinanciamientoInformes.getSelectedValue();
            if(f.getDescripcion().equals("PFIP")){
                FinanciacionPfip pf = (FinanciacionPfip) jListFinanciamientoInformes.getSelectedValue();
                Comunes.cargarJList(jListEtapas, pf.getEtapas());
            }
            if(f.getDescripcion().equals("DETEM")){
                FinanciacionDetem pf = (FinanciacionDetem) jListFinanciamientoInformes.getSelectedValue();
                Comunes.cargarJList(jListEtapas, pf.getEtapas());
            }
            
        }
    }
    private void cargarDocsDeEtapa(){
        if(jListEtapas.getSelectedIndex() != -1){
            Etapa e = (Etapa) jListEtapas.getSelectedValue();
            Comunes.cargarJList(jListInformesEtapas, e.getDocumentos());
        }
    }
        private void verDocumentoInforme() {
        if (jListInformesEtapas.getSelectedIndex() != -1) {
            try {
                Documento d = (Documento) jListInformesEtapas.getSelectedValue();
                byte[] archivoInterno = d.getContenidoArchivo();
                File archivo = File.createTempFile("tmp", d.getNombreArchivo());
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
                            JOptionPane.showMessageDialog(null, "No se encuentra el archivo: " + archivo.getAbsolutePath(), "Aviso", JOptionPane.WARNING_MESSAGE);
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

