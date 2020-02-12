package org.nachtvaohal.servlet;

import org.nachtvaohal.service.SendMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger LOG = Logger.getLogger(AddServlet.class.getName());

    @Inject
    private SendMessage sendMessage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
//        // todo проверка на латиницу, т.к. кириллицу yahoo не воспринимает
//        String city = request.getParameter("city");
//        request.setAttribute("city", city);
//        request.getRequestDispatcher("views/success.jsp").forward(request, response);

//        try {
//            //sendMessage.sendMessage(city);
//            request.setAttribute("city", city);
//            request.getRequestDispatcher("success.jsp").forward(request, response);
//        // TODO Какое то другое исключение должно быть наверно
//        } catch (RuntimeException e) {
//            response.sendRedirect("error.jsp");
//            LOG.info("Field \"City\" is empty");
//        }
    }

    // todo проверка на латиницу, т.к. кириллицу yahoo не воспринимает

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        // todo валидация
        sendMessage.sendMessage(city);
        request.setAttribute("city", city);
        request.getRequestDispatcher("views/success.jsp").forward(request, response);
    }

    private boolean validateString(String input) {
        String regex = "[a-zA-Z_0-9\\s]";
        return Pattern.matches(regex, input);
    }

    private String removeSpaces(String input) {
        String regex = "\\s";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }

}
