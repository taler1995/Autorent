package DaoTest;

import dao.CarsDao;
import dao.impl.CarsDaoImpl;
import db.ConnectionManager;
import entities.Cars;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class CarDaoTest {
    private CarsDao Cars = CarsDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(false);
        int beforeSave = Cars.getAll().size();
        System.out.println(Cars.getAll());
        System.out.println(beforeSave);
        Cars newCars = Cars.save(new Cars("Lada", "Vesta", "A7777A", "Red", 1, "Sedan", 1998, "Disl", "Mech", "Ava", 50));
        int afterSave = Cars.getAll().size();
        System.out.println(Cars.getAll());
        System.out.println(afterSave);
        Assert.assertNotSame(beforeSave, afterSave);

//        connection.rollback();

        newCars.setModel("HF 12-23");
        Cars.update(newCars);
        System.out.println(Cars.getAll());

        Cars updatedItem = Cars.get(newCars.getId());
        Assert.assertEquals("Метод update не корректен", "HF 12-23", updatedItem.getModel());

        /*Cars item2 = CarsDao.getItemByBrandAndModel("HF 12-23", "Makita");
        Assert.assertNotNull(item2);*/

        Cars.delete(newCars.getId());

        afterSave = Cars.getAll().size();
        System.out.println(beforeSave);
        System.out.println(afterSave);
        Assert.assertEquals(beforeSave, afterSave);

    }

    @Test
    public void getByModelAndBrandTest() throws SQLException {

    }
}
