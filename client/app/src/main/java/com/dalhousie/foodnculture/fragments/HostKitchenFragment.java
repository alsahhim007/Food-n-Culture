package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dalhousie.foodnculture.R;


public class HostKitchenFragment extends Fragment {

    public HostKitchenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View hostkitchen = inflater.inflate(R.layout.fragment_host_kitchen, container, false);
        ImageButton back_button = hostkitchen.findViewById(R.id.btnArrowleft);



        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        return hostkitchen;



    }
}