package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.UserDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.models.TableInform;
import by.gstu.training.models.User;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {


    public MySqlUserDao() {
        super(TableInform.USERS_INFORM);
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
