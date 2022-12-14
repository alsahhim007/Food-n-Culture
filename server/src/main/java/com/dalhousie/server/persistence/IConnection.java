package com.dalhousie.server.persistence;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface IConnection {
    public <T> List<T> executeProcedure(String procedure, RowMapper<T> rowMapper);
    public <T> List<T> executeProcedure(String procedure, RowMapper<T> rowMapper, Object... args);
    public <T> T executeProcedureForObject(String procedure, RowMapper<T> rowMapper, Object... args);
    public Double executeProcedureForDoubleObject(String procedure, Object... args);
    public int executeProcedure(String procedure, Object... args);
}