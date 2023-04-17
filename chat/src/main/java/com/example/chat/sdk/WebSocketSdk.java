package com.example.chat.sdk;

import com.example.chat.base.User;
import com.example.chat.base.DeviceType;
import com.example.chat.base.Message;
import com.example.chat.base.MessageType;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketSdk {

    private static WebSocketChatClient client;
    private volatile static WebSocketSdk sInstance;

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
    // TODO 连接成功和失败的回调
    public void connect(String address, String port) {
        try {
            client = new WebSocketChatClient(new URI("ws://" + address + ":" + port + "/my-websocket-endpoint"));
        } catch (URISyntaxException e) {
            // TODO 连接失败的一种
            e.printStackTrace();
        }
        client.connect();


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
        message.setName(User.getInstance().getNAME());
        message.setSend_id(User.getInstance().getID() + "");
        message.setRecipient_id(recipientId);
        message.setMessage(string);

        Gson gson = new Gson();
        String json = gson.toJson(message, Message.class);
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
        message.setName(User.getInstance().getNAME());
        message.setSend_id(User.getInstance().getID() + "");
        message.setRecipient_id(recipientId);
        message.setMessage("已经接受到消息");
    }


}
