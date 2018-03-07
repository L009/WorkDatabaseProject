package project.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import project.dbSevice.servlets.SignInServlet;
import project.dbSevice.servlets.SignUpServlet;

import java.sql.Driver;

/**0
 * Created by leonid on 09.05.17.
 */
public class Main {

    public static void main(String[] args) throws Exception
    {
        Driver dd=(Driver)Class.forName("org.postgresql.Driver").newInstance();
        ServletContextHandler servletCH = new ServletContextHandler(ServletContextHandler.SESSIONS);

        servletCH.addServlet(new ServletHolder(new SignUpServlet()), "/signup");
        servletCH.addServlet(new ServletHolder(new SignInServlet()), "/signin");

        HandlerList listHandler = new HandlerList();
        listHandler.setHandlers(new Handler[]{servletCH});

        Server server = new Server(8080);
        server.setHandler(listHandler);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();

        System.out.println();
    }
}
