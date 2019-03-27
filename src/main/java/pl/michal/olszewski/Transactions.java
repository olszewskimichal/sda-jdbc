package pl.michal.olszewski;

import java.sql.*;

public class Transactions {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO employee(name,salary) values('name12',2000)");

        //connection.rollback();
        closeConnections(connection, statement);
    }

    private static void closeConnections(Connection connection, Statement statement) throws SQLException {
        statement.close();
        connection.close();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname",
                "username",
                "password");
    }
}
