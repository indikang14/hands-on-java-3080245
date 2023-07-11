package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.amountException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {

    System.out.println("Welcome to Globe Bank International!");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();

  }

  private Customer authenticateUser() {
    System.out.println("Please enter your username: ");
    String username = scanner.next();

    System.out.println("Please enter your password: ");
    String password = scanner.next();

    Customer customer = null;

    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("There was an error: " + e.getMessage());
    }

    return customer;

  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;

    while (selection != 4 && customer.isAuth()) {

      System.out.println("===============================================");
      System.out.println("Please select one of the following options: ");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Check Balance");
      System.out.println("4. Exit");
      System.out.println("===============================================");

      selection = scanner.nextInt();
      double amount = 0;

      switch (selection) {
        case 1:// Deposit
          System.out.println("How much money would you like to deposit? Please enter an amount: ");
          amount = scanner.nextDouble();
          try {
            account.deposit(amount);
          } catch (amountException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
          }
          // method does not exist yet
          // Add new amount to account table under correct customer id
          break;

        case 2: // Withdraw
          System.out.println(
              "How much money would you like to withdraw? Your upper limit is set at 10000.00 . Please enter an amount: ");
          amount = scanner.nextDouble();
          if (amount > account.getBalance()) {
            System.out.println(
                "You are attempting to withdraw more than you have in you chequing account. Please enter another amount:  ");
            amount = scanner.nextDouble();
          }
          try {
            account.withdraw(amount); // method does not have body yet
          } catch (amountException e) {
            e.getMessage();
            System.out.println("Please try again.");
          }
          break;

        case 3: // Check Balance
          System.out.println("Your account balance is: $" + account.getBalance());
          break;

        case 4: // Exit (customer logs out)
          Authenticator.logout(customer);
          break;

        default:
          System.out.println("Not a valid option. Please try again. ");
          break;
      }

    }

  }

}
