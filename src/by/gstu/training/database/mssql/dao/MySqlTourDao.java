package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.TourDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.models.TableInform;
import by.gstu.training.models.Tour;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
