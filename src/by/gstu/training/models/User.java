package by.gstu.training.models;

public class User extends DbEntity{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int passportNumber;
    private String passportSeries;
    private long accountId;


    public User() {
    }

    public User(long id, String firstName, String lastName, String phoneNumber, int passportNumber, String passportSeries, long accountId) {
       super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
        this.accountId = accountId;
    }


    public User getInstance(User us) {
        return new User(us.getId(),
                us.getFirstName(),
                us.getLastName(),
                us.getPhoneNumber(),
                us.getPassportNumber(),
                us.getPassportSeries(), us.getAccountId());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getPassportNumber() != user.getPassportNumber()) return false;
        if (getAccountId() != user.getAccountId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() != null)
            return false;
        return getPassportSeries() != null ? getPassportSeries().equals(user.getPassportSeries()) : user.getPassportSeries() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + getPassportNumber();
        result = 31 * result + (getPassportSeries() != null ? getPassportSeries().hashCode() : 0);
        result = 31 * result + (int) (getAccountId() ^ (getAccountId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber=" + passportNumber +
                ", passportSeries='" + passportSeries + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
