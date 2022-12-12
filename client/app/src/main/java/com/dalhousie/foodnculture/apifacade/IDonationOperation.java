package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Donation;

import java.util.List;

public interface IDonationOperation extends ICrudOperation<Donation, Integer> {
    public List<Donation> getDonationsByEventId(Integer eventId);
}
