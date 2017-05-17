/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * diagAgregarInvestigador.java
 *
 * Created on 24/06/2011, 20:38:11
 */
package vistas.investigadores;

import controladores.CursoDictadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.DedicacionDocente;
import entidades.Documento;
import entidades.Resolucion;
import entidades.UnidadAcademica;
import entidades.Universidad;
import entidades.categorizacion.Categorizacion;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.investigador.formacionAcademica.FormacionAcademicaOtra;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
import entidades.localidad.Departamento;
import entidades.localidad.Localidad;
import entidades.localidad.Provincia;
import entidades.operaciones.Operacion;
import entidades.persona.CorreoElectronico;
import entidades.persona.DocumentoIdentidad;
import entidades.persona.Domicilio;
import entidades.persona.Persona;
import entidades.persona.Sexo;
import entidades.persona.Telefono;
import entidades.persona.TipoDocumento;
import entidades.persona.TipoTelefono;
import entidades.persona.investigador.CategoriaDocente;
import entidades.persona.investigador.DepartamentoDocente;
import entidades.persona.investigador.Docencia;
import entidades.persona.investigador.EspecialidadActividadAcademica;
import entidades.persona.investigador.EspecialidadInvestigacion;
import entidades.persona.investigador.Especializacion;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.ModoObtencionCargo;
import entidades.persona.investigador.actividadConduccion.ActividadConduccion;
import entidades.persona.investigador.actividadConduccion.CargoConduccion;
import entidades.persona.investigador.actividadConduccion.DedicacionConduccion;
import entidades.persona.investigador.curso.Asignatura;
import entidades.persona.investigador.curso.CursoDictado;
import entidades.persona.investigador.curso.TipoAsignatura;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.titulo.TituloGrado;
import entidades.titulo.TituloPosgrado;
import entidades.usuario.Usuario;
import facade.ActividadConduccionFacade;
import facade.CargoConduccionFacade;
import facade.CargoFacade;
import facade.CategoriaDocenteFacade;
import facade.ConexionFacade;
import facade.CursoDictadoFacade;
import facade.DedicacionConduccionFacade;
import facade.DedicacionDocenteFacade;
import facade.DepartamentoDocenteFacade;
import facade.DepartamentoFacade;
import facade.DocenciaFacade;
import facade.EspecialidadActividadAcademicaFacade;
import facade.EspecialidadInvestigacionFacade;
import facade.EspecializacionFacade;
import facade.InvestigadorFacade;
import facade.LocalidadFacade;
import facade.ModoObtencionCargoFacade;
import facade.OperacionFacade;
import facade.ProvinciaFacade;
import facade.ProyectoFacade;
import facade.ProyectoVinculacionFacade;
import facade.SexoFacade;
import facade.TipoAsignaturaFacade;
import facade.TipoDocumentoFacade;
import facade.TipoTelefonoFacade;
import facade.UnidadAcademicaFacade;
import facade.UniversidadFacade;
import facade.persona.investigador.curso.TipoDuracionAsignaturaFacade;
import includes.Comunes;
import includes.ModeloTablaNoEditable;
import includes.SuperDialog;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.validator.routines.BigIntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import vista.diagCargoConduccion;
import vista.panelControl.diagCategoriaDocente;
import vista.panelControl.diagDedicacionConduccion;
import vista.panelControl.diagDedicacionDocente;
import vista.panelControl.diagDepartamentoDocente;
import vista.panelControl.diagEspecialidadActividadAcademica;
import vista.panelControl.diagEspecialidadInvestigacion;
import vista.panelControl.diagModoObtencionCargo;
import vista.panelControl.diagTipoAsignatura;
import vista.panelControl.diagUnidadAcademica;
import vista.panelControl.diagUniversidad;
import vista.proyectos.diagProyecto;
import vista.proyectos.vinculacion.diagProyectoVinculacion;
import vista.resoluciones.diagResolucionAlta;
import vista.resoluciones.diagResolucionEleccion;
import vistas.investigadores.formacionAcademica.diagFormacionAcademicaGrado;
import vistas.investigadores.formacionAcademica.diagFormacionAcademicaOtros;
import vistas.investigadores.formacionAcademica.diagFormacionAcademicaPosgrado;
import vistas.mensajes.diagMensajes;

/**
 *
 * @author Carlos
 */
public class diagInvestigador extends SuperDialog {

    List<Telefono> telefonos = new ArrayList<Telefono>();
    List<CorreoElectronico> correosElectronicos = new ArrayList<CorreoElectronico>();
    private String tipoOperacion;
    private Persona persona;
    private Investigador investigador;
    private List<TituloGrado> titulosGrado = new ArrayList<TituloGrado>();
    private List<TituloPosgrado> titulosPosgrado = new ArrayList<TituloPosgrado>();
    private List<CursoDictado> cursosDictados = new ArrayList<CursoDictado>();
    private List<ActividadConduccion> actividadesConduccion = new ArrayList<ActividadConduccion>();
    private List<Docencia> docencias = new ArrayList<>();
    private List<Especializacion> especializaciones = new ArrayList<>();
    private Usuario usuario;
    private List<FormacionAcademicaGrado> formacionesAcademicasGrado = new ArrayList<FormacionAcademicaGrado>();
    private List<FormacionAcademicaPosgrado> formacionesAcademicasPosgrado = new ArrayList<FormacionAcademicaPosgrado>();
    private List<FormacionAcademicaOtra> formacionesAcademicasOtras = new ArrayList<FormacionAcademicaOtra>();
    private Asignatura asignaturaSeleccionada;
    ModeloTablaNoEditable modeloTablaCursos = new ModeloTablaNoEditable();
    ModeloTablaNoEditable modeloTablaActividadConduccion = new ModeloTablaNoEditable();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    private String tipoOperacionCursoDictado;
    private String tipoOperacionActividadConduccion;
    private String tipoOperacionDocencia;
    ActividadConduccion actividadConduccion = new ActividadConduccion();
    CursoDictado cursoDictado = new CursoDictado();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Docencia docencia = new Docencia();
    ModeloTablaNoEditable modeloTablaDocencia = new ModeloTablaNoEditable();
    private String tipoOperacionEspecializacion;
    private Especializacion especializacion;
    private ModeloTablaNoEditable modeloTablaEspecializacion;
    private List<Especializacion> listaEspecializaciones;
    private List<Resolucion> resoluciones = new ArrayList();

    /**
     * Creates new form diagAgregarInvestigador
     */
    public diagInvestigador(java.awt.Frame parent, boolean modal, String tipoOperacion, Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.usuario = usuario;
        initComponents();
        inicializarComponentes();
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    public diagInvestigador(java.awt.Frame parent, boolean modal, String tipoOperacion, Investigador investigador, Usuario usuario) {
        super(parent, modal);
        this.tipoOperacion = tipoOperacion;
        this.investigador = investigador;
        this.usuario = usuario;
        initComponents();
        inicializarComponentes();
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGuardar = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfApellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfCuil = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jXDateFechaNacimiento = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfCalle = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfPiso = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfDpto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfCodigoPostal = new javax.swing.JTextField();
        cboProvincias = new javax.swing.JComboBox();
        cboLocalidades = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cboDepartamentos = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        tfEntreCalles = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfReferencia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfBarrio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        tfNumeroDocumento = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cboTiposTelefonos = new javax.swing.JComboBox();
        tfNumeroTelefono = new javax.swing.JTextField();
        btnAgregarTelefono = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTelefonos = new javax.swing.JList();
        btnEliminarTelefono = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tfCorreoElectronico = new javax.swing.JTextField();
        btnAgregarCorreoElectronico = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListCorreosElectronicos = new javax.swing.JList();
        btnEliminarCorreoElectronico = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        cboSexo = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListFormacionAcademicaGrado = new javax.swing.JList();
        btAgregarFormacionAcademicaGrado = new javax.swing.JButton();
        btQuitarFormacionAcademicaGrado = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListFormacionAcademicaPosgrado = new javax.swing.JList();
        btAgregarFormacionAcademicaPosgrado = new javax.swing.JButton();
        btEliminarFormacionAcademicaPosgrado = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListFormacionAcademicaOtra = new javax.swing.JList();
        btAgregarFormacionAcademicaOtra = new javax.swing.JButton();
        btEliminarFormacionAcademicaOtra = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cboModoObtencionCargo = new javax.swing.JComboBox();
        jXDateFechaObtencionCargo = new org.jdesktop.swingx.JXDatePicker();
        btNuevoModoObtencionCargo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        tfHorasDedicacionPrimerCuatrimestre = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tfSemanasDedicacionPrimerCuatrimestre = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tfHorasDedicacionSegundoCuatrimestre = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tfSemanasDedicacionSegundoCuatrimestre = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaDocencia = new javax.swing.JTable();
        btnModificarDocencia = new javax.swing.JButton();
        bntEliminarDocencia = new javax.swing.JButton();
        btnAgregarDocencia = new javax.swing.JButton();
        btnCancelarDocencia = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        ftfAño = new javax.swing.JFormattedTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboUniversidad = new javax.swing.JComboBox();
        cboUnidadAcademica = new javax.swing.JComboBox();
        btNuevaUniversidad = new javax.swing.JButton();
        btNuevaUnidadAcademica = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cboDepartamentoDocente = new javax.swing.JComboBox();
        btNuevoDepartamentoDocente = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cboCategoriaDocente = new javax.swing.JComboBox();
        btNuevaCategoriaDocente = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        cboDedicacionDocente = new javax.swing.JComboBox();
        btNuevaDedicacionDocente = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cboCargoConduccion = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        cboDedicacionConduccion = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jXDateFechaDesdeConduccion = new org.jdesktop.swingx.JXDatePicker();
        jLabel44 = new javax.swing.JLabel();
        jXDateFechaHastaConduccion = new org.jdesktop.swingx.JXDatePicker();
        chkSigueVigente = new javax.swing.JCheckBox();
        btAgregarActividadConduccion = new javax.swing.JButton();
        cboDependencia = new javax.swing.JComboBox();
        btNuevoCargo = new javax.swing.JButton();
        btNuevaDedicacion = new javax.swing.JButton();
        btnCancelarActividadConduccion = new javax.swing.JButton();
        btnAgregarDependencia = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        chkActividadesConduccionVigentes = new javax.swing.JCheckBox();
        btEliminarActividadConduccion = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaActividadConduccion = new javax.swing.JTable();
        btnModificarActividadConduccion = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListParticipaciones = new javax.swing.JList();
        chkParticipacionesVigentes = new javax.swing.JCheckBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        jListVinculacion = new javax.swing.JList();
        jLabel50 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        chkParticipacionesVigentes1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCategorizaciones = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        cboEspecialidadActividadAcademica = new javax.swing.JComboBox();
        cboEspecialidadInvestigacion = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        btNuevaEspecialidadInvestigacion = new javax.swing.JButton();
        btNuevaEspecialidadActividadAcademica = new javax.swing.JButton();
        btnAceptarEspecializacion = new javax.swing.JButton();
        btnCancelarEspecializacion = new javax.swing.JButton();
        tfAñoEspecializacion = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        btnEliminarEspecializacion = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablaEspecializaciones = new javax.swing.JTable();
        btnEditarEspecializacion = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btAgregarAsignatura = new javax.swing.JButton();
        tfAsignatura = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        tfAñoAcademico = new javax.swing.JTextField();
        cboTipoAsignatura = new javax.swing.JComboBox();
        btNuevoTipoAsignatura = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        tfDictadoClases = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        tfDuracion = new javax.swing.JTextField();
        btnSeleccionarAsignatura = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        cboTipoDuracionAsignatura = new javax.swing.JComboBox();
        btNuevoTipoDuracionAsignatura = new javax.swing.JButton();
        btnCancelarAsignatura = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btEliminarAsignatura = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        btnEditarAsignatura = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        btnVerMensajes = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jlstDocumentacion = new javax.swing.JList();
        jLabel52 = new javax.swing.JLabel();
        btnAgregarDocumentacion = new javax.swing.JButton();
        btnQuitarDocumentacion = new javax.swing.JButton();
        btnVerDocumentacion = new javax.swing.JButton();
        btnModificarResolucion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alta de Investigador");

        btGuardar.setText("Aceptar");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Apellidos");

        jLabel2.setText("Nombres");

        jLabel3.setText("CUIL");

        jLabel4.setText("Fec. Nac.");

        jXDateFechaNacimiento.setFormats("dd/MM/yyyy");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));

        jLabel5.setText("Calle");

        jLabel6.setText("Número");

        jLabel7.setText("Piso");

        jLabel8.setText("Dpto");

        jLabel9.setText("Provincia");

        jLabel10.setText("Localidad");

        jLabel11.setText("Código Postal");

