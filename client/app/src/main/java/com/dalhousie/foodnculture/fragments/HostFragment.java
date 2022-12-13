package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.enums.EventType;
import com.dalhousie.foodnculture.models.Event;
import com.google.android.material.snackbar.Snackbar;

public class HostFragment extends Fragment {

    EditText title;
    EditText startDate;
    EditText venue;
    EditText endDate;
    EditText capacity;
    EditText description;

    public HostFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_hostevent, container, false);

        ImageButton back_button = v.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> getActivity().onBackPressed());

        Button createEventButton = v.findViewById(R.id.btnCreateEvent);
        createEventButton.setOnClickListener(view -> {

            title = v.findViewById(R.id.editTitle);
            startDate = v.findViewById(R.id.editDate);
            venue = v.findViewById(R.id.editVenue);
            endDate = v.findViewById(R.id.endDate);
            capacity = v.findViewById(R.id.editMaxCapacity);
            description = v.findViewById(R.id.editDescription);


            if (validateField(title) || validateField(startDate) || validateField(venue) || validateField(endDate) || validateField(capacity) || validateField(description)){
                Snackbar.make(view, "All the fields are required!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else {
                try {
                    if (saveEvent() == 1){
                        clearFields();
                        Toast.makeText(getContext(), "Event has been created successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong while creating an Event", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }

    private boolean validateField(EditText checkField) {
        if (checkField.getText().length() == 0) {
            checkField.setError("Required field");
            return true;
        }
        return false;
    }

    int saveEvent() throws Exception {
        Event event = new Event();
        event.setTitle(title.getText().toString());
        event.setStartDatetime(startDate.getText().toString());
        event.setVenue(venue.getText().toString());
        event.setStatus("CREATED");
        event.setEndDatetime(endDate.getText().toString());
        event.setDescription(description.getText().toString());
        event.setMaxCapacity(Integer.valueOf(capacity.getText().toString()));
        event.setEventType(EventType.COMMUNITY);
        return ApiFacade.getInstance().getEventApi().save(event);
    }

    private void clearFields(){
        title.getText().clear();
        startDate.getText().clear();
        venue.getText().clear();
        endDate.getText().clear();
        description.getText().clear();
        capacity.getText().clear();
    }

}