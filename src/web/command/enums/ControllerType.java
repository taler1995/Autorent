package web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import web.command.Controller;
import web.command.impl.*;

/**
 * Created by yslabko on 08/13/2017.
 */
@Getter
@AllArgsConstructor
public enum ControllerType {
    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("login.jsp", "Logout", "logout.title", new LogoutController()),
    ORDERS("orders/main.jsp", "Orders", "orders.title", new OrderController()),
    ADD_PRODUCTS_AJAX("", "addProduct", "", new AddToBasketController()),
    CARS("cars/main.jsp", "Cars", "cars.title", new CarsController()),
    REG ("reg/main.jsp","Reg","reg.title", new RegController()),
    PREORDER("preorder/main.jsp","Preorder","order.title",new PreOrdersController()),
    ACCEPT("accept/main.jsp","Accept","accept.title",new AcceptController());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с продуктами
        return CARS;
    }
}
