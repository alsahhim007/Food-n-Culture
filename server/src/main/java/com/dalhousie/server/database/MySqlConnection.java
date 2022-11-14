package com.dalhousie.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class MySqlConnection implements IConnection {

    @Value("${datasource.url}")
    private String connectionUrl;

    @Value("${datasource.username}")
    private String userName;

    @Value("${datasource.password}")
    private String password;

    private Connection connection = null;

    @Override
    public void connect() {
        try {
            this.connection = DriverManager.getConnection(this.connectionUrl, this.userName, this.password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet executeProcedure(String procedureName, String operation, String inputJsonData) {
        ResultSet resultSet = null;
        try {
            PreparedStatement pstmt = this.connection.prepareStatement(String.format("{ call %s(?, ?) }", procedureName));
            pstmt.setString(1, operation);
            pstmt.setObject(2, inputJsonData);
            resultSet = pstmt.executeQuery();
            pstmt.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = this.connection.createStatement().executeQuery(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public void executeUpdate(String query) {
        try {
            this.connection.createStatement().executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void executeStatement(PreparedStatement statement) {
        try {
            statement.executeQuery();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
	public Connection getConnection() {
		return this.connection;
	}

}
