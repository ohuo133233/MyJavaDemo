package com.example.chat.sdk;

import com.example.chat.base.Message;

public interface MessageCallBack {
    default void message(Message message) {
    }
}
