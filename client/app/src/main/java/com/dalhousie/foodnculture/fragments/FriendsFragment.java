package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.adapters.CustomAdapter;
import com.dalhousie.foodnculture.models.Friends;

import java.util.ArrayList;


public class FriendsFragment extends Fragment implements CustomAdapter.OnUserListener {
    

    private RecyclerView recyclerView;
    private ArrayList<Friends> Friends_all;
    private String[] friends_name;
    private String[] friends_username;
    private int[] ImgResource;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewfriends = inflater.inflate(R.layout.fragment_friends, container, false);

        ImageButton back_button = viewfriends.findViewById(R.id.btnArrowleft);


        back_button.setOnClickListener(view -> getActivity().onBackPressed());

        return viewfriends;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initialize_data();

        recyclerView = view.findViewById(R.id.recyclerFriends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomAdapter adapter = new CustomAdapter(getContext(), Friends_all, this);
        recyclerView.setAdapter(adapter);
        
    }

    private void initialize_data() {

        Friends_all = new ArrayList<>();

        friends_name = new String[]{
                getString(R.string.friend1),
                getString(R.string.friend2),
                getString(R.string.friend3),
                getString(R.string.friend4),
                getString(R.string.friend5),
                getString(R.string.friend6)
        };

        friends_username = new String[]{
                getString(R.string.frienduser1),
                getString(R.string.frienduser2),
                getString(R.string.frienduser3),
                getString(R.string.frienduser4),
                getString(R.string.frienduser5),
                getString(R.string.frienduser6)
        };

        ImgResource = new int[]{
                R.drawable.img_avatar,
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4,
                R.drawable.img_1
        };

        for (int iter = 0; iter<friends_name.length; iter++ ){
            Friends friend = new Friends(friends_name[iter],friends_username[iter],ImgResource[iter]);
            Friends_all.add(friend);
        }
    }

    @Override
    public void onUserClick(int position) {

    }
}
