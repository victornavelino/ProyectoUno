/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.PublicacionJpaController;
import entidades.proyecto.resultado.CapituloLibro;
import entidades.proyecto.resultado.Libro;
import entidades.proyecto.resultado.Publicacion;
import entidades.proyecto.resultado.TipoEdicion;
import java.util.Date;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author franco
 */
public class PublicacionTest {

    public PublicacionTest() {
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
    public void hello() {
//     Libro libro = new Libro();
//     CapituloLibro capituloLibro = new CapituloLibro();
//     libro.setAnioPublicacion("2012");
//     libro.setFechaAceptado(new Date());
//     libro.setTipoEdicion(TipoEdicion.NO_DECLARADO);
//     new PublicacionJpaController(Persistence.createEntityManagerFactory("ProyectoUnoPU")).create(libro);
//     capituloLibro.setISBN("iuuhhuhuhu");
//     capituloLibro.setLibro(libro);
//     new PublicacionJpaController(Persistence.createEntityManagerFactory("ProyectoUnoPU")).create(capituloLibro);
        JDialog dialgo = new JDialog();
        dialgo.setModal(true);
        JComboBox tipoDocComboBox = new JComboBox();
        tipoDocComboBox.setModel(new javax.swing.DefaultComboBoxModel(TipoEdicion.values()));
        tipoDocComboBox.addItem("SELECCIONE");
        tipoDocComboBox.setSelectedItem("SELECCIONE");

//cargar items
      /*
         * tipoDocComboBox.removeAllItems();
         * tipoDocComboBox.addItem("SELECCIONE"); TipoEdicion[] tipoDocList =
         * TipoEdicion.values(); for (TipoEdicion tipoDocumento : tipoDocList) {
         * tipoDocComboBox.addItem(tipoDocumento); }
         */
//fin cargar items
        dialgo.add(tipoDocComboBox);

        dialgo.pack();
        dialgo.setVisible(true);

        /*
         * en caso de querer hacer un swith
         *
         * switch (tipoDemanda){ case DNI: //codigo a realizar... break; case
         * LE: //codigo a realizar... break; case LC: //codigo a realizar...
         * break; }
         */

    }
}
