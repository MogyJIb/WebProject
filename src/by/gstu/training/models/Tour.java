package by.gstu.training.models;


public class Tour extends DbEntity {
    private String startDate;
    private String endDate;
    private double price;
    private String type;
    private long userId;
    private long travelAgentId;

    public Tour() {
    }

    public Tour(long id, String startDate, String endDate, double price, String type, long userId, long travelAgentId) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.type = type;
        this.userId = userId;
        this.travelAgentId = travelAgentId;
    }


    public static Tour getInstance(Tour tr) {
        return new Tour(tr.getId(),
                tr.getStartDate(),
                tr.getEndDate(),
                tr.getPrice(),
                tr.getType(),
                tr.getUserId(),
                tr.getTravelAgentId());
    }



    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTravelAgentId() {
        return travelAgentId;
    }

    public void setTravelAgentId(long travelAgentId) {
        this.travelAgentId = travelAgentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;

        Tour tour = (Tour) o;

        if (Double.compare(tour.getPrice(), getPrice()) != 0) return false;
        if (getUserId() != tour.getUserId()) return false;
        if (getTravelAgentId() != tour.getTravelAgentId()) return false;
        if (getStartDate() != null ? !getStartDate().equals(tour.getStartDate()) : tour.getStartDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(tour.getEndDate()) : tour.getEndDate() != null) return false;
        return getType() != null ? getType().equals(tour.getType()) : tour.getType() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getStartDate() != null ? getStartDate().hashCode() : 0;
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (int) (getTravelAgentId() ^ (getTravelAgentId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", travelAgentId=" + travelAgentId +
                '}';
    }
}
