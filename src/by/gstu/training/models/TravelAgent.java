package by.gstu.training.models;


public class TravelAgent extends DbEntity{

    private String name;
    private double salary;
    private long agencyId;
    private long accountId;

    public TravelAgent() {
    }

    public TravelAgent(long id, String name, long agencyId, long accountId, double salary) {
        super(id);
        this.name = name;
        this.salary = salary;
        this.agencyId = agencyId;
        this.accountId = accountId;
    }


    public static TravelAgent getInstance(TravelAgent trag) {
        return new TravelAgent(trag.getId(),
                trag.getName(),
               trag.getAgencyId(),
                trag.getAccountId(),
                trag.getSalary());
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelAgent)) return false;

        TravelAgent that = (TravelAgent) o;

        if (Double.compare(that.getSalary(), getSalary()) != 0) return false;
        if (getAgencyId() != that.getAgencyId()) return false;
        if (getAccountId() != that.getAccountId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getSalary());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (getAgencyId() ^ (getAgencyId() >>> 32));
        result = 31 * result + (int) (getAccountId() ^ (getAccountId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TravelAgent{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", agencyId=" + agencyId +
                ", accountId=" + accountId +
                '}';
    }
}
