package services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import entities.Item;
import entities.Order;

/**
 * Class OrderService
 *
 * Created by yslabko on 08/09/2017.
 */
public interface OrderService {
    Order save(Order item);
        Order get(Serializable id);
    void update(Order order);
    int delete(Serializable id);

    List<Order> getAll();
    List<Order> getByUserId(long userId);
}
