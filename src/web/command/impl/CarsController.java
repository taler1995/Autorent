package web.command.impl;

import services.CarsService;
import services.impl.CarsServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yslabko on 08/13/2017.
 */
public class CarsController implements Controller {
    private CarsService carsService = CarsServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("cars", carsService.getAll());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
