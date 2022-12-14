package com.dalhousie.server.contract;

import com.dalhousie.server.model.Venues;

import java.util.List;

public interface IVenueRepository extends ICrudRepository <Venues, Integer> {
    public List<Venues> getVenuesByUserId(Integer userId);
}
