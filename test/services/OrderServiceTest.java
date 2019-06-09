package services;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import db.ConnectionManager;
import org.junit.Test;
import services.impl.OrderServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class OrderServiceTest {
    private OrderDao order = OrderDaoImpl.getInstance();
    private OrderService service = OrderServiceImpl.getInstance();

    @Test
    public void fullTest() throws SQLException, ParseException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
}
    }
