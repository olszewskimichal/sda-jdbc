package pl.michal.olszewski;

import java.sql.*;

public class CreatePreparedStatement {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Connection connection = getConnection();
            //createTable(connection.createStatement());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee(name,salary) values(?,?)");
            preparedStatement.setString(1, "name5");
            preparedStatement.setInt(2, 1234);
            preparedStatement.executeUpdate();
            selectValuesGetByColumnNames(connection.createStatement());
        }catch (SQLException ex){
            ex.printStackTrace();;
        }
        //dropTable(connection.createStatement());
    }
    private static void selectValuesGetByColumnNames(Statement statement) throws SQLException {
        ResultSet resultSet2 = statement.executeQuery("select name,salary from employee");
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("name"));
            System.out.println(resultSet2.getInt("salary"));
            System.err.println("--------------");
        }
    }

    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE employee(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "salary int, " +
                "primary key (id) )");
    }

    private static void dropTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE employee");
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname?serverTimezone=UTC",
                "username",
                "password");
    }
}
