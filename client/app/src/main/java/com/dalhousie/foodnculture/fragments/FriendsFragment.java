package com.dalhousie.foodnculture.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.activities.ChatActivity;
import com.dalhousie.foodnculture.adapters.CustomAdapter;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Friends;
import com.dalhousie.foodnculture.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FriendsFragment extends Fragment implements CustomAdapter.OnUserListener {

    private ArrayList<Friends> Friends_all;
    private final List<String> friends_name = new ArrayList<>();
    private final List<String> friends_username = new ArrayList<>();
    private final List<Integer> friends_id = new ArrayList<>();
    Optional<User> currentUser;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFriends = inflater.inflate(R.layout.fragment_friends, container, false);
        ImageButton back_button = viewFriends.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> requireActivity().onBackPressed());
        return viewFriends;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize_data();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerFriends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomAdapter adapter = new CustomAdapter(getContext(), Friends_all, this);
        recyclerView.setAdapter(adapter);
    }

    private void initialize_data() {
        getFriends();
        Friends_all = new ArrayList<>();
        int[] imgResource = new int[]{
                R.drawable.img_avatar,
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4,
                R.drawable.img_1
        };
        for (int item = 0; item < friends_name.size(); item++) {
            Friends friend = new Friends(friends_name.get(item), friends_username.get(item), imgResource[item]);
            Friends_all.add(friend);
        }
    }

    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this.getActivity(), ChatActivity.class);
        intent.putExtra("friendId", friends_id.get(position));
        currentUser.ifPresent(user -> intent.putExtra("userId", user.getId()));
        startActivity(intent);
    }

    void getFriends() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        List<User> friends;
        try {
            if (email.length() > 0) {
                currentUser = ApiFacade.getInstance().getUserApi().getByEmail(email);
                if (currentUser.isPresent()) {
                    friends = ApiFacade.getInstance().getFriendApi().getAllFriendsByUserId(currentUser.get().getId());
                    if (friends.size() > 0) {
                        for (User friend : friends) {
                            friends_name.add(friend.getFirstName() + " " + friend.getLastName());
                            friends_username.add(friend.getUserName());
                            friends_id.add(friend.getId());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
