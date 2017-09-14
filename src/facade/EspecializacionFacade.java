/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EspecializacionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Especializacion;
import entidades.persona.investigador.Investigador;
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
public class EspecializacionFacade {
    
     
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static EspecializacionFacade instance = null;

    protected EspecializacionFacade() {
    }

    public static EspecializacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EspecializacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Especializacion especializacion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        new EspecializacionJpaController(emfa).create(especializacion);
    }

    public Especializacion buscar(long id) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        return new EspecializacionJpaController(emfa).findEspecializacion(id);

    }

    public void editar(Especializacion especializacion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        try {
            new EspecializacionJpaController(emfa).edit(especializacion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecializacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EspecializacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar(Long id){
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        try {
            new EspecializacionJpaController(emfa).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EspecializacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Especializacion> listarEspecializacionInvestigacion(String text) {
        Query quBuscar = em.createQuery("SELECT e FROM Especializacion e WHERE e.especialidadInvestigacion.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }
    
    
    
    public List<Especializacion> listarTodasEspecializaciones() {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        return new EspecializacionJpaController(emfa).findEspecializacionEntities();
    }
    public List<Especializacion> getEspecializacionesInvestigador(Investigador investigador){
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT e FROM Especializacion e WHERE e.investigador=:investigador");
        quBuscar.setParameter("investigador", investigador);
        return quBuscar.getResultList();
        
        
    }
    public void excelEspecializacion() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Apellido y Nombre|Especialidad Investigacion |Especialidad Actividad Academica");

        for (Especializacion e : listarTodasEspecializaciones()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(e.getId());
            stringBuider.append("|");
            if (e.getInvestigador() != null) {
                stringBuider.append(e.getInvestigador());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getEspecialidadInvestigacion()!=null) {
                stringBuider.append(e.getEspecialidadInvestigacion());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (e.getEspecialidadActividadAcademica() != null) {
                stringBuider.append(e.getEspecialidadActividadAcademica());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }
        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Especializacion Investigador");

    }
    public void exportarAExcelEspecializacion() {
        Comunes.ventanaCargando(this, "excelEspecializacion", "Preparandose para Exportar a Excel", null);
    }
      
}
