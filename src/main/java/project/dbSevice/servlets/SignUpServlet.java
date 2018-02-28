package project.dbSevice.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by leonid on 11.05.17.
 */
public class SignUpServlet extends BaseServlet {

    public SignUpServlet() throws SQLException {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if(login!=null && password!=null)
            {
                try {
                    if (!dbService.hasUser(login)) dbService.addUser(login, password);
                } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
    }
}
