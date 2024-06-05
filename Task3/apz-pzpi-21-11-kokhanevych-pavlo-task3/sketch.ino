#include <WiFi.h>
#include <HTTPClient.h>
#include <DHTesp.h>
#include <ArduinoJson.h>

const char* SSID = "Wokwi-GUEST";
const char* PASSWORD = "";

const char* SERVER_URL = "http://host.wokwi.internal:8080/device/3/updateState";
const char* API_KEY = "$2a$10$Upagga3BReZ1B9JgRixh7eiMAAgJRfPLJ4.qy0VnEaLL6Jyz.2g7K";

const char* HEADER_CONTENT_TYPE = "Content-Type";
const char* HEADER_CONTENT_TYPE_VALUE = "application/json";
const char* HEADER_API_KEY = "X-API-Key";

const int DHT_PIN = 15; 
const int LIGHT_SENSOR_PIN = 34; 


const char* MSG_CONNECTING_WIFI = "Connecting to WiFi...";
const char* MSG_CONNECTED_WIFI = "Connected to WiFi";
const char* MSG_FAILED_DHT = "Failed to read from DHT sensor!";
const char* MSG_HTTP_RESPONSE_CODE = "HTTP Response code: ";
const char* MSG_ERROR_CODE = "Error code: ";

DHTesp dht;

void setup() {
  Serial.begin(115200);
  dht.setup(DHT_PIN, DHTesp::DHT22);
  WiFi.begin(SSID, PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println(MSG_CONNECTING_WIFI);
  }
  Serial.println(MSG_CONNECTED_WIFI);
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    float temperature = dht.getTemperature();
    float humidity = dht.getHumidity();
    int lightLevel = analogRead(LIGHT_SENSOR_PIN);

    if (isnan(temperature) || isnan(humidity)) {
      Serial.println(MSG_FAILED_DHT);
      delay(2000);
      return;
    }

    StaticJsonDocument<200> doc;
    doc["temperature"] = temperature;
    doc["humidity"] = humidity;
    doc["brightness"] = lightLevel;

    HTTPClient http;
    http.begin(SERVER_URL);
    http.addHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE);
    http.addHeader(HEADER_API_KEY, API_KEY);
    String jsonString;
    serializeJson(doc, jsonString);
    int httpResponseCode = http.POST(jsonString);

    if (httpResponseCode > 0) {
      Serial.print(MSG_HTTP_RESPONSE_CODE);
      Serial.println(httpResponseCode);
    } else {
      Serial.print(MSG_ERROR_CODE);
      Serial.println(httpResponseCode);
    }

    http.end();
  }

  delay(60000);
}
