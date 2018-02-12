package by.gstu.training.database.mssql;

import by.gstu.training.database.common.AbstractDao;
import by.gstu.training.models.DbEntity;
import by.gstu.training.models.TableInform;
import by.gstu.training.utils.DbConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.gstu.training.logers.MyLoger.LOGER;

public abstract class AbstractMySqlDao<T extends DbEntity> implements AbstractDao<Integer,T> {
    protected TableInform tableInform;
    protected DbConnectionUtil connectionUtil;

    public AbstractMySqlDao(TableInform tableInform){
        this.tableInform = tableInform;
        this.connectionUtil = new DbConnectionUtil();
    }

    @Override
    public List<T> selectAll() {
        List<T> entities = new ArrayList<>();

        String selectAllSqlQuery = "SELECT * FROM "+tableInform.getTableName();

        ResultSet rs = null;
        try {
            rs = connectionUtil.
                    getPrepareStatement(MySqlDAOFactory.getDataSource(),selectAllSqlQuery).
                    executeQuery();
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

    @Override
    public  T select(long id) {
        T entity = null;

        String selectSqlQuery = "SELECT * FROM "+tableInform.getTableName()+
                " WHERE "+tableInform.getIdColumnName()+" = ?";

        PreparedStatement pst = connectionUtil.
                getPrepareStatement(MySqlDAOFactory.getDataSource(),selectSqlQuery);
        ResultSet rs = null;
        try {
            pst.setLong(1,id);
            rs = pst.executeQuery();
            rs.next();
            entity = getEntityFromRS(rs);

        } catch (SQLException e) {
            LOGER.error("Can't create ResultSet (method: select, id: "+id+
                    "). \n Exception message: " + e.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGER.error("Can't close ResultSet (method: select, id: "+id+")." +
                            "\n Exception message: " + e.getMessage());
                }
            connectionUtil.closePrepareStatement();
        }


        return entity;
    }

    @Override
    public  void insert(T entity) {
        String insertSqlQuery = "INSERT INTO "+tableInform.getTableName()+tableInform.getParameters()+
                " VALUES "+tableInform.getInsertedParameters();

        PreparedStatement pst = connectionUtil.
                getPrepareStatement(MySqlDAOFactory.getDataSource(),insertSqlQuery);
        try {
            setEntityToPS(pst,entity);
            pst.executeUpdate();

        } catch (SQLException e) {
            LOGER.error("Can't executeUpdate statement (method: insert, entity: "+
                    entity.toString()+"). \n Exception message: " + e.getMessage());
        } finally {
            connectionUtil.closePrepareStatement();
        }
    }

    @Override
    public  void update(T entity) {
        String updateSqlQuery = "UPDATE "+tableInform.getTableName()+
                " SET "+tableInform.getUpdatedParameters()+
                " WHERE "+tableInform.getIdColumnName()+" = ?";

        PreparedStatement pst = connectionUtil.
                getPrepareStatement(MySqlDAOFactory.getDataSource(),updateSqlQuery);
        try {
            setEntityToPS(pst,entity);
            pst.setLong(tableInform.getCountParameters(),entity.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            LOGER.error("Can't executeUpdate statement (method: update, entity: "+
                    entity.toString()+"). \n Exception message: " + e.getMessage());
        } finally {
            connectionUtil.closePrepareStatement();
        }
    }

    @Override
    public void delete(long id) {
        String deleteAllSqlQuery = "DELETE FROM "+tableInform.getTableName()+" WHERE "+tableInform.getIdColumnName()+" = ?";

        PreparedStatement pst = connectionUtil.
                getPrepareStatement(MySqlDAOFactory.getDataSource(),deleteAllSqlQuery);
        try {
            pst.setLong(1,id);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGER.error("Can't create ResultSet (method: select, id: "+id+"). \n Exception message: " + e.getMessage());
        } finally {
            connectionUtil.closePrepareStatement();
        }
    }


    protected abstract T getEntityFromRS(ResultSet rs);
    protected abstract void setEntityToPS(PreparedStatement ps,T entity);
}
