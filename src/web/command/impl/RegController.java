package web.command.impl;

import entities.User;
import services.UserService;
import services.impl.UserServiceImpl;
import web.auth.Encoder;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegController implements Controller {
    /*UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login==null || password==null) {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
        User user = userService.getByLogin(login);
        if (user != null && user.getPassword().equals(Encoder.encode(password))) {
//        if (user != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath+ "/frontController?command=orders");
            return;
        } else {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            req.setAttribute("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
    }*/
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ParseException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String str = req.getParameter("birthday");
        String pass_lett = req.getParameter("passport_lett");
        String pass_id_def = req.getParameter("passport_id");
        String country = req.getParameter("country");
        String identif = req.getParameter("identification_number");
        String driv_exp_def = req.getParameter("driving_experience");
        String number_of_phone_def = req.getParameter("number_of_phone");
        String email = req.getParameter("email");
        if (login == null || password == null) {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
        if (str != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date birt = format.parse(str);
            java.sql.Date birthday = new java.sql.Date(birt.getTime());
            int pass_id = Integer.parseInt(pass_id_def);
            int driv_exp = Integer.parseInt(driv_exp_def);
            int number_of_phone = Integer.parseInt(number_of_phone_def);
            User us = new User(name, surname, login, password, birthday, pass_lett, pass_id, country, identif, driv_exp, number_of_phone, email);
            User user = userService.save(us);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=cars");
        }
    }
}
