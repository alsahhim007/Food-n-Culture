package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;


public class HostKitchenFragment extends Fragment {

    public HostKitchenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View hostKitchen = inflater.inflate(R.layout.fragment_host_kitchen, container, false);
        ImageButton back_button = hostKitchen.findViewById(R.id.btnArrowleft);

        back_button.setOnClickListener(view -> requireActivity().onBackPressed());
        return hostKitchen;
    }
}