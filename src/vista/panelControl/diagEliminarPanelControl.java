/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * diagEliminarPanelControl.java
 *
 * Created on 06-dic-2011, 9:08:01
 */
package vista.panelControl;

import entidades.DedicacionDocente;
import entidades.Institucion;
import entidades.TipoProyecto;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.Universidad;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.investigador.formacionAcademica.FormacionAcademicaOtra;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
import entidades.localidad.Localidad;
import entidades.persona.investigador.CategoriaDocente;
import entidades.persona.investigador.DepartamentoDocente;
import entidades.persona.investigador.Docencia;
import entidades.persona.investigador.EspecialidadActividadAcademica;
import entidades.persona.investigador.EspecialidadInvestigacion;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.ModoObtencionCargo;
import entidades.persona.investigador.actividadConduccion.CargoConduccion;
import entidades.persona.investigador.actividadConduccion.DedicacionConduccion;
import entidades.persona.investigador.curso.CarreraAsignatura;
import entidades.persona.investigador.curso.TipoAsignatura;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
import entidades.proyecto.AreaTematica;
import entidades.proyecto.CampoAplicacion;
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.EntidadConvenio;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.Especialidad;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.LineaPrioritaria;
import entidades.proyecto.ObjetivoSocioeconomico;
import entidades.proyecto.Programa;
import entidades.proyecto.Proyecto;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.UnidadInvestigacion;
import entidades.titulo.Titulo;
import entidades.titulo.TituloGrado;
import entidades.titulo.TituloOtro;
import entidades.titulo.TituloPosgrado;
import facade.AreaTematicaFacade;
import facade.CampoAplicacionFacade;
import facade.CargoConduccionFacade;
import facade.CategoriaDocenteFacade;
import facade.DedicacionConduccionFacade;
import facade.DedicacionDocenteFacade;
import facade.DepartamentoDocenteFacade;
import facade.DisciplinaCientificaFacade;
import facade.DocenciaFacade;
import facade.EntidadConvenioFacade;
import facade.EntidadEvaluadoraFacade;
import facade.EspecialidadActividadAcademicaFacade;
import facade.EspecialidadFacade;
import facade.EspecialidadInvestigacionFacade;
import facade.FormacionAcademicaGradoFacade;
import facade.FormacionAcademicaOtraFacade;
import facade.FormacionAcademicaPosgradoFacade;
import facade.InstitucionFacade;
import facade.InvestigadorFacade;
import facade.LineaInvestigacionFacade;
import facade.LineaPrioritariaFacade;
import facade.LocalidadFacade;
import facade.ModoObtencionCargoFacade;
import facade.ObjetivoSocioeconomicoFacade;
import facade.ProgramaFacade;
import facade.ProyectoFacade;
import facade.SubDisciplinaCientificaFacade;
import facade.TipoActividadFacade;
import facade.TipoAsignaturaFacade;
import facade.TipoProyectoFacade;
import facade.TituloFacade;
import facade.TituloGradoFacade;
import facade.TituloOtroFacade;
import facade.TituloPosgradoFacade;
import facade.UnidadAcademicaFacade;
import facade.UnidadEjecutoraFacade;
import facade.UnidadInvestigacionFacade;
import facade.UniversidadFacade;
import facade.persona.investigador.curso.CarreraAsignaturaFacade;
import facade.persona.investigador.curso.TipoDuracionAsignaturaFacade;
import includes.Comunes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.swing.JOptionPane;
import vista.diagCargoConduccion;
import vista.diagLineaPrioritariaAlta;

/**
 *
 * @author panchi
 */
public class diagEliminarPanelControl extends javax.swing.JDialog {

    private List<TituloGrado> listaTituloGrado = new ArrayList<TituloGrado>();
    private List<TituloPosgrado> listaTituloPosgrado = new ArrayList<TituloPosgrado>();
    private List<TituloOtro> listaTituloOtro = new ArrayList<TituloOtro>();
    private List<CampoAplicacion> listaCampoAplicacion = new ArrayList<CampoAplicacion>();
    private List<Universidad> listaUniversidades = new ArrayList<Universidad>();
    private List<Institucion> listaInstituciones = new ArrayList<Institucion>();
    private List<EntidadConvenio> listaConvenios = new ArrayList<EntidadConvenio>();
    private List<UnidadInvestigacion> listaUnidadesInvestigacion = new ArrayList<UnidadInvestigacion>();
    private List<Especialidad> listaEspecialidades = new ArrayList<Especialidad>();
    private List<SubDisciplinaCientifica> listaSubdisciplina = new ArrayList<SubDisciplinaCientifica>();
    String tipo = null;
    private List<UnidadAcademica> listaUnidadesAcademicas = new ArrayList<>();
    LineaPrioritariaFacade lineaPrioritariaFacade = new LineaPrioritariaFacade();
    private List<LineaPrioritaria> listaLineasPrioritarias = new ArrayList<>();
    private List<Localidad> listaLocalidades = new ArrayList<>();

    /**
     * Creates new form diagEliminarPanelControl
     */
    public diagEliminarPanelControl(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTitulos = new javax.swing.JList();
        jButton10 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        tfFiltro = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        btnAgregarElemento = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cboEntidad = new javax.swing.JComboBox();
        btnBuscaEntidad = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setViewportView(jListTitulos);

        jButton10.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton10.text")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton4.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnAgregarElemento.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.btnAgregarElemento.text")); // NOI18N
        btnAgregarElemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarElementoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarElemento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton4)
                    .addComponent(btnAgregarElemento))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jPanel6.border.title"))); // NOI18N

        cboEntidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Unidad Académica", "Línea de Investigacion", "Unidad Ejecutora", "Tipo Actividades", "Entidad Evaluadora", "Programa", "Objetivo Socioeconómico", "Subdisciplinas Científicas", "Disciplinas Científicas", "Areas Temáticas", "Departamento Docente", "Categoría Docente", "Dedicación Docente", "Modo de Obtencion del Cargo", "Cargo Conduccion", "Dedicacion Conduccion", "Especialidad Investigacion", "Especialidad Actividad Académica", "Tipo de duración de Asignatura", "Tipo Asignatura", "Carrera Asignatura", "Localidad", "Institucion Financiera", "Línea Prioritaria" }));

        btnBuscaEntidad.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.btnBuscaEntidad.text")); // NOI18N
        btnBuscaEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaEntidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscaEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscaEntidad))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton12.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton12.text")); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton13.text")); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addGap(16, 16, 16))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton5.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton9.text")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText(org.openide.util.NbBundle.getMessage(diagEliminarPanelControl.class, "diagEliminarPanelControl.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton11.setText("Localidades");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    cargarTitulosGrado();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    filtrar();
}//GEN-LAST:event_jButton7ActionPerformed

private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    eliminar();
}//GEN-LAST:event_jButton10ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    cargarTitulosPosgrado();
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    cargarOtrosTitulos();
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    modificar();// TODO add your handling code here:
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    cargarCamposDeAplicacion();
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
    cargarUniversidades();
}//GEN-LAST:event_jButton12ActionPerformed

private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    cargarInstitucion();
}//GEN-LAST:event_jButton13ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    cargarConvenioCon();
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    cargarUnidadesInvestigacion();
}//GEN-LAST:event_jButton8ActionPerformed

