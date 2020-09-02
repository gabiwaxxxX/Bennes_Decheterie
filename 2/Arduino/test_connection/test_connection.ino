#include <string.h>

#include <WiFi.h>
#include <WiFiClient.h>

#include <WiFiServer.h>
#include <WiFiUdp.h>

#include <strings_en.h>
#include <WiFiManager.h>




const char* ssid = "ESP32AP";
const char* password = "esp32test";
const uint16_t port = 8090;
String MAC;
IPAddress IP;
int filling;
WiFiServer wifiServer(6666);
WiFiManager wm;


IPAddress serverAdress;



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

void setup() {
  Serial.begin(9600);
  Serial.begin(115200);
  delay(1000);
  Serial.println("\n");
  ConnectToWifi();
  getIpAdress();  
}



void loop() {

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
