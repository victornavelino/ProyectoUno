/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 06/10/2009, 22:33:56
 */
package vista;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import entidades.usuario.Usuario;
import facade.AreaSecytFacade;
import facade.MensajeFacade;
import facade.ProyectoFacade;
import includes.Comunes;
import includes.ImagenFondoCentrada;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import vista.EntradasSalidas.diagEntradas;
import vista.EntradasSalidas.diagEntradasPases;
import vista.EntradasSalidas.diagSalidas;
import vista.becas.evaluacion.DgBecasEvaluacionWeb;
import vista.becas.evaluacion.DgBecasItemEvaluar;
import vista.categorizacion.diagCargaRapidaCategorizacion;
import vista.categorizacion.diagInvestigadorBusqueda;
import vista.categorizacion.diagLlamadoBusqueda;
import vista.categorizacion.diagReportesCategorizacion;
import vista.categorizacion.winsip.diagWinsip;
import vista.economico.diagAdministrarEconomico;
import vista.economico.diagAdministrarEconomicoProyectos;
import vista.editorial.diagEditorialPrincipal;
import vista.editorial.diagReportesEditorialCientifica;
import vista.editorial.diagExpedienteEditorialPrincipal;
import vista.evaluacionesweb.diagEvaluacionesWeb;
import vista.panelControl.diagEliminarPanelControl;
import vista.panelControl.diagRepetidos;
import vista.proyectoWeb.diagConvocatoria;
import vista.proyectos.DiagLogProyecto;
import vista.proyectos.diagAgregarIntegrantes;
import vista.proyectos.diagProyecto;
import vista.proyectos.diagReporteProyectos;
import vista.proyectos.resultado.DiagProyectoResultadoReportes;
import vista.proyectos.resultado.diagProyectoResultado;
import vista.proyectos.resultado.importacion.diagProyectoResultadoImportacion;
import vista.proyectos.vinculacion.diagProyectoVinculacionPrincipal;
import vista.resoluciones.diagAsignacionResolucionInvestigadores;
import vista.usuarios.diagUsuarioControlCarga;
import vista.usuarios.frUsuarioAlta;
import vista.usuarios.frUsuarioModificacion;
import vistas.becas.diagAgregarPostulante;
import vistas.becas.diagInformes;
import vistas.investigadores.DiagAccesoWeb;
import vistas.investigadores.diagInvestigador;
import vistas.investigadores.diagReportesInvestigador;
import vistas.mensajes.diagMensajes;
import vistas.supervision.diagGestionContrasenas;
import vista.editorial.stock.diagStockPrincipal;
import vista.proyectos.vinculacion.DiagProyectoVinculacionBusquedaSimple;

/**
 *
 * @author mauricio
 */
public class frPrincipal extends javax.swing.JFrame {

