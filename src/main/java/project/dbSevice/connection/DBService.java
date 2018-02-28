package project.dbSevice.connection;

import project.dbSevice.dataSets.UsersDataset;
import java.sql.SQLException;

/**
 * Created by leonid on 24.05.17.
 */
public abstract class DBService {

    public abstract void printConnectInfo() throws SQLException;
    public abstract UsersDataset getUser(String login, String password) throws Exception;
    public abstract long addUser(String login, String password) throws Exception;
    public abstract boolean hasUser(String login) throws Exception;
}
