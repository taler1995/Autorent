package web.command.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
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
import java.util.List;

public class PreOrdersController implements Controller {
    UserService userService = UserServiceImpl.getInstance();
    CarsService carsService = CarsServiceImpl.getInstance();
    OrderService orderService = OrderServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        /*User user = (User)req.getSession().getAttribute("user");
        List<Order> orders = orderService.getByUserId(user.getId());*/
        String car1 =  req.getParameter("id");
        int cars = Integer.parseInt(car1);

        User user = (User)req.getSession().getAttribute("user");
        long a = user.getId();
        req.getSession().setAttribute("user",userService.getById(a));
//        Cars cars = carsSvice.getById(car1.getId());
       /* req.getSession().setAttribute("order", carsService.getAll());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);*/
        req.getSession().setAttribute("cars", carsService.getById(cars));
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
