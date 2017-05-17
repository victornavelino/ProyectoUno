/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.PersonaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.Persona;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.validator.routines.LongValidator;

/**
 *
 * @author Carlos
 */
public class PersonaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public void alta(Persona persona) {
        new PersonaJpaController(emf).create(persona);
    }

    public void modificar(Persona persona) {
        try {
            new PersonaJpaController(emf).edit(persona);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersonaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PersonaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new PersonaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersonaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Persona> buscarDniApellido(String busqueda) {
        List<Persona> personasEncontradas;
        if(LongValidator.getInstance().isValid(busqueda)) {
            Query quBuscarDni = em.createQuery("SELECT p FROM Persona p WHERE "
                    + "p.dni=:param");
            quBuscarDni.setParameter("param", Long.parseLong(busqueda));
            personasEncontradas = quBuscarDni.getResultList();
        } else {
            Query quBuscarApellido = em.createQuery("SELECT p FROM Persona p "
                + "WHERE p.apellido LIKE '%" + busqueda + "%'");
            personasEncontradas = quBuscarApellido.getResultList();
        }
        return personasEncontradas;
    }

    public List<Persona> getTodasPersonas() {
        Query quTodasPersonas = em.createQuery("SELECT p FROM Persona p "
                + "ORDER BY p.apellido");
        return quTodasPersonas.getResultList();
    }
    
    public Persona buscarApellidoNombre(String apellido, String nombre) {
        Query quBuscar = em.createQuery("SELECT p FROM Persona p WHERE "
                + "p.apellido=:apellido AND p.nombre=:nombre");
        quBuscar.setParameter("apellido", apellido);
        quBuscar.setParameter("nombre", nombre);
        List<Persona> personasEncontradas = quBuscar.getResultList();
        if (!personasEncontradas.isEmpty()) {
            return personasEncontradas.get(0);
        } else {
            return null;
        }
    }

    //    Inconsistencia 2.b) El campo 'IDENT' de persona o 'APELLIDO' o 'NOMBRE' o
//            'TIPO' o 'DOCUMENTO' se encuentran vacios
    public boolean estanCompletosCamposObligatorios(Persona persona) {
        boolean flag = false;
        if(!persona.getApellido().equals("")) {
            if(!persona.getNombre().equals("")) {
                if(persona.getDocumentoIdentidad() != null) {
                    flag = true;
                }
            }
        }
        return flag;
    }

}
