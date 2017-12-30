char command;
String string="";

void setup(){
    Serial.begin(9600);
}

void loop(){



while(Serial.available() > 0){
      command = ((byte)Serial.read());
      if(command == ':'){break;}
      else{string += command;}
      delay(10);
}

 if (string.length() >0) {
    Serial.println(string); 
    string="";
  }
delay(1000);
}

