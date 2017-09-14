/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ContrasenaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.Contrasena;
import entidades.persona.investigador.Investigador;
import includes.Encrypter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Fz
 */
public class ContrasenaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static ContrasenaFacade instance = null;

    protected ContrasenaFacade() {
    }

    public static ContrasenaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ContrasenaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Contrasena contrasena) throws Exception {
        String contrasenaSinConvertir = contrasena.getContrasena();
        String contrasenaConvertida = Encrypter.encriptar(contrasenaSinConvertir);
        contrasena.setContrasena(contrasenaConvertida);
        new ContrasenaJpaController(emf).create(contrasena);
    }

    public void eliminar(Contrasena contrasena) throws Exception {
        try {
            new ContrasenaJpaController(emf).destroy(contrasena.getId());
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al eliminar el usuario");
        }
    }

    public void modificarSinCambiarClave(Contrasena contrasena) throws Exception {
        try {
            new ContrasenaJpaController(emf).edit(contrasena);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al modificar el usuario");
        } catch (Exception ex) {
            throw new Exception("Se ha producido un error al modificar el usuario");
        }
    }
    
    public void modificarYCambiarClave(Contrasena contrasena) throws Exception {
        try {
            String contrasenaSinConvertir = contrasena.getContrasena();
            String contrasenaConvertida = Encrypter.encriptar(contrasenaSinConvertir);
            contrasena.setContrasena(contrasenaConvertida);
            new ContrasenaJpaController(emf).edit(contrasena);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al modificar el usuario");
        } catch (Exception ex) {
            throw new Exception("Se ha producido un error al modificar el usuario");
        }
    }

    public List<Contrasena> listar() throws Exception {
        return new ContrasenaJpaController(emf).findContrasenaEntities();
    }
    public Contrasena buscarPorInvestigador(Investigador investigador){
        Query buscar =em.createQuery("SELECT c FROM Contrasena c WHERE c.investigador = :investigador");
        buscar.setParameter("investigador", investigador);
        try{
            return (Contrasena)buscar.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
        
    }
}
