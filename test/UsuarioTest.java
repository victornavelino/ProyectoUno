/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.usuario.Grupo;
import facade.UsuarioFacade;
import entidades.usuario.Usuario;
import facade.GrupoFacade;
import includes.SHA1;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class UsuarioTest {

    public UsuarioTest() {
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

    @Test
    public void AltaUsuario() {
        try {
            SHA1 sha1 = new SHA1();
            Usuario usuario = new Usuario();
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            usuario.setNombreCompleto("Administrador Principal");
            usuario.setNombreUsuario("panchi");
            usuario.setContrasena("123");
            usuario.setContrasena(sha1.getHash(usuario.getContrasena()));
            Grupo grupo = new Grupo();
            grupo.setNombre("admin");
            grupo.setDescripcion("Administradores");
            GrupoFacade.getInstance().alta(grupo);
            usuario.setGrupo(grupo);
            usuarioFacade.alta(usuario);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
