package by.gstu.training.database.mssql;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.database.common.dao.*;
import by.gstu.training.database.mssql.dao.*;
import by.gstu.training.utils.DbConnectionUtil;

import javax.sql.DataSource;



public class MySqlDAOFactory extends DaoFactory {
    public static final String DATA_SOURCE_NAME = "jdbc/TravelAgency";
    private static DataSource dataSource;

    public synchronized static DataSource getDataSource() {
        if(dataSource == null){
            dataSource = DbConnectionUtil.getDataSource(DATA_SOURCE_NAME);
        }
        return dataSource;
    }


    @Override
    public AccountDao getAccountDao(){
        return new MySqlAccountDao();
    }

    @Override
    public AgencyDao getAgencyDao() {
        return new MySqlAgencyDao();
    }

    @Override
    public TourDao getTourDao() {
        return new MySqlTourDao();
    }

    @Override
    public TravelAgentDao getTravelAgentDao() {
        return new MySqlTravelAgentDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao();
    }


}

