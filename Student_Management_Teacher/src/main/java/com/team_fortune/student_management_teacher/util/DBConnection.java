package com.team_fortune.student_management_teacher.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    private static String url = "jdbc:mysql://localhost:3306/student_management";
    private static String user = "root";
    private static String password = "Anhthang61@";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new SQLException("MySQL JDBC driver not found.", ex);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to database.", e);
        }
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
