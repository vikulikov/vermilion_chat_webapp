package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() throws SQLException {
        StringBuilder url = new StringBuilder();
        url
                .append("jdbc:mysql://")
                .append("localhost:")
                .append("3306/")
                .append("vermilion?")
                .append("autoReconnect=true&")
                .append("useSSL=false&")
                .append("useUnicode=true&")
                .append("characterEncoding=utf-8&")
                .append("useJDBCCompliantTimezoneShift=true&")
                .append("useLegacyDatetimeCode=false&")
                .append("serverTimezone=UTC");
        return DriverManager.getConnection(url.toString(),
                "root", "root");
    }

    static void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