        cboProvincias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProvinciasItemStateChanged(evt);
            }
        });

        jLabel16.setText("Departamento");

        cboDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDepartamentosItemStateChanged(evt);
            }
        });

        jLabel17.setText("Entre Calles");

        jLabel18.setText("Referencia");

        jLabel19.setText("Barrio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel9)
                    .addComponent(jLabel16)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboDepartamentos, 0, 324, Short.MAX_VALUE)
                    .addComponent(cboProvincias, 0, 324, Short.MAX_VALUE)
                    .addComponent(tfCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfEntreCalles, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(tfReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(tfBarrio, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(cboLocalidades, javax.swing.GroupLayout.Alignment.TRAILING, 0, 324, Short.MAX_VALUE)
                    .addComponent(tfCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tfPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tfDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tfEntreCalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tfReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cboDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel14.setText("Tipo Doc.");

        jLabel15.setText("Núm. Doc. (sin puntos)");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfonos"));

        btnAgregarTelefono.setText("Agregar");
        btnAgregarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTelefonoActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListTelefonos);

        btnEliminarTelefono.setText("Elminar");
        btnEliminarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTelefonoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboTiposTelefonos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTiposTelefonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTelefono)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Correos Electrónicos"));

        btnAgregarCorreoElectronico.setText("Agregar");
        btnAgregarCorreoElectronico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCorreoElectronicoActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jListCorreosElectronicos);

        btnEliminarCorreoElectronico.setText("Eliminar");
        btnEliminarCorreoElectronico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCorreoElectronicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfCorreoElectronico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarCorreoElectronico)
                    .addComponent(btnEliminarCorreoElectronico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarCorreoElectronico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarCorreoElectronico)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel45.setText("Sexo");

        jLabel32.setText("Ej: PEREZ");

        jLabel33.setText("Ej: Juan Fulano");

        jLabel34.setText("Ej 32-30027321-1 o 32300273211");

        jLabel47.setText("Ej: 3002732");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfCuil, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNombres)
                                    .addComponent(tfApellidos)
                                    .addComponent(jXDateFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel47)))
                            .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(509, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(tfNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfCuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(602, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Personales", jPanel1);

        jLabel41.setText("Grado");

        jScrollPane6.setViewportView(jListFormacionAcademicaGrado);

        btAgregarFormacionAcademicaGrado.setText("Agregar");
        btAgregarFormacionAcademicaGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarFormacionAcademicaGradoActionPerformed(evt);
            }
        });

        btQuitarFormacionAcademicaGrado.setText("Quitar");
        btQuitarFormacionAcademicaGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarFormacionAcademicaGradoActionPerformed(evt);
            }
        });

        jLabel42.setText("Posgrado");

        jScrollPane7.setViewportView(jListFormacionAcademicaPosgrado);

        btAgregarFormacionAcademicaPosgrado.setText("Agregar");
        btAgregarFormacionAcademicaPosgrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarFormacionAcademicaPosgradoActionPerformed(evt);
            }
        });

        btEliminarFormacionAcademicaPosgrado.setText("Quitar");
        btEliminarFormacionAcademicaPosgrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarFormacionAcademicaPosgradoActionPerformed(evt);
            }
        });

        jLabel46.setText("Otra");

        jScrollPane9.setViewportView(jListFormacionAcademicaOtra);

        btAgregarFormacionAcademicaOtra.setText("Agregar");
        btAgregarFormacionAcademicaOtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarFormacionAcademicaOtraActionPerformed(evt);
            }
        });

        btEliminarFormacionAcademicaOtra.setText("Quitar");
        btEliminarFormacionAcademicaOtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarFormacionAcademicaOtraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btEliminarFormacionAcademicaOtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAgregarFormacionAcademicaOtra)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btQuitarFormacionAcademicaGrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btAgregarFormacionAcademicaGrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btEliminarFormacionAcademicaPosgrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btAgregarFormacionAcademicaPosgrado)))))
                .addContainerGap(571, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btAgregarFormacionAcademicaGrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btQuitarFormacionAcademicaGrado))
                    .addComponent(jLabel41)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btAgregarFormacionAcademicaPosgrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminarFormacionAcademicaPosgrado))
                    .addComponent(jLabel42)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btAgregarFormacionAcademicaOtra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminarFormacionAcademicaOtra))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(839, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Formación Académica", jPanel19);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Modo y Fecha de Obtención del Cargo Docente"));

        jXDateFechaObtencionCargo.setFormats("dd/MM/yyyy");

        btNuevoModoObtencionCargo.setText("Nuevo");
        btNuevoModoObtencionCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoModoObtencionCargoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboModoObtencionCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNuevoModoObtencionCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDateFechaObtencionCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDateFechaObtencionCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevoModoObtencionCargo)
                    .addComponent(cboModoObtencionCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Horas y Semanas Dedicadas a la Docencia"));

        jLabel23.setText("Horas 1er Cuat.");

        jLabel24.setText("Semanas 1er Cuat.");

        jLabel25.setText("Horas 2do Cuat.");

        jLabel26.setText("Semanas 2do Cuat.");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfHorasDedicacionPrimerCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSemanasDedicacionPrimerCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfHorasDedicacionSegundoCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSemanasDedicacionSegundoCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(tfHorasDedicacionSegundoCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)
                        .addComponent(tfSemanasDedicacionSegundoCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(tfHorasDedicacionPrimerCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)
                        .addComponent(tfSemanasDedicacionPrimerCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado Docencia"));

        tablaDocencia.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tablaDocencia);
        if (tablaDocencia.getColumnModel().getColumnCount() > 0) {
            tablaDocencia.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tablaDocencia.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tablaDocencia.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tablaDocencia.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        btnModificarDocencia.setText("Modificar");
        btnModificarDocencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDocenciaActionPerformed(evt);
            }
        });

        bntEliminarDocencia.setText("Eliminar");
        bntEliminarDocencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarDocenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntEliminarDocencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarDocencia, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnModificarDocencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntEliminarDocencia))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAgregarDocencia.setText("Aceptar");
        btnAgregarDocencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDocenciaActionPerformed(evt);
            }
        });

        btnCancelarDocencia.setText("Cancelar");
        btnCancelarDocencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarDocenciaActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Docencia"));

        try {
            ftfAño.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel49.setText("Año:");

        jLabel12.setText("Universidad");

        jLabel13.setText("Unidad Académica");

        btNuevaUniversidad.setText("Nueva");
        btNuevaUniversidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaUniversidadActionPerformed(evt);
            }
        });

        btNuevaUnidadAcademica.setText("Nueva");
        btNuevaUnidadAcademica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaUnidadAcademicaActionPerformed(evt);
            }
        });

        jLabel20.setText("Departamento Docente");

        btNuevoDepartamentoDocente.setText("Nueva");
        btNuevoDepartamentoDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoDepartamentoDocenteActionPerformed(evt);
            }
        });

        jLabel21.setText("Categoría Docente");

        btNuevaCategoriaDocente.setText("Nueva");
        btNuevaCategoriaDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaCategoriaDocenteActionPerformed(evt);
            }
        });

        jLabel22.setText("Dedicación Docente");

        btNuevaDedicacionDocente.setText("Nueva");
        btNuevaDedicacionDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaDedicacionDocenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboUnidadAcademica, 0, 278, Short.MAX_VALUE)
                            .addComponent(cboUniversidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btNuevaUnidadAcademica, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(btNuevaUniversidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfAño, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboCategoriaDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDepartamentoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDedicacionDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btNuevoDepartamentoDocente, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(btNuevaCategoriaDocente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNuevaDedicacionDocente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ftfAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevoDepartamentoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboDepartamentoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategoriaDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaCategoriaDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboUnidadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboDedicacionDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaDedicacionDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevaUnidadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(btnAgregarDocencia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelarDocencia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarDocencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarDocencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Docencia", jPanel5);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Actividad de Conducción"));

        jLabel27.setText("Cargo");

        jLabel28.setText("Dedicación");

        jLabel29.setText("Dependencia");

        jLabel43.setText("Fecha Desde");

        jXDateFechaDesdeConduccion.setFormats("dd/MM/yyyy");

        jLabel44.setText("Fecha Hasta");

        jXDateFechaHastaConduccion.setFormats("dd/MM/yyyy");

        chkSigueVigente.setText("Sigue Vigente");
        chkSigueVigente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkSigueVigenteStateChanged(evt);
            }
        });
        chkSigueVigente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSigueVigenteActionPerformed(evt);
            }
        });

        btAgregarActividadConduccion.setText("Agregar");
        btAgregarActividadConduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarActividadConduccionActionPerformed(evt);
            }
        });

        btNuevoCargo.setText("Nuevo");
        btNuevoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoCargoActionPerformed(evt);
            }
        });

        btNuevaDedicacion.setText("Nueva");
        btNuevaDedicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaDedicacionActionPerformed(evt);
            }
        });

        btnCancelarActividadConduccion.setText("Cancelar");
        btnCancelarActividadConduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActividadConduccionActionPerformed(evt);
            }
        });

        btnAgregarDependencia.setText("Nueva");
        btnAgregarDependencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDependenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboCargoConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btNuevoCargo)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboDedicacionConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btNuevaDedicacion))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jXDateFechaHastaConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkSigueVigente))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboDependencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jXDateFechaDesdeConduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarDependencia, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(btAgregarActividadConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarActividadConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cboCargoConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevoCargo)
                    .addComponent(jLabel28)
                    .addComponent(cboDedicacionConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaDedicacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cboDependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarDependencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jXDateFechaDesdeConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jXDateFechaHastaConduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSigueVigente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAgregarActividadConduccion)
                    .addComponent(btnCancelarActividadConduccion))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Actividades de Conducción"));

        chkActividadesConduccionVigentes.setText("Ver solo las actividades de conducción vigentes");
        chkActividadesConduccionVigentes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkActividadesConduccionVigentesStateChanged(evt);
            }
        });

        btEliminarActividadConduccion.setText("Eliminar");
        btEliminarActividadConduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActividadConduccionActionPerformed(evt);
            }
        });

        tablaActividadConduccion.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tablaActividadConduccion);

        btnModificarActividadConduccion.setText("Modificar");
        btnModificarActividadConduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActividadConduccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(chkActividadesConduccionVigentes)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificarActividadConduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(btEliminarActividadConduccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnModificarActividadConduccion)
                        .addGap(18, 18, 18)
                        .addComponent(btEliminarActividadConduccion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkActividadesConduccionVigentes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(789, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conducción", jPanel9);

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setText("Proyectos de Investigación");
        jPanel18.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jScrollPane5.setViewportView(jListParticipaciones);

        jPanel18.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 600, 150));

        chkParticipacionesVigentes.setText("Ver solo participaciones vigentes");
        chkParticipacionesVigentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkParticipacionesVigentesActionPerformed(evt);
            }
        });
        jPanel18.add(chkParticipacionesVigentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 280, 40));

        jScrollPane12.setViewportView(jListVinculacion);

        jPanel18.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 600, -1));

        jLabel50.setText("Proyectos de Vinculación ");
        jPanel18.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jButton1.setText("Ver detalles del proyecto seleccionado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, -1, -1));

        chkParticipacionesVigentes1.setText("Ver solo participaciones vigentes");
        chkParticipacionesVigentes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkParticipacionesVigentes1ActionPerformed(evt);
            }
        });
        jPanel18.add(chkParticipacionesVigentes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 280, 40));

        jButton2.setText("Ver detalles del proyecto seleccionado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, -1));

        jTabbedPane1.addTab("Participaciones en Proyectos", jPanel18);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Categorizaciones"));

        tblCategorizaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblCategorizaciones);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(518, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(766, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Categorización", jPanel12);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Especializacion"));

        jLabel31.setText("Especialidad Actividad Académica");

        jLabel30.setText("Especialidad Investigación");

        jLabel51.setText("Año");

        btNuevaEspecialidadInvestigacion.setText("Nueva");
        btNuevaEspecialidadInvestigacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaEspecialidadInvestigacionActionPerformed(evt);
            }
        });

        btNuevaEspecialidadActividadAcademica.setText("Nueva");
        btNuevaEspecialidadActividadAcademica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaEspecialidadActividadAcademicaActionPerformed(evt);
            }
        });

        btnAceptarEspecializacion.setText("Aceptar");
        btnAceptarEspecializacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEspecializacionActionPerformed(evt);
            }
        });

        btnCancelarEspecializacion.setText("Cancelar");
        btnCancelarEspecializacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEspecializacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfAñoEspecializacion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboEspecialidadActividadAcademica, 0, 266, Short.MAX_VALUE)
                                    .addComponent(cboEspecialidadInvestigacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btNuevaEspecialidadInvestigacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btNuevaEspecialidadActividadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(btnAceptarEspecializacion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarEspecializacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(tfAñoEspecializacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cboEspecialidadInvestigacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaEspecialidadInvestigacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cboEspecialidadActividadAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevaEspecialidadActividadAcademica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarEspecializacion)
                    .addComponent(btnCancelarEspecializacion))
                .addContainerGap())
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Especializaciones"));

        btnEliminarEspecializacion.setText("Eliminar");
        btnEliminarEspecializacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEspecializacionActionPerformed(evt);
            }
        });

        tablaEspecializaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(tablaEspecializaciones);

        btnEditarEspecializacion.setText("Editar");
        btnEditarEspecializacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEspecializacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditarEspecializacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarEspecializacion, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(btnEditarEspecializacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarEspecializacion)
                        .addGap(0, 77, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(519, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(775, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Especialización", jPanel11);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignatura"));

        jLabel35.setText("Asignatura");

        jLabel36.setText("Año Academico");

        jLabel37.setText("Tipo de Asignatura");

        btAgregarAsignatura.setText("Aceptar");
        btAgregarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarAsignaturaActionPerformed(evt);
            }
        });

        tfAsignatura.setEnabled(false);
        tfAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAsignaturaActionPerformed(evt);
            }
        });

        btNuevoTipoAsignatura.setText("Nuevo");
        btNuevoTipoAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoTipoAsignaturaActionPerformed(evt);
            }
        });

        jLabel38.setText("Dictado de Clases (Horas Semanales)");

        jLabel39.setText("Duración (Semanas)");

        btnSeleccionarAsignatura.setText("Seleccionar");
        btnSeleccionarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarAsignaturaActionPerformed(evt);
            }
        });

        jLabel48.setText("Tipo de Duración de Asignatura");

        btNuevoTipoDuracionAsignatura.setText("Nuevo");
        btNuevoTipoDuracionAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoTipoDuracionAsignaturaActionPerformed(evt);
            }
        });

        btnCancelarAsignatura.setText("Cancelar");
        btnCancelarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAsignaturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(jLabel35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel48)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(tfAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionarAsignatura))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(cboTipoDuracionAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btNuevoTipoDuracionAsignatura))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfDuracion, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                .addComponent(tfDictadoClases, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfAñoAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(cboTipoAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btNuevoTipoAsignatura))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(btAgregarAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(tfAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarAsignatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoDuracionAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevoTipoDuracionAsignatura)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(tfAñoAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(cboTipoAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevoTipoAsignatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(tfDictadoClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(tfDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAgregarAsignatura)
                    .addComponent(btnCancelarAsignatura)))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Cursos"));

        btEliminarAsignatura.setText("Eliminar");
        btEliminarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarAsignaturaActionPerformed(evt);
            }
        });

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(tablaCursos);

        btnEditarAsignatura.setText("Editar");
        btnEditarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAsignaturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEliminarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnEditarAsignatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminarAsignatura)
                        .addGap(0, 48, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(787, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Asignatura", jPanel15);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Ver Mensajes"));

        btnVerMensajes.setText("Ver");
        btnVerMensajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMensajesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnVerMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnVerMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(774, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(958, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mensajes", jPanel21);

        jScrollPane14.setViewportView(jlstDocumentacion);

        jLabel52.setText("Documentación");

        btnAgregarDocumentacion.setText("Agregar");
        btnAgregarDocumentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDocumentacionActionPerformed(evt);
            }
        });

        btnQuitarDocumentacion.setText("Eliminar");
        btnQuitarDocumentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarDocumentacionActionPerformed(evt);
            }
        });

        btnVerDocumentacion.setText("Ver detalles documentacion seleccionada");
        btnVerDocumentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDocumentacionActionPerformed(evt);
            }
        });

        btnModificarResolucion.setText("Modificar");
        btnModificarResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarResolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregarDocumentacion)
                            .addComponent(btnModificarResolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuitarDocumentacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(btnVerDocumentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(btnAgregarDocumentacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarDocumentacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarResolucion))
                    .addComponent(jLabel52)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerDocumentacion)
                .addContainerGap(820, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Documentación", jPanel25);

        jScrollPane10.setViewportView(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(427, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btGuardar)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(881, 549));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        aceptar();
    }//GEN-LAST:event_btGuardarActionPerformed

private void btNuevaEspecialidadActividadAcademicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaEspecialidadActividadAcademicaActionPerformed
    agregarNuevaEspecialidadActividadAcademica();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaEspecialidadActividadAcademicaActionPerformed

private void btNuevaEspecialidadInvestigacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaEspecialidadInvestigacionActionPerformed
    agregarNuevaEspecialidadInvestigacion();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaEspecialidadInvestigacionActionPerformed

private void btEliminarActividadConduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActividadConduccionActionPerformed
    eliminarActividadConduccion();        // TODO add your handling code here:
}//GEN-LAST:event_btEliminarActividadConduccionActionPerformed

private void chkActividadesConduccionVigentesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkActividadesConduccionVigentesStateChanged
    mostrarSegunEstado();
    // TODO add your handling code here:
}//GEN-LAST:event_chkActividadesConduccionVigentesStateChanged

private void btNuevaDedicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaDedicacionActionPerformed
    agregarNuevaDedicacionConduccion();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaDedicacionActionPerformed

private void btNuevoCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoCargoActionPerformed
    agregarNuevoCargo();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevoCargoActionPerformed

private void btAgregarActividadConduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarActividadConduccionActionPerformed
    agregarNuevoActividadConduccion();        // TODO add your handling code here:
}//GEN-LAST:event_btAgregarActividadConduccionActionPerformed

private void chkSigueVigenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSigueVigenteActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_chkSigueVigenteActionPerformed

private void chkSigueVigenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkSigueVigenteStateChanged
    cambiarEstadoFechaHasta();        // TODO add your handling code here:
}//GEN-LAST:event_chkSigueVigenteStateChanged

private void btEliminarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarAsignaturaActionPerformed
    quitarCurso();
}//GEN-LAST:event_btEliminarAsignaturaActionPerformed

private void btNuevoTipoAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoTipoAsignaturaActionPerformed
    agregarNuevoTipoAsignatura();
}//GEN-LAST:event_btNuevoTipoAsignaturaActionPerformed

private void tfAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAsignaturaActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_tfAsignaturaActionPerformed

private void btAgregarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarAsignaturaActionPerformed
    agregarNuevoCurso();
}//GEN-LAST:event_btAgregarAsignaturaActionPerformed

private void btNuevoModoObtencionCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoModoObtencionCargoActionPerformed
    agregarNuevoModoObtencionCargo();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevoModoObtencionCargoActionPerformed

private void btNuevaDedicacionDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaDedicacionDocenteActionPerformed
    agregarNuevaDedicacionDocente();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaDedicacionDocenteActionPerformed

private void btNuevaCategoriaDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaCategoriaDocenteActionPerformed
    agregarNuevaCategoriaDocente();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaCategoriaDocenteActionPerformed

private void btNuevoDepartamentoDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoDepartamentoDocenteActionPerformed
    agregarNuevoDepartamentoDocente();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevoDepartamentoDocenteActionPerformed

private void btNuevaUnidadAcademicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaUnidadAcademicaActionPerformed
    agregarNuevaUnidadAcademica();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaUnidadAcademicaActionPerformed

private void btNuevaUniversidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaUniversidadActionPerformed
    agregarNuevaUniversidad();        // TODO add your handling code here:
}//GEN-LAST:event_btNuevaUniversidadActionPerformed

private void btEliminarFormacionAcademicaOtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarFormacionAcademicaOtraActionPerformed
    quitarFormacionAcademicaOtra();
}//GEN-LAST:event_btEliminarFormacionAcademicaOtraActionPerformed

private void btAgregarFormacionAcademicaOtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarFormacionAcademicaOtraActionPerformed
    agregarFormacionAcademicaOtra();
}//GEN-LAST:event_btAgregarFormacionAcademicaOtraActionPerformed

private void btEliminarFormacionAcademicaPosgradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarFormacionAcademicaPosgradoActionPerformed
    quitarFormacionAcademicaPosgrado();
}//GEN-LAST:event_btEliminarFormacionAcademicaPosgradoActionPerformed

private void btAgregarFormacionAcademicaPosgradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarFormacionAcademicaPosgradoActionPerformed
    agregarFormacionAcademicaPosgrado();
}//GEN-LAST:event_btAgregarFormacionAcademicaPosgradoActionPerformed

private void btQuitarFormacionAcademicaGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarFormacionAcademicaGradoActionPerformed
    quitarFormacionAcademicaGrado();
}//GEN-LAST:event_btQuitarFormacionAcademicaGradoActionPerformed
private void btAgregarFormacionAcademicaGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarFormacionAcademicaGradoActionPerformed
    agregarFormacionAcademicaGrado();
}//GEN-LAST:event_btAgregarFormacionAcademicaGradoActionPerformed

private void btnEliminarCorreoElectronicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCorreoElectronicoActionPerformed
    eliminarCorreoElectronico();
}//GEN-LAST:event_btnEliminarCorreoElectronicoActionPerformed

private void btnAgregarCorreoElectronicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCorreoElectronicoActionPerformed
    agregarCorreoElectronico();
}//GEN-LAST:event_btnAgregarCorreoElectronicoActionPerformed
private void btnEliminarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTelefonoActionPerformed
    eliminarTelefono();
}//GEN-LAST:event_btnEliminarTelefonoActionPerformed

private void btnAgregarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTelefonoActionPerformed
    agregarTelefono();
}//GEN-LAST:event_btnAgregarTelefonoActionPerformed

private void cboDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDepartamentosItemStateChanged
    cargarLocalidades();
}//GEN-LAST:event_cboDepartamentosItemStateChanged

private void cboProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProvinciasItemStateChanged
    cargarDepartamentos();
}//GEN-LAST:event_cboProvinciasItemStateChanged

