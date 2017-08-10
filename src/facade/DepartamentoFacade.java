/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DepartamentoJpaController;
import entidades.localidad.Departamento;
import entidades.localidad.Provincia;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class DepartamentoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DepartamentoFacade instance = null;

    protected DepartamentoFacade() {
    }

    public static DepartamentoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DepartamentoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Departamento departamento) {
        new DepartamentoJpaController(emf).create(departamento);
    }

    public Departamento buscar(long id) {
        Query quBuscar = em.createQuery("SELECT p FROM Departamento p WHERE p.id="
                + id);
        try {
            return (Departamento) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Departamento departamento = null;
            return departamento;
        }

    }

    public List<Departamento> listarDepartamento(String text) {
        Query quBuscar = em.createQuery("SELECT p FROM Departamento p WHERE p.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Departamento> listarTodosDepartamento() {
        Query quBuscar = em.createQuery("SELECT p FROM Departamento p");
        return quBuscar.getResultList();
    }

    public List<Departamento> listarTodosDepartamentoOrdenados() {
        Query quBuscar = em.createQuery("SELECT p FROM Departamento p ORDER BY p.descripcion");
        return quBuscar.getResultList();
    }

    public List<Departamento> listarTodosDepartamentoOrdenados(Provincia provincia) {
        Query quBuscar = em.createQuery("SELECT p FROM Departamento p WHERE p.provincia = :provincia ORDER BY p.descripcion");
        quBuscar.setParameter("provincia", provincia);
        return quBuscar.getResultList();
    }
}
