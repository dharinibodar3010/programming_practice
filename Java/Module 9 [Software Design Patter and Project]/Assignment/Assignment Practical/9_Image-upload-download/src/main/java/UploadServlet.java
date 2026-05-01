import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part p = request.getPart("file");

        String fileName = p.getSubmittedFileName();

        String path = getServletContext().getRealPath("") + "images";

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        p.write(path + File.separator + fileName);

        response.getWriter().println("File Uploaded");
    }
}