package com.company.kr.javatpc;

import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

public class MqttClass implements MqttCallback {

  private MqttClient client = null;
  private ReceiveEventListner listner = null;

  public MqttClass() {
    new Thread(task1).start();
  }

  Runnable task1 = new Runnable() {
    @Override
    public void run() {
      try {
        String clientId = UUID.randomUUID().toString();
        client = new MqttClient("tcp://172.30.1.29", clientId);

        MqttConnectOptions connopt = new MqttConnectOptions();
        connopt.setCleanSession(true);
        client.connect(connopt);
        client.setCallback(MqttClass.this);
        client.subscribe("dht11");

        new IoTFrame(MqttClass.this);
      } catch (MqttException e) {
        System.out.println("ERR0" + e.getStackTrace());
      }
    }
  };

  public void sendMessage(String payload) {
    MqttMessage message = new MqttMessage();
    message.setPayload(payload.getBytes());
    try {
      if(client.isConnected()) {
        client.publish("led", message);
      }
    } catch (MqttException e) {
      System.out.println("error1-" + e.getStackTrace());
    }
  }

  public void setMyEventListener(ReceiveEventListner listener) {
    this.listner = listener;
  }

  @Override
  public void connectionLost(Throwable agr0) {
    try {
      System.out.println("disconnect");
      client.close();
    } catch (MqttException e) {
      System.out.println("error" + e.getMessage());
    }
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken arg0) {

  }

  @Override
  public void messageArrived(String topic, MqttMessage msg) throws Exception {
    listner.recvMsg(topic, msg);
  }
}
