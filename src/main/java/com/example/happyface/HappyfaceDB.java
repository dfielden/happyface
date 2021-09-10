package com.example.happyface;

import java.sql.*;

public final class HappyfaceDB {
    private final Connection connect;

    public HappyfaceDB() throws Exception {
        connect = DriverManager.getConnection("jdbc:sqlite:happyface.db", "root", "");

        String initiateTable = "CREATE TABLE IF NOT EXISTS Messages (" +
                    "id INTEGER PRIMARY KEY NOT NULL, " +
                    "name TEXT, " +
                    "email TEXT, " +
                    "date INTEGER, " +
                    "subject TEXT, " +
                    "message TEXT)";
        connect.createStatement().execute(initiateTable);
    }

    public synchronized long addMessage(Message message) throws Exception {
        String query = "INSERT INTO Messages (name, email, date, subject, message) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, message.getName());
            stmt.setString(2, message.getEmail());
            stmt.setLong(3, System.currentTimeMillis());
            stmt.setString(4, message.getSubject());
            stmt.setString(5, message.getMessage());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new IllegalStateException("Unable to get generated key for added Exercise");
            }
            return rs.getLong(1);
        }
    }
}


