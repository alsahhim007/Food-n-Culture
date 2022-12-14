package com.dalhousie.server.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBConnection implements IConnection {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public <T> List<T> executeProcedure(String procedure, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(procedure, rowMapper);
    }

    @Override
    public int executeProcedure(String procedure, Object... args) {
        return jdbcTemplate.update(procedure, args);
    }

    @Override
    public <T> T executeProcedureForObject(String procedure, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.queryForObject(procedure, rowMapper, args);
    }

    @Override
    public <T> List<T> executeProcedure(String procedure, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.query(procedure, rowMapper, args);
    }

    @Override
    public Double executeProcedureForDoubleObject(String procedure, Object... args) {
        return jdbcTemplate.queryForObject(procedure, Double.class, args);
    }
}
