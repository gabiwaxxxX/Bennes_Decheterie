const int pasVerticalMaximal = 10;
const int pasHorizontalMaximal = 10;

int matriceDeMesureStandard[pasVerticalMaximal][pasHorizontalMaximal];
int matriceDeMesureVide[pasVerticalMaximal][pasHorizontalMaximal];
int matriceDeMesurePlein[pasVerticalMaximal][pasHorizontalMaximal];
int matriceZoneDeMesure[pasVerticalMaximal][pasHorizontalMaximal];

double valeurRemplissageMinimal = 0.0;
double valeurRemplissageMaximal = 0.0;
double valeurMesure = 0.0;
int seuilDetectionBenne = 3;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  mesureRemplissageVide();
  mesureRemplissagePlein();
  calculZoneDeMesure();
  afficherMatriceVide();
  afficherMatricePlein();
  afficherZoneDeMesure();
}

void loop() {
  delay(2000);
  afficherMatriceVide();
  afficherMatricePlein();
  afficherZoneDeMesure();
  delay(20000);
}

void mesureRemplissageVide(){
  //goHome(); //bouger a la position initiale
  int variableTest = 10;
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      matriceDeMesureVide[positionH][positionV]= variableTest;//mesureLidar();
      //delay(10);
      //avancerUnPasHorizontal();
    }
    //avancerUnPasVertical(); 
  }
}

void mesureRemplissagePlein(){
  //goHome(); //bouger a la position initiale
  int variableTest = 8;
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      if((positionH>2) && (positionH<8) && (positionV>2) && (positionV<8)){variableTest = 15;}else{variableTest=8;}
      if(positionH==2){variableTest=7;}
      matriceDeMesurePlein[positionH][positionV]= variableTest;//mesureLidar();
      //delay(10);
      //avancerUnPasHorizontal();
    }
    //avancerUnPasVertical(); 
  }
}

void afficherMatriceVide(){
  Serial.print("\nmatriceDeMesureVide = \n");
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    Serial.print("    ");
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      Serial.print(matriceDeMesureVide[positionH][positionV]);
      Serial.print(" ");
    }
    Serial.print("\n");
  }
  Serial.print("\n\n");
}

void afficherMatricePlein(){
  Serial.print("\nmatriceDeMesurePlein = \n");
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    Serial.print("    ");
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      Serial.print(matriceDeMesurePlein[positionH][positionV]);
      Serial.print(" ");
    }
    Serial.print("\n");
  }
  Serial.print("\n\n");
}

void afficherZoneDeMesure(){
  Serial.print("\nmatriceZoneDeMesure = \n");
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    Serial.print("    ");
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      Serial.print(matriceZoneDeMesure[positionH][positionV]);
      Serial.print(" ");
    }
    Serial.print("\n");
  }
  Serial.print("\n\n");
}

void calculZoneDeMesure(){
  int difference = 0;
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      difference = abs(matriceDeMesurePlein[positionH][positionV]-matriceDeMesureVide[positionH][positionV]);
      if(difference<seuilDetectionBenne){
        difference = 0;
      }else{difference=1;}
      matriceZoneDeMesure[positionH][positionV]=difference;
    }
  }
  calculFinDeCalibrationMesure();
}

void calculFinDeCalibrationMesure(){
//valeurRemplissageMinimal
  int matriceCalculRemplissageMinimal[pasVerticalMaximal][pasHorizontalMaximal]; 
  int compteurDeValeursNonNullesMatriceMin = 0;
  int sommeDeValeursMatriceMin = 0;
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      matriceCalculRemplissageMinimal[positionH][positionV]= matriceDeMesureVide[positionH][positionV]*matriceZoneDeMesure[positionH][positionV];
      if(matriceCalculRemplissageMinimal[positionH][positionV]!=0){
        compteurDeValeursNonNullesMatriceMin+=1;
        sommeDeValeursMatriceMin+=matriceCalculRemplissageMinimal[positionH][positionV];
      }
    }
  }
  valeurRemplissageMinimal = sommeDeValeursMatriceMin/compteurDeValeursNonNullesMatriceMin;
  
//valeurRemplissageMaximal
  int matriceCalculRemplissageMaximal[pasVerticalMaximal][pasHorizontalMaximal]; 
    int compteurDeValeursNonNullesMatriceMax = 0;
    int sommeDeValeursMatriceMax = 0;
    for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
      for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
        matriceCalculRemplissageMaximal[positionH][positionV]= matriceDeMesurePlein[positionH][positionV]*matriceZoneDeMesure[positionH][positionV];
        if(matriceCalculRemplissageMaximal[positionH][positionV]!=0){
          compteurDeValeursNonNullesMatriceMax+=1;
          sommeDeValeursMatriceMax+=matriceCalculRemplissageMaximal[positionH][positionV];
        }
      }
    }
    valeurRemplissageMaximal = sommeDeValeursMatriceMax/compteurDeValeursNonNullesMatriceMax;
}

void mesureStandard(){
  //goHome(); //bouger a la position initiale
  int variableTest = 9;
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      matriceDeMesureStandard[positionH][positionV]= variableTest;//mesureLidar();
      //delay(10);
      //avancerUnPasHorizontal();
    }
    //avancerUnPasVertical(); 
  }
}

int mesureRemplissage(){
  int tauxDeRemplissage = -404;
  mesureStandard();
  int matriceCalculRemplissageStandard[pasVerticalMaximal][pasHorizontalMaximal]; 
  int compteurDeValeursNonNulles = 0;
  int sommeDeValeurs = 0;
  for(int positionV = 0; positionV<pasVerticalMaximal;positionV++){
    for(int positionH = 0; positionH<pasHorizontalMaximal;positionH++){
      matriceCalculRemplissageStandard[positionH][positionV]= matriceDeMesureStandard[positionH][positionV]*matriceZoneDeMesure[positionH][positionV];
      if(matriceCalculRemplissageStandard[positionH][positionV]!=0){
        compteurDeValeursNonNulles+=1;
        sommeDeValeurs+=matriceCalculRemplissageStandard[positionH][positionV];
      }
    }
  }
  valeurMesure = sommeDeValeurs/compteurDeValeursNonNulles;

  tauxDeRemplissage = (valeurMesure-valeurRemplissageMinimal)/(valeurRemplissageMaximal-valeurRemplissageMinimal)*100;  
  return tauxDeRemplissage;
}
