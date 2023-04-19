package com.example.chat.sdk;

import android.util.Log;

import com.example.chat.base.chat.DeviceType;
import com.example.chat.base.chat.Message;
import com.example.chat.base.chat.MessageType;
import com.example.chat.base.manager.UserManager;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketSdk {

    private static WebSocketChatClient client;
    private volatile static WebSocketSdk sInstance;

    private MessageCallBack mMessageCallBack;

    private WebSocketSdk() {

    }

    public static WebSocketSdk getInstance() {
        if (sInstance == null) {
            synchronized (WebSocketSdk.class) {
                if (sInstance == null) {
                    sInstance = new WebSocketSdk();
                }
            }
        }
        return sInstance;
    }

    /**
     * 连接
     *
     * @param address IP地址
     * @param port    端口
     */
    public void connect(String address, String port, ConnectCallBack connectCallBack) {
        try {
            client = new WebSocketChatClient(new URI("ws://" + address + ":" + port + "/my-websocket-endpoint"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            connectCallBack.fail();
        }
        client.connect();
        connectCallBack.success();

    }

    /**
     * 登录消息，通知服务端上线
     */
    public void login() {
        sendMessage(MessageType.ON_LINE, "上线消息", "0");
    }

    /**
     * 关闭
     */
    public void close() {
        client.close();
    }

    /**
     * 发送消息
     *
     * @param string      消息内容
     * @param recipientId 接受人ID
     */
    public void sendMessage(String string, String recipientId) {
        Message message = new Message();
        message.setDeviceType(DeviceType.ANDROID);
        message.setName(UserManager.getInstance().getNAME());
        message.setSend_id(UserManager.getInstance().getID() + "");
        message.setRecipient_id(recipientId);
        message.setMessageType(MessageType.PRIVATE_CHAT);
        message.setMessage(string);
        message.setTime(System.currentTimeMillis());

        sendMessage(message);
    }

    public void sendMessage(int messageType, String string, String recipientId) {
        Message message = new Message();
        message.setDeviceType(DeviceType.ANDROID);
        message.setName(UserManager.getInstance().getNAME());
        message.setSend_id(UserManager.getInstance().getID() + "");
        message.setRecipient_id(recipientId);
        message.setMessageType(messageType);
        message.setMessage(string);
        message.setTime(System.currentTimeMillis());

        sendMessage(message);
    }

    public void sendMessage(Message message) {
        Gson gson = new Gson();
        String json = gson.toJson(message, Message.class);
        Log.d("TAG", "sendMessage: " + json);
        client.send(json);
    }


    /**
     * 自动回复消息，当接受到消息，自动回复，告诉对方已经收到
     *
     * @param recipientId 接受人ID
     */
    public void replyToMessage(String recipientId) {
        Message message = new Message();
        message.setMessageType(MessageType.REPLY_MESSAGE);
        message.setDeviceType(DeviceType.ANDROID);
        message.setName(UserManager.getInstance().getNAME());
        message.setSend_id(UserManager.getInstance().getID() + "");
        message.setRecipient_id(recipientId);
        message.setMessage("已经接受到消息");
    }


    public void receiveMessage(MessageCallBack messageCallBack) {
        mMessageCallBack = messageCallBack;
    }


    public void decodeMessage(Message message) {
        switch (message.getMessageType()) {
            case MessageType.PRIVATE_CHAT:
                mMessageCallBack.message(message);
                break;
        }
    }
}
