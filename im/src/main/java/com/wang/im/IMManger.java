package com.wang.im;

import static com.tencent.imsdk.v2.V2TIMMessage.V2TIM_ELEM_TYPE_SOUND;
import static com.tencent.imsdk.v2.V2TIMMessage.V2TIM_ELEM_TYPE_TEXT;

import android.content.Context;
import android.util.Log;

import com.tencent.imsdk.v2.V2TIMAdvancedMsgListener;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMGroupMemberInfo;
import com.tencent.imsdk.v2.V2TIMLogListener;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMMessageExtension;
import com.tencent.imsdk.v2.V2TIMMessageListGetOption;
import com.tencent.imsdk.v2.V2TIMMessageReactionChangeInfo;
import com.tencent.imsdk.v2.V2TIMMessageReceipt;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMTextElem;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.imsdk.v2.V2TIMVideoElem;

import java.util.List;

public class IMManger {
    private final String TAG = IMManger.class.getSimpleName();
    private static IMManger instance;
    private int appId = DebugConfig.APP_ID;

    private IMManger() {
    }

    public static IMManger getInstance() {
        if (instance == null) {
            instance = new IMManger();
        }
        return instance;
    }

    public boolean init(Context context) {
        // 初始化 config 对象
        V2TIMSDKConfig config = new V2TIMSDKConfig();
        // 指定 log 输出级别
        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_INFO);
        // 指定 log 监听器
        config.setLogListener(new V2TIMLogListener() {
            @Override
            public void onLog(int logLevel, String logContent) {
                // logContent 为 SDK 日志内容
                Log.d(logLevel + "", logContent);
            }
        });

        return V2TIMManager.getInstance().initSDK(context, appId, config);


    }


    public void login(String userId, String userSig, V2TIMCallback v2TIMCallback) {
        V2TIMManager.getInstance().login(userId, userSig, v2TIMCallback);
    }

    public String getLoginUser() {
        return V2TIMManager.getInstance().getLoginUser();
    }

    public void sendMessage(String message, String receiver_user_id) {
        // API 返回 msgID，按需使用
        String msgID = V2TIMManager.getInstance().sendC2CTextMessage(message, receiver_user_id, new V2TIMValueCallback<V2TIMMessage>() {
            @Override
            public void onSuccess(V2TIMMessage message) {
                // 发送单聊文本消息成功
                Log.d(TAG, "发送单聊文本消息成功" + message);
            }


            @Override
            public void onError(int code, String desc) {
                // 发送单聊文本消息失败
                Log.d(TAG, "发送单聊文本消息失败 code：" + code + " desc: " + desc);
            }
        });

    }


    public void addMsgListener(MessageCallBack messageCallBack) {
        V2TIMManager.getMessageManager().addAdvancedMsgListener(new V2TIMAdvancedMsgListener() {
            @Override
            public void onRecvNewMessage(V2TIMMessage msg) {
                super.onRecvNewMessage(msg);
                Log.d(TAG, "onRecvNewMessage: " + msg.getMsgID());
                Log.d(TAG, "onRecvNewMessage: " + msg.getElemType());

                switch (msg.getElemType()) {
                    case V2TIM_ELEM_TYPE_TEXT:
                        Log.d(TAG, "文本消息");
                        V2TIMTextElem textElem = msg.getTextElem();
                        String text = textElem.getText();
                        Log.d(TAG, "text: " + text);
                        messageCallBack.text(text);
                        break;
                    case V2TIM_ELEM_TYPE_SOUND:
                        V2TIMVideoElem videoElem = msg.getVideoElem();

                        messageCallBack.sound();
                        Log.d(TAG, "语音消息");
                        break;
                }
            }

            @Override
            public void onRecvMessageReadReceipts(List<V2TIMMessageReceipt> receiptList) {
                super.onRecvMessageReadReceipts(receiptList);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvC2CReadReceipt(List<V2TIMMessageReceipt> receiptList) {
                super.onRecvC2CReadReceipt(receiptList);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvMessageRevoked(String msgID, V2TIMUserFullInfo operateUser, String reason) {
                super.onRecvMessageRevoked(msgID, operateUser, reason);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvMessageModified(V2TIMMessage msg) {
                super.onRecvMessageModified(msg);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvMessageExtensionsChanged(String msgID, List<V2TIMMessageExtension> extensions) {
                super.onRecvMessageExtensionsChanged(msgID, extensions);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvMessageExtensionsDeleted(String msgID, List<String> extensionKeys) {
                super.onRecvMessageExtensionsDeleted(msgID, extensionKeys);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvMessageReactionsChanged(List<V2TIMMessageReactionChangeInfo> changeInfos) {
                super.onRecvMessageReactionsChanged(changeInfos);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onRecvMessageRevoked(String msgID) {
                super.onRecvMessageRevoked(msgID);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }

            @Override
            public void onGroupMessagePinned(String groupID, V2TIMMessage message, boolean isPinned, V2TIMGroupMemberInfo opUser) {
                super.onGroupMessagePinned(groupID, message, isPinned, opUser);
                Log.d(TAG, "onRecvMessageReadReceipts");
            }
        });
    }


    public void getHistoryMessageList(String userID,V2TIMValueCallback<List<V2TIMMessage>> v2TIMValueCallback) {
        V2TIMMessageListGetOption option = new V2TIMMessageListGetOption();
        option.setGetType(V2TIMMessageListGetOption.V2TIM_GET_CLOUD_OLDER_MSG); // 拉取云端的更老的消息
        option.setGetTimeBegin(1640966400);         // 从 2022-01-01 00:00:00 开始
        option.setGetTimePeriod(7 * 24 * 60 * 60);  // 拉取7天的消息
        option.setCount(Integer.MAX_VALUE);         // 返回时间范围内所有的消息
        option.setUserID(userID);                    // 拉取单聊消息
        V2TIMManager.getMessageManager().getHistoryMessageList(option, new V2TIMValueCallback<List<V2TIMMessage>>() {
            @Override
            public void onSuccess(List<V2TIMMessage> v2TIMMessages) {
                Log.i("imsdk", "success"+v2TIMMessages.size());
            }


            @Override
            public void onError(int code, String desc) {
                Log.i("imsdk", "failure, code:" + code + ", desc:" + desc);
            }
        });

    }


    interface MessageCallBack {
        void text(String text);

        void sound();
    }

}