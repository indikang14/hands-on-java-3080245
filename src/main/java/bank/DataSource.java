package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        System.out.println(username + " fields: ");

        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getUsername());
        System.out.println(customer.getAccountId());
        System.out.println(customer.getPwd());

      } catch (SQLException e) {
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;

  }

  public static void main(String[] args) {
    // connect();
    String username = "lfromonte9@de.vu";
    getCustomer(username);

  }
}
