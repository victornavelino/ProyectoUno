/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ActividadConduccionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.actividadConduccion.ActividadConduccion;
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
public class ActividadConduccionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static ActividadConduccionFacade instance = null;

    protected ActividadConduccionFacade() {
    }

    public static ActividadConduccionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ActividadConduccionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ActividadConduccion actividadConduccion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        new ActividadConduccionJpaController(emfa).create(actividadConduccion);
    }

    public ActividadConduccion buscar(long id) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        return new ActividadConduccionJpaController(emfa).findActividadConduccion(id);

    }

    public void editar(ActividadConduccion actividadConduccion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        try {
            new ActividadConduccionJpaController(emfa).edit(actividadConduccion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ActividadConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActividadConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Long id) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        try {
            new ActividadConduccionJpaController(emfa).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ActividadConduccionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ActividadConduccion> listarActividadConduccion(String text) {
        Query quBuscar = em.createQuery("SELECT a FROM ActividadConduccion a WHERE a.cargoConduccion.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<ActividadConduccion> listarActividadConduccion(Investigador investigador) {
        Query quBuscar = em.createQuery("SELECT a FROM ActividadConduccion a,Investigador i, IN( a)");
        return quBuscar.getResultList();
    }

    public List<ActividadConduccion> listarTodasOrdenadasPorNombre() {
        Query quBuscar = em.createQuery("SELECT a FROM ActividadConduccion a,Investigador i, IN( a) ORDER BY i.persona.apellido ASC");
        return quBuscar.getResultList();
    }

    public List<ActividadConduccion> listarActividadConduccion() {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        return new ActividadConduccionJpaController(emfa).findActividadConduccionEntities();
    }

    public void excelActividadConduccion() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Apellido y Nombre|Cargo|Dedicacion|Dependencia|"
                + "Fecha desde|Decha hasta");
        for (Investigador investigador : InvestigadorFacade.getInstance().getTodosInvestigador()) {
            for (ActividadConduccion a : InvestigadorFacade.getInstance().listarActividadConduccion(investigador)) {
                StringBuilder stringBuider = new StringBuilder();
                stringBuider.append(a.getId());
                stringBuider.append("|");
                if (investigador.getPersona() != null) {
                    stringBuider.append(investigador.getPersona());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (a.getCargoConduccion()!=null) {
                    stringBuider.append(a.getCargoConduccion());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (a.getDedicacion() != null) {
                    stringBuider.append(a.getDedicacion());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (a.getDependencia() != null) {
                    stringBuider.append(a.getDependencia());
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (a.getFechaDesde() != null) {
                    stringBuider.append(formato.format(a.getFechaDesde()));
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                if (a.getFechaHasta()!= null) {
                    stringBuider.append(formato.format(a.getFechaHasta()));
                } else {
                    stringBuider.append(" ");
                }
                stringBuider.append("|");
                
                lista.add(stringBuider.toString());

            }
        }

        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Actividad Conduccion");

    }

    public void exportarAExcelActividadConduccion() {
        Comunes.ventanaCargando(this, "excelActividadConduccion", "Preparandose para Exportar a Excel", null);
    }
}
