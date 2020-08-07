import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

public class ConnectionCellRenderer extends DefaultTableCellRenderer {
    private Icon greenImage;
    private Icon redImage;
 
    public ConnectionCellRenderer() {
        super();
 
        greenImage = new ImageIcon(new ImageIcon("data/green.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
        redImage = new ImageIcon(new ImageIcon("data/rouge.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        Boolean connect = (Boolean)value;
 
        setText("");
 
        if(connect){
            setIcon(greenImage);
        } else {
            setIcon(redImage);
        }

         this.setHorizontalAlignment(SwingConstants.CENTER);
 
        return this;
    }
}