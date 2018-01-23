package by.gstu.training.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.gstu.training.logers.MyLoger.LOGER;

public class DbConnectionUtil {
    private static Connection connection;
    private static PreparedStatement prSt;
    public static PreparedStatement getPrepareStatement(DataSource dataSource, String sqlQuery){
        connection = getConnection(dataSource);
        prSt = getPrepareStatement(connection,sqlQuery);
        return prSt;
    }

    public static PreparedStatement getPrepareStatement(Connection connection, String sqlQuery){
        try {
            prSt = connection.prepareStatement(sqlQuery);
            return prSt;
        } catch (SQLException e) {
            LOGER.error("Can't create Statement. Exception message: " + e.getMessage(), e);
            return null;
        }
    }
    public static void closePrepareStatement(PreparedStatement st){
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {
                LOGER.error("Can't close PreparedStatement. Exception message: " + e.getMessage(), e);
            }
    }
    public static void closePrepareStatement(){
        closeConnection();
        closePrepareStatement(prSt);
    }
    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGER.error("Can't get Connection. Exception message: " + e.getMessage(), e);
            return null;
        }
    }
    public static void closeConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                LOGER.error("Can't close Connection. Exception message: " + e.getMessage(), e);
            }
    }
    public static void closeConnection() {
        closeConnection(connection);
    }

    public static DataSource getDataSource(String dataSourceName ) {

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/"+dataSourceName);

            return ds;
        } catch (NamingException e) {
            LOGER.error("Can't get context\n"+e.getMessage(),e);
            return null;
        }
    }

}
