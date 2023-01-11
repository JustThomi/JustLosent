package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {

    public static void readData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from scores");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getInt(3));
            }

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeData(String user, int score) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "");

            Statement statement = connection.createStatement();

            statement
                    .executeUpdate("INSERT INTO `scores`(`user`, `highscore`) VALUES ('" + user + "','" + score + "')");

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}