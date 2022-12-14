package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.Donation;

import java.util.List;

public interface IDonationOperation extends ICrudOperation<Donation, Integer> {
    List<Donation> getDonationsByEventId(Integer eventId);

    double getTotalDonationByEventId(Integer eventId);
}
