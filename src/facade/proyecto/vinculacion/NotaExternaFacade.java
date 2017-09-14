/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.proyecto.vinculacion;

import facade.*;
import controladores.NotaExternaJpaController;
import facade.proyecto.vinculacion.*;
import facade.*;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.NotaExterna;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Panchi
 */
public class NotaExternaFacade {
   

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    NotaExternaJpaController editorialJpaController = new NotaExternaJpaController(emf);
    private static NotaExternaFacade instance = null;
    private Query quBuscar;
    protected NotaExternaFacade() {
    }

    public static NotaExternaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NotaExternaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(NotaExterna notaExterna) {
//        if (!existeFinanciacionConNumeroDocumentoIdentidad(FinanciacionGenerica.getPersona().getDocumentoIdentidad().getTipoDocumento(),
  //              FinanciacionGenerica.getPersona().getDocumentoIdentidad().getNumero())) {
            new NotaExternaJpaController(emf).create(notaExterna);
    //    } else {
      //      System.out.println("Alta incorrecta. Ya existe un FinanciacionGenerica con ese número de documento de identidad");
     //   }
    }

    public void modificar(NotaExterna notaExterna) {
        try {
            new NotaExternaJpaController(emf).edit(notaExterna);
        } catch (Exception ex) {
            Logger.getLogger(facade.proyecto.vinculacion.NotaExternaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(NotaExterna notaExterna) {
            try {
                new NotaExternaJpaController(emf).destroy(notaExterna.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(NotaExternaFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public NotaExterna buscar(long id) {
        return new NotaExternaJpaController(emf).findNotaExterna(id);
    }

}
