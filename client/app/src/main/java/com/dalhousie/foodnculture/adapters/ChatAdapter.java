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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Myholder> {

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    Context context;
    List<Messages> chatmodels_list;
    Integer senderUserId;


    public ChatAdapter(Context context, List<Messages> list, Integer senderUserId) {
        this.context = context;
        this.chatmodels_list = list;
        this.senderUserId = senderUserId;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_LEFT) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_chat_left, parent, false);
            return new Myholder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.row_chat_right, parent, false);
            return new Myholder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        String message = chatmodels_list.get(position).getContent();
            holder.message.setText(message);
    }

    @Override
    public int getItemCount() {
        return chatmodels_list.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (chatmodels_list.get(position).getUserId() == senderUserId){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

    class Myholder extends RecyclerView.ViewHolder {

        TextView message;

        public Myholder(@NonNull View itemView) {

            super(itemView);

            message = itemView.findViewById(R.id.message_body);

        }
    }
}

