package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("Connected!");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;

  }

  public static Customer getCustomer(String username) {
    String sql = "Select * FROM Customers WHERE USERNAME  = ? ";
    Customer customer = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, username);
      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));

      } catch (SQLException e) {
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;

  }

  public static Account getAccount(int accountId) {
    String sql = "SELECT * FROM Accounts WHERE id = ?";
    Account account = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setInt(1, accountId);

      try (ResultSet results = statement.executeQuery()) {
        account = new Account(results.getInt("id"),
            results.getString("type"),
            results.getDouble("balance"));

      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;

  }

  // Modify balance in accounts table for specific account id
  public static void modifyAccountBalance(double amount, int accountId) {

    String sql = "Update Accounts SET balance = ? WHERE id = ?";

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setDouble(1, amount);
      statement.setInt(2, accountId);

      statement.executeUpdate(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /*
   * public static void main(String[] args) {
   * // connect();
   * String username = "lfromonte9@de.vu";
   * Customer customer = getCustomer(username);
   * 
   * System.out.println(username + " fields: ");
   * 
   * System.out.println(customer.getId());
   * System.out.println(customer.getName());
   * System.out.println(customer.getUsername());
   * System.out.println(customer.getAccountId());
   * System.out.println(customer.getPwd());
   * 
   * int accountId = customer.getAccountId();
   * 
   * Account account = getAccount(accountId);
   * 
   * System.out.println(account.getId());
   * System.out.println(account.getType());
   * System.out.println(account.getBalance());
   * 
   * }
   */
}
