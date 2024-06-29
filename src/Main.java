import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb"; // Database URL
    private static final String userName = "root"; // Database username
    private static final String password = "admin@123"; // Database password

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, userName, password);

            // Batch Processing using PreparedStatement
            String insertQuery = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            Scanner sc = new Scanner(System.in);
            while (true) {
                // Collect data from the user
                System.out.println("Enter name: ");
                String name = sc.next();
                System.out.println("Enter age: ");
                int age = sc.nextInt();
                System.out.println("Enter Marks: ");
                double marks = sc.nextDouble();
                System.out.println("Enter more data(Y/N): ");
                String choice = sc.next();

                // Set parameters for the PreparedStatement
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);
                preparedStatement.addBatch(); // Add to batch

                // Break loop if the user does not want to enter more data
                if (choice.toUpperCase().equals("N")) {
                    break;
                }
            }

            // Execute batch
            int[] arr = preparedStatement.executeBatch();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    System.out.println("Query: " + i + " not executed successfully.");
                }
            }

            // Uncomment the following blocks for individual CRUD operations

            // CRUD Operations using PreparedStatement

            // Insert data into the database (Uncomment to enable)
            /*
            String insertQuery = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "Fatima");
            preparedStatement.setInt(2, 23);
            preparedStatement.setDouble(3, 89.9);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data not inserted.");
            }
            */

            // Retrieve values from the database (Uncomment to enable)
            /*
            String readQuery = "SELECT * from students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setInt(1, 3); // ID to retrieve
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double marks = resultSet.getDouble("marks");
                System.out.println("Marks: " + marks);
            } else {
                System.out.println("Marks not found!");
            }
            */

            // Update values in the database (Uncomment to enable)
            /*
            String updateQuery = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, 92); // New marks
            preparedStatement.setInt(2, 3); // ID to update
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Data not updated.");
            }
            */

            // Delete data from the database (Uncomment to enable)
            /*
            String deleteQuery = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, 1); // ID to delete
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Data not deleted.");
            }
            */

            // Uncomment this block for CRUD Operations using Statement
            /*
            // Read data from the database (Uncomment to enable)
            String readQuery = "SELECT * from students";
            ResultSet resultSet = statement.executeQuery(readQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double marks = resultSet.getDouble("marks");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Marks: " + marks);
            }
            */

            // Insert data into the database (Uncomment to enable)
            /*
            String insertQuery = String.format("INSERT INTO students(name, age, marks) VALUES ('%s', %d, %f)", "Ahmed", 23, 89.9);
            int affectedRows = statement.executeUpdate(insertQuery);
            if (affectedRows > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data not inserted.");
            }
            */

            // Update data in the database (Uncomment to enable)
            /*
            String updateQuery = String.format("UPDATE students SET marks = %f WHERE id = %d", 84.5, 2);
            int affectedRows = statement.executeUpdate(updateQuery);
            if (affectedRows > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Data not updated.");
            }
            */

            // Delete data from the database (Uncomment to enable)
            /*
            String deleteQuery = String.format("DELETE FROM students WHERE id = 2");
            int affectedRows = statement.executeUpdate(deleteQuery);
            if (affectedRows > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Data not deleted.");
            }
            */

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
