/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.InvestigadorJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.DedicacionDocente;
import entidades.Universidad;
import entidades.categorizacion.Categorizacion;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.investigador.formacionAcademica.FormacionAcademicaOtra;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
import entidades.persona.CorreoElectronico;
import entidades.persona.Telefono;
import entidades.persona.TipoDocumento;
import entidades.persona.investigador.CategoriaDocente;
import entidades.persona.investigador.DepartamentoDocente;
import entidades.persona.investigador.Docencia;
import entidades.persona.investigador.EspecialidadActividadAcademica;
import entidades.persona.investigador.EspecialidadInvestigacion;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.ModoObtencionCargo;
import entidades.persona.investigador.actividadConduccion.ActividadConduccion;
import entidades.persona.investigador.actividadConduccion.CargoConduccion;
import entidades.persona.investigador.actividadConduccion.DedicacionConduccion;
import entidades.persona.investigador.curso.CarreraAsignatura;
import entidades.persona.investigador.curso.CursoDictado;
import entidades.persona.investigador.curso.TipoAsignatura;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
import entidades.proyecto.Participacion;
import entidades.titulo.TituloGrado;
import entidades.titulo.TituloOtro;
import entidades.titulo.TituloPosgrado;
import includes.Comunes;
import includes.ExportarExcel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.apache.commons.validator.routines.LongValidator;
import vista.diagInvestigadorBusquedaSimple;

/**
 *
 * @author Administrador
 */
