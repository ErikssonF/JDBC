package Labb3;

import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class Main {

    private Statement statement;
    private final Scanner scan = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private int age;
    private int artistId;
    private ResultSet resultSet;
    boolean run = true;

    {
        try {
            Connection connection = DriverManager
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

        int menuChoice = checkIfInputIsInteger(" ");

        switch (menuChoice) {
            case 1 -> add();
            case 2 -> delete();
            case 3 -> update();
            case 4 -> showAll();
            case 5 -> findById();
            case 6 -> findByAge();
            case 7 -> findByName();
            case 0 -> run = false;
            default -> System.out.println("Felaktig input, försök igen");
        }
    }

    private void menuPrint() {

        System.out.println(
                """
                        Vad vill du göra?
                        
                        1. Lägga till artist
                        2. Ta bort artist
                        3. Updatera artist som finns i listan
                        4. Visa alla artister
                        5. Söka på artist via ID
                        6. Söka på artist via ålder
                        7. Söka på artist via namn
                        0. Avsluta""");
    }


    private void createTable() throws SQLException {
        int resultSet = statement.executeUpdate("CREATE TABLE Artist" +
                "(id INTEGER AUTO_INCREMENT, " +
                "first_name VARCHAR(150), " +
                "last_name VARCHAR(150), " +
                "age smallint(200), " +
                "PRIMARY KEY (id) )");
    }

    private void artistInfoInput() {
        firstName = checkIfInputIsString("Ange förnamn ");
        lastName = checkIfInputIsString("Ange efternamn ");
        age = checkIfInputIsInteger("Ange Ålder ");
    }

    private int checkIfInputIsInteger(String input) {
        System.out.println(input);
        while (!scan.hasNextInt()) {
            System.out.println("Felaktig input, försök igen");
            scan.next();
        }
        return Integer.parseInt(scan.next());
    }

    private String checkIfInputIsString(String input) {
        System.out.println(input);
        while (scan.hasNextInt()) {
            System.out.println("Felaktig input, försök igen");
            scan.next();
        }
        return scan.next();
    }

    private void add() throws SQLException {

        artistInfoInput();
        statement.executeUpdate("INSERT INTO artist (first_name,last_name,age) VALUES ('"
                + firstName + "','"
                + lastName + "','"
                + age + "')");
    }

    private void delete() throws SQLException {

        artistId = checkIfInputIsInteger("Ange ID som du vill ta bort");
        artistInfoInput();
        statement.executeUpdate("DELETE FROM artist WHERE id = " + artistId);
    }

    private void update() throws SQLException {

        artistId = checkIfInputIsInteger("Ange ID som du vill ändra");
        artistInfoInput();
        statement.executeUpdate("UPDATE artist SET first_name = '"
                + firstName + "', last_name = '"
                + lastName + "', age = '"
                + age + "' WHERE id = "
                + artistId + ";");
    }

    private void printArtistLoop() throws SQLException {

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id")
                    + " " + resultSet.getString("first_name")
                    + " " + resultSet.getString("last_name")
                    + " " + resultSet.getInt("age"));
        }

    }

    private void showAll() throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM artist;");
        printArtistLoop();
    }

    private void findById() throws SQLException {

        artistId = checkIfInputIsInteger("Ange ID som du vill ändra");

        resultSet = statement.executeQuery("SELECT * FROM artist WHERE id = " + artistId);

        printArtistLoop();

    }

    private void findByAge() throws SQLException {

        age = checkIfInputIsInteger("Ange ålder som du vill söka på");

        resultSet = statement.executeQuery("SELECT * FROM artist WHERE age = " + age);

        printArtistLoop();

    }

    private void findByName() throws SQLException {

        firstName = checkIfInputIsString("Ange förnamn");

        lastName = checkIfInputIsString("Ange efternamn");

        resultSet = statement.executeQuery("SELECT * FROM artist WHERE first_name name = '"
                + firstName + "' AND last_name = '"
                + lastName + "';");

        printArtistLoop();
    }
}
