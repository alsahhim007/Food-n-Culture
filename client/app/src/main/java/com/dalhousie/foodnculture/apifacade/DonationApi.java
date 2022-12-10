package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Donation;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DonationApi implements IDonationOperation {
    private final IRequest request;
    private final String baseUrl = "http://localhost:8080/api/donations"; // TODO? find better place for me

    public DonationApi(IRequest<Donation> request) {
        this.request = request;
    }

    @Override
    public List<Donation> findAll() {
        Donation[] donations = new Donation[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            donations = Mapper.mapFromJson(buffer.toString(), Donation[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(donations);
    }

    @Override
    public int save(Donation object) {
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
    public int update(Donation object) {
        return 0;
    }

    @Override
    public int delete(Donation object) {
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
    public Optional<Donation> getById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Donation> getDonationsByEventId(Integer eventId) {
        return null;
    }
}
