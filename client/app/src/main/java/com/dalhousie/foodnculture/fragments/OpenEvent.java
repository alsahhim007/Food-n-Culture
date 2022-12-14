package com.dalhousie.foodnculture.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Amenities;
import com.dalhousie.foodnculture.models.Donation;
import com.dalhousie.foodnculture.models.Event;
import com.dalhousie.foodnculture.models.EventMember;
import com.dalhousie.foodnculture.models.User;
import com.dalhousie.foodnculture.utilities.EventDateAndVenueFormatter;
import com.dalhousie.foodnculture.utilities.IFormatter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class OpenEvent extends Fragment {

    Event event;
    Optional<User> currentUser;

    OpenEvent(Event event) {
        this.event = event;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View openEventView = inflater.inflate(R.layout.fragment_open_event, container, false);
        ImageButton back_button = openEventView.findViewById(R.id.btnArrowleft);

        TextView guestListButton = openEventView.findViewById(R.id.btnGuestList);

        Button registerButton = openEventView.findViewById(R.id.btnRegister);
        Button donateButton = openEventView.findViewById(R.id.btnDonate);
        IFormatter<Event> formatter = new EventDateAndVenueFormatter();
        if (isPastEvent()) {
            registerButton.setVisibility(View.INVISIBLE);
            donateButton.setVisibility(View.INVISIBLE);
        }

        TextView eventTitle = openEventView.findViewById(R.id.txtEventTitle);
        eventTitle.setText(event.getTitle());
        TextView eventDate = openEventView.findViewById(R.id.txtDate);
        eventDate.setText(formatter.format(event));
        TextView eventDescription = openEventView.findViewById(R.id.txtDescriptionTwo);
        eventDescription.setText(event.getDescription());
        TextView totalDonation = openEventView.findViewById(R.id.txtTotalDonation);
        totalDonation.setText(getTotalDonation());

        TextView amenitiesText = openEventView.findViewById(R.id.txtAmenities);
        amenitiesText.setText(getAmenities());

        registerButton.setOnClickListener(view -> registerEvent());

        guestListButton.setOnClickListener(view -> {
            BottomSheetDialog gListD = new BottomSheetDialog(view.getContext());
            BottomSheetBehavior<View> bottomSheetBehavior;
            View view1 = LayoutInflater.from(requireActivity()).inflate(R.layout.guest_list_bottom_sheet, null);
            gListD.setContentView(view1);
            bottomSheetBehavior = BottomSheetBehavior.from((View) view1.getParent());
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            LinearLayout layout = gListD.findViewById(R.id.GuestListLayout);
            layout.setMinimumHeight(1300);

            List<User> friends;
            TextView g1Name = view1.findViewById(R.id.Guest1txtGuestName);
            TextView g2Name = view1.findViewById(R.id.Guest2txtGuestName);
            TextView g3Name = view1.findViewById(R.id.Guest3txtGuestName);
            TextView g4Name = view1.findViewById(R.id.Guest4txtGuestName);

            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
            String email = sharedPreferences.getString("email", "");
            currentUser = ApiFacade.getInstance().getUserApi().getByEmail(email);
            friends = ApiFacade.getInstance().getFriendApi().getAllFriendsByUserId(currentUser.get().getId());

            g1Name.setText(String.format("%s %s", friends.get(0).getFirstName(), friends.get(0).getLastName()));
            g2Name.setText(String.format("%s %s", friends.get(1).getFirstName(), friends.get(1).getLastName()));
            g3Name.setText(String.format("%s %s", friends.get(2).getFirstName(), friends.get(2).getLastName()));
            g4Name.setText(String.format("%s %s", friends.get(3).getFirstName(), friends.get(3).getLastName()));

            gListD.show();

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
            if (gPay != null) {
                gPay.setOnClickListener(view1 -> {

                    EditText donateField = bsd.findViewById(R.id.txtInputDataOne);
                    int amount = 0;
                    if (donateField != null) {
                        amount = Integer.parseInt(donateField.getText().toString());
                    }
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
                            totalDonation.setText(getTotalDonation());
                        } else {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        back_button.setOnClickListener(view -> requireActivity().onBackPressed());
        return openEventView;
    }

    int saveDonation(int amount) throws Exception {
        Donation donation = new Donation();

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        donation.setAmount(amount);
        donation.setEventId(event.getId());
        donation.setNote("Donation");
        donation.setEmail(email);

        return ApiFacade.getInstance().getDonationApi().save(donation);
    }

    private String getAmenities() {
        List<Amenities> amenities = ApiFacade.getInstance().getAmenitiesApi().findAll();
        String delimiter = ", ";
        StringJoiner joiner = new StringJoiner(delimiter);
        for (int i = 0; i < 3; i++) {
            joiner.add(amenities.get(i).getName());
        }
        return joiner.toString();
    }

    void registerEvent() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        EventMember member = new EventMember();
        member.setEventId(event.getId());
        member.setStatus("Requested");
        try {
            if (email.length() > 0) {
                Optional<User> user = ApiFacade.getInstance().getUserApi().getByEmail(email);
                if (user.isPresent()) {
                    member.setUserId(user.get().getId());
                }
            }
            if (ApiFacade.getInstance().getEventMemberApi().save(member) == 1) {
                final BottomSheetDialog bsd = new BottomSheetDialog(requireContext());
                bsd.setContentView(R.layout.fragment_bottom_success_sheet);
                bsd.show();
            } else {
                Toast.makeText(getContext(), "There is an error while registering to event", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    String getTotalDonation() {
        double totalDonation = ApiFacade.getInstance().getDonationApi().getTotalDonationByEventId(event.getId());
        return "$" + totalDonation;
    }

    boolean isPastEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(event.getEndDatetime(), formatter);
        if (dateTime.isBefore(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

}