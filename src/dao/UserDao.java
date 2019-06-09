package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Cars;
import entities.User;

/**
 * Class UserDao
 *
 * Created by yslabko on 08/11/2017.
 */
public interface UserDao extends DAO<User> {
    User getByLogin(String login) throws SQLException;
    User getById(long Id) throws SQLException;
    List<User> getAll() throws SQLException;
    int getCountClients() throws SQLException;

}
