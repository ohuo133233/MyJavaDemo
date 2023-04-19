package com.example.chat.sdk;

import android.util.Log;

import com.example.chat.base.chat.Message;
import com.example.chat.base.chat.MessageType;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketChatClient extends WebSocketClient {

    private String TAG = "WebSocketChatClient";

    public WebSocketChatClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Log.e(TAG, "Connected to server: " + getURI());
    }

    @Override
    public void onMessage(String onMessage) {
        Log.e(TAG, "onMessage: " + onMessage);
        // 解析成JSON
        Gson gson = new Gson();
        Message message = gson.fromJson(onMessage, Message.class);


        switch (message.getMessageType()) {
            case MessageType.ON_LINE:
                break;
            case MessageType.PRIVATE_CHAT:
                // 收到私聊消息，首先回复对面已收到
                //   WebSocketSdk.getInstance().replyToMessage(message.getRecipient_id());
                // 解析消息类型
                WebSocketSdk.getInstance().decodeMessage(message);
                break;
            case MessageType.REPLY_MESSAGE:

                break;
            default:
                break;
        }

        Log.e(TAG, "接收到的JSON: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e(TAG, "Disconnected from server: " + code + " " + reason);
    }

    @Override
    public void onError(Exception ex) {
        Log.e(TAG, "Error: " + ex.getMessage());
    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketChatClient client = new WebSocketChatClient(new URI("ws://example.com/chat"));
        client.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            client.send(input);
        }
        client.close();
    }
}
