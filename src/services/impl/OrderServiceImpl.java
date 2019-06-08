package services.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import dao.CarsDao;
import dao.ItemDao;
import dao.OrderDao;
import dao.impl.CarsDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import entities.Cars;
import entities.Item;
import entities.Order;
import services.OrderService;
import services.ServiceException;

/**
 * Class OrderServiceImpl
 *
 * Created by yslabko on 08/09/2017.
 */
public class OrderServiceImpl extends AbstractService implements OrderService {
    private static volatile OrderService INSTANCE = null;

    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private CarsDao carsDao = CarsDaoImpl.getInstance();
    private ItemDao itemDao = ItemDaoImpl.getInstance();

    @Override
    public Order createOrder(long userId, long carsId, int quantity) {
        Order order = new Order();
        try {
            startTransaction();
            order.setIdClients(userId);

            Cars product = carsDao.get(carsId);
            if (quantity < 1) {
                quantity = 1;
            }
            order.setTotal(product.getPrice() * quantity);
            order = orderDao.save(order);

            Item item = new Item(order.getId(), carsId, quantity);
            itemDao.save(item);
            commit();
            return order;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Order " + order, e);
        }
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
    public List<Order> getByUserId(long userId) {
        try {
            startTransaction();
            List<Order> orders = orderDao.getByUserId(userId);
            for (Order order : orders) {
                List<Item> items = itemDao.getByOrderId(order.getId());
                order.setItems(items);
                double sum = 0;
                for (Item item : items) {
                    Cars product = carsDao.get(item.getProductId());
                    sum += product.getPrice() * item.getQuantity();
                }
                commit();
                order.setTotal(sum);
            }
            return orders;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Orders by userId" + userId);
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
