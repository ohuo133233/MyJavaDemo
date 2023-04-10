package com.example.chat;

import android.util.Log;

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