    // Atributos
    private Usuario usuario;
    private String mensajeSupervision;
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Timer timer = new Timer(60000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Date fechaServidor = new Date();
            lbHoraDelServidor.setText(dateFormat.format(fechaServidor));
        }
    });
    private frPrincipal frame;
    private String mensajeProyecto;
    private String mensajeIncentivo;
    private String mensajeBeca;
    private String mensajeCategorizacion;
    private String mensajeVinculacion;
    private String mensajeEconomico;
    private String entradas;
    private String salidas;

    public frPrincipal(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        imagenFondo();
        llenarTaskPanels(this);
        this.setTitle("Bienvenido al Sistema de Gestión de Proyectos");
        cargarMenuesSegunPermiso();
        // TODO: Agregar la validacion aqui

        inicializarComponentes();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void llenarTaskPanels(frPrincipal jFrame) {
        cargarContadorMensajes();
        this.frame = jFrame;

//        jXTaskPnInvestigadores.add(new AbstractAction() {
//
//            {
//                putValue(Action.NAME, "Gestión de Investigadores");
//                putValue(Action.SHORT_DESCRIPTION, "Gestión de Investigadores");
//               
//            }
//
//            public void actionPerformed(ActionEvent e) {
//                infrInvestigadorGestion investigadorGestion = new infrInvestigadorGestion();
//                jDesktopPane1.add(investigadorGestion);
//                investigadorGestion.setVisible(true);
//            }
//        });
//        jXTaskPnInvestigadores.add(new AbstractAction() {
//
//            {
//                putValue(Action.NAME, "Modificar Datos");
//                putValue(Action.SHORT_DESCRIPTION, "Modificar Datos Investigador");
//               
//            }
//
//            public void actionPerformed(ActionEvent e) {
//                //aca se instancia el internalframe y se lo agrega al desktoppane
//            }
//        });
//
//        jXTaskPnInvestigadores.add(new AbstractAction() {
//
//            {
//                putValue(Action.NAME, "Nuevo Investigador Incentivos");
//                putValue(Action.SHORT_DESCRIPTION, "Nuevo Investigador Incentivos");
//               
//            }
//
//            public void actionPerformed(ActionEvent e) {
//                diagInvestigador investigadorAlta2 = new diagInvestigador(null, true);
//                investigadorAlta2.setVisible(true);
//            }
//        });
//
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestión Proyectos");
                putValue(Action.SHORT_DESCRIPTION, "Gestión Proyectos");

            }

            public void actionPerformed(ActionEvent e) {
                diagAgregarIntegrantes agregarIntegrantes = new diagAgregarIntegrantes(null, true, usuario);
                agregarIntegrantes.setLocation(Comunes.centrarDialog(agregarIntegrantes));
                agregarIntegrantes.setVisible(true);
            }
        });

        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Proyecto");
                putValue(Action.SHORT_DESCRIPTION, "Agregar Integrantes");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
            }

            public void actionPerformed(ActionEvent e) {
                diagProyectoBusquedaSimple selecproyecto = new diagProyectoBusquedaSimple(null, true);
                selecproyecto.setLocation(Comunes.centrarDialog(selecproyecto));
                selecproyecto.setVisible(true);
                if (selecproyecto.getProyecto() != null) {
                    diagProyecto proyectoModificacion = new diagProyecto(null, true, "Modificación", selecproyecto.getProyecto(), usuario);
                    proyectoModificacion.setLocation(Comunes.centrarDialog(proyectoModificacion));
                    proyectoModificacion.setSize((int) proyectoModificacion.getToolkit().getScreenSize().getWidth(), ((int) proyectoModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    proyectoModificacion.setVisible(true);

                }
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Investigador");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Investigador");
            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Seleccione Investigador para modificar");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagInvestigador investigadorModificacion = new diagInvestigador(null, true, "Modificación", investigador, usuario);
                    investigadorModificacion.setSize((int) investigadorModificacion.getToolkit().getScreenSize().getWidth(), ((int) investigadorModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
                    investigadorModificacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
                }

            }
        });

        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Publicaciones");
                putValue(Action.SHORT_DESCRIPTION, "Agregar Publicaciones");
            }

            public void actionPerformed(ActionEvent e) {
                diagProyectoResultado proyectoResultado = new diagProyectoResultado(null, true, usuario);
                proyectoResultado.setLocation(Comunes.centrarDialog(proyectoResultado));
                proyectoResultado.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Importar Datos Publicaciones");
                putValue(Action.SHORT_DESCRIPTION, "Importar Datos");
            }

            public void actionPerformed(ActionEvent e) {
                diagProyectoResultadoImportacion resultadoImportacion = new diagProyectoResultadoImportacion(null, enabled, usuario);
                resultadoImportacion.setLocation(Comunes.centrarDialog(resultadoImportacion));
                resultadoImportacion.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Importar Proyecto de Vinculación");
                putValue(Action.SHORT_DESCRIPTION, "Importar Proyecto de Vinculación");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
            }

            public void actionPerformed(ActionEvent e) {
                DiagProyectoVinculacionBusquedaSimple selecproyecto = new DiagProyectoVinculacionBusquedaSimple(null, true);
                selecproyecto.setLocation(Comunes.centrarDialog(selecproyecto));
                selecproyecto.setVisible(true);
                if (selecproyecto.getProyecto() != null) {
                    //esta seguro que desea mofiicar?
                    Proyecto proyecto = ProyectoFacade.getInstance().importarProyectoVinculación(selecproyecto.getProyecto());
                    if (proyecto != null) {
                        diagProyecto proyectoModificacion = new diagProyecto(null, true, "Modificación", proyecto, usuario);
                        proyectoModificacion.setLocation(Comunes.centrarDialog(proyectoModificacion));
                        proyectoModificacion.setSize((int) proyectoModificacion.getToolkit().getScreenSize().getWidth(), ((int) proyectoModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                        proyectoModificacion.setVisible(true);
                    }
                }
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Reportes Publicaciones");
                putValue(Action.SHORT_DESCRIPTION, "Reportes de Publicaciones");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                DiagProyectoResultadoReportes diagProyectoResultadoReportes = new DiagProyectoResultadoReportes(null, true);
                diagProyectoResultadoReportes.setLocation(Comunes.centrarDialog(diagProyectoResultadoReportes));
                diagProyectoResultadoReportes.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Económico");
                putValue(Action.SHORT_DESCRIPTION, "Económico");
            }

            public void actionPerformed(ActionEvent e) {
                diagAdministrarEconomicoProyectos administrarEconomicoProyectos = new diagAdministrarEconomicoProyectos(null, enabled, usuario);
                administrarEconomicoProyectos.setLocation(Comunes.centrarDialog(administrarEconomicoProyectos));
                administrarEconomicoProyectos.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Reportes");
                putValue(Action.SHORT_DESCRIPTION, "Mostrar Reportes");
            }

            public void actionPerformed(ActionEvent e) {
                diagReporteProyectos reporteProyectos = new diagReporteProyectos(null, true);
                reporteProyectos.setLocation(Comunes.centrarDialog(reporteProyectos));
                reporteProyectos.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Convocatorias");
                putValue(Action.SHORT_DESCRIPTION, "Gestionar Convocatorias");
            }

            public void actionPerformed(ActionEvent e) {
                diagConvocatoria diagConvocatoria1 = new diagConvocatoria(null, true);
                diagConvocatoria1.setLocation(Comunes.centrarDialog(diagConvocatoria1));
                diagConvocatoria1.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeProyecto);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");
            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes mensajes = new diagMensajes(frame, true, AreaSecytFacade.getInstance().buscar("PROYECTO"));
                mensajes.setLocation(Comunes.centrarDialog(mensajes));
                mensajes.setVisible(true);
            }
        });
        jXTaskPnProyectos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Pase MEyS - Archivos Digitales");
                putValue(Action.SHORT_DESCRIPTION, "Pase MEyS - Archivos Digitales");
            }

            public void actionPerformed(ActionEvent e) {
                diagEntradasPases entradasPases = new diagEntradasPases(frame, true, usuario);
                entradasPases.setLocation(Comunes.centrarDialog(entradasPases));
                entradasPases.setVisible(true);
            }
        });

        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Categorizaciones");
                putValue(Action.SHORT_DESCRIPTION, "Categorizacion");

            }

            public void actionPerformed(ActionEvent e) {
                vista.categorizacion.diagInvestigadorBusqueda agregarIntegrantes = new diagInvestigadorBusqueda(null, true, usuario);
                agregarIntegrantes.setLocation(Comunes.centrarDialog(agregarIntegrantes));
                agregarIntegrantes.setVisible(true);
            }
        });

        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestión de Llamados");
                putValue(Action.SHORT_DESCRIPTION, "Gestión de las convocatorias");

            }

            public void actionPerformed(ActionEvent e) {
                diagLlamadoBusqueda llamadoBusqueda = new diagLlamadoBusqueda(null, true, usuario);
                llamadoBusqueda.setLocation(Comunes.centrarDialog(llamadoBusqueda));
                llamadoBusqueda.setVisible(true);
            }
        });
        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Investigador");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Investigador");
            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Seleccione Investigador para modificar");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagInvestigador investigadorModificacion = new diagInvestigador(null, true, "Modificación", investigador, usuario);
                    investigadorModificacion.setSize((int) investigadorModificacion.getToolkit().getScreenSize().getWidth(), ((int) investigadorModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
                    investigadorModificacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
                }

            }
        });
        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Reportes");
                putValue(Action.SHORT_DESCRIPTION, "Mostrar Reportes");

            }

            public void actionPerformed(ActionEvent e) {
                diagReportesCategorizacion reportesCat = new diagReportesCategorizacion(null, true);
                reportesCat.setLocation(Comunes.centrarDialog(reportesCat));
                reportesCat.setVisible(true);
            }
        });
        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestión de WINSIP");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Gestión de WINSIP");
            }

            public void actionPerformed(ActionEvent e) {
                diagWinsip winsip = new diagWinsip(null, true);
                winsip.setTitle("Gestión de WINSIP");
                winsip.setLocation(Comunes.centrarDialog(winsip));
                winsip.setVisible(true);
            }
        });
        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestión de Evaluaciones Web");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Gestión de Evaluaciones Web");
            }

            public void actionPerformed(ActionEvent e) {
                diagEvaluacionesWeb evaluacionesWeb = new diagEvaluacionesWeb(frame, true, usuario);
                evaluacionesWeb.setTitle("Gestión de Evaluaciones Web");
                evaluacionesWeb.setLocation(Comunes.centrarDialog(evaluacionesWeb));
                evaluacionesWeb.setVisible(true);
            }
        });
        jxTaskPnCategorizaciones.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeCategorizacion);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");

            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes mensajes = new diagMensajes(frame, true, AreaSecytFacade.getInstance().buscar("CATEGORIZACION"));
                mensajes.setLocation(Comunes.centrarDialog(mensajes));
                mensajes.setVisible(true);
            }
        });
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Becas");
                putValue(Action.SHORT_DESCRIPTION, "Becas");

            }

            public void actionPerformed(ActionEvent e) {
                diagAgregarPostulante agregarIntegrantes = new diagAgregarPostulante(null, true, usuario);
                agregarIntegrantes.setLocation(Comunes.centrarDialog(agregarIntegrantes));
                agregarIntegrantes.setVisible(true);
            }
        });
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Investigador");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Investigador");
            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Seleccione Investigador para modificar");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagInvestigador investigadorModificacion = new diagInvestigador(null, true, "Modificación", investigador, usuario);
                    investigadorModificacion.setSize((int) investigadorModificacion.getToolkit().getScreenSize().getWidth(), ((int) investigadorModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
                    investigadorModificacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
                }

            }
        });
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Reportes");
                putValue(Action.SHORT_DESCRIPTION, "Mostrar Reportes");

            }

            public void actionPerformed(ActionEvent e) {
                diagInformes reportes = new diagInformes(null, true);
                reportes.setLocation(Comunes.centrarDialog(reportes));
                reportes.setVisible(true);
            }
        });
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Evaluación");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Evaluación");
            }

            public void actionPerformed(ActionEvent e) {
                DgBecasEvaluacionWeb dgBecasEvaluacionWeb  = new DgBecasEvaluacionWeb (null, true);
                dgBecasEvaluacionWeb.setLocationRelativeTo(null);
                dgBecasEvaluacionWeb.setVisible(true);


            }
        });
        
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Items a evaluar");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Items a evaluar");
            }

            public void actionPerformed(ActionEvent e) {
                DgBecasItemEvaluar dgBecasItemEvaluar  = new DgBecasItemEvaluar (null, true);
                dgBecasItemEvaluar.setLocationRelativeTo(null);
                dgBecasItemEvaluar.setVisible(true);


            }
        });
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jxTaskPnBecas.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeBeca);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");

            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes mensajes = new diagMensajes(frame, true, AreaSecytFacade.getInstance().buscar("BECAS"));
                mensajes.setLocation(Comunes.centrarDialog(mensajes));
                mensajes.setVisible(true);
            }
        });

        jxTaskPnIncentivos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestión Proyectos");
                putValue(Action.SHORT_DESCRIPTION, "Gestión Proyectos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);

            }

            public void actionPerformed(ActionEvent e) {
                diagAgregarIntegrantes agregarIntegrantes = new diagAgregarIntegrantes(null, true, usuario);
                agregarIntegrantes.setLocation(Comunes.centrarDialog(agregarIntegrantes));
                agregarIntegrantes.setVisible(true);
            }
        });
        jxTaskPnIncentivos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Investigador");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Investigador");
            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Seleccione Investigador para modificar");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagInvestigador investigadorModificacion = new diagInvestigador(null, true, "Modificación", investigador, usuario);
                    investigadorModificacion.setSize((int) investigadorModificacion.getToolkit().getScreenSize().getWidth(), ((int) investigadorModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
                    investigadorModificacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
                }

            }
        });
        jxTaskPnIncentivos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Reportes");
                putValue(Action.SHORT_DESCRIPTION, "Mostrar Reportes");

            }

            public void actionPerformed(ActionEvent e) {
                diagReportesInvestigador reportesInvestigador = new diagReportesInvestigador(null, true);
                reportesInvestigador.setLocation(Comunes.centrarDialog(reportesInvestigador));
                reportesInvestigador.setVisible(true);
            }
        });
        jxTaskPnIncentivos.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jxTaskPnIncentivos.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeIncentivo);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");

            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes mensajes = new diagMensajes(frame, true, AreaSecytFacade.getInstance().buscar("INCENTIVOS"));
                mensajes.setLocation(Comunes.centrarDialog(mensajes));
                mensajes.setVisible(true);
            }
        });

