package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.UserDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.database.mssql.MySqlDAOFactory;
import by.gstu.training.models.TableInform;
import by.gstu.training.models.User;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.gstu.training.logers.MyLoger.LOGER;


public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {


    public MySqlUserDao() {
        super(TableInform.USERS_INFORM);
    }

    @Override
    public User selectByAccountId(long accountId) {
        User user = null;

        String selectSqlQuery = "SELECT * FROM "+tableInform.getTableName()+
                " WHERE accountId = ?";

        PreparedStatement pst = connectionUtil.
                getPrepareStatement(MySqlDAOFactory.getDataSource(),selectSqlQuery);
        ResultSet rs = null;
        try {
            pst.setLong(1,accountId);
            rs = pst.executeQuery();
            rs.next();
            user = getEntityFromRS(rs);

        } catch (SQLException e) {
            LOGER.error("Can't create ResultSet (method: selectByAccountId, accountId: "+accountId+")." +
                    " \n Exception message: " + e.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGER.error("Can't close ResultSet (method: selectByAccountId, accountId: "+accountId+")." +
                            "\n Exception message: " + e.getMessage());
                }
            connectionUtil.closePrepareStatement();
        }
        return user;
    }

    @Override
    protected User getEntityFromRS(ResultSet rs) {
        return DbDataConverter.getUserFromRS(rs);
    }

    @Override
    protected void setEntityToPS(PreparedStatement ps, User user) {
        DbDataConverter.setUserToPS(ps,user);
    }
}
