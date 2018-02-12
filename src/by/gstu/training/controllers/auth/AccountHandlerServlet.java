package by.gstu.training.controllers.auth;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.models.Account;
import by.gstu.training.utils.JsonConverter;
import by.gstu.training.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountHandlerServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        List<Account> accounts = factory.getAccountDao().selectAll();
        String jsStrList = JsonConverter.toJson(accounts);
        ServletUtils.sendJson(resp,jsStrList);
    }
}
