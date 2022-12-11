package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dalhousie.foodnculture.R;

public class HomeFragment extends Fragment {

    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        Button hostEvent = v.findViewById(R.id.btnHostEvent);
        Button beaGuest = v.findViewById(R.id.btnBeaGuest);
        TextView pastEvent = v.findViewById(R.id.txtEvents2);
        TextView event1 = v.findViewById(R.id.txtEvent1);

        event1.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new OpenEvent());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        hostEvent.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new HostFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        beaGuest.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new UpcomingEventFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        pastEvent.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new PastEventFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return v;
    }
}