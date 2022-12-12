package com.dalhousie.foodnculture.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

    Context context;
    ArrayList<Friends> Friend_all;


    public CustomAdapter(Context context, ArrayList<Friends> friend) {
        this.context = context;
        this.Friend_all = friend;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View friendview = LayoutInflater.from(context).inflate(R.layout.row_friends, parent, false);
        return new MyViewHolder(friendview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Friends friend = Friend_all.get(position);

        holder.f_name.setText(friend.FriendName);
        holder.user_name.setText('@'+friend.Uname);
        holder.user_image.setImageResource(friend.uimage);

    }

    @Override
    public int getItemCount() {
        return Friend_all.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView f_name;
        TextView user_name;
        ImageView user_image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            f_name = itemView.findViewById(R.id.txtFullnamerow);
            user_name = itemView.findViewById(R.id.txtUsername);
            user_image = itemView.findViewById(R.id.imageAvatar);
        }
    }
}























//    private ArrayList<name> FriendsName;
//    private ArrayList<fimage> FriendsImages;
//    private ArrayList<uname> Username;
//    Context context;
//
//
//    public CustomAdapter( Context context, ArrayList<name> FNames, ArrayList<uname> Username, ArrayList<fimage> personImages) {
//        this.context = context
//        this.FriendsName = FNames;
//        this.Username = Username;
//        this.FriendsImages = personImages;
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        // init the item view's
//        TextView name;
//        TextView username;
//        ImageView image;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            name = itemView.findViewById(R.id.txtFullnamerow);
//            username = itemView.findViewById(R.id.txtUsername);
//            image = itemView.findViewById(R.id.imageAvatar);
//        }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View friendview = LayoutInflater.from(context).inflate(R.layout.row_friends, parent, false);
//        return new MyViewHolder(friendview);
//        }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
////        String name = FriendsName.get(position).
//    }
//
//    @Override
//    public int getItemCount() {
//        return FriendsName.size();
//    }
//
//
////    @Override
////    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        // inflate the item Layout
////        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
////        // set the view's size, margins, paddings and layout parameters
////        return new MyViewHolder(v);
////    }
////
////    @Override
////    public void onBindViewHolder( MyViewHolder holder, int position) {
////        // set the data in items
////        holder.name.setText(FriendsName.get(position).toString());
////        holder.username.setText(Username.get(position).toString());
////        holder.image.setImageResource((Integer) FriendsImages.get(position));
////        // implement setOnClickListener event on item view.
//////        holder.itemView.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                // display a toast with person name on item click
//////                Toast.makeText(context, FriendsName.get(position), Toast.LENGTH_SHORT).show();
//////            }
//////        });
////    }
////
////    @Override
////    public int getItemCount() {
////        return FriendsName.size();
////    }
