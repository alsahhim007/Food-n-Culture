package com.dalhousie.foodnculture.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.activities.MainActivity;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Optional;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
    }

    SharedPreferences sharedPreferences;
    Optional<User> user;
    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        if (email.length() > 0) {
            user = ApiFacade.getInstance().getUserApi().getByEmail(email);
        }
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        ImageButton back_button = v.findViewById(R.id.btnArrowleft);
        back_button.setOnClickListener(view -> requireActivity().onBackPressed());

        TextView userProfileName = v.findViewById(R.id.txtUserProfileName);
        TextView userUserName = v.findViewById(R.id.txtUserUsername);

        if (user.isPresent()) {
            userProfileName.setText(String.format("%s %s", user.get().getFirstName(), user.get().getLastName()));
            userUserName.setText(String.format("@%s", user.get().getUserName()));
        }


        TextView checkPersonalDetails = v.findViewById(R.id.personaldetails);
        TextView checkPastEvents = v.findViewById(R.id.pastEvent);
        TextView checkfriends = v.findViewById(R.id.friends);
        TextView checkkitchen = v.findViewById(R.id.hostkitchen);
        TextView logoutoff = v.findViewById(R.id.logoutoff);
        TextView deleteaccount = v.findViewById(R.id.deleteaccount);


        // Open Profile page
        checkPersonalDetails.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new PersonalDetailsFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        // Open Past Events page
        checkPastEvents.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new PastEventFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });


        // Open Friends page
        checkfriends.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new FriendsFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        // Open Kitchen page
        checkkitchen.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.linearColumnairplane, new HostKitchenFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        logoutoff.setOnClickListener(view -> logoutDialog());

        // Delete Account
        deleteaccount.setOnClickListener(view -> deleteAccount());

        return v;

    }

    private void deleteAccount() {
        final BottomSheetDialog account_delete_bsd = new BottomSheetDialog(this.requireContext());
        account_delete_bsd.setContentView(R.layout.fragment_account_delete);
        account_delete_bsd.show();

        Button btnYes = account_delete_bsd.findViewById(R.id.btnYes);
        Button btnNo = account_delete_bsd.findViewById(R.id.btnNo);

        if (btnYes != null) {
            btnYes.setOnClickListener(view -> {
                if (user.isPresent()) {
                    int status = ApiFacade.getInstance().getUserApi().delete(user.get());
                    if (status == 1) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged", false);
                        editor.putString("email", null);
                        editor.apply();
                        account_delete_bsd.cancel();
                        Intent mainIntent = new Intent(requireContext(), MainActivity.class);
                        startActivity(mainIntent);
                        requireActivity().finishAffinity();
                        requireActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "There is an error while deleting user", Toast.LENGTH_SHORT).show();
                        account_delete_bsd.cancel();
                    }
                }
            });
        }
        if (btnNo != null) {
            btnNo.setOnClickListener(view -> account_delete_bsd.cancel());
        }
    }

    private void logoutDialog() {
        final BottomSheetDialog logout_bsd = new BottomSheetDialog(this.requireContext());
        logout_bsd.setContentView(R.layout.fragment_logout);
        logout_bsd.show();

        Button btnYes = logout_bsd.findViewById(R.id.btnYes);
        Button btnNo = logout_bsd.findViewById(R.id.btnNo);

        if (btnYes != null) {
            btnYes.setOnClickListener(view -> {
                if (email.length() > 0) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged", false);
                    editor.putString("email", null);
                    editor.apply();
                    logout_bsd.cancel();
                    Intent mainIntent = new Intent(requireContext(), MainActivity.class);
                    startActivity(mainIntent);
                    requireActivity().finishAffinity();
                    requireActivity().finish();
                }
            });
        }
        if (btnNo != null) {
            btnNo.setOnClickListener(view -> logout_bsd.cancel());
        }
    }
}