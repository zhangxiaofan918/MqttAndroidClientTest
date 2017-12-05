package com.nick;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {

    public static void main(String[] args) {

        String topic = "MQTT";
        String message = "发布消息";
        String serverUrl = "tcp://iot.eclipse.org:1883";
        String clientId = "CLIENTID JavaSample";
        try {
            MqttClient mqttClient = new MqttClient(serverUrl, clientId, new MemoryPersistence());
            mqttClient.connect(getOptions());
            System.out.println("Mqtt Connected!");
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(2);
            System.out.println("Mqtt set qos" + mqttMessage.getQos());
            mqttClient.publish(topic, mqttMessage);
            System.out.println("Mqtt published!");
            mqttClient.disconnect();
            System.out.println("Mqtt disconnected!");
        } catch (MqttException me) {
            me.printStackTrace();
        }

    }

    private static MqttConnectOptions getOptions() {
        MqttConnectOptions connOpts = new MqttConnectOptions();  //mqtt连接选项
        connOpts.setCleanSession(true);  //客户端是否记忆状态
//        connOpts.setConnectionTimeout(80); //设置超时时间
//        connOpts.setKeepAliveInterval(200); //保活时间
//        connOpts.setUserName("nick");   //用户名
//        connOpts.setPassword("123123".toCharArray()); //密码
        System.out.println("MqttConnect Options: " + connOpts.toString());
        return connOpts;
    }
}
