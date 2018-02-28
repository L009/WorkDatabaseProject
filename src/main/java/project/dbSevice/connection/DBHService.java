package project.dbSevice.connection;

import org.eclipse.jetty.server.session.SessionCache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import project.dbSevice.dao.HUsersDAO;
import project.dbSevice.dao.UsersDAO;
import project.dbSevice.dataSets.UsersDataset;
import  org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by leonid on 19.05.17.
 */
@SuppressWarnings({"UnusedDeclaration"})
public class DBHService extends DBService {

    private  final String showSql = "true";
    private final String hbm2ddl = "create";

    private SessionFactory sessionFactory;


    public DBHService()
    {
        Configuration configuration = getPostgreSQLConfiguration();
        this.sessionFactory = getSessionFactory(configuration);
    }



    private Configuration getPostgreSQLConfiguration()
    {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataset.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "2345675l");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/users_enviroment");

        configuration.setProperty("hibernate.show_sql", showSql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);

        return configuration;
    }



    public static SessionFactory getSessionFactory(Configuration configuration)
    {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registryBuilder.build();

        SessionFactory sessionFactory= configuration.buildSessionFactory(serviceRegistry);


        return sessionFactory;
    }


    @Override
    public void printConnectInfo() throws SQLException
    {
        Connection connection = sessionFactory.getSessionFactoryOptions().getServiceRegistry().
                getService(ConnectionProvider.class).getConnection();

        System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
        System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
        System.out.println("Driver: " + connection.getMetaData().getDriverName());
        System.out.println("Autocomit: " + connection.getAutoCommit());
    }



    @Override
    public boolean hasUser(String login) throws Exception {
        Session session = sessionFactory.openSession();
        UsersDAO usersDAO = new HUsersDAO(session);

        return usersDAO.hasUserByLogin(login);
    }

    @Override
    public UsersDataset getUser(String login, String password) throws Exception
    {
        Session session = sessionFactory.openSession();
        HUsersDAO hUsersDAO = new HUsersDAO(session);
        UsersDataset userDataset = hUsersDAO.getUserByLoginAndPass(login, password);
        session.close();

        return userDataset;
    }



    @Override
    public long addUser(String login, String password) throws Exception
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long id = -1;

        try
        {
            HUsersDAO hUsersDAO = new HUsersDAO(session);
            id = hUsersDAO.registerUser(login, password);
            transaction.commit();
        }
        catch (Exception exception)
        {
            transaction.rollback();
        }
        finally {
            session.close();
        }

        return id;
    }
}












