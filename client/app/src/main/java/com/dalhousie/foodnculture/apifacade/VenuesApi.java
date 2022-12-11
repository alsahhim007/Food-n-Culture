package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Venues;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VenuesApi implements IVenueOperation {
    private final IRequest request;
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
        return 0;
    }

    @Override
    public int delete(Venues object) {
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
    public Optional<Venues> getById(Integer integer) {
        return null;
    }

    @Override
    public List<Venues> getVenuesByUserId(Integer userId) {
        return null;
    }
}
