/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.OperacionJpaController;
import entidades.operaciones.Operacion;
import entidades.usuario.Usuario;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class OperacionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static OperacionFacade instance = null;

    protected OperacionFacade() {
    }

    public static OperacionFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new OperacionFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Operacion operacion) {
        new OperacionJpaController(emf).create(operacion);
    }

    public Operacion buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Operacion tt WHERE tt.id="
                + id);
        try {
            return (Operacion) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Operacion operacion = null;
            return operacion;
        }

    }

    public List<Operacion> listarOperacion(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Operacion tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Operacion> listarTodosOperacion() {
        Query quBuscar = em.createQuery("SELECT tt FROM Operacion tt");
        return quBuscar.getResultList();
    }

    public List<Operacion> listarTodosOperacionOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Operacion tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
    public List<Operacion> listarOperacionesUsuario(Usuario user) {
        Query quBuscar = em.createQuery("SELECT op FROM Operacion op WHERE op.usuario=:usuario ORDER BY op.fecha DESC");
        quBuscar.setParameter("usuario", user);
        return quBuscar.getResultList();
    }

}
