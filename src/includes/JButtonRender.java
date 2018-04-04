/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package includes;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author AFerSor
 */
public class JButtonRender extends JButton implements TableCellRenderer{

    boolean isBordered = true;

    public JButtonRender(boolean isBordered, String texto, ImageIcon icono) {
        this.isBordered = isBordered;

        this.setIcon(icono);
        this.setBorder(null);
        this.setToolTipText(texto);
        setOpaque(true);
        setContentAreaFilled( false );
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        // Va a mostrar el botón solo en la última fila.
        // de otra forma muestra un espacio en blanco.
        return this;
    }
    
    

    
}
