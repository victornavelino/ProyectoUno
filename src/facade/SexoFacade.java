/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.SexoJpaController;
import entidades.persona.Sexo;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class SexoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static SexoFacade instance = null;

    protected SexoFacade() {
    }

    public static SexoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new SexoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Sexo sexo) {
        new SexoJpaController(emf).create(sexo);
    }

    public Sexo buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Sexo tt WHERE tt.id="
                + id);
        try {
            return (Sexo) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Sexo sexo = null;
            return sexo;
        }

    }

    public List<Sexo> listarSexo(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Sexo tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }
    
    

    public List<Sexo> listarTodosSexo() {
        Query quBuscar = em.createQuery("SELECT tt FROM Sexo tt");
        return quBuscar.getResultList();
    }

    public List<Sexo> listarTodosSexoOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Sexo tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }
}
