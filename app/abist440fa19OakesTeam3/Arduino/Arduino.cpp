//
// Created by Prime on 10/30/2019.
//



/*
 * Project raptor
 * Shakhawat Hossain
 */

#include <ArduinoBLE.h>
#include <Servo.h>

//servo
Servo myServo;  // create servo object to control a servo
int pos = 0;    // variable to store the servo position

//Bluetooth
BLEService ledService("19B10000-E8F2-537E-4F6C-D104768A1214"); // BLE LED Service

// BLE LED Switch Characteristic - custom 128-bit UUID, read and writable by central
BLEByteCharacteristic switchCharacteristic("19B10001-E8F2-537E-4F6C-D104768A1214", BLERead | BLEWrite);

const int ledPin = LED_BUILTIN; // pin to use for the LED



void setup() {
  // put your setup code here, to run once:
  //Servo
  myservo.attach(3); //servo attached to pin 9

  // Bluetooth
  Serial.begin(9600);
  while (!Serial);

  // set LED pin to output mode
  pinMode(ledPin, OUTPUT);

  // begin initialization
  if (!BLE.begin()) {
    Serial.println("starting BLE failed!");

    while (1);
  }

  // set advertised local name and service UUID:
  BLE.setLocalName("Team_3");
  BLE.setDeviceName("Team_3");
  BLE.setAdvertisedService(ledService);

  // add the characteristic to the service
  ledService.addCharacteristic(switchCharacteristic);

  // add service
  BLE.addService(ledService);

  // set the initial value for the characeristic:
  switchCharacteristic.writeValue(0);

  // start advertising
  BLE.advertise();

  Serial.println("BLE LED Peripheral");




}

void loop() {
  // put your main code here, to run repeatedly:
  BLEDevice central = BLE.central();

  // if a central is connected to peripheral:
  if (central) {
    Serial.print("Connected to central: ");
    // print the central's MAC address:
    Serial.println(central.address());

    // while the central is still connected to peripheral:
    while (central.connected()) {
      // if the remote device wrote to the characteristic,
      // use the value to control the LED:
      if (switchCharacteristic.written()) {

        // servo turns right from innitial position
        if (switchCharacteristic.value()) {   // any value other than 0
          Serial.println("LED on");
          digitalWrite(ledPin, HIGH);         // will turn the LED on
          for (pos = 0; pos <= 180; pos += 1) { // goes from 0 degrees to 180 degrees
            // in steps of 1 degree
            myServo.write(pos);              // tell servo to go to position in variable 'pos'
            delay(15);                       // waits 15ms for the servo to reach the position
        } else {                              // a 0 value
          Serial.println(F("LED off"));
          digitalWrite(ledPin, LOW);          // will turn the LED off
          for (pos = 180; pos >= 0; pos -= 1) { // goes from 180 degrees to 0 degrees
            myServo.write(pos);              // tell servo to go to position in variable 'pos'
            delay(15);                       // waits 15ms for the servo to reach the position
          }
        }
      }
    }

    // when the central disconnects, print it out:
    Serial.print(F("Disconnected from central: "));
    Serial.println(central.address());
  }
}
