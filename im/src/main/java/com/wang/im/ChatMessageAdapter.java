package com.wang.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = ChatMessageAdapter.class.getSimpleName();
    private String mName;
    private Context mContext;
    private List<V2TIMMessage> messageList = new ArrayList<>();
    public static final int my_chat = 1;
    public static final int you_chat = 2;
    public static final int my_voice = 3;
    public static final int you_voice = 4;


    public ChatMessageAdapter(Context context) {
        mContext = context;
    }

    public void setItemList(List<V2TIMMessage> messageList) {
        this.messageList = messageList;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return my_chat;
        }
        if (position == 1) {
            return you_chat;
        }
        if (position == 2) {
            return my_voice;
        }
        if (position == 3) {
            return you_voice;
        }
        return my_chat;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType) {
            case my_chat:
                return new MyTextChatMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_chat_message, parent, false));
            case you_chat:
                return new YouTextChatMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.you_text_chat_message, parent, false));
            case my_voice:
                return new MyVoiceMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_voice_message, parent, false));
            case you_voice:
                return new YouVoiceMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.you_voice_message, parent, false));
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_chat_message, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (mMessage == null) {
//            return;
//
//        }
        V2TIMMessage v2TIMMessage = messageList.get(position);
        String text = v2TIMMessage.getTextElem().getText();

//        int elemType = mMessage.getElemType();
        if (holder instanceof MyTextChatMessageViewHolder) {
            ((MyTextChatMessageViewHolder) holder).name_icon.setText(mName);
            ((MyTextChatMessageViewHolder) holder).text_chat.setText(text);
        } else if (holder instanceof YouTextChatMessageViewHolder) {
            ((YouTextChatMessageViewHolder) holder).name_icon.setText(mName);
            ((YouTextChatMessageViewHolder) holder).text_chat.setText(text);
        } else if (holder instanceof MyVoiceMessageViewHolder) {
            ((MyVoiceMessageViewHolder) holder).name_icon.setText(mName);
            Glide.with(mContext).asGif().load(R.drawable.play_voice).into(((MyVoiceMessageViewHolder) holder).voice);
        } else if (holder instanceof YouVoiceMessageViewHolder) {
            ((YouVoiceMessageViewHolder) holder).name_icon.setText(mName);
            Glide.with(mContext).asGif().load(R.drawable.play_voice).into(((YouVoiceMessageViewHolder) holder).voice);
        }

    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void setChatName(String name) {
        mName = name;
        notifyDataSetChanged();
    }

    public void addMessage(String text) {
        V2TIMMessage textMessage = V2TIMManager.getMessageManager().createTextMessage(text);
        messageList.add(textMessage);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_chat;
        public TextView name_icon;

        public ViewHolder(View view) {
            super(view);
            name_icon = view.findViewById(R.id.name_icon);
            text_chat = view.findViewById(R.id.text_chat);
        }
    }


    public static class MyTextChatMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView name_icon;
        public TextView text_chat;

        public MyTextChatMessageViewHolder(View view) {
            super(view);
            name_icon = view.findViewById(R.id.name_icon);
            text_chat = view.findViewById(R.id.text_chat);
        }
    }

    public static class YouTextChatMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView text_chat;
        public TextView name_icon;

        public YouTextChatMessageViewHolder(View view) {
            super(view);
            name_icon = view.findViewById(R.id.name_icon);
            text_chat = view.findViewById(R.id.text_chat);
        }
    }

    public static class MyVoiceMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView name_icon;
        public ImageView voice;

        public MyVoiceMessageViewHolder(View view) {
            super(view);
            voice = view.findViewById(R.id.voice);
            name_icon = view.findViewById(R.id.name_icon);
        }
    }


    public static class YouVoiceMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView name_icon;
        public ImageView voice;

        public YouVoiceMessageViewHolder(View view) {
            super(view);
            name_icon = view.findViewById(R.id.name_icon);
            voice = view.findViewById(R.id.voice);
        }

    }
}