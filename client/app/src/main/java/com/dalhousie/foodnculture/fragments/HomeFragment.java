package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Event;
import com.dalhousie.foodnculture.utilities.EventDateAndVenueFormatter;
import com.dalhousie.foodnculture.utilities.IFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<Event> events = getEvents();
        View homeView = inflater.inflate(R.layout.home_fragment, container, false);

        Button hostEvent = homeView.findViewById(R.id.btnHostEvent);
        Button myEvents = homeView.findViewById(R.id.btnMyEvents);
        LinearLayout event1 = homeView.findViewById(R.id.event1);
        LinearLayout event2 = homeView.findViewById(R.id.event2);
        LinearLayout pastEvent1 = homeView.findViewById(R.id.pastEvent1);
        LinearLayout pastEvent2 = homeView.findViewById(R.id.pastEvent2);

        IFormatter<Event> formatter = new EventDateAndVenueFormatter();

        TextView event1Title = homeView.findViewById(R.id.eventTitle1);
        event1Title.setText(events.get(0).getTitle());
        TextView txtTimeLocation1 = homeView.findViewById(R.id.txtTimeLocation1);
        txtTimeLocation1.setText(formatter.format(events.get(0)));

        TextView event2Title = homeView.findViewById(R.id.eventTitle2);
        event2Title.setText(events.get(1).getTitle());
        TextView txtTimeLocation2 = homeView.findViewById(R.id.txtTimeLocation2);
        txtTimeLocation2.setText(formatter.format(events.get(1)));

        List<Event> pastEvents = getPastEvents(events);
        TextView pastEvent1Title = homeView.findViewById(R.id.txtPastEvent1);
        pastEvent1Title.setText(pastEvents.get(0).getTitle());
        TextView txtPastTimeLocation1 = homeView.findViewById(R.id.txtPastTimeLocation1);
        txtPastTimeLocation1.setText(formatter.format(pastEvents.get(0)));
        TextView pastEvent2Title = homeView.findViewById(R.id.txtPastEvent2);
        pastEvent2Title.setText(pastEvents.get(1).getTitle());
        TextView txtPastTimeLocation2 = homeView.findViewById(R.id.txtPastTimeLocation2);
        txtPastTimeLocation2.setText(formatter.format(pastEvents.get(1)));

        event1.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new OpenEvent(events.get(0)));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        event2.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new OpenEvent((events.get(1))));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        hostEvent.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new HostFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        myEvents.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new UpcomingEventFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        pastEvent1.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new OpenEvent((pastEvents.get(0))));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        pastEvent2.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment, new OpenEvent((pastEvents.get(1))));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        return homeView;
    }

    List<Event> getEvents() {
        return ApiFacade.getInstance().getEventApi().findAll();
    }

    List<Event> getPastEvents(List<Event> events) {
        List<Event> pastEvents = new ArrayList<>();
        ListIterator<Event> listIterator = events.listIterator(events.size());
        while (listIterator.hasPrevious()) {
            pastEvents.add(listIterator.previous());
        }
        return pastEvents;
    }
}