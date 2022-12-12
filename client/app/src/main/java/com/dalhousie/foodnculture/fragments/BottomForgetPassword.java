package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dalhousie.foodnculture.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomForgetPassword extends BottomSheetDialogFragment {

    public BottomForgetPassword(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bottom_forget_password = inflater.inflate(R.layout.bottomsheet_password, container, false);
        return bottom_forget_password;
    }
}