package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalhousie.foodnculture.R;


public class AccountDeleteFragment extends Fragment {

    public AccountDeleteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View account_delete = inflater.inflate(R.layout.fragment_account_delete, container, false);

        return account_delete;
    }
}