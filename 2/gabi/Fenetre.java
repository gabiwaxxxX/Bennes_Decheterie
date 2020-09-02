import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.io.*;
import javax.swing.table.TableColumn;
import java.util.Random;


public class Fenetre extends JFrame {

    private JButton bouton1 = new JButton("Open test.html (settings)");
    private JButton bouton2 = new JButton("Apparaillage");
    
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Remplissage des bennes");
    private JProgressBar bar  = new JProgressBar();
    private BenneTableau Bennes = new BenneTableau();
    private JScrollPane tab;

    private JTable tableau;


    
    public Fenetre(){  
        this.setTitle("Gestionnaire de bene");

        Font font = new Font("TimesRoman ", Font.BOLD, 20);

        //get your screen size
        int y1 = Toolkit.getDefaultToolkit().getScreenSize().height;  
        int x1 = Toolkit.getDefaultToolkit().getScreenSize().width;

        //Creat the window
        this.setSize(x1,y1);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        
        bar.setMaximum(700);
        bar.setMinimum(0);
        bar.setStringPainted(true);

        tableau = new JTable(Bennes); 

        
        //this.tableau.setGridColor(Color.white);
        this.tableau.setFont(font);
        this.tableau.setRowHeight(50);
        this.tableau.setDefaultRenderer(Boolean.class, new ConnectionCellRenderer());
        this.tableau.setDefaultRenderer(String.class, new NameCellRenderer());
        
        TableColumn myCol1 = this.tableau.getColumnModel().getColumn(1);
        
        myCol1.setCellRenderer(new ProgressCellRenderer());
        Font bigFont = new Font("TimesRoman", Font.PLAIN, 28); 
        this.tableau.getTableHeader().setPreferredSize(new Dimension(100, 75));
        this.tableau.getTableHeader().setFont(bigFont);


        


        tab = new JScrollPane(tableau);
        this.tab.setBounds(x1/4, y1/4,750,450);


        
       
        
        
        
        
        


        
        container.setLayout(null);
        container.setBackground(Color.decode("#D8D8D8"));
        JButton bouton3 = new JButton(new AddAction());

        bouton1.setBounds(x1*2/6-(x1/14),y1*5/7,150,60);  
        bouton2.setBounds(x1*3/6-(x1/14),y1*5/7,150,60);
        bouton3.setBounds(x1*4/6-(x1/14),y1*5/7,150,60);
        


        
        bouton1.addActionListener(new Bouton1Listener());
        bouton2.addActionListener(new Bouton2Listener());
        //bouton3.addActionListener(new Bouton3Listener());

        
        label.setBounds((x1/2-(x1/11)), y1/8,500,50);
        
        label.setFont(font);
        label.setForeground(Color.black);
        label.setHorizontalAlignment(SwingConstants.LEADING);
        
        container.add(bouton1);
        container.add(bouton2);
        container.add(bouton3);
        container.add(label);
        container.add(tab);
        
      



        this.setContentPane(container);
        this.setVisible(true);
        
        
    }
      
    class Bouton1Listener implements ActionListener{

      
      
      public void actionPerformed(ActionEvent arg0) {
        System.out.println("Prochainement sera pour l'apparaillage");   
        File htmlFile = new File("test.html");
        try {
        Desktop.getDesktop().browse(htmlFile.toURI());
          } 
        catch ( Exception e1) {e1.printStackTrace();};   
        
      }
    }
        
    
    class Bouton2Listener implements ActionListener{
      
      public void actionPerformed(ActionEvent arg0) {
         System.out.println("Prochainement sera pour l'arret");
      }
    }    

    

     private class AddAction extends AbstractAction {
        private AddAction() {
            super("Ajouter");
        }

      public void actionPerformed(ActionEvent arg0) {

        String name,nbr;
        boolean co;
        int nbre = Benne.getNombreInstances()+1;
        Random r = new Random();
        int n = r.nextInt(101);
        boolean[] con={true,false};
        int n2 = r.nextInt(2);

        name = "Benne \u2116 "+nbre;
        nbr = n+"%";
        co=con[n2];


        Bennes.addBenne(new Benne(name,nbr,co));


      }
    } 

    private static Object[][] Push(Object[][] array, Object[] Push) {

    Object[][] longer = new Object[array.length + 1][];
    for (int i = 0; i < array.length; i++)
        longer[i] = array[i];
    longer[array.length] = Push;
    return longer;
}

  
}
