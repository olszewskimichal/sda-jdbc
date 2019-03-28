package pl.michal.olszewski;


import java.sql.*;
import java.util.Date;

public class DateTimeInJDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        createTable(statement);

        insertSomeValuesToTable(connection);

        selectValuesGetByColumnNames(statement);

        dropTable(statement);

        closeConnections(connection, statement);
    }

    private static void closeConnections(Connection connection, Statement statement) throws SQLException {
        statement.close();
        connection.close();
    }

    private static void dropTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE employee2");
    }

    private static void selectValuesGetByColumnNames(Statement statement) throws SQLException {
        ResultSet resultSet2 = statement.executeQuery("select name,salary,employee_date from employee2");
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("name"));
            System.out.println(resultSet2.getInt("salary"));
            System.out.println(new Date(resultSet2.getDate("employee_date").getTime()));
            System.err.println("--------------");
        }
    }

    private static void insertSomeValuesToTable(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee2(name,salary,employee_date) values(?,?,?)");
        preparedStatement.setString(1, "name5");
        preparedStatement.setInt(2, 1234);
        preparedStatement.setDate(3,new java.sql.Date(new Date().getTime()));
        preparedStatement.executeUpdate();
    }

    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE employee2(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "employee_date DATETIME ,"+
                "salary int, " +
                "primary key (id) )");
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname",
                "username",
                "password");
    }
}
