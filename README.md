# README

## Overview

This repo consists of two Java files, `Main` and `Main0`, which interact with a MySQL database to perform various operations such as inserting, updating, deleting, and reading records. The project uses JDBC for database connectivity and includes batch processing and transaction management. These files are part of my learning experience while exploring JDBC.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server installed and running
- MySQL Connector/J (JDBC Driver)

## Setup

1. **Database Setup**:
    - Ensure that a MySQL database named `mydb` exists.
    - Create a table `students` with the following schema:
      ```sql
      CREATE TABLE students (
          id INT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255),
          age INT,
          marks DOUBLE
      );
      ```

2. **JDBC Configuration**:
    - Update the JDBC URL, username, and password in the `Main` and `Main0` files to match your MySQL server configuration.

## Main Class

### Functionality

- **Batch Processing**: Collects student data in a batch and inserts it into the `students` table.
- **CRUD Operations**:
    - Insert a new student record.
    - Read student records by ID.
    - Update student marks by ID.
    - Delete student records by ID.

### How to Run

1. Compile the `Main.java` file.
2. Run the `Main` class.
3. Follow the prompts to insert data or uncomment sections for specific CRUD operations.

## Main0 Class

### Functionality

- **Transaction Management**: Performs transactions to debit and credit amounts between accounts in the `accounts` table.
- **Balance Check**: Verifies sufficient funds before processing transactions.

### How to Run

1. Ensure the `accounts` table exists with the following schema:
   ```sql
   CREATE TABLE accounts (
       account_number INT PRIMARY KEY,
       balance DOUBLE
   );
   ```
2. Compile the `Main0.java` file.
3. Run the `Main0` class.
4. Follow the prompts to perform transactions between accounts.

## Notes

- Make sure the database server is running before executing the programs.
- Uncomment specific blocks in the code to execute different operations.
- Handle any exceptions that may arise due to incorrect database configurations or SQL syntax errors.

## License

This project is licensed under the MIT License.