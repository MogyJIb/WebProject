package by.gstu.training.utils;

import by.gstu.training.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.gstu.training.logers.MyLoger.LOGER;

public class DbDataConverter {

    public static User getUserFromRS(ResultSet rs) {
        if(rs==null) {
            LOGER.error("ResultSet is null (method: getUserFromRS).");
            return null;
        }
        try {

            long  id= rs.getLong("UserId");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String phoneNumber = rs.getString("PhoneNumber");
            int passportNumber = rs.getInt("PassportNumber");
            String passportSeries = rs.getString("PassportSeries");
            long accountId = rs.getLong("AccountId");

            return  new User(id,firstName,lastName,phoneNumber,passportNumber,passportSeries, accountId);
        } catch (SQLException e) {
            LOGER.error("Can't get data of User from column of ResultSet." +
                    " \n Exception message: " + e.getMessage());
            return null;
        }
    }

    public static void setUserToPS(PreparedStatement ps, User user) {
        if(ps==null) {
            LOGER.error("PreparedStatement is null.(method: setUserToPS).");
            return;
        }
        try {
            ps.setLong(1,user.getId());
            ps.setString(2,user.getFirstName());
            ps.setString(3,user.getLastName());
            ps.setString(4,user.getPhoneNumber());
            ps.setInt(5,user.getPassportNumber());
            ps.setString(6,user.getPassportSeries());
            ps.setLong(7,user.getAccountId());
        } catch (SQLException e) {
            LOGER.error("Can't set data of User to PreparedStatement." +
                    " \n Exception message: " + e.getMessage());
        }
    }

    public static Account getAccountFromRS(ResultSet rs) {
        if(rs==null) {
            LOGER.error("ResultSet is null (method: getAccountFromRS).");
            return null;
        }
        try {
            long  id= rs.getLong("AccountId");
            String login = rs.getString("Login");
            String password = rs.getString("Password");
            int accessLevel = rs.getInt("AccessLevel");

            return  new Account(id,login,password,accessLevel);
        } catch (SQLException e) {
            LOGER.error("Can't get data of Account from column of ResultSet. \n Exception message: " + e.getMessage());
            return null;
        }
    }

    public static void setAccountToPS(PreparedStatement ps, Account account) {
        if(ps==null) {
            LOGER.error("PreparedStatement is null.(method: setAccountToPS).");
            return;
        }
        try {
            ps.setLong(1,account.getId());
            ps.setString(2,account.getLogin());
            ps.setString(3,account.getPassword());
            ps.setInt(4,account.getAccessLevel());
        } catch (SQLException e) {
            LOGER.error("Can't set data of Account to PreparedStatement." +
                    " \n Exception message: " + e.getMessage());
        }
    }

    public static TravelAgent getTravelAgentFromRS(ResultSet rs) {
        if(rs==null) {
            LOGER.error("ResultSet is null (method: getTravelAgentFromRS).");
            return null;
        }
        try {
            long  id= rs.getLong("TravelAgentId");
            String name = rs.getString("Name");
            long agencyId = rs.getLong("AgencyId");
            long accountId = rs.getLong("AccountId");
            double salary = rs.getInt("Salary");

            return  new TravelAgent(id,name,  accountId,agencyId, salary);
        } catch (SQLException e) {
            LOGER.error("Can't get data of TravelAgent from column of ResultSet. \n Exception message: " + e.getMessage());
            return null;
        }
    }

    public static void setTravelAgentToPS(PreparedStatement ps, TravelAgent travelAgent) {
        if(ps==null) {
            LOGER.error("PreparedStatement is null.(method: setTravelAgentToPS).");
            return;
        }
        try {
            ps.setLong(1,travelAgent.getId());
            ps.setString(2,travelAgent.getName());
            ps.setLong(3,travelAgent.getAccountId());
            ps.setLong(4,travelAgent.getAgencyId());
            ps.setDouble(5,travelAgent.getSalary());
        } catch (SQLException e) {
            LOGER.error("Can't set data of TravelAgent to PreparedStatement." +
                    " \n Exception message: " + e.getMessage());
        }
    }

    public static Agency getAgencyFromRS(ResultSet rs) {
        if(rs==null) {
            LOGER.error("ResultSet is null (method: getAgencyFromRS).");
            return null;
        }
        try {
            long  id= rs.getLong("AgencyId");
            String title = rs.getString("Title");
            String phoneNumber = rs.getString("PhoneNumber");

            return  new Agency(id,title,phoneNumber);
        } catch (SQLException e) {
            LOGER.error("Can't get data of Agency from column of ResultSet. \n Exception message: " + e.getMessage());
            return null;
        }
    }

    public static void setAgencyToPS(PreparedStatement ps, Agency agency) {
        if(ps==null) {
            LOGER.error("PreparedStatement is null.(method: setAgencyToPS).");
            return;
        }
        try {
            ps.setLong(1,agency.getId());
            ps.setString(2,agency.getTitle());
            ps.setString(3,agency.getPhoneNumber());
        } catch (SQLException e) {
            LOGER.error("Can't set data of Agency to PreparedStatement." +
                    " \n Exception message: " + e.getMessage());
        }
    }

    public static Tour getTourFromRS(ResultSet rs) {
        if(rs==null) {
            LOGER.error("ResultSet is null (method: getTourFromRS).");
            return null;
        }
        try {
            long  id= rs.getLong("TourId");
            String startDate = rs.getString("StartDate");
            String endDate = rs.getString("EndDate");
            double price = rs.getDouble("Price");
            String type = rs.getString("Type");
            long userId = rs.getLong("UserId");
            long travelAgentId = rs.getLong("TravelAgentId");

            return  new Tour(id,startDate,endDate,price,type,userId,travelAgentId);
        } catch (SQLException e) {
            LOGER.error("Can't get data of Tour from column of ResultSet. \n Exception message: " + e.getMessage(),e);
            return null;
        }
    }

    public static void setTourToPS(PreparedStatement ps, Tour tour) {
        if(ps==null) {
            LOGER.error("PreparedStatement is null.(method: setTourToPS).");
            return;
        }
        try {
            ps.setLong(1,tour.getId());
            ps.setString(2,tour.getStartDate());
            ps.setString(3,tour.getEndDate());
            ps.setDouble(4,tour.getPrice());
            ps.setString(5,tour.getType());
            ps.setLong(6,tour.getUserId());
            ps.setLong(7,tour.getTravelAgentId());
        } catch (SQLException e) {
            LOGER.error("Can't set data of Tour to PreparedStatement." +
                    " \n Exception message: " + e.getMessage());
        }
    }
}
