package com.dalhousie.foodnculture.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Donation;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OpenEvent extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_open_event, container, false);
        ImageButton back_button = v.findViewById(R.id.btnArrowleft);

        Button registerButton = v.findViewById(R.id.btnRegister);
        Button donateButton = v.findViewById(R.id.btnDonate);

        registerButton.setOnClickListener(view ->   {
            final BottomSheetDialog bsd = new BottomSheetDialog(view.getContext());
            bsd.setContentView(R.layout.fragment_bottom_success_sheet);
            bsd.show();
        });

        donateButton.setOnClickListener(view -> {

            final BottomSheetDialog bsd = new BottomSheetDialog(view.getContext());
            bsd.setContentView(R.layout.fragment_bottom_donation_sheet);
            bsd.show();

            final BottomSheetDialog success_d = new BottomSheetDialog(view.getContext());
            success_d.setContentView(R.layout.fragment_bottom_success_sheet);

            final BottomSheetDialog process_d = new BottomSheetDialog(view.getContext());
            process_d.setContentView(R.layout.fragment_bottom_processing_sheet);

            FrameLayout gPay = bsd.findViewById(R.id.btnPayWithGpay);
            gPay.setOnClickListener(view1 -> {

                EditText donateField = bsd.findViewById(R.id.txtInputDataOne);
                int amount = Integer.parseInt(donateField.getText().toString());
                System.out.println(amount);

                bsd.dismiss();

                Toast.makeText(getContext(), "Processing...", Toast.LENGTH_SHORT).show();

                try {
                    Thread.sleep(1900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    if (saveDonation(amount) == 1) {
                        success_d.show();
                    }
                    else {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        return v;
    }

    int saveDonation(int amount) throws Exception {
        Donation donation = new Donation();

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        donation.setAmount(amount);
        donation.setEventId(1);
        donation.setNote("Donation");
        donation.setEmail(email);

        return ApiFacade.getInstance().getDonationApi().save(donation);
    }

}