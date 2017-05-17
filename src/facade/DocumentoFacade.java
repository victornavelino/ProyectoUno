/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DocumentoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Documento;
import entidades.Resolucion;
import entidades.categorizacion.Categorizacion;
import entidades.categorizacion.Winsip;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author huguito
 */
public class DocumentoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    DocumentoJpaController documentoJpaController = new DocumentoJpaController(emf);
    private static DocumentoFacade instance = null;

    public DocumentoFacade() {
    }

    public static DocumentoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DocumentoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Documento documento) {
        new DocumentoJpaController(emf).create(documento);
    }

    public void modificar(Documento documento) {
        try {
            new DocumentoJpaController(emf).edit(documento);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DocumentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DocumentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Long id) {
        try {
            new DocumentoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DocumentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Documento buscar(long id) {
        return new DocumentoJpaController(emf).findDocumento(id);
    }

    public List<Documento> getTodos() {
        return new DocumentoJpaController(emf).findDocumentoEntities();
    }

    public String getDocumentosDeCategorizacion(Categorizacion c) {
        String resultado = "";
        Query quBuscar = em.createQuery("SELECT r.nombreArchivo FROM Categorizacion c, "
                + "IN (c.resoluciones) r  WHERE c.id = :c ");
        quBuscar.setParameter("c", c.getId());
        for (Object s : quBuscar.getResultList()) {
            resultado += s + " ";
        }
        return resultado;
    }

    public String getNombreEvaluacionProyectoDeWinsip(Winsip winsip) {
        Query quBuscar = em.createQuery("SELECT w.evaluacionProyecto.documento.nombreArchivo FROM Winsip w  WHERE w = :winsip ");
        quBuscar.setParameter("winsip", winsip);
        return quBuscar.getSingleResult().toString();
    }

    public String getNombreEvaluacionIntegrantesDeWinsip(Winsip winsip) {
        Query quBuscar = em.createQuery("SELECT w.evaluacionIntegrantes.documento.nombreArchivo FROM Winsip w WHERE w = :winsip ");
        quBuscar.setParameter("winsip", winsip);
        return quBuscar.getSingleResult().toString();
    }
}
