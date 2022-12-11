package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;

public class UpcomingEventFragment extends Fragment {

    public UpcomingEventFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upcoming_event_fragment, container, false);
        ImageButton back_button = v.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        return v;
    }
}