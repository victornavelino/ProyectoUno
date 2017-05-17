/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.proyecto.vinculacion;

import facade.*;
import controladores.NotaInternaJpaController;
import facade.proyecto.vinculacion.*;
import facade.*;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.NotaInterna;
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
public class NotaInternaFacade {
   

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    NotaInternaJpaController editorialJpaController = new NotaInternaJpaController(emf);
    private static NotaInternaFacade instance = null;
    private Query quBuscar;
    protected NotaInternaFacade() {
    }

    public static NotaInternaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NotaInternaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(NotaInterna notaInterna) {
//        if (!existeFinanciacionConNumeroDocumentoIdentidad(FinanciacionGenerica.getPersona().getDocumentoIdentidad().getTipoDocumento(),
  //              FinanciacionGenerica.getPersona().getDocumentoIdentidad().getNumero())) {
            new NotaInternaJpaController(emf).create(notaInterna);
    //    } else {
      //      System.out.println("Alta incorrecta. Ya existe un FinanciacionGenerica con ese número de documento de identidad");
     //   }
    }

    public void modificar(NotaInterna notaInterna) {
        try {
            new NotaInternaJpaController(emf).edit(notaInterna);
        } catch (Exception ex) {
            Logger.getLogger(facade.proyecto.vinculacion.NotaInternaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(NotaInterna notaInterna) {
            try {
                new NotaInternaJpaController(emf).destroy(notaInterna.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(NotaInternaFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public NotaInterna buscar(long id) {
        return new NotaInternaJpaController(emf).findNotaInterna(id);
    }


}
