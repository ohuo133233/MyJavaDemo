package com.example.chat.base.chat;

public class MessageType {
    /**
     * 1下面都是系统定义类型，用户不应该主动发送该类型
     */
    // 上线消息
    public static final int ON_LINE = 1001;

    // 系统消息
    public static final int SYSTEM_MESSAGE = 1002;

    // 回复消息，接受到消息通知对面已收到
    public static final int REPLY_MESSAGE = 1003;


    /**
     * 2下面都是聊天类型
     */
    // 普通消息
    public static final int PRIVATE_CHAT = 2001;

    // 群消息
    public static final int GROUP_CHAT = 2002;

    // 聊天室
    public static final int CHAT_ROOM = 2003;


}
