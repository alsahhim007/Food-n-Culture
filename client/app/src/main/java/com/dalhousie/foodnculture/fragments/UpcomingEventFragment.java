package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;

public class UpcomingEventFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View upcomingEvents = inflater.inflate(R.layout.upcoming_event_fragment, container, false);
        ImageButton back_button = upcomingEvents.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        LinearLayout event1 = upcomingEvents.findViewById(R.id.txtEvent1);
        LinearLayout event2 = upcomingEvents.findViewById(R.id.txtEvent2);
        LinearLayout pastEvent1 = upcomingEvents.findViewById(R.id.txtEvent3);
        LinearLayout pastEvent2 = upcomingEvents.findViewById(R.id.txtEvent4);
        return upcomingEvents;
    }
}