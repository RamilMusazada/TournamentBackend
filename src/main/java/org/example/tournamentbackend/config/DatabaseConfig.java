package org.example.tournamentbackend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/tournament";
        String username = "postgres";
        String password = "1234";

        return DriverManager.getConnection(url, username, password);
    }
}
