package by.gstu.training.database.common.dao;

import by.gstu.training.database.common.AbstractDao;
import by.gstu.training.models.Tour;

import java.util.List;

public interface TourDao extends AbstractDao<Integer,Tour> {
    List<Tour> selectAll(boolean isSelled);
}
