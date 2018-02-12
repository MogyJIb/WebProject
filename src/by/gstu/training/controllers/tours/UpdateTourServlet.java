package by.gstu.training.controllers.tours;

import by.gstu.training.database.common.DaoFactory;
import by.gstu.training.models.Tour;
import by.gstu.training.models.User;
import by.gstu.training.utils.JsonConverter;
import by.gstu.training.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gstu.training.logers.MyLoger.LOGER;

public class UpdateTourServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException {
        long tourId =0;
        try{
            tourId = Long.parseLong(req.getParameter("tourId"));
        }catch (Exception ex){
            LOGER.error("Can't get and parse tourId (class: TourHandleServlet)." +
                    "\n Exception message: " + ex.getMessage());
        }

        DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);

        Tour tour = factory.getTourDao().select(tourId);
        tour.setSelled(true);

        factory.getTourDao().update(tour);
    }
}
