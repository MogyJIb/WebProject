package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.AccountDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.database.mssql.MySqlDAOFactory;
import by.gstu.training.models.Account;
import by.gstu.training.models.TableInform;
import by.gstu.training.utils.DbConnectionUtil;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.gstu.training.logers.MyLoger.LOGER;

public class MySqlAccountDao extends AbstractMySqlDao<Account> implements AccountDao {


    public MySqlAccountDao() {
        super(TableInform.ACCOUNTS_INFORM);
    }

    @Override
    protected Account getEntityFromRS(ResultSet rs) {
        return DbDataConverter.getAccountFromRS(rs);
    }

    @Override
    protected void setEntityToPS(PreparedStatement ps, Account account) {
        DbDataConverter.setAccountToPS(ps,account);
    }

    @Override
    public Account select(String login) {
        Account account = null;

        String selectSqlQuery = "SELECT * FROM "+tableInform.getTableName()+
                " WHERE Login = ?";

        PreparedStatement pst = DbConnectionUtil.
                getPrepareStatement(MySqlDAOFactory.getDataSource(),selectSqlQuery);
        ResultSet rs = null;
        try {
            pst.setString(1,login);
            rs = pst.executeQuery();
            rs.next();
            account = getEntityFromRS(rs);

        } catch (SQLException e) {
            LOGER.error("Can't create ResultSet (method: select, login: "+login+
                    "). \n Exception message: " + e.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGER.error("Can't close ResultSet (method: select, login: "+login+
                            "\n Exception message: " + e.getMessage());
                }
            DbConnectionUtil.closePrepareStatement();
        }


        return account;
    }
}
