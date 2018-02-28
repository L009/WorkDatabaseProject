package project.dbSevice.dao;

import project.dbSevice.dataSets.UsersDataset;
import project.dbSevice.executor.JDBSExecutor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by leonid on 09.05.17.
 */
public class JDBCUsersDAO extends UsersDAO {

    JDBSExecutor jdbsExecutor;

    public JDBCUsersDAO(Connection connection)
    {
        this.jdbsExecutor = new JDBSExecutor(connection);
    }


    @Override
    public long registerUser(String login, String password) throws SQLException {
        return
                jdbsExecutor.execUpdate("insert into users(login, password) " +
                        "values('" + login + "','" + password + "')");
    }

    @Override
    public UsersDataset getUserByLoginAndPass(String login, String password) throws SQLException  {
        return jdbsExecutor.execute(
                "select * from users where login = '"
                + login + "' and password = '" + password + "'",
                (resSet)->
                {
                        if (resSet.next()==false) return null;
                        return new UsersDataset(
                                resSet.getLong(1),
                                resSet.getString(2),
                                resSet.getString(3));
                }
        );
    }

    @Override
    public boolean hasUserByLogin(String login) throws SQLException {
        return jdbsExecutor.execute(
                "select * from users where login = '"
                        + login +"'",
                (resSet)->
                {
                    return resSet.next();
                }
        );
    }


    public void createUsersTable() throws SQLException {
        jdbsExecutor.execUpdate("create table " +
                "if not exists users(id bigserial primary key, login varchar(256), " +
                "password varchar(256))");
    }
}
