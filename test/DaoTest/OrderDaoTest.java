package DaoTest;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import db.ConnectionManager;
import entities.Order;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderDaoTest {
    private OrderDao order = OrderDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException, ParseException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        int beforeSave = order.getAll().size();
        System.out.println(order.getAll());
        System.out.println(beforeSave);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(df.parse("2019-05-05").getTime());
        Order newOrder = order.save(new Order(1,2,date,date,5));
        connection.commit();
        int afterSave = order.getAll().size();
        System.out.println(order.getAll());
        System.out.println(afterSave);
        Assert.assertNotSame(beforeSave, afterSave);
    }
}
