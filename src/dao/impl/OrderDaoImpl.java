package dao.impl;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import db.ConnectionManager;
import entities.Order;

/**
 * Class OrderDaoImpl
 * <p>
 * Created by yslabko on 08/09/2017.
 */
public class OrderDaoImpl extends AbstractDao implements OrderDao {
    private static volatile OrderDao INSTANCE = null;

        private static final String saveOrderQuery = "INSERT INTO ORDERS (USER_ID, ID_CARS, DATE_START_ORDER, DATE_FINISH_ORDER, TOTAL) VALUES (?,?,?,?,?)";
    private static final String updateOrderQuery = "UPDATE ORDERS SET ID_CARS=?, USER_ID=?, DATE_START_ORDER=?, DATE_FINISH_ORDER=?, TOTAL=? WHERE ID=?";
    private static final String getOrderQuery = "SELECT * FROM ORDERS WHERE ID=?";
    private static final String deleteOrderQuery = "DELETE FROM ORDERS WHERE ID=?";
    private static final String getAllOrderQuery = "SELECT * FROM ORDERS";
    private static final String getAllByUserQuery = "SELECT * FROM ORDERS WHERE USER_ID = ?";
    private static final String deleteAllDamage = "DELETE FROM ORDERS";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAllBy;
    private PreparedStatement psDeleteAll;

    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveOrderQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateOrderQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getOrderQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllOrderQuery);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteOrderQuery);
            psGetAllBy = ConnectionManager.getConnection().prepareStatement(getAllByUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderDaoImpl() {
    }

    @Override
    public Order save(Order order) throws SQLException {
        psSave.setLong(1, order.getIdClients());
        psSave.setLong(2, order.getIdCars());
        psSave.setDate(3, order.getStart());
        psSave.setDate(4, order.getFinish());
        psSave.setDouble(5, order.getTotal());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            order.setId(rs.getLong(1));
        }
        close(rs);
        return order;
    }

    @Override
    public Order get(Serializable id) throws SQLException {
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Order order) throws SQLException {
        psUpdate.setLong(5, order.getId());
        psUpdate.setLong(1, order.getIdCars());
        psUpdate.setLong(2, order.getIdClients());
        psUpdate.setDate(3, (Date) order.getStart());
        psUpdate.setDate(4, (Date) order.getFinish());
        psUpdate.setDouble(5,order.getTotal());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Order> getByUserId(long userId) throws SQLException {
        psGetAllBy.setLong(1, userId);
        psGetAllBy.execute();
        ResultSet rs = psGetAllBy.getResultSet();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);

        return list;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }


    private Order populateEntity(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong(1));
        order.setIdClients(rs.getLong(2));
        order.setIdCars(rs.getLong(3));
        order.setStart(rs.getDate(4));
        order.setFinish(rs.getDate(5));
        order.setTotal(rs.getDouble(6));
        return order;
    }

    public static OrderDao getInstance() {
        OrderDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new OrderDaoImpl();
                }
            }
        }

        return itemDao;
    }
}
