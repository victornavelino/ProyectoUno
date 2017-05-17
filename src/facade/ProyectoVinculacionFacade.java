/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ParticipacionVinculacionJpaController;
import controladores.ProyectoVinculacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.UnidadEjecutora;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.ObjetivoSocioeconomico;
import entidades.proyecto.Programa;
import entidades.proyecto.Rol;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.ParticipacionVinculacion;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import includes.Comunes;
import includes.ExportarExcel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Panchi
 */
public class ProyectoVinculacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ProyectoVinculacionJpaController proyectoVinculacionJpaController = new ProyectoVinculacionJpaController(emf);
    private static ProyectoVinculacionFacade instance = null;
    private Query quBuscarProyectoVinculacion;

    protected ProyectoVinculacionFacade() {
    }

    public static ProyectoVinculacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProyectoVinculacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ProyectoVinculacion proyectoVinculacion) {
        new ProyectoVinculacionJpaController(emf).create(proyectoVinculacion);
    }

    public void modificar(ProyectoVinculacion proyectoVinculacion) {
        try {
            new ProyectoVinculacionJpaController(emf).edit(proyectoVinculacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * public void eliminar(ProyectoVinculacion proyectoVinculacion) { try { new
     * ProyectoVinculacionJpaController(emf).destroy(proyectoVinculacion.getId()); } catch
     * (NonexistentEntityException ex) {
     * Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null,
     * ex); } }
     */
    public ProyectoVinculacion getUltimoProyectoVinculacion() {
        Query quProyectoVinculacion = em.createQuery("SELECT p FROM ProyectoVinculacion p");
        List<ProyectoVinculacion> proyectoVinculacion = quProyectoVinculacion.getResultList();
        if (!proyectoVinculacion.isEmpty()) {
            return proyectoVinculacion.get(proyectoVinculacion.size() - 1);
        } else {
            return null;
        }
    }

    public List<ProyectoVinculacion> buscar(String busqueda) {

        if (busqueda.length() < 4) {

            quBuscarProyectoVinculacion = em.createQuery("SELECT p FROM ProyectoVinculacion p "
                    + "WHERE p.titulo LIKE '" + busqueda + "%'" + "OR p.nroConvenio LIKE '" + busqueda + "%'");
        } else {
            quBuscarProyectoVinculacion = em.createQuery("SELECT p FROM ProyectoVinculacion p "
                    + "WHERE p.titulo LIKE '%" + busqueda + "%'" + "OR p.nroConvenio LIKE '%" + busqueda + "%'");

        }
        return quBuscarProyectoVinculacion.getResultList();
    }

    public List<ProyectoVinculacion> buscarPorParticipacion(String busqueda) {
        Query quBuscarPorNomOapeParticip = em.createQuery("select p from ProyectoVinculacion p, in (p.participaciones) par "
                + "   where par.investigador.persona.apellido like :var or "
                + " par.investigador.persona.nombre like :var ");
        quBuscarPorNomOapeParticip.setParameter("var", "%" + busqueda + "%");
        return quBuscarPorNomOapeParticip.getResultList();
    }

    public List<ProyectoVinculacion> buscarPorParticipacionInvestigador(Investigador investigador) {
        Query quBuscarPorNomOapeParticip = em.createQuery("select p from ProyectoVinculacion p, in (p.participaciones) par "
                + "   where par.investigador=:investigador");
        quBuscarPorNomOapeParticip.setParameter("investigador", investigador);
        return quBuscarPorNomOapeParticip.getResultList();
    }

    public List<ProyectoVinculacion> buscarPorAreaTematica(String busqueda) {
        Query quBuscarPorAreaTematica = em.createQuery("select distinct p from ProyectoVinculacion p, in (p.subDisciplinasCientificas) sub "
                + " where sub.disciplinaCientifica.areaTematica.descripcion like :var  ");
        quBuscarPorAreaTematica.setParameter("var", "%" + busqueda + "%");
        return quBuscarPorAreaTematica.getResultList();
    }

    public ProyectoVinculacion buscarPorCodigo(String codigo) {
        try {
            Query quBuscarProyectoVinculacion = em.createQuery("SELECT p FROM ProyectoVinculacion p "
                    + "WHERE p.codigo =   '" + codigo + "'");
            ProyectoVinculacion proyectoVinculacion = (ProyectoVinculacion) quBuscarProyectoVinculacion.getSingleResult();
            return proyectoVinculacion;
        } catch (Exception ex) {
            return null;
        }
    }

    public ProyectoVinculacion getproyectoVinculacion(String titulo) {
        quBuscarProyectoVinculacion = em.createQuery("SELECT p.titulo FROM ProyectoVinculacion p "
                + "WHERE p.titulo = '" + titulo + "'");
        return (ProyectoVinculacion) quBuscarProyectoVinculacion.getSingleResult();
    }

    public List<ProyectoVinculacion> getTitulosProyectoVinculacion() {
        quBuscarProyectoVinculacion = em.createQuery("SELECT p.titulo FROM ProyectoVinculacion p ");

        return quBuscarProyectoVinculacion.getResultList();
    }

    public List<ProyectoVinculacion> getTodos() {
        Query quTodos = em.createQuery("SELECT p FROM ProyectoVinculacion p");
        return quTodos.getResultList();
    }

    public List<ProyectoVinculacion> filtradoPorTipo(String tipo) {
        Query query = em.createQuery("select p from ProyectoVinculacion p, Financiacion f where f.descripcion=:var and f member of p.financiaciones");
        query.setParameter("var", tipo);
        return query.getResultList();
    }

    public List<Investigador> getTodosIntegrantes(ProyectoVinculacion proyectoVinculacion) {
        Query quParticipacionVinculaciones = em.createQuery("SELECT pa FROM ParticipacionVinculacion pa "
                + "WHERE pa.proyectoVinculacion.id=" + proyectoVinculacion.getId());
        List<ParticipacionVinculacion> participaciones = quParticipacionVinculaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (ParticipacionVinculacion participacion : participaciones) {
            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());
            }
        }
        return integrantes;
    }

    public List<Investigador> getTodosIntegrantesActuales(ProyectoVinculacion proyectoVinculacion) {
        Query quParticipacionVinculaciones = em.createQuery("SELECT pa FROM ParticipacionVinculacion pa "
                + "WHERE pa.fechaHasta  IS NULL AND  pa.proyectoVinculacion.id=" + proyectoVinculacion.getId());
        List<ParticipacionVinculacion> participaciones = quParticipacionVinculaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (ParticipacionVinculacion participacion : participaciones) {

            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());

            }
        }
        return integrantes;
    }

    public Investigador getDirector(ProyectoVinculacion proyectoVinculacion) {
        Query quInvestigador = em.createQuery("SELECT p.director FROM ProyectoVinculacion p "
                + "WHERE p.id=" + proyectoVinculacion.getId());
        return (Investigador) quInvestigador.getSingleResult();
    }

    public Investigador getCoDirector(ProyectoVinculacion proyectoVinculacion) {
        Query quInvestigador = em.createQuery("SELECT p.coDirector1 FROM ProyectoVinculacion p "
                + "WHERE p.id=" + proyectoVinculacion.getId());
        return (Investigador) quInvestigador.getSingleResult();
    }

    public List<Investigador> getTodosIntegrantesDirectorCoDirector(ProyectoVinculacion proyectoVinculacion) {
        List<Investigador> todosLosIntegrantesDirectorCoDirector = new ArrayList<Investigador>();
        todosLosIntegrantesDirectorCoDirector.add(getDirector(proyectoVinculacion));
        todosLosIntegrantesDirectorCoDirector.add(getCoDirector(proyectoVinculacion));
        todosLosIntegrantesDirectorCoDirector.addAll(getTodosIntegrantes(proyectoVinculacion));
        return todosLosIntegrantesDirectorCoDirector;
    }

    public List<ParticipacionVinculacion> getParticipacionVinculaciones(Investigador investigador, ProyectoVinculacion proyectoVinculacion) {
        Query quParticipacionVinculaciones = em.createQuery("SELECT pa FROM ParticipacionVinculacion pa "
                + "WHERE pa.investigador.id=" + investigador.getId()
                + " AND pa.proyectoVinculacion.id=" + proyectoVinculacion.getId());
        return quParticipacionVinculaciones.getResultList();
    }

    public List<ParticipacionVinculacion> getParticipacionVinculacionesActuales(ProyectoVinculacion proyectoVinculacion) {
        Query quParticipacionVinculaciones = em.createQuery("SELECT pa FROM ParticipacionVinculacion pa "
                + "WHERE pa.proyectoVinculacion.id=" + proyectoVinculacion.getId()
                + " AND (pa.fechaHasta=null OR pa.fechaHasta= :var )");
        quParticipacionVinculaciones.setParameter("var", proyectoVinculacion.getFechaFinalizacion());
        return quParticipacionVinculaciones.getResultList();
    }

    public List<ParticipacionVinculacion> getParticipacionVinculacionesTodas() {
        Query quParticipacionVinculaciones = em.createQuery("SELECT pa FROM ParticipacionVinculacion pa, ");

        return quParticipacionVinculaciones.getResultList();
    }

    public ParticipacionVinculacion getUltimaParticipacionVinculacion(Investigador investigador, ProyectoVinculacion proyectoVinculacion) {
        List<ParticipacionVinculacion> participaciones = getParticipacionVinculaciones(investigador, proyectoVinculacion);
        // TODO: Agregar la condición que también puede ser última participación si la fechaHasta = fechaFin del ProyectoVinculacion
        Query quUltimaParticipacionVinculacion = em.createQuery("SELECT pa FROM ParticipacionVinculacion pa "
                + "WHERE pa.fechaHasta=null AND pa MEMBER OF " + participaciones);
        return (ParticipacionVinculacion) quUltimaParticipacionVinculacion.getSingleResult();
    }

    public Date getFechaActualFinalizacion(ProyectoVinculacion proyectoVinculacion) {
        if (proyectoVinculacion.getProrrogas().isEmpty()) {
            return proyectoVinculacion.getFechaFinalizacion();
        } else {
            return proyectoVinculacion.getProrrogas().get(proyectoVinculacion.getProrrogas().size() - 1).getFecha();
        }
    }

    public boolean isActualmenteVigente(ParticipacionVinculacion participacion) {
        if (getFechaActualFinalizacion(participacion.getProyectoVinculacion()) == participacion.getFechaHasta()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDirectorActualmente(Investigador investigador, ProyectoVinculacion proyectoVinculacion) {
        boolean flag = false;
        ParticipacionVinculacion participacion = getUltimaParticipacionVinculacion(investigador, proyectoVinculacion);
        if (isActualmenteVigente(participacion)) {
            if (participacion.getRol().getDescripcion().equals("Director")) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isCoDirectorActualmente(Investigador investigador, ProyectoVinculacion proyectoVinculacion) {
        boolean flag = false;
        ParticipacionVinculacion participacion = getUltimaParticipacionVinculacion(investigador, proyectoVinculacion);
        if (isActualmenteVigente(participacion)) {
            if (participacion.getRol().getDescripcion().equals("Co-Director")) {
                flag = true;
            }
        }
        return flag;
    }

    public void agregarParticipacionVinculacion(Investigador investigador, ProyectoVinculacion proyectoVinculacion,
            Date fechaDesde, Date fechaHasta, Rol rol, int dedicacionSemanal) {
        ParticipacionVinculacion participacion = new ParticipacionVinculacion();
        participacion.setFechaDesde(fechaDesde);
        participacion.setFechaHasta(fechaHasta);
        participacion.setInvestigador(investigador);
        participacion.setProyectoVinculacion(proyectoVinculacion);
        participacion.setRol(rol);
        participacion.setDedicacionSemanal(dedicacionSemanal);
        new ParticipacionVinculacionJpaController(emf).create(participacion);
    }

    public void eliminarParticipacionVinculacion(ParticipacionVinculacion participacion) {
        try {
            participacion.getProyectoVinculacion().getParticipaciones().remove(participacion);
            try {
                new ProyectoVinculacionJpaController(emf).edit(participacion.getProyectoVinculacion());
            } catch (Exception ex) {
                Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            new ParticipacionVinculacionJpaController(emf).destroy(participacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarTodasParticipacionVinculaciones(Investigador investigador, ProyectoVinculacion proyectoVinculacion) {
        List<ParticipacionVinculacion> participaciones = getParticipacionVinculaciones(investigador, proyectoVinculacion);
        List<ParticipacionVinculacion> listaFinal = new ArrayList<>();
        List<ParticipacionVinculacion> participacionesfinales = proyectoVinculacion.getParticipaciones();
        for (ParticipacionVinculacion p3 : participacionesfinales) {
            // System.out.println(p3 + "" + p3.getId());
            for (ParticipacionVinculacion p2 : participaciones) {
                //     System.out.println(p2 + "" + p2.getId());
                if (!p3.equals(p2)) {
                    System.out.println("SI");
                    listaFinal.add(p3);
                }
            }
        }

        proyectoVinculacion.setParticipaciones(listaFinal);

        try {
            new ProyectoVinculacionJpaController(emf).edit(proyectoVinculacion);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

//        ParticipacionVinculacionJpaController participacionJpaController = new ParticipacionVinculacionJpaController(emf);
//        for (ParticipacionVinculacion participacion : participaciones) {
//            try {
//                participacionJpaController.destroy(participacion.getId());
//            } catch (NonexistentEntityException ex) {
//                Logger.getLogger(ProyectoVinculacionFacade.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public boolean existeCodigo(String codigo) {
        boolean flag = false;
        if (!"".equals(codigo)) {
            ProyectoVinculacion proyectoVinculacion = buscarPorCodigo(codigo);
            if (proyectoVinculacion != null) {
                flag = true;
            }
        }
        return flag;
    }

    public void eliminar(ProyectoVinculacion proyectoVinculacion) {
        try {
            new ProyectoVinculacionJpaController(emf).destroy(proyectoVinculacion.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(facade.FinanciacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List getproyectoVinculacionXEntidadEvaluadora(EntidadEvaluadora entidadEvaluadora) {
        Query query = em.createQuery("select p from ProyectoVinculacion p where p.entidadEvaluadora=:entidadEvaluadora");
        query.setParameter("entidadEvaluadora", entidadEvaluadora);
        return query.getResultList();
    }

    public List getproyectoVinculacionXLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        Query query = em.createQuery("select p from ProyectoVinculacion p where p.lineaInvestigacion=:lineaInvestigacion");
        query.setParameter("lineaInvestigacion", lineaInvestigacion);
        return query.getResultList();
    }

    public List getproyectoVinculacionXUnidadEjecutora(UnidadEjecutora unidadEjecutora) {
        Query query = em.createQuery("select p from ProyectoVinculacion p where p.unidadEjecutora=:unidadEjecutora");
        query.setParameter("unidadEjecutora", unidadEjecutora);
        return query.getResultList();
    }

    public List getproyectoVinculacionXPrograma(Programa programa) {
        Query query = em.createQuery("select p from ProyectoVinculacion p where p.programa=:programa");
        query.setParameter("programa", programa);
        return query.getResultList();
    }

    public void exportarAExcelProyectosUVT() {
        Comunes.ventanaCargando(this, "exportarAExcelProyectosVinculacion", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelProyectosVinculacion() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();

        lista.add("Código Proyecto|Nro de Convenio|Título|Area Temática|Disciplina Cientifica|Linea Financiamiento");
        List<ProyectoVinculacion> todos = new ProyectoVinculacionFacade().getTodos();
        for (ProyectoVinculacion p : todos) {
            StringBuilder stringBuider = new StringBuilder();
            try {
                stringBuider.append(p.getCodigo());
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                stringBuider.append(p.getNroConvenio());
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
                for (SubDisciplinaCientifica s : p.getSubDisciplinasCientificas()) {
                    try {
                        stringBuider.append(s.getDisciplinaCientifica().getAreaTematica().getDescripcion());
                    } catch (Exception e) {
                    }
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (SubDisciplinaCientifica s : p.getSubDisciplinasCientificas()) {
                    try {
                        stringBuider.append(s.getDisciplinaCientifica().getDescripcion());
                    } catch (Exception e) {
                    }
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            try {
                for (Financiacion f : p.getFinanciaciones()) {
                    try {
                        stringBuider.append(f.getDescripcion());
                    } catch (Exception e) {
                    }
                }
            } catch (java.lang.NullPointerException ex) {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Proyectos de Vinculacion");

    }
}
