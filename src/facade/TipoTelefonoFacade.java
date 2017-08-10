/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TipoTelefonoJpaController;
import entidades.persona.TipoTelefono;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class TipoTelefonoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static TipoTelefonoFacade instance = null;

    protected TipoTelefonoFacade() {
    }

    public static TipoTelefonoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoTelefonoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoTelefono tipoTelefono) {
        new TipoTelefonoJpaController(emf).create(tipoTelefono);
    }

    public TipoTelefono buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoTelefono tt WHERE tt.id="
                + id);
        try {
            return (TipoTelefono) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            TipoTelefono tipoTelefono = null;
            return tipoTelefono;
        }

    }

    public List<TipoTelefono> listarTipoTelefono(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoTelefono tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<TipoTelefono> listarTodosTipoTelefono() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoTelefono tt");
        return quBuscar.getResultList();
    }

    public List<TipoTelefono> listarTodosTipoTelefonoOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoTelefono tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
