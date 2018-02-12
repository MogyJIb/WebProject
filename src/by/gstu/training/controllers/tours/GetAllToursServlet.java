package by.gstu.training.controllers.tours;



import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.models.*;
import by.gstu.training.utils.JsonConverter;
import by.gstu.training.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.gstu.training.logers.MyLoger.LOGER;

public class GetAllToursServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean isSelled = false;
        try{
            isSelled = Boolean.parseBoolean(req.getParameter("isSelled"));
        }catch (Exception ex){
            LOGER.error("Can't get and parse isSelled (class: GetAllToursServlet)." +
                    "\n Exception message: " + ex.getMessage());
        }

        DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        List<Tour> tours = factory.getTourDao().selectAll(isSelled);
        String jsStrList = JsonConverter.toJson(tours);
        ServletUtils.sendJson(resp,jsStrList);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        throw new UnsupportedOperationException("from servlet: GetAllToursServlet, - method doPost don't implemented");
    }



}
