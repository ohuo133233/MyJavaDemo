package com.example.chat.chat;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;
import com.example.chat.base.common.CommonRecyclerViewAdapter;
import com.example.chat.base.common.IMutableItemView;
import com.example.chat.base.chat.Message;
import com.example.chat.base.manager.UserManager;
import com.example.chat.sdk.MessageCallBack;
import com.example.chat.sdk.WebSocketSdk;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ArrayList<Message> mMessageList = new ArrayList<>();
    private CommonRecyclerViewAdapter<Message> mMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();
        initMessageData();
    }

    private void initMessageData() {
        WebSocketSdk.getInstance().receiveMessage(new MessageCallBack() {
            @Override
            public void message(Message message) {
                mMessageList.add(message);
                mMessageAdapter.notifyDataSetChanged();
            }
        });

    }

    private final int one = 1;
    private final int two = 2;

    private void initView() {

        Button back = findViewById(R.id.back);
        TextView you_name = findViewById(R.id.you_name);
        EditText edit_text = findViewById(R.id.edit_text);
        Button send_message = findViewById(R.id.send_message);
        RecyclerView message_list = findViewById(R.id.message_list);

        // TODO 设置对方姓名
//        you_name.setText();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send_message.setOnClickListener(v -> {
            String messageString = edit_text.getText().toString().trim();
            int id = UserManager.getInstance().getID();
            if (id == 1) {
                WebSocketSdk.getInstance().sendMessage(messageString, "2");
            } else {
                WebSocketSdk.getInstance().sendMessage(messageString, "1");
            }
            // 添加到本地消息
            addMessage(messageString);
            edit_text.setText("");
            mMessageAdapter.notifyDataSetChanged();
        });


        IMutableItemView iMutableItemView = new IMutableItemView() {
            @Override
            public int getItemViewType(int position) {
                switch (position) {
                    case 1:
                        return one;
                    case 2:
                        return two;
                    default:
                        return one;
                }
            }

            @Override
            public int onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case one:
                        return R.layout.my_message_item;
                    case two:
                        return R.layout.you_message_item;
                    default:
                        return R.layout.my_message_item;
                }
            }
        };

        mMessageAdapter = new CommonRecyclerViewAdapter.Build<Message>()
                .setContext(this)
                .setLayoutId(R.layout.my_message_item)
                .setDataList(mMessageList)
                .setMutableItemView(iMutableItemView)
                .build();

        message_list.setAdapter(mMessageAdapter);
        message_list.setLayoutManager(new LinearLayoutManager(this));

        mMessageAdapter.setCommonRecyclerViewAdapterBackCall((holder, position) -> {
            TextView message = holder.getView(R.id.message);
            message.setText(mMessageList.get(position).getMessage());
        });

    }

    private void addMessage(String messageString) {
        Message message = new Message();
        message.setMessage(messageString);
        message.setName(UserManager.getInstance().getNAME());
        message.setTime(System.currentTimeMillis());
        mMessageList.add(message);
    }
}