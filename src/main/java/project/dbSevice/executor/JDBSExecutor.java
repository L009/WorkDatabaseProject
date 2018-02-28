package project.dbSevice.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**arguments]
leonid@leonid-HP:~$

 * Created by leonid on 19.05.17.
 */
public class JDBSExecutor extends Executor {

    Connection connection;

    public JDBSExecutor(Connection connection)
    {
        this.connection = connection;
    }

    public <T> T execute(String query, ResultHandlers<T, ResultSet> handler) throws SQLException
    {
        System.out.println(query);
        Statement statement = connection.createStatement();
        T value;

        try {
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            value = handler.handles(result);
            result.close();
        }
        finally {
            statement.close();
        }
        return value;
    }


    public long execUpdate(String query) throws SQLException
    {
        System.out.println(query);
        Statement statement = connection.createStatement();
        long value = -1;

        try {
            value = statement.executeUpdate(query);
        }
        catch (Exception exec)
        {
            exec.printStackTrace();
        }

        finally {
            statement.close();
        }

        return value;
    }

}
