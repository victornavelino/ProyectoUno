/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.usuario.Usuario;
import facade.UsuarioFacade;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import vista.frPrincipal;
import static org.junit.Assert.*;

/**
 *
 * @author carlos
 */
public class PrincipalTest {

    public PrincipalTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void pricipalTest() {
        Usuario usuario = new UsuarioFacade().buscar(1L);
        frPrincipal principal = new frPrincipal();
        principal.setVisible(true);
    }

}