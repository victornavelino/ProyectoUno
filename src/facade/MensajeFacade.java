/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.MensajeJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.AreaSecyt;
import entidades.persona.investigador.Destinatario;
import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.Mensaje;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Fz
 */
public class MensajeFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static MensajeFacade instance = null;

    protected MensajeFacade() {
    }

    public static MensajeFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new MensajeFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Mensaje mensaje) throws Exception {
        new MensajeJpaController(emf).create(mensaje);
    }

    public void eliminar(Mensaje mensaje) throws Exception {
        try {
            new MensajeJpaController(emf).destroy(mensaje.getId());
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al eliminar el mensaje");
        }
    }

    public void modificar(Mensaje mensaje) throws Exception {
        try {
            new MensajeJpaController(emf).edit(mensaje);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al modificar el mensaje");
        } catch (Exception ex) {
            throw new Exception("Se ha producido un error al modificar el mensaje");
        }
    }

    public List<Mensaje> listar() throws Exception {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m"
                + " WHERE m.destinatario =:dest AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    public List<Mensaje> listarEnviados() throws Exception {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m"
                + " WHERE m.destinatario =:dest AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("dest", Destinatario.INVESTIGADOR);
        return quMensaje.getResultList();
    }

    /**
     * Lista todos los mensajes no Leidos
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Mensaje> listarNoLeidos() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.leido = FALSE AND m.borrado=false AND m.destinatario =:dest "
                + "ORDER BY m.id DESC");
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista todos los mensajes no Leidos
     */
    public List<Mensaje> listarLeidos() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.leido = TRUE AND m.borrado=false AND m.destinatario =:dest "
                + "ORDER BY m.id DESC");
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista los mensajes no Leidos en una area en particular
     */
    public List<Mensaje> listarNoLeidos(AreaSecyt areaSecyt) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.leido = FALSE AND m.destinatario =:dest AND m.areaSecyt =:areaSecyt AND m.borrado=false "
                + "ORDER BY m.id DESC");
        quMensaje.setParameter("areaSecyt", areaSecyt);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista los mensajes Leidos en una area en particular
     */
    public List<Mensaje> listarLeidos(AreaSecyt areaSecyt) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.leido = TRUE AND m.destinatario =:dest AND m.areaSecyt =:areaSecyt AND m.borrado=false "
                + "ORDER BY m.id DESC");
        quMensaje.setParameter("areaSecyt", areaSecyt);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista todos los mensajes en una area en particular
     */
    public List<Mensaje> listarTodos(AreaSecyt areaSecyt) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.areaSecyt =:areaSecyt AND m.destinatario =:dest AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("areaSecyt", areaSecyt);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista los mensajes no Leidos de un investigador en particular
     */
    public List<Mensaje> listarNoLeidos(Investigador investigador) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.leido = FALSE AND m.destinatario =:dest AND m.investigador =:investigador AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("investigador", investigador);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista los mensajes Leidos de un investigador en particular
     */
    public List<Mensaje> listarLeidos(Investigador investigador) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.leido = TRUE AND m.destinatario =:dest AND m.investigador =:investigador AND m.borrado=false "
                + "ORDER BY m.id DESC");
        quMensaje.setParameter("investigador", investigador);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    /**
     * Lista todos los mensajes de un investigador en particular
     */
    public List<Mensaje> listarTodos(Investigador investigador) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.investigador =:investigador AND m.destinatario =:dest AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("investigador", investigador);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    public List<Mensaje> buscarPorNombre(String apellido) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.investigador.persona.apellido LIKE '%" + apellido + "%' AND m.destinatario =:dest AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    public List<Mensaje> buscarPorNombreYArea(String apellido, AreaSecyt area) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.areaSecyt=:area AND m.destinatario=:dest AND m.investigador.persona.apellido LIKE '%" + apellido + "%' "
                + "AND m.borrado=false ORDER BY m.leido ASC");
        quMensaje.setParameter("area", area);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    public Mensaje buscar(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.id =:id");
        quMensaje.setParameter("id", id);
        //quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return (Mensaje) quMensaje.getSingleResult();
    }

    public String getCantidadMjesNoLeidos(String area) {
        AreaSecyt areaSecyt = AreaSecytFacade.getInstance().buscar(area);
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT Count(m) FROM Mensaje m "
                + "WHERE m.leido = FALSE and m.areaSecyt=:areaSecyt AND m.borrado=false AND m.destinatario=:dest");
        quMensaje.setParameter("areaSecyt", areaSecyt);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getSingleResult().toString();
    }

    public String getCantidadMjesNoLeidos() {

        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT Count(m) FROM Mensaje m "
                + "WHERE m.leido = FALSE AND m.borrado=false AND m.destinatario=:dest");
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getSingleResult().toString();
    }

    public List<Mensaje> listarBorrados() throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "Where m.borrado=true AND m.destinatario=:dest ORDER BY m.id DESC");
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    public List<Mensaje> listarBorrados(AreaSecyt areaSecyt) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m "
                + "WHERE m.areaSecyt =:areaSecyt AND m.destinatario =:dest AND m.borrado=true "
                + "ORDER BY m.id DESC");
        quMensaje.setParameter("areaSecyt", areaSecyt);
        quMensaje.setParameter("dest", Destinatario.AREASECYT);
        return quMensaje.getResultList();
    }

    public List<Mensaje> listarEnviados(AreaSecyt areaSecyt) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.destinatario =:dest AND m.areaSecyt=:areaSecyt AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("areaSecyt", areaSecyt);
        quMensaje.setParameter("dest", Destinatario.INVESTIGADOR);
        return quMensaje.getResultList();
    }

    public List<Mensaje> listarEnviados(Investigador investigador) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.destinatario =:dest AND m.areaSecyt=:areaSecyt AND m.investigador=:investigador AND m.borrado=false "
                + "ORDER BY m.leido ASC");
        quMensaje.setParameter("investigador", investigador);
        quMensaje.setParameter("dest", Destinatario.INVESTIGADOR);
        return quMensaje.getResultList();
    }

    public List<Mensaje> buscarPorInvestigador(Investigador investigador) {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.investigador=:investigador");
        quMensaje.setParameter("investigador", investigador);
        return quMensaje.getResultList();
    }
}
