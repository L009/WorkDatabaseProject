package project.dbSevice.servlets;

import project.dbSevice.dataSets.UsersDataset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by leonid on 11.05.17.
 */
public class SignInServlet extends BaseServlet {

    public SignInServlet() throws SQLException {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!(login ==null || login.isEmpty()
                || password == null || password.isEmpty()))
        {
            UsersDataset usersDataset = null;
            try {
                usersDataset = dbService.getUser(login, password);
            }
            catch (Exception e) {
                e.printStackTrace();
            }


            resp.setContentType("text/html; charset=utf-8");
            if (usersDataset!=null)
            {
                resp.getWriter().println("Authorized: " + usersDataset.login);
                resp.setStatus(200);
            }
            else
            {
                resp.getWriter().println("Unauthorized");
                resp.setStatus(401);
            }

        }
    }
}
