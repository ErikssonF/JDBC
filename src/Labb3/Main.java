package Labb3;

import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    Connection connection;
    Statement statement;
    Scanner scan = new Scanner(System.in);
    boolean run = true;

    {
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/labb3",
                            "root",
                            "G5dj8vsa123!");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {

        Main main = new Main();

        //main.createTable();

        while (main.run) {
            main.menu();
        }
    }

    private void menu() throws SQLException {

        menuPrint();

        checkIfInputIsInteger();
        int menuChoice = scan.nextInt();

        switch (menuChoice) {
            case 1 -> add();
            case 2 -> delete();
            case 3 -> update();
            case 4 -> showAll();
            case 5 -> findById();
            case 6 -> findByAge();
            case 7 -> findByName();
            case 0 -> run = false;

        }
    }

    private void menuPrint() {
        System.out.println("Vad vill du göra?" + "\n" +
                "1. Lägga till artist" + "\n" +
                "2. Ta bort artist" + "\n" +
                "3. Updatera artist som finns i listan" + "\n" +
                "4. Visa alla artister" + "\n" +
                "5. Söka på artist via ID" + "\n" +
                "6. Söka på artist via ålder" + "\n" +
                "7. Söka på artist via namn" + "\n" +
                "0. Avsluta");
    }


    private void createTable() throws SQLException {
        int resultSet = statement.executeUpdate("CREATE TABLE Artist" +
                "(id INTEGER AUTO_INCREMENT, " +
                "first_name VARCHAR(150), " +
                "last_name VARCHAR(150), " +
                "age smallint(200), " +
                "PRIMARY KEY (id) )");

    }

    private void add() throws SQLException {

        System.out.println("Ange förnamn: ");

        checkIfInputIsString();
        String firstName = scan.nextLine();
        scan.nextLine();

        System.out.println("Ange efternamn: ");
        checkIfInputIsString();
        String lastName = scan.nextLine();

        System.out.println("Ange ålder: ");
        checkIfInputIsInteger();
        int age = scan.nextInt();

        statement.executeUpdate("INSERT INTO artist (first_name,last_name,age) VALUES ('"
                + firstName + "','"
                + lastName + "','"
                + age + "')");
    }

    private void checkIfInputIsInteger() {

        while (!scan.hasNextInt()) {
            System.out.println("Felaktig input, försök igen.");
            scan.next();

        }
    }

    private void checkIfInputIsString() {

        while(scan.hasNextInt()) {
            System.out.println("Felaktig input, försök igen");
            scan.next();
        }
    }

    private void delete() throws SQLException {

        System.out.println("Vem vill du ta bort? Ange ID.");

        checkIfInputIsInteger();
        int artistId = scan.nextInt();

        statement.executeUpdate("DELETE FROM artist WHERE id = " + artistId);
    }

    private void update() throws SQLException {

        System.out.println("Ange ID för artisten som du vill uppdatera");
        checkIfInputIsInteger();
        int id = scan.nextInt();

        printArtistLoop(id);

        System.out.println("Ange förnamn");
        checkIfInputIsString();
        scan.nextLine();
        String firstName = scan.nextLine();

        System.out.println("Ange efternamn");
        checkIfInputIsString();
        String lastName = scan.nextLine();

        System.out.println("Ange ålder");
        checkIfInputIsInteger();
        int age = scan.nextInt();

        statement.executeUpdate("UPDATE artist SET first_name = '"
                + firstName + "', last_name = '"
                + lastName + "', age = '"
                + age + "' WHERE id = "
                + id + ";");
    }

    private void printArtistLoop(int id) throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist WHERE id = " + id);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id")
                    + " " + resultSet.getString("first_name")
                    + " " + resultSet.getString("last_name")
                    + " " + resultSet.getInt("age"));
        }

    }

    private void showAll() throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist;");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id")
                    + " " + resultSet.getString("first_name")
                    + " " + resultSet.getString("last_name")
                    + " " + resultSet.getInt("age"));
        }
    }

    private void findById() throws SQLException {

        System.out.println("Ange ID: ");
        checkIfInputIsInteger();
        int id = scan.nextInt();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist WHERE id = " + id);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id")
                    + " " + resultSet.getString("first_name")
                    + " " + resultSet.getString("last_name")
                    + " " + resultSet.getInt("age"));
        }

    }

    private void findByAge() throws SQLException {

        System.out.println("Ange ålder: ");
        checkIfInputIsInteger();
        int ageInput = scan.nextInt();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist WHERE age = " + ageInput);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id")
                    + " " + resultSet.getString("first_name")
                    + " " + resultSet.getString("last_name")
                    + " " + resultSet.getInt("age"));
        }

    }

    private void findByName() throws SQLException {

        System.out.println("Ange förnamn");
        checkIfInputIsString();
        scan.nextLine();
        String firstName = scan.nextLine();

        System.out.println("Ange efternamn");
        checkIfInputIsInteger();
        String lastName = scan.nextLine();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist WHERE first_name = '"
                + firstName + "' AND last_name = '"
                + lastName + "';");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id")
                    + " " + resultSet.getString("first_name")
                    + " " + resultSet.getString("last_name")
                    + " " + resultSet.getInt("age"));
        }
    }
}
