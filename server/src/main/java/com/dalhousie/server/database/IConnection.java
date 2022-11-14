package com.dalhousie.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IConnection {
    public void connect();
    public ResultSet executeQuery(String query);
    public void executeUpdate(String query);
    public void executeStatement(PreparedStatement statement);
    public ResultSet executeProcedure(String procedureName, String operation, String inputJsonData);
    public Connection getConnection();
    public void disconnect();
}
