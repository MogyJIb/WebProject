package by.gstu.training.controllers;



import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.models.*;
import by.gstu.training.utils.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllToursServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        List<Tour> tours = factory.getTourDao().selectAll();
        String jsStrList = JsonConverter.toJson(tours);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsStrList);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        throw new UnsupportedOperationException("from servlet: GetAllToursServlet, - method doPost don't implemented");
    }



}
