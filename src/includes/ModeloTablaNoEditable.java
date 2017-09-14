/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package includes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlos
 */
public class ModeloTablaNoEditable extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
        /*// Aquí devolvemos true o false según queramos que una celda
        // identificada por fila,columna (row,column), sea o no editable
        if (column == 0) {
        return true;
        }*/
        return false;
    }
}
