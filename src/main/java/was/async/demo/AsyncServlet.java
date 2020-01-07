package was.async.demo;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Helly Guo
 * <p>
 * Created on 1/7/20 4:09 PM
 */
public class AsyncServlet extends HttpServlet {
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
                printResult(asyncResponse, "success");
                async.complete();
            });
        } else {
            printResult(resp, "failed");
        }
    }

    private void printResult(HttpServletResponse response, String result) {
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
