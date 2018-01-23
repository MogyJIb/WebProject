package by.gstu.training.database.common.dao;

import by.gstu.training.database.common.AbstractDao;
import by.gstu.training.models.Account;

public interface AccountDao extends AbstractDao<Integer,Account> {
    Account select(String login);
}
