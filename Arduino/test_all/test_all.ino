#include <string.h>
#include <WiFi.h>
#include <WiFiClient.h>
#include <WiFiServer.h>
#include <WiFiUdp.h>
#include <strings_en.h>
#include <WiFiManager.h>


//Init connection part
const char* ssid = "ESP32AP";
const char* password = "esp32test";
const uint16_t port = 8090;
String MAC;
IPAddress IP;
int filling;
WiFiServer wifiServer(6666);
WiFiManager wm;
IPAddress serverAdress;


//Init moteur part 
//Initialisation des pin 
#define stepVerticalPin 6
#define dirVerticalPin 7
#define stepHorizontalPin 8
#define dirHorizontalPin 9
#define buttonVerticalPin 2
#define buttonHorizontalPin 3

int signalCapteurHorizontal = 0;
int signalCapteurVertical = 0;
int positionMotHorizontal = 0;
int positionMotVertical = 0;
double angleDePas = 1.8;
//angleHorizontalMaximal =;
//angleVerticallMaximal = ;


/*//Init Commutateur part
//Initialisation des pin 
#define stepVerticalPin 6
#define dirVerticalPin 7
#define stepHorizontalPin 8
#define dirHorizontalPin 9
#define buttonVerticalPin 2
#define buttonHorizontalPin 3

//int signalCapteurHorizontal = 0;
//int signalCapteurVertical = 0;*/

//Init Matrice part
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


//Matrice function
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



//MoteurFonction
void calibrationPosition(){// initialiser la position des moteurs - tourner jusqu’au commutateur
  //calibrer le moteur horizontal
    digitalWrite(dirHorizontalPin,LOW); //sens antihoraire
    while(digitalRead(buttonHorizontalPin)==HIGH){
      digitalWrite(stepHorizontalPin, HIGH);
      delayMicroseconds(3000);
      digitalWrite(stepHorizontalPin, LOW);
      delayMicroseconds(3000);
    }
    positionMotHorizontal = 0; //remise a zero de ces 2 variables pour calibrer la position des moteurs 
    digitalWrite(dirHorizontalPin,HIGH); //sens horaire
  //idem pour calibrer le moteur vertical
      ///....
}
void tournerMoteurHorizontal(int nbDePas){
  digitalWrite(dirHorizontalPin,HIGH);
  for(int i=0; i<nbDePas;i++){
      digitalWrite(stepHorizontalPin, HIGH);
      delayMicroseconds(3000);
      digitalWrite(stepHorizontalPin, LOW);
      delayMicroseconds(3000); 
  } 
}

//Connection Function

void ConnectToWifi(){
  WiFi.mode(WIFI_STA);
  if (!wm.autoConnect(ssid, password))
    Serial.println("Erreur de connexion.");
  else
    Serial.println("Connexion etablie!");
   MAC= WiFi.macAddress();
   IP= WiFi.localIP();
}

void getIpAdress(){
  wifiServer.begin();
  WiFiClient client = wifiServer.available();

  if (client) {

    Serial.print("Client connected with IP:");
    Serial.println(client.remoteIP());
    serverAdress = client.remoteIP();

    client.stop();
    wifiServer.close();
  }
}

void messageHandler(char in){
  
  if (in == '1'){
    mesureRemplissageVide(); //to connect with the other folder
  }
  if (in == '2'){
    mesureRemplissagePlein(); //to connect with the other folder
  }
  else{
    Serial.print("not handeled message");
  }
  }

void setup(){

  //Setup Moteur/Commutateur
  // Declare pins as output:
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(stepVerticalPin, OUTPUT);
  pinMode(dirVerticalPin, OUTPUT);
  pinMode(stepHorizontalPin, OUTPUT);
  pinMode(dirHorizontalPin, OUTPUT);
  pinMode(buttonVerticalPin, OUTPUT);
  pinMode(buttonHorizontalPin, OUTPUT);

  digitalWrite(dirVerticalPin,HIGH);
  digitalWrite(dirHorizontalPin,HIGH);

  calibrationPosition();

  //Setup Matrice
  // put your setup code here, to run once:
  Serial.begin(9600);
  mesureRemplissageVide();
  mesureRemplissagePlein();
  calculZoneDeMesure();
  afficherMatriceVide();
  afficherMatricePlein();
  afficherZoneDeMesure();

  //Setup Connection
  Serial.begin(115200);
  delay(1000);
  Serial.println("\n");
  ConnectToWifi();
  getIpAdress();  

  
  
}

void loop(){

  //loop moteur
  signalCapteurHorizontal = digitalRead(buttonHorizontalPin); 
  signalCapteurVertical = digitalRead(buttonVerticalPin); 
  if((signalCapteurHorizontal==LOW)||(signalCapteurVertical==LOW)){
    digitalWrite(LED_BUILTIN, HIGH);
  }else{
    digitalWrite(LED_BUILTIN, LOW);
  }

  //loop matrice 
  afficherMatriceVide();
  afficherMatricePlein();
  afficherZoneDeMesure();

  //loop connection
  WiFiClient client;

  if (!client.connect(serverAdress, port)) {

    Serial.println("Connection to host failed");

    delay(1000);
    return;
  }

  Serial.println("Connected to server successful!");

  client.print("Hello from ESP32!");
  Serial.println("receiving from remote server");
  while (client.available()) {
    char ch = static_cast<char>(client.read()); //transform ch to command with the different function;
    while(!ch){
      String sendingData = MAC+"-"+mesureRemplissage();
    client.print(sendingData);
    delay(10000);
    };
    Serial.print(ch);
    messageHandler(ch);

    delay(1000);
    
    
  };
  

  
}
