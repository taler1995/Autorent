package dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import db.ConnectionManager;
import entities.User;

/**
 * Class UserDaoImpl
 * <p>
 * Created by yslabko on 08/11/2017.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
    private static volatile UserDao INSTANCE = null;

    private static final String getUser = "SELECT * FROM USER WHERE LOGIN=?";
    private static final String saveUserQuery = "INSERT INTO USER (NAME,SURNAME,LOGIN,PASSWORD,BIRTHDAY, PASSPORT_LETT,PASSPORT_ID,COUNTRY,IDENTIFICATION_NUMBER,DRIVING_EXPERIENCE,NUMBER_OF_PHONE,EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
    private static final String updateUserQuery = "UPDATE USER SET NAME=?, SURNAME=?,LOGIN=?,PASSWORD=?,BIRTHDAY=?, PASSPORT_LETT=?, PASSPORT_ID=?, COUNTRY=?, IDENTIFICATION_NUMBER=?, DRIVING_EXPERIENCE=?, NUMBER_OF_PHONE=?, EMAIL=? WHERE USER_ID=?";
    private static final String getUserQuery = "SELECT * FROM USER WHERE USER_ID=?";
    private static final String deleteUserQuery = "DELETE FROM USER WHERE USER_ID=?";
    private static final String getAllUserQuery = "SELECT * FROM USER";
    private static final String deleteAllDamage = "DELETE FROM USER";
    private static final String countAllUser = "SELECT COUNT(NAME) FROM USER";

    private PreparedStatement psGetByLogin;
    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;
    private PreparedStatement psDeleteAll;
    private PreparedStatement psCount;
    {
        try {
            psSave = ConnectionManager.getConnection().prepareStatement(saveUserQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionManager.getConnection().prepareStatement(updateUserQuery);
            psGet = ConnectionManager.getConnection().prepareStatement(getUserQuery);
            psGetAll = ConnectionManager.getConnection().prepareStatement(getAllUserQuery);
            psCount = ConnectionManager.getConnection().prepareStatement(countAllUser);
            psDelete = ConnectionManager.getConnection().prepareStatement(deleteUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private UserDaoImpl() {

    }

    @Override
    public User getByLogin(String login) throws SQLException {
        psGetByLogin = prepareStatement(getUser);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }


    @Override
    public User save(User user) throws SQLException {
        psSave.setString(1, user.getName());
        psSave.setString(2, user.getSurname());
        psSave.setString(3, user.getLogin());
        psSave.setString(4, user.getPassword());
        psSave.setDate(5, user.getBirthday());
        psSave.setString(6, user.getPasportLett());
        psSave.setLong(7, user.getPassportId());
        psSave.setString(8, user.getCountry());
        psSave.setString(9, user.getIdentification());
        psSave.setInt(10, user.getDrivingExp());
        /*psSave.setInt(8, clients.getNumberOfOrders());
        psSave.setInt(9, clients.getNumberOfAccidents());*/
        psSave.setLong(11, user.getPhoneNumber());
        psSave.setString(12, user.getEmail());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }

    @Override
    public User get(Serializable id) throws SQLException {
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
    public void update(User user) throws SQLException {
        psUpdate.setLong(13, user.getId());
        psUpdate.setString(1, user.getName());
        psUpdate.setString(2, user.getSurname());
        psUpdate.setString(3, user.getLogin());
        psUpdate.setString(4, user.getPassword());
        psUpdate.setDate(5,user.getBirthday());
        psUpdate.setString(6, user.getPasportLett());
        psUpdate.setLong(7, user.getPassportId());
        psUpdate.setString(8, user.getCountry());
        psUpdate.setString(9, user.getIdentification());
        psUpdate.setInt(10, user.getDrivingExp());
        // psUpdate.setInt(8, clients.getNumberOfOrders());
        // psUpdate.setInt(9, clients.getNumberOfAccidents());
        psUpdate.setLong(11, user.getPhoneNumber());
        psUpdate.setString(12, user.getEmail());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private User populateEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong(1));
        entity.setName(rs.getString(2));
        entity.setSurname(rs.getString(3));
        entity.setLogin(rs.getString(4));
        entity.setPassword(rs.getString(5));
        entity.setBirthday(rs.getDate(6));
        entity.setStatus(rs.getString(7));
        entity.setRole(rs.getString(8));
        entity.setPasportLett(rs.getString(9));
        entity.setPassportId(rs.getInt(10));
        entity.setCountry(rs.getString(11));
        entity.setIdentification(rs.getString(12));
        entity.setDrivingExp(rs.getInt(13));
        entity.setPhoneNumber(rs.getInt(14));
        entity.setEmail(rs.getString(15));
        return entity;
    }

    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (CarsDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }

        return userDao;
    }

    @Override
    public int getCountClients() throws SQLException {
        int count = 0;
        psCount.execute();
        ResultSet rs = psCount.getResultSet();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        close(rs);
        return count;
    }
}