package project.dbSevice.servlets;

import project.dbSevice.connection.DBHService;
import project.dbSevice.connection.DBJDBCService;
import project.dbSevice.connection.DBService;
import project.dbSevice.dao.UsersDAO;
import project.dbSevice.dao.HUsersDAO;
import project.dbSevice.dataSets.UsersDataset;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

/**
 * Created by leonid on 19.05.17.
 */
public class BaseServlet extends HttpServlet {

    DBService dbService;

    public BaseServlet() throws SQLException {
        this.dbService = new DBJDBCService();
    }
}
