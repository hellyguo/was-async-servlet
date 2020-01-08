package was.async.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Helly Guo
 * <p>
 * Created on 1/7/20 4:09 PM
 */
@WebServlet(name = "SyncServlet", urlPatterns = "/sync.do")
public class SyncServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printResult(resp, "sync");
    }
}
