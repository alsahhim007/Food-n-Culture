package com.dalhousie.foodnculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

public class ForgetPasswordFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_password, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText forgetPassword = view.findViewById(R.id.forget_email_field);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String forget_text = forgetPassword.getText().toString();

                if (forget_text.matches("")){
                    forgetPassword.setError("Email required");
                    Snackbar.make(view, "Oops, you forgot to enter email.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(view, "Reset link has been sent successfully to registered email.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
