package by.gstu.training.controllers;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.logers.MyLoger;
import by.gstu.training.models.Account;
import by.gstu.training.utils.JsonConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginServlet extends HttpServlet {

     public final static  String ERROR_PASSWORD_MASSAGE = "Incorrect password!";
    public final static  String ERROR_LOGIN_MASSAGE = "This user doesn't exist!";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        } catch (IOException e) {
            MyLoger.LOGER.error("Can't get input stream from request (class: LoginServlet).\n Exception message: " + e.getMessage());
            return;
        }
        String jsStr = "";
        if(reader!=null){

            try {
                jsStr = reader.readLine();
            } catch (IOException e) {
                MyLoger.LOGER.error("Can't read data from request (class: LoginServlet).\n Exception message: " + e.getMessage());
                return;
            }
        }

        Account inputedAccount = (Account) JsonConverter.fromJson(jsStr,Account.class);

        DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        Account loadedAccount = daoFactory.getAccountDao().select(inputedAccount.getLogin());

        if(loadedAccount!=null){
            if(loadedAccount.getPassword().equals(inputedAccount.getPassword())){
                HttpSession session=request.getSession();
                session.setAttribute("login",loadedAccount.getLogin());
                session.setAttribute("role",loadedAccount.getRole());

                try {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/index.html");
                    requestDispatcher.forward(request,response);
                } catch (IOException e) {
                    MyLoger.LOGER.error("Can't redirect to /index.html (class: LoginServlet).\n Exception message: " + e.getMessage());
                    return;
                }
            }else {
                final String jsonData = makeJsonErrorMassage("passwordErrorsMassage",ERROR_PASSWORD_MASSAGE);
                sendJson(response,jsonData);
            }
        }else {
            final String jsonData = makeJsonErrorMassage("loginErrorsMassage",ERROR_LOGIN_MASSAGE+"  ("+inputedAccount.getLogin()+")  ");
            sendJson(response,jsonData);
        }


    }
    private String makeJsonErrorMassage(String parameterName,String errorMassages){
        return "{\""+parameterName+"\":\""+errorMassages+"\"}";
    }
    private void sendJson(HttpServletResponse response, String jsonData){
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
        } catch (IOException e) {
            MyLoger.LOGER.error("Can't write data to response (class: LoginServlet).\n Exception message: " + e.getMessage());
            return;
        }
    }
}