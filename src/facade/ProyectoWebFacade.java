/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ConvocatoriaJpaController;
import controladores.ProyectoVinculacionJpaController;
import controladores.ProyectoWebJpaController;
import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.ProyectoWeb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author franco
 */
public class ProyectoWebFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    private static ProyectoWebFacade instance = null;

    protected ProyectoWebFacade() {
    }

    public static ProyectoWebFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProyectoWebFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Convocatoria convocatoria) {
        new ConvocatoriaJpaController(emf).create(convocatoria);
    }

    public void editar(Convocatoria convocatoria) throws Exception {
        new ConvocatoriaJpaController(emf).edit(convocatoria);
    }

    public void eliminar(Convocatoria convocatoria) throws Exception {
        new ConvocatoriaJpaController(emf).destroy(convocatoria.getId());
    }

    public void alta(ProyectoWeb proyectoWeb) {
        new ProyectoWebJpaController(emf).create(proyectoWeb);
    }

    public void editar(ProyectoWeb proyectoWeb) throws Exception {
        new ProyectoWebJpaController(emf).edit(proyectoWeb);
    }

    public void eliminar(ProyectoWeb proyectoWeb) throws Exception {
        new ProyectoWebJpaController(emf).destroy(proyectoWeb.getId());
    }

    public void eliminar(Long id) throws Exception {
        new ProyectoWebJpaController(emf).destroy(id);
    }

    public List<ProyectoWeb> listar() {
        return new ProyectoWebJpaController(emf).findProyectoWebEntities();
    }

    public void eliminarConvocatoria(Long id) throws Exception {
        new ConvocatoriaJpaController(emf).destroy(id);
    }

    public Convocatoria buscarConvocatoria(Long id) throws Exception {
        return new ConvocatoriaJpaController(emf).findConvocatoria(id);
    }

    public ProyectoWeb buscar(Long id) throws Exception {
        return new ProyectoWebJpaController(emf).findProyectoWeb(id);
    }

    public List<ProyectoWeb> listarFinalNoAprob(Convocatoria convocatoria){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM ProyectoWeb p WHERE p.convocatoria=:convocatoria AND p.finalizado = TRUE AND p.aprobado = FALSE");
        q.setParameter("convocatoria", convocatoria);
        em.getEntityManagerFactory().getCache().evictAll();;
        return q.getResultList();
    }

    public List<ProyectoWeb> listar(Convocatoria convocatoria) throws Exception {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM ProyectoWeb p WHERE p.convocatoria=:convocatoria");
        q.setParameter("convocatoria", convocatoria);
        em.getEntityManagerFactory().getCache().evictAll();;
        return q.getResultList();
    }

    public List<Convocatoria> listarConvocatorias() {
        return new ConvocatoriaJpaController(emf).findConvocatoriaEntities();
    }
}
