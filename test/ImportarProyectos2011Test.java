/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.proyecto.Proyecto;
import facade.ProyectoFacade;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Carlos
 */
public class ImportarProyectos2011Test {

    public ImportarProyectos2011Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void importarProyectosTest() {
        try {
            InputStream in = new FileInputStream("C:\\Users\\Carlos\\Downloads\\PROYECTOS2011.xls");
            Workbook workbook = Workbook.getWorkbook(in);
            Sheet sheet = workbook.getSheet(1);
            for (int i = 1; i < 73; i++) {
                Cell cell = sheet.getCell(0, i);
                String codigoIncentivo = cell.getContents();
                Cell cell1 = sheet.getCell(1, i);
                String titulo = cell1.getContents();
                Cell cell3 = sheet.getCell(3, i);
                String fechaInicio = cell3.getContents();
                Date fechaInicioDate = null;
                Date fechaFinalizacionDate = null;
                //Corrector fecha inicio
                if (!fechaInicio.equals("")) {
                    String[] elementosFechaInicio = fechaInicio.split("/");
                    Calendar fechaInicioCalendar = Calendar.getInstance();
                    fechaInicioCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(elementosFechaInicio[0]));
                    fechaInicioCalendar.set(Calendar.MONTH, (Integer.parseInt(elementosFechaInicio[1]) - 1));
                    fechaInicioCalendar.set(Calendar.YEAR, (Integer.parseInt(elementosFechaInicio[2]) + 2000));
                    fechaInicioDate = fechaInicioCalendar.getTime();
                }

                Cell cell4 = sheet.getCell(4, i);
                String fechaFinalizacion = cell4.getContents();
                //Corrector fecha finalizacion
                if (!fechaFinalizacion.equals("")) {
                    String[] elementosFechaFinalizacion = fechaFinalizacion.split("/");
                    Calendar fechaFinalizacionCalendar = Calendar.getInstance();
                    fechaFinalizacionCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(elementosFechaFinalizacion[0]));
                    fechaFinalizacionCalendar.set(Calendar.MONTH, (Integer.parseInt(elementosFechaFinalizacion[1]) - 1));
                    fechaFinalizacionCalendar.set(Calendar.YEAR, (Integer.parseInt(elementosFechaFinalizacion[2]) + 2000));
                    fechaFinalizacionDate = fechaFinalizacionCalendar.getTime();
                }

                Proyecto proyecto = new Proyecto();
                proyecto.setCodigoIncentivos(codigoIncentivo);
                proyecto.setTitulo(titulo);
                proyecto.setFechaInicio(fechaInicioDate);
                proyecto.setFechaFinalizacion(fechaFinalizacionDate);
                ProyectoFacade.getInstance().alta(proyecto);
            }
            workbook.close();
            System.out.println("Importación finalizada con éxito");

//            Cell cell2 = sheet.getCell(1, 0);
//            String strNum = cell2.getContents();
        } catch (IOException ex) {
            Logger.getLogger(ImportarProyectos2011Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ImportarProyectos2011Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
