package was.async.demo;

import javax.servlet.AsyncContext;
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
@WebServlet(asyncSupported = true, name = "AsyncServlet", urlPatterns = "/async.do")
public class AsyncServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.isAsyncSupported()) {
            AsyncContext async = req.startAsync();
            async.start(() -> {
                HttpServletResponse asyncResponse = (HttpServletResponse) async.getResponse();
                printResult(asyncResponse, "async");
                async.complete();
            });
        } else {
            printResult(resp, "sync");
        }
    }
}
