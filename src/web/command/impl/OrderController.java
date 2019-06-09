package web.command.impl;

import entities.Cars;
import entities.Order;
import entities.User;
import services.CarsService;
import services.OrderService;
import services.UserService;
import services.impl.CarsServiceImpl;
import services.impl.OrderServiceImpl;
import services.impl.UserServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yslabko on 08/13/2017.
 */
public class OrderController implements Controller {
    UserService userService = UserServiceImpl.getInstance();
    CarsService carsService = CarsServiceImpl.getInstance();
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        long us = user.getId();
        req.getSession().setAttribute("user", userService.getById(us));
        List<Order> order = orderService.getByUserId(us);
//        carsService.getById();
//        long cars = order.getIdCars();
//        req.getSession().setAttribute("cars", carsService.getById(cars));
        req.getSession().setAttribute("orders", orderService.getByUserId(us));


//        Order order = orderService.getByUserId();
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
