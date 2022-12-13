package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Donation;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DonationApi implements IDonationOperation {
    private final IRequest request;
    private String baseUrl = "/api/donations";

    public DonationApi(IRequest<Donation> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
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
    public int delete(Donation object) {
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
    public Optional<Donation> getById(Integer id) {
        Donation donation = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            donation = Mapper.mapFromJson(buffer.toString(), Donation.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(donation);
    }

    @Override
    public List<Donation> getDonationsByEventId(Integer eventId) {
        Donation[] donations = new Donation[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/events/" + eventId);
            donations = Mapper.mapFromJson(buffer.toString(), Donation[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(donations);
    }

    @Override
    public double getTotalDonationByEventId(Integer eventId) {
        double totalDonation = 0;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + eventId);
            totalDonation = Mapper.mapFromJson(buffer.toString(), Double.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalDonation;
    }
}
