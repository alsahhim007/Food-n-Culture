package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Donation;

@Component
public class DonationRepository implements IDonationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Donation> findAll() {
        return jdbcTemplate.query("SELECT * FROM donations", BeanPropertyRowMapper.newInstance(Donation.class));
    }

    @Override
    public int save(Donation donation) {
        return jdbcTemplate.update(
                "INSERT INTO donations(id, event_id, name, amount, email, note, updated_at, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                null, donation.getEventId(), donation.getName(), donation.getAmount(), donation.getEmail(),
                donation.getDescription(), donation.getUpdatedAt(), donation.getCreatedAt());
    }

    @Override
    public int update(Donation donation) {
        return jdbcTemplate.update(
                "UPDATE donations SET event_id=?, name=?, amount=?, email=?, note=?, updated_at=?, created_at=?) WHERE id=?",
                donation.getEventId(), donation.getName(), donation.getAmount(), donation.getEmail(),
                donation.getDescription(), donation.getUpdatedAt(), donation.getCreatedAt(), donation.getId());
    }

    @Override
    public int delete(Donation donation) {
        return jdbcTemplate.update("DELETE FROM donations WHERE id=?", donation.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM donations WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Donation> getById(Integer id) {
        try {
            Donation donation = jdbcTemplate.queryForObject("SELECT * from donations WHERE id=?", BeanPropertyRowMapper.newInstance(Donation.class), id);
            return Optional.of(donation);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Donation> getDonationsByEventId(Integer eventId) {
        return jdbcTemplate.query("SELECT * FROM donations WHERE id=?", BeanPropertyRowMapper.newInstance(Donation.class), eventId);
    }

}
