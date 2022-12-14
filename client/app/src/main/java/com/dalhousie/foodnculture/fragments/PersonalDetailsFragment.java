package com.dalhousie.foodnculture.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.User;

import java.util.Optional;

public class PersonalDetailsFragment extends Fragment {

    SharedPreferences sharedPreferences;
    Optional<User> user;

    public PersonalDetailsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        if (email.length() > 0) {
            user = ApiFacade.getInstance().getUserApi().getByEmail(email);
        }

        View v = inflater.inflate(R.layout.fragment_personal_details, container, false);
        ImageButton back_button = v.findViewById(R.id.btnArrowleft);

        EditText EditFirstName = v.findViewById(R.id.txtProfileName);
        EditText EditLastName = v.findViewById(R.id.txtProfileLastName);
        EditText EditEmail = v.findViewById(R.id.txtProfileEmail);

        ImageView updateFirstNameButton = v.findViewById(R.id.editNameButton);
        ImageView updateLastNameButton = v.findViewById(R.id.editLastNameButton);
        ImageView updateEmailButton = v.findViewById(R.id.editEmailButton);

        if (user.isPresent()) {
            EditFirstName.setText(String.format("%s", user.get().getFirstName()));
            EditLastName.setText(String.format("%s", user.get().getLastName()));
            EditEmail.setText(String.format("%s", user.get().getEmail()));
        }

        updateFirstNameButton.setOnClickListener(view -> {
            if (user.isPresent()) {
                String updatedName = EditFirstName.getText().toString();
                user.get().setFirstName(updatedName);
                if (ApiFacade.getInstance().getUserApi().update(user.get()) == 1) {
                    Toast.makeText(getContext(), "First Name updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to update First Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateLastNameButton.setOnClickListener(view -> {
            if (user.isPresent()) {
                String updatedName = EditLastName.getText().toString();
                user.get().setLastName(updatedName);
                if (ApiFacade.getInstance().getUserApi().update(user.get()) == 1) {
                    Toast.makeText(getContext(), "Last Name updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to update Last Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateEmailButton.setOnClickListener(view -> {
            if (user.isPresent()) {
                String updatedEmail = EditFirstName.getText().toString();
                user.get().setEmail(updatedEmail);
                if (ApiFacade.getInstance().getUserApi().update(user.get()) == 1) {
                    Toast.makeText(getContext(), "Email updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to update Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_button.setOnClickListener(view -> requireActivity().onBackPressed());
        return v;
    }
}