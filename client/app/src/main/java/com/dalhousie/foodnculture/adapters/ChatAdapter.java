package com.dalhousie.foodnculture.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.models.Messages;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHolder> {

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    Context context;
    List<Messages> chatModelsList;
    Integer senderUserId;


    public ChatAdapter(Context context, List<Messages> list, Integer senderUserId) {
        this.context = context;
        this.chatModelsList = list;
        this.senderUserId = senderUserId;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_LEFT) {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_left, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.row_chat_right, parent, false);
        }
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String message = chatModelsList.get(position).getContent();
        holder.message.setText(message);
    }

    @Override
    public int getItemCount() {
        return chatModelsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatModelsList.get(position).getUserId() == senderUserId) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView message;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_body);
        }
    }
}

