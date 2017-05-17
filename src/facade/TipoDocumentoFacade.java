/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TipoDocumentoJpaController;
import entidades.persona.TipoDocumento;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class TipoDocumentoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static TipoDocumentoFacade instance = null;

    protected TipoDocumentoFacade() {
    }

    public static TipoDocumentoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoDocumentoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoDocumento tipoDocumento) {
        new TipoDocumentoJpaController(emf).create(tipoDocumento);
    }

    public TipoDocumento buscar(long id) {
        Query quBuscar = em.createQuery("SELECT td FROM TipoDocumento td WHERE td.id="
                + id);
        try {
            return (TipoDocumento) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            TipoDocumento tipoDocumento = null;
            return tipoDocumento;
        }

    }

    public List<TipoDocumento> listarTipoDocumento(String text) {
        Query quBuscar = em.createQuery("SELECT td FROM TipoDocumento td WHERE td.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<TipoDocumento> listarTodosTipoDocumento() {
        Query quBuscar = em.createQuery("SELECT td FROM TipoDocumento td");
        return quBuscar.getResultList();
    }

    public List<TipoDocumento> listarTodosTipoDocumentoOrdenados() {
        Query quBuscar = em.createQuery("SELECT td FROM TipoDocumento td ORDER BY td.descripcion");
        return quBuscar.getResultList();
    }
}
