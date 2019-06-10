package services;

import entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Class UserService
 *
 * Created by yslabko on 08/11/2017.
 */
public interface UserService {
    User save(User user) throws SQLException;

    User getByLogin(String login);
    User getById(long Id);

    List<User> getAll();
}
