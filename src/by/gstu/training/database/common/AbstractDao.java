package by.gstu.training.database.common;

import by.gstu.training.models.DbEntity;

import java.util.List;

public interface AbstractDao <K,T extends DbEntity> {
    List<T> selectAll();
    void insert(T entity);
    void update(T entity);
    void delete(long id);
    T select(long id);
}
