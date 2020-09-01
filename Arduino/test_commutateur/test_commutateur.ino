//Initialisation des pin 
#define stepVerticalPin 6
#define dirVerticalPin 7
#define stepHorizontalPin 8
#define dirHorizontalPin 9
#define buttonVerticalPin 2
#define buttonHorizontalPin 3

int signalCapteurHorizontal = 0;
int signalCapteurVertical = 0;

void setup() {
  // Declare pins as output:
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(stepVerticalPin, OUTPUT);
  pinMode(dirVerticalPin, OUTPUT);
  pinMode(stepHorizontalPin, OUTPUT);
  pinMode(dirHorizontalPin, OUTPUT);
  pinMode(buttonVerticalPin, OUTPUT);
  pinMode(buttonHorizontalPin, OUTPUT);
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
