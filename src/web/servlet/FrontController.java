package web.servlet;

import web.command.enums.ControllerType;
import web.handlers.RequestHandler;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class FrontController
 *
 * Created by yslabko on 08/11/2017.
 */
@WebServlet(urlPatterns = "/frontController")
public class FrontController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerType controllerType = RequestHandler.getCommand(req);
        try {
            controllerType.getController().execute(req, resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
