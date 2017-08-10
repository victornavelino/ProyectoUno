/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import includes.Comunes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos
 */
public class PruebaFechas {

    public PruebaFechas() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void fechaTest() {
        Calendar calendario1 = Calendar.getInstance();
        calendario1.setTime(new Date());
        Calendar calendario2 = Calendar.getInstance();
        calendario2.setTime(Comunes.addDate(calendario1.getTime(), Calendar.DAY_OF_MONTH, 1));
        System.out.println(calendario1.getTime());
        System.out.println(calendario2.getTime());
    }

}