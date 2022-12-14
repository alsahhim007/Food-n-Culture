package com.dalhousie.server.contract;

import com.dalhousie.server.model.Amenities;

import java.util.List;

public interface IAmenitiesRepository extends ICrudRepository <Amenities, Integer> {
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId);
}
