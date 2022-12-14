package com.dalhousie.foodnculture.apifacade.api;

import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.apifacade.contract.IVenueOperation;
import com.dalhousie.foodnculture.models.Venues;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VenuesApi implements IVenueOperation {
    private final IRequest<Venues> request;
    private String baseUrl = "/api/venues";

    public VenuesApi(IRequest<Venues> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<Venues> findAll() {
        Venues[] venues = new Venues[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            venues = Mapper.mapFromJson(buffer.toString(), Venues[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(venues);
    }

    @Override
    public int save(Venues object) {
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
    public int update(Venues object) {

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
    public int delete(Venues object) {
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
    public Optional<Venues> getById(Integer id) {
        Venues venue = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            venue = Mapper.mapFromJson(buffer.toString(), Venues.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(venue);
    }

    @Override
    public List<Venues> getVenuesByUserId(Integer userId) {
        Venues[] venues = new Venues[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/users/" + userId);
            venues = Mapper.mapFromJson(buffer.toString(), Venues[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(venues);
    }
}
