/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CursoDictadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.curso.Asignatura;
import entidades.persona.investigador.curso.CursoDictado;
import includes.Comunes;
import includes.ExportarExcel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author huguito
 */
public class CursoDictadoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static CursoDictadoFacade instance = null;

    protected CursoDictadoFacade() {
    }

    public static CursoDictadoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CursoDictadoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(CursoDictado cursoDictado) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        new CursoDictadoJpaController(emfa).create(cursoDictado);
    }

    public CursoDictado buscar(long id) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        return new CursoDictadoJpaController(emfa).findCursoDictado(id);

    }

    public void editar(CursoDictado cursoDictado) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        try {
            new CursoDictadoJpaController(emfa).edit(cursoDictado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CursoDictadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CursoDictadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Long id) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        try {
            new CursoDictadoJpaController(emfa).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CursoDictadoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CursoDictado> listarCursos(String text) {
        Query quBuscar = em.createQuery("SELECT c FROM CursoDictado c WHERE c.asignatura.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<CursoDictado> listarCursos() {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        return new CursoDictadoJpaController(emfa).findCursoDictadoEntities();
    }

    public void excelAsignaturas() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Apellido y Nombre|Asignatura|Tipo de Duración de Asignatura|Año Academico|"
                + "Tipo de Asignatura|Dictado de Clases (Hs. Semanales)|Duración (Semanas)");
        for (Investigador investigador : InvestigadorFacade.getInstance().getTodosInvestigador()) {
            for (CursoDictado c : InvestigadorFacade.getInstance().listarCursosDictados(investigador)) {
                StringBuilder stringBuider = new StringBuilder();
                stringBuider.append(c.getId());
                stringBuider.append("|");
                if (investigador!= null) {
                    stringBuider.append(investigador);
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (c.getAsignatura()!=null) {
                    stringBuider.append(c.getAsignatura());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (c.getTipoDuracionAsignatura()!= null) {
                    stringBuider.append(c.getTipoDuracionAsignatura());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (c.getDuracionAnual() != null) {
                    stringBuider.append(c.getDuracionAnual());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (c.getTipoAsignatura() != null) {
                    stringBuider.append(c.getTipoAsignatura());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (c.getHorasSemanalesDictadoClases()>=0) {
                    stringBuider.append(c.getHorasSemanalesDictadoClases());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (c.getSemanasDuracion()>=0) {
                    stringBuider.append(c.getSemanasDuracion());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Asignaturas Investigador");

    }

    public void exportarAExcelDocencias() {
        Comunes.ventanaCargando(this, "excelAsignaturas", "Preparandose para Exportar a Excel", null);
    }
}
