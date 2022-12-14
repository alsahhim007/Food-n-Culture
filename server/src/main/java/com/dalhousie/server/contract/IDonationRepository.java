package com.dalhousie.server.contract;

import java.util.List;

import com.dalhousie.server.model.Donation;

public interface IDonationRepository extends ICrudRepository <Donation, Integer>  {
    public List<Donation> getDonationsByEventId(Integer eventId);
    public double getTotalDonationByEventId(Integer eventId);
}
