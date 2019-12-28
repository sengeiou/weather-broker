package servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(AddServlet.class.getName());

    @Inject
    private RequestService requestService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("IN GET");
        request.getRequestDispatcher("index.jsf").forward(request, response);
        //Получение параметра из поля на странице запроса.
        String city = request.getParameter("city");
        try {
            String result = requestService.sendMessage(city);
            response.getWriter().println("OK. SERVICE IS NULL:" + (requestService == null) + "; SERVICE RESULT:" + result);
            LOG.info("dick");

        } catch (RuntimeException e) {
            response.getWriter().println("NOT OK");
            LOG.info("Field \"City\" is empty");
        }
    }
}
