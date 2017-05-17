/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.investigadores.cursosdictados;

import entidades.persona.investigador.curso.Asignatura;
import entidades.persona.investigador.curso.CarreraAsignatura;
import entidades.persona.investigador.curso.FacultadAsignatura;
import facade.persona.investigador.curso.AsignaturaFacade;
import facade.persona.investigador.curso.CarreraAsignaturaFacade;
import facade.persona.investigador.curso.FacultadAsignaturaFacade;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author franco
 */
public class ImportarDatosCursosDictados {

    public ImportarDatosCursosDictados() {
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
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(chooser);
            File selectedFile = chooser.getSelectedFile();
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Call the printToConsole method to print the cell data in the console.
         */
        printToConsole(cellDataList);
    }

    /**
     * This method is used to print the cell data to the console.
     *
     * @param cellDataList - List of the data's in the spreadsheet.
     */
//    private void printToConsole(List cellDataList) {
//        for (int i = 0; i < cellDataList.size(); i++) {
//            List cellTempList = (List) cellDataList.get(i);
//            for (int j = 0; j < cellTempList.size(); j++) {
//                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
//                String stringCellValue = hssfCell.toString();
//                System.out.print(stringCellValue + "\t");
//            }
//            System.out.println();
//        }
//    }
    private void printToConsole(List cellDataList) {
        for (int i = 1; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
//0
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(0);
//            NumberFormat numberFormat = NumberFormat.getInstance();
//// trunca a 0 digitos
//            numberFormat.setMaximumFractionDigits(0);
//// le decimos al NumberFormat que el redondeado sea mediante truncamiento.
//            numberFormat.setRoundingMode(RoundingMode.DOWN);
//            String stringCellValue = numberFormat.format(Double.parseDouble(hssfCell.toString()));
            String stringCellValue = hssfCell.toString();
            System.out.print("Codigo " + stringCellValue + "\t");
            Asignatura asignatura = new Asignatura();
            asignatura.setCodigoAsignatura(stringCellValue);
            //1        
            hssfCell = (HSSFCell) cellTempList.get(1);
            stringCellValue = hssfCell.toString();
            System.out.print("asignatura " + stringCellValue + "\t");
            asignatura.setDescripcion(stringCellValue);
            //2          
            hssfCell = (HSSFCell) cellTempList.get(2);
            stringCellValue = hssfCell.toString();
            System.out.print("carrera " + stringCellValue + "\t");
            CarreraAsignatura ca = new CarreraAsignatura();
            List<CarreraAsignatura> listarCarreraAsignatura = CarreraAsignaturaFacade.getInstance().listarCarreraAsignatura(stringCellValue);
            if (!listarCarreraAsignatura.isEmpty()) {
                asignatura.setCarreraAsignatura(listarCarreraAsignatura.get(0));
            } else {
                ca.setDescripcion(stringCellValue);
                CarreraAsignaturaFacade.getInstance().alta(ca);
                asignatura.setCarreraAsignatura(ca);
            }
            //3       
            hssfCell = (HSSFCell) cellTempList.get(3);
            stringCellValue = hssfCell.toString();
            System.out.print("facultad " + stringCellValue + "\t");
            System.out.println();
            FacultadAsignatura fa = new FacultadAsignatura();
            List<FacultadAsignatura> facultadAsignaturas = FacultadAsignaturaFacade.getInstance().listarFacultadAsignatura(stringCellValue);
            if (!facultadAsignaturas.isEmpty()) {
                asignatura.setFacultadAsignatura(facultadAsignaturas.get(0));
            } else {
                fa.setDescripcion(stringCellValue);
                FacultadAsignaturaFacade.getInstance().alta(fa);
                asignatura.setFacultadAsignatura(fa);
            }
            AsignaturaFacade.getInstance().alta(asignatura);

        }
    }
}
