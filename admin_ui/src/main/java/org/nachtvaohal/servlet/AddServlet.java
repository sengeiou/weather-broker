package org.nachtvaohal.servlet;

import org.nachtvaohal.service.SendMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(AddServlet.class.getName());

    @Inject
    private SendMessage sendMessage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
        //Получение параметра из поля на странице запроса.
        // todo проверка на латиницу, т.к. кириллицу yahoo не воспринимает
        String city = request.getParameter("city");

        try {
            sendMessage.sendMessage(city);

        // TODO Какое то другое исключение должно быть наверно
        } catch (RuntimeException e) {
            LOG.info("Field \"City\" is empty");
        }
    }

    private boolean validateString(String input) {
        return true;
    }

    private String replaceUrlSpaces(String input) {
        String regex = "\\s";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("%20");
    }

}
