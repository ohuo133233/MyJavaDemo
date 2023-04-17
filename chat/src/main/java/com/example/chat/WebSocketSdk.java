package com.example.chat;

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


    public void connect(String address, String port) throws URISyntaxException {
        client = new WebSocketChatClient(new URI("ws://" + address + ":" + port + "/my-websocket-endpoint"));
        client.connect();
    }


    public void close() {
        client.close();
    }

    public void sendMessage(String string) {
        Message message = new Message();
        message.setName("默认");
        message.setSend_id("1");
        message.setRecipient_id("2");
        message.setMessage(string);

        Gson gson = new Gson();
        String json = gson.toJson(message, Message.class);
        client.send(json);
    }
}
