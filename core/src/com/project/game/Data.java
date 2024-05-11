package com.project.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Data {
    private final String url;
    private final String username;
    private final String password;
    private final String sql;


    public Data() {
        url = "jdbc:mysql://localhost:3306/game";
        username = "root";
        password = "";
        sql = "INSERT INTO players (name) VALUES (?)";
    }

    public void postData(String player) {
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, player);
            preparedStatement.executeUpdate();
            System.out.println("Data post success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
