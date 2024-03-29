package web.filters;

import web.command.enums.ControllerType;
import web.handlers.RequestHandler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static web.command.enums.ControllerType.ORDERS;
import static web.command.enums.ControllerType.PREORDER;

/**
 * Class AuthFilter
 *
 * Created by yslabko on 08/11/2017.
 */
@WebFilter(urlPatterns = {"/frontController"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        ControllerType type = RequestHandler.getCommand(req);
        if (ORDERS.equals(type)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            if((session.getAttribute("user") == null)) {
                res.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
        }
        if (PREORDER.equals(type)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            String param = req.getParameter("command");
            ControllerType type1 = ControllerType.getByPageName(param);
            session.setAttribute("type1",type1);
            if ((session.getAttribute("user") == null)) {
                res.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}