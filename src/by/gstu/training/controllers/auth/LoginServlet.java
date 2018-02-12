package by.gstu.training.controllers.auth;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.logers.MyLoger;
import by.gstu.training.models.Account;
import by.gstu.training.models.Tour;
import by.gstu.training.utils.JsonConverter;
import by.gstu.training.utils.ServletUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static by.gstu.training.logers.MyLoger.LOGER;

public class LoginServlet extends HttpServlet {

     public final static  String ERROR_PASSWORD_MASSAGE = "Incorrect password.";
    public final static  String ERROR_LOGIN_MASSAGE = "This user doesn't exist.";

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException {

        String jsStr = ServletUtils.getRequestJson(req);


        Account inputedAccount = (Account) JsonConverter.fromJson(jsStr,Account.class);

        DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        Account loadedAccount = daoFactory.getAccountDao().select(inputedAccount.getLogin());

        if(loadedAccount!=null){
            if(loadedAccount.getPassword().equals(inputedAccount.getPassword())){
                HttpSession session=req.getSession();
                session.setAttribute("login",loadedAccount.getLogin());
                session.setAttribute("role",loadedAccount.getRole());

                try {
                    res.sendRedirect("pages/index.html");
                } catch (IOException e) {
                    LOGER.error("Can't redirect to /index.html (class: LoginServlet).\n Exception message: " + e.getMessage());
                    return;
                }
            }else {
                final String jsonData = ServletUtils.
                        makeJsonErrorMassage("passwordErrorsMassage",ERROR_PASSWORD_MASSAGE);
                ServletUtils.sendJson(res,jsonData);
            }
        }else {
            final String jsonData = ServletUtils.
                    makeJsonErrorMassage("loginErrorsMassage",ERROR_LOGIN_MASSAGE+"  ("+inputedAccount.getLogin()+")  ");
            ServletUtils.sendJson(res,jsonData);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session=req.getSession();
        String login = (String) session.getAttribute("login");

        DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        Account account = daoFactory.getAccountDao().select(login);

        String jsStrList = JsonConverter.toJson(account);

        ServletUtils.sendJson(res,jsStrList);
    }


}