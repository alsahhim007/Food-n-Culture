package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Amenities;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AmenitiesApi implements IAmenityOperation {

    private final IRequest request;
    private final String baseUrl = "http://localhost:8080/api/amenities"; // TODO? find better place for me

    public AmenitiesApi(IRequest<Amenities> request) {
        this.request = request;
    }

    @Override
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId) {
        return null;
    }

    @Override
    public List<Amenities> findAll() {
        Amenities[] amenityList = new Amenities[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            amenityList = Mapper.mapFromJson(buffer.toString(), Amenities[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(amenityList);
    }

    @Override
    public int save(Amenities object) {
        try {
            StringBuffer buffer = this.request.doPost(baseUrl + "/", Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Amenities object) {
        return 0;
    }

    @Override
    public int delete(Amenities object) {
        return 0;
    }

    @Override
    public int deleteById(Integer integer) {
        return 0;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Optional<Amenities> getById(Integer integer) {
        return null;
    }
}
