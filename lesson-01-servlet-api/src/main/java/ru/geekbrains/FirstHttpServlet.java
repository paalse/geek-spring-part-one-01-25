package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class FirstHttpServlet extends HttpServlet {

    //--1 Получаем доступ к репозиторю пользователей описанному в BootstrapListener
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }
    //--1

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<table border=\"1\">");
        resp.getWriter().println("<caption>Пользователи</caption>");
        resp.getWriter().println("<tr>");
        resp.getWriter().println("<th>Id</th>");
        resp.getWriter().println("<th>Name</th>");
        resp.getWriter().println("</tr>");

        if (req.getPathInfo() == null) {
            for (User usr : userRepository.findAll()) {
                resp.getWriter().println("<tr>");
                resp.getWriter().println("<td>" + usr.getId() + "</td>");
                resp.getWriter().println("<td>" + usr.getUsername() + "</td>");
                resp.getWriter().println("</tr>");
            }
        } else {
            User usr = userRepository.findById(Long.parseLong(req.getPathInfo().replaceAll("/", "")));
            resp.getWriter().println("<tr>");
            resp.getWriter().println("<td>" + usr.getId() + "</td>");
            resp.getWriter().println("<td>" + usr.getUsername() + "</td>");
            resp.getWriter().println("</tr>");
        }
        resp.getWriter().println("</table>");
    }
}