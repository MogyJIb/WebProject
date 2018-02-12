package by.gstu.training.models;

public class Account extends DbEntity{
    public static final int ADMIN = 1;
    public static final int USER = 2;

    private String login;
    private String password;
    private int accessLevel;

    public Account() {
    }

    public Account(long id, String login, String password, int accessLevel) {
        super(id);
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }



    public static Account getInstance(Account acc) {
        return new Account(acc.getId(),acc.getLogin(),acc.getPassword(),acc.getAccessLevel());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getRole() {
        return accessLevel==1 ? "user" : "admin";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getAccessLevel() != account.getAccessLevel()) return false;
        if (getLogin() != null ? !getLogin().equals(account.getLogin()) : account.getLogin() != null) return false;
        return getPassword() != null ? getPassword().equals(account.getPassword()) : account.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + getAccessLevel();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +super.toString()+
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
