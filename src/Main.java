import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String userName = "root";
    private static final String password = "admin@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
          //  Statement statement = connection.createStatement();  //for statement operations



//Batch Processing using "PreparedStatement":

            String insertQuerry = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuerry);
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.println("Enter name: ");
                String name = sc.next();
                System.out.println("Enter age: ");
                int age = sc.nextInt();
                System.out.println("Enter Marks: ");
                double marks = sc.nextDouble();
                System.out.println("Enter more data(Y/N): ");
                String choice = sc.next();
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);
                preparedStatement.addBatch();

                if (choice.toUpperCase().equals("N")){
                    break;
                }

            }

            int arr[] = preparedStatement.executeBatch();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0){
                    System.out.println("Querry: "+i+" not executed successfully.");
                }
            }


//CRUD Operations using "PreparedStatement":

            //this block will insert data to the database

            /*

            String insertQuerry = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuerry);
            preparedStatement.setString(1, "Fatima");
            preparedStatement.setInt(2,23);
            preparedStatement.setDouble(3,89.9);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data not inserted.");
            }

             */



            //this block will retrieve values from database:


            /*
            String readQuery = "SELECT * from students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setInt(1,3); //here index = 1, id = 3

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                double marks = resultSet.getDouble("marks");
                System.out.println("Marks: "+marks);
            }
            else {
                System.out.println("Marks not found!");
            }

             */



            //this block will update the values in database:


            /*

            String updateQuery = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1,92);//here index = 1, marks = 92
            preparedStatement.setInt(2,3); //here index = 2, id = 3
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Data not updated.");
            }

             */



            //this block will delete the data from database:

            /*

            String deleteQuery = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,1); //here index = 1, id = 1
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Data not deleted.");
            }

             */




//CRUD Operations Using "Statement":

      //{
            // Uncomment this block to read data from the database

            /*
            // String readQuery = "SELECT * from students";   // Read

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



            // Uncomment this block to insert data to the database

            /*

            String insertQuery = String.format("INSERT INTO students(name, age, marks) VALUES ('%s', %d, %f)", "Ahmed", 23, 89.9);
            int affectedRows = statement.executeUpdate(insertQuerry);
            if (affectedRows > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data not inserted.");
            }

            */


            // Uncomment this block to update data from the database

            /*
            String updateQuerry = String.format("UPDATE students SET marks = %f WHERE id = %d", 84.5, 2);
            int affectedRows = statement.executeUpdate(updateQuerry);
            if (affectedRows > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Data not updated.");
            }

             */



            // Uncomment this block to delete data from the database


            /*

            String deleteQuerry = String.format("DELETE FROM students WHERE id = 2");
            int affectedRows = statement.executeUpdate(deleteQuerry);
            if (affectedRows > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Data not deleted.");
            }

            */


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //}
    }
}
