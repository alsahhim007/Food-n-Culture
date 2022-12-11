package com.dalhousie.foodnculture.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dalhousie.foodnculture.LogoutFragment;
import com.dalhousie.foodnculture.R;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        ImageButton back_button = v.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> getActivity().onBackPressed());

        TextView checkPersonalDetails = v.findViewById(R.id.personaldetails);
//        TextView checkfriends = v.findViewById(R.id.friends);
        TextView checkkitchen = v.findViewById(R.id.hostkitchen);
        TextView logoutoff = v.findViewById(R.id.logoutoff);
        TextView deleteaccount = v.findViewById(R.id.deleteaccount);


        //Open Profile page
        checkPersonalDetails.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new PersonalDetailsFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });


//        // Open Friends page
//        checkfriends.setOnClickListener(view -> {
//            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.linearColumnairplane, new FriendsFragment());
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        });
//
        // Open Kitchen page
        checkkitchen.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new HostKitchenFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
//
        logoutoff.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new LogoutFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
//
//        // Delete Account
        deleteaccount.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new AccountDeleteFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return v;

    }
}