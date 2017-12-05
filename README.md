# MqttAndroidClientTest
基于MQTT协议测试的消息推送

[MQTT协议中文版](https://github.com/mcxiaoke/mqtt)

### 前言

简单来说，mqtt是一种用于消息推送的协议。相比较其他第三方消息推送，如环信，友盟，极光等等，mqtt可以自定义部署，减少了很多限制。以前也用过谷歌的推送服务，叫Goole Cloud Messaging，但是我大天朝局域网就别想了，如果没有vpn，那用用谷歌翻译就行了。

### 界面截图
<figure class="half">
    <a href="https://github.com/zhangxiaofan918/MqttAndroidClient/blob/master/Images/device-2017-12-05-165743.png"><img width="200" height="355" src="https://github.com/zhangxiaofan918/MqttAndroidClient/blob/master/Images/device-2017-12-05-165743.png"></a>
    <a href="https://github.com/zhangxiaofan918/MqttAndroidClient/blob/master/Images/device-2017-12-05-165823.png"><img width="200" height="355" src="https://github.com/zhangxiaofan918/MqttAndroidClient/blob/master/Images/device-2017-12-05-165823.png"></a>
     <a href="https://github.com/zhangxiaofan918/MqttAndroidClient/blob/master/Images/device-2017-12-05-165758.png"><img width="200" height="355" src="https://github.com/zhangxiaofan918/MqttAndroidClient/blob/master/Images/device-2017-12-05-165758.png"></a>
</figure>

### 环境
`服务器`：使用mqtt.jar[点击下载](https://repo.eclipse.org/content/repositories/paho/org/eclipse/paho/mqtt-client/0.4.0/)，集成相关代码后模拟发布消息到服务器

`客户端`：使用[paho.mqtt.android](https://github.com/eclipse/paho.mqtt.android)

### 服务器搭建
创建一个Java工程，可以用Eclipse或者IntelliJ IDEA,然后导入上面下载的jar包，就可以编译项目了。发布消息的代码如下：

```
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
```
```
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
```
运行结果

```
MqttConnect Options: 
============== Connection options ==============
CleanSession                :  true
SocketFactory               :  null
KeepAliveInterval           :  60
ConTimeout                  :  30
UserName                    :  null
SSLProperties               :  null
WillDestination             :  null
==========================================

Mqtt Connected!
Mqtt set qos2
Mqtt published!
Mqtt disconnected!
```

### 客户端使用
- 打开侧边栏选择Add Connection，paho demo已经配置了部分参数，不用修改
- 打开右上角开关，点击SUBSCRIBE，点击底部的NEW SUBSCRIPTION, Topic和QoS填写应该服务器端的信息一致
- 再切换到HISTORY，编译服务器端项目模拟发送消息，就可以看到刚才订阅的topic的消息了







