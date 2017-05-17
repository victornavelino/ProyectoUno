/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ProyectoDatosComplementariosJpaController;
import entidades.proyecto.ProyectoDatosComplementarios;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hugo
 */
public class ProyectoDatosComplementariosFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ProyectoDatosComplementariosJpaController proyectoDCJpaController = new ProyectoDatosComplementariosJpaController(emf);
    private static ProyectoDatosComplementariosFacade instance = null;

    protected ProyectoDatosComplementariosFacade() {
    }

    public static ProyectoDatosComplementariosFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ProyectoDatosComplementariosFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ProyectoDatosComplementarios proyectoDatosComplementarios) {
        new ProyectoDatosComplementariosJpaController(emf).create(proyectoDatosComplementarios);
    }

    public void modificar(ProyectoDatosComplementarios proyectoDatosComplementarios) {
        try {
            new ProyectoDatosComplementariosJpaController(emf).edit(proyectoDatosComplementarios);
        } catch (Exception ex) {
            System.out.println("Erro: No se puede modificar los datos complementarios");
        }
    }
}
