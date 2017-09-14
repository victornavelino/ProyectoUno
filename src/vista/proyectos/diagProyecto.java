/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagProyecto.java
 *
 * Created on 07/08/2011, 10:00:11
 */
package vista.proyectos;

import entidades.*;
import entidades.localidad.Departamento;
import entidades.localidad.Localidad;
import entidades.localidad.Provincia;
import entidades.operaciones.Operacion;
import entidades.proyecto.AreaTematica;
import entidades.proyecto.CampoAplicacion;
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.EntidadAcreditadora;
import entidades.proyecto.EntidadConvenio;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.EntidadFinanciadora;
import entidades.proyecto.Especialidad;
import entidades.proyecto.Evaluacion;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.ObjetivoSocioeconomico;
import entidades.proyecto.Participacion;
import entidades.proyecto.Programa;
import entidades.proyecto.Prorroga;
import entidades.proyecto.Proyecto;
import entidades.proyecto.ProyectoDatosComplementarios;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.UnidadInvestigacion;
import entidades.usuario.Usuario;
import facade.*;
import includes.Comunes;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.validator.routines.IntegerValidator;
import vista.diagProyectoBusquedaSimple;
import vista.panelControl.diagAreaTematica;
import vista.panelControl.diagCampoAplicacion;
import vista.panelControl.diagDisciplinaCientifica;
import vista.panelControl.diagEntidadConvenio;
import vista.panelControl.diagEntidadEvaluadora;
import vista.panelControl.diagEspecialidad;
import vista.panelControl.diagLineaInvestigacion;
import vista.panelControl.diagPrograma;
import vista.panelControl.diagProrroga;
import vista.panelControl.diagSubDisciplinaCientifica;
import vista.panelControl.diagTipoProyecto;
import vista.panelControl.diagUnidadAcademica;
import vista.panelControl.diagUnidadEjecutora;
import vista.panelControl.diagUnidadInvestigacion;
import vista.participaciones.diagParticipacion;
import vista.participaciones.diagParticipacionesPorInvestigador;
import vista.resoluciones.diagResolucionAlta;
import vista.resoluciones.diagResolucionEleccion;
import vistas.evaluaciones.diagEvaluacion;

/**
 *
 * @author Carlos
 */
public class diagProyecto extends javax.swing.JDialog {

    private String tipoOperacion;
    private Proyecto proyecto;
    private List<Participacion> participaciones = new ArrayList<>();
    private List<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
    private List<Prorroga> prorrogas = new ArrayList<Prorroga>();
    private List<CampoAplicacion> camposAplicacion = new ArrayList<CampoAplicacion>();
    private List<Especialidad> especialidades = new ArrayList<Especialidad>();
    private List<UnidadInvestigacion> unidadesInvestigacion = new ArrayList<UnidadInvestigacion>();
    private List<EntidadConvenio> conveniosCon = new ArrayList<EntidadConvenio>();
    private List<SubDisciplinaCientifica> subDisciplinasCientificas = new ArrayList<SubDisciplinaCientifica>();
    private List<DisciplinaCientifica> disciplinasCientificas = new ArrayList<DisciplinaCientifica>();
    private List<AreaTematica> areasTematicas = new ArrayList<AreaTematica>();
    private List<Resolucion> resoluciones = new ArrayList();
    private UnidadAcademica unidadAcademica;
    private LineaInvestigacion lineaInvestigacion;
    private UnidadEjecutora unidadEjecutora;
    private TipoProyecto tipoProyecto;
    private ObjetivoSocioeconomico objetivoSocioeconomico;
    private EntidadEvaluadora entidadEvaluadora;
    private Programa programa;
    private List<String> palabrasClaves = new ArrayList<String>();
    private List<String> keyWords = new ArrayList<String>();
    private Proyecto proyectoComplementarioPadre;
    private List<Proyecto> proyectosComplementariosHijos = new ArrayList<Proyecto>();
    private Usuario usuario;
    private DefaultTableModel modeloTabla;
    private Evaluacion evaluacion;
    private ProyectoDatosComplementarios proyectoDatosComplementarios;
    private Participacion participacion;
    private Resolucion documentoWinsip;
    private JLabel jLabel44;
    private EntidadFinanciadora entidadFinanciadora;
    private EntidadAcreditadora entidadAcreditadora;
    public static final int MIN_ZOOM = 0;
    public static final int MAX_ZOOM = 21;
    public int zoomValue = 4;

    /**
     * Creates new form diagProyecto
     */
    public diagProyecto(java.awt.Frame parent, boolean modal, String tipoOperacion, Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.usuario = usuario;
        modeloTabla = new DefaultTableModel();
        initComponents();
        inicializarComponentes();
        //size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
//available size of the screen 
        this.setSize(screenSize.width, screenSize.height - taskBarSize);

    }

