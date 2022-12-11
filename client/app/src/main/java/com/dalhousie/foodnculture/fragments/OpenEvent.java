package com.dalhousie.foodnculture.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.Constants;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.callback.OnCompleteListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

            final BottomSheetDialog ssd = new BottomSheetDialog(view.getContext());
            ssd.setContentView(R.layout.fragment_bottom_success_sheet);

            FrameLayout gPay = bsd.findViewById(R.id.btnPayWithGpay);
            gPay.setOnClickListener(view1 -> {
                requestPayment(view);
                bsd.cancel();
                ssd.show();
            });
        });

        back_button.setOnClickListener(view -> getActivity().onBackPressed());
        return v;
    }

    private void requestPayment(View view) {

    }


}