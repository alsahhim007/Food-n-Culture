package com.dalhousie.foodnculture;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;

public class HostFragment extends Fragment {

    public HostFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_hostevent, container, false);

        ImageButton back_button = v.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> getActivity().onBackPressed());

        EditText title = v.findViewById(R.id.editTitle);
        EditText date = v.findViewById(R.id.editDate);
        EditText venue = v.findViewById(R.id.editVenue);
        EditText email = v.findViewById(R.id.editEmailID);
        EditText capacity = v.findViewById(R.id.editMaxCapacity);
        EditText description = v.findViewById(R.id.editDescription);

        String t_str = title.getText().toString();
        String d_str = date.getText().toString();
        String v_str = venue.getText().toString();
        String e_str = email.getText().toString();
        String c_str = capacity.getText().toString();
        String des_str = description.getText().toString();

        Button createEventButton = v.findViewById(R.id.btnCreateEvent);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_str.matches("") || d_str.matches("") || v_str.matches("") || e_str.matches("") || c_str.matches("") || des_str.matches("")){
                    Snackbar.make(view, "All the fields are required!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // TODO? - Clear editText fields and show snack-bar on successful request
                Snackbar.make(view, "Event created successfully! 🎉", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return v;
    }
}