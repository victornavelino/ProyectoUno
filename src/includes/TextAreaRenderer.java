/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author operador
 */
public class TextAreaRenderer extends JTextArea
    implements TableCellRenderer{

    public TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        this.setAutoscrolls(true);
        this.setPreferredSize(new Dimension(10,100));
        this.setEditable(false);
        //this.setMinimumSize(new Dimension(5,10));
    }
    
    public Component getTableCellRendererComponent(JTable jTable,
      Object obj, boolean isSelected, boolean hasFocus, int row,
      int column) {

        this.setText((String)obj);
        //this.setText((String)obj);

        int height_wanted = (int)getPreferredSize().getHeight();

        if (height_wanted != jTable.getRowHeight(row)){
            jTable.setRowHeight(row, height_wanted);
        }
        
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(this);
        
       // setBackground(jTable.getBackground());
        //setHorizontalAlignment(JLabel.CENTER);
        
        return sp;
    }
    
}
