package com.example.mqtt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URI;
import java.net.URISyntaxException;

public class MqttService extends Service {
    private static final String TAG = MqttService.class.getSimpleName();

    public MqttService() {
        Log.d(TAG, "MqttService: ");


        String broker = "top://p7e59ee8.ala.cn-hangzhou.emqxsl.cn";
        URI uri;
        try {
            uri = new URI(broker);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String scheme = uri.getScheme();

        if (scheme == null || scheme.isEmpty()) {
            Log.d(TAG, "scheme==null ");
        }else {
            Log.d(TAG, "scheme： "+scheme);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                //连接mqtt
                try {
                    connectMqtt();
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void connectMqtt() throws MqttException {
        String broker = "tcp://47.110.155.71";

        // MQTT 连接选项
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 设置认证信息
        connOpts.setUserName("test");
        connOpts.setPassword("test".toCharArray());
        connOpts.setAutomaticReconnect(false);

        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = new MqttClient(broker, "clientId", persistence);
        // 设置回调
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.d(TAG, "connectionLost: ");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.d(TAG, "messageArrived: ");
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(TAG, "deliveryComplete: ");
            }
        });

        client.connect(connOpts);

        boolean connected = client.isConnected();
        Log.d(TAG, "connectMqtt: "+connected);
    }

}