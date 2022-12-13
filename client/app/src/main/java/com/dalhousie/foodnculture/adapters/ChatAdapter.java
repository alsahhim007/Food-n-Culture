package com.dalhousie.foodnculture.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.models.ChatModel;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Myholder> {

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    Context context;
    ArrayList<ChatModel> chatmodels_list;

    // Database connection
//    FirebaseUser firebaseUser;

    public ChatAdapter(Context context, ArrayList<ChatModel> list) {
        this.context = context;
        this.chatmodels_list = list;
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
        String message = chatmodels_list.get(position).getMessage();
//        String type = chatmodels_list.get(position);
            holder.message.setText(message);

//        try {
////            Glide.with(context).load(imageurl).into(holder.image);
//        } catch (Exception e) {
//
//        }
//        if (type.equals("text")) {
//            holder.message.setVisibility(View.VISIBLE);
////            holder.mimage.setVisibility(View.GONE);
//            holder.message.setText(message);
//        } else {
//            holder.message.setVisibility(View.GONE);
////            holder.mimage.setVisibility(View.VISIBLE);
////            Glide.with(context).load(message).into(holder.mimage);
//        }
    }

    @Override
    public int getItemCount() {
        return chatmodels_list.size();
    }

    @Override
    public int getItemViewType(int position) {

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chatmodels_list.get(position).getSender().equals(1)){
            //firebaseUser.getUid())) {
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

