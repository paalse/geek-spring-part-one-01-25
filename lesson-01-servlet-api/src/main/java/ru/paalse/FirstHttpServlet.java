package ru.paalse;

import ru.paalse.persist.User;
import ru.paalse.persist.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/first-http-servlet/*")
public class FirstHttpServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Привет от сервлета !!</h1>");
        resp.getWriter().println("<p>ContextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>ServletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>PathInfo: " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>QueryString: " + req.getQueryString() + "</p>");
        resp.getWriter().println("<p>Param1: " + req.getParameter("param1") + "</p>");
        resp.getWriter().println("<p>Param1: " + req.getParameter("param2") + "</p>");



        String[] str = req.getPathInfo().split("\\/d");
        resp.getWriter().println("<p>" + str[0] + "</p>");
        resp.getWriter().println("<p>" + str[1] + "</p>");

        resp.getWriter().println("<table border=2>");
        resp.getWriter().println("<tr><th>Id</th><td>Name</th></tr>");

        if (str.length == 0) {

            for (User user : userRepository.findAll()) {
                resp.getWriter().println("<tr><td>" + user.getId() + "</td><td>" + user.getUsername() + "</td>");
            }
        } else if (str.length > 1 && str[0] == "user") {

            long n = Long.valueOf(str[1]);
            resp.getWriter().println("<tr><td>" + userRepository.findById(n).getId() + "</td><td>" + userRepository.findById(n).getUsername() + "</td>");
        }
        resp.getWriter().println("</table>");
    }
}

