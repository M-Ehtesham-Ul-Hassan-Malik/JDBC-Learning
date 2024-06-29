import java.sql.*;
import java.util.Scanner;

public class Main0 {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/khata";
    private static final String userName = "root";
    private static final String password = "admin@123";

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            // Disable auto-commit for transaction management
            connection.setAutoCommit(false);

            // Prepare SQL queries for debit and credit operations
            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement debitPreparedStatement = connection.prepareStatement(debitQuery);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery);
            Scanner sc = new Scanner(System.in);

            // Get user input for account numbers and transfer amount
            System.out.print("Enter account number to debit: ");
            int debitAccountNumber = sc.nextInt();
            System.out.print("Enter account number to credit: ");
            int creditAccountNumber = sc.nextInt();
            System.out.print("Enter amount to transfer: ");
            double amount = sc.nextDouble();

            // Check if the debit account has sufficient funds
            if (isSufficient(connection, debitAccountNumber, amount)) {
                // Set parameters for debit and credit operations
                debitPreparedStatement.setDouble(1, amount);
                debitPreparedStatement.setInt(2, debitAccountNumber);
                creditPreparedStatement.setDouble(1, amount);
                creditPreparedStatement.setInt(2, creditAccountNumber);

                // Execute the updates
                debitPreparedStatement.executeUpdate();
                creditPreparedStatement.executeUpdate();

                // Commit the transaction if successful
                connection.commit();
                System.out.println("Transaction completed successfully.");
            } else {
                // Rollback the transaction if funds are insufficient
                connection.rollback();
                System.out.println("Transaction failed: insufficient funds.");
            }

            // Close resources
            creditPreparedStatement.close();
            debitPreparedStatement.close();
            sc.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to check if the account has sufficient balance
    static boolean isSufficient(Connection connection, int accountNumber, double amount) {
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double currentBalance = resultSet.getDouble("balance");
                return amount <= currentBalance;  // Return true if balance is sufficient
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;  // Return false if balance is insufficient or error occurs
    }
}