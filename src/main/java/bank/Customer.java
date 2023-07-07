package bank;

public class Customer {
  private int id;
  private String name;
  private String username;
  private String pwd;
  private int accountId;
  private boolean auth;

  public Customer(int id, String name, String username, String pwd, int accountId) {
    setId(id);
    setName(name);
    setUsername(username);
    setPwd(pwd);
    setAccountId(accountId);
    setAuth(false);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPwd() {
    return this.pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean isAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }

}
