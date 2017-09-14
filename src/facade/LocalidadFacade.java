/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.LocalidadJpaController;
import entidades.localidad.Departamento;
import entidades.localidad.Localidad;
import entidades.localidad.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class LocalidadFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static LocalidadFacade instance = null;

    protected LocalidadFacade() {
    }

    public static LocalidadFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new LocalidadFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Localidad localidad) {
        new LocalidadJpaController(emf).create(localidad);
    }

    public void modificar(Localidad localidad) throws Exception {
        new LocalidadJpaController(emf).edit(localidad);
    }

    public void eliminar(Long localidad) throws Exception {
        new LocalidadJpaController(emf).destroy(localidad);
    }

    public Localidad buscar(long id) {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.id="
                + id);
        try {
            return (Localidad) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Localidad localidad = null;
            return localidad;
        }

    }

    public List<Localidad> listarLocalidad(String text) {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Localidad> listarTodosLocalidad() {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l");
        return quBuscar.getResultList();
    }

    public List<Localidad> listarTodosLocalidadOrdenados() {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l ORDER BY l.descripcion");
        return quBuscar.getResultList();
    }

    public List<Localidad> listarTodosLocalidadOrdenados(Departamento departamento) {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.departamento "
                + "= :departamento ORDER BY l.descripcion");
        quBuscar.setParameter("departamento", departamento);
        return quBuscar.getResultList();
    }

    public List<Localidad> listarLocalidadesCatamarca() {
        Provincia catamarca = ProvinciaFacade.getInstance().buscar(2L);
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.departamento.provincia "
                + "= :provincia ORDER BY l.descripcion");
        quBuscar.setParameter("provincia", catamarca);
        return quBuscar.getResultList();
    }

    public List<Localidad> listarLocalidadesCatamarca(String text) {
        Provincia catamarca = ProvinciaFacade.getInstance().buscar(2L);
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.departamento.provincia "
                + "= :provincia AND l.descripcion LIKE '%"
                + text + "%' ORDER BY l.descripcion");
        quBuscar.setParameter("provincia", catamarca);
        return quBuscar.getResultList();
    }

    public Localidad buscar(String localidad) {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.descripcion "
                + "= :localidad");
        quBuscar.setParameter("localidad", localidad);
        try {
            return (Localidad) quBuscar.getResultList().get(0);
        } catch (NoResultException ex) {
            Localidad localidad1 = null;
            return localidad1;
        }
    }

    public boolean existeLocalidad(String localidad) {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.descripcion "
                + "= :localidad");
        quBuscar.setParameter("localidad", localidad);
        if (quBuscar.getResultList().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existeLocalidad(String localidad, Departamento departamento) {
        Query quBuscar = em.createQuery("SELECT l FROM Localidad l WHERE l.descripcion "
                + "= :localidad AND l.departamento "
                + "= :departamento");
        quBuscar.setParameter("localidad", localidad);
        quBuscar.setParameter("departamento", departamento);
        if (quBuscar.getResultList().size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
