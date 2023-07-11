package bank;

import java.sql.SQLException;

import bank.exceptions.amountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws amountException {
    if (amount < 1) {
      throw new amountException("Minimum deposit is 1.00");
    } else {
      double newBalance = balance + amount;
      setBalance(newBalance);
      // update database
      DataSource.modifyAccountBalance(balance, id);

    }

  }

  public void withdraw(double amount) throws amountException {
    if (amount < 1 || amount > balance) {
      throw new amountException("Withdrawal amount has to be greater than 1 and less than current balance. ");
    } else {
      double newBalance = balance - amount;
      setBalance(newBalance);
      DataSource.modifyAccountBalance(balance, id);
    }

  }

}
