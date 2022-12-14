package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.Amenities;

import java.util.List;

public interface IAmenityOperation extends ICrudOperation<Amenities, Integer> {
    List<Amenities> getAllAmenitiesByVenueId(Integer venueId);
}
