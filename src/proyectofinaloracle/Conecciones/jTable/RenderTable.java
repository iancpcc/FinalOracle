/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones.jTable;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author chris
 */
public class RenderTable extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if(value instanceof JCheckBox ){
                JCheckBox chkbx =  (JCheckBox)value;
                return chkbx;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
    }
      
    
    
}
