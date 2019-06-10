package DaoTest;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import db.ConnectionManager;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientsDaoTest {

    private UserDao user = UserDaoImpl.getInstance();

    public void initData() {
    }

    @Test
    public void fullTest() throws SQLException, ParseException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        int beforeSave = user.getAll().size();
        System.out.println(user.getAll());
        System.out.println(beforeSave);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(df.parse("2019-05-05").getTime());
        User newUser = user.save(new User("111", "111", "111","ds", date, "11",
                111, "465", "45688", 111, 1234, "123"));
        int afterSave = user.getAll().size();
        System.out.println(user.getAll());
        System.out.println(afterSave);
        Assert.assertNotSame(beforeSave, afterSave);
        connection.commit();

        connection.rollback();

        newUser.setName("Dif");
        user.update(newUser);
        System.out.println(user.getAll());

        User updatedPart = user.get(newUser.getId());
        Assert.assertEquals("Метод update не корректен", "Dif", updatedPart.getName());
        user.delete(newUser.getId());

        afterSave = user.getAll().size();

        System.out.println(afterSave);
        Assert.assertEquals(beforeSave, afterSave);
    }


    @Test
    public void countTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        int a = user.getCountClients();
        System.out.println(a);
    }
}
