package by.gstu.training.models;

public class Agency extends DbEntity {
    private String title;
    private String phoneNumber;

    public Agency() {
    }

    public Agency(long id, String title, String phoneNumber) {
        super(id);
        this.title = title;
        this.phoneNumber = phoneNumber;
    }


    public static Agency getInstance(Agency ag) {
        return new Agency(ag.getId(),ag.getTitle(),ag.getPhoneNumber());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agency)) return false;

        Agency agency = (Agency) o;

        if (getTitle() != null ? !getTitle().equals(agency.getTitle()) : agency.getTitle() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(agency.getPhoneNumber()) : agency.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "title='" + title + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
