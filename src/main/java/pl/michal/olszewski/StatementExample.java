package pl.michal.olszewski;

import java.sql.*;

public class StatementExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true",
                "username",
                "password");

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists movies(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "productionYear int, " +
                "primary key (id) )");

        /*statement.executeUpdate(
                "INSERT INTO movies(name,productionYear) " +
                        "values ('STAR WARS',1989)");

        for (int i = 1; i <= 5; i++) {
            statement.executeUpdate(
                    "INSERT INTO movies(name,productionYear) " +
                            "values ('STAR WARS"+i+"',1989)");
        }*/

       /* PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO movies(name,productionYear) values (?,?)");
        for (int i = 0; i <5 ; i++) {
            preparedStatement.setString(1,"HARRY POTTER"+i);
            preparedStatement.setInt(2,2014+i);
            preparedStatement.executeUpdate();
        }*/

        /*PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM movies where productionYear = ?");
        preparedStatement1.setInt(1,2016);
        preparedStatement1.executeUpdate();*/


        PreparedStatement preparedStatement = connection
                .prepareStatement("Select id,name,productionYear from movies");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int productionYear = resultSet.getInt("productionYear");
            System.out.println(id+" "+name+" "+productionYear);
        }


    }
}