public class InvestigadorFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    InvestigadorJpaController investigadorJpaController = new InvestigadorJpaController(emf);
    private static InvestigadorFacade instance = null;

    protected InvestigadorFacade() {
    }

    public static InvestigadorFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new InvestigadorFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Investigador investigador) {
        try {
            if (!existeInvestigadorConNumeroDocumentoIdentidad(investigador.getPersona().getDocumentoIdentidad().getTipoDocumento(),
                    investigador.getPersona().getDocumentoIdentidad().getNumero())) {
                new InvestigadorJpaController(emf).create(investigador);
            } else {
                System.out.println("Alta incorrecta. Ya existe un investigador con ese número de documento de identidad");
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Null Pointer Exception",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificar(Investigador investigador) {
        try {
            new InvestigadorJpaController(emf).edit(investigador);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(InvestigadorFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InvestigadorFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Investigador investigador) {
        try {
            new InvestigadorJpaController(emf).destroy(investigador.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(InvestigadorFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Investigador buscar(long id) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        return new InvestigadorJpaController(emf).findInvestigador(id);
    }

    public Investigador getUltimoInvestigador() {
        Query quInvestigador = em.createQuery("SELECT i FROM Investigador i");
        List<Investigador> investigadores = quInvestigador.getResultList();
        if (!investigadores.isEmpty()) {
            return investigadores.get(investigadores.size() - 1);
        } else {
            return null;
        }
    }

    public List<Investigador> getTodosInvestigador() {
        Query quTodosInvestigador = em.createQuery("SELECT i FROM Investigador i "
                + "ORDER BY i.persona.apellido");
        return quTodosInvestigador.getResultList();
    }

    public List<Investigador> getCienPrimerosInvestigadores() {
        Query quTodosInvestigador = em.createQuery("SELECT i FROM Investigador i "
                + "ORDER BY i.persona.apellido").setMaxResults(100);
        return quTodosInvestigador.getResultList();
    }

    public boolean existePersonaInvestigador(Investigador investigador) {
        Query quExistePersonaInvestigador = em.createQuery("SELECT i FROM "
                + "Investigador i WHERE i.persona.id=" + investigador.getPersona().getId());
        List<Investigador> investigadoresEncontrados
                = quExistePersonaInvestigador.getResultList();
        if (!investigadoresEncontrados.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List<Investigador> getInvestigadores(String apellidoNombre) {
        Query quTodosInvestigador = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.persona.apellidoNombre LIKE '%" + apellidoNombre
                + "%' ORDER BY i.persona.apellidoNombre");
        return quTodosInvestigador.getResultList();
    }

    public Investigador buscarApellidoNombre(String apellido, String nombre) {
        Query quBuscar = em.createQuery("SELECT i FROM Investigador i WHERE "
                + "i.persona.apellido=:apellido AND i.persona.nombre=:nombre");
        quBuscar.setParameter("apellido", apellido);
        quBuscar.setParameter("nombre", nombre);
        List<Investigador> personasEncontradas = quBuscar.getResultList();
        if (!personasEncontradas.isEmpty()) {
            return personasEncontradas.get(0);
        } else {
            return null;
        }
    }

    public List<Investigador> buscarDniApellido(String busqueda) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        List<Investigador> investigadoresEncontrados;
        if (LongValidator.getInstance().isValid(busqueda)) {
            Query quBuscarDni = em.createQuery("SELECT i FROM Investigador i WHERE "
                    + "i.persona.documentoIdentidad.numero=:param ORDER BY i.persona.apellido");
            quBuscarDni.setParameter("param", Long.parseLong(busqueda));
            investigadoresEncontrados = quBuscarDni.getResultList();
        } else {
            Query quBuscarApellido = em.createQuery("SELECT i FROM Investigador i "
                    + "WHERE i.persona.apellido LIKE '%" + busqueda + "%' "
                    + "ORDER BY i.persona.apellido");
            investigadoresEncontrados = quBuscarApellido.getResultList();
        }
        return investigadoresEncontrados;
    }

    public List<Categorizacion> buscarxId(Long id) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        List<Categorizacion> listaCategorizaciones = new ArrayList<>();
        Query quId = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.id=:id ");
        quId.setParameter("id", id);
        Investigador inv = (Investigador) quId.getSingleResult();
        for (Categorizacion cat : inv.getCategorizaciones()) {
            if (!inv.getCategorizaciones().isEmpty()) {
                listaCategorizaciones.add(cat);
            }
        }
        //listaCategorizaciones = quId.getResultList();
        return listaCategorizaciones;
    }

    public List<Investigador> filtrar(List<Investigador> investigadores, String apellidoNombre) {
        Query quBuscar = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.persona.apellido LIKE '%" + apellidoNombre + "%' OR "
                + "i.persona.nombre LIKE '%" + apellidoNombre + "%'");
        List<Investigador> investigadoresFiltrados = quBuscar.getResultList();
        List<Investigador> investigadoresFueraFiltrados = new ArrayList<Investigador>();
        for (Investigador investigador : investigadores) {
            if (investigadoresFiltrados.contains(investigador)) {
                investigadoresFueraFiltrados.add(investigador);
            }
        }
        return investigadoresFueraFiltrados;
    }

    public List<Investigador> buscarPorApellidoNombre(String apellidoNombre) {
        Query quBuscar = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.persona.apellido LIKE '%" + apellidoNombre + "%' OR "
                + "i.persona.nombre LIKE '%" + apellidoNombre + "%'");
        return quBuscar.getResultList();
    }

    public List<Investigador> buscarPorDocumentoIdentidad(TipoDocumento tipoDocumento, long numeroDocumentoIdentidad) {
        Query quBuscar = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.persona.documentoIdentidad.tipoDocumento.id = :idTipoDocumento AND "
                + "i.persona.documentoIdentidad.numero = :numeroDocumentoIdentidad");
        quBuscar.setParameter("idTipoDocumento", tipoDocumento.getId());
        quBuscar.setParameter("numeroDocumentoIdentidad", numeroDocumentoIdentidad);
        return quBuscar.getResultList();
    }

    public Investigador buscarPorCuil(String cuil) {
        Query quBuscar = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.persona.cuil = :cuil");
        quBuscar.setParameter("cuil", cuil);
        try {
            return (Investigador) quBuscar.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Investigador> buscarPorNumeroDocumentoIdentidad(long numeroDocumentoIdentidad) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT i FROM Investigador i "
                + "WHERE i.persona.documentoIdentidad.numero = :numeroDocumentoIdentidad");
        quBuscar.setParameter("numeroDocumentoIdentidad", numeroDocumentoIdentidad);
        return quBuscar.getResultList();
    }

    public boolean existeInvestigadorConNumeroDocumentoIdentidad(TipoDocumento tipoDocumento, long numeroDocumentoIdentidad) {
        List<Investigador> listaInv = buscarPorDocumentoIdentidad(tipoDocumento, numeroDocumentoIdentidad);
        if (listaInv.isEmpty()) {
            return false;
        } else {
            return true;
        }
//        if (buscarPorDocumentoIdentidad(tipoDocumento, numeroDocumentoIdentidad) != null) {
//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * Exporta los datos de los investigadores a un archivo de excel
     */
    public void exportarAExcel() {
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Apellido y Nombre|CUIL|Domicilio|Ciudad|Roles|Email|Unidad Academica|Telefono");

        for (Investigador i : getTodosInvestigador()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(i.getId());
            stringBuider.append("|");
            stringBuider.append(i.getPersona().getApellido());
            stringBuider.append(" ");
            stringBuider.append(i.getPersona().getNombre());
            stringBuider.append("|");
            if (i.getPersona().getCuil() != null) {
                if (!"".equals(i.getPersona().getCuil())) {
                    stringBuider.append(i.getPersona().getCuil());
                } else {
                    stringBuider.append(" ");
                }
            } else {
                stringBuider.append(" ");
            }

            stringBuider.append("|");
            if (i.getPersona().getDomicilio() != null) {
                if (i.getPersona().getDomicilio().getCalle() != null) {
                    stringBuider.append(i.getPersona().getDomicilio().getCalle()).append(" ").append(i.getPersona().getDomicilio().getNumero());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (i.getPersona().getDomicilio().getLocalidad() != null) {
                    stringBuider.append(i.getPersona().getDomicilio().getLocalidad());
                } else {
                    stringBuider.append(" ");
                }

                stringBuider.append("|");
            } else {
                stringBuider.append(" | |");
            }
            for (Participacion p : i.getParticipacionesProyecto()) {
                if (p.getProyecto() != null) {
                    stringBuider.append(p.getRol().getDescripcion());
                    stringBuider.append(" en ");
                    stringBuider.append(p.getProyecto().toString());
                    stringBuider.append(" de ").append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaDesde())).append(" a ");
                    if (p.getFechaHasta() != null) {
                        stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaHasta()));
                    } else {
                        stringBuider.append("la actualidad");
                    }
                    stringBuider.append(" ");

                } else {
                    stringBuider.append(" ");
                }
            }
            stringBuider.append(" |");
            for (CorreoElectronico c : i.getPersona().getCorreosElectronicos()) {
                stringBuider.append(c.getDireccion());
                stringBuider.append(" ");
            }
            stringBuider.append(" |");
            for (Docencia d : i.getDocencias()) {
                if (d.getUnidadAcademica() != null) {
                    if (d.getUnidadAcademica().getDescripcion() != null) {
                        stringBuider.append(d.getUnidadAcademica().getDescripcion());
                        stringBuider.append(", ");
                    }
                } else {
                    stringBuider.append(" ");
                }

            }
            stringBuider.append(" |");
            for (Telefono t : i.getPersona().getTelefonos()) {
                if (t.getTipoTelefono() != null) {
                    stringBuider.append(t.getTipoTelefono());
                }
                stringBuider.append(" ");
                stringBuider.append(t.getNumero());
                stringBuider.append(" ");
            }
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Proyectos");

    }

    public void exportarAExcelInvestigadoresDocencias() {
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Apellido y Nombre|CUIL|Domicilio|Ciudad|Roles|Email|Unidad Academica|Telefono|Docencia");

        for (Investigador i : getTodosInvestigador()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(i.getId());
            stringBuider.append("|");
            stringBuider.append(i.getPersona().getApellido());
            stringBuider.append(" ");
            stringBuider.append(i.getPersona().getNombre());
            stringBuider.append("|");
            if (i.getPersona().getCuil() != null) {
                if (!"".equals(i.getPersona().getCuil())) {
                    stringBuider.append(i.getPersona().getCuil());
                } else {
                    stringBuider.append(" ");
                }
            } else {
                stringBuider.append(" ");
            }

            stringBuider.append("|");
            if (i.getPersona().getDomicilio() != null) {
                if (i.getPersona().getDomicilio().getCalle() != null) {
                    stringBuider.append(i.getPersona().getDomicilio().getCalle()).append(" ").append(i.getPersona().getDomicilio().getNumero());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (i.getPersona().getDomicilio().getLocalidad() != null) {
                    stringBuider.append(i.getPersona().getDomicilio().getLocalidad());
                } else {
                    stringBuider.append(" ");
                }

                stringBuider.append("|");
            } else {
                stringBuider.append(" | |");
            }
            for (Participacion p : i.getParticipacionesProyecto()) {
                if (p.getProyecto() != null) {
                    stringBuider.append(p.getRol().getDescripcion());
                    stringBuider.append(" en ");
                    stringBuider.append(p.getProyecto().toString());
                    stringBuider.append(" de ").append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaDesde())).append(" a ");
                    if (p.getFechaHasta() != null) {
                        stringBuider.append(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaHasta()));
                    } else {
                        stringBuider.append("la actualidad");
                    }
                    stringBuider.append(" ");

                } else {
                    stringBuider.append(" ");
                }
            }
            stringBuider.append(" |");
            for (CorreoElectronico c : i.getPersona().getCorreosElectronicos()) {
                stringBuider.append(c.getDireccion());
                stringBuider.append(" ");
            }
            stringBuider.append(" |");
            for (Docencia d : i.getDocencias()) {
                if (d.getUnidadAcademica() != null) {
                    if (d.getUnidadAcademica().getDescripcion() != null) {
                        stringBuider.append(d.getUnidadAcademica().getDescripcion());
                        stringBuider.append(", ");
                    }
                } else {
                    stringBuider.append(" ");
                }

            }
            stringBuider.append(" |");
            for (Telefono t : i.getPersona().getTelefonos()) {
                if (t.getTipoTelefono() != null) {
                    stringBuider.append(t.getTipoTelefono());
                }
                stringBuider.append(" ");
                stringBuider.append(t.getNumero());
                stringBuider.append(" ");
            }
            stringBuider.append(" |");
            try {
                for (Docencia d : i.getDocencias()) {
                    stringBuider.append(", ");
                    if (d != null) {

                        stringBuider.append(d);
                        stringBuider.append(", ");

                    } else {
                        stringBuider.append(" ");
                    }
                }
            } catch (Exception e) {
                stringBuider.append(" ");
            }

            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Proyectos");

    }
    //crea la ventana para exportar a excel

    public void exportarAExcelVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcel", "Preparandose para Exportar a Excel", null);
    }

    public void exportarAExcelInvestigDocenciaVentanaCargando() {
        Comunes.ventanaCargando(this, "exportarAExcelInvestigadoresDocencias", "Preparandose para Exportar a Excel", null);
    }

    /**
     * Exporta los datos de los investigadores a un archivo de excel
     */
    public void exportarAExcelDatosDocentes() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();
        lista.add("Nº|Apellido y Nombre|Universidad|Unidad Academica|Titulos de Grado|Titulos de Posgrado");

        for (Investigador i : getTodosInvestigador()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(i.getId());
            stringBuider.append("|");
            stringBuider.append(i.getPersona().getApellido());
            stringBuider.append(" ");
            stringBuider.append(i.getPersona().getNombre());
            stringBuider.append("|");
            if (!i.getDocencias().isEmpty()) {
                stringBuider.append(i.getDocencias().get(i.getDocencias().size() - 1).getUniversidad());

            } else {
                stringBuider.append(" ");
            }

            stringBuider.append("|");
            if (!i.getDocencias().isEmpty()) {
                stringBuider.append(i.getDocencias().get(i.getDocencias().size() - 1).getUnidadAcademica());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");

            for (FormacionAcademicaOtra f : i.getFormacionesAcademicasOtras()) {
                stringBuider.append(f.getTituloOtro());
                stringBuider.append(" - ");
            }
            stringBuider.append(" |");
            for (FormacionAcademicaGrado f : i.getFormacionesAcademicasGrado()) {
                stringBuider.append(f.getTituloGrado());
                stringBuider.append(" - ");
            }
            stringBuider.append(" |");
            for (FormacionAcademicaPosgrado f : i.getFormacionesAcademicasPosgrado()) {
                stringBuider.append(f.getTituloPosgrado());
                stringBuider.append(" - ");
            }
            stringBuider.append(" |");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Investigadores");

    }
    //crea la ventana para exportar a excel

    public void exportarAExcelVentanaCargandoDatosDocentes() {
        Comunes.ventanaCargando(this, "exportarAExcelDatosDocentes", "Preparandose para Exportar a Excel", null);
    }

    /**
     * Exporta los datos de los investigadores a un archivo de excel
     */
    public void exportarAExcelDatosDocentesCatTelMail() {
        //Datos a escribir
        List<String> lista = new ArrayList<>();
        lista.add("Nº|Apellido y Nombre|CUIL|Sexo|Unidad Academica Categoria|Categoria|Condicion|Telefonos|Correos Electronicos");

        for (Investigador i : getTodosInvestigador()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(i.getId());
            stringBuider.append("|");
            stringBuider.append(i.getPersona().getApellido());
            stringBuider.append(" ");
            stringBuider.append(i.getPersona().getNombre());
            stringBuider.append("|");
            stringBuider.append(i.getPersona().getCuil());
            stringBuider.append("|");
            stringBuider.append(i.getPersona().getSexo());
            stringBuider.append("|");
            if (!i.getCategorizaciones().isEmpty()) {
                List<Categorizacion> categorizaciones = i.getCategorizaciones();
                try {
                    Collections.sort(categorizaciones);
                } catch (Exception ex) {
                }
                stringBuider.append(categorizaciones.get(categorizaciones.size() - 1).getUnidadAcademica());
                stringBuider.append("|");

                stringBuider.append(categorizaciones.get(categorizaciones.size() - 1));
                stringBuider.append("|");

                stringBuider.append(categorizaciones.get(categorizaciones.size() - 1).getCondicion());

            } else {
                stringBuider.append(" ");
                stringBuider.append("|");
                stringBuider.append(" ");
                stringBuider.append("|");
                stringBuider.append(" ");

            }

            stringBuider.append("|");

            for (Telefono f : i.getPersona().getTelefonos()) {
                stringBuider.append(f);
                stringBuider.append(" - ");
            }
            stringBuider.append(" |");
            for (CorreoElectronico c : i.getPersona().getCorreosElectronicos()) {
                stringBuider.append(c);
                stringBuider.append(" - ");
            }
            stringBuider.append(" |");
            lista.add(stringBuider.toString());

        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Investigadores");

    }
    //crea la ventana para exportar a excel

    public void exportarAExcelVentanaCargandoDatosDocentesCatTelMail() {
        Comunes.ventanaCargando(this, "exportarAExcelDatosDocentesCatTelMail", "Preparandose para Exportar a Excel", null);
    }

    public List<Docencia> listarDocencias(Investigador investigador) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        Query buscar = em.createQuery("SELECT d FROM Docencia d WHERE d.investigador=:investigador");
        buscar.setParameter("investigador", investigador);
        try {
            return buscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }

    }

    public List<ActividadConduccion> listarActividadConduccion(Investigador investigador) {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        em = emf.createEntityManager();
        Query buscar = em.createQuery("SELECT ac FROM Investigador i, IN(i.actividadesConduccion) ac Where i=:investigador");
        buscar.setParameter("investigador", investigador);
        try {
            return buscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }

    }

    public List<CursoDictado> listarCursosDictados(Investigador investigador) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT cd FROM Investigador i, IN(i.cursosDictados) cd Where i=:investigador");
        buscar.setParameter("investigador", investigador);
        try {
            return buscar.getResultList();
        } catch (Exception ex) {
            return null;
        }

    }

    public List<Investigador> getInvestigadoresXPostgrado(TituloPosgrado tituloPosgrado) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.formacionesAcademicasPosgrado) fAP Where "
                + "fAP.tituloPosgrado=:tituloPosgrado");
        buscar.setParameter("tituloPosgrado", tituloPosgrado);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXGrado(TituloGrado tituloGrado) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.formacionesAcademicasGrado) fAG Where "
                + "fAG.tituloGrado=:tituloGrado");
        buscar.setParameter("tituloGrado", tituloGrado);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXOtroTitulo(TituloOtro tituloOtro) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.formacionesAcademicasOtras) fAO Where "
                + "fAO.tituloOtro=:tituloOtro");
        buscar.setParameter("tituloOtro", tituloOtro);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXUniversidad(Universidad universidad) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.docencias) doc Where "
                + "doc.universidad=:universidad");
        buscar.setParameter("universidad", universidad);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXDptoDocente(DepartamentoDocente departamentoDocente) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.docencias) doc Where "
                + "doc.departamentoDocente=:departamentoDocente");
        buscar.setParameter("departamentoDocente", departamentoDocente);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXCategoriaDocente(CategoriaDocente categoriaDocente) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.docencias) doc Where "
                + "doc.categoriaDocente=:categoriaDocente");
        buscar.setParameter("categoriaDocente", categoriaDocente);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXDedicacionDocente(DedicacionDocente dedicacionDocente) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.docencias) doc Where "
                + "doc.dedicacionDocente=:dedicacionDocente");
        buscar.setParameter("dedicacionDocente", dedicacionDocente);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXModoObtencionCargo(ModoObtencionCargo modoObtencionCargo) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.docencias) doc Where "
                + "doc.modoObtencionCargo=:modoObtencionCargo");
        buscar.setParameter("modoObtencionCargo", modoObtencionCargo);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXCargoConduccion(CargoConduccion cargoConduccion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.actividadesConduccion) doc Where "
                + "doc.cargoConduccion=:cargoConduccion");
        buscar.setParameter("cargoConduccion", cargoConduccion);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXDedicacionConduccion(DedicacionConduccion dedicacionConduccion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.actividadesConduccion) doc Where "
                + "doc.dedicacion=:dedicacionConduccion");
        buscar.setParameter("dedicacionConduccion", dedicacionConduccion);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXEspecialidadInvestigacion(EspecialidadInvestigacion especialidadInvestigacion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.especializaciones) doc Where "
                + "doc.especialidadInvestigacion=:especialidadInvestigacion");
        buscar.setParameter("especialidadInvestigacion", especialidadInvestigacion);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXEspecialidadActividadAcademica(EspecialidadActividadAcademica especialidadActividadAcademica) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.especializaciones) doc Where "
                + "doc.especialidadActividadAcademica=:especialidadActividadAcademica");
        buscar.setParameter("especialidadActividadAcademica", especialidadActividadAcademica);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXTipoDuracionAsignatura(TipoDuracionAsignatura tipoDuracionAsignatura) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.cursosDictados) doc Where "
                + "doc.tipoDuracionAsignatura=:tipoDuracionAsignatura");
        buscar.setParameter("tipoDuracionAsignatura", tipoDuracionAsignatura);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXTipoAsignatura(TipoAsignatura tipoAsignatura) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.cursosDictados) doc Where "
                + "doc.tipoAsignatura=:tipoAsignatura");
        buscar.setParameter("tipoAsignatura", tipoAsignatura);
        return buscar.getResultList();
    }

    public List<Investigador> getInvestigadoresXCarreraAsignatura(CarreraAsignatura carreraAsignatura) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT i FROM Investigador i, IN(i.cursosDictados) doc Where "
                + "doc.asignatura.carreraAsignatura=:carreraAsignatura");
        buscar.setParameter("carreraAsignatura", carreraAsignatura);
        return buscar.getResultList();
    }

    public List<Participacion> getParticipacionesPorInvestigador(Investigador investigador) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query buscar = ema.createQuery("SELECT p FROM Participacion p "
                + "WHERE p.investigador=:investigador ");
        buscar.setParameter("investigador", investigador);
        return buscar.getResultList();
    }

    public void mostrarCvAr(String cuil) {
        new reportes.Reporte().reporteCvAr(cuil);
    }

    public List<Investigador> getTodosInvestigadoresVigentes(Date finalizacion) {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa WHERE pa.proyecto.fechaFinalizacion >=:finalizacion ");
        quParticipaciones.setParameter("finalizacion", finalizacion);
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (Participacion participacion : participaciones) {

            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());

            }
        }
        return integrantes;
    }

    public List<Investigador> getTodosInvestigadoresVigentesProrrogas(Date prorroga) {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa, IN(pa.proyecto.prorrogas) pro WHERE pro.fecha >=:prorroga ");
        quParticipaciones.setParameter("prorroga", prorroga);
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (Participacion participacion : participaciones) {

            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());

            }
        }
        return integrantes;
    }
}
