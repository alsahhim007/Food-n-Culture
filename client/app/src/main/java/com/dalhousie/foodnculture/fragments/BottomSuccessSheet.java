package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dalhousie.foodnculture.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSuccessSheet extends BottomSheetDialogFragment {

    public BottomSuccessSheet(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bottomDonation = inflater.inflate(R.layout.fragment_bottom_success_sheet, container, false);
        return bottomDonation;
    }
}