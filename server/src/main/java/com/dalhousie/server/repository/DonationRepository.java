package com.dalhousie.server.repository;

import com.dalhousie.server.contract.IDonationRepository;
import com.dalhousie.server.model.Donation;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.DonationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DonationRepository implements IDonationRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<Donation> findAll() {
        return dbConnection.executeProcedure("CALL getAllDonations()", new DonationRowMapper());
    }

    @Override
    public int save(Donation donation) {
        return dbConnection.executeProcedure(
                "CALL createDonation(?, ?, ?, ?, ?, ?)",
                donation.getId(), donation.getEventId(), donation.getName(),
                donation.getAmount(), donation.getEmail(), donation.getNote());
    }

    @Override
    public int update(Donation donation) {
        return dbConnection.executeProcedure(
                "CALL updateDonation(?, ?, ?, ?, ?, ?)",
                donation.getEventId(), donation.getName(), donation.getAmount(), donation.getEmail(),
                donation.getNote(), donation.getId());
    }

    @Override
    public int delete(Donation donation) {
        return dbConnection.executeProcedure("CALL deleteDonation(?)", donation.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteDonation(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Donation> getById(Integer id) {
        try {
            Donation donation = dbConnection.executeProcedureForObject("CALL getDonationById(?)", new DonationRowMapper(), id);
            return Optional.of(donation);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Donation> getDonationsByEventId(Integer eventId) {
        return dbConnection.executeProcedure("CALL getDonationsByEventId(?)", new DonationRowMapper(), eventId);
    }

    @Override
    public double getTotalDonationByEventId(Integer eventId) {
        return dbConnection.executeProcedureForDoubleObject("CALL getTotalDonationByEventId(?)", eventId);
    }

}
