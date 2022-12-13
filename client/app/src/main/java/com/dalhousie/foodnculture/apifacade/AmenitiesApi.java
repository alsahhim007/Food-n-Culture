package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Amenities;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AmenitiesApi implements IAmenityOperation {

    private final IRequest request;
    private String baseUrl = "/api/amenities";

    public AmenitiesApi(IRequest<Amenities> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId) {
        Amenities[] amenities = new Amenities[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/venues/" + venueId);
            amenities = Mapper.mapFromJson(buffer.toString(), Amenities[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(amenities);
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
        try {
            StringBuffer buffer = this.request.doPut(baseUrl + "/" + object.getId(), Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Amenities object) {
        return deleteById(object.getId());
    }

    @Override
    public int deleteById(Integer id) {
        try {
            StringBuffer buffer = this.request.doDelete(baseUrl + "/" + id);
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean exists(Integer id) {
        return getById(id).isPresent();
    }

    @Override
    public Optional<Amenities> getById(Integer id) {
        Amenities amenities = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            amenities = Mapper.mapFromJson(buffer.toString(), Amenities.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(amenities);
    }
}
