package by.gstu.training.database.common;

import by.gstu.training.database.common.dao.*;
import by.gstu.training.database.mssql.MySqlDAOFactory;

// Абстрактный класс DAO Factory
public abstract class DaoFactory {
    // Список типов DAO, поддерживаемых генератором
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;


    // Здесь будет метод для каждого DAO, который может быть
    // создан. Реализовывать эти методы
    // должны конкретные генераторы.
    public abstract AccountDao getAccountDao();
    public abstract AgencyDao getAgencyDao();
    public abstract TourDao getTourDao();
    public abstract TravelAgentDao getTravelAgentDao();
    public abstract UserDao getUserDao();



    public static DaoFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySqlDAOFactory();

            default:
                return null;
        }
    }


}