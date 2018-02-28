package project.dbSevice.executor;

import org.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by leonid on 19.05.17.
 */
public class HExecutor extends Executor
{
    Session session;

    public HExecutor(Session session)
    {
        this.session = session;
    }


    public <T> T execute(ResultHandlers<T, Session> handler) throws SQLException {
        T value = handler.handles(session);
        return value;
    }

}