private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    cargarEspecialidad();
}//GEN-LAST:event_jButton9ActionPerformed

    private void btnBuscaEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaEntidadActionPerformed
        buscarEntidad();
    }//GEN-LAST:event_btnBuscaEntidadActionPerformed

    private void btnAgregarElementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarElementoActionPerformed
        Agregar();
    }//GEN-LAST:event_btnAgregarElementoActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        cargarLocalidad();
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(diagEliminarPanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagEliminarPanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagEliminarPanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagEliminarPanelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                diagEliminarPanelControl dialog = new diagEliminarPanelControl(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarElemento;
    private javax.swing.JButton btnBuscaEntidad;
    private javax.swing.JComboBox cboEntidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JList jListTitulos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tfFiltro;
    // End of variables declaration//GEN-END:variables

    private void modificar() {
        if (jListTitulos.getSelectedIndex() != -1) {
            switch (tipo) {
                case "grado":
                    TituloGrado tituloGrado = (TituloGrado) jListTitulos.getSelectedValue();
                    diagTituloGrado tituloGradoModificacion = new diagTituloGrado(null, true, "Modificación", tituloGrado);
                    tituloGradoModificacion.setLocation(Comunes.centrarDialog(tituloGradoModificacion));
                    tituloGradoModificacion.setVisible(true);
                    cargarTitulosGrado();
                    break;
                case "posgrado":
                    TituloPosgrado tituloPosgrado = (TituloPosgrado) jListTitulos.getSelectedValue();
                    diagTituloPosgrado tituloPosgradoModificacion = new diagTituloPosgrado(null, true, "Modificación", tituloPosgrado);
                    tituloPosgradoModificacion.setLocation(Comunes.centrarDialog(tituloPosgradoModificacion));
                    tituloPosgradoModificacion.setVisible(true);
                    cargarTitulosPosgrado();
                    break;
                case "otros":
                    TituloOtro tituloOtro = (TituloOtro) jListTitulos.getSelectedValue();
                    diagTituloOtro tituloOtroModificacion = new diagTituloOtro(null, true, "Modificación", tituloOtro);
                    tituloOtroModificacion.setLocation(Comunes.centrarDialog(tituloOtroModificacion));
                    tituloOtroModificacion.setVisible(true);
                    cargarOtrosTitulos();
                    break;
                case "camposDeAplicacion":
                    CampoAplicacion campoAplicacion = (CampoAplicacion) jListTitulos.getSelectedValue();
                    diagCampoAplicacion diagCampoAplicacion = new diagCampoAplicacion(null, true, "Modificación", campoAplicacion);
                    diagCampoAplicacion.setLocation(Comunes.centrarDialog(diagCampoAplicacion));
                    diagCampoAplicacion.setVisible(true);
                    cargarCamposDeAplicacion();
                    break;
                case "universidad":
                    Universidad universidad = (Universidad) jListTitulos.getSelectedValue();
                    diagUniversidad diagUniversidad = new diagUniversidad(null, true, "Modificación", universidad);
                    diagUniversidad.setLocation(Comunes.centrarDialog(diagUniversidad));
                    diagUniversidad.setVisible(true);
                    cargarUniversidades();
                    break;
                case "institucion":
                    Institucion institucion = (Institucion) jListTitulos.getSelectedValue();
                    diagInstitucion diagInstitucion = new diagInstitucion(null, true, "Modificación", institucion);
                    diagInstitucion.setLocation(Comunes.centrarDialog(diagInstitucion));
                    diagInstitucion.setVisible(true);
                    cargarInstitucion();
                    break;
                case "convenioCon":
                    EntidadConvenio entidadConvenio = (EntidadConvenio) jListTitulos.getSelectedValue();
                    diagEntidadConvenio diagEntidadConvenio = new diagEntidadConvenio(null, true, "Modificación", entidadConvenio);
                    diagEntidadConvenio.setLocation(Comunes.centrarDialog(diagEntidadConvenio));
                    diagEntidadConvenio.setVisible(true);
                    cargarConvenioCon();
                    break;
                case "especialidad":
                    Especialidad especialidad = (Especialidad) jListTitulos.getSelectedValue();
                    diagEspecialidad diagEspecialidad = new diagEspecialidad(null, true, "Modificación", especialidad);
                    diagEspecialidad.setLocation(Comunes.centrarDialog(diagEspecialidad));
                    diagEspecialidad.setVisible(true);
                    cargarEspecialidad();
                    break;
                case "unidadInvestigacion":
                    UnidadInvestigacion unidadInvestigacion = (UnidadInvestigacion) jListTitulos.getSelectedValue();
                    diagUnidadInvestigacion diagUnidadInvestigacion = new diagUnidadInvestigacion(null, true, "Modificación", unidadInvestigacion);
                    diagUnidadInvestigacion.setLocation(Comunes.centrarDialog(diagUnidadInvestigacion));
                    diagUnidadInvestigacion.setVisible(true);
                    cargarUnidadesInvestigacion();
                    break;
                case "subdisciplina":
                    SubDisciplinaCientifica subdisciplinaCientifica = (SubDisciplinaCientifica) jListTitulos.getSelectedValue();
                    diagSubDisciplinaCientifica diagSubdisciplinaCientifica = new diagSubDisciplinaCientifica(null, true, "Modificación", subdisciplinaCientifica);
                    diagSubdisciplinaCientifica.setLocation(Comunes.centrarDialog(diagSubdisciplinaCientifica));
                    diagSubdisciplinaCientifica.setVisible(true);
                    cargarSubdisciplina();
                    break;
                case "Unidad Académica":
                    UnidadAcademica unidadAcademinca = (UnidadAcademica) jListTitulos.getSelectedValue();
                    diagUnidadAcademica diagUnidadAcademica = new diagUnidadAcademica(null, true, "Modificación", unidadAcademinca);
                    diagUnidadAcademica.setLocation(Comunes.centrarDialog(diagUnidadAcademica));
                    diagUnidadAcademica.setVisible(true);
                    cargarUnidadesAcademicas();
                    break;
                case "Línea de Investigacion":
                    LineaInvestigacion lineaInvestigacion = (LineaInvestigacion) jListTitulos.getSelectedValue();
                    diagLineaInvestigacion diagLineaIvestigacion = new diagLineaInvestigacion(null, true, "Modificación", lineaInvestigacion);
                    diagLineaIvestigacion.setLocation(Comunes.centrarDialog(diagLineaIvestigacion));
                    diagLineaIvestigacion.setVisible(true);
                    cargarLineasInvestigacion();
                    break;
                case "Unidad Ejecutora":
                    UnidadEjecutora unidadEjecutora = (UnidadEjecutora) jListTitulos.getSelectedValue();
                    diagUnidadEjecutora diagUnidadEjecutora = new diagUnidadEjecutora(null, true, "Modificación", unidadEjecutora);
                    diagUnidadEjecutora.setLocation(Comunes.centrarDialog(diagUnidadEjecutora));
                    diagUnidadEjecutora.setVisible(true);
                    cargarUnidadesEjecutoras();
                    break;
                case "Tipo Actividades":
                    TipoProyecto tipoProyecto = (TipoProyecto) jListTitulos.getSelectedValue();
                    diagTipoProyecto diagTipoProyecto = new diagTipoProyecto(null, true, "Modificación", tipoProyecto);
                    diagTipoProyecto.setLocation(Comunes.centrarDialog(diagTipoProyecto));
                    diagTipoProyecto.setVisible(true);
                    cargarTipoActividades();
                    break;
                case "Entidad Evaluadora":
                    EntidadEvaluadora entidadEvaluadora = (EntidadEvaluadora) jListTitulos.getSelectedValue();
                    diagEntidadEvaluadora diagEntidadEvaluadora = new diagEntidadEvaluadora(null, true, "Modificación", entidadEvaluadora);
                    diagEntidadEvaluadora.setLocation(Comunes.centrarDialog(diagEntidadEvaluadora));
                    diagEntidadEvaluadora.setVisible(true);
                    cargarEntidadesEvaluadoras();
                    break;
                case "Programa":
                    Programa programa = (Programa) jListTitulos.getSelectedValue();
                    diagPrograma diagPrograna = new diagPrograma(null, true, "Modificación", programa);
                    diagPrograna.setLocation(Comunes.centrarDialog(diagPrograna));
                    diagPrograna.setVisible(true);
                    cargarProgramas();
                    break;
                case "Objetivo Socioeconómico":
                    ObjetivoSocioeconomico objetivoSocioeconomico = (ObjetivoSocioeconomico) jListTitulos.getSelectedValue();
                    diagObjetivoSocioeconomico diagObjetivoSE = new diagObjetivoSocioeconomico(null, true, "Modificación", objetivoSocioeconomico);
                    diagObjetivoSE.setLocation(Comunes.centrarDialog(diagObjetivoSE));
                    diagObjetivoSE.setVisible(true);
                    cargarObjetivosSocioeconomicos();
                    break;
                case "Disciplina":
                    DisciplinaCientifica disciplina = (DisciplinaCientifica) jListTitulos.getSelectedValue();
                    diagDisciplinaCientifica diagDisciplina = new diagDisciplinaCientifica(null, true, "Modificación", disciplina);
                    diagDisciplina.setLocation(Comunes.centrarDialog(diagDisciplina));
                    diagDisciplina.setVisible(true);
                    cargarDisciplinasCientificas();
                    break;
                case "Area Temática":
                    AreaTematica areaTematica = (AreaTematica) jListTitulos.getSelectedValue();
                    diagAreaTematica diagAreaTematica = new diagAreaTematica(null, true, "Modificación", areaTematica);
                    diagAreaTematica.setLocation(Comunes.centrarDialog(diagAreaTematica));
                    diagAreaTematica.setVisible(true);
                    cargarAreasTematicas();
                    break;
                case "DepartamentoDocente":
                    DepartamentoDocente departamentoDocente = (DepartamentoDocente) jListTitulos.getSelectedValue();
                    diagDepartamentoDocente diagDeptoDocente = new diagDepartamentoDocente(null, true, "Modificación", departamentoDocente);
                    diagDeptoDocente.setLocation(Comunes.centrarDialog(diagDeptoDocente));
                    diagDeptoDocente.setVisible(true);
                    cargarDepartamentosDocentes();
                    break;
                case "CategoriaDocente":
                    CategoriaDocente categoriaDocente = (CategoriaDocente) jListTitulos.getSelectedValue();
                    diagCategoriaDocente diagCatDocente = new diagCategoriaDocente(null, true, "Modificación", categoriaDocente);
                    diagCatDocente.setLocation(Comunes.centrarDialog(diagCatDocente));
                    diagCatDocente.setVisible(true);
                    cargarCategoriasDocentes();
                    break;
                case "DedicacionDocente":
                    DedicacionDocente dedicacionDocente = (DedicacionDocente) jListTitulos.getSelectedValue();
                    diagDedicacionDocente diagDedDocente = new diagDedicacionDocente(null, true, "Modificación", dedicacionDocente);
                    diagDedDocente.setLocation(Comunes.centrarDialog(diagDedDocente));
                    diagDedDocente.setVisible(true);
                    cargarDedicacionesDocentes();
                    break;
                case "ModoObtencionCargo":
                    ModoObtencionCargo modoObtencionCargo = (ModoObtencionCargo) jListTitulos.getSelectedValue();
                    diagModoObtencionCargo diagModoObtencionCargo = new diagModoObtencionCargo(null, true, "Modificación", modoObtencionCargo);
                    diagModoObtencionCargo.setLocation(Comunes.centrarDialog(diagModoObtencionCargo));
                    diagModoObtencionCargo.setVisible(true);
                    cargarModosdeObtenciondelCargo();
                    break;
                case "CargoConduccion":
                    CargoConduccion cargoConduccion = (CargoConduccion) jListTitulos.getSelectedValue();
                    diagCargoConduccion diagCargoConduccion = new diagCargoConduccion(null, true, "Modificación", cargoConduccion);
                    diagCargoConduccion.setLocation(Comunes.centrarDialog(diagCargoConduccion));
                    diagCargoConduccion.setVisible(true);
                    cargarCargosConducciones();
                    break;
                case "DedicacionConduccion":
                    DedicacionConduccion dedicacionConduccion = (DedicacionConduccion) jListTitulos.getSelectedValue();
                    diagDedicacionConduccion diagDedicacionConduccion = new diagDedicacionConduccion(null, true, "Modificación", dedicacionConduccion);
                    diagDedicacionConduccion.setLocation(Comunes.centrarDialog(diagDedicacionConduccion));
                    diagDedicacionConduccion.setVisible(true);
                    cargarDedicacionConduccion();
                    break;
                case "EspecialidadInvestigacion":
                    EspecialidadInvestigacion especialidadInvestigacion = (EspecialidadInvestigacion) jListTitulos.getSelectedValue();
                    diagEspecialidadInvestigacion diagEspecialidadInvestigacion = new diagEspecialidadInvestigacion(null, true, "Modificación", especialidadInvestigacion);
                    diagEspecialidadInvestigacion.setLocation(Comunes.centrarDialog(diagEspecialidadInvestigacion));
                    diagEspecialidadInvestigacion.setVisible(true);
                    cargarEspecialidadInvesticacion();
                    break;
                case "EspecialidadActividadAcademica":
                    EspecialidadActividadAcademica especialidadActividadAcademica = (EspecialidadActividadAcademica) jListTitulos.getSelectedValue();

                    diagEspecialidadActividadAcademica diagEspecialidadActividadAcademica = new diagEspecialidadActividadAcademica(null, true, "Modificación", especialidadActividadAcademica);
                    diagEspecialidadActividadAcademica.setLocation(Comunes.centrarDialog(diagEspecialidadActividadAcademica));
                    diagEspecialidadActividadAcademica.setVisible(true);
                    cargarEspecialidadesAAcademicas();
                    break;
                case "TipoDuracionAsignatura":
                    TipoDuracionAsignatura tipoDuracionAsignatura = (TipoDuracionAsignatura) jListTitulos.getSelectedValue();
                    diagTipoDuracionAsignatura diagTipoDuracionAsignatura = new diagTipoDuracionAsignatura(null, true, "Modificación", tipoDuracionAsignatura);
                    diagTipoDuracionAsignatura.setLocation(Comunes.centrarDialog(diagTipoDuracionAsignatura));
                    diagTipoDuracionAsignatura.setVisible(true);
                    cargarTipoDuracionAsignatura();
                    break;
                case "TipoAsignatura":
                    TipoAsignatura tipoAsignatura = (TipoAsignatura) jListTitulos.getSelectedValue();
                    diagTipoAsignatura diagTipoAsignatura = new diagTipoAsignatura(null, true, "Modificación", tipoAsignatura);
                    diagTipoAsignatura.setLocation(Comunes.centrarDialog(diagTipoAsignatura));
                    diagTipoAsignatura.setVisible(true);
                    cargarTipoAsignatura();
                    break;
                case "CarreraAsignatura":
                    CarreraAsignatura carreraAsignatura = (CarreraAsignatura) jListTitulos.getSelectedValue();
                    diagCarreraAsignatura asignatura = new diagCarreraAsignatura(null, true, "Modificación", carreraAsignatura);
                    asignatura.setLocation(Comunes.centrarDialog(asignatura));
                    asignatura.setVisible(true);
                    cargarCarreraAsignatura();
                    break;
                case "Localidad":
                    Localidad localidad = (Localidad) jListTitulos.getSelectedValue();
                    diagLocalidad diagLocalidad1 = new diagLocalidad(null, true, "Modificación", localidad);
                    diagLocalidad1.setLocation(Comunes.centrarDialog(diagLocalidad1));
                    diagLocalidad1.setVisible(true);
                    cargarLocalidad();
                    break;
                case "Líneas Prioritarias":
                    LineaPrioritaria lineaPrioritaria = (LineaPrioritaria) jListTitulos.getSelectedValue();
                    diagLineaPrioritariaAlta diagLineaPrioritaria = new diagLineaPrioritariaAlta(null, true, "Modificación", lineaPrioritaria);
                    diagLineaPrioritaria.setLocation(Comunes.centrarDialog(diagLineaPrioritaria));
                    diagLineaPrioritaria.setVisible(true);
                    cargarLineasPrioritarias();
                    break;
            }
        }
    }

    private void eliminar() {
        if (jListTitulos.getSelectedIndex() != -1) {
            switch (tipo) {
                case "grado":
                    TituloGrado tituloGrado = (TituloGrado) jListTitulos.getSelectedValue();
                    try {
                        for (FormacionAcademicaGrado fag : FormacionAcademicaGradoFacade.getInstance().getFormacionAcademicaGrado(tituloGrado)) {
                            FormacionAcademicaGradoFacade.getInstance().eliminar(fag.getId());
                        }
                        TituloGradoFacade.getInstance().eliminar(tituloGrado.getId());
                        cargarTitulosGrado();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El titulo de Grado tiene investigadores asociados\n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadoresMod = new diagInvestigadorPModificar(null, true, (Object) tituloGrado);
                            diagInvestigadoresMod.setLocation(Comunes.centrarDialog(diagInvestigadoresMod));
                            diagInvestigadoresMod.setVisible(true);
                            cargarTitulosGrado();
                        }
                    }
                    break;
                case "posgrado":
                    TituloPosgrado tituloPosgrado = (TituloPosgrado) jListTitulos.getSelectedValue();
                    try {
                        for (FormacionAcademicaPosgrado fap : FormacionAcademicaPosgradoFacade.getInstance().getFormacionAcademicaPosgrado(tituloPosgrado)) {
                            FormacionAcademicaPosgradoFacade.getInstance().eliminar(fap.getId());
                        }
                        TituloPosgradoFacade.getInstance().eliminar(tituloPosgrado.getId());
                        cargarTitulosPosgrado();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El titulo de Posgrado tiene investigadores asociados\n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadoresMod = new diagInvestigadorPModificar(null, true, (Object) tituloPosgrado);
                            diagInvestigadoresMod.setLocation(Comunes.centrarDialog(diagInvestigadoresMod));
                            diagInvestigadoresMod.setVisible(true);
                            cargarTitulosPosgrado();
                        }
                    }
                    break;
                case "otros":
                    TituloOtro tituloOtro = (TituloOtro) jListTitulos.getSelectedValue();
                    try {
                        for (FormacionAcademicaOtra fao : FormacionAcademicaOtraFacade.getInstance().getFormacionAcademicaOtra(tituloOtro)) {
                            FormacionAcademicaOtraFacade.getInstance().eliminar(fao.getId());
                        }
                        TituloOtroFacade.getInstance().eliminar(tituloOtro.getId());
                        cargarOtrosTitulos();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El titulo tiene investigadores asociados\n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadoresMod = new diagInvestigadorPModificar(null, true, (Object) tituloOtro);
                            diagInvestigadoresMod.setLocation(Comunes.centrarDialog(diagInvestigadoresMod));
                            diagInvestigadoresMod.setVisible(true);
                            cargarOtrosTitulos();
                        }
                    }
                    break;
                case "camposDeAplicacion":
                    if (!tieneProyectosAsociados((CampoAplicacion) jListTitulos.getSelectedValue())) {
                        CampoAplicacion campoAplicacion = (CampoAplicacion) jListTitulos.getSelectedValue();
                        CampoAplicacionFacade.getInstance().eliminar(campoAplicacion.getId());
                        cargarCamposDeAplicacion();
                    } else {
                        JOptionPane.showMessageDialog(null, "El campo de aplicación escogido tiene proyectos asociados");
                    }
                    break;
                case "universidad":
                    Universidad universidad = (Universidad) jListTitulos.getSelectedValue();
                    try {
                        UniversidadFacade.getInstance().eliminar(universidad.getId());
                        cargarUniversidades();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "La universidad tiene investigadores asociados!!");
                    }
                    break;
                case "institucion":
                    if (!tieneInvestigadoresAsociados((Institucion) jListTitulos.getSelectedValue())) {
                        Institucion institucion = (Institucion) jListTitulos.getSelectedValue();
                        InstitucionFacade.getInstance().eliminar(institucion.getId());
                        cargarInstitucion();
                    } else {
                        JOptionPane.showMessageDialog(null, "La institución escogida tiene investigadores asociados");
                    }
                    break;
                case "convenioCon":
                    if (!tieneProyectosAsociados((EntidadConvenio) jListTitulos.getSelectedValue())) {
                        EntidadConvenio entidadConvenio = (EntidadConvenio) jListTitulos.getSelectedValue();
                        EntidadConvenioFacade.getInstance().eliminar(entidadConvenio.getId());
                        cargarConvenioCon();
                    } else {
                        JOptionPane.showMessageDialog(null, "El convenio escogido tiene proyectos asociados");
                    }
                    break;
                case "especialidad":
                    if (!tieneProyectosAsociados((Especialidad) jListTitulos.getSelectedValue())) {
                        Especialidad especialidad = (Especialidad) jListTitulos.getSelectedValue();
                        EspecialidadFacade.getInstance().eliminar(especialidad.getId());
                        cargarEspecialidad();
                    } else {
                        JOptionPane.showMessageDialog(null, "La especialidad tiene proyectos asociados");
                    }
                    break;
                case "unidadInvestigacion":
                    if (!tieneProyectosAsociados((UnidadInvestigacion) jListTitulos.getSelectedValue())) {
                        UnidadInvestigacion unidadInvestigacion = (UnidadInvestigacion) jListTitulos.getSelectedValue();
                        UnidadInvestigacionFacade.getInstance().eliminar(unidadInvestigacion.getId());
                        cargarUnidadesInvestigacion();
                    } else {
                        JOptionPane.showMessageDialog(null, "El convenio escogido tiene proyectos asociados");
                    }
                    break;
                case "subdisciplina":
                    SubDisciplinaCientifica subdisciplina = (SubDisciplinaCientifica) jListTitulos.getSelectedValue();
                    if (!tieneProyectosAsociados((SubDisciplinaCientifica) jListTitulos.getSelectedValue())) {
                        SubDisciplinaCientificaFacade.getInstance().eliminar(subdisciplina.getId());
                        cargarSubdisciplina();
                    } else {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagProyectoSubDisciplinaPModificar diagProyectoMod = new diagProyectoSubDisciplinaPModificar(null, true, subdisciplina);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarSubdisciplina();
                        }
                    }
                    break;
                case "Unidad Académica":
                    UnidadAcademica unidadAcademica = (UnidadAcademica) jListTitulos.getSelectedValue();
                    try {
                        UnidadAcademicaFacade.getInstance().eliminar(unidadAcademica.getId());
                        cargarUnidadesAcademicas();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            List<Proyecto> lista = ProyectoFacade.getInstance().getproyectosXUnidadAcademica(unidadAcademica);
                            diagProyectoPModificar diagProyectoMod = new diagProyectoPModificar(null, true, lista, unidadAcademica);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarUnidadesAcademicas();
                        }
                    }
                    break;
                case "Línea de Investigacion":
                    LineaInvestigacion lineaInvestigacion = (LineaInvestigacion) jListTitulos.getSelectedValue();
                    try {
                        LineaInvestigacionFacade.getInstance().eliminar(lineaInvestigacion.getId());
                        cargarLineasInvestigacion();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {

                            diagProyectoLineaInvestigacionPModificar diagProyectoMod = new diagProyectoLineaInvestigacionPModificar(null, true, lineaInvestigacion);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarLineasInvestigacion();
                        }

                    }
                    break;
                case "Tipo Actividades":
                    TipoProyecto tipoProyecto = (TipoProyecto) jListTitulos.getSelectedValue();
                    try {
                        TipoProyectoFacade.getInstance().eliminar(tipoProyecto.getId());
                        cargarTipoActividades();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            List<Proyecto> lista = ProyectoFacade.getInstance().getproyectosXTipoProyecto(tipoProyecto);
                            diagProyectoPModificar diagProyectoMod = new diagProyectoPModificar(null, true, lista, null);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarTipoActividades();
                        }

                    }
                    break;
                case "Entidad Evaluadora":
                    EntidadEvaluadora entidadEvaluadora = (EntidadEvaluadora) jListTitulos.getSelectedValue();
                    try {
                        EntidadEvaluadoraFacade.getInstance().eliminar(entidadEvaluadora.getId());
                        cargarEntidadesEvaluadoras();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            List<Proyecto> lista = ProyectoFacade.getInstance().getproyectosXEntidadEvaluadora(entidadEvaluadora);
                            diagProyectoVinculacionPModificar diagProyectoMod = new diagProyectoVinculacionPModificar(null, true, lista, entidadEvaluadora);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarEntidadesEvaluadoras();
                        }

                    }
                    break;
                case "Unidad Ejecutora":
                    UnidadEjecutora unidadEjecutora = (UnidadEjecutora) jListTitulos.getSelectedValue();
                    try {
                        UnidadEjecutoraFacade.getInstance().eliminar(unidadEjecutora.getId());
                        cargarUnidadesEjecutoras();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            List<Proyecto> lista = ProyectoFacade.getInstance().getproyectosXUnidadEjecutora(unidadEjecutora);
                            diagProyectoUnidadEjecutoraPModificar diagProyectoMod = new diagProyectoUnidadEjecutoraPModificar(null, true, unidadEjecutora);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarUnidadesEjecutoras();
                        }

                    }
                    break;
                case "Programa":
                    Programa programa = (Programa) jListTitulos.getSelectedValue();
                    try {
                        ProgramaFacade.getInstance().eliminar(programa.getId());
                        cargarProgramas();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            List<Proyecto> lista = ProyectoFacade.getInstance().getproyectosXPrograma(programa);
                            diagProyectoProgramaPModificar diagProyectoMod = new diagProyectoProgramaPModificar(null, true, programa);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarProgramas();
                        }
                    }
                    break;
                case "Objetivo Socioeconómico":
                    ObjetivoSocioeconomico objetivoSocioeconomico = (ObjetivoSocioeconomico) jListTitulos.getSelectedValue();
                    try {
                        ObjetivoSocioeconomicoFacade.getInstance().eliminar(objetivoSocioeconomico.getId());
                        cargarObjetivosSocioeconomicos();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagProyectoObjetivoSEPModificar diagProyectoMod = new diagProyectoObjetivoSEPModificar(null, true, objetivoSocioeconomico);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarObjetivosSocioeconomicos();
                        }

                    }
                    break;
                case "Disciplina":
                    DisciplinaCientifica disciplina = (DisciplinaCientifica) jListTitulos.getSelectedValue();
                    if (!tieneSubDisciplinasAsociadas(disciplina)) {
                        DisciplinaCientificaFacade.getInstance().eliminar(disciplina.getId());
                        cargarDisciplinasCientificas();
                    } else {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene subdisciplinas asociadas \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagProyectoDisciplinaPModificar diagProyectoMod = new diagProyectoDisciplinaPModificar(null, true, disciplina);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarDisciplinasCientificas();
                        }

                    }
                    break;
                case "Area Temática":
                    AreaTematica areaTematica = (AreaTematica) jListTitulos.getSelectedValue();
                    if (!tieneDisciplinasAsociadas(areaTematica)) {
                        AreaTematicaFacade.getInstance().eliminar(areaTematica.getId());
                        cargarAreasTematicas();
                    } else {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene proyectos asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagProyectoAreaTematicaPModificar diagProyectoMod = new diagProyectoAreaTematicaPModificar(null, true, areaTematica);
                            diagProyectoMod.setLocation(Comunes.centrarDialog(diagProyectoMod));
                            diagProyectoMod.setVisible(true);
                            cargarAreasTematicas();
                        }

                    }
                    break;
                case "DepartamentoDocente":
                    DepartamentoDocente departamentoDocente = (DepartamentoDocente) jListTitulos.getSelectedValue();
                    try {

                        DepartamentoDocenteFacade.getInstance().eliminar(departamentoDocente.getId());
                        cargarDepartamentosDocentes();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) departamentoDocente);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarDepartamentosDocentes();
                        }
                    }
                    break;
                case "CategoriaDocente":
                    CategoriaDocente categoriaDocente = (CategoriaDocente) jListTitulos.getSelectedValue();
                    try {

                        CategoriaDocenteFacade.getInstance().eliminar(categoriaDocente.getId());
                        cargarCategoriasDocentes();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) categoriaDocente);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarCategoriasDocentes();
                        }
                    }
                    break;
                case "DedicacionDocente":
                    DedicacionDocente dedicacionDocente = (DedicacionDocente) jListTitulos.getSelectedValue();
                    try {

                        DedicacionDocenteFacade.getInstance().eliminar(dedicacionDocente.getId());
                        cargarDedicacionesDocentes();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) dedicacionDocente);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarDedicacionesDocentes();
                        }
                    }
                    break;
                case "ModoObtencionCargo":
                    ModoObtencionCargo modoObtencionCargo = (ModoObtencionCargo) jListTitulos.getSelectedValue();
                    try {

                        ModoObtencionCargoFacade.getInstance().eliminar(modoObtencionCargo.getId());
                        cargarModosdeObtenciondelCargo();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) modoObtencionCargo);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarModosdeObtenciondelCargo();
                        }
                    }
                    break;
                case "CargoConduccion":
                    CargoConduccion cargoConduccion = (CargoConduccion) jListTitulos.getSelectedValue();
                    try {

                        CargoConduccionFacade.getInstance().eliminar(cargoConduccion.getId());
                        cargarCargosConducciones();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) cargoConduccion);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarCargosConducciones();
                        }
                    }
                    break;
                case "DedicacionConduccion":
                    DedicacionConduccion dedicacionConduccion = (DedicacionConduccion) jListTitulos.getSelectedValue();
                    try {

                        DedicacionConduccionFacade.getInstance().eliminar(dedicacionConduccion.getId());
                        cargarDedicacionConduccion();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) dedicacionConduccion);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarDedicacionConduccion();
                        }
                    }
                    break;
                case "EspecialidadInvestigacion":
                    EspecialidadInvestigacion especialidadInvestigacion = (EspecialidadInvestigacion) jListTitulos.getSelectedValue();
                    try {

                        EspecialidadInvestigacionFacade.getInstance().eliminar(especialidadInvestigacion.getId());
                        cargarEspecialidadInvesticacion();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) especialidadInvestigacion);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarEspecialidadInvesticacion();
                        }
                    }
                    break;
                case "EspecialidadActividadAcademica":
                    EspecialidadActividadAcademica especialidadActividadAcademica = (EspecialidadActividadAcademica) jListTitulos.getSelectedValue();
                    try {

                        EspecialidadActividadAcademicaFacade.getInstance().eliminar(especialidadActividadAcademica.getId());
                        cargarEspecialidadesAAcademicas();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) especialidadActividadAcademica);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarEspecialidadesAAcademicas();
                        }
                    }
                    break;
                case "TipoDuracionAsignatura":
                    TipoDuracionAsignatura tipoDuracionAsignatura = (TipoDuracionAsignatura) jListTitulos.getSelectedValue();
                    try {

                        TipoDuracionAsignaturaFacade.getInstance().eliminar(tipoDuracionAsignatura.getId());
                        cargarTipoDuracionAsignatura();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) tipoDuracionAsignatura);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarTipoDuracionAsignatura();
                        }
                    }
                    break;
                case "TipoAsignatura":
                    TipoAsignatura tipoAsignatura = (TipoAsignatura) jListTitulos.getSelectedValue();
                    try {

                        TipoAsignaturaFacade.getInstance().eliminar(tipoAsignatura.getId());
                        cargarTipoAsignatura();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) tipoAsignatura);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarTipoAsignatura();
                        }
                    }
                    break;
                case "CarreraAsignatura":
                    CarreraAsignatura carreraAsignatura = (CarreraAsignatura) jListTitulos.getSelectedValue();
                    try {

                        CarreraAsignaturaFacade.getInstance().eliminar(carreraAsignatura.getId());
                        cargarTipoAsignatura();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) carreraAsignatura);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarCarreraAsignatura();
                        }
                    }
                    break;
                case "Localidad":
                    Localidad localidad = (Localidad) jListTitulos.getSelectedValue();
                    try {

                        LocalidadFacade.getInstance().eliminar(localidad.getId());
                        cargarLocalidad();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) localidad);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarCarreraAsignatura();
                        }
                    }
                    break;
                case "Líneas Prioritarias":
                    LineaPrioritaria lineaPrioritaria = (LineaPrioritaria) jListTitulos.getSelectedValue();
                    try {

                        new LineaPrioritariaFacade().eliminar(lineaPrioritaria.getId());
                        cargarLineasPrioritarias();
                    } catch (Exception ex) {
                        int resolver = JOptionPane.showConfirmDialog(null, "El elemento escogido tiene investigadores asociados \n"
                                + "desea resolver el conflicto?", "Error", JOptionPane.YES_NO_OPTION);
                        if (resolver == JOptionPane.YES_OPTION) {
                            diagInvestigadorPModificar diagInvestigadorMod = new diagInvestigadorPModificar(null, true, (Object) lineaPrioritaria);
                            diagInvestigadorMod.setLocation(Comunes.centrarDialog(diagInvestigadorMod));
                            diagInvestigadorMod.setVisible(true);
                            cargarLineasPrioritarias();
                        }
                    }
                    break;

            }

        }
    }

    private void filtrar() {
        if (!"".equals(tfFiltro.getText())) {
            switch (tipo) {
                case "grado":
                    Comunes.cargarJList(jListTitulos, TituloGradoFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "posgrado":
                    Comunes.cargarJList(jListTitulos, TituloPosgradoFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "otros":
                    Comunes.cargarJList(jListTitulos, TituloOtroFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "camposDeAplicacion":
                    Comunes.cargarJList(jListTitulos, CampoAplicacionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "universidad":
                    Comunes.cargarJList(jListTitulos, UniversidadFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "institucion":
                    Comunes.cargarJList(jListTitulos, InstitucionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "convenioCon":
                    Comunes.cargarJList(jListTitulos, EntidadConvenioFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "especialidad":
                    Comunes.cargarJList(jListTitulos, EspecialidadFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "unidadInvestigacion":
                    Comunes.cargarJList(jListTitulos, UnidadInvestigacionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Unidad Académica":
                    Comunes.cargarJList(jListTitulos, UnidadAcademicaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Línea de Investigacion":
                    Comunes.cargarJList(jListTitulos, LineaInvestigacionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Unidad Ejecutora":
                    Comunes.cargarJList(jListTitulos, UnidadEjecutoraFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Tipo Actividades":
                    Comunes.cargarJList(jListTitulos, TipoProyectoFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Entidad Evaluadora":
                    Comunes.cargarJList(jListTitulos, EntidadEvaluadoraFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Programa":
                    Comunes.cargarJList(jListTitulos, ProgramaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Objetivo Socioeconómico":
                    Comunes.cargarJList(jListTitulos, ObjetivoSocioeconomicoFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "subdisciplina":
                    Comunes.cargarJList(jListTitulos, SubDisciplinaCientificaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "Disciplina":
                    Comunes.cargarJList(jListTitulos, DisciplinaCientificaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "DepartamentoDocente":
                    Comunes.cargarJList(jListTitulos, DepartamentoDocenteFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "CategoriaDocente":
                    Comunes.cargarJList(jListTitulos, CategoriaDocenteFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "DedicacionDocente":
                    Comunes.cargarJList(jListTitulos, DedicacionDocenteFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "ModoObtencionCargo":
                    Comunes.cargarJList(jListTitulos, ModoObtencionCargoFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "CargoConduccion":
                    Comunes.cargarJList(jListTitulos, CargoConduccionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "DedicacionConduccion":
                    Comunes.cargarJList(jListTitulos, DedicacionConduccionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "EspecialidadInvestigacion":
                    Comunes.cargarJList(jListTitulos, EspecialidadInvestigacionFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "EspecialidadActividadAcademica":
                    Comunes.cargarJList(jListTitulos, EspecialidadActividadAcademicaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "TipoDuracionAsignatura":
                    Comunes.cargarJList(jListTitulos, TipoDuracionAsignaturaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "TipoAsignatura":
                    Comunes.cargarJList(jListTitulos, TipoAsignaturaFacade.getInstance().filtrar(tfFiltro.getText()));
                    break;
                case "CarreraAsignatura":
                    Comunes.cargarJList(jListTitulos, CarreraAsignaturaFacade.getInstance().listarCarreraAsignatura(tfFiltro.getText()));
                    break;
                case "Localidad":
                    Comunes.cargarJList(jListTitulos, LocalidadFacade.getInstance().listarLocalidadesCatamarca(tfFiltro.getText()));
                    break;
                case "Líneas Prioritarias":
                    Comunes.cargarJList(jListTitulos, new LineaPrioritariaFacade().getLineaPrioritaria(tfFiltro.getText()));
                    break;
            }

        }

    }

    private void cargarTitulosGrado() {
        listaTituloGrado = TituloGradoFacade.getInstance().getTodosTituloGrado();
        Comunes.cargarJList(jListTitulos, listaTituloGrado);
        tipo = "grado";
    }

    private void cargarTitulosPosgrado() {
        listaTituloPosgrado = TituloPosgradoFacade.getInstance().getTodosTituloPosgrado();
        Comunes.cargarJList(jListTitulos, listaTituloPosgrado);
        tipo = "posgrado";
    }

    private void cargarOtrosTitulos() {
        listaTituloOtro = TituloOtroFacade.getInstance().getTodosTituloOtro();
        Comunes.cargarJList(jListTitulos, listaTituloOtro);
        tipo = "otros";
    }

    private void cargarCamposDeAplicacion() {
        listaCampoAplicacion = CampoAplicacionFacade.getInstance().getTodosCampoAplicacion();
        Comunes.cargarJList(jListTitulos, listaCampoAplicacion);
        tipo = "camposDeAplicacion";
    }

    private void cargarUniversidades() {
        listaUniversidades = UniversidadFacade.getInstance().listarTodosUniversidadOrdenados();
        Comunes.cargarJList(jListTitulos, listaUniversidades);
        tipo = "universidad";
    }

    private void cargarInstitucion() {
        listaInstituciones = InstitucionFacade.getInstance().listarTodosInstitucionOrdenados();
        Comunes.cargarJList(jListTitulos, listaInstituciones);
        tipo = "institucion";
    }

    private void cargarConvenioCon() {
        listaConvenios = EntidadConvenioFacade.getInstance().getTodosEntidadConvenio();
        Comunes.cargarJList(jListTitulos, listaConvenios);
        tipo = "convenioCon";
    }

    private void cargarUnidadesInvestigacion() {
        listaUnidadesInvestigacion = UnidadInvestigacionFacade.getInstance().getTodosUnidadInvestigacion();
        Comunes.cargarJList(jListTitulos, listaUnidadesInvestigacion);
        tipo = "unidadInvestigacion";
    }

    private void cargarEspecialidad() {
        listaEspecialidades = EspecialidadFacade.getInstance().getTodosEspecialidad();
        Comunes.cargarJList(jListTitulos, listaEspecialidades);
        tipo = "especialidad";
    }

    private void cargarSubdisciplina() {
        listaSubdisciplina = SubDisciplinaCientificaFacade.getInstance().getTodosSubDisciplinaCientifica();
        Comunes.cargarJList(jListTitulos, listaSubdisciplina);
        tipo = "subdisciplina";
    }

    private boolean tieneProyectosAsociados(CampoAplicacion campoAplicacion) {

        List<Proyecto> listaProyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : listaProyectos) {
            List<CampoAplicacion> listaCamposAplicacion = proyecto.getCamposAplicacion();
            for (CampoAplicacion campoAplicacion1 : listaCamposAplicacion) {
                if (campoAplicacion1.equals(campoAplicacion)) {
                    return true;
                }
            }

        }
        return false;

    }

    private boolean tieneProyectosAsociados(EntidadConvenio entidadConvenio) {

        List<Proyecto> listaProyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : listaProyectos) {
            List<EntidadConvenio> listaConvenios = proyecto.getConveniosCon();
            for (EntidadConvenio entidadConvenio1 : listaConvenios) {
                if (entidadConvenio1.equals(entidadConvenio)) {
                    return true;
                }
            }

        }
        return false;

    }

    private boolean tieneProyectosAsociados(Especialidad especialidad) {

        List<Proyecto> listaProyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : listaProyectos) {

            List<Especialidad> listaEspecialidades = proyecto.getEspecialidades();
            for (Especialidad especialidad1 : listaEspecialidades) {
                if (especialidad1.equals(especialidad)) {
                    return true;
                }
            }

        }
        return false;

    }

    private boolean tieneProyectosAsociados(UnidadInvestigacion unidadInvestigacion) {

        List<Proyecto> listaProyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : listaProyectos) {

            List<UnidadInvestigacion> listaUnidadInvestigaciones = proyecto.getUnidadesInvestigacion();
            for (UnidadInvestigacion unidadInvestigacion1 : listaUnidadInvestigaciones) {
                if (unidadInvestigacion1.equals(unidadInvestigacion)) {
                    return true;
                }
            }

        }
        return false;

    }

    private boolean tieneProyectosAsociados(SubDisciplinaCientifica subDisciplinaCientifica) {

        List<Proyecto> listaProyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : listaProyectos) {

            List<SubDisciplinaCientifica> listaSubDisciplinaCientificas = proyecto.getSubDisciplinasCientificas();
            for (SubDisciplinaCientifica subDisciplinaCientifica1 : listaSubDisciplinaCientificas) {
                if (subDisciplinaCientifica1.equals(subDisciplinaCientifica)) {
                    return true;
                }
            }

        }
        return false;

    }

    private boolean tieneSubDisciplinasAsociadas(DisciplinaCientifica disciplina) {
        if (!SubDisciplinaCientificaFacade.getInstance().getSubDisciplinas(disciplina).isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    private Boolean tieneInvestigadoresAsociados(TituloGrado tituloGrado) {
        List<Investigador> listaInvestigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
        for (Investigador investigador : listaInvestigadores) {
            List<FormacionAcademicaGrado> listaFormacionAcademicaGrados = investigador.getFormacionesAcademicasGrado();
            for (FormacionAcademicaGrado formacionAcademicaGrado : listaFormacionAcademicaGrados) {
                if (formacionAcademicaGrado.getTituloGrado().equals(tituloGrado)) {
                    return true;
                }
            }

        }
        return false;
    }

    private Boolean tieneInvestigadoresAsociados(Universidad universidad) {
        List<Investigador> listaInvestigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
        for (Investigador investigador : listaInvestigadores) {
            List<FormacionAcademicaGrado> listaFormacionAcademicaGrados = investigador.getFormacionesAcademicasGrado();
            List<FormacionAcademicaPosgrado> listaFormacionAcademicaPosgrados = investigador.getFormacionesAcademicasPosgrado();
            for (FormacionAcademicaGrado formacionAcademicaGrado : listaFormacionAcademicaGrados) {
                if (formacionAcademicaGrado.getUniversidad().equals(universidad)) {
                    return true;
                }
            }
            for (FormacionAcademicaPosgrado formacionAcademicaPosgrado : listaFormacionAcademicaPosgrados) {
                if (formacionAcademicaPosgrado.getUniversidad().equals(universidad)) {
                    return true;
                }
            }

        }
        return false;
    }

    private Boolean tieneInvestigadoresAsociados(Institucion institucion) {
        try {
            List<Investigador> listaInvestigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
            for (Investigador investigador : listaInvestigadores) {
                List<FormacionAcademicaOtra> listaFormacionAcademicaOtra = investigador.getFormacionesAcademicasOtras();
                for (FormacionAcademicaOtra formacionAcademicaOtra : listaFormacionAcademicaOtra) {
                    if (formacionAcademicaOtra.getInstitucion().equals(institucion)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (java.lang.NullPointerException ex) {
            return false;
        }
    }

    private Boolean tieneInvestigadoresAsociados(TituloPosgrado tituloPosgrado) {
        List<Investigador> listaInvestigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
        for (Investigador investigador : listaInvestigadores) {
            List<FormacionAcademicaPosgrado> listaFormacionAcademicaPosgrados = investigador.getFormacionesAcademicasPosgrado();
            for (FormacionAcademicaPosgrado formacionAcademicaPosgrado : listaFormacionAcademicaPosgrados) {
                if (formacionAcademicaPosgrado.getTituloPosgrado().equals(tituloPosgrado)) {
                    return true;
                }
            }

        }
        return false;
    }

    private Boolean tieneInvestigadoresAsociados(TituloOtro tituloOtro) {
        List<Investigador> listaInvestigadores = InvestigadorFacade.getInstance().getTodosInvestigador();
        for (Investigador investigador : listaInvestigadores) {
            List<FormacionAcademicaOtra> listaFormacionAcademicaOtros = investigador.getFormacionesAcademicasOtras();
            for (FormacionAcademicaOtra formacionAcademicaOtro : listaFormacionAcademicaOtros) {
                if (formacionAcademicaOtro.getTituloOtro().equals(tituloOtro)) {
                    return true;
                }
            }

        }
        return false;
    }

    private void buscarEntidad() {
        String entidad = cboEntidad.getSelectedItem().toString();

        switch (entidad) {
            case "Unidad Académica":
                cargarUnidadesAcademicas();
                break;
            case "Línea de Investigacion":
                cargarLineasInvestigacion();
                break;
            case "Unidad Ejecutora":
                cargarUnidadesEjecutoras();
                break;
            case "Tipo Actividades":
                cargarTipoActividades();
                break;
            case "Entidad Evaluadora":
                cargarEntidadesEvaluadoras();
                break;
            case "Programa":
                cargarProgramas();
                break;
            case "Objetivo Socioeconómico":
                cargarObjetivosSocioeconomicos();
                break;
            case "Subdisciplinas Científicas":
                cargarSubdisciplina();
                break;
            case "Disciplinas Científicas":
                cargarDisciplinasCientificas();
                break;
            case "Areas Temáticas":
                cargarAreasTematicas();
                break;
            case "Departamento Docente":
                cargarDepartamentosDocentes();
                break;
            case "Categoría Docente":
                cargarCategoriasDocentes();
                break;
            case "Dedicación Docente":
                cargarDedicacionesDocentes();
                break;
            case "Modo de Obtencion del Cargo":
                cargarModosdeObtenciondelCargo();
                break;
            case "Cargo Conduccion":
                cargarCargosConducciones();
                break;
            case "Dedicacion Conduccion":
                cargarDedicacionConduccion();
                break;
            case "Especialidad Investigacion":
                cargarEspecialidadInvesticacion();
                break;
            case "Especialidad Actividad Académica":
                cargarEspecialidadesAAcademicas();
                break;
            case "Tipo de duración de Asignatura":
                cargarTipoDuracionAsignatura();
                break;
            case "Tipo Asignatura":
                cargarTipoAsignatura();
                break;
            case "Localidad":
                cargarLocalidad();
                break;
            case "Línea Prioritaria":
                cargarLineasPrioritarias();
                break;
        }

    }

    private void cargarUnidadesAcademicas() {
        Comunes.cargarJList(jListTitulos, UnidadAcademicaFacade.getInstance().getTodasUnidadAcademica());
        tipo = "Unidad Académica";
    }

    private void cargarLineasInvestigacion() {
        Comunes.cargarJList(jListTitulos, LineaInvestigacionFacade.getInstance().getTodosLineaInvestigacion());
        tipo = "Línea de Investigacion";
    }

    private void cargarLineasPrioritarias() {
        Comunes.cargarJList(jListTitulos, new LineaPrioritariaFacade().getTodosLineaPrioritaria());
        tipo = "Líneas Prioritarias";
    }

    private void cargarUnidadesEjecutoras() {
        Comunes.cargarJList(jListTitulos, UnidadEjecutoraFacade.getInstance().getTodosUnidadEjecutora());
        tipo = "Unidad Ejecutora";
    }

    private void cargarTipoActividades() {
        Comunes.cargarJList(jListTitulos, TipoProyectoFacade.getInstance().getTodosTipoProyecto());
        tipo = "Tipo Actividades";
    }

    private void cargarEntidadesEvaluadoras() {
        Comunes.cargarJList(jListTitulos, EntidadEvaluadoraFacade.getInstance().getTodosEntidadEvaluadora());
        tipo = "Entidad Evaluadora";
    }

    private void cargarProgramas() {
        Comunes.cargarJList(jListTitulos, ProgramaFacade.getInstance().getTodosPrograma());
        tipo = "Programa";
    }

    private void cargarObjetivosSocioeconomicos() {
        Comunes.cargarJList(jListTitulos, ObjetivoSocioeconomicoFacade.getInstance().getTodas());
        tipo = "Objetivo Socioeconómico";
    }

    private void cargarDisciplinasCientificas() {
        Comunes.cargarJList(jListTitulos, DisciplinaCientificaFacade.getInstance().getTodas());
        tipo = "Disciplina";
    }

    private void cargarAreasTematicas() {
        Comunes.cargarJList(jListTitulos, AreaTematicaFacade.getInstance().getTodas());
        tipo = "Area Temática";
    }

    private void cargarDepartamentosDocentes() {
        Comunes.cargarJList(jListTitulos, DepartamentoDocenteFacade.getInstance().listarTodosDepartamentoDocenteOrdenados());
        tipo = "DepartamentoDocente";
    }

    private void cargarCategoriasDocentes() {
        Comunes.cargarJList(jListTitulos, CategoriaDocenteFacade.getInstance().listarTodosCategoriaDocenteOrdenados());
        tipo = "CategoriaDocente";
    }

    private void cargarDedicacionesDocentes() {
        Comunes.cargarJList(jListTitulos, DedicacionDocenteFacade.getInstance().listarTodosDedicacionDocenteOrdenados());
        tipo = "DedicacionDocente";
    }

    private void cargarModosdeObtenciondelCargo() {
        Comunes.cargarJList(jListTitulos, ModoObtencionCargoFacade.getInstance().listarTodosModoObtencionCargoOrdenados());
        tipo = "ModoObtencionCargo";
    }

    private void cargarCargosConducciones() {
        Comunes.cargarJList(jListTitulos, CargoConduccionFacade.getInstance().listarTodosCargoConduccionOrdenados());
        tipo = "CargoConduccion";
    }

    private void cargarDedicacionConduccion() {
        Comunes.cargarJList(jListTitulos, DedicacionConduccionFacade.getInstance().listarTodosDedicacionConduccionOrdenados());
        tipo = "DedicacionConduccion";
    }

    private void cargarEspecialidadInvesticacion() {
        Comunes.cargarJList(jListTitulos, EspecialidadInvestigacionFacade.getInstance().getTodosEspecialidadInvestigacion());
        tipo = "EspecialidadInvestigacion";
    }

    private void cargarEspecialidadesAAcademicas() {
        Comunes.cargarJList(jListTitulos, EspecialidadActividadAcademicaFacade.getInstance().getTodosEspecialidadActividadAcademica());
        tipo = "EspecialidadActividadAcademica";
    }

    private void cargarTipoDuracionAsignatura() {
        Comunes.cargarJList(jListTitulos, TipoDuracionAsignaturaFacade.getInstance().listarTodosTipoDuracionAsignaturaOrdenados());
        tipo = "TipoDuracionAsignatura";
    }

    private void cargarTipoAsignatura() {
        Comunes.cargarJList(jListTitulos, TipoAsignaturaFacade.getInstance().listarTodosTipoAsignaturaOrdenados());
        tipo = "TipoAsignatura";
    }

    private void cargarCarreraAsignatura() {
        Comunes.cargarJList(jListTitulos, CarreraAsignaturaFacade.getInstance().listarTodosCarreraAsignaturaOrdenados());
        tipo = "CarreraAsignatura";
    }

    private void cargarLocalidad() {
        Comunes.cargarJList(jListTitulos, LocalidadFacade.getInstance().listarLocalidadesCatamarca());
        tipo = "Localidad";
    }

    private void Agregar() {
        if (tipo != null) {
            switch (tipo) {
                case "Unidad Académica":
                    agregarUnidadAcademica();
                    break;
                case "Línea de Investigacion":
                    agregarLineaDeInvestigacion();
                    break;
                case "Unidad Ejecutora":
                    agregarUnidadEjecutora();
                    break;
                case "Tipo Actividades":
                    agregarTiposActividades();
                    break;
                case "Entidad Evaluadora":
                    agregarEntidadEvaluadora();
                    break;
                case "Programa":
                    agregarPrograma();
                    break;
                case "Objetivo Socioeconómico":
                    agregarObjetivoSocieconomico();
                    break;
                case "subdisciplina":
                    agregarSubdisciplina();
                    break;
                case "Disciplina":
                    agregarDisciplina();
                    break;
                case "Area Temática":
                    agregarAreaTematica();
                    break;
                case "grado":
                    agregarTituloGrado();
                    break;
                case "posgrado":
                    agregarTituloPosgrado();
                    break;
                case "otros":
                    agregarTituloGrado();
                    break;
                case "universidad":
                    agregarUniversidad();
                    break;
                case "DepartamentoDocente":
                    agregarDepartamentoDocente();
                    break;
                case "CategoriaDocente":
                    agregarCategoriaDocente();
                    break;
                case "DedicacionDocente":
                    agregarDedicacionDocente();
                    break;
                case "ModoObtencionCargo":
                    agregarModoObtencionCargo();
                    break;
                case "CargoConduccion":
                    agregarCargoConduccion();
                    break;
                case "DedicacionConduccion":
                    agregarDedicacionConduccion();
                    break;
                case "EspecialidadInvestigacion":
                    agregarEspecialidadInvestigacion();
                    break;
                case "EspecialidadActividadAcademica":
                    agregarEspecialidadActividadAcademica();
                    break;
                case "TipoDuracionAsignatura":
                    agregarTipoDuracionAsignatura();
                    break;
                case "TipoAsignatura":
                    agregarTipoAsignatura();
                    break;
                case "CarreraAsignatura":
                    agregarCarreraAsignatura();
                    break;
                case "Localidad":
                    agregarLocalidad();
                    break;
                case "Líneas Prioritarias":
                    agregarLineaPrioritaria();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe elegir primero el tipo de dato que quiere agregar!");
        }

    }

    private void agregarUnidadAcademica() {
        diagUnidadAcademica nuevaUnidadAcademica = new diagUnidadAcademica(null, true, "Alta");
        nuevaUnidadAcademica.setLocation(Comunes.centrarDialog(nuevaUnidadAcademica));
        nuevaUnidadAcademica.setVisible(true);
        cargarUnidadesAcademicas();
    }

    private void agregarLineaDeInvestigacion() {
        diagLineaInvestigacion nuevaLineaInv = new diagLineaInvestigacion(null, true, "Alta");
        nuevaLineaInv.setLocation(Comunes.centrarDialog(nuevaLineaInv));
        nuevaLineaInv.setVisible(true);
        cargarLineasInvestigacion();
    }

    private void agregarLineaPrioritaria() {
        diagLineaPrioritariaAlta nuevaLineaInv = new diagLineaPrioritariaAlta(null, true, "Alta");
        nuevaLineaInv.setLocation(Comunes.centrarDialog(nuevaLineaInv));
        nuevaLineaInv.setVisible(true);
        cargarLineasPrioritarias();
    }

    private void agregarUnidadEjecutora() {
        diagUnidadEjecutora nuevaUnidadEjecutora = new diagUnidadEjecutora(null, true, "Alta");
        nuevaUnidadEjecutora.setLocation(Comunes.centrarDialog(nuevaUnidadEjecutora));
        nuevaUnidadEjecutora.setVisible(true);
        cargarUnidadesEjecutoras();
    }

    private void agregarTiposActividades() {
        diagTipoProyecto nuevoTipo = new diagTipoProyecto(null, true, "Alta");
        nuevoTipo.setLocation(Comunes.centrarDialog(nuevoTipo));
        nuevoTipo.setVisible(true);
        cargarTipoActividades();
    }

    private void agregarEntidadEvaluadora() {
        diagEntidadEvaluadora nuevaEntidad = new diagEntidadEvaluadora(null, true, "Alta");
        nuevaEntidad.setLocation(Comunes.centrarDialog(nuevaEntidad));
        nuevaEntidad.setVisible(true);
        cargarEntidadesEvaluadoras();
    }

    private void agregarPrograma() {
        diagPrograma nuevoPrograma = new diagPrograma(null, true, "Alta");
        nuevoPrograma.setLocation(Comunes.centrarDialog(nuevoPrograma));
        nuevoPrograma.setVisible(true);
        cargarProgramas();
    }

    private void agregarObjetivoSocieconomico() {
        diagObjetivoSocioeconomico nuevoObjetivoSE = new diagObjetivoSocioeconomico(null, true, "Alta");
        nuevoObjetivoSE.setLocation(Comunes.centrarDialog(nuevoObjetivoSE));
        nuevoObjetivoSE.setVisible(true);
        cargarObjetivosSocioeconomicos();
    }

    private void agregarSubdisciplina() {
        diagSubDisciplinaCientifica nuevaSubdisciplina = new diagSubDisciplinaCientifica(null, true, "Alta");
        nuevaSubdisciplina.setLocation(Comunes.centrarDialog(nuevaSubdisciplina));
        nuevaSubdisciplina.setVisible(true);
        cargarSubdisciplina();
    }

    private void agregarDisciplina() {
        diagDisciplinaCientifica nuevaDisciplina = new diagDisciplinaCientifica(null, true, "Alta");
        nuevaDisciplina.setLocation(Comunes.centrarDialog(nuevaDisciplina));
        nuevaDisciplina.setVisible(true);
        cargarDisciplinasCientificas();
    }

    private void agregarAreaTematica() {
        diagAreaTematica nuevaAreaTematica = new diagAreaTematica(null, true, "Alta");
        nuevaAreaTematica.setLocation(Comunes.centrarDialog(nuevaAreaTematica));
        nuevaAreaTematica.setVisible(true);
        cargarAreasTematicas();
    }

    private boolean tieneDisciplinasAsociadas(AreaTematica areaTematica) {
        if (!DisciplinaCientificaFacade.getInstance().getDisciplinaCientifica(areaTematica).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void agregarTituloGrado() {
        diagTituloGrado nuevoTituloGrado = new diagTituloGrado(null, true, "Alta");
        nuevoTituloGrado.setLocation(Comunes.centrarDialog(nuevoTituloGrado));
        nuevoTituloGrado.setVisible(true);
        cargarTitulosGrado();
    }

    private void agregarTituloPosgrado() {
        diagTituloPosgrado nuevoTituloPosgrado = new diagTituloPosgrado(null, true, "Alta");
        nuevoTituloPosgrado.setLocation(Comunes.centrarDialog(nuevoTituloPosgrado));
        nuevoTituloPosgrado.setVisible(true);
        cargarTitulosPosgrado();
    }

    private void agregarUniversidad() {
        diagUniversidad nuevaUniversidad = new diagUniversidad(null, true, "Alta");
        nuevaUniversidad.setLocation(Comunes.centrarDialog(nuevaUniversidad));
        nuevaUniversidad.setVisible(true);
        cargarUniversidades();
    }

    private void agregarDepartamentoDocente() {
        diagDepartamentoDocente nuevoDepartamentoDocente = new diagDepartamentoDocente(null, true, "Alta");
        nuevoDepartamentoDocente.setLocation(Comunes.centrarDialog(nuevoDepartamentoDocente));
        nuevoDepartamentoDocente.setVisible(true);
        cargarDepartamentosDocentes();
    }

    private void agregarCategoriaDocente() {
        diagCategoriaDocente nuevaCatDoc = new diagCategoriaDocente(null, true, "Alta");
        nuevaCatDoc.setLocation(Comunes.centrarDialog(nuevaCatDoc));
        nuevaCatDoc.setVisible(true);
        cargarCategoriasDocentes();
    }

    private void agregarDedicacionDocente() {
        diagDedicacionDocente dedicacionDocente = new diagDedicacionDocente(null, true, "Alta");
        dedicacionDocente.setLocation(Comunes.centrarDialog(dedicacionDocente));
        dedicacionDocente.setVisible(true);
        cargarDedicacionesDocentes();
    }

    private void agregarModoObtencionCargo() {
        diagModoObtencionCargo nuevoMOC = new diagModoObtencionCargo(null, true, "Alta");
        nuevoMOC.setLocation(Comunes.centrarDialog(nuevoMOC));
        nuevoMOC.setVisible(true);
        cargarModosdeObtenciondelCargo();
    }

    private void agregarCargoConduccion() {
        diagCargoConduccion nuevoCC = new diagCargoConduccion(null, true);
        nuevoCC.setLocation(Comunes.centrarDialog(nuevoCC));
        nuevoCC.setVisible(true);
        cargarCargosConducciones();
    }

    private void agregarDedicacionConduccion() {
        diagDedicacionConduccion nuevaDC = new diagDedicacionConduccion(null, true, "Alta");
        nuevaDC.setLocation(Comunes.centrarDialog(nuevaDC));
        nuevaDC.setVisible(true);
        cargarDedicacionConduccion();
    }

    private void agregarEspecialidadInvestigacion() {
        diagEspecialidadInvestigacion nuevaEI = new diagEspecialidadInvestigacion(null, true, "Alta");
        nuevaEI.setLocation(Comunes.centrarDialog(nuevaEI));
        nuevaEI.setVisible(true);
        cargarEspecialidadInvesticacion();
    }

    private void agregarEspecialidadActividadAcademica() {
        diagEspecialidadActividadAcademica nuevaEAA = new diagEspecialidadActividadAcademica(null, true, "Alta");
        nuevaEAA.setLocation(Comunes.centrarDialog(nuevaEAA));
        nuevaEAA.setVisible(true);
        cargarEspecialidadesAAcademicas();
    }

    private void agregarTipoDuracionAsignatura() {
        diagTipoDuracionAsignatura nuevaTDA = new diagTipoDuracionAsignatura(null, true, "Alta");
        nuevaTDA.setLocation(Comunes.centrarDialog(nuevaTDA));
        nuevaTDA.setVisible(true);
        cargarTipoDuracionAsignatura();
    }

    private void agregarTipoAsignatura() {
        diagTipoAsignatura nuevaTA = new diagTipoAsignatura(null, true, "Alta");
        nuevaTA.setLocation(Comunes.centrarDialog(nuevaTA));
        nuevaTA.setVisible(true);
        cargarTipoAsignatura();
    }

    private void agregarCarreraAsignatura() {
        diagCarreraAsignatura nuevaCA = new diagCarreraAsignatura(null, true, "Alta");
        nuevaCA.setLocation(Comunes.centrarDialog(nuevaCA));
        nuevaCA.setVisible(true);
        cargarCarreraAsignatura();
    }

    private void agregarLocalidad() {
        diagLocalidad nuevaLocalidad = new diagLocalidad(null, true, "Alta");
        nuevaLocalidad.setLocation(Comunes.centrarDialog(nuevaLocalidad));
        nuevaLocalidad.setVisible(true);
        cargarLocalidad();
    }
}
