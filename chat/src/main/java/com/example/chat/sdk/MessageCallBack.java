package com.example.chat.sdk;

import com.example.chat.base.chat.Message;

public interface MessageCallBack {
    default void message(Message message) {
    }
}
