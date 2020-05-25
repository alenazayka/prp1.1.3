package dao;

import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers() throws DBException;
    public void deleteUserById(int id) throws DBException;
    public void updateUser(int id, String name, int age) throws DBException;
    public User getUserById(int id) throws DBException;
    public void addUser(User user) throws SQLException, DBException;
}
