package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {
    private static UserService INSTANCE;
    private List<User> list = new ArrayList<>();
  //private UserJdbcDAO dao = UserJdbcDAO.getInstance();
  private UserDAO dao = UserHibernateDAO.getInstance();

    private UserService() {
    }

    public static UserService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public List<User> getAllUsers() {
        try {
            List<User> list = dao.getAllUsers();
            return list;
        } catch (DBException t) {
            return Collections.emptyList();
        }
    }

    public User getUserById(int id) {
        try {
            return dao.getUserById(id);
        } catch (DBException t) {
            return null;
        }
    }

    public boolean updateUser(int id, String name, int age) {
        try {
            dao.updateUser(id, name, age);
            return true;
        } catch (DBException e) {
            return false;
        }
    }

    public boolean deleteUserById(int id) {
        try{
            dao.deleteUserById(id);
            return true;
        } catch(DBException e){
            return false;
        }
    }
    public boolean addUser(User user) {
        try {
            dao.addUser(user);
            return true;
        } catch (SQLException | DBException e) {
            return false;
        }
    }
}
