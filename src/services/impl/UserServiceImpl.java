package services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import db.ConnectionManager;
import entities.User;
import services.ServiceException;
import services.UserService;

/**
 * Class UserServiceImpl
 *
 * Created by yslabko on 08/11/2017.
 */
public class UserServiceImpl extends AbstractService implements UserService {
    private static volatile UserService INSTANCE = null;
    private UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User save(User user) throws SQLException {
        try {
            Connection connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            user = userDao.save(user);
            ConnectionManager.getConnection().commit();
        } catch (SQLException e) {
            ConnectionManager.getConnection().rollback();
            throw new ServiceException("Error creating Item" + user);
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        try {
            return userDao.getByLogin(login);
        } catch (SQLException e) {
            throw new ServiceException("Error getting User by login" + login);
        }
    }

    @Override
    public User getById(long Id) {
        try {
            return userDao.getById(Id);
        }
        catch (SQLException e) {
            throw new ServiceException("Error getting User by login" + Id);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            startTransaction();
            List<User> list = userDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }

        return userService;
    }
}
