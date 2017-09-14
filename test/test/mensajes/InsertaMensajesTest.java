/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.mensajes;

import entidades.persona.investigador.AreaSecyt;
import entidades.persona.investigador.Destinatario;
import entidades.persona.investigador.Mensaje;
import facade.AreaSecytFacade;
import facade.InvestigadorFacade;
import facade.MensajeFacade;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author huguito
 */
public class InsertaMensajesTest {
    
    public InsertaMensajesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    @Test
    public void hello() throws Exception {
        
        Mensaje mensaje = new Mensaje();
        System.out.println("areaaaaaa poryectoooooooo: "+AreaSecytFacade.getInstance().listar());
        mensaje.setAreaSecyt(AreaSecytFacade.getInstance().buscar("PROYECTO"));
        mensaje.setInvestigador(InvestigadorFacade.getInstance().buscar(854L));
        mensaje.setDescripcion("PRUEBAAAA");
        mensaje.setFechaEnvio(new Date());
        mensaje.setLeido(false);
        mensaje.setDestinatario(Destinatario.INVESTIGADOR);
        MensajeFacade.getInstance().alta(mensaje);
        mensaje = new Mensaje();
        
        mensaje.setAreaSecyt(AreaSecytFacade.getInstance().buscar("INCENTIVOS"));
        mensaje.setInvestigador(InvestigadorFacade.getInstance().buscar(854L));
        mensaje.setDescripcion("OTRA PRUEBAAAAAA");
        mensaje.setFechaEnvio(new Date());
        mensaje.setLeido(true);
        mensaje.setDestinatario(Destinatario.AREASECYT);
        MensajeFacade.getInstance().alta(mensaje);
    }
}