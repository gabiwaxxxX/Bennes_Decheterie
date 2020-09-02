#include "Arduino.h"
//Initialisation des pin 
 #define LED_BUILTIN
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

void setup() {
  // Declare pins as output:
  //pinMode(LED_BUILTIN, OUTPUT);
  pinMode(stepVerticalPin, OUTPUT);
  pinMode(dirVerticalPin, OUTPUT);
  pinMode(stepHorizontalPin, OUTPUT);
  pinMode(dirHorizontalPin, OUTPUT);
  pinMode(buttonVerticalPin, OUTPUT);
  pinMode(buttonHorizontalPin, OUTPUT);

  digitalWrite(dirVerticalPin,HIGH);
  digitalWrite(dirHorizontalPin,HIGH);

  calibrationPosition();
}
void loop(){
  signalCapteurHorizontal = digitalRead(buttonHorizontalPin); 
  signalCapteurVertical = digitalRead(buttonVerticalPin); 
  if((signalCapteurHorizontal==LOW)||(signalCapteurVertical==LOW)){
    digitalWrite(LED_BUILTIN, HIGH);
  }else{
    digitalWrite(LED_BUILTIN, LOW);
  }
}
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
