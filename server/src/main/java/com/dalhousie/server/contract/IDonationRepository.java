package com.dalhousie.server.contract;

import com.dalhousie.server.model.Donation;

import java.util.List;

public interface IDonationRepository extends ICrudRepository <Donation, Integer>  {
    public List<Donation> getDonationsByEventId(Integer eventId);
    public double getTotalDonationByEventId(Integer eventId);
}
