package project.dbSevice.dao;

import project.dbSevice.dataSets.UsersDataset;
import project.dbSevice.executor.Executor;

import java.sql.SQLException;

/**
 * Created by leonid on 09.05.17.
 */
public abstract class UsersDAO {

    public abstract long registerUser(String login, String password) throws SQLException;

    public abstract UsersDataset getUserByLoginAndPass(String login, String password) throws SQLException;

    public abstract boolean hasUserByLogin(String login) throws SQLException;
}