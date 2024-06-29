import java.sql.*;
import java.util.Scanner;

public class Main0 {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/khata";
    private static final String userName = "root";
    private static final String password = "admin@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            connection.setAutoCommit(false);

            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement debitPreparedStatement = connection.prepareStatement(debitQuery);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery);
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter account number to debit: ");
            int debitAccountNumber = sc.nextInt();
            System.out.print("Enter account number to credit: ");
            int creditAccountNumber = sc.nextInt();
            System.out.print("Enter amount to transfer: ");
            double amount = sc.nextDouble();

            if (isSufficient(connection, debitAccountNumber, amount)) {
                debitPreparedStatement.setDouble(1, amount);
                debitPreparedStatement.setInt(2, debitAccountNumber);
                creditPreparedStatement.setDouble(1, amount);
                creditPreparedStatement.setInt(2, creditAccountNumber);
                debitPreparedStatement.executeUpdate();
                creditPreparedStatement.executeUpdate();

                connection.commit();
                System.out.println("Transaction completed successfully.");
            } else {
                connection.rollback();
                System.out.println("Transaction failed: insufficient funds.");
            }

            creditPreparedStatement.close();
            debitPreparedStatement.close();
            sc.close();
            connection.close();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isSufficient(Connection connection, int accountNumber, double amount) {
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double currentBalance = resultSet.getDouble("balance");
                return amount <= currentBalance;
            }


            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
