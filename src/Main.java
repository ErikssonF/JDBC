import com.mysql.cj.protocol.Resultset;
import com.sun.jdi.connect.Connector;

import java.sql.*;
import java.util.Scanner;

class Test {
    Connection connection;
    Statement statement;
    PreparedStatement pStatement;
    Scanner scan = new Scanner(System.in);

    {
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sakila",
                            "root",
                            "G5dj8vsa123!");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Test test = new Test();

//        String lmao = test.scan.nextLine();
//
//        test.uppgift1(lmao);

        String firstname = "Albin";
        String lastname = "Dubëch";

        test.uppgift6();

    }

    public void uppgift1(String x) throws SQLException {
//        pStatement = connection.prepareStatement("SELECT first_name, last_name FROM actor LIMIT " + x);
        ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name FROM actor LIMIT " + x);
        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }

    }

    public void uppgift2() throws SQLException{

        System.out.println("ange förnamn");
        String firstName = scan.nextLine();
        System.out.println("ange efternamn");
        String lastName = scan.nextLine();

        statement.executeUpdate("INSERT INTO actor(first_name,last_name) VALUES ('"+firstName+"','"+lastName+"')");

    }

    public void uppgift3(String firstname, String lastname) throws SQLException{

        statement.executeUpdate("UPDATE actor SET first_name = '"+firstname+"', last_name = '"+lastname+"' WHERE actor_id = 202;");
        statement.executeUpdate("UPDATE actor SET first_name = '" + firstname + "', last_name = '" + lastname + "' WHERE actor_id = 202");
    }

    public void uppgift4() throws SQLException{

        ResultSet resultSet = statement.executeQuery("SELECT title, length FROM film ORDER BY length DESC");
        while(resultSet.next()){
            System.out.println(resultSet.getString("title") + " " + resultSet.getString("length"));
        }

    }

    public void uppgift5() throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT COUNT(film_id), length FROM film WHERE length BETWEEN 60 AND 65");
            while (resultSet.next())
                System.out.println(resultSet.getString("COUNT(film_id)"));
        }

    public void uppgift6() throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT COUNT(film_id), length FROM film WHERE length BETWEEN 60 AND 65");
        while (resultSet.next())
            System.out.println(resultSet.getString("COUNT(film_id)"));
    }


}
