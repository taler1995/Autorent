package web.command.impl;

import com.google.gson.Gson;
import entities.Order;
import entities.User;
import services.OrderService;
import services.impl.OrderServiceImpl;
import web.command.Controller;
import web.vo.BasketVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yslabko on 08/17/2017.
 */
public class AddToBasketController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasketVO basket = (BasketVO) req.getSession().getAttribute("basket");
        if (basket == null) {
            basket = new BasketVO(new HashMap<>());
            req.getSession().setAttribute("basket", basket);
        }
        long carsId = Long.parseLong(req.getParameter("carsId"));
//        String id = req.getReader().readLine();
//        Gson in = new Gson();
//        long carsId = in.fromJson(id, Long.class);
        AtomicInteger count = basket.getBasket().get(carsId);
        int currentCount = 0;
        if (count == null) {
            count = new AtomicInteger();
            count.set(1);
            currentCount = 1;
        } else {
            currentCount = count.incrementAndGet();
        }
        basket.getBasket().put(carsId, count);
        PrintWriter writer = resp.getWriter();
        writer.print(new Gson().toJson(currentCount));
    }
}
