package com.example.mqtt;


import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SampleCallback implements MqttCallback {
    private final String TAG = "SampleCallbacks";

    // 连接丢失
    public void connectionLost(Throwable cause) {

        Log.d(TAG, "connectionLost: " +cause.getMessage());
    }

    //  收到消息
    public void messageArrived(String topic, MqttMessage message) {
        Log.d(TAG, "messageArrived: " +"Received message: \n  topic：" + topic + "\n  Qos：" + message.getQos() + "\n  payload：" + new String(message.getPayload()));
    }

    // 消息传递成功
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.d(TAG, "deliveryComplete: ");
    }
}
