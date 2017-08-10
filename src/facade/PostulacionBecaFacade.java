/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PostulacionBecaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Becas;
import entidades.becas.Evaluaciones;
import entidades.becas.Observacion;
import entidades.becas.PostulacionBeca;
import entidades.investigador.formacionAcademica.FormacionAcademicaGrado;
import entidades.investigador.formacionAcademica.FormacionAcademicaPosgrado;
import entidades.persona.investigador.Docencia;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Evaluacion;
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
import javax.swing.JOptionPane;

/**
 *
 * @author huguito
 */
public class PostulacionBecaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    PostulacionBecaJpaController BecasJpaController = new PostulacionBecaJpaController(emf);
    private static PostulacionBecaFacade instance = null;

    protected PostulacionBecaFacade() {
    }

    public static PostulacionBecaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PostulacionBecaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(PostulacionBeca pbeca) {
        new PostulacionBecaJpaController(emf).create(pbeca);
    }

    public void modificar(PostulacionBeca pbeca) {
        try {
            new PostulacionBecaJpaController(emf).edit(pbeca);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PostulacionBecaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PostulacionBecaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new PostulacionBecaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PostulacionBecaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<PostulacionBeca> getTodosPostulaciones() {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quTodosbecas = ema.createQuery("SELECT p FROM PostulacionBeca p");
        return quTodosbecas.getResultList();
    }

    public List<PostulacionBeca> getPostulacionesBecas(Becas beca) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query qutbb = ema.createQuery("SELECT p FROM PostulacionBeca p WHERE p.beca=:beca");
        qutbb.setParameter("beca", beca);
        return qutbb.getResultList();
    }

    public Boolean existePostulante(Investigador investigador) {
        Query qutbb = em.createQuery("SELECT p FROM PostulacionBeca p "
                + "WHERE p.postulante.id = '" + investigador.getId() + "'");
        if (!qutbb.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List<PostulacionBeca> getPostulantes(Investigador investigador) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query qutbb = ema.createQuery("SELECT p FROM PostulacionBeca p "
                + "WHERE p.postulante.id = '" + investigador.getId() + "'");
        return qutbb.getResultList();
    }

    public List<PostulacionBeca> getPostulante(String descripcion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query qutbb = ema.createQuery("SELECT p FROM PostulacionBeca p "
                + "WHERE p.postulante.persona.apellido LIKE '%" + descripcion + "%'");
        return qutbb.getResultList();
    }

    public List<PostulacionBeca> getPostulanteDeBeca(Becas beca, String descripcion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query qutbb = ema.createQuery("SELECT p FROM PostulacionBeca p "
                + "WHERE p.beca.descripcion ='" + beca.getDescripcion() + "'AND p.postulante.persona.apellido LIKE '%" + descripcion + "%'");
        return qutbb.getResultList();
    }

    public List<PostulacionBeca> getPostulanteDeBeca(String nombre, Becas beca) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query qutbb = ema.createQuery("SELECT p FROM PostulacionBeca p, IN (p.asesores) pa Where (pa.persona.apellido LIKE :nombre AND p.beca=:beca) OR (p.postulante.persona.apellido LIKE :nombre AND p.beca=:beca )OR (p.postulante.persona.documentoIdentidad.numero =:numero AND p.beca=:beca)");
        qutbb.setParameter("nombre", "%" + nombre + "%");
        qutbb.setParameter("beca", beca);
        try {
            //long numero=Long.parseLong(nombre);

            qutbb.setParameter("numero", Long.parseLong(nombre));
        } catch (NumberFormatException e) {
            qutbb.setParameter("numero", 0);
        }
        return qutbb.getResultList();
    }

    public void exportarAExcelPostulantes() {
        Comunes.ventanaCargando(this, "excelPostulantes", "Preparandose para Exportar a Excel", null);
    }

    public void excelPostulantes() {
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Codigo Incentivos|Postulante|Beca|"
                + "Carreras|Docencias|Postgrado|Facultad|Proyecto|"
                + "Plan|Directores");

        for (PostulacionBeca p : getTodosPostulaciones()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(p.getId());
            stringBuider.append("|");
            if (p.getProyecto() != null) {
                stringBuider.append(p.getProyecto().getCodigoIncentivos());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulante() != null) {
                stringBuider.append(p.getPostulante().toString());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getBeca() != null) {
                stringBuider.append(p.getBeca().toString());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulante().getFormacionesAcademicasGrado() != null) {
                for (FormacionAcademicaGrado f : p.getPostulante().getFormacionesAcademicasGrado()) {
                    if (f.toString() != null) {
                        stringBuider.append(f.toString());
                        stringBuider.append("; ");
                    } else {
                        stringBuider.append(" ");
                    }
                }

            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulante().getDocencias() != null) {
                for (Docencia d : p.getPostulante().getDocencias()) {
                    if (d.getDedicacionDocente() != null) {
                        stringBuider.append(d.toString());
                        stringBuider.append("; ");
                    } else {
                        stringBuider.append(" ");
                    }
                }

            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");

            if (p.getPostulante().getFormacionesAcademicasPosgrado() != null) {
                for (FormacionAcademicaPosgrado f : p.getPostulante().getFormacionesAcademicasPosgrado()) {
                    if (f.getTituloPosgrado() != null) {
                        stringBuider.append(f.toString());
                        stringBuider.append("; ");
                    } else {
                        stringBuider.append(" ");
                    }
                }

            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");

            if (p.getPostulante().getDocencias() != null) {
                for (Docencia d : p.getPostulante().getDocencias()) {
                    if (d.getUnidadAcademica() != null) {
                        stringBuider.append(d.getUnidadAcademica());
                        stringBuider.append("; ");
                    } else {
                        stringBuider.append(" ");
                    }
                }

            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getProyecto() != null) {
                stringBuider.append(p.getProyecto());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPlandetrabajo() != null) {
                stringBuider.append(p.getPlandetrabajo());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getAsesores() != null) {
                for (Investigador i : p.getAsesores()) {
                    stringBuider.append(i.toString());
                    stringBuider.append("; ");
                }
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }
        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Postulantes");

    }

    public void exportarAExcelSituacionPostulantes() {
        Comunes.ventanaCargando(this, "excelSituacionPostulantes", "Preparandose para Exportar a Excel", null);
    }

    public void excelSituacionPostulantes() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Codigo Incentivos|Beca|Postulante|Proyecto|Estado|Informe de Avance|"
                + "Informe Final|Fecha|Observaciones");

        for (PostulacionBeca p : getTodosPostulaciones()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(p.getId());
            stringBuider.append("|");
            if (p.getProyecto() != null) {
                stringBuider.append(p.getProyecto().getCodigoIncentivos());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getBeca() != null) {
                stringBuider.append(p.getBeca());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulante() != null) {
                stringBuider.append(p.getPostulante().toString());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getProyecto() != null) {
                stringBuider.append(p.getProyecto());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulanteestado() != null) {
                stringBuider.append(p.getPostulanteestado().getDescripcion());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getInformeavance() != null) {
                stringBuider.append(p.getInformeavance().getDescripcion());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getInformefinal() != null) {
                stringBuider.append(p.getInformefinal().getDescripcion());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getFechaBecaInformeFinal() != null) {
                stringBuider.append(formato.format(p.getFechaBecaInformeFinal()));
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getObservaciones() != null) {
                for (Observacion o : p.getObservaciones()) {
                    if (o.getObservacion() != null) {
                        stringBuider.append(o.toString());
                        stringBuider.append("; ");
                    } else {
                        stringBuider.append(" ");
                    }
                }
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }
        // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Evaluaciones");

    }

    public List<PostulacionBeca> getTodosPostulaciones(Investigador investigador) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query query = ema.createQuery("SELECT p FROM PostulacionBeca p WHERE p.postulante=:postulante");
        query.setParameter("postulante", investigador);
        return query.getResultList();
    }

    public List<PostulacionBeca> getPostulacionesDirector(Investigador investigador) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query query = ema.createQuery("SELECT p FROM PostulacionBeca p, IN(p.asesores)a WHERE a=:investigador");
        query.setParameter("investigador", investigador);
        return query.getResultList();
    }
}
