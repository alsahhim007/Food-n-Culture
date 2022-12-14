package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Messages;

@Component
public class MessagesRepository implements IMessagesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Messages> findAll() {
        return jdbcTemplate.query("CALL getAllMessages", BeanPropertyRowMapper.newInstance(Messages.class));
    }

    @Override
    public int save(Messages msg) {
        return jdbcTemplate.update(
                "CALL createMessage(?, ?, ?, ?, ?)",
                msg.getId(), msg.getUserId(), msg.getContent(), msg.isRead(), msg.getTargetUserId());
    }

    @Override
    public int update(Messages msg) {
        return jdbcTemplate.update(
                "CALL updateMessage(?, ?, ?, ?, ?)",
                msg.getId(), msg.getUserId(), msg.getContent(), msg.isRead(), msg.getTargetUserId());
    }

    @Override
    public int delete(Messages msg) {
        return jdbcTemplate.update("CALL deleteMessageById(?)", msg.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteMessageById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Messages> getById(Integer id) {
        try {
            Messages msg = jdbcTemplate.queryForObject("CALL getMessageById(?)", BeanPropertyRowMapper.newInstance(Messages.class), id);
            return Optional.of(msg);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2) {
        return jdbcTemplate.query("CALL getAllMessagesBetweenUsers(?, ?)", BeanPropertyRowMapper.newInstance(Messages.class), user1, user2);
    }
    
}
