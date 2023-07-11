package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

  public static Customer login(String username, String password) throws LoginException {
    Customer customer = DataSource.getCustomer(username);
    if (customer == null) {
      throw new LoginException("Username not found");
    }
    if (password.equals(customer.getPwd())) {
      customer.setAuth(true);
      return customer;
    } else {
      throw new LoginException("Incorrect password.");
    }
  }

  public static void logout(Customer customer) {

    customer.setAuth(false);
  }

}