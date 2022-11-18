package com.dalhousie.server.businesslogic;

import java.util.List;

import com.dalhousie.server.model.Amenities;

public interface IAmenitiesRepository extends ICrudRepository <Amenities, Integer> {
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId);
}
