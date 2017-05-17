/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importador.categorizacion;

import entidades.persona.investigador.Investigador;
import facade.InvestigadorFacade;
import facade.PersonaFacade;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Administrador
 */
public class InvestigadoresEncontrados {

    public static void main(String args[]) {
        int investigadoresEncontrados = 0;
        int investigadorNoEncontrados = 0;
        InputStream in = null;
        try {
            in = new FileInputStream("d:\\InvestigadoresUNCa.xls");
            Workbook workbook = Workbook.getWorkbook(in);
            Sheet sheet = workbook.getSheet(1);
            for (int i = 3; i < 781; i++) {
                Cell cell = sheet.getCell(2, i);
                String apellido = cell.getContents();
                Cell cell2 = sheet.getCell(3, i);
                String nombre = cell2.getContents();
                Investigador investigador = InvestigadorFacade.getInstance().buscarApellidoNombre(apellido, nombre);
                if(investigador != null) {
                    System.out.println(apellido + ", " + nombre + " - ENCONTRADO");
                    investigadoresEncontrados++;
                } else {
                    System.out.println(apellido + ", " + nombre + " - NO ENCONTRADO");
                    investigadorNoEncontrados++;
                }
            }
            System.out.println("Investigadores Encontrados = " + investigadoresEncontrados);
            System.out.println("Investigadores No Encontrados = " + investigadorNoEncontrados);
        } catch (IOException ex) {
            Logger.getLogger(InvestigadoresEncontrados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(InvestigadoresEncontrados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(InvestigadoresEncontrados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}