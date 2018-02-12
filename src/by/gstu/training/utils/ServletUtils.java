package by.gstu.training.utils;

import by.gstu.training.logers.MyLoger;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServletUtils {
    public  static String getRequestJson(HttpServletRequest request){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        } catch (IOException e) {
            MyLoger.LOGER.error("Can't get input stream from request (class: ServletUtils).\n Exception message: " + e.getMessage());
            return "";
        }
        String jsStr = "";
        if(reader!=null){
            try {
                jsStr = reader.readLine();
            } catch (IOException e) {
                MyLoger.LOGER.error("Can't read data from request (class: ServletUtils).\n Exception message: " + e.getMessage());
                return "";
            }
        }
        return jsStr;
    }

    public  static  String makeJsonErrorMassage(String parameterName, String errorMassages){
        return "{\""+parameterName+"\":\""+errorMassages+"\"}";
    }
    public  static  void sendJson(HttpServletResponse res, String jsonData){
        try {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(jsonData);
        } catch (IOException e) {
            MyLoger.LOGER.error("Can't write data to response (class: LoginServlet).\n Exception message: " + e.getMessage());
            return;
        }
    }
}
