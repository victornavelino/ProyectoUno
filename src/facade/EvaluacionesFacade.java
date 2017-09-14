/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EvaluacionesJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Evaluaciones;
import entidades.becas.PostulacionBeca;
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
 * @author hugo
 */
public class EvaluacionesFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EvaluacionesJpaController evaluacionesJpaController = new EvaluacionesJpaController(emf);
    private static EvaluacionesFacade instance = null;

    protected EvaluacionesFacade() {
    }

    public static EvaluacionesFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluacionesFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Evaluaciones evaluaciones) {
        new EvaluacionesJpaController(emf).create(evaluaciones);
    }

    public void modificar(Evaluaciones evaluaciones) {
        try {
            new EvaluacionesJpaController(emf).edit(evaluaciones);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionesFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluacionesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    public void eliminar(long id) {
        try {
            new EvaluacionesJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Evaluaciones buscar(long id) {
        return new EvaluacionesJpaController(emf).findEvaluaciones(id);
    }

    public List<Evaluaciones> getTodas(String text) {
        Query quBuscar = em.createQuery("SELECT e FROM Evaluaciones e WHERE e.evaluador LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Evaluaciones> getTodas() {
        Query quBuscar = em.createQuery("SELECT e FROM Evaluaciones e");
        return quBuscar.getResultList();
    }
    
    public List<Evaluaciones> getEvaluacionesPostulacion(PostulacionBeca postulacion) {
         EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT e FROM Evaluaciones e WHERE e.postulacionBeca =:postulacion");
        quBuscar.setParameter("postulacion", postulacion);
        return quBuscar.getResultList();
    }

    public List<Evaluaciones> listarTodosEvaluacionOrdenados() {
        Query quBuscar = em.createQuery("SELECT e FROM Evaluaciones e ORDER BY e.evaluador");
        return quBuscar.getResultList();
    }
    
        public void exportarAExcelEvaluaciones() {
        Comunes.ventanaCargando(this, "excelEvaluaciones", "Preparandose para Exportar a Excel", null);
    }
    
    public void excelEvaluaciones() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Codigo Incentivos|Beca|Postulante|Proyecto|Evaluador|Fecha de Evaluacion|"
                + "Institucion|Estado de Beca|Calificacion Letras|Calificacion Numero|Observaciones");

        for (Evaluaciones e : EvaluacionesFacade.getInstance().getTodas()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(e.getId());
            stringBuider.append("|");
            if (e.getPostulacionBeca().getProyecto() != null) {
                stringBuider.append(e.getPostulacionBeca().getProyecto().getCodigoIncentivos());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getPostulacionBeca().getBeca() != null) {
                stringBuider.append(e.getPostulacionBeca().getBeca());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getPostulacionBeca().getPostulante() != null) {
                stringBuider.append(e.getPostulacionBeca().getPostulante().toString());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getPostulacionBeca().getProyecto() != null) {
                stringBuider.append(e.getPostulacionBeca().getProyecto());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getEvaluador() != null) {
                stringBuider.append(e.getEvaluador().toString());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getFecha() != null) {
                stringBuider.append(formato.format(e.getFecha()));
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getInstitucion() != null) {
                stringBuider.append(e.getInstitucion());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getEvaluacionEstado() != null) {
                stringBuider.append(e.getEvaluacionEstado());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getCalificacionEnLetras() != null) {
                stringBuider.append(e.getCalificacionEnLetras());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            stringBuider.append(e.getCalificacionEnNumeros());
            stringBuider.append("|");
            if (e.getObservaciones() != null) {
                stringBuider.append(e.getObservaciones());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");

            lista.add(stringBuider.toString());

        }
        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Evaluaciones");

    }
    
    
}
