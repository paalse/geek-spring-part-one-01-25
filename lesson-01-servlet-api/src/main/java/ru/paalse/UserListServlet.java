package ru.paalse;

import ru.paalse.persist.User;
import ru.paalse.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/users")
public class UserListServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<table border=2>");
        resp.getWriter().println("<tr><th>Id</th><td>Name</th></tr>");

        for (User user : userRepository.findAll()) {
            resp.getWriter().println("<tr><td>" + user.getId() + "</td><td><a href='"+ getServletContext().getContextPath() +"/user/" +user.getId() + "'>" + user.getUsername() + "</a></td>");
        }

        resp.getWriter().println("</table>");
    }
}
