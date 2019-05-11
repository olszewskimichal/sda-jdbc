package pl.michal.olszewski;

import java.sql.*;

public class CreateStatement {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        createTable(connection.createStatement());

        insertSomeValuesToTable(connection.createStatement());














    }

    private static void closeConnections(Connection connection, Statement statement) throws SQLException {
        statement.close();
        connection.close();
    }

    private static void dropTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE employee");
    }

    private static void selectValuesGetByColumnIndex(Statement statement) throws SQLException {
        ResultSet resultSet = statement
                .executeQuery("select name,salary from employee");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getInt(2));
            System.err.println("--------------");
        }
    }

    private static void selectValuesGetByColumnNames(Statement statement) throws SQLException {
        ResultSet resultSet2 = statement
                .executeQuery("select name,salary from employee");
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("name"));
            System.out.println(resultSet2.getInt("salary"));
            System.err.println("--------------");
        }
    }

    private static void insertSomeValuesToTable(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO employee(name,salary) values('name',2000)");
        statement.executeUpdate("INSERT INTO employee(name,salary) values('name1',2000)");
    }

    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE employee(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "salary int, " +
                "primary key (id) )");
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname?serverTimezone=UTC",
                "username",
                "password");
    }
}
