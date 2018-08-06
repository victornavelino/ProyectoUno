/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BloqueJpaController;
import controladores.CargoJpaController;
import controladores.ConvocatoriaWinsipJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cargo;
import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.evaluacion.Bloque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class ConvocatoriaWinsipFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static ConvocatoriaWinsipFacade instance = null;

    protected ConvocatoriaWinsipFacade() {
    }

    public static ConvocatoriaWinsipFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ConvocatoriaWinsipFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ConvocatoriaWinsip convocatoriaWinsip) {
        new ConvocatoriaWinsipJpaController(emf).create(convocatoriaWinsip);
    }

    public void modificar(ConvocatoriaWinsip convocatoriaWinsip) {
        try {
            new ConvocatoriaWinsipJpaController(emf).edit(convocatoriaWinsip);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ConvocatoriaWinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConvocatoriaWinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ConvocatoriaWinsip> getTodos() {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT c FROM ConvocatoriaWinsip c");
        ema.getEntityManagerFactory().getCache().evictAll();
        return quBuscar.getResultList();
    }

//    public List<Bloque> getBloques(String descripcion) {
//        Query quBuscar = em.createQuery("SELECT b FROM Bloque b where b.descripcion=:descripcion");
//        quBuscar.setParameter("descripcion", descripcion);
//        return quBuscar.getResultList();
//    }
    public ConvocatoriaWinsip buscar(Long id) throws Exception {
        return new ConvocatoriaWinsipJpaController(emf).findConvocatoriaWinsip(id);
    }

    public void eliminar(ConvocatoriaWinsip convocatoria) throws Exception {
        new ConvocatoriaWinsipJpaController(emf).destroy(convocatoria.getId());
    }

}
