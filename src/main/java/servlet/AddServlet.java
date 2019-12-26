package servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AddServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(AddServlet.class.getName());
    // зачем?
    private static final long serialVersionUID = 1L;

    @Inject
    private RequestService requestService;

    public AddServlet(){}

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("IN GET");

        request.getRequestDispatcher("/").forward(request, response);
        //Получение параметра из поля на странице запроса.
        String city = request.getParameter("city");
        try {
            requestService.sendMessage(city);
            LOG.info("dick");
        } catch (RuntimeException e) {
            LOG.info("Field \"City\" is empty");
        }
    }
}
