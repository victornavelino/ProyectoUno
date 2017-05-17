/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.AreaSecytJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.AreaSecyt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Fz
 */
public class AreaSecytFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static AreaSecytFacade instance = null;

    protected AreaSecytFacade() {
    }

    public static AreaSecytFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AreaSecytFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(AreaSecyt areaSecyt) throws Exception {
        new AreaSecytJpaController(emf).create(areaSecyt);
    }

    public void eliminar(AreaSecyt areaSecyt) throws Exception {
        try {
            new AreaSecytJpaController(emf).destroy(areaSecyt.getId());
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al eliminar el área");
        }
    }

    public void modificar(AreaSecyt areaSecyt) throws Exception {
        try {
            new AreaSecytJpaController(emf).edit(areaSecyt);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al modificar el área");
        } catch (Exception ex) {
            throw new Exception("Se ha producido un error al modificar el área");
        }
    }

    public AreaSecyt buscar(String descripcion) {
        try {
            Query quBuscar = em.createQuery("SELECT a FROM AreaSecyt a WHERE a.descripcion = :descripcion");
            quBuscar.setParameter("descripcion", descripcion);
            return (AreaSecyt) quBuscar.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<AreaSecyt> listar() throws Exception {
        return new AreaSecytJpaController(emf).findAreaSecytEntities();
    }
}
