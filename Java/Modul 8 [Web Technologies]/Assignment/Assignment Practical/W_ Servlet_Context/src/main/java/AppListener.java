
/*Create a web application listener that logs application start and stop events.*/

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    // Called when application starts
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application Started: " + sce.getServletContext().getContextPath());
    }

    // Called when application stops
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application Stopped: " + sce.getServletContext().getContextPath());
    }
}