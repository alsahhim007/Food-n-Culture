package com.dalhousie.server.businesslogic;

import java.util.List;

import com.dalhousie.server.model.Venues;

public interface IVenueRepository extends ICrudRepository <Venues, Integer> {
    public List<Venues> getVenuesByUserId(Integer userId);
}
