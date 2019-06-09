package services.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import dao.CarsDao;
import dao.ItemDao;
import dao.OrderDao;
import dao.UserDao;
import dao.impl.CarsDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;
import entities.Cars;
import entities.Item;
import entities.Order;
import entities.User;
import services.OrderService;
import services.ServiceException;

/**
 * Class OrderServiceImpl
 * <p>
 * Created by yslabko on 08/09/2017.
 */
public class OrderServiceImpl extends AbstractService implements OrderService {
    private static volatile OrderService INSTANCE = null;

    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private CarsDao carsDao = CarsDaoImpl.getInstance();
    private ItemDao itemDao = ItemDaoImpl.getInstance();


    @Override
    public Order save(Order order) {
        try {
            startTransaction();
            order = orderDao.save(order);
            commit();
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + order);
        }
        return order;
    }

    @Override
    public Order get(Serializable id) {
        try {
            return orderDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Order by id" + id);
        }
    }

    @Override
    public void update(Order order) {
        try {
            orderDao.update(order);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Order " + order);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return orderDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + id);
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            startTransaction();
            List<Order> list = orderDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }

    @Override
    public List<Order> getByUserId(long userId) {
        try {
            startTransaction();
            List<Order> list = orderDao.getByUserId(userId);
            commit();
            return list;
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + userId);
        }
    }


    public static OrderService getInstance() {
        OrderService orderService = INSTANCE;
        if (orderService == null) {
            synchronized (OrderServiceImpl.class) {
                orderService = INSTANCE;
                if (orderService == null) {
                    INSTANCE = orderService = new OrderServiceImpl();
                }
            }
        }

        return orderService;
    }
}
