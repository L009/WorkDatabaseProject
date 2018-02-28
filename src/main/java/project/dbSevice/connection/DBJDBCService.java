package project.dbSevice.connection;

import project.dbSevice.dao.JDBCUsersDAO;
import project.dbSevice.dataSets.UsersDataset;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by leonid on 19.05.17.
 */
public class DBJDBCService extends DBService {

    Connection connection;

    public DBJDBCService() throws SQLException {
        this.connection = getPosgreSQLConnection();
        new JDBCUsersDAO(this.connection).createUsersTable();
    }

    public static Connection getPosgreSQLConnection()
    {
        try {
            DriverManager.registerDriver((Driver)Class.forName("org.postgresql.Driver").newInstance());
            StringBuilder stringBuilder = getConfiguration();
            Connection connection =  DriverManager.getConnection(stringBuilder.toString());
            return connection;
        }
        catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException exception)
        {
            exception.getStackTrace();
            return null;
        }
    }


    private static StringBuilder getConfiguration()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("jdbc:postgresql://");
        stringBuilder.append("localhost:");
        stringBuilder.append("5432/");
        stringBuilder.append("users_enviroment?");
        stringBuilder.append("user=postgres&");
        stringBuilder.append("password=2345675l");

        System.out.println("URL: " + stringBuilder.toString());

        return stringBuilder;
    }



    @Override
    public void printConnectInfo() throws SQLException {
        System.out.println("DB name: " + this.connection.getMetaData().getDatabaseProductName());
        System.out.println("DB version: " + this.connection.getMetaData().getDatabaseProductVersion());
        System.out.println("Driver: " + this.connection.getMetaData().getDriverName());
        System.out.println("Autocomit: " + this.connection.getAutoCommit());
    }

    @Override
    public UsersDataset getUser(String login, String password) throws Exception {
        JDBCUsersDAO jdbcUsersDAO = new JDBCUsersDAO(this.connection);
        UsersDataset userDataset = jdbcUsersDAO.getUserByLoginAndPass(login, password);

        return userDataset;
    }

    @Override
    public long addUser(String login, String password) throws Exception {
        JDBCUsersDAO jdbcUsersDAO = new JDBCUsersDAO(this.connection);
        long id = jdbcUsersDAO.registerUser(login, password);

        return id;
    }

    @Override
    public boolean hasUser(String login) throws Exception {
        JDBCUsersDAO jdbcUsersDAO = new JDBCUsersDAO(connection);
        return  jdbcUsersDAO.hasUserByLogin(login);
    }
}
