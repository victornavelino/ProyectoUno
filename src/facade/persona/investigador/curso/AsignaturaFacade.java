/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.persona.investigador.curso;

import controladores.AsignaturaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.Asignatura;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class AsignaturaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static AsignaturaFacade instance = null;

    protected AsignaturaFacade() {
    }

    public static AsignaturaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AsignaturaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Asignatura asignatura) {
        new AsignaturaJpaController(emf).create(asignatura);
    }

    public void modificar(Asignatura asignatura) {
        try {
            new AsignaturaJpaController(emf).edit(asignatura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Asignatura buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM Asignatura tt WHERE tt.id="
                + id);
        try {
            return (Asignatura) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            Asignatura asignatura = null;
            return asignatura;
        }

    }

    public List<Asignatura> listarAsignatura(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM Asignatura tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<Asignatura> listarTodosAsignatura() {
        Query quBuscar = em.createQuery("SELECT tt FROM Asignatura tt");
        return quBuscar.getResultList();
    }

    public List<Asignatura> listarTodosAsignaturaOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM Asignatura tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public boolean existeCodigo(String codigo) {
        boolean flag;
        Query quBuscar = em.createQuery("SELECT a FROM Asignatura a "
                + "WHERE a.codigoAsignatura=:codigo");
        quBuscar.setParameter("codigo", codigo.trim());
        try{
            Asignatura asignatura=(Asignatura)quBuscar.getSingleResult();
            flag=true;
        }
        catch(NoResultException ex){
            flag=false;
        }
        return flag;
    }
    public void eliminar(Asignatura asignatura){
        try {
            new AsignaturaJpaController(emf).destroy(asignatura.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
