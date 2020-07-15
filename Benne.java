

public class Benne  {
 
  private String nomBenne;
  private boolean Connexion;
  private String Remplissement;
  private static int nbreInstances = 0;   
   
  public Benne(){
    
    super();
   
  }
 
  public Benne(String pNom, String pNbre, boolean pCon)
  {
    //System.out.println("Création d'une benne avec des paramètres !");
    nomBenne = pNom;
    Connexion = pCon;
    Remplissement = pNbre;
    nbreInstances++;

  }  
    

  public String getNom()  {  
    return nomBenne;
  }

  public boolean getConnexion()
  {
    return Connexion;
  }


  public String getRemplissement()
  {
    return Remplissement;
  } 


  public void setNom(String pNom)
  {
    nomBenne = pNom;
  }


  public void setConnexion(boolean pCon)
  {
    Connexion = pCon;
  }


  public void setRemplissement(String nbre)
  {
    Remplissement = nbre;

  }  
    public static int getNombreInstances()
  {
    return nbreInstances;
  }  
 
 
}