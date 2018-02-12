package by.gstu.training.controllers;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.models.User;
import by.gstu.training.utils.JsonConverter;
import by.gstu.training.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gstu.training.logers.MyLoger.LOGER;

public class UserHandleServlet extends HttpServlet{
    public final static  String ERROR_MASSAGE = "Error, can't add new User.";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException {
        String jsStr = ServletUtils.getRequestJson(req);

        User user = (User) JsonConverter.fromJson(jsStr,User.class);
        DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        if(user!=null){
            daoFactory.getUserDao().insert(user);
            try {
                res.sendRedirect("pages/index.html");
            } catch (IOException e) {
                LOGER.error("Can't redirect to /index.html (class: UserHandleServlet)." +
                        "\n Exception message: " + e.getMessage());
                return;
            }
        }else {
            final String jsonData = ServletUtils.
                    makeJsonErrorMassage("errorsMassage",ERROR_MASSAGE);
            ServletUtils.sendJson(res,jsonData);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        long accountId =0;
        try{
            accountId = Long.parseLong(req.getParameter("accountId"));
        }catch (Exception ex){
            LOGER.error("Can't get and parse accountId (class: UserHandleServlet)." +
                    "\n Exception message: " + ex.getMessage());
        }

        DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        User user = factory.getUserDao().selectByAccountId(accountId);
        String jsStrList = JsonConverter.toJson(user);

        ServletUtils.sendJson(resp,jsStrList);
    }
}
