package pl.michal.olszewski;

import java.sql.*;

public class SqlInjectionFixed {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String name="name5";
        //String name = "1' or '1'='1";
        PreparedStatement preparedStatement = connection
                .prepareStatement("select name,salary from employee where name =?");
        preparedStatement
                .setString(1, name);
        ResultSet resultSet2 = preparedStatement.executeQuery();
        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("name"));
            System.out.println(resultSet2.getInt("salary"));
            System.err.println("--------------");
        }

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
