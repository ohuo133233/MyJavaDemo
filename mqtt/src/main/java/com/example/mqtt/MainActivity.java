package com.example.mqtt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private MqttClient mMqttClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        Button send_message = findViewById(R.id.send_message);
        Button reconnect = findViewById(R.id.reconnect);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        send_message.setOnClickListener(this);
        reconnect.setOnClickListener(this);

    }


    private void reconnect() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "run: 检查是否连接中：" + mMqttClient.isConnected());

                if (!mMqttClient.isConnected()) {
                    Log.d(TAG, "run: 已经断开连接，重新连接");
                    try {
                        mMqttClient.reconnect();
                    } catch (MqttException e) {
                        Log.e(TAG, "MqttException: " + e.toString());
                    }
                }
            }//每隔10秒使用handler发送一下消息,也就是每隔10秒执行一次,一直重复执行
        }, 1000, 10_000);
    }


    private void connectMqtt() throws MqttException {


        // MQTT 连接选项
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 设置认证信息
        connOpts.setUserName(Constant.USER);
        connOpts.setPassword(Constant.PASS_WORD.toCharArray());
        connOpts.setAutomaticReconnect(false);
        connOpts.setConnectionTimeout(60);
        connOpts.setKeepAliveInterval(60);
        connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);

        MemoryPersistence persistence = new MemoryPersistence();
        mMqttClient = new MqttClient(Constant.URL, "clientId", persistence);
        // 设置回调
        mMqttClient.setCallback(new MqttCallbackExtended() {

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.d(TAG, "connectComplete: reconnect: " + reconnect);
                Log.d(TAG, "connectComplete: serverURI: " + serverURI);
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.d(TAG, "connectionLost: MQTT连接断开" + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.d(TAG, "messageArrived: topic：" + topic + " ,message: " + message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                try {
                    Log.d(TAG, "deliveryComplete: " + token.getMessage());
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mMqttClient.connect(connOpts);

        boolean connected = mMqttClient.isConnected();
        Log.d(TAG, "connectMqtt: " + connected);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //连接mqtt
                        try {
                            connectMqtt();
                            subscribeAllTopics();
                        } catch (MqttException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }.start();

                break;
            case R.id.stop:
                try {
                    mMqttClient.disconnect();
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
                break;
            case R.id.send_message:
                MqttMessage mqttMessage = new MqttMessage();
                mqttMessage.setQos(1);
                mqttMessage.setRetained(false);
                mqttMessage.setPayload("hello".getBytes());

                try {
                    mMqttClient.publish("test", mqttMessage);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
                break;
            case R.id.reconnect:
                reconnect();
                break;
        }
    }


    /**
     * 订阅所有主题
     */
    private void subscribeAllTopics() {
        //订阅主消息主题和更新消息主题
        subscribeToTopic(Constant.SUB_TOPIC, 2);
    }

    /**
     * 订阅一个主主题
     *
     * @param subTopic 主题名称
     */
    private void subscribeToTopic(String subTopic, int qos) {
        try {
            mMqttClient.subscribe(subTopic, qos);
        } catch (MqttException ex) {
            Log.e(TAG, "subscribeToTopic: Exception whilst subscribing");
            ex.printStackTrace();
        }
    }
}