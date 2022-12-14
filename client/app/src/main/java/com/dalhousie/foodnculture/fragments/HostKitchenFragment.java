package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;


public class HostKitchenFragment extends Fragment {

    public HostKitchenFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View hostkitchen = inflater.inflate(R.layout.fragment_host_kitchen, container, false);
        ImageButton back_button = hostkitchen.findViewById(R.id.btnArrowleft);

        CheckBox Refri = hostkitchen.findViewById(R.id.frameCheckmark_refri);
        CheckBox oven = hostkitchen.findViewById(R.id.frameCheckmark_oven);
        CheckBox stove = hostkitchen.findViewById(R.id.frameCheckmark_stove);
        CheckBox toaster = hostkitchen.findViewById(R.id.frameCheckmark_toaster);

        Button Add_item = hostkitchen.findViewById(R.id.ItemAdd);

        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        return hostkitchen;
    }
}