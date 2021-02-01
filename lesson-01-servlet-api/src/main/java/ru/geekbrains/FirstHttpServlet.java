package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user-all")
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
        PrintWriter pw = resp.getWriter();

        pw.println("<table border=\"1\">");
        pw.println("<caption>Пользователи</caption>");
        pw.println("<tr>");
        pw.println("<th>Id</th>");
        pw.println("<th>Name</th>");
        pw.println("</tr>");

        for (User usr : userRepository.findAll()) {
            pw.println("<tr>");
            pw.println("<td>" + usr.getId() + "</td>");
            pw.println("<td><a href='" + getServletContext().getContextPath()+"/user/"+ usr.getId()+ "'>" + usr.getUsername() + "</td>");
            pw.println("</tr>");
        }

        resp.getWriter().println("</table>");
    }
}