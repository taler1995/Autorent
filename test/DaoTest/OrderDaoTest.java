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
        Date date2 = new Date(df.parse("2019-05-07").getTime());
        Order newOrder = order.save(new Order(1, 2, date, date2, 5));
        java.util.Date birt1 = newOrder.getFinish();
        java.util.Date birt2 = newOrder.getStart();
        java.sql.Date birthday1 = new java.sql.Date(birt1.getTime());
        java.sql.Date birthday2 = new java.sql.Date(birt2.getTime());
        long aaa = birt1.getTime();
        long bbb = birt2.getTime();
        long ccc = aaa-bbb;
        System.out.println(ccc);
        long a  = ccc/1000/60/60/24;
                System.out.println(aaa);
        System.out.println(a);
        System.out.println(birthday1);
        System.out.println(birthday2);

        connection.commit();
        int afterSave = order.getAll().size();
        System.out.println(order.getAll());
        System.out.println(afterSave);
        Assert.assertNotSame(beforeSave, afterSave);
    }
}
