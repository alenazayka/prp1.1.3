package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import exception.DBException;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;
    private SessionFactory sessionFactory;
    private static volatile UserHibernateDAO INSTANCE;

    public static UserDAO getInstance() {
        UserHibernateDAO result = INSTANCE;
        if (result != null) {
            return result;
        }
        synchronized (UserHibernateDAO.class) {
            if (INSTANCE == null) {
                INSTANCE = new UserHibernateDAO();
            }
            return INSTANCE;
        }
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_users?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "password12345");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update"); //Change to create
        configuration.setProperty("hibernate.jdbc.time_zone", "UTC");
        return configuration;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private UserHibernateDAO() {
        sessionFactory = createSessionFactory();
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        List<User> list = session.createQuery("FROM User").list();
        session.close();
        return list;

    }

    @Override
    public void deleteUserById(int id) throws DBException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(int id, String name, int age) throws DBException {
        Session session = sessionFactory.openSession();
        String hql = "Update User SET name = :nameParam, age = :ageParam " +
                "WHERE id = :idParam";
        Transaction transaction = session.beginTransaction();
        session.createQuery(hql)
                .setParameter("nameParam", name)
                .setParameter("ageParam", age)
                .setParameter("idParam", id)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(int id) throws DBException {
        Session session = sessionFactory.openSession();
        User user =(User)session.createQuery("FROM User WHERE id = :id ")
        .setParameter("id", id)
        .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void addUser(User user) throws DBException {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }
}
