package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.TourDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.database.mssql.MySqlDAOFactory;
import by.gstu.training.models.TableInform;
import by.gstu.training.models.Tour;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.gstu.training.logers.MyLoger.LOGER;

public class MySqlTourDao extends AbstractMySqlDao<Tour> implements TourDao {

    public MySqlTourDao() {
        super(TableInform.TOURS_INFORM);
    }

    @Override
    protected Tour getEntityFromRS(ResultSet rs) {
        return DbDataConverter.getTourFromRS(rs);
    }

    @Override
    protected void setEntityToPS(PreparedStatement ps, Tour tour) {
        DbDataConverter.setTourToPS(ps,tour);
    }


    @Override
    public List<Tour> selectAll(boolean isSelled) {
        List<Tour> entities = new ArrayList<>();

        String selectAllSqlQuery = "SELECT * FROM "+tableInform.getTableName()+
                " WHERE IsSelled = ?";;

        PreparedStatement ps = connectionUtil.getPrepareStatement(MySqlDAOFactory.getDataSource(),selectAllSqlQuery);
        ResultSet rs = null;
        try {
            ps.setBoolean(1,isSelled);
            rs = ps.executeQuery();
            while (rs.next())
                entities.add(getEntityFromRS(rs));

        } catch (SQLException e) {
            LOGER.error("Can't create ResultSet (method: selectAll). " +
                    "\n Exception message: " + e.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGER.error("Can't close ResultSet (method: selectAll)." +
                            "\n Exception message: " + e.getMessage());
                }
            connectionUtil.closePrepareStatement();
        }

        return entities;
    }
}
