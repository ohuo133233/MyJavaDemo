package com.wang.im;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMValueCallback;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private final String TAG = ChatActivity.class.getSimpleName();
    private ChatMessageAdapter mChatMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        EditText send_text_edit_text = findViewById(R.id.send_text_edit_text);
        RecyclerView chat_message = findViewById(R.id.chat_message);
        Button send_message = findViewById(R.id.send_message);

        mChatMessageAdapter = new ChatMessageAdapter(this);
        chat_message.setAdapter(mChatMessageAdapter);
        chat_message.setLayoutManager(new LinearLayoutManager(this));
        IMManger.getInstance().getHistoryMessageList(DebugConfig.USER, new V2TIMValueCallback<List<V2TIMMessage>>() {
            @Override
            public void onSuccess(List<V2TIMMessage> v2TIMMessages) {
                Log.d(TAG, "onSuccess: v2TIMMessages.size" + v2TIMMessages.size());
                mChatMessageAdapter.setItemList(v2TIMMessages);
                mChatMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int code, String desc) {
                Log.d(TAG, "onError  code: " + code + " ,desc: " + desc);
            }
        });
        IMManger.getInstance().addMsgListener(new IMManger.MessageCallBack() {
            @Override
            public void text(String text) {
                mChatMessageAdapter.addMessage(text);
                mChatMessageAdapter.notifyDataSetChanged();

            }

            @Override
            public void sound() {

            }
        });


        send_message.setOnClickListener(view -> {
            String message = send_text_edit_text.getText().toString().trim();
            Log.d(TAG, "发送的消息： " + message);
            IMManger.getInstance().sendMessage(message, DebugConfig.SEND_USER);
        });
    }
}