    public diagProyecto(java.awt.Frame parent, boolean modal, String tipoOperacion, Proyecto proyecto, Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.proyecto = proyecto;
        modeloTabla = new DefaultTableModel();
        this.usuario = usuario;
        initComponents();
        try {
            inicializarComponentes();
        } catch (Exception ex) {
            Comunes.mensajeError(ex, "Error Intentando Cargar Proyecto");
            this.proyecto = null;
            jButton1.setVisible(false);
            jScrollPane20.setVisible(false);
        }
        //size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
//available size of the screen 
        this.setSize(screenSize.width, screenSize.height - taskBarSize);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jListCamposAplicacion = new javax.swing.JList();
        btAgregarCampoAplicacion = new javax.swing.JButton();
        btQuitarCampoAplicacion = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jListUnidadesInvestigacion = new javax.swing.JList();
        btAgregarUnidadInvestigacion = new javax.swing.JButton();
        btQuitarUnidadInvestigacion = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jListEspecialidades = new javax.swing.JList();
        btAgregarEspecialidad = new javax.swing.JButton();
        btQuitarEspecialidad = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboObjetivoSocioeconomico = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        tfCodigoProyecto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfCodigoIncentivos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taTitulo = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cboUnidadAcademica = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        tfDuracionTeorica = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dateFechaInicio = new org.jdesktop.swingx.JXDatePicker();
        jLabel9 = new javax.swing.JLabel();
        dateFechaFinalizacion = new org.jdesktop.swingx.JXDatePicker();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListProrrogas = new javax.swing.JList();
        btNuevaProrroga = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cboLineaInvestigacion = new javax.swing.JComboBox();
        btNuevaLineaInvestigacion = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cboUnidadEjecutora = new javax.swing.JComboBox();
        btNuevaUnidadEjecutora = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cboTipoProyecto = new javax.swing.JComboBox();
        btNuevoTipoProyecto = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cboPrograma = new javax.swing.JComboBox();
        btNuevoPrograma = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        cboEntidadEvaluadora = new javax.swing.JComboBox();
        btNuevaEntidadEvaluadora = new javax.swing.JButton();
        btNuevaUnidadAcademica = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jListConveniosCon = new javax.swing.JList();
        btAgregarConvenioCon = new javax.swing.JButton();
        btQuitarConveniocon = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
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
        cboEntidadAcreditadora = new javax.swing.JComboBox();
        btNuevaEntidadAcreditadora = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        cboEntidadFinanciadora = new javax.swing.JComboBox();
        btNuevaEntidadFinanciadora = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jListParticipaciones = new javax.swing.JList();
        chkParticipacionesActualesOUltimas = new javax.swing.JCheckBox();
        btAgregarParticipacion = new javax.swing.JButton();
        btVerDetallesParticipacionSeleccionada = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        tfPersonalApoyo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jListResoluciones = new javax.swing.JList();
        btAgregarResolucion = new javax.swing.JButton();
        btVerDetallesResolucionSeleccionada = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        btAgregarEvaluacion = new javax.swing.JButton();
        btVerDetallesEvaluacionSeleccionada = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEditarEvaluacion = new javax.swing.JButton();
        btnEliminarEvaluacion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResumen = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPalabrasClaves = new javax.swing.JList();
        btAgregarPalabraClave = new javax.swing.JButton();
        btQuitarPalabraClave = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        taObservaciones = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        taTitle = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        taSummary = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListKeywords = new javax.swing.JList();
        btAgregarKeyword = new javax.swing.JButton();
        btQuitarKeyword = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListProyectosComplementariosHijos = new javax.swing.JList();
        btAgregarProyectoComplementarioHijo = new javax.swing.JButton();
        btQuitarProyectoComplementarioHijo = new javax.swing.JButton();
        btSeleccionarProyectoComplementarioPadre = new javax.swing.JButton();
        tfProyectoComplementarioPadre = new javax.swing.JTextField();
        panelDatosComplementarios = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        tfConvocatoria = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfAntecedentes = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        tfLocalizacion = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        taMetodologia = new javax.swing.JTextArea();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        taObjetivoEspecificoHipotesis = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        taObjetivoGeneral = new javax.swing.JTextArea();
        cbProyectoIniciacion = new javax.swing.JCheckBox();
        cbProyectoOrientado = new javax.swing.JCheckBox();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        taRelevanciaProblema = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        tfSubDisciplinaCientificaOtra = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        tfUnidadEjecutoraOtra = new javax.swing.JTextField();
        jScrollPane26 = new javax.swing.JScrollPane();
        tpCronogramaTrabajo = new javax.swing.JTextPane();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        tpFormacionRRHH = new javax.swing.JTextPane();
        pnDocumentoWinsip = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        tfDocumentoWinsip = new javax.swing.JTextField();
        jBtnAgregarResolucionProyecto = new javax.swing.JButton();
        jBtnModificarResolucionProyecto = new javax.swing.JButton();
        jBtnVerResolucionProyecto = new javax.swing.JButton();
        btnQuitarResProyeto = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        tfLatitud = new javax.swing.JTextField();
        tfLongitud = new javax.swing.JTextField();
        btnVerMapa = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        cboProvincias = new javax.swing.JComboBox();
        jLabel51 = new javax.swing.JLabel();
        cboDepartamentos = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        cboLocalidades = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.title")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane13.setViewportView(jListCamposAplicacion);

        btAgregarCampoAplicacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarCampoAplicacion.text")); // NOI18N
        btAgregarCampoAplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarCampoAplicacionActionPerformed(evt);
            }
        });

        btQuitarCampoAplicacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarCampoAplicacion.text")); // NOI18N
        btQuitarCampoAplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarCampoAplicacionActionPerformed(evt);
            }
        });

        jLabel33.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel33.text")); // NOI18N

        jScrollPane15.setViewportView(jListUnidadesInvestigacion);

        btAgregarUnidadInvestigacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarUnidadInvestigacion.text")); // NOI18N
        btAgregarUnidadInvestigacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarUnidadInvestigacionActionPerformed(evt);
            }
        });

        btQuitarUnidadInvestigacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarUnidadInvestigacion.text")); // NOI18N
        btQuitarUnidadInvestigacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarUnidadInvestigacionActionPerformed(evt);
            }
        });

        jLabel21.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel21.text")); // NOI18N

        jScrollPane14.setViewportView(jListEspecialidades);

        btAgregarEspecialidad.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarEspecialidad.text")); // NOI18N
        btAgregarEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarEspecialidadActionPerformed(evt);
            }
        });

        btQuitarEspecialidad.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarEspecialidad.text")); // NOI18N
        btQuitarEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarEspecialidadActionPerformed(evt);
            }
        });

        jLabel34.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel34.text")); // NOI18N

        jLabel17.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel17.text")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btQuitarCampoAplicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAgregarCampoAplicacion))
                        .addGap(82, 82, 82)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btQuitarEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAgregarEspecialidad))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAgregarUnidadInvestigacion)
                            .addComponent(btQuitarUnidadInvestigacion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboObjetivoSocioeconomico, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addComponent(btAgregarEspecialidad)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btQuitarEspecialidad))
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(btAgregarCampoAplicacion)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btQuitarCampoAplicacion))))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel21))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btAgregarUnidadInvestigacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btQuitarUnidadInvestigacion))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cboObjetivoSocioeconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel1.text")); // NOI18N

        tfCodigoProyecto.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfCodigoProyecto.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel2.text")); // NOI18N

        tfCodigoIncentivos.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfCodigoIncentivos.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel3.text")); // NOI18N

        taTitulo.setColumns(20);
        taTitulo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taTitulo.setLineWrap(true);
        taTitulo.setRows(7);
        jScrollPane1.setViewportView(taTitulo);

        jLabel6.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel6.text")); // NOI18N

        cboUnidadAcademica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadAcademicaItemStateChanged(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel7.text")); // NOI18N

        tfDuracionTeorica.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfDuracionTeorica.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel8.text")); // NOI18N

        dateFechaInicio.setFormats("dd/MM/yyyy");

        jLabel9.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel9.text")); // NOI18N

        dateFechaFinalizacion.setFormats("dd/MM/yyyy");

        jLabel10.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel10.text")); // NOI18N

        jScrollPane4.setViewportView(jListProrrogas);

        btNuevaProrroga.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaProrroga.text")); // NOI18N
        btNuevaProrroga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaProrrogaActionPerformed(evt);
            }
        });

        jLabel16.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel16.text")); // NOI18N

        cboLineaInvestigacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLineaInvestigacionItemStateChanged(evt);
            }
        });

        btNuevaLineaInvestigacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaLineaInvestigacion.text")); // NOI18N
        btNuevaLineaInvestigacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaLineaInvestigacionActionPerformed(evt);
            }
        });

        jLabel18.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel18.text")); // NOI18N

        cboUnidadEjecutora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadEjecutoraItemStateChanged(evt);
            }
        });

        btNuevaUnidadEjecutora.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaUnidadEjecutora.text")); // NOI18N
        btNuevaUnidadEjecutora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaUnidadEjecutoraActionPerformed(evt);
            }
        });

        jLabel19.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel19.text")); // NOI18N

        cboTipoProyecto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTipoProyectoItemStateChanged(evt);
            }
        });

        btNuevoTipoProyecto.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevoTipoProyecto.text")); // NOI18N
        btNuevoTipoProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoTipoProyectoActionPerformed(evt);
            }
        });

        jLabel20.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel20.text")); // NOI18N

        cboPrograma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProgramaItemStateChanged(evt);
            }
        });

        btNuevoPrograma.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevoPrograma.text")); // NOI18N
        btNuevoPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoProgramaActionPerformed(evt);
            }
        });

        jLabel22.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel22.text")); // NOI18N

        cboEntidadEvaluadora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboEntidadEvaluadoraItemStateChanged(evt);
            }
        });

        btNuevaEntidadEvaluadora.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaEntidadEvaluadora.text")); // NOI18N
        btNuevaEntidadEvaluadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaEntidadEvaluadoraActionPerformed(evt);
            }
        });

        btNuevaUnidadAcademica.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaUnidadAcademica.text")); // NOI18N
        btNuevaUnidadAcademica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaUnidadAcademicaActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel7.border.title"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel9.border.title"))); // NOI18N

        jScrollPane16.setViewportView(jListConveniosCon);

        btAgregarConvenioCon.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarConvenioCon.text")); // NOI18N
        btAgregarConvenioCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarConvenioConActionPerformed(evt);
            }
        });

        btQuitarConveniocon.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarConveniocon.text")); // NOI18N
        btQuitarConveniocon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarConvenioconActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btQuitarConveniocon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAgregarConvenioCon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btAgregarConvenioCon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btQuitarConveniocon))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel11.text")); // NOI18N

        jListAreasTematicas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane17.setViewportView(jListAreasTematicas);

        jLabel12.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel12.text")); // NOI18N

        jListDisciplinasCientificas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane18.setViewportView(jListDisciplinasCientificas);

        jLabel13.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel13.text")); // NOI18N

        jListSubDisciplinasCientificas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane19.setViewportView(jListSubDisciplinasCientificas);

        btAgregarSubDisciplinaCientifica.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarSubDisciplinaCientifica.text")); // NOI18N
        btAgregarSubDisciplinaCientifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarSubDisciplinaCientificaActionPerformed(evt);
            }
        });

        btQuitarSubDisciplinaCientifica.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarSubDisciplinaCientifica.text")); // NOI18N
        btQuitarSubDisciplinaCientifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarSubDisciplinaCientificaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btQuitarSubDisciplinaCientifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAgregarSubDisciplinaCientifica))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btAgregarSubDisciplinaCientifica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btQuitarSubDisciplinaCientifica))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        cboEntidadAcreditadora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboEntidadAcreditadoraItemStateChanged(evt);
            }
        });

        btNuevaEntidadAcreditadora.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaEntidadAcreditadora.text")); // NOI18N
        btNuevaEntidadAcreditadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaEntidadAcreditadoraActionPerformed(evt);
            }
        });

        jLabel45.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel45.text")); // NOI18N

        cboEntidadFinanciadora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboEntidadFinanciadoraItemStateChanged(evt);
            }
        });

        btNuevaEntidadFinanciadora.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btNuevaEntidadFinanciadora.text")); // NOI18N
        btNuevaEntidadFinanciadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaEntidadFinanciadoraActionPerformed(evt);
            }
        });

        jLabel47.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel47.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cboUnidadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btNuevaUnidadAcademica)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfDuracionTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dateFechaFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(58, 58, 58)
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfCodigoIncentivos, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(94, 94, 94)
                                            .addComponent(jLabel3)))
                                    .addGap(7, 7, 7)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btNuevaProrroga))))
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboTipoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btNuevoTipoProyecto)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboEntidadEvaluadora, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btNuevoPrograma)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btNuevaEntidadEvaluadora)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel45))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboLineaInvestigacion, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btNuevaLineaInvestigacion)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboUnidadEjecutora, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btNuevaUnidadEjecutora)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel47)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboEntidadFinanciadora, 0, 235, Short.MAX_VALUE)
                                    .addComponent(cboEntidadAcreditadora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btNuevaEntidadFinanciadora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btNuevaEntidadAcreditadora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 2008, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(tfCodigoIncentivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboUnidadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDuracionTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btNuevaUnidadAcademica)
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dateFechaFinalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(btNuevaProrroga, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel10))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboEntidadAcreditadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaEntidadAcreditadora)
                        .addComponent(jLabel47))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(cboLineaInvestigacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboUnidadEjecutora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaUnidadEjecutora)
                        .addComponent(btNuevaLineaInvestigacion)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboEntidadFinanciadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaEntidadFinanciadora)
                        .addComponent(jLabel45))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(cboTipoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevoTipoProyecto)
                        .addComponent(jLabel22)
                        .addComponent(cboEntidadEvaluadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaEntidadEvaluadora)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(btNuevoPrograma))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jLabel29.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel29.text")); // NOI18N

        jListParticipaciones.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListParticipacionesValueChanged(evt);
            }
        });
        jScrollPane10.setViewportView(jListParticipaciones);

        chkParticipacionesActualesOUltimas.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.chkParticipacionesActualesOUltimas.text")); // NOI18N
        chkParticipacionesActualesOUltimas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkParticipacionesActualesOUltimasStateChanged(evt);
            }
        });
        chkParticipacionesActualesOUltimas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkParticipacionesActualesOUltimasActionPerformed(evt);
            }
        });

        btAgregarParticipacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarParticipacion.text")); // NOI18N
        btAgregarParticipacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarParticipacionActionPerformed(evt);
            }
        });

        btVerDetallesParticipacionSeleccionada.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btVerDetallesParticipacionSeleccionada.text")); // NOI18N
        btVerDetallesParticipacionSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerDetallesParticipacionSeleccionadaActionPerformed(evt);
            }
        });

        jLabel32.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel32.text")); // NOI18N

        tfPersonalApoyo.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfPersonalApoyo.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkParticipacionesActualesOUltimas)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btAgregarParticipacion))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(btVerDetallesParticipacionSeleccionada))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPersonalApoyo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(2533, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAgregarParticipacion)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkParticipacionesActualesOUltimas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btVerDetallesParticipacionSeleccionada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(tfPersonalApoyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(274, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jLabel30.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel30.text")); // NOI18N

        jScrollPane11.setViewportView(jListResoluciones);

        btAgregarResolucion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarResolucion.text")); // NOI18N
        btAgregarResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarResolucionActionPerformed(evt);
            }
        });

        btVerDetallesResolucionSeleccionada.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btVerDetallesResolucionSeleccionada.text")); // NOI18N
        btVerDetallesResolucionSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerDetallesResolucionSeleccionadaActionPerformed(evt);
            }
        });

        btnEliminar.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btnEliminar.text")); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btAgregarResolucion))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(btVerDetallesResolucionSeleccionada)))
                .addContainerGap(2564, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btAgregarResolucion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btVerDetallesResolucionSeleccionada)))
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel31.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel31.text")); // NOI18N

        btAgregarEvaluacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarEvaluacion.text")); // NOI18N
        btAgregarEvaluacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarEvaluacionActionPerformed(evt);
            }
        });

        btVerDetallesEvaluacionSeleccionada.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btVerDetallesEvaluacionSeleccionada.text")); // NOI18N
        btVerDetallesEvaluacionSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerDetallesEvaluacionSeleccionadaActionPerformed(evt);
            }
        });

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
        jScrollPane21.setViewportView(jTable1);

        btnEditarEvaluacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btnEditarEvaluacion.text")); // NOI18N
        btnEditarEvaluacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEvaluacionActionPerformed(evt);
            }
        });

        btnEliminarEvaluacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btnEliminarEvaluacion.text")); // NOI18N
        btnEliminarEvaluacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEvaluacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarEvaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAgregarEvaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarEvaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(btVerDetallesEvaluacionSeleccionada)))
                .addContainerGap(2562, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btAgregarEvaluacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarEvaluacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarEvaluacion))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btVerDetallesEvaluacionSeleccionada)))
                .addContainerGap(361, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel4.text")); // NOI18N

        taResumen.setColumns(20);
        taResumen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taResumen.setLineWrap(true);
        taResumen.setRows(20);
        jScrollPane2.setViewportView(taResumen);

        jLabel5.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel5.text")); // NOI18N

        jScrollPane3.setViewportView(jListPalabrasClaves);

        btAgregarPalabraClave.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarPalabraClave.text")); // NOI18N
        btAgregarPalabraClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarPalabraClaveActionPerformed(evt);
            }
        });

        btQuitarPalabraClave.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarPalabraClave.text")); // NOI18N
        btQuitarPalabraClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarPalabraClaveActionPerformed(evt);
            }
        });

        jLabel23.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel23.text")); // NOI18N

        taObservaciones.setColumns(20);
        taObservaciones.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taObservaciones.setLineWrap(true);
        taObservaciones.setRows(20);
        jScrollPane5.setViewportView(taObservaciones);

        jLabel24.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel24.text")); // NOI18N

        taTitle.setColumns(20);
        taTitle.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taTitle.setLineWrap(true);
        taTitle.setRows(7);
        jScrollPane6.setViewportView(taTitle);

        jLabel25.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel25.text")); // NOI18N

        taSummary.setColumns(20);
        taSummary.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taSummary.setLineWrap(true);
        taSummary.setRows(20);
        jScrollPane7.setViewportView(taSummary);

        jLabel26.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel26.text")); // NOI18N

        jScrollPane8.setViewportView(jListKeywords);

        btAgregarKeyword.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarKeyword.text")); // NOI18N
        btAgregarKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarKeywordActionPerformed(evt);
            }
        });

        btQuitarKeyword.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarKeyword.text")); // NOI18N
        btQuitarKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarKeywordActionPerformed(evt);
            }
        });

        jLabel27.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel27.text")); // NOI18N

        jLabel28.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel28.text")); // NOI18N

        jScrollPane9.setViewportView(jListProyectosComplementariosHijos);

        btAgregarProyectoComplementarioHijo.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btAgregarProyectoComplementarioHijo.text")); // NOI18N
        btAgregarProyectoComplementarioHijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarProyectoComplementarioHijoActionPerformed(evt);
            }
        });

        btQuitarProyectoComplementarioHijo.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btQuitarProyectoComplementarioHijo.text")); // NOI18N
        btQuitarProyectoComplementarioHijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarProyectoComplementarioHijoActionPerformed(evt);
            }
        });

        btSeleccionarProyectoComplementarioPadre.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btSeleccionarProyectoComplementarioPadre.text")); // NOI18N
        btSeleccionarProyectoComplementarioPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSeleccionarProyectoComplementarioPadreActionPerformed(evt);
            }
        });

        tfProyectoComplementarioPadre.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfProyectoComplementarioPadre.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btQuitarPalabraClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btAgregarPalabraClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane6)
                                    .addComponent(jScrollPane8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btAgregarKeyword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btQuitarKeyword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1732, 1732, 1732)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btAgregarProyectoComplementarioHijo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btQuitarProyectoComplementarioHijo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfProyectoComplementarioPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSeleccionarProyectoComplementarioPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(1533, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btAgregarPalabraClave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btQuitarPalabraClave))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel24)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel26)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btAgregarKeyword)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btQuitarKeyword)
                                        .addGap(33, 33, 33)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(btSeleccionarProyectoComplementarioPadre)
                    .addComponent(tfProyectoComplementarioPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btAgregarProyectoComplementarioHijo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btQuitarProyectoComplementarioHijo)
                .addGap(46, 46, 46))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jLabel14.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel14.text")); // NOI18N

        tfConvocatoria.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfConvocatoria.text")); // NOI18N

        jLabel15.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel15.text")); // NOI18N

        tfAntecedentes.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfAntecedentes.text")); // NOI18N

        jLabel35.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel35.text")); // NOI18N

        jLabel36.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel36.text")); // NOI18N

        tfLocalizacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfLocalizacion.text")); // NOI18N

        jLabel37.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel37.text")); // NOI18N

        taMetodologia.setColumns(20);
        taMetodologia.setRows(5);
        jScrollPane22.setViewportView(taMetodologia);

        jLabel38.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel38.text")); // NOI18N

        taObjetivoEspecificoHipotesis.setColumns(20);
        taObjetivoEspecificoHipotesis.setRows(5);
        jScrollPane23.setViewportView(taObjetivoEspecificoHipotesis);

        jLabel39.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel39.text")); // NOI18N

        taObjetivoGeneral.setColumns(20);
        taObjetivoGeneral.setRows(5);
        jScrollPane24.setViewportView(taObjetivoGeneral);

        cbProyectoIniciacion.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.cbProyectoIniciacion.text")); // NOI18N

        cbProyectoOrientado.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.cbProyectoOrientado.text")); // NOI18N

        jLabel40.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel40.text")); // NOI18N

        taRelevanciaProblema.setColumns(20);
        taRelevanciaProblema.setRows(5);
        jScrollPane25.setViewportView(taRelevanciaProblema);

        jLabel41.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel41.text")); // NOI18N

        tfSubDisciplinaCientificaOtra.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfSubDisciplinaCientificaOtra.text")); // NOI18N

        jLabel42.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel42.text")); // NOI18N

        tfUnidadEjecutoraOtra.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfUnidadEjecutoraOtra.text")); // NOI18N

        jScrollPane26.setViewportView(tpCronogramaTrabajo);

        jLabel43.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel43.text")); // NOI18N

        jScrollPane27.setViewportView(tpFormacionRRHH);

        javax.swing.GroupLayout panelDatosComplementariosLayout = new javax.swing.GroupLayout(panelDatosComplementarios);
        panelDatosComplementarios.setLayout(panelDatosComplementariosLayout);
        panelDatosComplementariosLayout.setHorizontalGroup(
            panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel37)
                        .addComponent(jScrollPane22)
                        .addComponent(jLabel38)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel36)
                            .addGap(18, 18, 18)
                            .addComponent(tfLocalizacion))
                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosComplementariosLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39)
                            .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProyectoIniciacion)
                            .addComponent(cbProyectoOrientado)
                            .addComponent(jLabel40)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSubDisciplinaCientificaOtra)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosComplementariosLayout.createSequentialGroup()
                            .addComponent(jLabel42)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfUnidadEjecutoraOtra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel43)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2236, 2236, 2236))
        );
        panelDatosComplementariosLayout.setVerticalGroup(
            panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                        .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(tfConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(tfAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35))
                    .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbProyectoIniciacion)
                        .addGap(9, 9, 9)
                        .addComponent(cbProyectoOrientado))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosComplementariosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                        .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(tfLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosComplementariosLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(tfSubDisciplinaCientificaOtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDatosComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(tfUnidadEjecutoraOtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.panelDatosComplementarios.TabConstraints.tabTitle"), panelDatosComplementarios); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel6.border.title"))); // NOI18N

        jLabel46.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel45.text")); // NOI18N

        tfDocumentoWinsip.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfDocumentoWinsip.text")); // NOI18N

        jBtnAgregarResolucionProyecto.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jBtnAgregarResolucionProyecto.text")); // NOI18N
        jBtnAgregarResolucionProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarResolucionProyectoActionPerformed(evt);
            }
        });

        jBtnModificarResolucionProyecto.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jBtnModificarResolucionProyecto.text")); // NOI18N
        jBtnModificarResolucionProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarResolucionProyectoActionPerformed(evt);
            }
        });

        jBtnVerResolucionProyecto.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jBtnVerResolucionProyecto.text")); // NOI18N
        jBtnVerResolucionProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVerResolucionProyectoActionPerformed(evt);
            }
        });

        btnQuitarResProyeto.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btnQuitarResProyeto.text")); // NOI18N
        btnQuitarResProyeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarResProyetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addGap(34, 34, 34)
                .addComponent(tfDocumentoWinsip, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAgregarResolucionProyecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitarResProyeto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnVerResolucionProyecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnModificarResolucionProyecto)
                .addContainerGap(2548, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(tfDocumentoWinsip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAgregarResolucionProyecto)
                    .addComponent(jBtnModificarResolucionProyecto)
                    .addComponent(jBtnVerResolucionProyecto)
                    .addComponent(btnQuitarResProyeto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnDocumentoWinsipLayout = new javax.swing.GroupLayout(pnDocumentoWinsip);
        pnDocumentoWinsip.setLayout(pnDocumentoWinsipLayout);
        pnDocumentoWinsipLayout.setHorizontalGroup(
            pnDocumentoWinsipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3335, Short.MAX_VALUE)
            .addGroup(pnDocumentoWinsipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDocumentoWinsipLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnDocumentoWinsipLayout.setVerticalGroup(
            pnDocumentoWinsipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
            .addGroup(pnDocumentoWinsipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDocumentoWinsipLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.pnDocumentoWinsip.TabConstraints.tabTitle"), pnDocumentoWinsip); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel12.border.title"))); // NOI18N

        jLabel48.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel48.text")); // NOI18N

        jLabel49.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel49.text")); // NOI18N

        tfLatitud.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfLatitud.text")); // NOI18N

        tfLongitud.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.tfLongitud.text")); // NOI18N

        btnVerMapa.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.btnVerMapa.text")); // NOI18N
        btnVerMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMapaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel49)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnVerMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(473, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnVerMapa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(tfLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel49)
                            .addComponent(tfLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel50.setText(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jLabel50.text")); // NOI18N

        cboProvincias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProvinciasItemStateChanged(evt);
            }
        });

        jLabel51.setText("Departamento");

        cboDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDepartamentosItemStateChanged(evt);
            }
        });

        jLabel52.setText("Localidad");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboDepartamentos, javax.swing.GroupLayout.Alignment.LEADING, 0, 296, Short.MAX_VALUE)
                            .addComponent(cboProvincias, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboLocalidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(cboProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(cboDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(cboLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(diagProyecto.class, "diagProyecto.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jScrollPane20.setViewportView(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(480, 480, 480))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNuevaUnidadAcademicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaUnidadAcademicaActionPerformed
        agregarNuevaUnidadAcademica();
    }//GEN-LAST:event_btNuevaUnidadAcademicaActionPerformed

    private void btNuevaLineaInvestigacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaLineaInvestigacionActionPerformed
        agregarNuevaLineaInvestigacion();
    }//GEN-LAST:event_btNuevaLineaInvestigacionActionPerformed

    private void btNuevaUnidadEjecutoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaUnidadEjecutoraActionPerformed
        agregarNuevaUnidadEjecutora();
    }//GEN-LAST:event_btNuevaUnidadEjecutoraActionPerformed

    private void btNuevoTipoProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoTipoProyectoActionPerformed
        agregarNuevoTipoProyecto();
    }//GEN-LAST:event_btNuevoTipoProyectoActionPerformed

    private void btNuevoProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoProgramaActionPerformed
        agregarNuevoPrograma();
    }//GEN-LAST:event_btNuevoProgramaActionPerformed

    private void btNuevaEntidadEvaluadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaEntidadEvaluadoraActionPerformed
        agregarNuevaEntidadEvaluadora();
    }//GEN-LAST:event_btNuevaEntidadEvaluadoraActionPerformed

    private void btNuevaProrrogaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaProrrogaActionPerformed
        agregarNuevaProrroga();
    }//GEN-LAST:event_btNuevaProrrogaActionPerformed

    private void btAgregarProyectoComplementarioHijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarProyectoComplementarioHijoActionPerformed
        agregarProyectosComplementariosHijos();
    }//GEN-LAST:event_btAgregarProyectoComplementarioHijoActionPerformed

    private void btAgregarPalabraClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarPalabraClaveActionPerformed
        agregarPalabraClave();
    }//GEN-LAST:event_btAgregarPalabraClaveActionPerformed

    private void btAgregarKeywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarKeywordActionPerformed
        agregarKeyWords();
    }//GEN-LAST:event_btAgregarKeywordActionPerformed

    private void btSeleccionarProyectoComplementarioPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSeleccionarProyectoComplementarioPadreActionPerformed
        seleccionarProyectoComplementarioPadre();
    }//GEN-LAST:event_btSeleccionarProyectoComplementarioPadreActionPerformed

    private void btQuitarPalabraClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarPalabraClaveActionPerformed
        borrarPalabraClave();
    }//GEN-LAST:event_btQuitarPalabraClaveActionPerformed

    private void btQuitarKeywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarKeywordActionPerformed
        borrarKeyWord();
    }//GEN-LAST:event_btQuitarKeywordActionPerformed

    private void btQuitarProyectoComplementarioHijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarProyectoComplementarioHijoActionPerformed
        borrarProyectosComplementariosHijos();
    }//GEN-LAST:event_btQuitarProyectoComplementarioHijoActionPerformed

    private void btAgregarResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarResolucionActionPerformed
        agregarNuevaResolucion();
    }//GEN-LAST:event_btAgregarResolucionActionPerformed

    private void btAgregarCampoAplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarCampoAplicacionActionPerformed
        agregarCampoAplicacion();
    }//GEN-LAST:event_btAgregarCampoAplicacionActionPerformed

    private void btQuitarCampoAplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarCampoAplicacionActionPerformed
        quitarCampoAplicacion();
    }//GEN-LAST:event_btQuitarCampoAplicacionActionPerformed

    private void btAgregarEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarEspecialidadActionPerformed
        agregarEspecialidad();
    }//GEN-LAST:event_btAgregarEspecialidadActionPerformed

    private void btQuitarEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarEspecialidadActionPerformed
        quitarEspecialidad();
    }//GEN-LAST:event_btQuitarEspecialidadActionPerformed

    private void btAgregarUnidadInvestigacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarUnidadInvestigacionActionPerformed
        agregarUnidadInvestigacion();
    }//GEN-LAST:event_btAgregarUnidadInvestigacionActionPerformed

    private void btQuitarUnidadInvestigacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarUnidadInvestigacionActionPerformed
        quitarUnidadInvestigacion();
    }//GEN-LAST:event_btQuitarUnidadInvestigacionActionPerformed

    private void btAgregarConvenioConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarConvenioConActionPerformed
        agregarConvenioCon();
    }//GEN-LAST:event_btAgregarConvenioConActionPerformed

    private void btQuitarConvenioconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarConvenioconActionPerformed
        quitarConvenioCon();
    }//GEN-LAST:event_btQuitarConvenioconActionPerformed

    private void btAgregarSubDisciplinaCientificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarSubDisciplinaCientificaActionPerformed
        agregarSubDisciplinaCientifica();
    }//GEN-LAST:event_btAgregarSubDisciplinaCientificaActionPerformed

    private void btQuitarSubDisciplinaCientificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarSubDisciplinaCientificaActionPerformed
        quitarSubDisciplinaCientifica();
    }//GEN-LAST:event_btQuitarSubDisciplinaCientificaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        aceptar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboUnidadAcademicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadAcademicaItemStateChanged
        cambiarUnidadAcademicaSeleccionada();
    }//GEN-LAST:event_cboUnidadAcademicaItemStateChanged

    private void cboLineaInvestigacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLineaInvestigacionItemStateChanged
        cambiarLineaInvestigacionSeleccionada();
    }//GEN-LAST:event_cboLineaInvestigacionItemStateChanged

    private void cboUnidadEjecutoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadEjecutoraItemStateChanged
        cambiarUnidadEjecutoraSeleccionada();
    }//GEN-LAST:event_cboUnidadEjecutoraItemStateChanged

    private void cboTipoProyectoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoProyectoItemStateChanged
        cambiarTipoProyectoSeleccionado();
    }//GEN-LAST:event_cboTipoProyectoItemStateChanged

    private void cboEntidadEvaluadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboEntidadEvaluadoraItemStateChanged
        cambiarEntidadEvaluadoraSeleccionada();
    }//GEN-LAST:event_cboEntidadEvaluadoraItemStateChanged

    private void cboProgramaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProgramaItemStateChanged
        cambiarProgramaSeleccionado();
    }//GEN-LAST:event_cboProgramaItemStateChanged

    private void btVerDetallesResolucionSeleccionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerDetallesResolucionSeleccionadaActionPerformed
        abrirDocumentos();
    }//GEN-LAST:event_btVerDetallesResolucionSeleccionadaActionPerformed

    private void btVerDetallesEvaluacionSeleccionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerDetallesEvaluacionSeleccionadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btVerDetallesEvaluacionSeleccionadaActionPerformed

    private void btAgregarEvaluacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarEvaluacionActionPerformed
        agregarNuevaEvaluacion();        // TODO add your handling code here:
    }//GEN-LAST:event_btAgregarEvaluacionActionPerformed

    private void btVerDetallesParticipacionSeleccionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerDetallesParticipacionSeleccionadaActionPerformed
        verDetalles();
    }//GEN-LAST:event_btVerDetallesParticipacionSeleccionadaActionPerformed

    private void btAgregarParticipacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarParticipacionActionPerformed
        agregarNuevaParticipacion();       // TODO add your handling code here:
    }//GEN-LAST:event_btAgregarParticipacionActionPerformed

    private void chkParticipacionesActualesOUltimasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkParticipacionesActualesOUltimasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkParticipacionesActualesOUltimasActionPerformed

    private void chkParticipacionesActualesOUltimasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkParticipacionesActualesOUltimasStateChanged
        cambiarEstado();
        // TODO add your handling code here:
    }//GEN-LAST:event_chkParticipacionesActualesOUltimasStateChanged

    private void btnEditarEvaluacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEvaluacionActionPerformed
        modificarEvaluacion();
    }//GEN-LAST:event_btnEditarEvaluacionActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarDocumentacion();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarEvaluacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEvaluacionActionPerformed
        eliminarEvaluacion();
    }//GEN-LAST:event_btnEliminarEvaluacionActionPerformed

    private void jListParticipacionesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListParticipacionesValueChanged
        cambiarSeleccion();        // TODO add your handling code here:
    }//GEN-LAST:event_jListParticipacionesValueChanged

    private void jBtnAgregarResolucionProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarResolucionProyectoActionPerformed
        diagResolucionEleccion res = new diagResolucionEleccion(null, true);
        res.setLocationRelativeTo(this);
        res.setVisible(true);
        if (res.getResolucion() != null) {
            documentoWinsip = res.getResolucion();
            tfDocumentoWinsip.setText(documentoWinsip.toString());
        }
    }//GEN-LAST:event_jBtnAgregarResolucionProyectoActionPerformed

    private void jBtnModificarResolucionProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarResolucionProyectoActionPerformed
        if (documentoWinsip != null) {
            diagResolucionAlta res = new diagResolucionAlta(null, true, "Modificacion", documentoWinsip);
            res.setLocationRelativeTo(this);
            res.setVisible(true);
            documentoWinsip = res.getResolucion();
        }
    }//GEN-LAST:event_jBtnModificarResolucionProyectoActionPerformed

    private void jBtnVerResolucionProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVerResolucionProyectoActionPerformed
        if (documentoWinsip != null) {
            abrirDocumento(documentoWinsip);
        }
    }//GEN-LAST:event_jBtnVerResolucionProyectoActionPerformed

    private void btnQuitarResProyetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarResProyetoActionPerformed
        quitarResolucionProyectos();
    }//GEN-LAST:event_btnQuitarResProyetoActionPerformed

    private void cboEntidadAcreditadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboEntidadAcreditadoraItemStateChanged
        cambiarEntidadAcreditadora();
    }//GEN-LAST:event_cboEntidadAcreditadoraItemStateChanged

    private void btNuevaEntidadAcreditadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaEntidadAcreditadoraActionPerformed
        agregarNuevaEntidadAcreditadora();
    }//GEN-LAST:event_btNuevaEntidadAcreditadoraActionPerformed

    private void btNuevaEntidadFinanciadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaEntidadFinanciadoraActionPerformed
        agregarNuevaEntidadFinanciadora();
    }//GEN-LAST:event_btNuevaEntidadFinanciadoraActionPerformed

    private void cboEntidadFinanciadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboEntidadFinanciadoraItemStateChanged
        cambiarEntidadFinanciadora();
    }//GEN-LAST:event_cboEntidadFinanciadoraItemStateChanged

    private void btnVerMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMapaActionPerformed
        mostrarGoogleMaps();
    }//GEN-LAST:event_btnVerMapaActionPerformed

    private void cboProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProvinciasItemStateChanged
        cargarDepartamentos();
    }//GEN-LAST:event_cboProvinciasItemStateChanged

    private void cboDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDepartamentosItemStateChanged
        cargarLocalidades();
    }//GEN-LAST:event_cboDepartamentosItemStateChanged

    private void cargarProvincias() {
        try {
            Comunes.cargarJComboConBlanco(cboProvincias, ProvinciaFacade.getInstance().listarTodosProvinciaOrdenados());
            cargarDepartamentos();
        } catch (java.lang.ClassCastException ex) {
        }
    }

    private void cargarDepartamentos() {
        try {
            Comunes.cargarJComboConBlanco(cboDepartamentos, DepartamentoFacade.getInstance().
                    listarTodosDepartamentoOrdenados((Provincia) cboProvincias.getSelectedItem()));
            cargarLocalidades();
        } catch (java.lang.ClassCastException ex) {
        }
    }

    private void cargarLocalidades() {
        try {
            Comunes.cargarJComboConBlanco(cboLocalidades, LocalidadFacade.getInstance().listarTodosLocalidadOrdenados((Departamento) cboDepartamentos.getSelectedItem()));
        } catch (java.lang.ClassCastException ex) {
        }

    }

    private void abrirDocumento(Resolucion res) {

        try {

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
            System.out.println("Error abriendo documento: " + ex);
        }
    }

    private void quitarResolucionProyectos() {
        documentoWinsip = null;
        tfDocumentoWinsip.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagProyecto dialog = new diagProyecto(new javax.swing.JFrame(), true, new String(), new Usuario());
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
    private javax.swing.JButton btAgregarCampoAplicacion;
    private javax.swing.JButton btAgregarConvenioCon;
    private javax.swing.JButton btAgregarEspecialidad;
    private javax.swing.JButton btAgregarEvaluacion;
    private javax.swing.JButton btAgregarKeyword;
    private javax.swing.JButton btAgregarPalabraClave;
    private javax.swing.JButton btAgregarParticipacion;
    private javax.swing.JButton btAgregarProyectoComplementarioHijo;
    private javax.swing.JButton btAgregarResolucion;
    private javax.swing.JButton btAgregarSubDisciplinaCientifica;
    private javax.swing.JButton btAgregarUnidadInvestigacion;
    private javax.swing.JButton btNuevaEntidadAcreditadora;
    private javax.swing.JButton btNuevaEntidadEvaluadora;
    private javax.swing.JButton btNuevaEntidadFinanciadora;
    private javax.swing.JButton btNuevaLineaInvestigacion;
    private javax.swing.JButton btNuevaProrroga;
    private javax.swing.JButton btNuevaUnidadAcademica;
    private javax.swing.JButton btNuevaUnidadEjecutora;
    private javax.swing.JButton btNuevoPrograma;
    private javax.swing.JButton btNuevoTipoProyecto;
    private javax.swing.JButton btQuitarCampoAplicacion;
    private javax.swing.JButton btQuitarConveniocon;
    private javax.swing.JButton btQuitarEspecialidad;
    private javax.swing.JButton btQuitarKeyword;
    private javax.swing.JButton btQuitarPalabraClave;
    private javax.swing.JButton btQuitarProyectoComplementarioHijo;
    private javax.swing.JButton btQuitarSubDisciplinaCientifica;
    private javax.swing.JButton btQuitarUnidadInvestigacion;
    private javax.swing.JButton btSeleccionarProyectoComplementarioPadre;
    private javax.swing.JButton btVerDetallesEvaluacionSeleccionada;
    private javax.swing.JButton btVerDetallesParticipacionSeleccionada;
    private javax.swing.JButton btVerDetallesResolucionSeleccionada;
    private javax.swing.JButton btnEditarEvaluacion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarEvaluacion;
    private javax.swing.JButton btnQuitarResProyeto;
    private javax.swing.JButton btnVerMapa;
    private javax.swing.JCheckBox cbProyectoIniciacion;
    private javax.swing.JCheckBox cbProyectoOrientado;
    private javax.swing.JComboBox cboDepartamentos;
    private javax.swing.JComboBox cboEntidadAcreditadora;
    private javax.swing.JComboBox cboEntidadEvaluadora;
    private javax.swing.JComboBox cboEntidadFinanciadora;
    private javax.swing.JComboBox cboLineaInvestigacion;
    private javax.swing.JComboBox cboLocalidades;
    private javax.swing.JComboBox cboObjetivoSocioeconomico;
    private javax.swing.JComboBox cboPrograma;
    private javax.swing.JComboBox cboProvincias;
    private javax.swing.JComboBox cboTipoProyecto;
    private javax.swing.JComboBox cboUnidadAcademica;
    private javax.swing.JComboBox cboUnidadEjecutora;
    private javax.swing.JCheckBox chkParticipacionesActualesOUltimas;
    private org.jdesktop.swingx.JXDatePicker dateFechaFinalizacion;
    private org.jdesktop.swingx.JXDatePicker dateFechaInicio;
    private javax.swing.JButton jBtnAgregarResolucionProyecto;
    private javax.swing.JButton jBtnModificarResolucionProyecto;
    private javax.swing.JButton jBtnVerResolucionProyecto;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListAreasTematicas;
    private javax.swing.JList jListCamposAplicacion;
    private javax.swing.JList jListConveniosCon;
    private javax.swing.JList jListDisciplinasCientificas;
    private javax.swing.JList jListEspecialidades;
    private javax.swing.JList jListKeywords;
    private javax.swing.JList jListPalabrasClaves;
    private javax.swing.JList jListParticipaciones;
    private javax.swing.JList jListProrrogas;
    private javax.swing.JList jListProyectosComplementariosHijos;
    private javax.swing.JList jListResoluciones;
    private javax.swing.JList jListSubDisciplinasCientificas;
    private javax.swing.JList jListUnidadesInvestigacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JScrollPane jScrollPane11;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelDatosComplementarios;
    private javax.swing.JPanel pnDocumentoWinsip;
    private javax.swing.JTextArea taMetodologia;
    private javax.swing.JTextArea taObjetivoEspecificoHipotesis;
    private javax.swing.JTextArea taObjetivoGeneral;
    private javax.swing.JTextArea taObservaciones;
    private javax.swing.JTextArea taRelevanciaProblema;
    private javax.swing.JTextArea taResumen;
    private javax.swing.JTextArea taSummary;
    private javax.swing.JTextArea taTitle;
    private javax.swing.JTextArea taTitulo;
    private javax.swing.JTextField tfAntecedentes;
    private javax.swing.JTextField tfCodigoIncentivos;
    private javax.swing.JTextField tfCodigoProyecto;
    private javax.swing.JTextField tfConvocatoria;
    private javax.swing.JTextField tfDocumentoWinsip;
    private javax.swing.JTextField tfDuracionTeorica;
    private javax.swing.JTextField tfLatitud;
    private javax.swing.JTextField tfLocalizacion;
    private javax.swing.JTextField tfLongitud;
    private javax.swing.JTextField tfPersonalApoyo;
    private javax.swing.JTextField tfProyectoComplementarioPadre;
    private javax.swing.JTextField tfSubDisciplinaCientificaOtra;
    private javax.swing.JTextField tfUnidadEjecutoraOtra;
    private javax.swing.JTextPane tpCronogramaTrabajo;
    private javax.swing.JTextPane tpFormacionRRHH;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        configurarjTextPaneEnHTML();
        cargarProvincias();
        cargarUnidadesAcademicas();
        cargarObjetivosSocioeconomicos();
        cargarAreasTematicas();
        cargarDisciplinasCientificas();
        cargarSubDisciplinasCientificas();
        cargarEspecialidades();
        cargarCamposAplicacion();
        cargarLineasInvestigacion();
        cargarUnidadesInvestigacion();
        cargarUnidadesEjecutoras();
        cargarTiposProyectos();
        cargarProgramas();
        cargarConveniosCon();
        cargarEntidadesEvaluadoras();
        cargarProrrogas();
        cargarEntidadesAcreditadoras();
        cargarEntidadesFinanciadoras();
        cargarTablaEncabezadosEvaluaciones(modeloTabla);
        btVerDetallesEvaluacionSeleccionada.setVisible(false);
        if (tipoOperacion.equals("Alta")) {
            habilitarPanelDatosComplementarios(false);
            proyectoDatosComplementarios = new ProyectoDatosComplementarios();
            btAgregarParticipacion.setVisible(false);
            btVerDetallesParticipacionSeleccionada.setVisible(false);
            chkParticipacionesActualesOUltimas.setVisible(false);
        } else if (tipoOperacion.equals("Modificación")) {
            if (usuario.getGrupo().getNombre().equals("incentivos")) {
                deshabilitarPaneles();
            }
            try {
                cboEntidadAcreditadora.setSelectedItem(proyecto.getEntidadAcreditadora());
            } catch (Exception e) {

            }
            try {
                cboEntidadFinanciadora.setSelectedItem(proyecto.getEntidadFinanciadora());
            } catch (Exception e) {

            }
            try {
                if (proyecto.getProyectoDatosComplementarios() != null) {
                    proyectoDatosComplementarios = proyecto.getProyectoDatosComplementarios();
                    cargarDatosComplementarios();

                } else {
                    proyectoDatosComplementarios = new ProyectoDatosComplementarios();
                }
            } catch (NullPointerException ex) {
            }

            try {
                if (proyecto.getParticipaciones() != null) {
                    participaciones = proyecto.getParticipaciones();
                    cargarParticipaciones();

                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getResoluciones() != null) {
                    resoluciones = proyecto.getResoluciones();
                    cargarResoluciones();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getEvaluaciones() != null) {
                    evaluaciones = proyecto.getEvaluaciones();
                    cargarEvaluaciones();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getTitulo() != null) {
                    taTitulo.setText(proyecto.getTitulo());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getCodigo() != null) {
                    tfCodigoProyecto.setText(proyecto.getCodigo());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getCodigoIncentivos() != null) {
                    tfCodigoIncentivos.setText(proyecto.getCodigoIncentivos());
                }
            } catch (NullPointerException ex) {
            }

            try {
                tfDuracionTeorica.setText(((Integer) proyecto.getDuracionTeorica()).toString());
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getFechaInicio() != null) {
                    dateFechaInicio.setDate(proyecto.getFechaInicio());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getFechaFinalizacion() != null) {
                    dateFechaFinalizacion.setDate(proyecto.getFechaFinalizacion());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getUnidadAcademica() != null) {
                    cargarUnidadesAcademicas();
                    unidadAcademica = proyecto.getUnidadAcademica();
                    cboUnidadAcademica.setSelectedItem(unidadAcademica);
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getObjetivoSocioeconomico() != null) {
                    cargarObjetivosSocioeconomicos();
                    objetivoSocioeconomico = proyecto.getObjetivoSocioeconomico();
                    cboObjetivoSocioeconomico.setSelectedItem(objetivoSocioeconomico);
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getSubDisciplinasCientificas() != null) {
                    subDisciplinasCientificas = proyecto.getSubDisciplinasCientificas();
                    cargarSubDisciplinasCientificas();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getEspecialidades() != null) {
                    especialidades = proyecto.getEspecialidades();
                    cargarEspecialidades();

                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getCamposAplicacion() != null) {
                    camposAplicacion = proyecto.getCamposAplicacion();
                    cargarCamposAplicacion();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getLineaInvestigacion() != null) {
                    cboLineaInvestigacion.setSelectedItem(proyecto.getLineaInvestigacion());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getUnidadesInvestigacion() != null) {
                    unidadesInvestigacion = proyecto.getUnidadesInvestigacion();
                    cargarUnidadesInvestigacion();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getUnidadEjecutora() != null) {
                    cboUnidadEjecutora.setSelectedItem(proyecto.getUnidadEjecutora());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getTipoProyecto() != null) {
                    cboTipoProyecto.setSelectedItem(proyecto.getTipoProyecto());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getPrograma() != null) {
                    cboPrograma.setSelectedItem(proyecto.getPrograma());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getConveniosCon() != null) {
                    conveniosCon = proyecto.getConveniosCon();
                    cargarConveniosCon();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getEntidadEvaluadora() != null) {
                    cboEntidadEvaluadora.setSelectedItem(proyecto.getEntidadEvaluadora());
                }
            } catch (NullPointerException ex) {
            }

            try {
                if (proyecto.getPalabrasClaves() != null) {
                    Comunes.cargarJList(jListPalabrasClaves, proyecto.getPalabrasClaves());
                    palabrasClaves = proyecto.getPalabrasClaves();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getObservaciones() != null) {
                    taObservaciones.setText(proyecto.getObservaciones());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getTitle() != null) {
                    taTitle.setText(proyecto.getTitle());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getSummary() != null) {
                    taSummary.setText(proyecto.getSummary());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getResumen() != null) {
                    taResumen.setText(proyecto.getResumen());
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getKeywords() != null) {
                    Comunes.cargarJList(jListKeywords, proyecto.getKeywords());
                    keyWords = proyecto.getKeywords();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getProyectosComplementarios() != null) {
                    Comunes.cargarJList(jListProyectosComplementariosHijos, proyecto.getProyectosComplementarios());
                    proyectosComplementariosHijos = proyecto.getProyectosComplementarios();
                }
            } catch (NullPointerException ex) {
            }
            try {
                tfPersonalApoyo.setText(String.valueOf(proyecto.getPersonalApoyo()));
            } catch (NullPointerException ex) {
            }

            try {
                if (proyecto.getProyectoComplementarioPadre() != null) {
                    tfProyectoComplementarioPadre.setText(proyecto.getProyectoComplementarioPadre().toString());
                    proyectoComplementarioPadre = proyecto.getProyectoComplementarioPadre();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (!proyecto.getProrrogas().isEmpty()) {
                    prorrogas = proyecto.getProrrogas();
                    cargarProrrogas();
                }
            } catch (NullPointerException ex) {
            }
            try {
                if (proyecto.getDocumentoWinsip() != null) {
                    documentoWinsip = proyecto.getDocumentoWinsip();
                    tfDocumentoWinsip.setText(documentoWinsip.getDescripcion());
                }
            } catch (NullPointerException ex) {
            }

        }
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

    private void cargarEspecialidades() {
        Comunes.cargarJList(jListEspecialidades, especialidades);
    }

    private void cargarCamposAplicacion() {
        Comunes.cargarJList(jListCamposAplicacion, camposAplicacion);
    }

    private void cargarLineasInvestigacion() {
        Comunes.cargarJComboConBlanco(cboLineaInvestigacion, LineaInvestigacionFacade.getInstance().getTodosLineaInvestigacion());
    }

    private void cargarUnidadesInvestigacion() {
        Comunes.cargarJList(jListUnidadesInvestigacion, unidadesInvestigacion);
    }

    private void cargarUnidadesEjecutoras() {
        Comunes.cargarJComboConBlanco(cboUnidadEjecutora, UnidadEjecutoraFacade.getInstance().getTodosUnidadEjecutora());
    }

    private void cargarTiposProyectos() {
        Comunes.cargarJComboConBlanco(cboTipoProyecto, TipoProyectoFacade.getInstance().getTodosTipoProyecto());
    }

    private void cargarProgramas() {
        Comunes.cargarJComboConBlanco(cboPrograma, ProgramaFacade.getInstance().getTodosPrograma());
    }

    private void cargarConveniosCon() {
        Comunes.cargarJList(jListConveniosCon, conveniosCon);
    }

    private void cargarEntidadesEvaluadoras() {
        Comunes.cargarJComboConBlanco(cboEntidadEvaluadora, EntidadEvaluadoraFacade.getInstance().getTodosEntidadEvaluadora());
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

    private void agregarNuevaAreaTematica() {
        diagAreaTematica areaTematicaAlta = new diagAreaTematica(null, true, "Alta");
        areaTematicaAlta.setLocation(Comunes.centrarDialog(areaTematicaAlta));
        areaTematicaAlta.setVisible(true);
        cargarAreasTematicas();
    }

    private void agregarNuevaDisciplinaCientifica() {
        diagDisciplinaCientifica disciplinaCientificaAlta = new diagDisciplinaCientifica(null, true, "Alta");
        disciplinaCientificaAlta.setLocation(Comunes.centrarDialog(disciplinaCientificaAlta));
        disciplinaCientificaAlta.setVisible(true);
        cargarDisciplinasCientificas();
    }

    private void agregarNuevaSubDisciplinaCientifica() {
        diagSubDisciplinaCientifica subDisciplinaCientificaAlta = new diagSubDisciplinaCientifica(null, true, "Alta");
        subDisciplinaCientificaAlta.setLocation(Comunes.centrarDialog(subDisciplinaCientificaAlta));
        subDisciplinaCientificaAlta.setVisible(true);
        cargarSubDisciplinasCientificas();
    }

    private void agregarNuevaEspecialidad() {
        diagEspecialidad especialidadAlta = new diagEspecialidad(null, true, "Alta");
        especialidadAlta.setLocation(Comunes.centrarDialog(especialidadAlta));
        especialidadAlta.setVisible(true);
        cargarEspecialidades();
    }

    private void agregarNuevoCampoAplicacion() {
        diagCampoAplicacion campoAplicacionAlta = new diagCampoAplicacion(null, true, "Alta");
        campoAplicacionAlta.setLocation(Comunes.centrarDialog(campoAplicacionAlta));
        campoAplicacionAlta.setVisible(true);
        cargarCamposAplicacion();
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

    private void agregarNuevaUnidadInvestigacion() {
        diagUnidadInvestigacion unidadInvestigacionAlta = new diagUnidadInvestigacion(null, true, "Alta");
        unidadInvestigacionAlta.setLocation(Comunes.centrarDialog(unidadInvestigacionAlta));
        unidadInvestigacionAlta.setVisible(true);
        cargarUnidadesInvestigacion();
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

    private void agregarNuevoTipoProyecto() {
        diagTipoProyecto tipoProyectoAlta = new diagTipoProyecto(null, true, "Alta");
        tipoProyectoAlta.setLocation(Comunes.centrarDialog(tipoProyectoAlta));
        tipoProyectoAlta.setVisible(true);
        cargarTiposProyectos();
        if (tipoProyectoAlta.getTipoProyectoCreada() != null) {
            cboTipoProyecto.setSelectedItem(tipoProyectoAlta.getTipoProyectoCreada());
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

    private void agregarNuevoConvenioCon() {
        diagEntidadConvenio entidadConvenioAlta = new diagEntidadConvenio(null, true, "Alta");
        entidadConvenioAlta.setLocation(Comunes.centrarDialog(entidadConvenioAlta));
        entidadConvenioAlta.setVisible(true);
        cargarConveniosCon();
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

    private void agregarNuevaProrroga() {
        diagProrroga prorrogaNueva = new diagProrroga(null, true, "Alta");
        prorrogaNueva.setLocation(Comunes.centrarDialog(prorrogaNueva));
        prorrogaNueva.setVisible(true);
        if (prorrogaNueva.getProrrogaCreada() != null) {
            prorrogas.add(prorrogaNueva.getProrrogaCreada());
            cargarProrrogas();
        }
    }

    private void agregarNuevaResolucion() {
        diagResolucionEleccion resolucionEleccion = new diagResolucionEleccion(null, true);
        resolucionEleccion.setLocation(Comunes.centrarDialog(resolucionEleccion));
        resolucionEleccion.setVisible(true);
        if (resolucionEleccion.getResolucion() != null) {
            resoluciones.add(resolucionEleccion.getResolucion());
            proyecto.setResoluciones(resoluciones);
            cargarResoluciones();
        }
    }

    private void cargarProrrogas() {
        Comunes.cargarJList(jListProrrogas, prorrogas);
    }

    private void cargarResoluciones() {
        Comunes.cargarJList(jListResoluciones, resoluciones);
    }

    private void agregarPalabraClave() {
        palabrasClaves.add(JOptionPane.showInputDialog("Inserte una palabra clave"));
        Comunes.cargarJList(jListPalabrasClaves, palabrasClaves);
    }

    private void agregarKeyWords() {
        keyWords.add(JOptionPane.showInputDialog("Inserte una palabra clave"));
        Comunes.cargarJList(jListKeywords, keyWords);
    }

    private void borrarPalabraClave() {
        if (jListPalabrasClaves.getSelectedIndex() != -1) {
            palabrasClaves.remove((String) jListPalabrasClaves.getSelectedValue());
            Comunes.cargarJList(jListPalabrasClaves, palabrasClaves);
        }
    }

    private void borrarKeyWord() {
        if (jListKeywords.getSelectedIndex() != -1) {
            keyWords.remove((String) jListKeywords.getSelectedValue());
            Comunes.cargarJList(jListKeywords, keyWords);
        }
    }

    private void seleccionarProyectoComplementarioPadre() {
        Proyecto proyectoBuscado = buscarProyecto();
        if (proyectoBuscado != null) {
            proyectoComplementarioPadre = proyectoBuscado;
            tfProyectoComplementarioPadre.setText(proyectoBuscado.toString());
        }
    }

    private void agregarProyectosComplementariosHijos() {
        Proyecto proyectoBuscado = buscarProyecto();
        if (proyectoBuscado != null) {
            proyectosComplementariosHijos.add(proyectoBuscado);
            Comunes.cargarJList(jListProyectosComplementariosHijos, proyectosComplementariosHijos);
        }
    }

    private void borrarProyectosComplementariosHijos() {
        if (jListProyectosComplementariosHijos.getSelectedIndex() != -1) {
            proyectosComplementariosHijos.remove(jListProyectosComplementariosHijos.getSelectedValue());
            Comunes.cargarJList(jListProyectosComplementariosHijos, proyectosComplementariosHijos);
        }
    }

    private Proyecto buscarProyecto() {
        diagProyectoBusquedaSimple proyectoBusquedaSimple = new diagProyectoBusquedaSimple(null, true);
        proyectoBusquedaSimple.setLocation(Comunes.centrarDialog(proyectoBusquedaSimple));
        proyectoBusquedaSimple.setVisible(true);
        return proyectoBusquedaSimple.getProyecto();
    }

    private void agregarCampoAplicacion() {
        diagCampoAplicacionEleccion campoAplicacionEleccion = new diagCampoAplicacionEleccion(null, true, camposAplicacion);
        campoAplicacionEleccion.setLocation(Comunes.centrarDialog(campoAplicacionEleccion));
        campoAplicacionEleccion.setVisible(true);
        if (campoAplicacionEleccion.getCampoAplicacionEscogido() != null) {
            camposAplicacion.add(campoAplicacionEleccion.getCampoAplicacionEscogido());
            cargarCamposAplicacion();
        }
    }

    private void quitarCampoAplicacion() {
        if (jListCamposAplicacion.getSelectedIndex() != -1) {
            camposAplicacion.remove(jListCamposAplicacion.getSelectedValue());
            cargarCamposAplicacion();
        }
    }

    private void agregarEspecialidad() {
        diagEspecialidadEleccion especialidadEleccion = new diagEspecialidadEleccion(null, true, especialidades);
        especialidadEleccion.setLocation(Comunes.centrarDialog(especialidadEleccion));
        especialidadEleccion.setVisible(true);
        if (especialidadEleccion.getEspecialidadEscogido() != null) {
            especialidades.add(especialidadEleccion.getEspecialidadEscogido());
            cargarEspecialidades();
        }
    }

    private void quitarEspecialidad() {
        if (jListEspecialidades.getSelectedIndex() != -1) {
            especialidades.remove(jListEspecialidades.getSelectedValue());
            cargarEspecialidades();
        }
    }

    private void agregarUnidadInvestigacion() {
        diagUnidadInvestigacionEleccion unidadInvestigacionEleccion = new diagUnidadInvestigacionEleccion(null, true, unidadesInvestigacion);
        unidadInvestigacionEleccion.setLocation(Comunes.centrarDialog(unidadInvestigacionEleccion));
        unidadInvestigacionEleccion.setVisible(true);
        if (unidadInvestigacionEleccion.getUnidadInvestigacionEscogido() != null) {
            unidadesInvestigacion.add(unidadInvestigacionEleccion.getUnidadInvestigacionEscogido());
            cargarUnidadesInvestigacion();
        }

    }

    private void quitarUnidadInvestigacion() {
        if (jListUnidadesInvestigacion.getSelectedIndex() != -1) {
            unidadesInvestigacion.remove(jListUnidadesInvestigacion.getSelectedValue());
            cargarUnidadesInvestigacion();
        }
    }

    private void agregarConvenioCon() {
        diagEntidadConvenioEleccion entidadConvenioEleccion = new diagEntidadConvenioEleccion(null, true, conveniosCon);
        entidadConvenioEleccion.setLocation(Comunes.centrarDialog(entidadConvenioEleccion));
        entidadConvenioEleccion.setVisible(true);
        if (entidadConvenioEleccion.getEntidadConvenioEscogido() != null) {
            conveniosCon.add(entidadConvenioEleccion.getEntidadConvenioEscogido());
            cargarConveniosCon();
        }
    }

    private void quitarConvenioCon() {
        if (jListConveniosCon.getSelectedIndex() != -1) {
            conveniosCon.remove(jListConveniosCon.getSelectedValue());
            cargarConveniosCon();
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

    private void quitarSubDisciplinaCientifica() {
        if (jListSubDisciplinasCientificas.getSelectedIndex() != -1) {
            subDisciplinasCientificas.remove(jListSubDisciplinasCientificas.getSelectedValue());
            cargarSubDisciplinasCientificas();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una subdisciplina científica", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cambiarUnidadAcademicaSeleccionada() {
        if (cboUnidadAcademica.getSelectedIndex() > 0) {
            unidadAcademica = (UnidadAcademica) cboUnidadAcademica.getSelectedItem();
        } else {
            unidadAcademica = null;
        }
    }

    private void cambiarLineaInvestigacionSeleccionada() {
        if (cboLineaInvestigacion.getSelectedIndex() > 0) {
            lineaInvestigacion = (LineaInvestigacion) cboLineaInvestigacion.getSelectedItem();
        } else {
            lineaInvestigacion = null;
        }
    }

    private void cambiarUnidadEjecutoraSeleccionada() {
        if (cboUnidadEjecutora.getSelectedIndex() > 0) {
            unidadEjecutora = (UnidadEjecutora) cboUnidadEjecutora.getSelectedItem();
        } else {
            unidadEjecutora = null;
        }
    }

    private void cambiarTipoProyectoSeleccionado() {
        if (cboTipoProyecto.getSelectedIndex() > 0) {
            tipoProyecto = (TipoProyecto) cboTipoProyecto.getSelectedItem();
        } else {
            tipoProyecto = null;
        }
    }

    private void cambiarEntidadEvaluadoraSeleccionada() {
        if (cboEntidadEvaluadora.getSelectedIndex() > 0) {
            entidadEvaluadora = (EntidadEvaluadora) cboEntidadEvaluadora.getSelectedItem();
        } else {
            entidadEvaluadora = null;
        }
    }

    private void cambiarProgramaSeleccionado() {
        if (cboPrograma.getSelectedIndex() > 0) {
            programa = (Programa) cboPrograma.getSelectedItem();
        } else {
            programa = null;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="comment">
    //</editor-fold>
    private void aceptar() {
        if (validar()) {
            Operacion operacion = new Operacion();
            switch (tipoOperacion) {
                case "Alta":
                    proyecto = new Proyecto();
                    if (entidadAcreditadora != null) {
                        proyecto.setEntidadAcreditadora(entidadAcreditadora);
                    } else {
                        proyecto.setEntidadAcreditadora(null);
                    }
                    if (entidadFinanciadora != null) {
                        proyecto.setEntidadFinanciadora(entidadFinanciadora);
                    } else {
                        proyecto.setEntidadFinanciadora(null);
                    }
                    if (participaciones.size() > 0) {
                        proyecto.setParticipaciones(participaciones);
                    } else {
                        proyecto.setParticipaciones(null);
                    }
                    //setearResoluciones
                    if (resoluciones.size() > 0) {
                        proyecto.setResoluciones(resoluciones);
                    } else {
                        proyecto.setResoluciones(null);
                    }
                    //Lo seteamos cuando agregamos una nueva
                    if (evaluaciones.size() > 0) {
                        proyecto.setEvaluaciones(evaluaciones);
                    } else {
                        proyecto.setEvaluaciones(null);
                    }
                    if (!tfCodigoProyecto.getText().equals("")) {
                        proyecto.setCodigo(tfCodigoProyecto.getText());
                    } else {
                        proyecto.setCodigo(null);
                    }
                    if (!tfCodigoIncentivos.getText().equals("")) {
                        proyecto.setCodigoIncentivos(tfCodigoIncentivos.getText());
                    } else {
                        proyecto.setCodigoIncentivos(null);
                    }
                    if (!taTitulo.getText().equals("")) {
                        proyecto.setTitulo(taTitulo.getText());
                    } else {
                        proyecto.setTitulo(null);
                    }
                    if (unidadAcademica != null) {
                        proyecto.setUnidadAcademica(unidadAcademica);
                    } else {
                        proyecto.setUnidadAcademica(null);
                    }
                    if (IntegerValidator.getInstance().isValid(tfDuracionTeorica.getText())) {
                        proyecto.setDuracionTeorica(Integer.parseInt(tfDuracionTeorica.getText()));
                    } else {
                        proyecto.setDuracionTeorica(0);
                    }
                    if (dateFechaInicio.getDate() != null) {
                        proyecto.setFechaInicio(dateFechaInicio.getDate());
                    } else {
                        proyecto.setFechaInicio(null);
                    }
                    if (dateFechaFinalizacion.getDate() != null) {
                        proyecto.setFechaFinalizacion(dateFechaFinalizacion.getDate());
                    } else {
                        proyecto.setFechaFinalizacion(null);
                    }
                    if (prorrogas.size() > 0) {
                        proyecto.setProrrogas(prorrogas);
                    } else {
                        proyecto.setProrrogas(null);
                    }
                    if (lineaInvestigacion != null) {
                        proyecto.setLineaInvestigacion(lineaInvestigacion);
                    } else {
                        proyecto.setLineaInvestigacion(null);
                    }
                    if (unidadEjecutora != null) {
                        proyecto.setUnidadEjecutora(unidadEjecutora);
                    } else {
                        proyecto.setUnidadEjecutora(null);
                    }
                    if (cboObjetivoSocioeconomico.getSelectedIndex() > 0) {
                        objetivoSocioeconomico = (ObjetivoSocioeconomico) cboObjetivoSocioeconomico.getSelectedItem();
                        proyecto.setObjetivoSocioeconomico(objetivoSocioeconomico);
                    } else {
                        proyecto.setObjetivoSocioeconomico(null);
                    }
                    if (tipoProyecto != null) {
                        proyecto.setTipoProyecto(tipoProyecto);
                    } else {
                        proyecto.setTipoProyecto(null);
                    }
                    if (entidadEvaluadora != null) {
                        proyecto.setEntidadEvaluadora(entidadEvaluadora);
                    } else {
                        proyecto.setEntidadEvaluadora(null);
                    }
                    if (programa != null) {
                        proyecto.setPrograma(programa);
                    } else {
                        proyecto.setPrograma(null);
                    }
                    if (camposAplicacion.size() > 0) {
                        proyecto.setCamposAplicacion(camposAplicacion);
                    } else {
                        proyecto.setCamposAplicacion(null);
                    }
                    if (especialidades.size() > 0) {
                        proyecto.setEspecialidades(especialidades);
                    } else {
                        proyecto.setEspecialidades(null);
                    }
                    if (unidadesInvestigacion.size() > 0) {
                        proyecto.setUnidadesInvestigacion(unidadesInvestigacion);
                    } else {
                        proyecto.setUnidadesInvestigacion(null);
                    }
                    if (conveniosCon.size() > 0) {
                        proyecto.setConveniosCon(conveniosCon);
                    } else {
                        proyecto.setConveniosCon(null);
                    }
                    if (subDisciplinasCientificas.size() > 0) {
                        proyecto.setSubDisciplinasCientificas(subDisciplinasCientificas);
                    } else {
                        proyecto.setSubDisciplinasCientificas(null);
                    }
                    if (!taResumen.getText().equals("")) {
                        proyecto.setResumen(taResumen.getText());
                    } else {
                        proyecto.setResumen(null);
                    }
                    if (palabrasClaves.size() > 0) {
                        proyecto.setPalabrasClaves(palabrasClaves);
                    } else {
                        proyecto.setPalabrasClaves(null);
                    }
                    if (!taObservaciones.getText().equals("")) {
                        proyecto.setObservaciones(taObservaciones.getText());
                    } else {
                        proyecto.setObservaciones(null);
                    }
                    if (!taTitle.getText().equals("")) {
                        proyecto.setTitle(taTitle.getText());
                    } else {
                        proyecto.setTitle(null);
                    }
                    if (!taSummary.getText().equals("")) {
                        proyecto.setSummary(taSummary.getText());
                    } else {
                        proyecto.setSummary(null);
                    }
                    if (keyWords.size() > 0) {
                        proyecto.setKeywords(keyWords);
                    } else {
                        proyecto.setKeywords(null);
                    }
                    if (proyectoComplementarioPadre != null) {
                        proyecto.setProyectoComplementarioPadre(proyectoComplementarioPadre);
                    } else {
                        proyecto.setProyectoComplementarioPadre(null);
                    }
                    if (proyectosComplementariosHijos.size() > 0) {
                        proyecto.setProyectosComplementarios(proyectosComplementariosHijos);
                    }
                    if (documentoWinsip != null) {
                        proyecto.setDocumentoWinsip(documentoWinsip);
                    }
                    setearDatosComplementarios();
                    ProyectoFacade.getInstance().alta(proyecto);
                    operacion = new Operacion();
                    operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                    operacion.setOperacion("Alta de Proyecto");
                    operacion.setUsuario(usuario);
                    OperacionFacade.getInstance().alta(operacion);
                    this.dispose();
                    break;
                case "Modificación":
                    proyecto.setParticipaciones(participaciones);
                    //Lo seteamos cuando agregamos una nueva
                    proyecto.setResoluciones(resoluciones);
                    proyecto.setEvaluaciones(evaluaciones);
                    if (entidadAcreditadora != null) {
                        proyecto.setEntidadAcreditadora(entidadAcreditadora);
                    } else {
                        proyecto.setEntidadAcreditadora(null);
                    }
                    if (entidadFinanciadora != null) {
                        proyecto.setEntidadFinanciadora(entidadFinanciadora);
                    } else {
                        proyecto.setEntidadFinanciadora(null);
                    }
                    if (!tfCodigoProyecto.getText().equals("")) {
                        proyecto.setCodigo(tfCodigoProyecto.getText());
                    } else {
                        proyecto.setCodigo(null);
                    }
                    if (!tfCodigoIncentivos.getText().equals("")) {
                        proyecto.setCodigoIncentivos(tfCodigoIncentivos.getText());
                    } else {
                        proyecto.setCodigoIncentivos(null);
                    }
                    if (!taTitulo.getText().equals("")) {
                        proyecto.setTitulo(taTitulo.getText());
                    } else {
                        proyecto.setTitulo(null);
                    }
                    if (unidadAcademica != null) {
                        proyecto.setUnidadAcademica(unidadAcademica);
                    } else {
                        proyecto.setUnidadAcademica(null);
                    }
                    if (cboObjetivoSocioeconomico.getSelectedIndex() > 0) {
                        objetivoSocioeconomico = (ObjetivoSocioeconomico) cboObjetivoSocioeconomico.getSelectedItem();
                        proyecto.setObjetivoSocioeconomico(objetivoSocioeconomico);
                    } else {
                        proyecto.setObjetivoSocioeconomico(null);
                    }
                    if (IntegerValidator.getInstance().isValid(tfDuracionTeorica.getText())) {
                        proyecto.setDuracionTeorica(Integer.parseInt(tfDuracionTeorica.getText()));
                    } else {
                        proyecto.setDuracionTeorica(0);
                    }
                    if (dateFechaInicio.getDate() != null) {
                        proyecto.setFechaInicio(dateFechaInicio.getDate());
                    } else {
                        proyecto.setFechaInicio(null);
                    }
                    if (dateFechaFinalizacion.getDate() != null) {
                        proyecto.setFechaFinalizacion(dateFechaFinalizacion.getDate());
                    } else {
                        proyecto.setFechaFinalizacion(null);
                    }
                    if (prorrogas.size() > 0) {
                        proyecto.setProrrogas(prorrogas);
                    } else {
                        proyecto.setProrrogas(null);
                    }
                    if (lineaInvestigacion != null) {
                        proyecto.setLineaInvestigacion(lineaInvestigacion);
                    } else {
                        proyecto.setLineaInvestigacion(null);
                    }
                    if (unidadEjecutora != null) {
                        proyecto.setUnidadEjecutora(unidadEjecutora);
                    } else {
                        proyecto.setUnidadEjecutora(null);
                    }
                    if (tipoProyecto != null) {
                        proyecto.setTipoProyecto(tipoProyecto);
                    } else {
                        proyecto.setTipoProyecto(null);
                    }
                    if (entidadEvaluadora != null) {
                        proyecto.setEntidadEvaluadora(entidadEvaluadora);
                    } else {
                        proyecto.setEntidadEvaluadora(null);
                    }
                    if (programa != null) {
                        proyecto.setPrograma(programa);
                    } else {
                        proyecto.setPrograma(null);
                    }
                    if (camposAplicacion.size() > 0) {
                        proyecto.setCamposAplicacion(camposAplicacion);
                    } else {
                        proyecto.setCamposAplicacion(null);
                    }
                    if (especialidades.size() > 0) {
                        proyecto.setEspecialidades(especialidades);
                    } else {
                        proyecto.setEspecialidades(null);
                    }
                    if (unidadesInvestigacion.size() > 0) {
                        proyecto.setUnidadesInvestigacion(unidadesInvestigacion);
                    } else {
                        proyecto.setUnidadesInvestigacion(null);
                    }
                    if (conveniosCon.size() > 0) {
                        proyecto.setConveniosCon(conveniosCon);
                    } else {
                        proyecto.setConveniosCon(null);
                    }
                    if (subDisciplinasCientificas.size() > 0) {
                        proyecto.setSubDisciplinasCientificas(subDisciplinasCientificas);
                    } else {
                        proyecto.setSubDisciplinasCientificas(null);
                    }
                    if (!taResumen.getText().equals("")) {
                        proyecto.setResumen(taResumen.getText());
                    } else {
                        proyecto.setResumen(null);
                    }
                    if (palabrasClaves.size() > 0) {
                        proyecto.setPalabrasClaves(palabrasClaves);
                    } else {
                        proyecto.setPalabrasClaves(null);
                    }
                    if (!taObservaciones.getText().equals("")) {
                        proyecto.setObservaciones(taObservaciones.getText());
                    } else {
                        proyecto.setObservaciones(null);
                    }
                    if (!taTitle.getText().equals("")) {
                        proyecto.setTitle(taTitle.getText());
                    } else {
                        proyecto.setTitle(null);
                    }
                    if (!taSummary.getText().equals("")) {
                        proyecto.setSummary(taSummary.getText());
                    } else {
                        proyecto.setSummary(null);
                    }
                    if (keyWords.size() > 0) {
                        proyecto.setKeywords(keyWords);
                    } else {
                        proyecto.setKeywords(null);
                    }
                    if (proyectoComplementarioPadre != null) {
                        proyecto.setProyectoComplementarioPadre(proyectoComplementarioPadre);
                    } else {
                        proyecto.setProyectoComplementarioPadre(null);
                    }
                    if (proyectosComplementariosHijos.size() > 0) {
                        proyecto.setProyectosComplementarios(proyectosComplementariosHijos);
                    }
                    if (documentoWinsip != null) {
                        proyecto.setDocumentoWinsip(documentoWinsip);
                    } else {
                        proyecto.setDocumentoWinsip(null);
                    }
                    try {
                        setearDatosComplementarios();
                        ProyectoFacade.getInstance().modificar(proyecto);
                        operacion = new Operacion();
                        operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                        operacion.setOperacion("Modificación de Proyecto");
                        operacion.setUsuario(usuario);
                        OperacionFacade.getInstance().alta(operacion);
                        System.out.println("Modificado con éxito");
                    } catch (Exception ex) {
                        Comunes.mensajeError(ex, "Error Modificando Proyecto");
                    }
                    this.dispose();
                    break;
            }
        }
    }

    private boolean validar() {
        boolean flag = false;
        // el primer if evita un nullpoint cuando haces proyecto.getcodigo() en el segundo if
        boolean valido = false;
        try {
            if (proyecto.getCodigo() != null) {
                valido = proyecto.getCodigo().equals(tfCodigoProyecto.getText());
            }
        } catch (java.lang.NullPointerException ex) {
        }
        if (!ProyectoFacade.getInstance().existeCodigo(tfCodigoProyecto.getText()) || valido) {
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
            try {
                Proyecto proyectoConMismoCodigo = ProyectoFacade.getInstance().buscarPorCodigo(tfCodigoProyecto.getText());
                JOptionPane.showMessageDialog(null, "Ya existe un proyecto con el código " + tfCodigoProyecto.getText() + ": "
                        + proyectoConMismoCodigo.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                tfCodigoProyecto.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no existe codigo " + ex);
            }

        }
        return flag;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    private void agregarNuevaParticipacion() {

        diagParticipacion participacion = new diagParticipacion(null, true, proyecto, usuario, "Alta");
        participacion.setLocation(Comunes.centrarDialog(participacion));
        participacion.setVisible(true);

        if (participacion.isGuardado()) {
            participaciones.add(participacion.getParticipacion());
        }

        cargarParticipaciones();
    }

    private void agregarNuevaEvaluacion() {
        diagEvaluacion evaluacion = new diagEvaluacion(null, true, "Alta", proyecto, usuario);
        evaluacion.setLocation(Comunes.centrarDialog(evaluacion));
        evaluacion.setVisible(true);
        if (evaluacion.getEvaluacionCreada() != null) {
            evaluaciones.add(evaluacion.getEvaluacionCreada());
            cargarEvaluacionALaTabla(evaluacion.getEvaluacionCreada());
        }

    }

    private void cargarParticipaciones() {
        Comunes.cargarJList(jListParticipaciones, participaciones);
//          diagParticipacion participacion = new diagParticipacion(null, true, proyecto, usuario);
//        participacion.setLocation(Comunes.centrarDialog(participacion));
//        participacion.setVisible(true);
//
//        if (participacion.isGuardado()) {
//            participaciones.add(participacion.getParticipacion());
//        }
//
//        Comunes.cargarJList(jListParticipaciones, participaciones);
    }

    private void cargarEvaluaciones() {
        cargarTablaEvaluaciones();

    }

    private void mostrarParticipacionesActualesOUltimas() {
        List<Participacion> listaP = new ArrayList<Participacion>();
        Iterator i = participaciones.iterator();
        while (i.hasNext()) {
            Participacion p = (Participacion) i.next();
            if (p.getFechaHasta() == null) {
                listaP.add(p);

            }
            Comunes.cargarJList(jListParticipaciones, listaP);

        }
    }

    private void cambiarEstado() {
        if (chkParticipacionesActualesOUltimas.isSelected()) {
            mostrarParticipacionesActualesOUltimas();
        } else {
            cargarParticipaciones();
        }

    }

    public void cargarTablaEvaluaciones() {
        //  jTable1.setRowHeight(50);
        //  jTable1.setDefaultRenderer(String.class,new MultiLineaCellRenderer());

        //jTable1.getColumnModel().getColumn(0).setCellRenderer(
        // new MultiLineaCellRenderer());
        Object[] fila = new Object[4];
        if (!evaluaciones.isEmpty()) {

            for (int i = 0; i < evaluaciones.size(); i++) {

                fila[0] = evaluaciones.get(i).getId();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    fila[1] = formato.format(evaluaciones.get(i).getFecha());
                } catch (Exception ex) {
                    fila[1] = "";
                }

                for (int j = 0; j < evaluaciones.get(i).getEvaluacionIndividual().size(); j++) {
                    fila[2] = evaluaciones.get(i).getEvaluacionIndividual().get(j).getEvaluador();
                    fila[3] = evaluaciones.get(i).getEvaluacionIndividual().get(j).getNota();
                    modeloTabla.addRow(fila);
                }
            }
        }

    }

    private void cargarTablaEncabezadosEvaluaciones(DefaultTableModel modeloTabla) {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Evaluador");
        modeloTabla.addColumn("Nota");
        jTable1.setModel(modeloTabla);

    }

    private void verDetalles() {

        //if(jListParticipaciones.getSelectedIndex() != -1){
        //  Participacion p = (Participacion) jListParticipaciones.getSelectedValue();
        diagParticipacionesPorInvestigador diagParticipaciones
                = new diagParticipacionesPorInvestigador(null, true, participacion.getInvestigador());
        diagParticipaciones.setVisible(true);

//        }
    }

    private void cargarEvaluacionALaTabla(Evaluacion evaluacionCreada) {
        Object[] fila = new Object[4];

        fila[0] = evaluacionCreada.getId();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String fechaFormateada = sdf.format(evaluacionCreada.getFecha());
            fila[1] = fechaFormateada;
        } catch (Exception ex) {
            fila[1] = "";
        }

        //c.setTime(evaluacionCreada.getFecha());
        //fila[1] = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        for (int j = 0; j < evaluacionCreada.getEvaluacionIndividual().size(); j++) {
            fila[2] = evaluacionCreada.getEvaluacionIndividual().get(j).getEvaluador();
            fila[3] = evaluacionCreada.getEvaluacionIndividual().get(j).getNota();
            modeloTabla.addRow(fila);
        }
    }

    private void abrirDocumentos() {
        if (jListResoluciones.getSelectedIndex() != -1) //deberás hacer 
        {
            try {
                Resolucion resolucion = (Resolucion) jListResoluciones.getSelectedValue();
                Documento documento = resolucion.getDocumento();
                byte[] archivoInterno = documento.getContenidoArchivo();
                File archivo = File.createTempFile("tmp", documento.getNombreArchivo());
                archivo.deleteOnExit();
                try (FileOutputStream fos = new FileOutputStream(archivo)) {
                    fos.write(archivoInterno);
                }
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
                Comunes.mensajeError(ex, "No se pudo abrir el documento seleccionado");
            } catch (Exception ex) {
                Comunes.mensajeError(ex, "No se pudo abrir el documento seleccionado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void cargarObjetivosSocioeconomicos() {
        Comunes.cargarJComboConBlanco(cboObjetivoSocioeconomico, ObjetivoSocioeconomicoFacade.getInstance().getTodas());

    }

    private void modificarEvaluacion() {
        if (jTable1.getSelectedRow() != -1) {
            evaluacion = EvaluacionFacade.getInstance().buscar((Long) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            diagEvaluacion diagevaluacion = new diagEvaluacion(null, true, "Modificación", proyecto, evaluacion, usuario);
            diagevaluacion.setLocation(Comunes.centrarDialog(diagevaluacion));
            diagevaluacion.setVisible(true);
            if (diagevaluacion.getEvaluacionCreada() != null) {
                evaluaciones = new ArrayList<>();
                evaluaciones.add(diagevaluacion.getEvaluacionCreada());
                modeloTabla = new DefaultTableModel();
                cargarTablaEncabezadosEvaluaciones(modeloTabla);
                cargarEvaluacionALaTabla(diagevaluacion.getEvaluacionCreada());

            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una evaluacion");
        }
    }

    private void eliminarDocumentacion() {
        if (jListResoluciones.getSelectedIndex() != -1) {
            proyecto.getResoluciones().remove((Resolucion) jListResoluciones.getSelectedValue());
            cargarResoluciones();

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento de la lista \n"
                    + "para poder eliminarlo");
        }

        /**
         * List<Resolucion> resoluciones; if (resolucionEleccion.getResolucion()
         * != null) { if (!proyecto.getResoluciones().isEmpty()) { resoluciones
         * = proyecto.getResoluciones(); } else { resoluciones = new
         * ArrayList<Resolucion>(); }
         * resoluciones.add(resolucionEleccion.getResolucion());
         * proyecto.setResoluciones(resoluciones);
         *
         * }*
         */
    }

    private void eliminarEvaluacion() {
        if (jTable1.getSelectedRow() != -1) {
            int i = JOptionPane.showConfirmDialog(null, "Se eliminaran las tres evaluaciones \n correspondientes. "
                    + "Esta usted Seguro?", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                Evaluacion evaluacionEliminar = EvaluacionFacade.getInstance().buscar((Long) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                evaluaciones.remove(evaluacionEliminar);
                EvaluacionFacade.getInstance().Eliminar(evaluacionEliminar.getId());
                JOptionPane.showMessageDialog(null, "Evaluacion Eliminada!");
                modeloTabla = new DefaultTableModel();
                cargarTablaEncabezadosEvaluaciones(modeloTabla);
                cargarEvaluaciones();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila!");
        }
    }

    private void cargarDatosComplementarios() {
        try {
            tfConvocatoria.setText(proyectoDatosComplementarios.getConvocatoria().getNombre());
        } catch (Exception e) {
        }
        try {
            tfAntecedentes.setText(proyectoDatosComplementarios.getAntecedentes());
        } catch (Exception e) {
        }
        try {
            tpCronogramaTrabajo.setText(proyectoDatosComplementarios.getCronogramaTrabajo());
        } catch (Exception e) {
        }
        try {
            tfLocalizacion.setText(proyectoDatosComplementarios.getLocalizacionOtra());
        } catch (Exception e) {
        }
        try {
            taMetodologia.setText(proyectoDatosComplementarios.getMetodologia());
        } catch (Exception e) {
        }
        try {
            taObjetivoEspecificoHipotesis.setText(proyectoDatosComplementarios.getObjetivoEspecificoHipotesis());
        } catch (Exception e) {
        }
        try {
            taObjetivoGeneral.setText(proyectoDatosComplementarios.getObjetivoGral());
        } catch (Exception e) {
        }
        try {
            cbProyectoIniciacion.setSelected(proyectoDatosComplementarios.getProyectoIniciacion());
        } catch (Exception e) {
        }
        try {
            cbProyectoOrientado.setSelected(proyectoDatosComplementarios.getProyectoOrientado());
        } catch (Exception e) {
        }
        try {
            taRelevanciaProblema.setText(proyectoDatosComplementarios.getRelevanciaProblema());
        } catch (Exception e) {
        }
        try {
            tfSubDisciplinaCientificaOtra.setText(proyectoDatosComplementarios.getSubDisciplinaCientificaOtra());
        } catch (Exception e) {
        }
        try {
            tfUnidadEjecutoraOtra.setText(proyectoDatosComplementarios.getUnidadEjecutoraOtra());
        } catch (Exception e) {
        }
        try {
            tpFormacionRRHH.setText(proyectoDatosComplementarios.getCapacitacionFormacionRH());
        } catch (Exception e) {
        }
        try {
            tfLatitud.setText(String.valueOf(proyectoDatosComplementarios.getLatitud()));
        } catch (Exception e) {

        }
        try {
            tfLongitud.setText(String.valueOf(proyectoDatosComplementarios.getLongitud()));
        } catch (Exception e) {

        }

        try {
            cboProvincias.setSelectedItem(proyectoDatosComplementarios.getLocalidad().getDepartamento().getProvincia());
            cargarDepartamentos();
        } catch (java.lang.NullPointerException ex) {
        }
        try {
            cboDepartamentos.setSelectedItem(proyectoDatosComplementarios.getLocalidad().getDepartamento());
            cargarLocalidades();
        } catch (java.lang.NullPointerException ex) {
        }
        try {
            if (proyectoDatosComplementarios.getLocalidad() != null) {
                cboLocalidades.setSelectedItem(proyectoDatosComplementarios.getLocalidad());
            }
        } catch (java.lang.NullPointerException ex) {
        }
    }

    private void habilitarPanelDatosComplementarios(Boolean flag) {
        for (Component c : panelDatosComplementarios.getComponents()) {
            c.setEnabled(flag);
        }
        tpCronogramaTrabajo.setEnabled(flag);
        tpFormacionRRHH.setEnabled(flag);
        taMetodologia.setEnabled(flag);
        taObjetivoEspecificoHipotesis.setEnabled(flag);
        taObjetivoGeneral.setEnabled(flag);
        taRelevanciaProblema.setEnabled(flag);
    }

    private void configurarjTextPaneEnHTML() {
        tpCronogramaTrabajo.setContentType("text/html");
        tpFormacionRRHH.setContentType("text/html");
    }

    private void deshabilitarPaneles() {

        //PANEL DATOS DEL PROYECTO
        for (Component componente : jPanel1.getComponents()) {
            componente.setEnabled(false);
        }
        for (Component componente : jPanel11.getComponents()) {
            componente.setEnabled(false);
        }
        for (Component componente : jPanel10.getComponents()) {
            componente.setEnabled(false);
        }
        taTitulo.setEnabled(false);
        jListAreasTematicas.setEnabled(false);
        jListDisciplinasCientificas.setEnabled(false);
        jListSubDisciplinasCientificas.setEnabled(false);
        //habilitamos solo codigoincentivos
        tfCodigoIncentivos.setEnabled(true);
        //PANEL DOCUMENTACION
        for (Component componente : jPanel4.getComponents()) {
            componente.setEnabled(false);
        }
        //PANEL EVALUACIONES
        for (Component componente : jPanel5.getComponents()) {
            componente.setEnabled(false);
        }
        //PANEL MAS DATOS
        for (Component componente : jPanel2.getComponents()) {
            componente.setEnabled(false);
        }
        taResumen.setEnabled(false);
        taSummary.setEnabled(false);
        taObservaciones.setEnabled(false);
        taTitle.setEnabled(false);

        //PANEL DATOS COMPLEMENTARIOS
        for (Component componente : panelDatosComplementarios.getComponents()) {
            componente.setEnabled(false);
        }
        taObjetivoGeneral.setEnabled(false);
        taMetodologia.setEnabled(false);
        taRelevanciaProblema.setEnabled(false);
        taObjetivoEspecificoHipotesis.setEnabled(false);
        tpFormacionRRHH.setEnabled(false);
        tpCronogramaTrabajo.setEnabled(false);
    }

    private void cambiarSeleccion() {
        if (jListParticipaciones.getSelectedIndex() != -1) {
            participacion = (Participacion) jListParticipaciones.getSelectedValue();
        }
    }

    private void cargarEntidadesAcreditadoras() {
        Comunes.cargarJComboConBlanco(cboEntidadAcreditadora, EntidadAcreditadoraFacade.getInstance().getTodosUnidadEjecutora());
    }

    private void cargarEntidadesFinanciadoras() {
        Comunes.cargarJComboConBlanco(cboEntidadFinanciadora, EntidadFinanciadoraFacade.getInstance().getTodosEntidadFinanciadoras());
    }

    private void cambiarEntidadAcreditadora() {

        if (cboEntidadAcreditadora.getSelectedIndex() > 0) {
            entidadAcreditadora = (EntidadAcreditadora) cboEntidadAcreditadora.getSelectedItem();
        } else {
            entidadAcreditadora = null;
        }

    }

    private void cambiarEntidadFinanciadora() {

        if (cboEntidadFinanciadora.getSelectedIndex() > 0) {
            entidadFinanciadora = (EntidadFinanciadora) cboEntidadFinanciadora.getSelectedItem();
        } else {
            entidadFinanciadora = null;
        }

    }

    private void agregarNuevaEntidadAcreditadora() {
        diagEntidadAcreditadora dentidadAcreditadora = new diagEntidadAcreditadora(null, true, "Alta");
        dentidadAcreditadora.setLocation(Comunes.centrarDialog(dentidadAcreditadora));
        dentidadAcreditadora.setVisible(true);
        cargarEntidadesAcreditadoras();
        if (dentidadAcreditadora.getEntidadAcreditadora() != null) {
            cboUnidadEjecutora.setSelectedItem(dentidadAcreditadora.getEntidadAcreditadora());
        }
    }

    private void agregarNuevaEntidadFinanciadora() {
        diagEntidadFinanciadora dentidadFinanciadora = new diagEntidadFinanciadora(null, true, "Alta");
        dentidadFinanciadora.setLocation(Comunes.centrarDialog(dentidadFinanciadora));
        dentidadFinanciadora.setVisible(true);
        cargarEntidadesFinanciadoras();
        if (dentidadFinanciadora.getEntidadFinanciadora() != null) {
            cboUnidadEjecutora.setSelectedItem(dentidadFinanciadora.getEntidadFinanciadora());
        }
    }

    private void mostrarGoogleMaps() {
        if (!tfLatitud.getText().isEmpty() && !tfLongitud.getText().isEmpty()) {
            String ubicacion = tfLatitud.getText() + "," + tfLongitud.getText();
            if (ubicacion.equals("0.0,0.0")) {
                ubicacion = "-28.469337,-65.779138";
            }
            // String urlString = "https://www.google.com.ar/maps/@" + ubicacion + ",16.25z?hl=es-419";
            String urlString = "http://maps.google.com/maps?q=loc:" + ubicacion;
            try {
                Desktop.getDesktop().browse(new URL(urlString).toURI());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar al mapa");

            }
        } else {
            String urlString = "https://www.google.com.ar/maps/place/Universidad+Nacional+de+Catamarca/@-28.4647371,-65.7838292,15z/data=!4m2!3m1!1s0x0000000000000000:0x73c2363432b582ab?hl=es-419";

            try {
                Desktop.getDesktop().browse(new URL(urlString).toURI());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar al mapa");

            }
        }

    }

    private void setearDatosComplementarios() {
        try {
            if (!tfLatitud.getText().isEmpty()) {
                proyectoDatosComplementarios.setLatitud(Double.parseDouble(tfLatitud.getText()));
            } else {
                proyectoDatosComplementarios.setLatitud(-28.457883);
            }
            if (!tfLongitud.getText().isEmpty()) {
                proyectoDatosComplementarios.setLongitud(Double.parseDouble(tfLongitud.getText()));
            } else {
                proyectoDatosComplementarios.setLongitud(-65.783472);
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Error: no se puede setear latitud y longitud");
        }
        proyectoDatosComplementarios.setLocalidad((Localidad) cboLocalidades.getSelectedItem());
        proyecto.setProyectoDatosComplementarios(proyectoDatosComplementarios);
        switch (tipoOperacion) {
            case "Alta":
                ProyectoDatosComplementariosFacade.getInstance().alta(proyectoDatosComplementarios);
                break;
            case "Modificación":
                if (proyectoDatosComplementarios.getId() != null) {
                    ProyectoDatosComplementariosFacade.getInstance().modificar(proyectoDatosComplementarios);
                } else {
                    ProyectoDatosComplementariosFacade.getInstance().alta(proyectoDatosComplementarios);
                }
                break;
        }

    }

}
