package dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CarsDao;
import entities.Cars;

/**
 * Class ProductDaoImpl
 * <p>
 * Created by yslabko on 08/08/2017.
 */
public class CarsDaoImpl extends AbstractDao implements CarsDao {
    private static volatile CarsDao INSTANCE = null;

    private static final String getCars = "SELECT * FROM CARS WHERE ID_CARS=?";
    private static final String getByModelAndSupplierQuery = "SELECT * FROM CRS WHERE MODEL=? AND SUPPLIER=?";
    private static final String saveCarsQuery = "INSERT INTO CARS (BRAND, MODEL, NUMBER, COLOUR, CAPACITY, TYPE_OF_BODY, YEAR_OF_EDITION, TYPE_OF_ENGINE,  GEARBOX, STATUS, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateCarsQuery = "UPDATE CARS SET BRAND=?, MODEL=?, NUMBER=?, COLOUR=?, CAPACITY=?, TYPE_OF_BODY=?, YEAR_OF_EDITION=?, TYPE_OF_ENGINE=?, GEARBOX=?, STATUS=?, PRICE=? WHERE ID_CARS=?";
    private static final String getCarsQuery = "SELECT * FROM CARS WHERE ID_CARS=?";
    private static final String getCarsByBrandQuery = "SELECT * FROM CARS WHERE BRAND = ?";
    private static final String getAllCarsQuery = "SELECT * FROM CARS";
    private static final String deleteCarsQuery = "DELETE FROM CARS WHERE ID_CARS=?";

    private PreparedStatement psSave;
    private PreparedStatement psGetById;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetByBrand;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    private CarsDaoImpl() {
    }

    @Override
    public Cars save(Cars cars) throws SQLException {
        psSave = prepareStatement(saveCarsQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, cars.getBrand());
        psSave.setString(2, cars.getModel());
        psSave.setString(3, cars.getPlateNumber());
        psSave.setString(4, cars.getColour());
        psSave.setInt(5, cars.getCapacity());
        psSave.setString(6, cars.getTypeOfBody());
        psSave.setInt(7, cars.getYearOfEdition());
        psSave.setString(8, cars.getTypeOfEngine());
        psSave.setString(9, cars.getGearbox());
        psSave.setString(10, cars.getStatus());
        psSave.setLong(11, cars.getPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            cars.setId(rs.getLong(1));
        }
        close(rs);
        return cars;
    }

    @Override
    public Cars get(Serializable id) throws SQLException {
        psGet = prepareStatement(getCarsQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateProduct(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Cars cars) throws SQLException {
        psUpdate = prepareStatement(updateCarsQuery);
        psUpdate.setLong(12, cars.getId());
        psUpdate.setString(1, cars.getBrand());
        psUpdate.setString(2, cars.getModel());
        psUpdate.setString(3, cars.getPlateNumber());
        psUpdate.setString(4, cars.getColour());
        psUpdate.setInt(5, cars.getCapacity());
        psUpdate.setString(6, cars.getTypeOfBody());
        psUpdate.setInt(7, cars.getYearOfEdition());
        psUpdate.setString(8, cars.getTypeOfEngine());
        psUpdate.setString(9, cars.getGearbox());
        psUpdate.setString(10, cars.getStatus());
        psUpdate.setLong(11, cars.getPrice());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteCarsQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    @Override
    public Cars getCarsByBrand(String brand) throws SQLException {
        psGetByBrand = prepareStatement(getCarsByBrandQuery);
        psGetByBrand.setString(1, brand);
        psGetByBrand.execute();
        ResultSet rs = psGetByBrand.getResultSet();
        while (rs.next()) {
            return populateProduct(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public Cars getById(long id) throws SQLException {
        psGetById = prepareStatement(getCars);
        psGetById.setLong(1, id);
        ResultSet rs = psGetById.executeQuery();
        Cars cars = null;
        if (rs.next()) {
            cars = populateProduct(rs);
        }
        close(rs);

        return cars;
    }

    @Override
    public List<Cars> getAll() throws SQLException {
        psGetAll = prepareStatement(getAllCarsQuery);
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<Cars> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateProduct(rs));
        }
        close(rs);
        return list;
    }

    private Cars populateProduct(ResultSet rs) throws SQLException {
        Cars entity = new Cars();
        entity.setId(rs.getLong(1));
        entity.setBrand(rs.getString(2));
        entity.setModel(rs.getString(3));
        entity.setPlateNumber(rs.getString(4));
        entity.setColour(rs.getString(5));
        entity.setCapacity(rs.getInt(6));
        entity.setTypeOfBody(rs.getString(7));
        entity.setYearOfEdition(rs.getInt(8));
        entity.setTypeOfEngine(rs.getString(9));
        entity.setGearbox(rs.getString(10));
        entity.setStatus(rs.getString(11));
        entity.setPrice(rs.getLong(12));
        return entity;
    }

    public static CarsDao getInstance() {
        CarsDao productDao = INSTANCE;
        if (productDao == null) {
            synchronized (CarsDaoImpl.class) {
                productDao = INSTANCE;
                if (productDao == null) {
                    INSTANCE = productDao = new CarsDaoImpl();
                }
            }
        }

        return productDao;
    }
}
