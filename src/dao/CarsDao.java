package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Cars;
import entities.User;

/**
 * Class ProductDao
 *
 * Created by yslabko on 08/08/2017.
 */
public interface CarsDao extends DAO<Cars> {
    Cars getCarsByBrand(String brand) throws SQLException;
    Cars getById(long id) throws SQLException;
    List<Cars> getAll() throws SQLException;
}
