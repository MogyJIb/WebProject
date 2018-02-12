package by.gstu.training.controllers.auth;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.logers.MyLoger;
import by.gstu.training.models.Account;
import by.gstu.training.utils.JsonConverter;
import by.gstu.training.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterServlet extends HttpServlet{
    public final static  String ERROR_LOGIN_MASSAGE = "This login already exist.";

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException {

        String jsStr = ServletUtils.getRequestJson(req);

        Account inputedAccount = (Account) JsonConverter.fromJson(jsStr,Account.class);

        DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        Account loadedAccount = daoFactory.getAccountDao().select(inputedAccount.getLogin());

        if(loadedAccount==null) {
            inputedAccount.setAccessLevel(Account.USER);
            daoFactory.getAccountDao().insert(inputedAccount);

            HttpSession session = req.getSession();
            session.setAttribute("login", inputedAccount.getLogin());
            session.setAttribute("role", inputedAccount.getRole());

            try {
                res.sendRedirect("pages/index.html");
            } catch (IOException e) {
                MyLoger.LOGER.error("Can't redirect to /index.html (class: RegisterServlet)." +
                        "\n Exception message: " + e.getMessage());
                return;
            }
        }
        else {
            final String jsonData = ServletUtils.
                    makeJsonErrorMassage("loginErrorsMassage",ERROR_LOGIN_MASSAGE+"  ("+inputedAccount.getLogin()+")  ");
            ServletUtils.sendJson(res,jsonData);
        }


    }
}
