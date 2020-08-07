import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

public class NameCellRenderer extends DefaultTableCellRenderer {
    private Icon greenImage;
    private Icon redImage;
 
    public NameCellRenderer() {
        super();
 
    
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

         this.setHorizontalAlignment(SwingConstants.CENTER);
 
        return this;
    }
}