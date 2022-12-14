package com.dalhousie.server.contract;

import java.util.List;

import com.dalhousie.server.model.Amenities;

public interface IAmenitiesRepository extends ICrudRepository <Amenities, Integer> {
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId);
}
