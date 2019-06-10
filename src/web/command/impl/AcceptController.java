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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AcceptController implements Controller {
    UserService userService = UserServiceImpl.getInstance();
    CarsService carsService = CarsServiceImpl.getInstance();
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ParseException, SQLException {
        String start = req.getParameter("date_start_order");
        String finish = req.getParameter("date_finish_order");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date str = format.parse(start);
        java.util.Date fnsh = format.parse(finish);
        java.sql.Date str1 = new java.sql.Date(str.getTime());
        java.sql.Date fnsh1 = new java.sql.Date(fnsh.getTime());

        long finish1 = fnsh.getTime();
        long start1 = str.getTime();
        String car1 = req.getParameter("id");
        int cars = Integer.parseInt(car1);
        Cars car = carsService.getById(cars);
        long price = car.getPrice();
        long ccc = finish1 - start1;
        long a = ccc / 1000 / 60 / 60 / 24;
        long total = a * price;

        User user = (User)req.getSession().getAttribute("user");
        long us = user.getId();
        req.getSession().setAttribute("user",userService.getById(us));
        req.getSession().setAttribute("cars", carsService.getById(cars));
        Order order = new Order(user.getId(), cars, str1, fnsh1, total);
        req.getSession().setAttribute("order", orderService.save(order));
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}

