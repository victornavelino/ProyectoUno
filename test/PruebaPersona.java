/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import includes.Comunes;
import entidades.persona.Persona;
import controladores.PersonaJpaController;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import vista.participaciones.diagPersona;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class PruebaPersona {

    public PruebaPersona() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Ignore
    @Test
    public void probarPersona() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU");

        Persona persona = new PersonaJpaController(emf).findPersona(15L);//Probar 5L que no tiene todos los campos
        diagPersona proyectoModificacion = new diagPersona(null, true, "Alta");
        proyectoModificacion.setLocation(Comunes.centrarDialog(proyectoModificacion));
        proyectoModificacion.setVisible(true);
    }
}
