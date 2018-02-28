package project.dbSevice.executor;

import java.sql.SQLException;

/**
 * Created by leonid on 20.05.17.
 */

public interface ResultHandlers<T, U> {
    T handles(U input) throws SQLException;
}