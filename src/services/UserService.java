package services;

import entities.Cars;
import entities.Item;
import entities.User;

import java.util.List;

/**
 * Class UserService
 *
 * Created by yslabko on 08/11/2017.
 */
public interface UserService {
    User save(User user);

    User getByLogin(String login);

    List<User> getAll();
}
