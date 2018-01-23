package by.gstu.training.database.mssql.dao;

import by.gstu.training.database.common.dao.AgencyDao;
import by.gstu.training.database.mssql.AbstractMySqlDao;
import by.gstu.training.models.Agency;
import by.gstu.training.models.TableInform;
import by.gstu.training.utils.DbDataConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlAgencyDao extends AbstractMySqlDao<Agency> implements AgencyDao {

    public MySqlAgencyDao() {
        super(TableInform.AGENCIES_INFORM);
    }

    @Override
    protected Agency getEntityFromRS(ResultSet rs) {
        return DbDataConverter.getAgencyFromRS(rs);
    }

    @Override
    protected void setEntityToPS(PreparedStatement ps, Agency agency) {
        DbDataConverter.setAgencyToPS(ps,agency);
    }
}
