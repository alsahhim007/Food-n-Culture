package com.dalhousie.foodnculture.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Event;
import com.dalhousie.foodnculture.models.EventMember;
import com.dalhousie.foodnculture.models.User;
import com.dalhousie.foodnculture.utilities.EventDateAndVenueFormatter;
import com.dalhousie.foodnculture.utilities.IFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Set;

public class UpcomingEventFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View upcomingEventsView = inflater.inflate(R.layout.upcoming_event_fragment, container, false);
        ImageButton back_button = upcomingEventsView.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        LinearLayout event1 = upcomingEventsView.findViewById(R.id.linearlayout1);
        LinearLayout event2 = upcomingEventsView.findViewById(R.id.linearlayout2);
        LinearLayout pastEvent1 = upcomingEventsView.findViewById(R.id.linearlayout3);
        LinearLayout pastEvent2 = upcomingEventsView.findViewById(R.id.linearlayout4);
        IFormatter<Event> formatter = new EventDateAndVenueFormatter();

        List<Event> events = getEvents();
        List<Event> upcomingEvents = getUpcomingEvents(events);
        TextView event1Title = upcomingEventsView.findViewById(R.id.txtEvent1);
        if (upcomingEvents.size() > 0) {
            event1Title.setText(upcomingEvents.get(0).getTitle());
            TextView txtTimeLocation1 = upcomingEventsView.findViewById(R.id.txtDate);
            txtTimeLocation1.setText(formatter.format(upcomingEvents.get(0)));
            if (upcomingEvents.size() > 1) {
                TextView event2Title = upcomingEventsView.findViewById(R.id.txtEvent2);
                event2Title.setText(upcomingEvents.get(1).getTitle());
                TextView txtTimeLocation2 = upcomingEventsView.findViewById(R.id.txtDate2);
                txtTimeLocation2.setText(formatter.format(upcomingEvents.get(1)));
            }
        }

        List<Event> pastEvents = getPastEvents(events);
        if (pastEvents.size() > 0) {
            TextView pastEvent1Title = upcomingEventsView.findViewById(R.id.txtEvent3);
            pastEvent1Title.setText(pastEvents.get(0).getTitle());
            TextView txtPastTimeLocation1 = upcomingEventsView.findViewById(R.id.txtDate3);
            txtPastTimeLocation1.setText(formatter.format(pastEvents.get(0)));
            if (pastEvents.size() > 1) {
                TextView pastEvent2Title = upcomingEventsView.findViewById(R.id.txtEvent4);
                pastEvent2Title.setText(pastEvents.get(1).getTitle());
                TextView txtPastTimeLocation2 = upcomingEventsView.findViewById(R.id.txtDate4);
                txtPastTimeLocation2.setText(formatter.format(pastEvents.get(1)));
            }
        }
        if (upcomingEvents.size() > 0) {
            event1.setOnClickListener(view -> {
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment, new OpenEvent(upcomingEvents.get(0)));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });
        }
        if (upcomingEvents.size() > 1) {
            event2.setOnClickListener(view -> {
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment, new OpenEvent((upcomingEvents.get(1))));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });
        }

        if (pastEvents.size() > 0) {
            pastEvent1.setOnClickListener(view -> {
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment, new OpenEvent((pastEvents.get(0))));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });
        }
        if (pastEvents.size() > 1) {
            pastEvent2.setOnClickListener(view -> {
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment, new OpenEvent((pastEvents.get(1))));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });
        }
        return upcomingEventsView;
    }

    List<Event> getEvents() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        try {
            if (email.length() > 0) {
                Optional<User> currentUser = ApiFacade.getInstance().getUserApi().getByEmail(email);
                if (currentUser.isPresent()) {
                    List<EventMember> members = ApiFacade.getInstance().getEventMemberApi().getMembersByUserId(currentUser.get().getId());
                    return getEventListFromMembers(members);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    List<Event> getUpcomingEvents(List<Event> events) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Event> pastEvents = new ArrayList<>();
        ListIterator<Event> listIterator = events.listIterator(events.size());
        while (listIterator.hasNext()) {
            Event pastEvent = listIterator.next();
            LocalDateTime dateTime = LocalDateTime.parse(pastEvent.getStartDatetime(), formatter);
            if (dateTime.isAfter(LocalDateTime.now())) {
                pastEvents.add(pastEvent);
            }
        }
        return pastEvents;
    }

    List<Event> getPastEvents(List<Event> events) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Event> pastEvents = new ArrayList<>();
        ListIterator<Event> listIterator = events.listIterator(events.size());
        while (listIterator.hasPrevious()) {
            Event pastEvent = listIterator.previous();
            LocalDateTime dateTime = LocalDateTime.parse(pastEvent.getEndDatetime(), formatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                pastEvents.add(pastEvent);
            }
        }
        return pastEvents;
    }

    List<Event> getEventListFromMembers(List<EventMember> members) {
        List<Event> events = new ArrayList<>();
        Set<Integer> eventIds = new HashSet<>();
        for (EventMember member : members) {
            eventIds.add(member.getEventId());
        }
        for (int eventId : eventIds) {
            Event event = ApiFacade.getInstance().getEventApi().getById(eventId).get();
            events.add(event);
        }
        return events;
    }
}