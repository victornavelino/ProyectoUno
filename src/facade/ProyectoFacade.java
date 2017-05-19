/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ParticipacionJpaController;
import controladores.ProyectoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.TipoProyecto;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.categorizacion.Categorizacion;
import entidades.categorizacion.Winsip;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.investigador.formacionAcademica.FormacionAcademicaOtra;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
import entidades.persona.investigador.Docencia;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.CampoAplicacion;
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.Especialidad;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.ObjetivoSocioeconomico;
import entidades.proyecto.Participacion;
import entidades.proyecto.Programa;
import entidades.proyecto.Prorroga;
import entidades.proyecto.Proyecto;
import entidades.proyecto.Rol;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.UnidadInvestigacion;
import entidades.proyecto.resultado.ArticuloRevista;
import entidades.proyecto.resultado.CapituloLibro;
import entidades.proyecto.resultado.Congreso;
import entidades.proyecto.resultado.Contrato;
import entidades.proyecto.resultado.FormacionRRHH;
import entidades.proyecto.resultado.Industrial;
import entidades.proyecto.resultado.Intelectual;
import entidades.proyecto.resultado.Libro;
import entidades.proyecto.vinculacion.ParticipacionVinculacion;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import includes.Comunes;
import includes.ExportarExcel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class ProyectoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ProyectoJpaController proyectoJpaController = new ProyectoJpaController(emf);
    private static ProyectoFacade instance = null;

    protected ProyectoFacade() {
    }

    public static ProyectoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProyectoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Proyecto proyecto) {
        new ProyectoJpaController(emf).create(proyecto);
    }

    public void modificar(Proyecto proyecto) {
        try {
            new ProyectoJpaController(emf).edit(proyecto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * public void eliminar(Proyecto proyecto) { try { new
     * ProyectoJpaController(emf).destroy(proyecto.getId()); } catch
     * (NonexistentEntityException ex) {
     * Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null,
     * ex); } }
     */
    public Proyecto getUltimoProyecto() {
        Query quProyecto = em.createQuery("SELECT p FROM Proyecto p");
        List<Proyecto> proyecto = quProyecto.getResultList();
        if (!proyecto.isEmpty()) {
            return proyecto.get(proyecto.size() - 1);
        } else {
            return null;
        }
    }

    public List<Proyecto> buscar(String titulo) {
        Query quBuscarProyecto = em.createQuery("SELECT p FROM Proyecto p "
                + "WHERE p.titulo like :titulo "
                + "OR p.codigo like :titulo "
                + "OR p.codigoIncentivos like :titulo "
                + "OR p.resumen like :titulo ");
        quBuscarProyecto.setParameter("titulo", "%" + titulo + "%");
        return quBuscarProyecto.getResultList();
    }

    public Proyecto buscarPorCodigo(String codigo) {
        try {
            Query quBuscarProyecto = em.createQuery("SELECT p FROM Proyecto p "
                    + "WHERE p.codigo =   '" + codigo + "'");
            Proyecto proyecto = (Proyecto) quBuscarProyecto.getSingleResult();
            return proyecto;
        } catch (Exception ex) {
            return null;
        }
    }

    public Proyecto buscarPorCodigoIncentivo(String codigo) {
        try {
            Query quBuscarProyecto = em.createQuery("SELECT p FROM Proyecto p "
                    + "WHERE p.codigoIncentivos =   '" + codigo + "'");
            Proyecto proyecto = (Proyecto) quBuscarProyecto.getSingleResult();
            return proyecto;
        } catch (Exception ex) {
            return null;
        }
    }

    public Proyecto getproyecto(String titulo) {
        Query quBuscarProyecto = em.createQuery("SELECT p.titulo FROM Proyecto p "
                + "WHERE p.titulo = '" + titulo + "'");
        return (Proyecto) quBuscarProyecto.getSingleResult();
    }

    public List<Proyecto> getproyectos() {
        Query quBuscarProyecto = em.createQuery("SELECT p.titulo FROM Proyecto p ");

        return quBuscarProyecto.getResultList();
    }

    public List<Proyecto> getTodos() {
        return new ProyectoJpaController(emf).findProyectoEntities();
    }

    public List<Proyecto> getCienPrimeros() {
        Query quProyecto = em.createQuery("SELECT p FROM Proyecto p").setMaxResults(50);
        return quProyecto.getResultList();
    }

    public List<Investigador> getTodosIntegrantes(Proyecto proyecto) {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa "
                + "WHERE pa.proyecto.id=" + proyecto.getId());
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (Participacion participacion : participaciones) {
            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());
            }
        }
        return integrantes;
    }

    public List<Participacion> getTodosIntegrantesSinDirector(Proyecto proyecto) {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa "
                + "WHERE pa.proyecto.id=" + proyecto.getId());
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Participacion> listasindirector = new ArrayList<Participacion>();
        for (Participacion participacion : participaciones) {
            if (!listasindirector.contains(participacion) && !participacion.getRol().getDescripcion().equals("Director")) {
                listasindirector.add(participacion);
            }
        }
        return listasindirector;
    }

    public boolean TieneWinsip(Proyecto proyecto) {
        Query quWinsip = em.createQuery("SELECT w FROM Winsip w "
                + "WHERE w.proyecto.id=" + proyecto.getId());
        List<Winsip> winsips = quWinsip.getResultList();
        if (winsips.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<Investigador> getTodosIntegrantesActuales(Proyecto proyecto) {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa "
                + "WHERE pa.fechaHasta  IS NULL AND  pa.proyecto.id=" + proyecto.getId());
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (Participacion participacion : participaciones) {

            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());

            }
        }
        return integrantes;
    }

    public List<Participacion> getParticipaciones(Investigador investigador, Proyecto proyecto) {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa "
                + "WHERE pa.investigador.id=" + investigador.getId()
                + " AND pa.proyecto.id=" + proyecto.getId());
        return quParticipaciones.getResultList();
    }

    public List<Participacion> getParticipacionesTodas() {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa ");

        return quParticipaciones.getResultList();
    }

    public Participacion getUltimaParticipacion(Investigador investigador, Proyecto proyecto) {
        List<Participacion> participaciones = getParticipaciones(investigador, proyecto);
        // TODO: Agregar la condición que también puede ser última participación si la fechaHasta = fechaFin del Proyecto
        Query quUltimaParticipacion = em.createQuery("SELECT pa FROM Participacion pa "
                + "WHERE pa.fechaHasta=null AND pa MEMBER OF " + participaciones);
        return (Participacion) quUltimaParticipacion.getSingleResult();
    }

    public Date getFechaActualFinalizacion(Proyecto proyecto) {
        if (proyecto.getProrrogas().isEmpty()) {
            return proyecto.getFechaFinalizacion();
        } else {
            return proyecto.getProrrogas().get(proyecto.getProrrogas().size() - 1).getFecha();
        }
    }

    public boolean isActualmenteVigente(Participacion participacion) {
        if (getFechaActualFinalizacion(participacion.getProyecto()) == participacion.getFechaHasta()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDirectorActualmente(Investigador investigador, Proyecto proyecto) {
        boolean flag = false;
        Participacion participacion = getUltimaParticipacion(investigador, proyecto);
        if (isActualmenteVigente(participacion)) {
            if (participacion.getRol().getDescripcion().equals("Director")) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isCoDirectorActualmente(Investigador investigador, Proyecto proyecto) {
        boolean flag = false;
        Participacion participacion = getUltimaParticipacion(investigador, proyecto);
        if (isActualmenteVigente(participacion)) {
            if (participacion.getRol().getDescripcion().equals("Co-Director")) {
                flag = true;
            }
        }
        return flag;
    }

    public void agregarParticipacion(Investigador investigador, Proyecto proyecto,
            Date fechaDesde, Date fechaHasta, Rol rol, int dedicacionSemanal) {
        Participacion participacion = new Participacion();
        participacion.setFechaDesde(fechaDesde);
        participacion.setFechaHasta(fechaHasta);
        participacion.setInvestigador(investigador);
        participacion.setProyecto(proyecto);
        participacion.setRol(rol);
        participacion.setDedicacionSemanal(dedicacionSemanal);
        new ParticipacionJpaController(emf).create(participacion);
    }

    public void agregarParticipacion(Investigador investigador, Proyecto proyecto,
            Date fechaDesde, Date fechaHasta, Rol rol, int dedicacionSemanal,
            String resolucionAlta, String resolucionBaja) {
        Participacion participacion = new Participacion();
        participacion.setFechaDesde(fechaDesde);
        participacion.setFechaHasta(fechaHasta);
        participacion.setInvestigador(investigador);
        participacion.setProyecto(proyecto);
        participacion.setRol(rol);
        participacion.setDedicacionSemanal(dedicacionSemanal);
        participacion.setResolucionAlta(resolucionAlta);
        participacion.setResolucionBaja(resolucionBaja);
        new ParticipacionJpaController(emf).create(participacion);
    }

    public void eliminarParticipacion(Participacion participacion) {
        try {
            participacion.getProyecto().getParticipaciones().remove(participacion);
            try {
                new ProyectoJpaController(emf).edit(participacion.getProyecto());
            } catch (Exception ex) {
                Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            new ParticipacionJpaController(emf).destroy(participacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarTodasParticipaciones(Investigador investigador, Proyecto proyecto) {
        List<Participacion> participaciones = getParticipaciones(investigador, proyecto);
        ParticipacionJpaController participacionJpaController = new ParticipacionJpaController(emf);
        for (Participacion participacion : participaciones) {
            try {
                participacionJpaController.destroy(participacion.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean existeCodigo(String codigo) {
        boolean flag = false;
        if (!"".equals(codigo)) {
            Proyecto proyecto = buscarPorCodigo(codigo);
            if (proyecto != null) {
                flag = true;
            }
        }
        return flag;
    }

    public List<Proyecto> proyectoxTexto(String cadena) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.titulo LIKE :tit OR p.codigo LIKE :cod");
        quProyectos.setParameter("tit", "%" + cadena + "%");
        quProyectos.setParameter("cod", "%" + cadena + "%");
        return quProyectos.getResultList();
    }

    public List<Proyecto> ProyectoxDirector(String nombre, String cargo) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p, IN (p.participaciones) par WHERE "
                + "par.rol.descripcion LIKE :car AND par.investigador.persona.apellido LIKE :nom "
                + "AND (par.fechaHasta IS NULL OR par.fechaHasta > :fecha )");
        quProyectos.setParameter("fecha", Comunes.obtenerFechaActualDesdeDB());
        quProyectos.setParameter("nom", nombre);
        quProyectos.setParameter("car", cargo);
        return quProyectos.getResultList();
    }

    public void exportarAExcelInvestigadoresProyecto() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();

        lista.add("Apellido y Nombre|Rol| Desde | Hasta | Cod Incentivos|Titulo|Fecha Inicio|Fecha Fin|Prorrogas");
        List<Participacion> todos = new ParticipacionFacade().getTodos();
        for (Participacion p : todos) {
            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(p.getInvestigador().getPersona().getApellido());
                stringBuider.append(" ");
                stringBuider.append(p.getInvestigador().getPersona().getNombre());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            stringBuider.append(p.getRol().getDescripcion());
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaDesde()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaHasta()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getProyecto().getCodigoIncentivos());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getProyecto().getTitulo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {

                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getProyecto().getFechaInicio()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getProyecto().getFechaFinalizacion()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Prorroga pro : p.getProyecto().getProrrogas()) {
                    if (p.getProyecto().getProrrogas() != null) {
                        stringBuider.append(pro);
                        stringBuider.append(" ");
                    } else {
                        stringBuider.append(" ");
                    }
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Investigadores en Proyectos");

    }

    public void exportarAExcel() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();

        lista.add("Cod Incentivos|Unidad Académica|Duracion Teórica|Linea de Investigación|Unidad Ejecutora|Tipo de Proyecto|Entidad evaluadora|Subdisciplina Científica|Resumén|Palabras Claves");
        List<Proyecto> todos = new ProyectoFacade().getTodos();
        for (Proyecto p : todos) {
            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(p.getCodigoIncentivos());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getUnidadAcademica().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getDuracionTeorica());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getLineaInvestigacion().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getUnidadEjecutora().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }

            stringBuider.append("|");
            try {
                stringBuider.append(p.getTipoProyecto().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getEntidadEvaluadora().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }

            stringBuider.append(
                    "|");
            try {
                stringBuider.append(p.getEntidadEvaluadora().getDescripcion());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (SubDisciplinaCientifica s : p.getSubDisciplinasCientificas()) {
                    stringBuider.append(s.getDescripcion());
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getResumen());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (String s : p.getPalabrasClaves()) {
                    stringBuider.append(s);
                    stringBuider.append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");

            stringBuider.append(
                    "|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Investigadores en Proyectos");

    }

    public void exportarAExcelProrrogas() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();
        lista.add("Codigo de Proyecto|Titulo de Proyecto|Fecha de Inicio|Fecha de Finalizacion|"
                + "Fecha de Prorroga(1ra)|Fecha de Prorroga(2da)|Fecha de Prorroga(3ra)|Director|"
                + "Participacion en proyectos del Director|Unidad Academica|Co-director|"
                + "Participacion en proyectos del Co-director|Integrantes|Participaciones de los integrantes");
        List<Proyecto> todos = new ProyectoFacade().getTodos();
        for (Proyecto p : todos) {
            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(p.getCodigoIncentivos());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getTitulo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaInicio()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaFinalizacion()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            List<Prorroga> prorrogas = p.getProrrogas();
            Collections.sort(prorrogas);
            try {
                if (!prorrogas.isEmpty()) {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(prorrogas.get(0).getFecha()));
                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            prorrogas = p.getProrrogas();
            Collections.sort(prorrogas);
            try {
                if (!prorrogas.isEmpty()) {
                    try {
                        stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(prorrogas.get(1).getFecha()));
                    } catch (Exception e) {
                        stringBuider.append(" ");
                    }

                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            prorrogas = p.getProrrogas();
            Collections.sort(prorrogas);
            try {
                if (!prorrogas.isEmpty()) {
                    try {
                        stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(prorrogas.get(2).getFecha()));
                    } catch (Exception e) {
                        stringBuider.append(" ");
                    }

                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                ParticipacionFacade partFacade = new ParticipacionFacade();
                stringBuider.append(partFacade.getDirector(p).getInvestigador());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                ParticipacionFacade partFacade = new ParticipacionFacade();
                for (Participacion participacion : partFacade.getDirector(p).getInvestigador().getParticipacionesProyecto()) {
                    stringBuider.append(participacion);
                    stringBuider.append(" / ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getUnidadAcademica());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                ParticipacionFacade partFacade = new ParticipacionFacade();
                stringBuider.append(partFacade.getCoDirector(p).getInvestigador());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                ParticipacionFacade partFacade = new ParticipacionFacade();
                for (Participacion participacion : partFacade.getCoDirector(p).getInvestigador().getParticipacionesProyecto()) {
                    stringBuider.append(participacion);
                    stringBuider.append(" ; ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Participacion part : p.getParticipaciones()) {

                    stringBuider.append(part.getInvestigador());
                    stringBuider.append(" ; ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Participacion part : p.getParticipaciones()) {

                    stringBuider.append(part);
                    stringBuider.append(" ; ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Proyectos prorroga");

    }

    public void exportarAExcelInvProySubdisc() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|ROL|APELLIDO Y NOMBRE|CATEGORIA 2010|AREAS TEMATICAS PROY|DISCIPLINAS CIENTIFICAS PROY|SUBDISCIPLINAS CIENTIFICAS PROY|UNIDAD ACADEMICA|LINEA PRIORITARIA|TIPO DE ACTIVIDADES|SECTORES PRIORITARIOS|OBJETIVO SOCIOECONOMICO|RESUMEN|FA GRADO|FA POSGRADO|FA OTRA|DEDICACION DOCENTE 2015|SEXO|FECHA NACIMIENTO|EDAD");
        List<Participacion> todos = new ParticipacionFacade().getTodos();
        for (Participacion p : todos) {
            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(p.getProyecto().getCodigoIncentivos());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getProyecto().getTitulo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getProyecto().getFechaInicio()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getProyecto().getFechaFinalizacion()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            stringBuider.append(p.getRol().getDescripcion());
            stringBuider.append("|");
            try {
                stringBuider.append(p.getInvestigador().getPersona().getApellido());
                stringBuider.append(" ");
                stringBuider.append(p.getInvestigador().getPersona().getNombre());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");

            try {
                String strCategoria = " ";
                for (Categorizacion cat : p.getInvestigador().getCategorizaciones()) {
                    if (cat.getLlamado().getId() == 6L) {
                        strCategoria = cat.getCategoriaAsignada().getValorCategoria().getDescripcion();
                    }
                }
                stringBuider.append(strCategoria);
            } catch (Exception ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                if (!p.getProyecto().getSubDisciplinasCientificas().isEmpty()) {
                    for (SubDisciplinaCientifica s : p.getProyecto().getSubDisciplinasCientificas()) {
                        stringBuider.append(s.getDisciplinaCientifica().getAreaTematica().getDescripcion()).append(";");
                    }
                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                if (!p.getProyecto().getSubDisciplinasCientificas().isEmpty()) {
                    for (SubDisciplinaCientifica s : p.getProyecto().getSubDisciplinasCientificas()) {
                        stringBuider.append(s.getDisciplinaCientifica().getDescripcion()).append(";");
                    }
                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (SubDisciplinaCientifica s : p.getProyecto().getSubDisciplinasCientificas()) {
                    stringBuider.append(s.getDescripcion()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getProyecto().getUnidadAcademica().toString());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append("wWWWWW ");
            }
            stringBuider.append("|");
            try {
                if (p.getProyecto().getLineaInvestigacion() != null) {
                    stringBuider.append(p.getProyecto().getLineaInvestigacion());
                } else {
                    stringBuider.append(" RRRRR");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                //LINEA PRIORITARIA
                if (p.getProyecto().getTipoProyecto().getId() != null) {
                    stringBuider.append(p.getProyecto().getTipoProyecto());
                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                //SECTOR PRIORITARIO
                if (!p.getProyecto().getEspecialidades().isEmpty()) {
                    for (Especialidad e : p.getProyecto().getEspecialidades()) {
                        stringBuider.append(e.getDescripcion()).append(" ");
                    }
                } else {
                    stringBuider.append(" EEEE");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                if (p.getProyecto().getObjetivoSocioeconomico().getId() != null) {
                    stringBuider.append(p.getProyecto().getObjetivoSocioeconomico());
                } else {
                    stringBuider.append("XXX ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getProyecto().getResumen());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                if (!p.getInvestigador().getFormacionesAcademicasGrado().isEmpty()) {
                    for (FormacionAcademicaGrado s : p.getInvestigador().getFormacionesAcademicasGrado()) {
                        stringBuider.append(s).append("; ");
                    }
                } else {
                    stringBuider.append(" ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (FormacionAcademicaPosgrado s : p.getInvestigador().getFormacionesAcademicasPosgrado()) {
                    stringBuider.append(s).append("; ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (FormacionAcademicaOtra s : p.getInvestigador().getFormacionesAcademicasOtras()) {
                    stringBuider.append(s).append("; ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Docencia d : p.getInvestigador().getDocencias()) {
                    if (d.getAño() == 2015) {
                        stringBuider.append(d);
                    } else {
                        stringBuider.append(" ");
                    }

                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getInvestigador().getPersona().getSexo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getInvestigador().getPersona().getFechaNacimiento()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getInvestigador().getPersona().getFechaNacimiento());
                stringBuider.append(Comunes.calcularEdad(cal));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Investigadores en Proyectos");

    }

    public void exportarAExcelProyectosLibros() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|TITULO LIBRO|ENVIADO|ACEPTADO|PUBLICADO|AÑO|ISBN");
        List<Libro> libros = PublicacionFacade.getInstance().getLibros();
        for (Libro libro : libros) {
            for (Proyecto proyecto : libro.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(libro.getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(libro.getFechaEnviado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(libro.getFechaAceptado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(libro.getFechaPublicado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(libro.getAnioPublicacion());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(libro.getISBN());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Libros en Proyectos");

    }

    public void exportarAExcelProyectosCapitulosLibros() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|TITULO CAPITULO|LIBRO|ENVIADO|ACEPTADO|PUBLICADO|AÑO|ISBN");
        List<CapituloLibro> capitulosLibro = PublicacionFacade.getInstance().getCapitulosLibro();
        for (CapituloLibro capituloLibro : capitulosLibro) {
            for (Proyecto proyecto : capituloLibro.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(capituloLibro.getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(capituloLibro.getNombreLibro());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                try {
                    stringBuider.append(capituloLibro.getLibro().getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(capituloLibro.getFechaEnviado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(capituloLibro.getFechaAceptado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(capituloLibro.getFechaPublicado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(capituloLibro.getAnioPublicacion());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(capituloLibro.getISBN());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Capitulos de Libros en Proyectos");

    }

    public void exportarAExcelProyectosArticulosRevista() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|TITULO ARTICULO|NOMBRE REVISTA|PAG. INICIAL|PAG. FINAL|ENVIADO|ACEPTADO|PUBLICADO|AÑO|ISBN");
        List<ArticuloRevista> articuloRevistas = PublicacionFacade.getInstance().getArticulosRevista();
        for (ArticuloRevista articuloRevista : articuloRevistas) {
            for (Proyecto proyecto : articuloRevista.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(articuloRevista.getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(articuloRevista.getNombreRevista());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {
                    stringBuider.append(articuloRevista.getPaginaInicial());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(articuloRevista.getPaginaFinal());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(articuloRevista.getFechaEnviado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(articuloRevista.getFechaAceptado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(articuloRevista.getFechaPublicado()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(articuloRevista.getAnioEdicion());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(articuloRevista.getISBN());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Articulos en Revistas de Proyectos");

    }

    public void exportarAExcelProyectosCongresos() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|TEMA|NOMBRE EVENTO|CARACTER|FECHA|PUBLICADO EN ACTAS|REFERATO|VOLUMEN|PAGINA|AÑO");
        List<Congreso> congresos = PublicacionFacade.getInstance().getCongresos();
        for (Congreso congreso : congresos) {
            for (Proyecto proyecto : congreso.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(congreso.getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(congreso.getNombreEvento());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {
                    stringBuider.append(congreso.getCaracter().toString());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(congreso.getFecha()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(congreso.getPublicadoEnActas().toString());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(congreso.getReferato().toString());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(congreso.getVolumen());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(congreso.getPagina());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(congreso.getAnio());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Congresos en Proyectos");

    }

    public void exportarAExcelProyectosContratos() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|OBJETO/TEMA|TIPO CONTRATO|DESTINATARIO|MONTO OBTENIDO|FECHA");
        List<Contrato> contratos = ContratoFacade.getInstance().getTodas();
        for (Contrato contrato : contratos) {
            for (Proyecto proyecto : contrato.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(contrato.getObjeto());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(contrato.getTipoContrato().toString());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {
                    stringBuider.append(contrato.getDestinatarios());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(contrato.getMontoObtenido());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(contrato.getFechaContrato()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }

                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Contratos en Proyectos");

    }

    public void exportarAExcelProyectosIntelectuales() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|TITULO|TIPO REGISTRO|TITULAR REGISTRO|REGISTRO N°|FECHA|PAIS");
        List<Intelectual> intelectuales = PropiedadFacade.getInstance().getIntelectuales();
        for (Intelectual intelectual : intelectuales) {
            for (Proyecto proyecto : intelectual.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(intelectual.getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(intelectual.getTipoRegistroIntelectual().toString());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {
                    stringBuider.append(intelectual.getTitulares());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(intelectual.getNroRegistro());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(intelectual.getFecha()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }

                stringBuider.append("|");
                try {

                    stringBuider.append(intelectual.getPais());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Propiedad Intelectual en Proyectos");

    }

    public void exportarAExcelProyectosIndustriales() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|TITULO|TIPO REGISTRO|TITULAR REGISTRO|REGISTRO N°|FECHA|PAIS");
        List<Industrial> industriales = PropiedadFacade.getInstance().getIndustriales();
        for (Industrial industrial : industriales) {
            for (Proyecto proyecto : industrial.getProyectos()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(industrial.getTitulo());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(industrial.getTipoRegistroIndustrial().toString());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {
                    stringBuider.append(industrial.getTitulares());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(industrial.getNroRegistro());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(industrial.getFecha()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }

                stringBuider.append("|");
                try {

                    stringBuider.append(industrial.getPais());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Propiedad Industrial en Proyectos");

    }

    public void exportarAExcelProyectosRRHH() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|DIRECTOR|BECARIOS ALUMNOS|BECARIOS GRADUADOS|TESIS DIRIGIDAS|APROB. DE MAESTRIA|APROB. DE DOCTORADO|TESINAS APROB.|FECHA");
        List<Proyecto> proyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : proyectos) {
            for (FormacionRRHH formacionRRHH : proyecto.getFormacionRRHHs()) {
                StringBuilder stringBuider = new StringBuilder();
                try {
                    stringBuider.append(proyecto.getCodigoIncentivos());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(proyecto.getTitulo());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    for (Prorroga prorroga : proyecto.getProrrogas()) {
                        stringBuider.append(prorroga.toString()).append(" ");
                    }
                } catch (java.lang.NullPointerException ex) {
                }
                stringBuider.append(" |");
                try {
                    stringBuider.append(new ParticipacionFacade().getDirector(proyecto).getInvestigador().toString());
                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {
                    stringBuider.append(formacionRRHH.getBecariosAlumnos());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");

                try {
                    stringBuider.append(formacionRRHH.getBecariosGraduados());

                } catch (java.lang.NullPointerException ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(formacionRRHH.getTesisDirigidas());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }

                stringBuider.append("|");
                try {

                    stringBuider.append(formacionRRHH.getTesisAprobadasMaestria());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(formacionRRHH.getTesisAprobadasDoctorado());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(formacionRRHH.getTesinasGradoAprobadas());
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                try {

                    stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(formacionRRHH.getFecha()));
                } catch (Exception ex) {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el ficher
        new ExportarExcel().crearExcel(lista, "Formacion de RRHH en Proyectos");

    }

    public void exportarAExcelLocalizacionProyecto() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("FACULTAD|CODIGO INCENTIVOS|NOMBRE PROYECTO|INTEGRANTES|FECHA INICIO|FECHA FINAL|PRORROGAS|AREA TEMÁTICA|DISCIPLINA CIENTIFICA|SUBDISCIPLINA CIENTIFICA|CAMPO DE APLICACION|LOCALIZACION DEL PROYECTO");
        List<Proyecto> proyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : proyectos) {

            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(proyecto.getUnidadAcademica());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(proyecto.getCodigoIncentivos());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(proyecto.getTitulo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                if (!proyecto.getParticipaciones().isEmpty()) {
                    for (Participacion part : proyecto.getParticipaciones()) {

                        stringBuider.append(part.getInvestigador());
                        stringBuider.append(" ");
                    }
                } else {
                    stringBuider.append("  ");
                }

            } catch (java.lang.NullPointerException ex) {
                stringBuider.append("  ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Prorroga prorroga : proyecto.getProrrogas()) {
                    stringBuider.append(prorroga.toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }
            stringBuider.append(" |");
            try {
                for (SubDisciplinaCientifica subDisciplinaCientifica : proyecto.getSubDisciplinasCientificas()) {
                    stringBuider.append(subDisciplinaCientifica.getDisciplinaCientifica().getAreaTematica().toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }
            stringBuider.append(" |");
            try {
                for (SubDisciplinaCientifica subDisciplinaCientifica : proyecto.getSubDisciplinasCientificas()) {
                    stringBuider.append(subDisciplinaCientifica.getDisciplinaCientifica().toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }
            stringBuider.append(" |");
            try {
                for (SubDisciplinaCientifica subDisciplinaCientifica : proyecto.getSubDisciplinasCientificas()) {
                    stringBuider.append(subDisciplinaCientifica.toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }
            stringBuider.append(" |");
            try {
                for (CampoAplicacion campoAplicacion : proyecto.getCamposAplicacion()) {
                    stringBuider.append(campoAplicacion.toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }
            stringBuider.append(" |");
            try {
                for (UnidadInvestigacion unidadInvestigacion : proyecto.getUnidadesInvestigacion()) {
                    stringBuider.append(unidadInvestigacion.toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }

            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Localizacion de Proyectos");

    }

    public void exportarAExcelProyectosDepto() {
        //Datos a escribir

        List<String> lista = new ArrayList<>();
        lista.add("CODIGO INCENTIVOS|NOMBRE PROYECTO|FECHA INICIO PROY|FECHA FINAL PROYECTO|PRORROGAS|DIRECTOR|AREA TEMATICA|LOCALIZACION FISICA");
        List<Proyecto> proyectos = ProyectoFacade.getInstance().getTodos();
        for (Proyecto proyecto : proyectos) {
            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(proyecto.getCodigoIncentivos());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(proyecto.getTitulo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaInicio()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFechaFinalizacion()));
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Prorroga prorroga : proyecto.getProrrogas()) {
                    stringBuider.append(prorroga.toString()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
            }
            stringBuider.append(" |");
            try {
                stringBuider.append(new ParticipacionFacade().getDirector(proyecto).getInvestigador().toString());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (SubDisciplinaCientifica s : proyecto.getSubDisciplinasCientificas()) {
                    stringBuider.append(s.getDisciplinaCientifica().getAreaTematica().getDescripcion()).append(" ");
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append(" |");
            try {
                stringBuider.append(proyecto.getProyectoDatosComplementarios().getLocalizacionOtra());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Formacion de RRHH en Proyectos");

    }

    //crea la ventana para exportar a excel
    public void exportarAExcelInvProyVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelInvestigadoresProyecto", "Preparandose para Exportar a Excel", null);
    }
    //crea la ventana para exportar a excel

    public void exportarAExcelVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcel", "Preparandose para Exportar a Excel", null);
    }
    //crea la ventana para exportar a excel

    public void exportarAExcelInvProySubVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelInvProySubdisc", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosLibrosVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosLibros", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosCapitulosVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosCapitulosLibros", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosArticulosVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosArticulosRevista", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosCongresosVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosCongresos", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosContratosVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosContratos", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyIntelectualesVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosIntelectuales", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyIndustrialesVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosIndustriales", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosRRHHVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosRRHH", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProrrogasVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProrrogas", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelLocalizacionProyectoVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelLocalizacionProyecto", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosDeptoVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosDepto", "Preparandose para Exportar a Excel", null);
    }

    public List<Proyecto> getproyectosXLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.lineaInvestigacion=:lineaInvestigacion");
        quProyectos.setParameter("lineaInvestigacion", lineaInvestigacion);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXTipoProyecto(TipoProyecto tipoProyecto) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.tipoProyecto=:tipoProyecto");
        quProyectos.setParameter("tipoProyecto", tipoProyecto);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXEntidadEvaluadora(EntidadEvaluadora entidadEvaluadora) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.entidadEvaluadora=:entidadEvaluadora");
        quProyectos.setParameter("entidadEvaluadora", entidadEvaluadora);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXUnidadEjecutora(UnidadEjecutora unidadEjecutora) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.unidadEjecutora=:unidadEjecutora");
        quProyectos.setParameter("unidadEjecutora", unidadEjecutora);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXPrograma(Programa programa) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.programa=:programa");
        quProyectos.setParameter("programa", programa);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXObjetivoSE(ObjetivoSocioeconomico objetivoSocioeconomico) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.objetivoSocioeconomico=:objetivoSocioeconomico");
        quProyectos.setParameter("objetivoSocioeconomico", objetivoSocioeconomico);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXSubdisciplinaSE(SubDisciplinaCientifica subdisciplina) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.subDisciplinasCientificas=:subdisciplina");
        quProyectos.setParameter("subdisciplina", subdisciplina);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXDisciplina(DisciplinaCientifica disciplina) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.disciplina=:disciplina");
        quProyectos.setParameter("subdisciplina", disciplina);
        return quProyectos.getResultList();
    }

    public List<Proyecto> getproyectosXUnidadAcademica(UnidadAcademica unidadAcademica) {
        Query quProyectos = em.createQuery("SELECT p FROM Proyecto p WHERE "
                + "p.unidadAcademica=:unidadAcademica");
        quProyectos.setParameter("unidadAcademica", unidadAcademica);
        return quProyectos.getResultList();
    }

    public List<String[]> getproyectosXDirectorAltaBaja() {
        Query quProyectos = em.createQuery("SELECT p.unidadAcademica.descripcion , pDirector.investigador.persona.apellido  FROM Proyecto p, IN(p.participaciones) pDirector, IN (p.participaciones) pBaja WHERE pdirector.rol.descripcion=:director AND pBaja.fechaHasta<:fecha");
        quProyectos.setParameter("director", "Director");
        quProyectos.setParameter("fecha", Comunes.obtenerFechaActualDesdeDB());
        List<String[]> result = quProyectos.getResultList();
        return result;
    }

    public Proyecto importarProyectoVinculación(ProyectoVinculacion proyecto) {
        int confirmar = JOptionPane.showConfirmDialog(null, "Desea importar el Proyecto: \n" + proyecto.getTitulo(), "Importar", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_NO_OPTION) {
            try {
                Proyecto proyecto1 = new Proyecto();
                try {
                    proyecto1.setTitulo(proyecto.getTitulo());
                } catch (Exception e) {
                }
                try {
                    if (ProyectoFacade.getInstance().buscarPorCodigo(proyecto.getCodigo()) == null) {
                        proyecto1.setCodigo(proyecto.getCodigo());
                    } else {
                        proyecto1.setCodigo(proyecto.getCodigo() + "1");
                    }

                } catch (Exception e) {
                }
                try {
                    if (ProyectoFacade.getInstance().buscarPorCodigoIncentivo(proyecto.getNroConvenio()) == null) {
                        proyecto1.setCodigoIncentivos(proyecto.getNroConvenio());
                    } else {
                        proyecto1.setCodigoIncentivos(proyecto.getNroConvenio() + "1");
                    }

                } catch (Exception e) {
                }
                try {
                    proyecto1.setUnidadAcademica(proyecto.getUnidadAcademica());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setUnidadEjecutora(proyecto.getUnidadEjecutora());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setDuracionTeorica(proyecto.getDuracionTeorica());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setFechaInicio(proyecto.getFechaInicio());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setFechaFinalizacion(proyecto.getFechaFinalizacion());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setProrrogas(proyecto.getProrrogas());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setLineaInvestigacion(proyecto.getLineaInvestigacion());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setEntidadEvaluadora(proyecto.getEntidadEvaluadora());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setSubDisciplinasCientificas(proyecto.getSubDisciplinasCientificas());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setResumen(proyecto.getResumen());
                } catch (Exception e) {
                }
                try {
                    proyecto1.setResumen(proyecto.getResumen());
                } catch (Exception e) {
                }
                ParticipacionFacade participacionFacade = new ParticipacionFacade();

                if (!proyecto.getParticipaciones().isEmpty()) {
                    List<Participacion> participaciones = new ArrayList<>();
                    Participacion participacion = new Participacion();
                    for (ParticipacionVinculacion pw : proyecto.getParticipaciones()) {
                        try {
                            participacion.setDedicacionSemanal(pw.getDedicacionSemanal());
                            participacion.setFechaDesde(pw.getFechaDesde());
                            participacion.setFechaHasta(pw.getFechaHasta());
                            participacion.setInvestigador(pw.getInvestigador());
                            participacion.setProyecto(proyecto1);
                            participacion.setRol(pw.getRol());
                            participacionFacade.alta(participacion);
                            participaciones.add(participacion);
                        } catch (Exception e) {
                        }

                    }
                    try {
                        proyecto1.setParticipaciones(participaciones);
                    } catch (Exception e) {
                    }

                }
                //AGREGADA ṔOR HUGO 
                ProyectoFacade.getInstance().alta(proyecto1);
                JOptionPane.showMessageDialog(null, "Se importó correctamente el proyecto \n ");
                return proyecto1;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error, No se pudo importar el proyecto seleccionado \n " + ex);
            }

        }
        return null;
    }

}
