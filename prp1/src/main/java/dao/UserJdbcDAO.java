package dao;

import exception.DBException;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO{
    private static UserJdbcDAO INSTANCE;
    private Connection connection;

    public static UserJdbcDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJdbcDAO();
        }
        return INSTANCE;
    }


    private UserJdbcDAO()
    {
        this.connection = getMysqlConnection();
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("mydbtest?").          //db name
                    append("user=root&").          //login
                    append("password=password12345").       //password
                    append("&serverTimezone=UTC");   //setup server time

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public List<User> getAllUsers() throws DBException {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int age = result.getInt(3);
                User user = new User(id, name, age);
                list.add(user);
            }
        } catch (SQLException t) {
            throw new DBException(t);
        }
        return list;
    }
    public void deleteUserById(int id) throws DBException {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("delete from users where id=" + id);
            stmt.close();
        } catch (SQLException t) {
            throw new DBException(t);
        }

    }
    public void updateUser(int id, String name, int age) throws DBException {
        try {
            PreparedStatement pstmt = connection.prepareStatement("update users set name = ?, age = ? where id = ?");
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setLong(3, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException t) {
            throw new DBException(t);
        }

    }
    public User getUserById(int id) throws DBException {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users where id=" + id);
            ResultSet result = stmt.getResultSet();
            result.next();
            String name = result.getString("name");
            String surname = result.getString("surname");
            int age = result.getInt("age");
            result.close();
            stmt.close();
            return new User(id, name, age);
        } catch (SQLException t) {
            throw new DBException(t);
        }
    }
    public void addUser(User user) throws SQLException {
        String name = user.getName();
        int age = user.getAge();

        Statement stmt = connection.createStatement();
        stmt.execute("insert into users (name, age) values('" +
                name + "', '" + age + "')");
        stmt.close();
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id int auto_increment, name varchar(45), age int, primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}

