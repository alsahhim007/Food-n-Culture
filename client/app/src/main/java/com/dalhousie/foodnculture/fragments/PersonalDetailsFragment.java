package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dalhousie.foodnculture.R;

public class PersonalDetailsFragment extends Fragment {


    public PersonalDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal_details, container, false);
        ImageButton back_button = v.findViewById(R.id.btnArrowleft);

        EditText EditName = v.findViewById(R.id.txtName);
        EditText EditEmail = v.findViewById(R.id.txtEmail);

        back_button.setOnClickListener(view -> getActivity().onBackPressed());

        return v;
    }
}