package com.dalhousie.foodnculture.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.models.Friends;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private static final String TAG = "FriendsRecyclerAdapter";
    Context context;
    ArrayList<Friends> Friend_all;
    OnUserListener monUserListener;


    public CustomAdapter(Context context, ArrayList<Friends> friend, OnUserListener onUserListener) {
        this.context = context;
        this.Friend_all = friend;
        this.monUserListener = onUserListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View friendview = LayoutInflater.from(context).inflate(R.layout.row_friends, parent, false);
        return new MyViewHolder(friendview, monUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Friends friend = Friend_all.get(position);

        holder.f_name.setText(friend.FriendName);
        holder.user_name.setText(String.format("@%s", friend.Uname));
        holder.user_image.setImageResource(friend.uimage);

    }

    @Override
    public int getItemCount() {
        return Friend_all.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView f_name;
        TextView user_name;
        ImageView user_image;

        OnUserListener onUserListener;


        public MyViewHolder(@NonNull View itemView, OnUserListener onUserListener) {
            super(itemView);

            f_name = itemView.findViewById(R.id.txtFullnamerow);
            user_name = itemView.findViewById(R.id.txtUsername);
            user_image = itemView.findViewById(R.id.imageAvatar);
            this.onUserListener = onUserListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + (getAdapterPosition()));
            onUserListener.onUserClick(getAdapterPosition());
        }
    }
    public interface OnUserListener{

        void onUserClick(int position);

    }
}