//USUARIOS
        jXTaskPnUsuarios.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Alta de Usuarios");
                putValue(Action.SHORT_DESCRIPTION, "Alta de Usuarios");

            }

            public void actionPerformed(ActionEvent e) {
                frUsuarioAlta usuarioAlta = new frUsuarioAlta();
                jDesktopPane1.add(usuarioAlta);
                usuarioAlta.setVisible(true);
            }
        });

        jXTaskPnUsuarios.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificación de Usuarios");
                putValue(Action.SHORT_DESCRIPTION, "Modificación de Usuarios");

            }

            public void actionPerformed(ActionEvent e) {
                frUsuarioModificacion usuarioModificacion = new frUsuarioModificacion();
                jDesktopPane1.add(usuarioModificacion);
                usuarioModificacion.setVisible(true);
            }
        });
// supervicion deshabilitada
        jXTaskPnSupervision.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Control de Operaciones");
                putValue(Action.SHORT_DESCRIPTION, "Control de Carga");

            }

            public void actionPerformed(ActionEvent e) {
                diagUsuarioControlCarga carga = new diagUsuarioControlCarga(null, true);
                carga.setLocation(Comunes.centrarDialog(carga));
                carga.setVisible(true);
            }
        });
        jXTaskPnSupervision.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestion Contraseñas Web");
                putValue(Action.SHORT_DESCRIPTION, "Gestion Contraseñas Web");

            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Gestion de Contraseñas - Seleccione Investigador ");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagGestionContrasenas diagContrasenas = new diagGestionContrasenas(null, true, investigador);
                    diagContrasenas.setLocation(Comunes.centrarDialog(diagContrasenas));
                    diagContrasenas.setVisible(true);
                }
            }
        });
        jXTaskPnSupervision.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Busquedas de la Web");
                putValue(Action.SHORT_DESCRIPTION, "Busquedas de la Web");
            }

            public void actionPerformed(ActionEvent e) {
                DiagLogProyecto diagLogProyecto = new DiagLogProyecto(null, true);
                diagLogProyecto.setTitle("Búsquedas de la Web");
                diagLogProyecto.setLocation(Comunes.centrarDialog(diagLogProyecto));
                diagLogProyecto.setVisible(true);
            }
        });
        jXTaskPnSupervision.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Acceso de Investigadores Web");
                putValue(Action.SHORT_DESCRIPTION, "Acceso de Investigadores Web");
            }

            public void actionPerformed(ActionEvent e) {
                DiagAccesoWeb accesoWeb = new DiagAccesoWeb(null, true);
                accesoWeb.setTitle("Acceso de Investigadores Web");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jXTaskPnSupervision.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jXTaskPnSupervision.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeSupervision);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");

            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes diagMensajes = new diagMensajes(frame, true);
                diagMensajes.setLocation(Comunes.centrarDialog(diagMensajes));
                diagMensajes.setVisible(true);
            }
        });
        jXTaskPnProyectosVinculacion.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Alta y Modificación de Proyectos de Vinculación");
                putValue(Action.SHORT_DESCRIPTION, "Alta y Modificación de Proyectos de Vinculación");
            }

            public void actionPerformed(ActionEvent e) {
                diagProyectoVinculacionPrincipal proyectoVinculacionPrincipal = new diagProyectoVinculacionPrincipal(null, true, usuario);
                proyectoVinculacionPrincipal.setLocation(Comunes.centrarDialog(proyectoVinculacionPrincipal));
                proyectoVinculacionPrincipal.setVisible(true);
            }
        });
        jXTaskPnProyectosVinculacion.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Investigador");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Investigador");
            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Seleccione Investigador para modificar");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagInvestigador investigadorModificacion = new diagInvestigador(null, true, "Modificación", investigador, usuario);
                    investigadorModificacion.setSize((int) investigadorModificacion.getToolkit().getScreenSize().getWidth(), ((int) investigadorModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
                    investigadorModificacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
                }

            }
        });
        jXTaskPnProyectosVinculacion.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jXTaskPnProyectosVinculacion.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeVinculacion);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");

            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes mensajes = new diagMensajes(frame, true, AreaSecytFacade.getInstance().buscar("VINCULACION"));
                mensajes.setLocation(Comunes.centrarDialog(mensajes));
                mensajes.setVisible(true);
            }
        });

        jXTaskPnEconomico.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Gestión Económica");
                putValue(Action.SHORT_DESCRIPTION, "Gestión Económica");

            }

            public void actionPerformed(ActionEvent e) {
                diagAdministrarEconomico administrarEconomico = new diagAdministrarEconomico(null, true, usuario);
                administrarEconomico.setLocation(Comunes.centrarDialog(administrarEconomico));
                administrarEconomico.setVisible(true);
            }
        });
        jXTaskPnEconomico.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Proyecto");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Proyecto");
            }

            public void actionPerformed(ActionEvent e) {
                diagProyectoBusquedaSimple selecproyecto = new diagProyectoBusquedaSimple(null, true);
                selecproyecto.setLocation(Comunes.centrarDialog(selecproyecto));
                selecproyecto.setVisible(true);
                if (selecproyecto.getProyecto() != null) {
                    diagProyecto proyectoModificacion = new diagProyecto(null, true, "Modificación", selecproyecto.getProyecto(), usuario);
                    proyectoModificacion.setLocation(Comunes.centrarDialog(proyectoModificacion));
                    proyectoModificacion.setSize((int) proyectoModificacion.getToolkit().getScreenSize().getWidth(), ((int) proyectoModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    proyectoModificacion.setVisible(true);

                }

            }
        });
        jXTaskPnEconomico.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Modificar Investigador");
                putValue(Action.SHORT_DESCRIPTION, "Modificar Investigador");
            }

            public void actionPerformed(ActionEvent e) {
                diagInvestigadorBusquedaSimple busquedaSimple = new diagInvestigadorBusquedaSimple(null, true);
                busquedaSimple.setTitle("Seleccione Investigador para modificar");
                busquedaSimple.setLocation(Comunes.centrarDialog(busquedaSimple));
                busquedaSimple.setVisible(true);
                Investigador investigador = busquedaSimple.getInvestigador();
                if (investigador != null) {
                    diagInvestigador investigadorModificacion = new diagInvestigador(null, true, "Modificación", investigador, usuario);
                    investigadorModificacion.setSize((int) investigadorModificacion.getToolkit().getScreenSize().getWidth(), ((int) investigadorModificacion.getToolkit().getScreenSize().getHeight()) - 50);
                    investigadorModificacion.setLocation(Comunes.centrarDialog(investigadorModificacion));
                    investigadorModificacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó ningún investigador");
                }

            }
        });
        jXTaskPnEconomico.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jXTaskPnEconomico.add(new AbstractAction() {
            {
                putValue(Action.NAME, mensajeEconomico);
                putValue(Action.SHORT_DESCRIPTION, "Mensajes");

            }

            public void actionPerformed(ActionEvent e) {
                diagMensajes mensajes = new diagMensajes(frame, true, AreaSecytFacade.getInstance().buscar("UNIDAD DE ADMINISTRACION"));
                mensajes.setLocation(Comunes.centrarDialog(mensajes));
                mensajes.setVisible(true);
            }
        });

        jXTaskPnEntradasSalidas.add(new AbstractAction() {
            {
                putValue(Action.NAME, entradas);
                putValue(Action.SHORT_DESCRIPTION, "Entradas");

            }

            public void actionPerformed(ActionEvent e) {
                diagEntradas dlgE = new diagEntradas(null, true, usuario);
                dlgE.setLocation(Comunes.centrarDialog(dlgE));
                dlgE.setVisible(true);
            }
        });

        jXTaskPnEntradasSalidas.add(new AbstractAction() {
            {
                putValue(Action.NAME, salidas);
                putValue(Action.SHORT_DESCRIPTION, "Salidas");

            }

            public void actionPerformed(ActionEvent e) {
                diagSalidas dlgS = new diagSalidas(null, true, usuario);
                dlgS.setLocation(Comunes.centrarDialog(dlgS));
                dlgS.setVisible(true);
            }
        });
        jXTaskPnEntradasSalidas.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Envío Archivos");
                javax.swing.Icon icon = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
                putValue(Action.SMALL_ICON, icon);
                putValue(Action.SHORT_DESCRIPTION, "Envío Multiple de Archivos");
            }

            public void actionPerformed(ActionEvent e) {
                diagAsignacionResolucionInvestigadores accesoWeb = new diagAsignacionResolucionInvestigadores(null, true);
                accesoWeb.setTitle("Envío Multiple de Archivos");
                accesoWeb.setLocation(Comunes.centrarDialog(accesoWeb));
                accesoWeb.setVisible(true);
            }
        });
        jXTaskPnEditorial.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Editoriales");
                putValue(Action.SHORT_DESCRIPTION, "Editoriales");
            }

            public void actionPerformed(ActionEvent e) {
                diagEditorialPrincipal editorialPrincipal = new diagEditorialPrincipal(null, true, usuario);
                editorialPrincipal.setLocation(Comunes.centrarDialog(editorialPrincipal));
                editorialPrincipal.setVisible(true);
            }
        });
        jXTaskPnEditorial.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Expedientes");
                putValue(Action.SHORT_DESCRIPTION, "Expedientes");
            }

            public void actionPerformed(ActionEvent e) {
                diagExpedienteEditorialPrincipal expedientePrincipal = new diagExpedienteEditorialPrincipal(null, true, usuario);
                expedientePrincipal.setLocation(Comunes.centrarDialog(expedientePrincipal));
                expedientePrincipal.setVisible(true);
            }
        });
        jXTaskPnEditorial.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Stock");
                putValue(Action.SHORT_DESCRIPTION, "Stock");
            }

            public void actionPerformed(ActionEvent e) {
                diagStockPrincipal stockPrincipal = new diagStockPrincipal(null, true, usuario);
                stockPrincipal.setLocation(Comunes.centrarDialog(stockPrincipal));
                stockPrincipal.setVisible(true);
            }
        });
        
        jXTaskPnEditorial.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Reportes");
                putValue(Action.SHORT_DESCRIPTION, "Resportes");
            }
            
            public void actionPerformed(ActionEvent e) {               
                diagReportesEditorialCientifica reportesEditorialCientifica = new diagReportesEditorialCientifica(null, true);
                reportesEditorialCientifica.setLocation(Comunes.centrarDialog(reportesEditorialCientifica));
                reportesEditorialCientifica.setVisible(true);
            }
        });

    }

    /**
     * Creates new form Principal
     */
    public frPrincipal() {
        initComponents();
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        imagenFondo();
        llenarTaskPanels(this);
        this.setTitle("Bienvenido al Sistema de Gestión de Proyectos");
        if (usuario == null) {
            Comunes.mensajeError("No puede ingresar directamente sin autenticarse", "Error de Autenticación");
            System.exit(0);
        }
        cargarMenuesSegunPermiso();
        // TODO: Agregar la validacion aqui

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

        jMenu7 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        taSalida = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        tbBarraPrincipal = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btonCerrarSesion = new javax.swing.JButton();
        btonTerminar2 = new javax.swing.JButton();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPnProyectos = new org.jdesktop.swingx.JXTaskPane();
        jxTaskPnIncentivos = new org.jdesktop.swingx.JXTaskPane();
        jxTaskPnBecas = new org.jdesktop.swingx.JXTaskPane();
        jxTaskPnCategorizaciones = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPnUsuarios = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPnSupervision = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPnProyectosVinculacion = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPnEconomico = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPnEntradasSalidas = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPnEditorial = new org.jdesktop.swingx.JXTaskPane();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        lbEtiquetaHoraDelServidor = new javax.swing.JLabel();
        lbHoraDelServidor = new javax.swing.JLabel();

        jMenu7.setText("Impresión");
        jMenu7.setActionCommand("jMenu7");
        jMenu7.setName("jMenu7"); // NOI18N

        jMenuItem16.setText("Configurar Recibo");
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem16);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(0, 0, 0));

        jDesktopPane1.setName("jDesktopPane1"); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setForeground(new java.awt.Color(255, 204, 0));
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        taSalida.setBackground(new java.awt.Color(0, 0, 0));
        taSalida.setColumns(20);
        taSalida.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        taSalida.setForeground(new java.awt.Color(153, 255, 102));
        taSalida.setRows(5);
        taSalida.setBorder(null);
        taSalida.setName("taSalida"); // NOI18N
        jScrollPane1.setViewportView(taSalida);

        jDesktopPane1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 990, 150);

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        tbBarraPrincipal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbBarraPrincipal.setRollover(true);
        tbBarraPrincipal.setAlignmentX(0.0F);
        tbBarraPrincipal.setName("tbBarraPrincipal"); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton1.setText("CAMBIAR CLAVE");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(85, 55));
        jButton1.setMinimumSize(new java.awt.Dimension(85, 55));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(75, 17));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        tbBarraPrincipal.add(jButton1);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setLabel("PANEL DE CONTROL");
        jButton3.setMaximumSize(new java.awt.Dimension(85, 55));
        jButton3.setMinimumSize(new java.awt.Dimension(85, 55));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        tbBarraPrincipal.add(jButton3);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jButton4.setText("DUPLICADOS");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setMaximumSize(new java.awt.Dimension(85, 55));
        jButton4.setMinimumSize(new java.awt.Dimension(85, 55));
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        tbBarraPrincipal.add(jButton4);

        btonCerrarSesion.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        btonCerrarSesion.setText("CERRAR SESION");
        btonCerrarSesion.setFocusable(false);
        btonCerrarSesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btonCerrarSesion.setMaximumSize(new java.awt.Dimension(88, 55));
        btonCerrarSesion.setMinimumSize(new java.awt.Dimension(88, 55));
        btonCerrarSesion.setName("btonCerrarSesion"); // NOI18N
        btonCerrarSesion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonCerrarSesionActionPerformed(evt);
            }
        });
        tbBarraPrincipal.add(btonCerrarSesion);

        btonTerminar2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        btonTerminar2.setText("SALIR");
        btonTerminar2.setFocusable(false);
        btonTerminar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btonTerminar2.setMaximumSize(new java.awt.Dimension(88, 55));
        btonTerminar2.setMinimumSize(new java.awt.Dimension(88, 55));
        btonTerminar2.setName("btonTerminar2"); // NOI18N
        btonTerminar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btonTerminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonTerminar2ActionPerformed(evt);
            }
        });
        tbBarraPrincipal.add(btonTerminar2);

        jToolBar1.add(tbBarraPrincipal);

        jXTaskPaneContainer1.setBackground(new java.awt.Color(240, 240, 240));
        jXTaskPaneContainer1.setName("jXTaskPaneContainer1"); // NOI18N

        jXTaskPnProyectos.setExpanded(false);
        jXTaskPnProyectos.setTitle("Proyectos");
        jXTaskPnProyectos.setName("jXTaskPnProyectos"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnProyectos);

        jxTaskPnIncentivos.setExpanded(false);
        jxTaskPnIncentivos.setTitle("Incentivos");
        jxTaskPnIncentivos.setName("jxTaskPnIncentivos"); // NOI18N
        jXTaskPaneContainer1.add(jxTaskPnIncentivos);

        jxTaskPnBecas.setExpanded(false);
        jxTaskPnBecas.setTitle("Becas");
        jxTaskPnBecas.setName("jxTaskPnBecas"); // NOI18N
        jXTaskPaneContainer1.add(jxTaskPnBecas);

        jxTaskPnCategorizaciones.setExpanded(false);
        jxTaskPnCategorizaciones.setTitle("Evaluaciones");
        jxTaskPnCategorizaciones.setName("jxTaskPnCategorizaciones"); // NOI18N
        jXTaskPaneContainer1.add(jxTaskPnCategorizaciones);

        jXTaskPnUsuarios.setExpanded(false);
        jXTaskPnUsuarios.setTitle("Usuarios");
        jXTaskPnUsuarios.setName("jXTaskPnUsuarios"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnUsuarios);

        jXTaskPnSupervision.setExpanded(false);
        jXTaskPnSupervision.setTitle("Supervisión");
        jXTaskPnSupervision.setName("jXTaskPnSupervision"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnSupervision);

        jXTaskPnProyectosVinculacion.setExpanded(false);
        jXTaskPnProyectosVinculacion.setTitle("Proyectos de Vinculacion");
        jXTaskPnProyectosVinculacion.setName("jXTaskPnProyectosVinculacion"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnProyectosVinculacion);

        jXTaskPnEconomico.setExpanded(false);
        jXTaskPnEconomico.setTitle("Económico");
        jXTaskPnEconomico.setName("jXTaskPnEconomico"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnEconomico);

        jXTaskPnEntradasSalidas.setExpanded(false);
        jXTaskPnEntradasSalidas.setTitle("Entradas/Salidas");
        jXTaskPnEntradasSalidas.setName("jXTaskPnEntradasSalidas"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnEntradasSalidas);

        jXTaskPnEditorial.setExpanded(false);
        jXTaskPnEditorial.setTitle("Editorial Científica");
        jXTaskPnEditorial.setName("jXTaskPnEditorial"); // NOI18N
        jXTaskPaneContainer1.add(jXTaskPnEditorial);

        jXPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jXPanel1.setName("jXPanel1"); // NOI18N

        lbEtiquetaHoraDelServidor.setText("Hora");
        lbEtiquetaHoraDelServidor.setName("lbEtiquetaHoraDelServidor"); // NOI18N

        lbHoraDelServidor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHoraDelServidor.setText("00:00");
        lbHoraDelServidor.setName("lbHoraDelServidor"); // NOI18N

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHoraDelServidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel1Layout.createSequentialGroup()
                        .addComponent(lbEtiquetaHoraDelServidor)
                        .addGap(26, 26, 26))))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbEtiquetaHoraDelServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoraDelServidor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonCerrarSesionActionPerformed
        frLogin login = new frLogin();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btonCerrarSesionActionPerformed

    private void btonTerminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonTerminar2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btonTerminar2ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
    }//GEN-LAST:event_jMenuItem16ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    frUsuarioModificacion usuarioModificacion = new frUsuarioModificacion(usuario);
    jDesktopPane1.add(usuarioModificacion);
    usuarioModificacion.setVisible(true);

}//GEN-LAST:event_jButton1ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    panelDeControlTitulos();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        diagRepetidos diagRepetidos = new diagRepetidos(this, true);
        diagRepetidos.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        diagRepetidos.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frPrincipal().setVisible(true);

            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btonCerrarSesion;
    private javax.swing.JButton btonTerminar2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnEconomico;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnEditorial;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnEntradasSalidas;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnProyectos;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnProyectosVinculacion;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnSupervision;
    private org.jdesktop.swingx.JXTaskPane jXTaskPnUsuarios;
    private org.jdesktop.swingx.JXTaskPane jxTaskPnBecas;
    private org.jdesktop.swingx.JXTaskPane jxTaskPnCategorizaciones;
    private org.jdesktop.swingx.JXTaskPane jxTaskPnIncentivos;
    private javax.swing.JLabel lbEtiquetaHoraDelServidor;
    private javax.swing.JLabel lbHoraDelServidor;
    private javax.swing.JTextArea taSalida;
    private javax.swing.JToolBar tbBarraPrincipal;
    // End of variables declaration//GEN-END:variables

    private void imagenFondo() {
        BufferedImage image = null;

        try {
            image = ImageIO.read(this.getClass().getResource("/imagenes/logo.jpg"));
            //image = ImageIO.read(getClass().getResourceAsStream("/imagenes/telecentro.png"));

        } catch (IOException ex) {
            Logger.getLogger(frPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jDesktopPane1.setBorder(new ImagenFondoCentrada(image));

    }

    private void inicializarComponentes() {
        timer.setRepeats(true);
        timer.start();
        cargarHoraDelServidorAlInicio();

    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                taSalida.append(text);

            }
        });

    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));

            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));

            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);

            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));

    }

    public void cargarHoraDelServidorAlInicio() {
        Date fechaServidor = new Date();//cambiado
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaServidor);

        if (calendario.get(Calendar.MINUTE) < 10) {
            lbHoraDelServidor.setText(calendario.get(Calendar.HOUR_OF_DAY) + ":0" + calendario.get(Calendar.MINUTE));

        } else {
            lbHoraDelServidor.setText(calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE));

        }
    }

    private void cargarMenuesSegunPermiso() {

        if (!usuario.getGrupo().getNombre().equals("admin")) {
            jXTaskPnUsuarios.setVisible(false);
            jXTaskPnSupervision.setVisible(false);
            jScrollPane1.setVisible(false); //para no ver los errores
            if (usuario.getGrupo().getNombre().equals("fiscalizadores")) {
                jXTaskPnSupervision.setVisible(false);//true cuando este la parte esa
            } else if (usuario.getGrupo().getNombre().equals("proyectos")) {
                jXTaskPnEconomico.setVisible(false);
                jXTaskPnProyectosVinculacion.setVisible(false);
                jxTaskPnBecas.setVisible(false);
                jxTaskPnCategorizaciones.setVisible(false);
                jxTaskPnIncentivos.setVisible(false);
                jXTaskPnEntradasSalidas.setVisible(false);

            } else if (usuario.getGrupo().getNombre().equals("vinculacion")) {
                jXTaskPnEconomico.setVisible(false);
                jXTaskPnProyectos.setVisible(false);
                jxTaskPnBecas.setVisible(false);
                jxTaskPnCategorizaciones.setVisible(false);
                jxTaskPnIncentivos.setVisible(false);
                jXTaskPnEntradasSalidas.setVisible(false);

            } else if (usuario.getGrupo().getNombre().equals("economico")) {
                jXTaskPnProyectosVinculacion.setVisible(false);
                jXTaskPnProyectos.setVisible(false);
                jxTaskPnBecas.setVisible(false);
                jxTaskPnCategorizaciones.setVisible(false);
                jxTaskPnIncentivos.setVisible(false);
                jXTaskPnEntradasSalidas.setVisible(false);

            } else if (usuario.getGrupo().getNombre().equals("becas")) {
                jXTaskPnProyectosVinculacion.setVisible(false);
                jXTaskPnProyectos.setVisible(false);
                jXTaskPnEconomico.setVisible(false);
                jxTaskPnCategorizaciones.setVisible(false);
                jxTaskPnIncentivos.setVisible(false);
                jXTaskPnEntradasSalidas.setVisible(false);

            } else if (usuario.getGrupo().getNombre().equals("categorizacion")) {
                jXTaskPnProyectosVinculacion.setVisible(false);
                jXTaskPnProyectos.setVisible(false);
                jXTaskPnEconomico.setVisible(false);
                jxTaskPnBecas.setVisible(false);
                jxTaskPnIncentivos.setVisible(false);
                jXTaskPnEntradasSalidas.setVisible(false);

            } else if (usuario.getGrupo().getNombre().equals("incentivos")) {
                jXTaskPnProyectosVinculacion.setVisible(false);
                jXTaskPnProyectos.setVisible(false);
                jXTaskPnEconomico.setVisible(false);
                jxTaskPnBecas.setVisible(false);
                jxTaskPnCategorizaciones.setVisible(false);
                jXTaskPnEntradasSalidas.setVisible(false);

            } else if (usuario.getGrupo().getNombre().equals("mesaentradas")) {
                jXTaskPnProyectosVinculacion.setVisible(false);
                jXTaskPnProyectos.setVisible(false);
                jXTaskPnEconomico.setVisible(false);
                jxTaskPnBecas.setVisible(false);
                jxTaskPnCategorizaciones.setVisible(false);
                jxTaskPnIncentivos.setVisible(false);
            } else if (usuario.getGrupo().getNombre().equals("editorialcientifica")) {
            System.out.println("USUARIOOO: " + usuario.getNombreUsuario());
            System.out.println("USUARIOOO: grupo" + usuario.getGrupo().getDescripcion());
            System.out.println("entro");
            jXTaskPnProyectosVinculacion.setVisible(false);
            jXTaskPnProyectos.setVisible(false);
            jXTaskPnEconomico.setVisible(false);
            jxTaskPnBecas.setVisible(false);
            jxTaskPnCategorizaciones.setVisible(false);
            jxTaskPnIncentivos.setVisible(false);
            jXTaskPnUsuarios.setVisible(false);
            jXTaskPnSupervision.setVisible(false);
            jXTaskPnEntradasSalidas.setVisible(false);
        } else {
            // redirectSystemStreams();
        }

    }
    }
    private void panelDeControlTitulos() {
        diagEliminarPanelControl diagEliminarPanelControl = new diagEliminarPanelControl(this, true);
        diagEliminarPanelControl.setLocation(Comunes.centrarDialog(diagEliminarPanelControl));
        diagEliminarPanelControl.setVisible(true);
    }

    public void actualizarMensajes() {
        jXTaskPnUsuarios.removeAll();
        jXTaskPnSupervision.removeAll();
        jXTaskPnProyectos.removeAll();
        jxTaskPnIncentivos.removeAll();
        jxTaskPnBecas.removeAll();
        jxTaskPnCategorizaciones.removeAll();
        jXTaskPnProyectosVinculacion.removeAll();
        jXTaskPnEconomico.removeAll();
        //actualizamos el contenedor
        jXTaskPaneContainer1.updateUI();
        //y lo cargamos de nuevo
        llenarTaskPanels(frame);

    }

    private void cargarContadorMensajes() {
        mensajeSupervision = "Mensajes(" + MensajeFacade.getInstance().getCantidadMjesNoLeidos() + ")";
        mensajeProyecto = "Mensajes (" + MensajeFacade.getInstance().getCantidadMjesNoLeidos("PROYECTO") + ")";
        mensajeIncentivo = "Mensajes(" + MensajeFacade.getInstance().getCantidadMjesNoLeidos("INCENTIVOS") + ")";
        mensajeBeca = "Mensajes(" + MensajeFacade.getInstance().getCantidadMjesNoLeidos("BECAS") + ")";
        mensajeCategorizacion = "Mensajes(" + MensajeFacade.getInstance().getCantidadMjesNoLeidos("CATEGORIZACION") + ")";
        mensajeVinculacion = "Mensajes(" + MensajeFacade.getInstance().getCantidadMjesNoLeidos("VINCULACION") + ")";
        mensajeEconomico = "Mensajes(" + MensajeFacade.getInstance().getCantidadMjesNoLeidos("UNIDAD DE ADMINISTRACION") + ")";
        entradas = "Entradas";
        salidas = "Salidas";
    }
}
