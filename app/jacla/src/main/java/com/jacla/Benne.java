package com.jacla;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Benne  {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)

  private  String nomBenne;
  private  boolean Connexion;
  private  String Remplissement;
  private  static int nbreInstances = 0;
  private Long id;
   
  protected Benne() {}

 
  public Benne(String pNom, String pNbre, boolean pCon)
  {
    //System.out.println("Création d'une benne avec des paramètres !");
    nomBenne = pNom;
    Remplissement = pNbre;
    Connexion = pCon;
    
    nbreInstances++;

  }  

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, nomBenne='%s', Remplissement='%s']",
        id, nomBenne, Remplissement);
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

  public Long getId() {
    return id;
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