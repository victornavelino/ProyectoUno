/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CobroJpaController;
import controladores.ParticipacionWebJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.economico.Cobro;
import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.ParticipacionWeb;
import entidades.proyectoWeb.ProyectoWeb;
import includes.Comunes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
public class ParticipacionWebFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ParticipacionWebJpaController participacionWebJpaController = new ParticipacionWebJpaController(emf);

    private static ParticipacionWebFacade instance = null;

    protected ParticipacionWebFacade() {
    }

    public static ParticipacionWebFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ParticipacionWebFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public ParticipacionWeb getDirector(ProyectoWeb proyectoWeb) {
        try {
            Query quDirector = em.createQuery("SELECT pa FROM ParticipacionWeb pa WHERE"
                    + " pa.rol.descripcion=:director AND ((pa.fechaHasta IS NULL  OR pa.fechaHasta > :fecha ) OR pa.proyectoWeb.fechaFinalizacion < :fecha )"
                    + "AND pa.proyectoWeb.id=:proye ORDER BY pa.fechaHasta DESC");
            quDirector.setParameter("director", "Director");
            quDirector.setParameter("fecha", Comunes.obtenerFechaActualDesdeDB());
            quDirector.setParameter("proye", proyectoWeb.getId());
            return (ParticipacionWeb) quDirector.getResultList().get(0);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
      public List<ParticipacionWeb> getParticipacionesWeb(Convocatoria convocatoria) {
        try {
            Query quParticip = em.createQuery("SELECT pa FROM ParticipacionWeb pa WHERE pa.proyectoWeb.convocatoria=:convocatoria ORDER BY pa.investigador.persona.apellido ASC");
            quParticip.setParameter("convocatoria", convocatoria);
            return quParticip.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

}
