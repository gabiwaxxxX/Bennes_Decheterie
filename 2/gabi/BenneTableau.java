import javax.swing.table.*;
import java.util.*;

public class BenneTableau extends AbstractTableModel {
    private final List<Benne> bennes = new ArrayList<Benne>();
    
    private final String  title[] = {"Bennes", "% Remplissage", "Connexion"};

 
    public BenneTableau() {
        super();


        bennes.add(new Benne("Benne \u2116 1", "58%",true));
        bennes.add(new Benne("Benne \u2116 2", "32%",true));
        bennes.add(new Benne("Benne \u2116 3", "41%",false));
        bennes.add(new Benne("Benne \u2116 4", "82%",true));
    
        }
    
 
    public int getRowCount() {
        return bennes.size();
    }
 
    public int getColumnCount() {
        return title.length;
    }
 
    public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: 
                return bennes.get(rowIndex).getNom();
            case 1: 
                return bennes.get(rowIndex).getRemplissement();
            case 2: 
                return bennes.get(rowIndex).getConnexion();

            default:
                    return null;
        }
    }

    public Class getColumnClass(int columnIndex){
	switch(columnIndex){
        case 0:
            return String.class;
        case 2:
			return Boolean.class;
		default:
			return Object.class;
	}
    }

    public void addBenne(Benne benne) {
        bennes.add(benne);
 
        fireTableRowsInserted(bennes.size() -1, bennes.size() -1);
        
    }

    public void removeBenne(int rowIndex) {
        bennes.remove( rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}