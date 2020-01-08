package was.async.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Helly Guo
 * <p>
 * Created on 1/7/20 4:09 PM
 */
abstract class AbstractServlet extends HttpServlet {

    void printResult(HttpServletResponse response, String result) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.write("<h1>" + result + "</h1>");
            writer.flush();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}
