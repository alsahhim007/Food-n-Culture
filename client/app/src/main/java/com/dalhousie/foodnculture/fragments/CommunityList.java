package com.dalhousie.foodnculture.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Community;

import java.util.List;

public class CommunityList extends Fragment {
    TextView cTitle1;
    TextView cTitle2;
    TextView cTitle3;
    TextView cDescription1;
    TextView cDescription2;
    TextView cDescription3;

    public CommunityList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_community_list, container, false);
        showCommunity(v);
        return v;
    }

    void showCommunity(View v) {
        List<Community> community = ApiFacade.getInstance().getCommunityApi().findAll();
        cTitle1 = v.findViewById(R.id.cTitle1);
        cTitle2 = v.findViewById(R.id.cTitle2);
        cTitle3 = v.findViewById(R.id.cTitle3);
        cDescription1 = v.findViewById(R.id.cDescription1);
        cDescription2 = v.findViewById(R.id.cDescription2);
        cDescription3 = v.findViewById(R.id.cDescription3);

        cTitle1.setText(community.get(0).getTitle());
        cDescription1.setText(community.get(0).getDescription());

        cTitle2.setText(community.get(1).getTitle());
        cDescription2.setText(community.get(1).getDescription());

        cTitle3.setText(community.get(2).getTitle());
        cDescription3.setText(community.get(2).getDescription());
    }
}