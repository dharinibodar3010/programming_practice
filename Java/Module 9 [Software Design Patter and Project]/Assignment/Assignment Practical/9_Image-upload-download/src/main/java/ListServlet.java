import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = getServletContext().getRealPath("") + "images";

        File folder = new File(path);

        File[] files = folder.listFiles();

        response.setContentType("text/html");

        response.getWriter().println("<h2>Images</h2>");

        for (File file : files) {
            String name = file.getName();
            response.getWriter().println(
                "<a href='download?name=" + name + "'>" + name + "</a><br>"
            );
        }
    }
}