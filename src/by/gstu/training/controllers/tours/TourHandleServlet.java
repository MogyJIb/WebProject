package by.gstu.training.controllers.tours;

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
import java.io.IOException;
import java.util.List;

import static by.gstu.training.logers.MyLoger.LOGER;

public class TourHandleServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long tourId =0;
        try{
            tourId = Long.parseLong(req.getParameter("tourId"));
        }catch (Exception ex){
            LOGER.error("Can't get and parse tourId (class: TourHandleServlet)." +
                    "\n Exception message: " + ex.getMessage());
        }

        DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        Tour tour = factory.getTourDao().select(tourId);
        String jsStrList = JsonConverter.toJson(tour);

        ServletUtils.sendJson(resp,jsStrList);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String jsStr = ServletUtils.getRequestJson(req);

        Tour tour = (Tour) JsonConverter.fromJson(jsStr,Tour.class);

        if(tour!=null){
            DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

            factory.getTourDao().update(tour);
        }
        else {
            LOGER.error("Can't update tour data. Tour is null (class: TourHandleServlet).");
        }
    }



}
