package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Amenities;

import java.util.List;

public interface IAmenityOperation extends ICrudOperation<Amenities, Integer> {
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId);
}
