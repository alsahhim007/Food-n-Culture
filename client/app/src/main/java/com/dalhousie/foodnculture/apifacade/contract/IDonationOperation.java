package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.Donation;

import java.util.List;

public interface IDonationOperation extends ICrudOperation<Donation, Integer> {
    public List<Donation> getDonationsByEventId(Integer eventId);
    public double getTotalDonationByEventId(Integer eventId);
}
