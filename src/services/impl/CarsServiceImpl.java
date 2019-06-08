package services.impl;

import dao.CarsDao;
import dao.impl.CarsDaoImpl;
import entities.Cars;
import entities.User;
import services.CarsService;
import services.ServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yslabko on 08/13/2017.
 */
public class CarsServiceImpl extends AbstractService implements CarsService {
    private static volatile CarsService INSTANCE = null;

    CarsDao carsDao = CarsDaoImpl.getInstance();

    @Override
    public Cars getCarsByBrand(String brand) {
        try {
            return carsDao.getCarsByBrand(brand);
        } catch (SQLException e) {
            throw new ServiceException("Error getting by Brand:" + brand);
        }
    }

    @Override
    public List<Cars> getAll() {
        try {
            startTransaction();
            List<Cars> list = carsDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }

    @Override
    public Cars getById(long id) {
        try {
            return carsDao.getById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Cars by Id" + id);
        }
    }


    public static CarsService getInstance() {
        CarsService productService = INSTANCE;
        if (productService == null) {
            synchronized (CarsServiceImpl.class) {
                productService = INSTANCE;
                if (productService == null) {
                    INSTANCE = productService = new CarsServiceImpl();
                }
            }
        }

        return productService;
    }
}
