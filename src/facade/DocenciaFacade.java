/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DocenciaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.DepartamentoDocente;
import entidades.persona.investigador.Docencia;
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
public class DocenciaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    DocenciaJpaController docenciaJpaController = new DocenciaJpaController(emf);
    private static DocenciaFacade instance = null;

    protected DocenciaFacade() {
    }

    public static DocenciaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DocenciaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Docencia docencia) {
        new DocenciaJpaController(emf).create(docencia);
    }
    
    public void modificar(Docencia docencia) {
        try {
            new DocenciaJpaController(emf).edit(docencia);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DocenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DocenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Long id) {
        try {
            new DocenciaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DocenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Docencia buscar(long id) {
        return new DocenciaJpaController(emf).findDocencia(id);
    }
    
    public List<Docencia> getTodos(){
        return new DocenciaJpaController(emf).findDocenciaEntities();
    }
    public List<Docencia> getTodosOrdenadosPorNombre(){
        Query query=em.createQuery("SELECT d From Docencia d ORDER BY d.investigador.persona.apellido ASC");
        return query.getResultList();
    }
    
    public void excelDocencias() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Apellido y Nombre|Año|Universidad|Unidad Academica|"
                + "Departamento Docente|Categoria Docente|Dedicacion Docente|"
                + "Modo de obtencion del cargo|Fecha de obtencion del cargo|"
                + "Horas 1er Cuatrimestre|Semanas 1er Cuatrimestre|"
                + "Horas 2do Cuatrimestre|Semanas 2do Cuatrimestre");

        for (Docencia d : getTodosOrdenadosPorNombre()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(d.getId());
            stringBuider.append("|");
            if (d.getInvestigador() != null) {
                stringBuider.append(d.getInvestigador());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getAño()>0) {
                stringBuider.append(d.getAño());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getUniversidad() != null) {
                stringBuider.append(d.getUniversidad());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getUnidadAcademica() != null) {
                stringBuider.append(d.getUnidadAcademica());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getDepartamentoDocente() != null) {
                stringBuider.append(d.getDepartamentoDocente());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getCategoriaDocente()!= null) {
                stringBuider.append(d.getCategoriaDocente());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getDedicacionDocente() != null) {
                stringBuider.append(d.getDedicacionDocente());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getModoObtencionCargo() != null) {
                stringBuider.append(d.getModoObtencionCargo());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getFechaObtencionCargo() != null) {
                stringBuider.append(formato.format(d.getFechaObtencionCargo()));
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getHorasDedicadasDocenciaPrimerCuatrimestre()>=0) {
                stringBuider.append(d.getHorasDedicadasDocenciaPrimerCuatrimestre());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getSemanasDedicadasDocenciaPrimerCuatrimestre()>=0) {
                stringBuider.append(d.getSemanasDedicadasDocenciaPrimerCuatrimestre());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getHorasDedicadasDocenciaSegundoCuatrimestre()>=0) {
                stringBuider.append(d.getHorasDedicadasDocenciaSegundoCuatrimestre());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (d.getSemanasDedicadasDocenciaSegundoCuatrimestre()>=0) {
                stringBuider.append(d.getSemanasDedicadasDocenciaSegundoCuatrimestre());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }
        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Docencias Investigador");

    }
    public void exportarAExcelDocencias() {
        Comunes.ventanaCargando(this, "excelDocencias", "Preparandose para Exportar a Excel", null);
    }

    public List<Docencia> getDocenciaXDeptoDocente(DepartamentoDocente departamentoDocente) {
         Query query=em.createQuery("SELECT d From Docencia d WHERE d.departamentoDocente=:departamentoDocente");
         query.setParameter("departamentoDocente", departamentoDocente);
         return query.getResultList(); 
    }
}