private void chkParticipacionesVigentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkParticipacionesVigentesActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_chkParticipacionesVigentesActionPerformed

    private void btNuevoTipoDuracionAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoTipoDuracionAsignaturaActionPerformed
        // TODO add your handling code here: Agregar Alta de Tipo de Duración de Asignatura
    }//GEN-LAST:event_btNuevoTipoDuracionAsignaturaActionPerformed

    private void btnSeleccionarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarAsignaturaActionPerformed
        seleccionarAsignatura();
    }//GEN-LAST:event_btnSeleccionarAsignaturaActionPerformed

    private void btnEditarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAsignaturaActionPerformed
        editarAsignatura();
    }//GEN-LAST:event_btnEditarAsignaturaActionPerformed

    private void btnCancelarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAsignaturaActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarAsignaturaActionPerformed

    private void btnCancelarActividadConduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActividadConduccionActionPerformed
        limpiarCamposActividadConduccion();
    }//GEN-LAST:event_btnCancelarActividadConduccionActionPerformed

    private void btnModificarActividadConduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActividadConduccionActionPerformed
        modificarActividadConduccion();
    }//GEN-LAST:event_btnModificarActividadConduccionActionPerformed

    private void btnAgregarDocenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDocenciaActionPerformed
        agregarDocencia();
    }//GEN-LAST:event_btnAgregarDocenciaActionPerformed

    private void btnCancelarDocenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarDocenciaActionPerformed
        limpiarCamposDocencia();
    }//GEN-LAST:event_btnCancelarDocenciaActionPerformed

    private void btnAgregarDependenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDependenciaActionPerformed
        agregarDependencia();
    }//GEN-LAST:event_btnAgregarDependenciaActionPerformed

    private void btnModificarDocenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDocenciaActionPerformed
        modificarDocencia();
    }//GEN-LAST:event_btnModificarDocenciaActionPerformed

    private void bntEliminarDocenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarDocenciaActionPerformed
        eliminarDocencia();
    }//GEN-LAST:event_bntEliminarDocenciaActionPerformed

    private void btnVerMensajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMensajesActionPerformed
        verMensajes();
    }//GEN-LAST:event_btnVerMensajesActionPerformed

    private void btnEliminarEspecializacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEspecializacionActionPerformed
        eliminarEspecializacion();
    }//GEN-LAST:event_btnEliminarEspecializacionActionPerformed

    private void btnEditarEspecializacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEspecializacionActionPerformed
        editarEspecializacion();
    }//GEN-LAST:event_btnEditarEspecializacionActionPerformed

    private void btnAceptarEspecializacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEspecializacionActionPerformed
        agregarNuevaEspecializacion();
    }//GEN-LAST:event_btnAceptarEspecializacionActionPerformed

    private void btnCancelarEspecializacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEspecializacionActionPerformed
        limpiarCamposEspecializacion();
    }//GEN-LAST:event_btnCancelarEspecializacionActionPerformed

    private void btnAgregarDocumentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDocumentacionActionPerformed
        agregarDocumentacion();
    }//GEN-LAST:event_btnAgregarDocumentacionActionPerformed

    private void btnQuitarDocumentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDocumentacionActionPerformed
        quitarDocumentacion();
    }//GEN-LAST:event_btnQuitarDocumentacionActionPerformed

    private void btnVerDocumentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDocumentacionActionPerformed
        mostrarDocumentacion();
    }//GEN-LAST:event_btnVerDocumentacionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verDetallesProyectoInvestigacion();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chkParticipacionesVigentes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkParticipacionesVigentes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkParticipacionesVigentes1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        verDetallesProyectoVinculacion();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnModificarResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarResolucionActionPerformed
        modificarResolucion();
    }//GEN-LAST:event_btnModificarResolucionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntEliminarDocencia;
    private javax.swing.JButton btAgregarActividadConduccion;
    private javax.swing.JButton btAgregarAsignatura;
    private javax.swing.JButton btAgregarFormacionAcademicaGrado;
    private javax.swing.JButton btAgregarFormacionAcademicaOtra;
    private javax.swing.JButton btAgregarFormacionAcademicaPosgrado;
    private javax.swing.JButton btEliminarActividadConduccion;
    private javax.swing.JButton btEliminarAsignatura;
    private javax.swing.JButton btEliminarFormacionAcademicaOtra;
    private javax.swing.JButton btEliminarFormacionAcademicaPosgrado;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btNuevaCategoriaDocente;
    private javax.swing.JButton btNuevaDedicacion;
    private javax.swing.JButton btNuevaDedicacionDocente;
    private javax.swing.JButton btNuevaEspecialidadActividadAcademica;
    private javax.swing.JButton btNuevaEspecialidadInvestigacion;
    private javax.swing.JButton btNuevaUnidadAcademica;
    private javax.swing.JButton btNuevaUniversidad;
    private javax.swing.JButton btNuevoCargo;
    private javax.swing.JButton btNuevoDepartamentoDocente;
    private javax.swing.JButton btNuevoModoObtencionCargo;
    private javax.swing.JButton btNuevoTipoAsignatura;
    private javax.swing.JButton btNuevoTipoDuracionAsignatura;
    private javax.swing.JButton btQuitarFormacionAcademicaGrado;
    private javax.swing.JButton btnAceptarEspecializacion;
    private javax.swing.JButton btnAgregarCorreoElectronico;
    private javax.swing.JButton btnAgregarDependencia;
    private javax.swing.JButton btnAgregarDocencia;
    private javax.swing.JButton btnAgregarDocumentacion;
    private javax.swing.JButton btnAgregarTelefono;
    private javax.swing.JButton btnCancelarActividadConduccion;
    private javax.swing.JButton btnCancelarAsignatura;
    private javax.swing.JButton btnCancelarDocencia;
    private javax.swing.JButton btnCancelarEspecializacion;
    private javax.swing.JButton btnEditarAsignatura;
    private javax.swing.JButton btnEditarEspecializacion;
    private javax.swing.JButton btnEliminarCorreoElectronico;
    private javax.swing.JButton btnEliminarEspecializacion;
    private javax.swing.JButton btnEliminarTelefono;
    private javax.swing.JButton btnModificarActividadConduccion;
    private javax.swing.JButton btnModificarDocencia;
    private javax.swing.JButton btnModificarResolucion;
    private javax.swing.JButton btnQuitarDocumentacion;
    private javax.swing.JButton btnSeleccionarAsignatura;
    private javax.swing.JButton btnVerDocumentacion;
    private javax.swing.JButton btnVerMensajes;
    private javax.swing.JComboBox cboCargoConduccion;
    private javax.swing.JComboBox cboCategoriaDocente;
    private javax.swing.JComboBox cboDedicacionConduccion;
    private javax.swing.JComboBox cboDedicacionDocente;
    private javax.swing.JComboBox cboDepartamentoDocente;
    private javax.swing.JComboBox cboDepartamentos;
    private javax.swing.JComboBox cboDependencia;
    private javax.swing.JComboBox cboEspecialidadActividadAcademica;
    private javax.swing.JComboBox cboEspecialidadInvestigacion;
    private javax.swing.JComboBox cboLocalidades;
    private javax.swing.JComboBox cboModoObtencionCargo;
    private javax.swing.JComboBox cboProvincias;
    private javax.swing.JComboBox cboSexo;
    private javax.swing.JComboBox cboTipoAsignatura;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JComboBox cboTipoDuracionAsignatura;
    private javax.swing.JComboBox cboTiposTelefonos;
    private javax.swing.JComboBox cboUnidadAcademica;
    private javax.swing.JComboBox cboUniversidad;
    private javax.swing.JCheckBox chkActividadesConduccionVigentes;
    private javax.swing.JCheckBox chkParticipacionesVigentes;
    private javax.swing.JCheckBox chkParticipacionesVigentes1;
    private javax.swing.JCheckBox chkSigueVigente;
    private javax.swing.JFormattedTextField ftfAño;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel44;
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
    private javax.swing.JList jListCorreosElectronicos;
    private javax.swing.JList jListFormacionAcademicaGrado;
    private javax.swing.JList jListFormacionAcademicaOtra;
    private javax.swing.JList jListFormacionAcademicaPosgrado;
    private javax.swing.JList jListParticipaciones;
    private javax.swing.JList jListTelefonos;
    private javax.swing.JList jListVinculacion;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField9;
    private org.jdesktop.swingx.JXDatePicker jXDateFechaDesdeConduccion;
    private org.jdesktop.swingx.JXDatePicker jXDateFechaHastaConduccion;
    private org.jdesktop.swingx.JXDatePicker jXDateFechaNacimiento;
    private org.jdesktop.swingx.JXDatePicker jXDateFechaObtencionCargo;
    private javax.swing.JList jlstDocumentacion;
    private javax.swing.JTable tablaActividadConduccion;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTable tablaDocencia;
    private javax.swing.JTable tablaEspecializaciones;
    private javax.swing.JTable tblCategorizaciones;
    private javax.swing.JTextField tfApellidos;
    private javax.swing.JTextField tfAsignatura;
    private javax.swing.JTextField tfAñoAcademico;
    private javax.swing.JTextField tfAñoEspecializacion;
    private javax.swing.JTextField tfBarrio;
    private javax.swing.JTextField tfCalle;
    private javax.swing.JTextField tfCodigoPostal;
    private javax.swing.JTextField tfCorreoElectronico;
    private javax.swing.JTextField tfCuil;
    private javax.swing.JTextField tfDictadoClases;
    private javax.swing.JTextField tfDpto;
    private javax.swing.JTextField tfDuracion;
    private javax.swing.JTextField tfEntreCalles;
    private javax.swing.JTextField tfHorasDedicacionPrimerCuatrimestre;
    private javax.swing.JTextField tfHorasDedicacionSegundoCuatrimestre;
    private javax.swing.JTextField tfNombres;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfNumeroDocumento;
    private javax.swing.JTextField tfNumeroTelefono;
    private javax.swing.JTextField tfPiso;
    private javax.swing.JTextField tfReferencia;
    private javax.swing.JTextField tfSemanasDedicacionPrimerCuatrimestre;
    private javax.swing.JTextField tfSemanasDedicacionSegundoCuatrimestre;
    // End of variables declaration//GEN-END:variables

    private void inicializarComponentes() {
        Comunes.limitarTextField(tfAñoEspecializacion, 4);
        tipoOperacionEspecializacion = "Alta";
        tipoOperacionCursoDictado = "Alta";
        tipoOperacionActividadConduccion = "Alta";
        tipoOperacionDocencia = "Alta";
        btnCancelarEspecializacion.setVisible(true);
        btnCancelarAsignatura.setVisible(true);
        btnCancelarActividadConduccion.setVisible(true);
        btnCancelarDocencia.setVisible(true);
        cargarModeloTablaEspecializaciones();
        cargarModeloTablaActividadConduccion();
        cargarModeloTablaCursos();
        cargarModeloTablaDocencia();
        cargarProvincias();
        cargarTiposTelefonos();
        cargarTiposDocumentos();
        cargarUniversidades();
        cargarUnidadesAcademicas();
        cargarDepartamentosDocentes();
        cargarCategoriasDocentes();
        cargarDedicacionesDocentes();
        cargarModosObtencionCargo();
        cargarTiposAsignaturas();
        cargarCargosConduccion();
        cargarDedicacionesDocentesConduccion();
        cargarDependenciasConduccion();
        cargarEspecialidadActividadAcademica();
        cargarEspecialidadInvestigacion();
        cargarSexos();
        cargarTipoDuracionAsignatura();
        cargarTablaDocencia();
        cargarTablaEspecializacion();

        deshabilitarLoQueFaltaImplementar();

        if (tipoOperacion.equals("Alta")) {
//            tfApellidos.setText("");
//            tfBarrio.setText("");
//            tfCalle.setText("");
//            tfCodigoPostal.setText("");
//            tfCuil.setText("");
//            tfDpto.setText("");
//            tfEntreCalles.setText("");
//            tfNombres.setText("");
//            tfNumero.setText("");
//            tfNumeroDocumento.setText("");
//            tfNumeroTelefono.setText("");
//            tfPiso.setText("");
//            tfReferencia.setText("");
            this.setTitle("Agregar nueva Persona");
        } else if (tipoOperacion.equals("Modificación")) {
            cargarInvestigador();
            this.setTitle("Modificación de Persona");

        } else if (tipoOperacion.equals("Consulta")) {
            cargarInvestigador();
            tfApellidos.setEditable(false);
            tfBarrio.setEditable(false);
            tfCalle.setEditable(false);
            tfCodigoPostal.setEditable(false);
            tfCuil.setEditable(false);
            tfDpto.setEditable(false);
            tfEntreCalles.setEditable(false);
            tfNombres.setEditable(false);
            tfNumero.setEditable(false);
            tfNumeroDocumento.setEditable(false);
            tfNumeroTelefono.setEditable(false);
            tfPiso.setEditable(false);
            tfReferencia.setEditable(false);
            tfNumeroTelefono.setEditable(false);
            tfCorreoElectronico.setEditable(false);
            cboTipoDocumento.setEnabled(false);
            cboDepartamentos.setEnabled(false);
            cboProvincias.setEnabled(false);
            cboLocalidades.setEnabled(false);
            cboTiposTelefonos.setEnabled(false);
            jXDateFechaNacimiento.setEditable(false);
            btnAgregarCorreoElectronico.setVisible(false);
            btnAgregarTelefono.setVisible(false);
            btnEliminarCorreoElectronico.setVisible(false);
            btnEliminarTelefono.setVisible(false);
            btnAgregarDocencia.setVisible(false);
            btnModificarDocencia.setVisible(false);
            btAgregarFormacionAcademicaGrado.setVisible(false);
            btAgregarFormacionAcademicaPosgrado.setVisible(false);
            btAgregarFormacionAcademicaOtra.setVisible(false);
            btQuitarFormacionAcademicaGrado.setVisible(false);
            btEliminarFormacionAcademicaOtra.setVisible(false);
            btEliminarFormacionAcademicaPosgrado.setVisible(false);
            btNuevaUniversidad.setVisible(false);
            btNuevaUnidadAcademica.setVisible(false);
            btNuevoDepartamentoDocente.setVisible(false);
            btNuevaCategoriaDocente.setVisible(false);
            btNuevaDedicacionDocente.setVisible(false);
            btNuevoModoObtencionCargo.setVisible(false);
            btnCancelarDocencia.setVisible(false);
            bntEliminarDocencia.setVisible(false);
            btNuevoCargo.setVisible(false);
            btNuevaDedicacion.setVisible(false);
            btnAgregarDependencia.setVisible(false);
            btAgregarActividadConduccion.setVisible(false);
            btnCancelarActividadConduccion.setVisible(false);
            btEliminarActividadConduccion.setVisible(false);
            btnModificarActividadConduccion.setVisible(false);
            btNuevaEspecialidadInvestigacion.setVisible(false);
            btNuevaEspecialidadActividadAcademica.setVisible(false);
            btnAceptarEspecializacion.setVisible(false);
            btnCancelarEspecializacion.setVisible(false);
            btnEliminarEspecializacion.setVisible(false);
            btnEditarEspecializacion.setVisible(false);
            btnSeleccionarAsignatura.setVisible(false);
            btNuevoTipoDuracionAsignatura.setVisible(false);
            btNuevoTipoAsignatura.setVisible(false);
            btAgregarAsignatura.setVisible(false);
            btnCancelarAsignatura.setVisible(false);
            btnEditarAsignatura.setVisible(false);
            btEliminarAsignatura.setVisible(false);
            btnVerMensajes.setVisible(false);
            btGuardar.setVisible(false);
            Comunes.cargarJList(jListTelefonos, persona.getTelefonos());
            Comunes.cargarJList(jListCorreosElectronicos, persona.getCorreosElectronicos());
            this.setTitle("Ver Persona");
        }
    }

    private void aceptar() {
        if (validar()) {
            if (tipoOperacion.equals("Modificación")) {
                persona = investigador.getPersona();
                persona.setApellido(tfApellidos.getText().toUpperCase());
                persona.setNombre(tfNombres.getText());
                try {
                    DocumentoIdentidad documentoIdentidad = new DocumentoIdentidad();
                    if ((!tfNumeroDocumento.getText().equals("")) && (cboTipoDocumento.getSelectedIndex() > 0)) {
                        documentoIdentidad.setNumero(Long.parseLong(tfNumeroDocumento.getText()));
                        documentoIdentidad.setTipoDocumento((TipoDocumento) cboTipoDocumento.getSelectedItem());
                    } else {
                        documentoIdentidad = null;
                    }
                    persona.setDocumentoIdentidad(documentoIdentidad);
                } catch (Exception ex) {
                }
                if (!tfCuil.getText().equals("")) {
                    persona.setCuil(Comunes.colocarGuionesAlCuit(tfCuil));
                }
                if (jXDateFechaNacimiento.getDate() != null) {
                    persona.setFechaNacimiento(jXDateFechaNacimiento.getDate());
                }
                try {
                    persona.setSexo((Sexo) cboSexo.getSelectedItem());
                } catch (Exception ex) {
                }
                Domicilio domicilio = new Domicilio();
                domicilio.setCalle(tfCalle.getText());
                domicilio.setNumero(tfNumero.getText());
                domicilio.setPiso(tfPiso.getText());
                domicilio.setDpto(tfDpto.getText());
                domicilio.setEntreCalles(tfEntreCalles.getText());
                domicilio.setReferencia(tfReferencia.getText());
                domicilio.setBarrio(tfBarrio.getText());
                domicilio.setLocalidad((Localidad) cboLocalidades.getSelectedItem());
                domicilio.setCodigoPostal(tfCodigoPostal.getText());
                persona.setDomicilio(domicilio);
                persona.setTelefonos(telefonos);
                persona.setCorreosElectronicos(correosElectronicos);
                investigador.setPersona(persona);
                //Formacion Academica
                investigador.setFormacionesAcademicasGrado(formacionesAcademicasGrado);
                investigador.setFormacionesAcademicasPosgrado(formacionesAcademicasPosgrado);
                investigador.setFormacionesAcademicasOtras(formacionesAcademicasOtras);
                //Docencia
                investigador.setDocencias(docencias);
                //CursosDictados
                investigador.setCursosDictados(cursosDictados);
                //Especializacion
                investigador.setEspecializaciones(especializaciones);
                //Actividad Conduccion
                investigador.setActividadesConduccion(actividadesConduccion);
                try {
                    docencia.setUniversidad((Universidad) cboUniversidad.getSelectedItem());
                } catch (Exception ex) {
                }
                try {
                    docencia.setUnidadAcademica((UnidadAcademica) cboUnidadAcademica.getSelectedItem());
                } catch (Exception ex) {
                }
                try {
                    docencia.setDepartamentoDocente((DepartamentoDocente) cboDepartamentoDocente.getSelectedItem());
                } catch (Exception ex) {
                }
                try {
                    docencia.setCategoriaDocente((CategoriaDocente) cboCategoriaDocente.getSelectedItem());
                } catch (Exception ex) {
                }
                try {
                    docencia.setDedicacionDocente((DedicacionDocente) cboDedicacionDocente.getSelectedItem());
                } catch (Exception ex) {
                }
                try {
                    docencia.setModoObtencionCargo((ModoObtencionCargo) cboModoObtencionCargo.getSelectedItem());
                } catch (Exception ex) {
                }
                docencia.setFechaObtencionCargo(jXDateFechaObtencionCargo.getDate());
                try {
                    docencia.setHorasDedicadasDocenciaPrimerCuatrimestre(Integer.parseInt(tfHorasDedicacionPrimerCuatrimestre.getText()));
                } catch (Exception ex) {
                }
                try {
                    docencia.setSemanasDedicadasDocenciaPrimerCuatrimestre(Integer.parseInt(tfSemanasDedicacionPrimerCuatrimestre.getText()));
                } catch (Exception ex) {
                }
                try {
                    docencia.setHorasDedicadasDocenciaSegundoCuatrimestre(Integer.parseInt(tfHorasDedicacionSegundoCuatrimestre.getText()));
                } catch (Exception ex) {
                }
                try {
                    docencia.setSemanasDedicadasDocenciaSegundoCuatrimestre(Integer.parseInt(tfSemanasDedicacionSegundoCuatrimestre.getText()));
                } catch (Exception ex) {
                }
                InvestigadorFacade.getInstance().modificar(investigador);
                Operacion operacion = new Operacion();
                operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                operacion.setOperacion("Modificación de Investigador");
                operacion.setUsuario(usuario);
                OperacionFacade.getInstance().alta(operacion);
                JOptionPane.showMessageDialog(null, "Investigador modificado con éxito");
                this.dispose();
            } else if (tipoOperacion.equals("Alta")) {
                persona = new Persona();
                persona.setApellido(tfApellidos.getText().toUpperCase());
                persona.setNombre(tfNombres.getText());
                try {
                    DocumentoIdentidad documentoIdentidad = new DocumentoIdentidad();
                    if ((!tfNumeroDocumento.getText().equals("")) && (cboTipoDocumento.getSelectedIndex() > 0)) {
                        documentoIdentidad.setNumero(Long.parseLong(tfNumeroDocumento.getText()));
                        documentoIdentidad.setTipoDocumento((TipoDocumento) cboTipoDocumento.getSelectedItem());
                    } else {
                        documentoIdentidad = null;
                    }
                    persona.setDocumentoIdentidad(documentoIdentidad);
                } catch (Exception ex) {
                }
                if (!tfCuil.getText().equals("")) {
                    persona.setCuil(Comunes.colocarGuionesAlCuit(tfCuil));
                }
                if (jXDateFechaNacimiento.getDate() != null) {
                    persona.setFechaNacimiento(jXDateFechaNacimiento.getDate());
                }
                try {
                    persona.setSexo((Sexo) cboSexo.getSelectedItem());
                } catch (Exception ex) {
                }
                Domicilio domicilio = new Domicilio();
                domicilio.setCalle(tfCalle.getText());
                domicilio.setNumero(tfNumero.getText());
                domicilio.setPiso(tfPiso.getText());
                domicilio.setDpto(tfDpto.getText());
                domicilio.setEntreCalles(tfEntreCalles.getText());
                domicilio.setReferencia(tfReferencia.getText());
                domicilio.setBarrio(tfBarrio.getText());
                domicilio.setLocalidad((Localidad) cboLocalidades.getSelectedItem());
                domicilio.setCodigoPostal(tfCodigoPostal.getText());
                persona.setDomicilio(domicilio);
                persona.setTelefonos(telefonos);
                persona.setCorreosElectronicos(correosElectronicos);
                investigador = new Investigador();
                investigador.setPersona(persona);

                //formacion academica
                investigador.setFormacionesAcademicasGrado(formacionesAcademicasGrado);
                investigador.setFormacionesAcademicasPosgrado(formacionesAcademicasPosgrado);
                investigador.setFormacionesAcademicasOtras(formacionesAcademicasOtras);

                //docencia
                investigador.setDocencias(docencias);
                //CursosDictados
                investigador.setCursosDictados(cursosDictados);
                //Especializaciones
                investigador.setEspecializaciones(especializaciones);
                //Conduccion
                investigador.setActividadesConduccion(actividadesConduccion);
                InvestigadorFacade.getInstance().alta(investigador);
                Operacion operacion = new Operacion();
                operacion.setFecha(Comunes.obtenerFechaActualDesdeDB());
                operacion.setOperacion("Alta de Investigador");
                operacion.setUsuario(usuario);
                OperacionFacade.getInstance().alta(operacion);
                JOptionPane.showMessageDialog(null, "Investigador creado con éxito");
                this.dispose();
            } else if (tipoOperacion.equals("Consulta")) {
                this.dispose();
            }
        }
    }

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

    private void cargarTiposTelefonos() {
        Comunes.cargarJComboConBlanco(cboTiposTelefonos, TipoTelefonoFacade.getInstance().listarTodosTipoTelefonoOrdenados());

    }

    private void cargarTiposDocumentos() {
        Comunes.cargarJComboConBlanco(cboTipoDocumento, TipoDocumentoFacade.getInstance().listarTodosTipoDocumentoOrdenados());

    }

    private void cargarInvestigador() {
        persona = investigador.getPersona();
        if (persona != null) {
            try {
                tfApellidos.setText(persona.getApellido());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfNombres.setText(persona.getNombre());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                cboTipoDocumento.setSelectedItem(persona.getDocumentoIdentidad().getTipoDocumento());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfNumeroDocumento.setText(((Long) persona.getDocumentoIdentidad().getNumero()).toString());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfCuil.setText(persona.getCuil());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                jXDateFechaNacimiento.setDate(persona.getFechaNacimiento());
            } catch (java.lang.NullPointerException ex) {
                jXDateFechaNacimiento.setDate(null);
            }
            try {
                tfCalle.setText(persona.getDomicilio().getCalle());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfNumero.setText(persona.getDomicilio().getNumero());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfPiso.setText(persona.getDomicilio().getPiso());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfDpto.setText(persona.getDomicilio().getDpto());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfEntreCalles.setText(persona.getDomicilio().getEntreCalles());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfReferencia.setText(persona.getDomicilio().getReferencia());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfBarrio.setText(persona.getDomicilio().getBarrio());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                cboProvincias.setSelectedItem(persona.getDomicilio().getLocalidad().getDepartamento().getProvincia());
                cargarDepartamentos();
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                cboDepartamentos.setSelectedItem(persona.getDomicilio().getLocalidad().getDepartamento());
                cargarLocalidades();
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                if (persona.getDomicilio().getLocalidad() != null) {
                    cboLocalidades.setSelectedItem(persona.getDomicilio().getLocalidad());
                }
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                tfCodigoPostal.setText(persona.getDomicilio().getCodigoPostal());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                telefonos = persona.getTelefonos();
                Comunes.cargarJList(jListTelefonos, persona.getTelefonos());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                correosElectronicos = persona.getCorreosElectronicos();
                Comunes.cargarJList(jListCorreosElectronicos, persona.getCorreosElectronicos());
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                if (persona.getSexo() != null) {
                    cboSexo.setSelectedItem(persona.getSexo());
                }
            } catch (java.lang.NullPointerException ex) {
            }
            try {
                if (investigador.getResoluciones() != null) {
                    resoluciones = investigador.getResoluciones();
                    cargarDocumentacion();
                }
            } catch (NullPointerException ex) {
            }
        }
        //cargarFormacionesAcademicas
        try {
            formacionesAcademicasGrado = investigador.getFormacionesAcademicasGrado();
            cargarFormacionesAcademicasGrado();
        } catch (java.lang.NullPointerException ex) {
        }
        try {
            formacionesAcademicasPosgrado = investigador.getFormacionesAcademicasPosgrado();
            cargarFormacionesAcademicasPosgrado();
        } catch (java.lang.NullPointerException ex) {
        }
        try {
            formacionesAcademicasOtras = investigador.getFormacionesAcademicasOtras();
            cargarFormacionesAcademicasOtras();
        } catch (java.lang.NullPointerException ex) {
        }
        //cargar Docencia
        try {
            docencias = investigador.getDocencias();
            cargarTablaDocencia();
        } catch (java.lang.NullPointerException ex) {
        }

        //cargar Cursos
        try {
            cursosDictados = investigador.getCursosDictados();
            cargarTablaCursos();
        } catch (java.lang.NullPointerException ex) {
        }
        //cargar Conduccion
        try {
            actividadesConduccion = investigador.getActividadesConduccion();
            cargarTablaActividadConduccion();
        } catch (java.lang.NullPointerException ex) {
            System.out.println("Error cargando conduccion");
        }
        //cargar Especializaciones
        try {
            especializaciones = investigador.getEspecializaciones();
            cargarTablaEspecializacion();
        } catch (java.lang.NullPointerException ex) {
            System.out.println("Error cargando especializacion");
        }
        try {
            Comunes.cargarJList(jListParticipaciones, investigador.getParticipacionesProyecto());
        } catch (java.lang.NullPointerException ex) {
        }
        try {
            Comunes.cargarJList(jListVinculacion, ProyectoVinculacionFacade.getInstance().buscarPorParticipacionInvestigador(investigador));
        } catch (java.lang.NullPointerException ex) {
        }
        cargarTablaCategorizaciones();
    }

    private void agregarTelefono() {
        if (cboTiposTelefonos.getSelectedIndex() > 0) {
            if (BigIntegerValidator.getInstance().isValid(tfNumeroTelefono.getText())) {
                Telefono telefono = new Telefono();
                telefono.setNumero(tfNumeroTelefono.getText());
                telefono.setTipoTelefono((TipoTelefono) cboTiposTelefonos.getSelectedItem());
                telefonos.add(telefono);
                Comunes.cargarJList(jListTelefonos, telefonos);
                cboTiposTelefonos.setSelectedIndex(0);
                tfNumeroTelefono.setText("");
                tfNumeroTelefono.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Número de teléfono incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                tfNumeroTelefono.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe escoger un tipo de teléfono", "Error", JOptionPane.ERROR_MESSAGE);
            cboTiposTelefonos.requestFocus();
        }
    }

    private void agregarCorreoElectronico() {
        if (Comunes.validarEmail(tfCorreoElectronico)) {
            CorreoElectronico correoElectronico = new CorreoElectronico();
            correoElectronico.setDireccion(tfCorreoElectronico.getText());
            correosElectronicos.add(correoElectronico);
            Comunes.cargarJList(jListCorreosElectronicos, correosElectronicos);
            tfCorreoElectronico.setText("");
            tfCorreoElectronico.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Correo electrónico incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            tfCorreoElectronico.requestFocus();
        }

    }

    private void eliminarTelefono() {
        if (jListTelefonos.getSelectedIndex() != -1) {
            telefonos.remove((Telefono) jListTelefonos.getSelectedValue());
            Comunes.cargarJList(jListTelefonos, telefonos);
        }
    }

    private void eliminarCorreoElectronico() {
        if (jListCorreosElectronicos.getSelectedIndex() != -1) {
            correosElectronicos.remove((CorreoElectronico) jListCorreosElectronicos.getSelectedValue());
            Comunes.cargarJList(jListCorreosElectronicos, correosElectronicos);
        }
    }

    public Persona getPersona() {
        return persona;
    }

//    private void agregarTituloGrado() {
//        diagTituloGradoEleccion tituloGradoEleccion = new diagTituloGradoEleccion(null, true, titulosGrado);
//        tituloGradoEleccion.setLocation(Comunes.centrarDialog(tituloGradoEleccion));
//        tituloGradoEleccion.setVisible(true);
//        if (tituloGradoEleccion.getTituloGradoEscogido() != null) {
//            titulosGrado.add(tituloGradoEleccion.getTituloGradoEscogido());
//            Comunes.cargarJList(jListFormacionAcademicaGrado, titulosGrado);
//        }
//    }
//    private void agregarTituloPosgrado() {
//        diagTituloPosgradoEleccion tituloPosgradoEleccion = new diagTituloPosgradoEleccion(null, true, titulosPosgrado);
//        tituloPosgradoEleccion.setLocation(Comunes.centrarDialog(tituloPosgradoEleccion));
//        tituloPosgradoEleccion.setVisible(true);
//        if (tituloPosgradoEleccion.getTituloPosgradoEscogido() != null) {
//            titulosPosgrado.add(tituloPosgradoEleccion.getTituloPosgradoEscogido());
//            Comunes.cargarJList(jListFormacionAcademicaPosgrado, titulosPosgrado);
//        }
//    }
    private void cargarUniversidades() {
        Comunes.cargarJComboConBlanco(cboUniversidad, UniversidadFacade.getInstance().listarTodosUniversidadOrdenados());
    }

    private void cargarUnidadesAcademicas() {
        Comunes.cargarJComboConBlanco(cboUnidadAcademica, UnidadAcademicaFacade.getInstance().getTodasUnidadAcademica());
    }

    private void cargarDepartamentosDocentes() {
        Comunes.cargarJComboConBlanco(cboDepartamentoDocente, DepartamentoDocenteFacade.getInstance().listarTodosDepartamentoDocenteOrdenados());
    }

    private void cargarCategoriasDocentes() {
        Comunes.cargarJComboConBlanco(cboCategoriaDocente, CategoriaDocenteFacade.getInstance().listarTodosCategoriaDocenteOrdenados());
    }

    private void cargarDedicacionesDocentes() {
        Comunes.cargarJComboConBlanco(cboDedicacionDocente, DedicacionDocenteFacade.getInstance().listarTodosDedicacionDocenteOrdenados());
    }

    private void cargarModosObtencionCargo() {
        Comunes.cargarJComboConBlanco(cboModoObtencionCargo, ModoObtencionCargoFacade.getInstance().listarTodosModoObtencionCargoOrdenados());
    }

    private void agregarNuevoCurso() {
        if (tipoOperacionCursoDictado.equals("Alta")) {
            if (asignaturaSeleccionada != null) {
                if (!tfDictadoClases.getText().isEmpty()) {
                    try {
                        cursoDictado = new CursoDictado();
                        cursoDictado.setAsignatura(asignaturaSeleccionada);
                        cursoDictado.setDuracionAnual(tfAñoAcademico.getText());
                        cursoDictado.setTipoDuracionAsignatura((TipoDuracionAsignatura) cboTipoDuracionAsignatura.getSelectedItem());
                        cursoDictado.setTipoAsignatura((TipoAsignatura) cboTipoAsignatura.getSelectedItem());
                        cursoDictado.setHorasSemanalesDictadoClases(Integer.parseInt(tfDictadoClases.getText()));
                        cursoDictado.setSemanasDuracion(Integer.parseInt(tfDuracion.getText()));
                        CursoDictadoFacade.getInstance().alta(cursoDictado);
                        cursosDictados.add(cursoDictado);
                        cargarTablaCursos();
                        limpiarCampos();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Por favor complete todos los datos del nuevo curso");

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese la cantidad de horas semanales");
                    tfDictadoClases.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una asignatura primero");
                btnSeleccionarAsignatura.requestFocus();
            }
        }
        if (tipoOperacionCursoDictado.equals("Modificación")) {
            if (asignaturaSeleccionada != null) {
                if (!tfDictadoClases.getText().isEmpty()) {
                    try {

                        cursoDictado.setAsignatura(asignaturaSeleccionada);
                        cursoDictado.setDuracionAnual(tfAñoAcademico.getText());
                        cursoDictado.setTipoDuracionAsignatura((TipoDuracionAsignatura) cboTipoDuracionAsignatura.getSelectedItem());
                        cursoDictado.setTipoAsignatura((TipoAsignatura) cboTipoAsignatura.getSelectedItem());
                        cursoDictado.setHorasSemanalesDictadoClases(Integer.parseInt(tfDictadoClases.getText()));
                        cursoDictado.setSemanasDuracion(Integer.parseInt(tfDuracion.getText()));
                        CursoDictadoFacade.getInstance().editar(cursoDictado);
                        cursosDictados.set(cursosDictados.indexOf(cursoDictado), cursoDictado);
                        cargarTablaCursos();
                        btnCancelarAsignatura.setVisible(false);
                        limpiarCampos();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Por favor complete todos los datos del nuevo curso");

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese la cantidad de horas semanales");
                    tfDictadoClases.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una asignatura primero");
                btnSeleccionarAsignatura.requestFocus();
            }
        }
        tipoOperacionCursoDictado = "Alta";

    }

    private void cargarTiposAsignaturas() {
        Comunes.cargarJComboConBlanco(cboTipoAsignatura, TipoAsignaturaFacade.getInstance().listarTodosTipoAsignaturaOrdenados());
    }

    private void quitarCurso() {
        if (tablaCursos.getSelectedRow() != -1) {
            CursoDictado cursoEliminar = CursoDictadoFacade.getInstance().buscar((Long) tablaCursos.getValueAt(tablaCursos.getSelectedRow(), 0));
            cursosDictados.remove(cursoEliminar);
            CursoDictadoFacade.getInstance().eliminar(cursoEliminar.getId());
            cargarTablaCursos();

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para poder eliminar");
        }

    }

    private void agregarNuevoTipoAsignatura() {
        diagTipoAsignatura tipoAsignaturaAlta = new diagTipoAsignatura(null, true, "Alta");
        tipoAsignaturaAlta.setLocation(Comunes.centrarDialog(tipoAsignaturaAlta));
        tipoAsignaturaAlta.setVisible(true);
        cargarTiposAsignaturas();
        cboTipoAsignatura.setSelectedItem(tipoAsignaturaAlta.getTipoAsignaturaCreada());
    }

    private void cargarCargos() {
        Comunes.cargarJComboConBlanco(cboCargoConduccion, CargoFacade.getInstance().listarTodosCargoOrdenados());
    }

    private void cargarDependenciasConduccion() {
        Comunes.cargarJComboConBlanco(cboDependencia, UnidadAcademicaFacade.getInstance().getTodasUnidadAcademica());
    }

    private void cargarDedicacionesDocentesConduccion() {
        Comunes.cargarJComboConBlanco(cboDedicacionConduccion, DedicacionConduccionFacade.getInstance().listarTodosDedicacionConduccionOrdenados());
    }

    private void deshabilitarLoQueFaltaImplementar() {

        // Pestaña actividad conducción
//        cboCargo.setEnabled(false);
//        cboDedicacion.setEnabled(false);
//        cboDependencia.setEnabled(false);
//        jXDateFechaDesdeConduccion.setEnabled(false);
//        jXDateFechaHastaConduccion.setEnabled(false);
//        chkActividadesConduccionVigentes.setEnabled(false);
//        btAgregarActividadConduccion.setEnabled(false);
//        jListActividadesConduccion.setEnabled(false);
//        chkActividadesConduccionVigentes.setEnabled(false);
//        btEliminarActividadConduccion.setEnabled(false);
        // Pestaña especialidades
//        cboEspecialidadInvestigacion.setEnabled(false);
//        cboEspecialidadActividadAcademica.setEnabled(false);
        // Pestaña categorización        
        tblCategorizaciones.setEnabled(false);

        // Pestaña participaciones
        jListParticipaciones.setEnabled(false);
        chkParticipacionesVigentes.setEnabled(false);
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    private void cargarSexos() {
        Comunes.cargarJComboConBlanco(cboSexo, SexoFacade.getInstance().listarTodosSexoOrdenados());
    }

    private boolean validar() {
        boolean flag = false;
        if (!tfNombres.getText().isEmpty() && !tfApellidos.getText().isEmpty()) {
            if (!tfCuil.getText().equals("") && Comunes.validarTextFieldCuit(tfCuil)) {

                if (LongValidator.getInstance().isValid(tfNumeroDocumento.getText()) && !tfNumeroDocumento.getText().contains(".")) {
                    if (cboTipoDocumento.getSelectedIndex() > 0) {
                        flag = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de documento", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (!tfNumeroDocumento.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Número de documento incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    flag = true;
                }
            } else if (tfCuil.getText().equals("")) {
                flag = true;
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar correctamente el CUIL", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "El investigador debe tener Nombre y Apellido", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return flag;
    }

    private void agregarFormacionAcademicaGrado() {
        diagFormacionAcademicaGrado formacionAcademicaGrado = new diagFormacionAcademicaGrado(null, true, "Alta");
        formacionAcademicaGrado.setLocation(Comunes.centrarDialog(formacionAcademicaGrado));
        formacionAcademicaGrado.setVisible(true);

        if (formacionAcademicaGrado.getFormacionAcademicaGrado() != null) {
            formacionesAcademicasGrado.add(formacionAcademicaGrado.getFormacionAcademicaGrado());
            cargarFormacionesAcademicasGrado();
        }
    }

    private void quitarFormacionAcademicaGrado() {
        if (jListFormacionAcademicaGrado.getSelectedIndex() != -1) {
            formacionesAcademicasGrado.remove(jListFormacionAcademicaGrado.getSelectedIndex());
            cargarFormacionesAcademicasGrado();
        }
    }

    private void agregarFormacionAcademicaPosgrado() {
        diagFormacionAcademicaPosgrado formacionAcademicaPosgrado = new diagFormacionAcademicaPosgrado(null, true, "Alta");
        formacionAcademicaPosgrado.setLocation(Comunes.centrarDialog(formacionAcademicaPosgrado));
        formacionAcademicaPosgrado.setVisible(true);

        if (formacionAcademicaPosgrado.getFormacionAcademicaPosgrado() != null) {
            formacionesAcademicasPosgrado.add(formacionAcademicaPosgrado.getFormacionAcademicaPosgrado());
            cargarFormacionesAcademicasPosgrado();
        }
    }

    private void quitarFormacionAcademicaPosgrado() {
        if (jListFormacionAcademicaPosgrado.getSelectedIndex() != -1) {
            formacionesAcademicasPosgrado.remove(jListFormacionAcademicaPosgrado.getSelectedIndex());
            cargarFormacionesAcademicasPosgrado();
        }
    }

    private void agregarFormacionAcademicaOtra() {
        diagFormacionAcademicaOtros formacionAcademicaOtros = new diagFormacionAcademicaOtros(null, true, "Alta");
        formacionAcademicaOtros.setLocation(Comunes.centrarDialog(formacionAcademicaOtros));
        formacionAcademicaOtros.setVisible(true);

        if (formacionAcademicaOtros.getFormacionAcademicaOtra() != null) {
            formacionesAcademicasOtras.add(formacionAcademicaOtros.getFormacionAcademicaOtra());
            cargarFormacionesAcademicasOtras();
        }
    }

    private void quitarFormacionAcademicaOtra() {
        if (jListFormacionAcademicaOtra.getSelectedIndex() != -1) {
            formacionesAcademicasOtras.remove(jListFormacionAcademicaOtra.getSelectedIndex());
            cargarFormacionesAcademicasOtras();
        }
    }

    private void cargarFormacionesAcademicasGrado() {
        Comunes.cargarJList(jListFormacionAcademicaGrado, formacionesAcademicasGrado);
    }

    private void cargarFormacionesAcademicasPosgrado() {
        Comunes.cargarJList(jListFormacionAcademicaPosgrado, formacionesAcademicasPosgrado);
    }

    private void cargarFormacionesAcademicasOtras() {
        Comunes.cargarJList(jListFormacionAcademicaOtra, formacionesAcademicasOtras);
    }

    private void agregarNuevaUniversidad() {
        diagUniversidad universidadAlta = new diagUniversidad(null, true, "Alta");
        universidadAlta.setLocation(Comunes.centrarDialog(universidadAlta));
        universidadAlta.setVisible(true);
        cargarUniversidades();
        if (universidadAlta.getUniversidadCreada() != null) {
            cboUniversidad.setSelectedItem(universidadAlta.getUniversidadCreada());
        }
    }

    public void cargarTablaCategorizaciones() {
        DefaultTableModel modeloTabla = new DefaultTableModel();

        cargarTablaEncabezadosCategorizaciones(modeloTabla);

        List<Categorizacion> listaCategorizaciones = investigador.getCategorizaciones();
        if (!listaCategorizaciones.isEmpty()) {
            Object[] fila = new Object[3];
            for (int i = 0; i < listaCategorizaciones.size(); i++) {
                try {
                    if (listaCategorizaciones.get(i).getLlamado().getDescripcion() != null) {
                        fila[0] = listaCategorizaciones.get(i).getLlamado().getDescripcion();
                    } else {
                        fila[0] = "";
                    }
                } catch (Exception e) {
                    fila[0] = "";
                }

                try {
                    if (listaCategorizaciones.get(i).getCategoriaAsignada().getValorCategoria() != null) {
                        fila[1] = listaCategorizaciones.get(i).getCategoriaAsignada().getValorCategoria();
                    } else {
                        fila[1] = "";
                    }
                } catch (Exception e) {
                    fila[1] = "";
                }

                try {
                    if (listaCategorizaciones.get(i).getCategoriaAsignada().getFechaCategoria() != null) {
                        fila[2] = formato.format(listaCategorizaciones.get(i).getCategoriaAsignada().getFechaCategoria());
                    } else {
                        fila[2] = "";
                    }
                } catch (Exception e) {
                    fila[2] = "";
                }

                modeloTabla.addRow(fila);
            }
        }
    }

    private void agregarNuevoDepartamentoDocente() {
        diagDepartamentoDocente departamentoDocente = new diagDepartamentoDocente(null, true, "Alta");
        departamentoDocente.setLocation(Comunes.centrarDialog(departamentoDocente));
        departamentoDocente.setVisible(true);
        cargarDepartamentosDocentes();
        if (departamentoDocente.getDepartamentoDocenteCreada() != null) {
            cboDepartamentoDocente.setSelectedItem(departamentoDocente.getDepartamentoDocenteCreada());
        }
    }

    private void cargarTablaEncabezadosCategorizaciones(DefaultTableModel modeloTabla) {
        modeloTabla.addColumn("Llamado");
        modeloTabla.addColumn("Categoría Asignada");
        modeloTabla.addColumn("Fecha Categoría");
        tblCategorizaciones.setModel(modeloTabla);
    }

    private void agregarNuevaDedicacionDocente() {
        diagDedicacionDocente dedicacionDocente = new diagDedicacionDocente(null, true, "Alta");
        dedicacionDocente.setLocation(Comunes.centrarDialog(dedicacionDocente));
        dedicacionDocente.setVisible(true);
        cargarDedicacionesDocentes();
        if (dedicacionDocente.getDedicacionDocenteCreada() != null) {
            cboDedicacionDocente.setSelectedItem(dedicacionDocente.getDedicacionDocenteCreada());
        }
    }

    private void agregarNuevoModoObtencionCargo() {
        diagModoObtencionCargo modoObtencionCargo = new diagModoObtencionCargo(null, true, "Alta");
        modoObtencionCargo.setLocation(Comunes.centrarDialog(modoObtencionCargo));
        modoObtencionCargo.setVisible(true);
        cargarModosObtencionCargo();
        if (modoObtencionCargo.getModoObtencionCargoCreada() != null) {
            cboModoObtencionCargo.setSelectedItem(modoObtencionCargo.getModoObtencionCargoCreada());
        }
    }

    private void agregarNuevoActividadConduccion() {
        if (tipoOperacionActividadConduccion.equals("Alta")) {

            try {
                actividadConduccion = new ActividadConduccion();
                actividadConduccion.setCargoConduccion((CargoConduccion) cboCargoConduccion.getSelectedItem());
                actividadConduccion.setDedicacion((DedicacionConduccion) cboDedicacionConduccion.getSelectedItem());
                actividadConduccion.setDependencia((UnidadAcademica) cboDependencia.getSelectedItem());
                actividadConduccion.setFechaDesde(jXDateFechaDesdeConduccion.getDate());
                if (chkSigueVigente.isSelected()) {
                    actividadConduccion.setFechaHasta(null);
                } else {
                    actividadConduccion.setFechaHasta(jXDateFechaHastaConduccion.getDate());

                }
                ActividadConduccionFacade.getInstance().alta(actividadConduccion);
                actividadesConduccion.add(actividadConduccion);
                cargarTablaActividadConduccion();
                limpiarCamposActividadConduccion();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("ERRORRRRRRRRRRRRRRRR");
            }
        }
        if (tipoOperacionActividadConduccion.equals("Modificación")) {
            try {

                actividadConduccion.setCargoConduccion((CargoConduccion) cboCargoConduccion.getSelectedItem());
                actividadConduccion.setDedicacion((DedicacionConduccion) cboDedicacionConduccion.getSelectedItem());
                actividadConduccion.setDependencia((UnidadAcademica) cboDependencia.getSelectedItem());
                actividadConduccion.setFechaDesde(jXDateFechaDesdeConduccion.getDate());
                if (chkSigueVigente.isSelected()) {
                    actividadConduccion.setFechaHasta(null);
                } else {
                    actividadConduccion.setFechaHasta(jXDateFechaHastaConduccion.getDate());

                }
                ActividadConduccionFacade.getInstance().editar(actividadConduccion);
                actividadesConduccion.set(actividadesConduccion.indexOf(actividadConduccion), actividadConduccion);
                cargarTablaActividadConduccion();
                limpiarCamposActividadConduccion();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        tipoOperacionActividadConduccion = "Alta";
    }

    private void eliminarActividadConduccion() {
        if (tablaActividadConduccion.getSelectedRow() != -1) {
            ActividadConduccion ACEliminar = ActividadConduccionFacade.getInstance().buscar((Long) tablaActividadConduccion.getValueAt(tablaActividadConduccion.getSelectedRow(), 0));
            actividadesConduccion.remove(ACEliminar);
            ActividadConduccionFacade.getInstance().eliminar(ACEliminar.getId());
            cargarTablaActividadConduccion();

        }
    }

    private void cambiarEstadoFechaHasta() {
        if (chkSigueVigente.isSelected()) {
            jXDateFechaHastaConduccion.setEnabled(false);
        } else {
            jXDateFechaHastaConduccion.setEnabled(true);
        }
    }

    private void verConduccionVigentes() {

        List<ActividadConduccion> listaP = new ArrayList<ActividadConduccion>();
        Iterator i = investigador.getActividadesConduccion().iterator();
        while (i.hasNext()) {
            ActividadConduccion p = (ActividadConduccion) i.next();
            if (p.getFechaHasta() == null) {
                listaP.add(p);

            }
        }
        modeloTablaActividadConduccion = new ModeloTablaNoEditable();
        cargarEncabezadosTablaActividadConduccion(modeloTablaActividadConduccion);

        for (ActividadConduccion actividadCond : listaP) {
            cargarActividadConduccion(actividadCond);

        }
        tablaActividadConduccion.setModel(modeloTablaActividadConduccion);

    }

    private void agregarNuevaEspecialidadInvestigacion() {
        diagEspecialidadInvestigacion especialidadInvestigacion = new diagEspecialidadInvestigacion(null, true, "Alta");
        especialidadInvestigacion.setLocation(Comunes.centrarDialog(especialidadInvestigacion));
        especialidadInvestigacion.setVisible(true);
        cargarEspecialidadInvestigacion();
        if (especialidadInvestigacion.getEspecialidadInvestigacionCreada() != null) {
            cboEspecialidadInvestigacion.setSelectedItem(especialidadInvestigacion.getEspecialidadInvestigacionCreada());
        }
    }

    private void cargarEspecialidadInvestigacion() {
        Comunes.cargarJComboConBlanco(cboEspecialidadInvestigacion, EspecialidadInvestigacionFacade.getInstance().getTodosEspecialidadInvestigacion());
    }

    private void agregarNuevaEspecialidadActividadAcademica() {
        diagEspecialidadActividadAcademica especialidadActividadAcademica = new diagEspecialidadActividadAcademica(null, true, "Alta");
        especialidadActividadAcademica.setLocation(Comunes.centrarDialog(especialidadActividadAcademica));
        especialidadActividadAcademica.setVisible(true);
        cargarEspecialidadActividadAcademica();
        if (especialidadActividadAcademica.getEspecialidadActividadAcademicaCreada() != null) {
            cboEspecialidadActividadAcademica.setSelectedItem(especialidadActividadAcademica.getEspecialidadActividadAcademicaCreada());
        }
    }

    private void cargarEspecialidadActividadAcademica() {
        Comunes.cargarJComboConBlanco(cboEspecialidadActividadAcademica, EspecialidadActividadAcademicaFacade.getInstance().getTodosEspecialidadActividadAcademica());
    }

    private void agregarNuevoCargo() {
        diagCargoConduccion diagCargoConduccion = new diagCargoConduccion(null, true);
        diagCargoConduccion.setLocation(Comunes.centrarDialog(diagCargoConduccion));
        diagCargoConduccion.setVisible(true);
        cargarCargosConduccion();
        if (diagCargoConduccion.getCargoConduccionCreado() != null) {
            cboCargoConduccion.setSelectedItem(diagCargoConduccion.getCargoConduccionCreado());

        }
    }

    private void agregarNuevaDedicacionConduccion() {
        diagDedicacionConduccion diagDedicacionConduccion = new diagDedicacionConduccion(null, true, "Alta");
        diagDedicacionConduccion.setLocation(Comunes.centrarDialog(diagDedicacionConduccion));
        diagDedicacionConduccion.setVisible(true);
        cargarDedicacionesDocentesConduccion();
        if (diagDedicacionConduccion.getDedicacionConduccionCreada() != null) {
            cboDedicacionConduccion.setSelectedItem(diagDedicacionConduccion.getDedicacionConduccionCreada());
        }
    }

    private void cargarCargosConduccion() {
        Comunes.cargarJComboConBlanco(cboCargoConduccion, CargoConduccionFacade.getInstance().listarTodosCargoConduccionOrdenados());
    }

    private void mostrarSegunEstado() {
        if (chkActividadesConduccionVigentes.isSelected()) {
            verConduccionVigentes();
        } else {
            cargarTablaActividadConduccion();
        }
    }

    private void agregarNuevaUnidadAcademica() {
        diagUnidadAcademica diagUnidadAcademica = new diagUnidadAcademica(null, true, "Alta");
        diagUnidadAcademica.setLocation(Comunes.centrarDialog(diagUnidadAcademica));
        diagUnidadAcademica.setVisible(true);
        cargarUnidadesAcademicas();
        if (diagUnidadAcademica.getUnidadAcademicaCreada() != null) {
            cboUnidadAcademica.setSelectedItem(diagUnidadAcademica.getUnidadAcademicaCreada());
        }
    }

    private void agregarNuevaCategoriaDocente() {
        diagCategoriaDocente diagCategoriaDocente = new diagCategoriaDocente(null, true, "Alta");
        diagCategoriaDocente.setLocation(Comunes.centrarDialog(diagCategoriaDocente));
        diagCategoriaDocente.setVisible(true);
        cargarCategoriasDocentes();
        if (diagCategoriaDocente.getCategoriaDocenteCreada() != null) {
            cboCategoriaDocente.setSelectedItem(diagCategoriaDocente.getCategoriaDocenteCreada());

        }
    }

    private void seleccionarAsignatura() {
        DiagBuscarAsignatura buscarAsignatura = new DiagBuscarAsignatura(null, true);
        buscarAsignatura.setLocation(Comunes.centrarDialog(buscarAsignatura));
        buscarAsignatura.setTitle("Seleccione una asignatura");
        buscarAsignatura.setVisible(true);
        buscarAsignatura.pack();
        if (buscarAsignatura.getAsignatura() != null) {
            asignaturaSeleccionada = buscarAsignatura.getAsignatura();
            tfAsignatura.setText(asignaturaSeleccionada.toString());

        }
    }

    private void cargarTipoDuracionAsignatura() {
        Comunes.cargarJComboConBlanco(cboTipoDuracionAsignatura, TipoDuracionAsignaturaFacade.getInstance().listarTodosTipoDuracionAsignatura());
    }

    private void cargarModeloTablaCursos() {
        modeloTablaCursos = new ModeloTablaNoEditable();
        cargarEncabezadosTablaCursos(modeloTablaCursos);
    }

    private void cargarEncabezadosTablaCursos(ModeloTablaNoEditable modeloTabla) {
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Asignatura");
        modeloTabla.addColumn("Tipo Asignatura");

        tablaCursos.setModel(modeloTabla);
    }

    private void cargarEncabezadosTablaActividadConduccion(ModeloTablaNoEditable modeloTabla) {
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Cargo");
        modeloTabla.addColumn("Dedicación");
        modeloTabla.addColumn("Dependencia");
        modeloTabla.addColumn("Fecha desde");
        modeloTabla.addColumn("Fecha hasta");
        tablaActividadConduccion.setModel(modeloTabla);
    }

    private void cargarTablaCursos() {
        modeloTablaCursos = new ModeloTablaNoEditable();
        cargarEncabezadosTablaCursos(modeloTablaCursos);
        for (CursoDictado cursoDic : cursosDictados) {

            cargarCursoDictado(cursoDic);

        }
        tablaCursos.setModel(modeloTablaCursos);

    }

    private void cargarCursoDictado(CursoDictado cursoDictado) {
        Object[] fila = new Object[4];
        fila[0] = cursoDictado.getId();
        fila[1] = cursoDictado.getDuracionAnual();
        fila[2] = cursoDictado.getAsignatura();
        fila[3] = cursoDictado.getTipoAsignatura();
        modeloTablaCursos.addRow(fila);
    }

    private void editarAsignatura() {
        tipoOperacionCursoDictado = "Modificación";
        btnCancelarAsignatura.setVisible(true);
        if (tablaCursos.getSelectedRow() != -1) {
            cursoDictado = CursoDictadoFacade.getInstance().buscar((Long) tablaCursos.getValueAt(tablaCursos.getSelectedRow(), 0));
            try {
                asignaturaSeleccionada = cursoDictado.getAsignatura();
                tfAsignatura.setText(cursoDictado.getAsignatura().toString());
            } catch (Exception ex) {
            }
            try {
                cboTipoDuracionAsignatura.setSelectedItem(cursoDictado.getTipoDuracionAsignatura());
            } catch (Exception ex) {
            }
            try {
                tfAñoAcademico.setText(cursoDictado.getDuracionAnual());
            } catch (Exception ex) {
            }
            try {
                cboTipoAsignatura.setSelectedItem(cursoDictado.getTipoAsignatura());
            } catch (Exception ex) {
            }
            try {
                tfDictadoClases.setText(String.valueOf(cursoDictado.getHorasSemanalesDictadoClases()));
            } catch (Exception ex) {
            }
            try {
                tfDuracion.setText(String.valueOf(cursoDictado.getSemanasDuracion()));
            } catch (Exception ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }
    }

    private void limpiarCampos() {
        tfAsignatura.setText("");
        cboTipoDuracionAsignatura.setSelectedIndex(0);
        tfAñoAcademico.setText("");
        cboTipoAsignatura.setSelectedIndex(0);
        tfDictadoClases.setText("");
        tfDuracion.setText("");
        ftfAño.setText("");
        tipoOperacionCursoDictado = "Alta";
        btnCancelarAsignatura.setVisible(false);

    }

    private void cargarModeloTablaActividadConduccion() {
        modeloTablaActividadConduccion = new ModeloTablaNoEditable();
        cargarEncabezadosTablaActividadConduccion(modeloTablaActividadConduccion);
    }

    private void cargarTablaActividadConduccion() {

        modeloTablaActividadConduccion = new ModeloTablaNoEditable();
        cargarEncabezadosTablaActividadConduccion(modeloTablaActividadConduccion);

        for (ActividadConduccion actividadCond : actividadesConduccion) {
            cargarActividadConduccion(actividadCond);

        }
        tablaActividadConduccion.setModel(modeloTablaActividadConduccion);
    }

    private void cargarActividadConduccion(ActividadConduccion actividadCond) {
        Object[] fila = new Object[6];
        fila[0] = actividadCond.getId();
        fila[1] = actividadCond.getCargoConduccion();
        fila[2] = actividadCond.getDedicacion();
        fila[3] = actividadCond.getDependencia();
        try {

            fila[4] = formato.format(actividadCond.getFechaDesde());
        } catch (Exception ex) {
        }
        try {
            fila[5] = formato.format(actividadCond.getFechaHasta());
        } catch (Exception ex) {
        }

        modeloTablaActividadConduccion.addRow(fila);
    }

    private void limpiarCamposActividadConduccion() {
        cboCargoConduccion.setSelectedIndex(0);
        cboDedicacionConduccion.setSelectedIndex(0);
        cboDependencia.setSelectedIndex(0);
        jXDateFechaDesdeConduccion.setDate(new Date());
        jXDateFechaHastaConduccion.setDate(new Date());
        chkSigueVigente.setSelected(false);
        tipoOperacionActividadConduccion = "Alta";
        btnCancelarActividadConduccion.setVisible(false);
    }

    private void modificarActividadConduccion() {
        if (tablaActividadConduccion.getSelectedRow() != -1) {
            tipoOperacionActividadConduccion = "Modificación";
            btnCancelarActividadConduccion.setVisible(true);
            actividadConduccion = ActividadConduccionFacade.getInstance().buscar((Long) tablaActividadConduccion.getValueAt(tablaActividadConduccion.getSelectedRow(), 0));
            try {
                cboCargoConduccion.setSelectedItem(actividadConduccion.getCargoConduccion());
            } catch (Exception ex) {
            }
            try {
                cboDedicacionConduccion.setSelectedItem(actividadConduccion.getDedicacion());
            } catch (Exception ex) {
            }
            try {
                cboDependencia.setSelectedItem(actividadConduccion.getDependencia());
            } catch (Exception ex) {
            }
            try {
                jXDateFechaDesdeConduccion.setDate(actividadConduccion.getFechaDesde());
            } catch (Exception ex) {
            }
            try {
                jXDateFechaHastaConduccion.setDate(actividadConduccion.getFechaHasta());
            } catch (Exception ex) {
            }
            try {
                if (actividadConduccion.getFechaHasta() == null) {
                    chkSigueVigente.setSelected(true);
                } else {
                    chkSigueVigente.setSelected(false);
                }

            } catch (Exception ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila!");
        }

    }

    private void agregarDocencia() {
        if (tipoOperacionDocencia.equals("Alta")) {
            if (cboUniversidad.getSelectedIndex() >= 0) {
                try {
                    docencia = new Docencia();

                    docencia.setUniversidad((Universidad) cboUniversidad.getSelectedItem());

                    docencia.setUnidadAcademica((UnidadAcademica) cboUnidadAcademica.getSelectedItem());

                    docencia.setDepartamentoDocente((DepartamentoDocente) cboDepartamentoDocente.getSelectedItem());

                    docencia.setCategoriaDocente((CategoriaDocente) cboCategoriaDocente.getSelectedItem());

                    docencia.setDedicacionDocente((DedicacionDocente) cboDedicacionDocente.getSelectedItem());

                    docencia.setModoObtencionCargo((ModoObtencionCargo) cboModoObtencionCargo.getSelectedItem());

                    docencia.setFechaObtencionCargo(jXDateFechaObtencionCargo.getDate());

                    docencia.setHorasDedicadasDocenciaPrimerCuatrimestre(Integer.parseInt(tfHorasDedicacionPrimerCuatrimestre.getText()));

                    docencia.setSemanasDedicadasDocenciaPrimerCuatrimestre(Integer.parseInt(tfSemanasDedicacionPrimerCuatrimestre.getText()));

                    docencia.setHorasDedicadasDocenciaSegundoCuatrimestre(Integer.parseInt(tfHorasDedicacionSegundoCuatrimestre.getText()));

                    docencia.setSemanasDedicadasDocenciaSegundoCuatrimestre(Integer.parseInt(tfSemanasDedicacionSegundoCuatrimestre.getText()));

                    docencia.setAño(Integer.parseInt(ftfAño.getText()));
                    docencia.setInvestigador(investigador);

                    DocenciaFacade.getInstance().alta(docencia);
                    docencias.add(docencia);
                    cargarTablaDocencia();
                    limpiarCamposDocencia();
                    limpiarCampos();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Por favor complete todos los datos de docencia");

                }

            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una Universidad");
                cboUniversidad.requestFocus();
            }
        }
        if (tipoOperacionDocencia.equals("Modificación")) {
            if (cboUniversidad.getSelectedIndex() >= 0) {
                try {
                    docencia.setUniversidad((Universidad) cboUniversidad.getSelectedItem());

                    docencia.setUnidadAcademica((UnidadAcademica) cboUnidadAcademica.getSelectedItem());

                    docencia.setDepartamentoDocente((DepartamentoDocente) cboDepartamentoDocente.getSelectedItem());

                    docencia.setCategoriaDocente((CategoriaDocente) cboCategoriaDocente.getSelectedItem());

                    docencia.setDedicacionDocente((DedicacionDocente) cboDedicacionDocente.getSelectedItem());

                    docencia.setModoObtencionCargo((ModoObtencionCargo) cboModoObtencionCargo.getSelectedItem());

                    docencia.setFechaObtencionCargo(jXDateFechaObtencionCargo.getDate());

                    docencia.setHorasDedicadasDocenciaPrimerCuatrimestre(Integer.parseInt(tfHorasDedicacionPrimerCuatrimestre.getText()));

                    docencia.setSemanasDedicadasDocenciaPrimerCuatrimestre(Integer.parseInt(tfSemanasDedicacionPrimerCuatrimestre.getText()));

                    docencia.setHorasDedicadasDocenciaSegundoCuatrimestre(Integer.parseInt(tfHorasDedicacionSegundoCuatrimestre.getText()));

                    docencia.setSemanasDedicadasDocenciaSegundoCuatrimestre(Integer.parseInt(tfSemanasDedicacionSegundoCuatrimestre.getText()));

                    docencia.setAño(Integer.parseInt(ftfAño.getText()));

                    DocenciaFacade.getInstance().modificar(docencia);
                    docencias.set(docencias.indexOf(docencia), docencia);
                    cargarTablaDocencia();
                    limpiarCamposDocencia();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Por favor complete todos los datos de docencia");

                }

            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una Universidad");
                cboUniversidad.requestFocus();
            }
        }
        tipoOperacionDocencia = "Alta";
    }

    private void cargarTablaDocencia() {
        modeloTablaDocencia = new ModeloTablaNoEditable();
        cargarEncabezadosTablaDocencia(modeloTablaDocencia);
        try {
            for (Docencia doc : docencias) {
                cargarDocencia(doc);
            }
        } catch (Exception ex) {
            System.out.println("Error cargando docencia: " + ex);
        }
        tablaDocencia.setModel(modeloTablaDocencia);
    }

    private void cargarModeloTablaDocencia() {
        modeloTablaDocencia = new ModeloTablaNoEditable();
        cargarEncabezadosTablaDocencia(modeloTablaDocencia);
    }

    private void cargarEncabezadosTablaDocencia(ModeloTablaNoEditable modeloTabla) {
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Universidad");
        modeloTabla.addColumn("Unidad Academica");
        modeloTabla.addColumn("Categoria Docente");
        modeloTabla.addColumn("Dedicacion Docente");

        tablaDocencia.setModel(modeloTabla);
    }

    private void cargarDocencia(Docencia docencia) {
        Object[] fila = new Object[6];
        fila[0] = docencia.getId();
        fila[1] = docencia.getAño();
        fila[2] = docencia.getUniversidad();
        fila[3] = docencia.getUnidadAcademica();
        fila[4] = docencia.getCategoriaDocente();
        fila[5] = docencia.getDedicacionDocente();
        modeloTablaDocencia.addRow(fila);
    }

    private void limpiarCamposDocencia() {
        ftfAño.setText("");
        cboUniversidad.setSelectedIndex(0);
        cboUnidadAcademica.setSelectedIndex(0);
        cboCategoriaDocente.setSelectedIndex(0);
        cboDepartamentoDocente.setSelectedIndex(0);
        cboDedicacionDocente.setSelectedIndex(0);
        cboModoObtencionCargo.setSelectedIndex(0);
        jXDateFechaObtencionCargo.setDate(new Date());
        tfHorasDedicacionPrimerCuatrimestre.setText("");
        tfSemanasDedicacionPrimerCuatrimestre.setText("");
        tfHorasDedicacionSegundoCuatrimestre.setText("");
        tfSemanasDedicacionSegundoCuatrimestre.setText("");
        tipoOperacionDocencia = "Alta";

    }

    private void agregarDependencia() {
        diagUnidadAcademica diagUnidadAcademica = new diagUnidadAcademica(null, true, "Alta");
        diagUnidadAcademica.setLocation(Comunes.centrarDialog(diagUnidadAcademica));
        diagUnidadAcademica.setVisible(true);
        cargarDependenciasConduccion();
        if (diagUnidadAcademica.getUnidadAcademicaCreada() != null) {
            cboDependencia.setSelectedItem(diagUnidadAcademica.getUnidadAcademicaCreada());
        }
    }

    private void modificarDocencia() {
        if (tablaDocencia.getSelectedRow() != -1) {
            tipoOperacionDocencia = "Modificación";
            btnCancelarDocencia.setVisible(true);
            docencia = DocenciaFacade.getInstance().buscar((Long) tablaDocencia.getValueAt(tablaDocencia.getSelectedRow(), 0));
            try {
                ftfAño.setText(String.valueOf(docencia.getAño()));
            } catch (Exception ex) {
            }
            try {
                cboUniversidad.setSelectedItem(docencia.getUniversidad());
            } catch (Exception ex) {
            }
            try {
                cboUnidadAcademica.setSelectedItem(docencia.getUnidadAcademica());
            } catch (Exception ex) {
            }
            try {
                cboDepartamentoDocente.setSelectedItem(docencia.getDepartamentoDocente());
            } catch (Exception ex) {
            }
            try {
                cboCategoriaDocente.setSelectedItem(docencia.getCategoriaDocente());
            } catch (Exception ex) {
            }
            try {
                cboDedicacionDocente.setSelectedItem(docencia.getDedicacionDocente());
            } catch (Exception ex) {
            }
            try {
                cboModoObtencionCargo.setSelectedItem(docencia.getModoObtencionCargo());
            } catch (Exception ex) {
            }
            try {
                jXDateFechaObtencionCargo.setDate(docencia.getFechaObtencionCargo());
            } catch (Exception ex) {
            }
            try {
                tfHorasDedicacionPrimerCuatrimestre.setText(String.valueOf(docencia.getHorasDedicadasDocenciaPrimerCuatrimestre()));
            } catch (Exception ex) {
            }
            try {
                tfSemanasDedicacionPrimerCuatrimestre.setText(String.valueOf(docencia.getSemanasDedicadasDocenciaPrimerCuatrimestre()));
            } catch (Exception ex) {
            }
            try {
                tfHorasDedicacionSegundoCuatrimestre.setText(String.valueOf(docencia.getHorasDedicadasDocenciaSegundoCuatrimestre()));
            } catch (Exception ex) {
            }
            try {
                tfSemanasDedicacionSegundoCuatrimestre.setText(String.valueOf(docencia.getSemanasDedicadasDocenciaSegundoCuatrimestre()));
            } catch (Exception ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila!");
        }

    }

    private void eliminarDocencia() {
        if (tablaDocencia.getSelectedRow() != -1) {
            Docencia DEliminar = DocenciaFacade.getInstance().buscar((Long) tablaDocencia.getValueAt(tablaDocencia.getSelectedRow(), 0));
            docencias.remove(DEliminar);
            DocenciaFacade.getInstance().eliminar(DEliminar.getId());
            cargarTablaDocencia();

        }

    }

    private void verMensajes() {
        diagMensajes diagMensajes = new diagMensajes(null, true, investigador);
        diagMensajes.setLocation(Comunes.centrarDialog(diagMensajes));
        diagMensajes.setVisible(true);
    }

    private void agregarNuevaEspecializacion() {
        if (investigador != null) {
            if (validarEspecializacion()) {
                if (tipoOperacionEspecializacion.equals("Alta")) {
                    especializacion = new Especializacion();
                    try {
                        especializacion.setAño(Integer.parseInt(tfAñoEspecializacion.getText()));
                    } catch (Exception ex) {
                        especializacion.setAño(0);
                    }

                    especializacion.setEspecialidadActividadAcademica((EspecialidadActividadAcademica) cboEspecialidadActividadAcademica.getSelectedItem());
                    especializacion.setEspecialidadInvestigacion((EspecialidadInvestigacion) cboEspecialidadInvestigacion.getSelectedItem());
                    especializacion.setInvestigador(investigador);
                    EspecializacionFacade.getInstance().alta(especializacion);
                    especializaciones.add(especializacion);
                    cargarTablaEspecializacion();
                    limpiarCamposEspecializacion();
                }
                if (tipoOperacionEspecializacion.equals("Modificación")) {
                    try {
                        especializacion.setAño(Integer.parseInt(tfAñoEspecializacion.getText()));
                    } catch (Exception ex) {
                        especializacion.setAño(0);
                    }
                    especializacion.setEspecialidadActividadAcademica((EspecialidadActividadAcademica) cboEspecialidadActividadAcademica.getSelectedItem());
                    especializacion.setEspecialidadInvestigacion((EspecialidadInvestigacion) cboEspecialidadInvestigacion.getSelectedItem());
                    EspecializacionFacade.getInstance().editar(especializacion);
                    especializaciones.set(especializaciones.indexOf(especializacion), especializacion);
                    cargarTablaEspecializacion();
                    limpiarCamposEspecializacion();
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe dar de Alta previamente al investigador");
        }

    }

    private boolean validarEspecializacion() {
        boolean flag = false;
        if (Comunes.validarInteger(tfAñoEspecializacion.getText())) {
            if (cboEspecialidadInvestigacion.getSelectedIndex() > 0) {
                if (cboEspecialidadActividadAcademica.getSelectedIndex() > 0) {
                    flag = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione Actividad Academica");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione Especialidad Investigacion");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ecriba el Año de la especializacion");
        }
        return flag;

    }

    private void cargarModeloTablaEspecializaciones() {
        modeloTablaEspecializacion = new ModeloTablaNoEditable();
        cargarEncabezadosTablaEspecializacion(modeloTablaEspecializacion);
    }

    private void cargarEncabezadosTablaEspecializacion(ModeloTablaNoEditable modeloTabla) {
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Esp. Investigacion");
        modeloTabla.addColumn("Esp. Actividad Academica");
        tablaEspecializaciones.setModel(modeloTabla);

    }

    private void cargarTablaEspecializacion() {
        modeloTablaEspecializacion = new ModeloTablaNoEditable();
        cargarEncabezadosTablaEspecializacion(modeloTablaEspecializacion);
        try {
            for (Especializacion esp : especializaciones) {
                cargarEspecializacion(esp);
            }
        } catch (Exception ex) {
            System.out.println("Error cargando Especializaciones: " + ex);
        }
        tablaEspecializaciones.setModel(modeloTablaEspecializacion);
    }

    private void cargarEspecializacion(Especializacion esp) {
        Object[] fila = new Object[4];
        fila[0] = esp.getId();
        fila[1] = esp.getAño();
        fila[2] = esp.getEspecialidadInvestigacion();
        fila[3] = esp.getEspecialidadActividadAcademica();
        modeloTablaEspecializacion.addRow(fila);

    }

    private void limpiarCamposEspecializacion() {
        tfAñoEspecializacion.setText("");
        cboEspecialidadActividadAcademica.setSelectedIndex(0);
        cboEspecialidadInvestigacion.setSelectedIndex(0);
        tipoOperacionEspecializacion = "Alta";
        btnCancelarEspecializacion.setVisible(false);
    }

    private void editarEspecializacion() {
        tipoOperacionEspecializacion = "Modificación";
        btnCancelarEspecializacion.setVisible(true);
        if (tablaEspecializaciones.getSelectedRow() != -1) {
            especializacion = EspecializacionFacade.getInstance().buscar((Long) tablaEspecializaciones.getValueAt(tablaEspecializaciones.getSelectedRow(), 0));
            try {
                tfAñoEspecializacion.setText(String.valueOf(especializacion.getAño()));
            } catch (Exception ex) {
            }
            try {
                cboEspecialidadActividadAcademica.setSelectedItem(especializacion.getEspecialidadActividadAcademica());
            } catch (Exception ex) {
            }
            try {
                cboEspecialidadInvestigacion.setSelectedItem(especializacion.getEspecialidadInvestigacion());
            } catch (Exception ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }
    }

    private void eliminarEspecializacion() {
        if (tablaEspecializaciones.getSelectedRow() != -1) {
            Especializacion especializacionEliminar = EspecializacionFacade.getInstance().buscar((Long) tablaEspecializaciones.getValueAt(tablaEspecializaciones.getSelectedRow(), 0));
            especializaciones.remove(especializacionEliminar);
            EspecializacionFacade.getInstance().eliminar(especializacionEliminar.getId());
            cargarTablaEspecializacion();

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para poder eliminar");
        }
    }

    private void agregarNuevaResolucion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarDocumentacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void abrirDocumentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void agregarDocumentacion() {
        diagResolucionEleccion resolucionEleccion = new diagResolucionEleccion(null, true);
        resolucionEleccion.setLocation(Comunes.centrarDialog(resolucionEleccion));
        resolucionEleccion.setVisible(true);
        if (resolucionEleccion.getResolucion() != null) {
            resoluciones.add(resolucionEleccion.getResolucion());
            investigador.setResoluciones(resoluciones);
            cargarDocumentacion();
        }
    }

    private void cargarDocumentacion() {
        Comunes.cargarJList(jlstDocumentacion, resoluciones);
    }

    private void quitarDocumentacion() {
        if (jlstDocumentacion.getSelectedIndex() != -1) {
            investigador.getResoluciones().remove((Resolucion) jlstDocumentacion.getSelectedValue());
            cargarDocumentacion();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento de la lista!");
        }
    }

    private void mostrarDocumentacion() {
        if (jlstDocumentacion.getSelectedIndex() != -1) //deberás hacer 
        {
            try {
                Resolucion resolucion = (Resolucion) jlstDocumentacion.getSelectedValue();
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

    private void verDetallesProyectoVinculacion() {
        if (jListVinculacion.getSelectedIndex() > 0) {
            diagProyectoVinculacion proyectoVinculacion = new diagProyectoVinculacion(null, true, "Modificación", (ProyectoVinculacion) jListVinculacion.getSelectedValue(), usuario);
            proyectoVinculacion.setLocation(Comunes.centrarDialog(proyectoVinculacion));
            proyectoVinculacion.setVisible(true);
        }

    }

    private void verDetallesProyectoInvestigacion() {
        if (jListParticipaciones.getSelectedIndex() > 0) {
            diagProyecto proyecto = new diagProyecto(null, true, "Modificación", (Proyecto) jListParticipaciones.getSelectedValue(), usuario);
            proyecto.setLocation(Comunes.centrarDialog(proyecto));
            proyecto.setVisible(true);
        }
    }

    private void modificarResolucion() {
        if (jlstDocumentacion.getSelectedIndex() != -1) {
            diagResolucionAlta resolucionAlta = new diagResolucionAlta(null, true, "Modificación", (Resolucion) jlstDocumentacion.getSelectedValue());
            resolucionAlta.setLocation(Comunes.centrarDialog(resolucionAlta));
            resolucionAlta.setVisible(true);
            if (resolucionAlta.getResolucion() != null) {
                resoluciones.set(jlstDocumentacion.getSelectedIndex(), resolucionAlta.getResolucion());
                investigador.setResoluciones(resoluciones);
                cargarDocumentacion();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento de la lista!");
        }

    }
}
