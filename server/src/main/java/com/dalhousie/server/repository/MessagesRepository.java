package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IMessagesRepository;
import com.dalhousie.server.model.Messages;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.MessageRowMapper;

@Component
public class MessagesRepository implements IMessagesRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<Messages> findAll() {
        return dbConnection.executeProcedure("CALL getAllMessages", new MessageRowMapper());
    }

    @Override
    public int save(Messages msg) {
        return dbConnection.executeProcedure(
                "CALL createMessage(?, ?, ?, ?, ?)",
                msg.getId(), msg.getUserId(), msg.getContent(), msg.isRead(), msg.getTargetUserId());
    }

    @Override
    public int update(Messages msg) {
        return dbConnection.executeProcedure(
                "CALL updateMessage(?, ?, ?, ?, ?)",
                msg.getId(), msg.getUserId(), msg.getContent(), msg.isRead(), msg.getTargetUserId());
    }

    @Override
    public int delete(Messages msg) {
        return dbConnection.executeProcedure("CALL deleteMessageById(?)", msg.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteMessageById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Messages> getById(Integer id) {
        try {
            Messages msg = dbConnection.executeProcedureForObject("CALL getMessageById(?)", new MessageRowMapper(), id);
            return Optional.of(msg);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2) {
        return dbConnection.executeProcedure("CALL getAllMessagesBetweenUsers(?, ?)", new MessageRowMapper(), user1, user2);
    }
    
}
