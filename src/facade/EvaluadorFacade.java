/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EvaluadorJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.Evaluador;
import entidades.persona.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.validator.routines.LongValidator;

/**
 *
 * @author Administrador
 */
public class EvaluadorFacade {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EvaluadorJpaController evaluadorJpaController = new EvaluadorJpaController(emf);
    private static EvaluadorFacade instance = null;

    protected EvaluadorFacade() {
    }

    public static EvaluadorFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluadorFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Evaluador evaluador) {
//        if (!existeEvaluadorConNumeroDocumentoIdentidad(evaluador.getPersona().getDocumentoIdentidad().getTipoDocumento(),
  //              evaluador.getPersona().getDocumentoIdentidad().getNumero())) {
            new EvaluadorJpaController(emf).create(evaluador);
    //    } else {
      //      System.out.println("Alta incorrecta. Ya existe un evaluador con ese número de documento de identidad");
     //   }
    }

    public void modificar(Evaluador evaluador) {
        try {
            new EvaluadorJpaController(emf).edit(evaluador);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluadorFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluadorFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Evaluador evaluador) {
        try {
            new EvaluadorJpaController(emf).destroy(evaluador.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluadorFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Evaluador buscar(long id) {
        return new EvaluadorJpaController(emf).findEvaluador(id);
    }

    public Evaluador getUltimoEvaluador() {
        Query quEvaluador = em.createQuery("SELECT i FROM Evaluador i");
        List<Evaluador> evaluadores = quEvaluador.getResultList();
        if (!evaluadores.isEmpty()) {
            return evaluadores.get(evaluadores.size() - 1);
        } else {
            return null;
        }
    }

    public List<Evaluador> getTodosEvaluador() {
        Query quTodosEvaluador = em.createQuery("SELECT i FROM Evaluador i "
                + "ORDER BY i.investigador.persona.apellido");
        return quTodosEvaluador.getResultList();
    }

    public boolean existePersonaEvaluador(Evaluador evaluador) {
        Query quExistePersonaEvaluador = em.createQuery("SELECT i FROM "
                + "Evaluador i WHERE i.investigador.id=" + evaluador.getInvestigador().getId());
        List<Evaluador> evaluadoresEncontrados =
                quExistePersonaEvaluador.getResultList();
        if (!evaluadoresEncontrados.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List<Evaluador> getEvaluadores(String apellidoNombre) {
        Query quTodosEvaluador = em.createQuery("SELECT i FROM Evaluador i "
                + "WHERE i.investigador.persona.apellido LIKE '%" + apellidoNombre
                + "%' ORDER BY i.investigador.persona.apellido");
        return quTodosEvaluador.getResultList();
    }

    public List<Evaluador> buscarDniApellido(String busqueda) {
        List<Evaluador> evaluadoresEncontrados;
        if (LongValidator.getInstance().isValid(busqueda)) {
            Query quBuscarDni = em.createQuery("SELECT i FROM Evaluador i WHERE "
                    + "i.investigador.persona.dni=:param");
            quBuscarDni.setParameter("param", Long.parseLong(busqueda));
            evaluadoresEncontrados = quBuscarDni.getResultList();
        } else {
            Query quBuscarApellido = em.createQuery("SELECT i FROM Evaluador i "
                    + "WHERE i.investigador.persona.apellido LIKE '%" + busqueda + "%'");
            evaluadoresEncontrados = quBuscarApellido.getResultList();
        }
        return evaluadoresEncontrados;
    }

    public List<Evaluador> filtrar(List<Evaluador> evaluadores, String apellidoNombre) {
        Query quBuscar = em.createQuery("SELECT i FROM Evaluador i "
                + "WHERE i.investigador.persona.apellido LIKE '%" + apellidoNombre + "%' OR "
                + "i.investigador.persona.nombre LIKE '%" + apellidoNombre + "%'");
        List<Evaluador> evaluadoresFiltrados = quBuscar.getResultList();
        List<Evaluador> evaluadoresFueraFiltrados = new ArrayList<Evaluador>();
        for (Evaluador evaluador : evaluadores) {
            if (evaluadoresFiltrados.contains(evaluador)) {
                evaluadoresFueraFiltrados.add(evaluador);
            }
        }
        return evaluadoresFueraFiltrados;
    }

    public List<Evaluador> buscarPorApellidoNombre(String apellidoNombre) {
        Query quBuscar = em.createQuery("SELECT i FROM Evaluador i "
                + "WHERE i.investigador.persona.apellido LIKE '%" + apellidoNombre + "%' OR "
                + "i.investigador.persona.nombre LIKE '%" + apellidoNombre + "%'");
        return quBuscar.getResultList();
    }

    public Evaluador buscarPorDocumentoIdentidad(TipoDocumento tipoDocumento, long numeroDocumentoIdentidad) {
        Query quBuscar = em.createQuery("SELECT i FROM Evaluador i "
                + "WHERE i.investigador.persona.documentoIdentidad.tipoDocumento.id = :idTipoDocumento AND "
                + "i.investigador.persona.documentoIdentidad.numero = :numeroDocumentoIdentidad");
        quBuscar.setParameter("idTipoDocumento", tipoDocumento.getId());
        quBuscar.setParameter("numeroDocumentoIdentidad", numeroDocumentoIdentidad);
        return (Evaluador) quBuscar.getSingleResult();
    }

    public List<Evaluador> buscarPorNumeroDocumentoIdentidad(long numeroDocumentoIdentidad) {
        Query quBuscar = em.createQuery("SELECT i FROM Evaluador i "
                + "WHERE i.investigador.persona.documentoIdentidad.numero = :numeroDocumentoIdentidad");
        quBuscar.setParameter("numeroDocumentoIdentidad", numeroDocumentoIdentidad);
        return quBuscar.getResultList();
    }

    public boolean existeEvaluadorConNumeroDocumentoIdentidad(TipoDocumento tipoDocumento, long numeroDocumentoIdentidad) {
        if (buscarPorDocumentoIdentidad(tipoDocumento, numeroDocumentoIdentidad) != null) {
            return true;
        } else {
            return false;
        }
    }

}
