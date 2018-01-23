package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.TravelAgentDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.models.TableInform;
import by.gstu.training.models.TravelAgent;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlTravelAgentDao extends AbstractMySqlDao<TravelAgent> implements TravelAgentDao {

    public MySqlTravelAgentDao() {
        super(TableInform.TRAVELAGENTS_INFORM);
    }

    @Override
    protected TravelAgent getEntityFromRS(ResultSet rs) {
        return DbDataConverter.getTravelAgentFromRS(rs);
    }

    @Override
    protected void setEntityToPS(PreparedStatement ps, TravelAgent travelAgent) {
        DbDataConverter.setTravelAgentToPS(ps,travelAgent);
    }
}
