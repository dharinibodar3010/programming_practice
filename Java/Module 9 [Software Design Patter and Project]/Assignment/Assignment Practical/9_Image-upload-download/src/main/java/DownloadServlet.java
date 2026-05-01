import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        String path = getServletContext().getRealPath("") + "images" + File.separator + name;

        FileInputStream fis = new FileInputStream(path);

        response.setContentType("image/jpeg");
        response.setHeader("Content-Disposition", "attachment; filename=" + name);

        byte[] b = new byte[1024];
        int i;

        while ((i = fis.read(b)) != -1) {
            response.getOutputStream().write(b, 0, i);
        }

        fis.close();
    }
}