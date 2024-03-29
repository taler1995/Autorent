package web.handlers;

import web.command.enums.ControllerType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static web.command.enums.ControllerType.CARS;
import static web.command.enums.ControllerType.PREORDER;

/**
 * Created by yslabko on 08/13/2017.
 */
public class RequestHandler {
    public static ControllerType getCommand(HttpServletRequest req){
        String param = req.getParameter("command");
        if (param == null && "".equals(param)) {
            param = "cars.title";
        }


        ControllerType type = ControllerType.getByPageName(param);
        req.setAttribute("title", type.getI18nKey());
        HttpSession session = req.getSession();
        String pageName = (String)session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", CARS.getPageName());
            session.setAttribute("pagePath", CARS.getPagePath());
        }

        return type;
    }
}
